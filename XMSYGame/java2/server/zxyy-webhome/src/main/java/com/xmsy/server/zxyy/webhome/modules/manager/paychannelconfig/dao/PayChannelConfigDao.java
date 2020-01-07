package com.xmsy.server.zxyy.webhome.modules.manager.paychannelconfig.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.xmsy.server.zxyy.webhome.modules.manager.paychannelconfig.entity.PayChannelConfigEntity;

/**
 * 支付渠道配置
 * 
 * @author aleng
 * @email xxxxx
 * @date 2019-04-10 14:20:07
 */
@Mapper
public interface PayChannelConfigDao extends BaseMapper<PayChannelConfigEntity> {
	
	/**
	 * 获取支付金额配置列表
	 * 
	 * @param paychannelconfig
	 * @param pageParam
	 * @return
	 */
	List<PayChannelConfigEntity> getPayChannelConfigs(Pagination page, @Param("record") PayChannelConfigEntity config);
	
}
