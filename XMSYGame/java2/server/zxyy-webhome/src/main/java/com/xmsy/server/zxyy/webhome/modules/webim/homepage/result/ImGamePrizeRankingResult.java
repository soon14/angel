package com.xmsy.server.zxyy.webhome.modules.webim.homepage.result;

import java.math.BigDecimal;

import lombok.Data;

/**
 * 33娱乐游戏介绍
 * 
 * @author xiaoliu
 * @email xxxxx
 * @date 2019-06-22
 */
@Data
public class ImGamePrizeRankingResult {

	/**
	 * 标题
	 */
	private String title;
	/**
	 * 图片路径
	 */
	private String enclosureUrl;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 奖金总额
	 */
	private BigDecimal prize;
	/**
	 * 排序
	 */
	private int orderNo;
}
