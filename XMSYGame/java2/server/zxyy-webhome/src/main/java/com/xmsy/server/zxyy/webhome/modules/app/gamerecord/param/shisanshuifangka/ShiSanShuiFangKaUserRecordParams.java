package com.xmsy.server.zxyy.webhome.modules.app.gamerecord.param.shisanshuifangka;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.xmsy.server.zxyy.webhome.common.validator.group.AddGroup;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


/**
 * 十三水
 * 用户记录
 * 
 * @author adu
 * @email xxxxx
 * @date 2019-02-28 15:50:25
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Accessors(chain=true)
public class ShiSanShuiFangKaUserRecordParams  {
	
	/**
	 * 用户id
	 */
	@NotBlank(message = "用户id不能为空", groups = AddGroup.class)
    private Long userId;
	/**
	 * 账号名称
	 */
	@NotBlank(message = "用户账号不能为空", groups = AddGroup.class)
	private String userAccount;
	/**
	 * 下注前金币
	 */
	@NotBlank(message = "下注前金币不能为空", groups = AddGroup.class)
	private Long coinsBefore;
	/**
	 * 下注金币
	 */
	@NotBlank(message = "下注金币不能为空", groups = AddGroup.class)
	private Long betCoins;
	
	/**
	 * 赢输金币
	 */
	@NotBlank(message = "赢输金币不能为空", groups = AddGroup.class)
	private Long coins;
	/**
	 *结束后金币
	 */
	@NotBlank(message = "结束后金币不能为空", groups = AddGroup.class)
	private Long coinsAfter;
	/**
	 * 底分
	 */
	@NotBlank(message = "底分不能为空", groups = AddGroup.class)
	private Long score;
	/**
	 * 倍数
	 */
	@NotBlank(message = "倍数不能为空", groups = AddGroup.class)
	private Integer multiple;
	
	/**
	 * 是否机器人
	 */
	@NotBlank(message = "是否机器人不能为空", groups = AddGroup.class)
	private Boolean robot;
	/**
	 * 头墩牌型集合
	 */
	@NotBlank(message = "头墩牌型集合不能为空", groups = AddGroup.class)
	private List<Integer> headCards;
	/**
	 * 中墩牌型集合
	 */
	@NotBlank(message = "中墩牌型集合不能为空", groups = AddGroup.class)
	private List<Integer> middleCards;
	/**
	 * 尾墩牌型集合
	 */
	@NotBlank(message = "尾墩牌型集合不能为空", groups = AddGroup.class)
	private List<Integer> bottomCards;
	
	}
