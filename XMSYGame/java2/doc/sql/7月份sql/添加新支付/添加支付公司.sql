--添加支付配置
INSERT INTO `webhome`.`sys_config` (`id`, `param_key`, `param_value`, `type`, `parent_id`, `delete_status`, `create_time`, `update_time`, `version`) VALUES ('131', '一加支付', 'yijiapay', '', '21', '0', '2019-07-06 04:17:02', '2019-07-06 04:17:02', '0');
INSERT INTO `webhome`.`sys_config` (`id`, `param_key`, `param_value`, `type`, `parent_id`, `delete_status`, `create_time`, `update_time`, `version`) VALUES ('132', '产品编码', 'product', '', '131', '0', '2019-06-21 14:33:53', '2019-06-21 14:33:53', '0');
INSERT INTO `webhome`.`sys_config` (`id`, `param_key`, `param_value`, `type`, `parent_id`, `delete_status`, `create_time`, `update_time`, `version`) VALUES ('133', '密钥', 'certificate', '', '131', '0', '2019-06-21 14:35:15', '2019-06-21 14:35:15', '0');
INSERT INTO `webhome`.`sys_config` (`id`, `param_key`, `param_value`, `type`, `parent_id`, `delete_status`, `create_time`, `update_time`, `version`) VALUES ('134', '回调地址', 'callbackUrl', '', '131', '0', '2019-06-21 14:34:22', '2019-06-21 14:34:22', '0');
INSERT INTO `webhome`.`sys_config` (`id`, `param_key`, `param_value`, `type`, `parent_id`, `delete_status`, `create_time`, `update_time`, `version`) VALUES ('135', '支付地址', 'payUrl', '', '131', '0', '2019-06-21 14:34:07', '2019-06-21 14:34:07', '0');
INSERT INTO `webhome`.`sys_config` (`id`, `param_key`, `param_value`, `type`, `parent_id`, `delete_status`, `create_time`, `update_time`, `version`) VALUES ('136', 'h5', 'h5', '', '132', '0', '2019-07-06 04:19:37', '2019-07-06 04:19:37', '0');
INSERT INTO `webhome`.`sys_config` (`id`, `param_key`, `param_value`, `type`, `parent_id`, `delete_status`, `create_time`, `update_time`, `version`) VALUES ('137', 'pc', 'pc', '', '132', '0', '2019-07-06 04:19:46', '2019-07-06 04:19:46', '0');
INSERT INTO `webhome`.`sys_config` (`id`, `param_key`, `param_value`, `type`, `parent_id`, `delete_status`, `create_time`, `update_time`, `version`) VALUES ('138', 'uid', '10009', '', '133', '0', '2019-07-06 04:21:14', '2019-07-06 04:21:14', '0');
INSERT INTO `webhome`.`sys_config` (`id`, `param_key`, `param_value`, `type`, `parent_id`, `delete_status`, `create_time`, `update_time`, `version`) VALUES ('139', 'secret', '7A146D6043A049F198811458B1CF4BA2', '', '133', '0', '2019-07-06 04:21:38', '2019-07-06 04:21:38', '0');
INSERT INTO `webhome`.`sys_config` (`id`, `param_key`, `param_value`, `type`, `parent_id`, `delete_status`, `create_time`, `update_time`, `version`) VALUES ('140', 'url', 'http://api.itzoon.com/api/addOrder', '', '135', '0', '2019-07-06 06:02:42', '2019-07-06 06:02:42', '0');
INSERT INTO `webhome`.`sys_config` (`id`, `param_key`, `param_value`, `type`, `parent_id`, `delete_status`, `create_time`, `update_time`, `version`) VALUES ('141', 'url', 'http://zxyy-hsk.qicp.io:40192/v1/payment/yijiapay', '', '134', '0', '2019-07-06 06:04:03', '2019-07-06 06:04:03', '0');
INSERT INTO `webhome`.`sys_config` (`id`, `param_key`, `param_value`, `type`, `parent_id`, `delete_status`, `create_time`, `update_time`, `version`) VALUES ('142', 'alipay', 'wap', '', '136', '0', '2019-07-06 06:05:01', '2019-07-06 06:05:01', '0');
INSERT INTO `webhome`.`sys_config` (`id`, `param_key`, `param_value`, `type`, `parent_id`, `delete_status`, `create_time`, `update_time`, `version`) VALUES ('143', 'weixin', 'wxwap', '', '136', '0', '2019-07-06 06:05:29', '2019-07-06 06:05:29', '0');
INSERT INTO `webhome`.`sys_config` (`id`, `param_key`, `param_value`, `type`, `parent_id`, `delete_status`, `create_time`, `update_time`, `version`) VALUES ('144', 'unionpay', 'ylwg', '', '136', '0', '2019-07-06 06:07:29', '2019-07-06 06:07:29', '0');
INSERT INTO `webhome`.`sys_config` (`id`, `param_key`, `param_value`, `type`, `parent_id`, `delete_status`, `create_time`, `update_time`, `version`) VALUES ('145', 'quickpay', 'ylkj', '', '136', '0', '2019-07-06 06:08:08', '2019-07-06 06:08:08', '0');
INSERT INTO `webhome`.`sys_config` (`id`, `param_key`, `param_value`, `type`, `parent_id`, `delete_status`, `create_time`, `update_time`, `version`) VALUES ('146', 'qqpay', 'qqqb', '', '136', '0', '2019-07-06 06:08:34', '2019-07-06 06:08:34', '0');
INSERT INTO `webhome`.`sys_config` (`id`, `param_key`, `param_value`, `type`, `parent_id`, `delete_status`, `create_time`, `update_time`, `version`) VALUES ('147', 'alipay', 'qrcode', '', '137', '0', '2019-07-06 06:05:01', '2019-07-06 06:05:01', '0');
INSERT INTO `webhome`.`sys_config` (`id`, `param_key`, `param_value`, `type`, `parent_id`, `delete_status`, `create_time`, `update_time`, `version`) VALUES ('148', 'weixin', 'wxqrcode', '', '137', '0', '2019-07-06 06:05:29', '2019-07-06 06:05:29', '0');
INSERT INTO `webhome`.`sys_config` (`id`, `param_key`, `param_value`, `type`, `parent_id`, `delete_status`, `create_time`, `update_time`, `version`) VALUES ('149', 'unionpay', 'ylsm', '', '137', '0', '2019-07-06 06:07:29', '2019-07-06 06:07:29', '0');
INSERT INTO `webhome`.`sys_config` (`id`, `param_key`, `param_value`, `type`, `parent_id`, `delete_status`, `create_time`, `update_time`, `version`) VALUES ('150', 'quickpay', 'ylkj', '', '137', '0', '2019-07-06 06:08:08', '2019-07-06 06:08:08', '0');
INSERT INTO `webhome`.`sys_config` (`id`, `param_key`, `param_value`, `type`, `parent_id`, `delete_status`, `create_time`, `update_time`, `version`) VALUES ('151', 'qqpay', 'qqqb', '', '137', '0', '2019-07-06 06:08:34', '2019-07-06 06:08:34', '0');
INSERT INTO `webhome`.`sys_config` (`id`, `param_key`, `param_value`, `type`, `parent_id`, `delete_status`, `create_time`, `update_time`, `version`) VALUES ('152', '钉钉支付', 'dingdingpay', '', '21', '0', '2019-07-06 06:23:16', '2019-07-06 06:23:16', '0');
INSERT INTO `webhome`.`sys_config` (`id`, `param_key`, `param_value`, `type`, `parent_id`, `delete_status`, `create_time`, `update_time`, `version`) VALUES ('153', '产品编码', 'product', '', '152', '0', '2019-06-21 14:33:53', '2019-06-21 14:33:53', '0');
INSERT INTO `webhome`.`sys_config` (`id`, `param_key`, `param_value`, `type`, `parent_id`, `delete_status`, `create_time`, `update_time`, `version`) VALUES ('154', '密钥', 'certificate', '', '152', '0', '2019-06-21 14:35:15', '2019-06-21 14:35:15', '0');
INSERT INTO `webhome`.`sys_config` (`id`, `param_key`, `param_value`, `type`, `parent_id`, `delete_status`, `create_time`, `update_time`, `version`) VALUES ('155', '回调地址', 'callbackUrl', '', '152', '0', '2019-06-21 14:34:22', '2019-06-21 14:34:22', '0');
INSERT INTO `webhome`.`sys_config` (`id`, `param_key`, `param_value`, `type`, `parent_id`, `delete_status`, `create_time`, `update_time`, `version`) VALUES ('156', '支付地址', 'payUrl', '', '152', '0', '2019-06-21 14:34:07', '2019-06-21 14:34:07', '0');
INSERT INTO `webhome`.`sys_config` (`id`, `param_key`, `param_value`, `type`, `parent_id`, `delete_status`, `create_time`, `update_time`, `version`) VALUES ('157', 'h5', 'h5', '', '153', '0', '2019-07-06 04:19:37', '2019-07-06 04:19:37', '0');
INSERT INTO `webhome`.`sys_config` (`id`, `param_key`, `param_value`, `type`, `parent_id`, `delete_status`, `create_time`, `update_time`, `version`) VALUES ('158', 'pc', 'pc', '', '153', '0', '2019-07-06 04:19:46', '2019-07-06 04:19:46', '0');
INSERT INTO `webhome`.`sys_config` (`id`, `param_key`, `param_value`, `type`, `parent_id`, `delete_status`, `create_time`, `update_time`, `version`) VALUES ('159', 'uid', '881422', '', '154', '0', '2019-07-06 04:21:14', '2019-07-06 04:21:14', '0');
INSERT INTO `webhome`.`sys_config` (`id`, `param_key`, `param_value`, `type`, `parent_id`, `delete_status`, `create_time`, `update_time`, `version`) VALUES ('160', 'secret', '88606EC4C8A543D5ADEFED6BCA184662', '', '154', '0', '2019-07-06 04:21:38', '2019-07-06 04:21:38', '0');
INSERT INTO `webhome`.`sys_config` (`id`, `param_key`, `param_value`, `type`, `parent_id`, `delete_status`, `create_time`, `update_time`, `version`) VALUES ('161', 'url', 'http://47.110.245.153:8080/api/pay', '', '156', '0', '2019-07-06 06:02:42', '2019-07-06 06:02:42', '0');
INSERT INTO `webhome`.`sys_config` (`id`, `param_key`, `param_value`, `type`, `parent_id`, `delete_status`, `create_time`, `update_time`, `version`) VALUES ('162', 'url', 'http://zxyy-hsk.qicp.io:40192/v1/payment/dingdingpay', '', '155', '0', '2019-07-06 06:04:03', '2019-07-06 06:04:03', '0');
INSERT INTO `webhome`.`sys_config` (`id`, `param_key`, `param_value`, `type`, `parent_id`, `delete_status`, `create_time`, `update_time`, `version`) VALUES ('163', 'alipay', 'aliPayH5', '', '157', '0', '2019-07-06 06:05:01', '2019-07-06 06:05:01', '0');
INSERT INTO `webhome`.`sys_config` (`id`, `param_key`, `param_value`, `type`, `parent_id`, `delete_status`, `create_time`, `update_time`, `version`) VALUES ('164', 'weixin', 'wxPayH5', '', '157', '0', '2019-07-06 06:05:29', '2019-07-06 06:05:29', '0');
INSERT INTO `webhome`.`sys_config` (`id`, `param_key`, `param_value`, `type`, `parent_id`, `delete_status`, `create_time`, `update_time`, `version`) VALUES ('165', 'unionpay', 'unionPayWG', '', '157', '0', '2019-07-06 06:07:29', '2019-07-06 06:07:29', '0');
INSERT INTO `webhome`.`sys_config` (`id`, `param_key`, `param_value`, `type`, `parent_id`, `delete_status`, `create_time`, `update_time`, `version`) VALUES ('166', 'quickpay', 'unionPayKJ', '', '157', '0', '2019-07-06 06:08:08', '2019-07-06 06:08:08', '0');
INSERT INTO `webhome`.`sys_config` (`id`, `param_key`, `param_value`, `type`, `parent_id`, `delete_status`, `create_time`, `update_time`, `version`) VALUES ('167', 'qqpay', 'qqPayQB', '', '157', '0', '2019-07-06 06:08:34', '2019-07-06 06:08:34', '0');
INSERT INTO `webhome`.`sys_config` (`id`, `param_key`, `param_value`, `type`, `parent_id`, `delete_status`, `create_time`, `update_time`, `version`) VALUES ('168', 'alipay', 'aliPaySM', '', '158', '0', '2019-07-06 06:05:01', '2019-07-06 06:05:01', '0');
INSERT INTO `webhome`.`sys_config` (`id`, `param_key`, `param_value`, `type`, `parent_id`, `delete_status`, `create_time`, `update_time`, `version`) VALUES ('169', 'weixin', 'wxPaySM', '', '158', '0', '2019-07-06 06:05:29', '2019-07-06 06:05:29', '0');
INSERT INTO `webhome`.`sys_config` (`id`, `param_key`, `param_value`, `type`, `parent_id`, `delete_status`, `create_time`, `update_time`, `version`) VALUES ('170', 'unionpay', 'unionPaySM', '', '158', '0', '2019-07-06 06:07:29', '2019-07-06 06:07:29', '0');
INSERT INTO `webhome`.`sys_config` (`id`, `param_key`, `param_value`, `type`, `parent_id`, `delete_status`, `create_time`, `update_time`, `version`) VALUES ('171', 'quickpay', 'unionPayKJ', '', '158', '0', '2019-07-06 06:08:08', '2019-07-06 06:08:08', '0');
INSERT INTO `webhome`.`sys_config` (`id`, `param_key`, `param_value`, `type`, `parent_id`, `delete_status`, `create_time`, `update_time`, `version`) VALUES ('172', 'qqpay', 'qqPayQB', '', '158', '0', '2019-07-06 06:08:34', '2019-07-06 06:08:34', '0');
INSERT INTO `webhome`.`sys_config` (`id`, `param_key`, `param_value`, `type`, `parent_id`, `delete_status`, `create_time`, `update_time`, `version`) VALUES ('173', 'jindongpay', 'jdPaySM', '', '158', '0', '2019-07-06 06:08:34', '2019-07-06 06:08:34', '0');
INSERT INTO `webhome`.`sys_config` (`id`, `param_key`, `param_value`, `type`, `parent_id`, `delete_status`, `create_time`, `update_time`, `version`) VALUES ('174', 'unioncloud', 'unionPayYSF', '', '157', '0', NULL, NULL, '0');
INSERT INTO `webhome`.`sys_config` (`id`, `param_key`, `param_value`, `type`, `parent_id`, `delete_status`, `create_time`, `update_time`, `version`) VALUES ('175', 'unioncloud', 'unionPayYSF', '', '158', '0', NULL, NULL, '0');


--添加新的支付公司
INSERT INTO `webhome`.`pay_config` (`id`, `delete_status`, `create_time`, `update_time`, `version`, `name`, `payment_method`, `enable`, `first_push`, `payment_method_name`, `app_icon_id`, `app_icon_md5`, `web_icon_id`, `web_icon_md5`, `enclosure_id`, `enclosure_md5`, `alias_name`, `order_num`) VALUES ('1003', '0', '2019-07-06 06:37:12', '2019-07-06 06:37:12', '0', '一加支付', '12,13', '', '\0', '支付宝,微信', NULL, NULL, NULL, NULL, NULL, NULL, 'yijiapay', '6');
INSERT INTO `webhome`.`pay_config` (`id`, `delete_status`, `create_time`, `update_time`, `version`, `name`, `payment_method`, `enable`, `first_push`, `payment_method_name`, `app_icon_id`, `app_icon_md5`, `web_icon_id`, `web_icon_md5`, `enclosure_id`, `enclosure_md5`, `alias_name`, `order_num`) VALUES ('1004', '0', '2019-07-06 06:38:15', '2019-07-06 06:38:15', '0', '钉钉支付', '12,13', '', '\0', '支付宝,微信', NULL, NULL, NULL, NULL, NULL, NULL, 'dingdingpay', '7');


--添加新的支付渠道
INSERT INTO `webhome`.`recharge_channel` (`id`, `delete_status`, `create_time`, `update_time`, `version`, `name`, `type`, `min_amount`, `enable`, `app_icon_id`, `app_icon_md5`, `web_icon_id`, `web_icon_md5`, `enclosure_id`, `md5`, `alias`) VALUES ('1001', '0', '2019-07-06 07:31:56', '2019-07-06 07:31:56', '0', '银联云闪付', '1001', '0', '', NULL, '', NULL, '', NULL, NULL, 'unioncloud');
