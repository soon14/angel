UPDATE `webhome`.`sys_menu` SET `menu_id`='836', `parent_id`='0', `name`='平台数据报表', `url`=NULL, `perms`=NULL, `type`='0', `icon`='mudedi', `order_num`='6' WHERE (`menu_id`='836');
UPDATE `webhome`.`sys_menu` SET `menu_id`='837', `parent_id`='836', `name`='每日平台数据', `url`='platformdatareporting/platformdatareporting', `perms`='reportdatadaily:reportdatadaily:list', `type`='1', `icon`='config', `order_num`='1' WHERE (`menu_id`='837');
UPDATE `webhome`.`sys_menu` SET `menu_id`='838', `parent_id`='837', `name`='模块营收数据', `url`='platformdatareporting/modularrevenuedata', `perms`='reportgamedaily:reportgamedaily:list', `type`='2', `icon`='config', `order_num`='2' WHERE (`menu_id`='838');
UPDATE `webhome`.`sys_menu` SET `menu_id`='839', `parent_id`='836', `name`='游戏场次详情', `url`='platformdatareporting/playgrounddetails', `perms`='reportgamegradedaily:reportgamegradedaily:list', `type`='1', `icon`='config', `order_num`='3' WHERE (`menu_id`='839');
UPDATE `webhome`.`sys_menu` SET `menu_id`='840', `parent_id`='839', `name`='游戏用户数据', `url`='platformdatareporting/gameuserdata', `perms`='reportplayergamedaily:reportplayergamedaily:list', `type`='2', `icon`='config', `order_num`='4' WHERE (`menu_id`='840');
UPDATE `webhome`.`sys_menu` SET `menu_id`='841', `parent_id`='836', `name`='用户场次详情', `url`='platformdatareporting/userfielddetails', `perms`='reportplayergamedaily:reportplayergamedaily:list', `type`='1', `icon`='config', `order_num`='5' WHERE (`menu_id`='841');
