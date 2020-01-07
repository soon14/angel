package com.xmsy.server.zxyy.webhome.modules.manager.payconfig.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.xmsy.server.zxyy.webhome.cache.EhCacheName;
import com.xmsy.server.zxyy.webhome.common.exception.RRException;
import com.xmsy.server.zxyy.webhome.common.utils.ErrorCode;
import com.xmsy.server.zxyy.webhome.constant.SysConstant;
import com.xmsy.server.zxyy.webhome.modules.app.recharge.param.AppQrcodePaymentParam;
import com.xmsy.server.zxyy.webhome.modules.manager.payconfig.dao.PayConfigDao;
import com.xmsy.server.zxyy.webhome.modules.manager.payconfig.entity.PayConfigEntity;
import com.xmsy.server.zxyy.webhome.modules.manager.payconfig.entity.PayConfigResultEntity;
import com.xmsy.server.zxyy.webhome.modules.manager.payconfig.service.PayConfigService;
import com.xmsy.server.zxyy.webhome.modules.manager.rechargechannel.entity.RechargeChannelEntity;
import com.xmsy.server.zxyy.webhome.modules.manager.rechargechannel.service.RechargeChannelService;
import com.xmsy.server.zxyy.webhome.modules.web.recharge.param.QrcodePaymentParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * .支付公司配置
 *
 * @author Administrator
 */
@Slf4j
@Service("payConfigService")
public class PayConfigServiceImpl extends ServiceImpl<PayConfigDao, PayConfigEntity> implements PayConfigService {

    @Autowired
    private RechargeChannelService rechargeChannelService;

    @Override
    @CacheEvict(value = EhCacheName.PAY_CONFIG_CACHE, allEntries = true)
    public boolean insert(PayConfigEntity entity) {
        // TODO Auto-generated method stub
        return super.insert(entity);
    }

    @Override
    @CacheEvict(value = EhCacheName.PAY_CONFIG_CACHE, allEntries = true)
    public boolean deleteById(Serializable id) {
        // TODO Auto-generated method stub
        return super.deleteById(id);
    }

    @Override
    @CacheEvict(value = EhCacheName.PAY_CONFIG_CACHE, allEntries = true)
    public boolean updateById(PayConfigEntity entity) {
        // TODO Auto-generated method stub
        return super.updateById(entity);
    }

    @Override
    @Cacheable(cacheNames = EhCacheName.PAY_CONFIG_CACHE, key = "#id", unless = "#result == null")
    public PayConfigEntity selectById(Serializable id) {
        // TODO Auto-generated method stub
        return super.selectById(id);
    }

    @Override
    @Cacheable(cacheNames = EhCacheName.PAY_CONFIG_CACHE, key = "'payConfig'", unless = "#result == null || #result.size() == 0")
    public List<PayConfigEntity> getPayConfig() {
        return selectList(
                new EntityWrapper<PayConfigEntity>(new PayConfigEntity().setFirstPush(SysConstant.ENABLE_TRUE)));
    }

    @Override
    public void firstPush(Long id) {
        List<PayConfigEntity> list = selectList(
                new EntityWrapper<PayConfigEntity>(new PayConfigEntity().setFirstPush(SysConstant.ENABLE_TRUE)));
        if (!CollectionUtil.isEmpty(list)) {
            for (PayConfigEntity entity : list) {
                entity.setFirstPush(SysConstant.ENABLE_FALSE);
            }
            updateBatchById(list);
        }
        PayConfigEntity payConfig = new PayConfigEntity();
        payConfig.setId(id);
        payConfig.setFirstPush(SysConstant.ENABLE_TRUE);
        updateById(payConfig);
    }

    @Override
    public List<PayConfigResultEntity> appRechargeNavigation() {
        List<PayConfigResultEntity> list = baseMapper.selectListForApp();
        // TODO Auto-generated method stub
        log.info("[appRechargeNavigation] list {}", list);
        return list;
    }

    @Override
    public List<PayConfigResultEntity> webRechargeNavigation() {
        List<PayConfigResultEntity> list = baseMapper.selectListForWeb();
        // TODO Auto-generated method stub
        log.info("[webRechargeNavigation] list {}", list);
        return list;
    }

    @Override
    @Cacheable(cacheNames = EhCacheName.PAY_CONFIG_CACHE, key = "#aliasName", unless = "#result == null")
    public PayConfigEntity selectByAliasName(String aliasName) {
        return selectOne(
                new EntityWrapper<>(new PayConfigEntity().setAliasName(aliasName)));
    }

    // 校验支付公司和支付渠道
    @Override
    public void valitePayConfig(Object paymentParam) throws Exception {
        if (null == paymentParam) {
            throw new RRException(ErrorCode.PayErrorCode.PAY_COMPANY_ERRO.getErrMsg(),
                    ErrorCode.PayErrorCode.PAY_COMPANY_ERRO.getCode());
        }
        Long payId = null;
        Long payChannel = null;
        if (paymentParam instanceof QrcodePaymentParam) {
            QrcodePaymentParam payment = (QrcodePaymentParam) paymentParam;
            payId = payment.getPayId();
            payChannel = payment.getPayChannel();

        } else if (paymentParam instanceof AppQrcodePaymentParam) {
            AppQrcodePaymentParam payment = (AppQrcodePaymentParam) paymentParam;
            payId = payment.getPayId();
            payChannel = payment.getPayChannel();

        } else {
            throw new RRException(ErrorCode.PayErrorCode.PAY_COMPANY_ERRO.getErrMsg(),
                    ErrorCode.PayErrorCode.PAY_COMPANY_ERRO.getCode());
        }
        PayConfigEntity payConfig = selectById(payId);
        RechargeChannelEntity rechargeChannel = rechargeChannelService.getRechargeChannelByType(payChannel);
        // 支付公司校验
        if (null == payConfig) {
            throw new RRException(ErrorCode.PayErrorCode.PAY_COMPANY_ERRO.getErrMsg(),
                    ErrorCode.PayErrorCode.PAY_COMPANY_ERRO.getCode());
        }
        // 支付渠道校验
        if (null == rechargeChannel) {
            throw new RRException(ErrorCode.PayErrorCode.PAY_CHANNEL_ERRO.getErrMsg(),
                    ErrorCode.PayErrorCode.PAY_CHANNEL_ERRO.getCode());
        }
        // 支付公司是否包含该支付渠道校验
        String payMethods = payConfig.getPaymentMethod();
        if (StringUtils.isEmpty(payMethods)) {
            throw new RRException(ErrorCode.PayErrorCode.PAY_COMPANY_CHANNEL_ERRO.getErrMsg(),
                    ErrorCode.PayErrorCode.PAY_COMPANY_CHANNEL_ERRO.getCode());
        }
        ArrayList<String> rechargeChannels = Lists.newArrayList(payMethods.split(","));
        if (rechargeChannels.contains(String.valueOf(rechargeChannel.getId()))) {
            return;
        } else {
            throw new RRException(ErrorCode.PayErrorCode.PAY_COMPANY_CHANNEL_ERRO.getErrMsg(),
                    ErrorCode.PayErrorCode.PAY_COMPANY_CHANNEL_ERRO.getCode());
        }
    }
}
