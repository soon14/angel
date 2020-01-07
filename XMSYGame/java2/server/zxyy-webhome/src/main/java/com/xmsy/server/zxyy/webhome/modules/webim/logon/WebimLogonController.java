package com.xmsy.server.zxyy.webhome.modules.webim.logon;

import cn.hutool.http.HttpRequest;
import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.xmsy.common.bean.message.RegisterMessage;
import com.xmsy.common.define.exception.ParamInvalidException;
import com.xmsy.network.sms.SendSMS;
import com.xmsy.server.zxyy.webhome.cache.LocalContentCache;
import com.xmsy.server.zxyy.webhome.common.annotation.AppRegisteredRepeat;
import com.xmsy.server.zxyy.webhome.common.annotation.LoginRepeat;
import com.xmsy.server.zxyy.webhome.common.annotation.WebRegisteredRepeat;
import com.xmsy.server.zxyy.webhome.common.exception.RRException;
import com.xmsy.server.zxyy.webhome.common.utils.ErrorCode;
import com.xmsy.server.zxyy.webhome.common.utils.R;
import com.xmsy.server.zxyy.webhome.constant.HallUrlConstant;
import com.xmsy.server.zxyy.webhome.constant.SysConstant;
import com.xmsy.server.zxyy.webhome.constant.ThirdPartyDef;
import com.xmsy.server.zxyy.webhome.modules.app.login.param.OAuth2Params;
import com.xmsy.server.zxyy.webhome.modules.app.login.result.UserDetail;
import com.xmsy.server.zxyy.webhome.modules.manager.sysconfig.service.SysConfigService;
import com.xmsy.server.zxyy.webhome.modules.manager.sysregisternecessary.entity.SysRegisterNecessaryEntity;
import com.xmsy.server.zxyy.webhome.modules.manager.sysregisternecessary.service.SysRegisterNecessaryService;
import com.xmsy.server.zxyy.webhome.modules.manager.user.entity.UserEntity;
import com.xmsy.server.zxyy.webhome.modules.manager.user.service.UserService;
import com.xmsy.server.zxyy.webhome.modules.manager.userlogin.entity.UserLoginEntity;
import com.xmsy.server.zxyy.webhome.modules.manager.userlogin.service.UserLoginService;
import com.xmsy.server.zxyy.webhome.modules.manager.userrecommendation.entity.UserRecommendationEntity;
import com.xmsy.server.zxyy.webhome.modules.manager.userrecommendation.service.UserRecommendationService;
import com.xmsy.server.zxyy.webhome.modules.web.login.entity.LoginEntity;
import com.xmsy.server.zxyy.webhome.modules.webim.logon.entity.WebimPhoneRegisterEntity;
import com.xmsy.server.zxyy.webhome.mq.MqClient;
import com.xmsy.server.zxyy.webhome.oauth2.OAuth2;
import com.xmsy.server.zxyy.webhome.oauth2.OAuth2Utils;
import com.xmsy.server.zxyy.webhome.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * 登陆
 *
 * @author xiaoliu
 * @email xxxxx
 * @date 2018-06-19
 */
@Slf4j
@RestController
@RequestMapping("webim/v1/logon")
public class WebimLogonController {

	@Autowired
	private UserService userService;
	@Autowired
	private SysConfigService sysConfigService;
	@Autowired
	private UserLoginService userLoginService;
	@Resource
	private OAuth2Utils oauth2Utils;
	@Autowired
	private LocalContentCache localContentCache;
	@Autowired
	private UserRecommendationService userRecommendationService;
	@Autowired
	private SysRegisterNecessaryService sysRegisterNecessaryService;
	@Autowired
	private MqClient mqClient;

	/**
	 * web获取手机验证码
	 */
	@GetMapping("/verification-code")
	public R phoneVerificationCode(@RequestParam("phoneNo") String phoneNo) {
		if (!VerificationUitl.phoneVerification(phoneNo)) {
			throw new RRException(ErrorCode.UserErrorCode.USER_PHONE_ERRO.getErrMsg(),
					ErrorCode.UserErrorCode.USER_PHONE_ERRO.getCode());
		}
		if (null != localContentCache.getPhoneVerificationCode(phoneNo)) {
			throw new RRException(ErrorCode.UserErrorCode.USER_VALIDATION_CODE_LIMIT_ERRO.getErrMsg(),
					ErrorCode.UserErrorCode.USER_VALIDATION_CODE_LIMIT_ERRO.getCode());
		}
		//log.info("[APP获取手机验证码] phoneNo {}", phoneNo);
		String account = sysConfigService.selectByParamKey(ThirdPartyDef.BENSI_SMS, ThirdPartyDef.ACCOUNT);
		String password = sysConfigService.selectByParamKey(ThirdPartyDef.BENSI_SMS, ThirdPartyDef.PASSWORD);
		String url = sysConfigService.selectByParamKey(ThirdPartyDef.BENSI_SMS, ThirdPartyDef.URL);
		String extno = sysConfigService.selectByParamKey(ThirdPartyDef.BENSI_SMS, ThirdPartyDef.EXTNO);
		String smsTemplate = sysConfigService.selectByParamKey(ThirdPartyDef.BENSI_SMS, ThirdPartyDef.SMS_TEMPLATE);
		if (StringUtils.isEmpty(account) || StringUtils.isEmpty(password) || StringUtils.isEmpty(url)
				|| StringUtils.isEmpty(extno) || StringUtils.isEmpty(smsTemplate)) {
			log.error("[APP获取手机验证码] url {},account {},password {},extno {},smsTemplate {}", url, account, password,
					extno, smsTemplate);
			throw new RRException(ErrorCode.ThirdPartyErrorCode.SMS_NETWORK_ERRO.getErrMsg(),
					ErrorCode.ThirdPartyErrorCode.SMS_NETWORK_ERRO.getCode());
		}
		String randomCode = VerificationCodeUitl.createVerificationCode();
		String content = String.format(smsTemplate, randomCode);
		if (SendSMS.isSuccess(SendSMS.sendSMS(url, phoneNo, account, password, content, extno))) {
			localContentCache.putPhoneVerificationCode(phoneNo, randomCode);
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 验证邀请码是否存在
	 */
	@GetMapping("/verification/invitation-code")
	public R verificationInvitationCode(@RequestParam("invitationCode") String invitationCode) {
		UserRecommendationEntity entity = new UserRecommendationEntity();
		entity.setRecommendationCode(invitationCode);
		List<UserRecommendationEntity> list = userRecommendationService
				.selectList(new EntityWrapper<>(entity));
		if (CollectionUtils.isEmpty(list)) {
			throw new RRException(ErrorCode.InvitationCodeErrorCode.RECOMMENDATION_CODE_VERIFICATION_ERRO.getErrMsg(),
					ErrorCode.InvitationCodeErrorCode.RECOMMENDATION_CODE_VERIFICATION_ERRO.getCode());
		}
		return R.ok();
	}

	/**
	 * 获取注册手机号是否必填写
	 * @return
	 */
	@GetMapping("/register/phone/necessary")
	public R registerPhoneNecessary() {
		//log.info("[APP手机注册] 获取手机号是否必填");
		SysRegisterNecessaryEntity sysRegisterNecessaryEntity = sysRegisterNecessaryService.selectById(SysConstant.REGISTER_PHONE);
		return R.ok().put("data", sysRegisterNecessaryEntity.getNecessary());
	}

	/**
	 * web手机注册
	 */
	@WebRegisteredRepeat("官网注册表单重复提交验证")
	@PostMapping("/register/phone")
	public R phoneRegister(@RequestBody @Valid WebimPhoneRegisterEntity phoneRegisterEntity,
			HttpServletRequest httpServletRequest) {
		String ip = IpUtil.getIPAddress(httpServletRequest);
		phoneRegisterEntity.setRegisterIp(ip);
		UserEntity user = userService.webimPhoneRegister(phoneRegisterEntity);
		RegisterMessage message = new RegisterMessage(user.getId(), user.getAccount(), ip,
				phoneRegisterEntity.getDeviceType(), phoneRegisterEntity.getRegisterDeviceCode(),
				phoneRegisterEntity.getInvitationCode(), phoneRegisterEntity.getHallId(),
				phoneRegisterEntity.getEdition());
		message.setToken(user.getToken());
		mqClient.registerPush(message);
		return R.ok().put("token", user.getToken());
	}

	/**
	 * 第三方登陆
	 */
	@AppRegisteredRepeat("APP第三方登入注册表单重复提交验证")
	@PostMapping("/oauth2/login")
	public R oauth2Login(@RequestBody OAuth2Params oauth2Param, HttpServletRequest httpServletRequest)
			throws Exception {
		//log.info("oauth2Param {}", oauth2Param);
		String ip = IpUtil.getIPAddress(httpServletRequest);
		OAuth2 oauth2 = oauth2Utils.getOAuth2(oauth2Param.getType());
		if (null == oauth2) {
			throw new RRException(ErrorCode.UserErrorCode.USER_REGISTER_TYPE_ERRO.getErrMsg(),
					ErrorCode.UserErrorCode.USER_REGISTER_TYPE_ERRO.getCode());
		}
		String respone = oauth2.getAccessToken(oauth2Param.getCode());
		log.info("respone {}", respone);
		String access_token = JSON.parseObject(respone).getString("access_token");
		String openId = JSON.parseObject(respone).getString("openid");
		UserEntity entity = new UserEntity();
		entity.setOpenId(openId);
		if (StringUtils.isEmpty(openId)) {
			throw new RRException(ErrorCode.UserErrorCode.USER_OAUTH_CODE_ERRO.getErrMsg(),
					ErrorCode.UserErrorCode.USER_OAUTH_CODE_ERRO.getCode());
		}
		//log.info("[oauth2LoginWithPhone] entity{}", entity);
		UserEntity user = userService.selectOne(new EntityWrapper<UserEntity>(entity));
		if (null == user) {
			// 该第三方未登陆过，需要注册
			entity.setRegisterDeviceCode(oauth2Param.getCommonParam().getDeviceCode());
			entity.setUnionType(oauth2Param.getType());
			entity.setRegisterIp(ip);
			String userinfo = oauth2.getUser(access_token, openId);
			JSONObject userResult = JSON.parseObject(userinfo);
			//log.info("[oauth2LoginWithPhone] userinfo obj {}", userResult);
			if (userResult.getInteger("errcode") != null) {
				throw new RRException("第三方登陆出错");
			}
			String unionId = userResult.getString("unionid");
			int sex = userResult.getIntValue("sex");
			entity.setUnionId(unionId);
			if (sex == 0) {
				entity.setSex(false);
			} else if (sex == 1) {
				entity.setSex(true);
			}
			UserDetail userDetail = userService.thirdPartyRegister(entity, userResult, oauth2Param);
			RegisterMessage message = new RegisterMessage(userDetail.getId(), userDetail.getAccount(), ip,
					oauth2Param.getCommonParam().getDeviceType(), oauth2Param.getCommonParam().getDeviceCode(), oauth2Param.getCommonParam().getInvitationCode(),
					1L, oauth2Param.getCommonParam().getEdition());
			message.setToken(userDetail.getToken());
			log.info("message {}", message);
			mqClient.registerPush(message);
			return R.ok().put("data", userDetail);
		}
		String token = null;
		try {
			token = userService.webThirdPartyLogin(user, oauth2Param, ip);
		} catch (Exception e) {
			String ipData[] = IPQueryUtil.queryIp(ip);
			// 新增一条登陆失败记录
			UserLoginEntity userLogin = new UserLoginEntity();
			userLogin.setUserId(user.getId());
			userLogin.setHallId(1L);
			userLogin.setIp(ip);
			try {
				if (ipData != null) {
					userLogin.setNation(ipData[0]);
					userLogin.setIpAddress(ipData[0] + ipData[1] + ipData[2]);
				}
			} catch (Exception e1) {
				log.error("[ip解析返回错误]", e1);
			}
			userLogin.setEdition(oauth2Param.getCommonParam().getEdition());
			userLogin.setDeviceCode(oauth2Param.getCommonParam().getDeviceCode());
			userLogin.setLoginStatus(SysConstant.FAIL);
			userLogin.setDeviceType(oauth2Param.getCommonParam().getDeviceType());
			userLoginService.insert(userLogin);
			throw e;
		}
		JSONObject param = new JSONObject();
		param.put("Uid", JwtUtil.getId(token));
		try {
			HttpRequest.post(HallUrlConstant.getKICK_URL()).timeout(5000).body(param.toString()).execute().body();
		} catch (Exception e) {
			log.error("官网登入发送uid到大厅", e);
			throw new RRException(ErrorCode.UserErrorCode.HALL_REQUEST_ERRO.getErrMsg(),
					ErrorCode.UserErrorCode.HALL_REQUEST_ERRO.getCode());
		}
		return R.ok().put("data", token);
	}

	/**
	 * 登陆
	 */
	@LoginRepeat("重复登入校验")
	@PostMapping("/login")
	public R login(@RequestBody LoginEntity loginEntity, HttpServletRequest httpServletRequest) throws Exception {
		// 校验参数
		String errorEesult = EntityValidateUtil.validateModel(loginEntity);
		if (!StringUtils.isEmpty(errorEesult)) {
			throw new ParamInvalidException(errorEesult);
		}

		String token;
		String ip = IpUtil.getIPAddress(httpServletRequest);
		UserEntity entity = new UserEntity();
		entity.setAccount(loginEntity.getAccount());
		UserEntity user = null;
		// 判断账号是不是手机号格式
		if (!VerificationUitl.phoneVerification(loginEntity.getAccount())) {
			user = userService.selectOne(new EntityWrapper<UserEntity>(entity));
		} else {
			user = userService.selectOne(new EntityWrapper<UserEntity>(entity).orNew("phone={0}", entity.getAccount()));
		}
		// 查看账号是否输入正确
		if (null == user) {
			throw new RRException(ErrorCode.UserErrorCode.USER_ACCOUNT_INVALID.getErrMsg(),
					ErrorCode.UserErrorCode.USER_ACCOUNT_INVALID.getCode());
		}
		Long userId = user.getId();
		int i = 0;
		if (localContentCache.get(SysConstant.PASSWORD_ERROR + userId) != null) {
			i = (int) localContentCache.get(SysConstant.PASSWORD_ERROR + userId);// 输入错误密码次数
			if (i >= SysConstant.PASSWORD_ERROR_NUM) {
				throw new RRException(ErrorCode.PasswordErrorCode.PASSWORD_NUM_ERRO.getErrMsg(),
						ErrorCode.PasswordErrorCode.PASSWORD_NUM_ERRO.getCode());
			}
		}
		user.setId(userId);
		user.setToken(user.getToken());
		if (StringUtils.isEmpty(ip)) {
			// IP为空
			throw new RRException(ErrorCode.UserErrorCode.USER_IP_ISNULL_ERRO.getErrMsg(),
					ErrorCode.UserErrorCode.USER_IP_ISNULL_ERRO.getCode());
		}

		try {
			token = userService.login(user, loginEntity, ip);
		} catch (Exception e) {
			// 新增一条登陆失败记录
			userLoginService.insert(userId, ip, loginEntity.getRegisterDeviceCode(), loginEntity.getDeviceType(), null,
					SysConstant.FAIL);
			// 新增一条密码错误记录
			localContentCache.put(SysConstant.PASSWORD_ERROR + userId, i + 1);
			throw e;
		}
		// JSONObject param = new JSONObject();
		// param.put("Uid", userId);
		// try {
		// HttpRequest.post(HallUrlConstant.getKICK_URL()).timeout(5000).body(param.toString()).execute().body();
		// } catch (Exception e) {
		// log.error("官网登入发送uid到大厅", e);
		// throw new RRException(ErrorCode.UserErrorCode.HALL_REQUEST_ERRO.getErrMsg(),
		// ErrorCode.UserErrorCode.HALL_REQUEST_ERRO.getCode());
		// }
		return R.ok().put("token", token);
	}
}
