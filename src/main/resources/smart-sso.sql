/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : smart-sso

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-11-28 15:31:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for gateway_api_define
-- ----------------------------
DROP TABLE IF EXISTS `gateway_api_define`;
CREATE TABLE `gateway_api_define` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `path` varchar(255) NOT NULL COMMENT '路径上下文',
  `service_id` varchar(50) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `retryable` tinyint(1) DEFAULT NULL COMMENT '是否重试',
  `is_enable` tinyint(1) NOT NULL COMMENT '是否启用',
  `strip_prefix` int(11) DEFAULT NULL,
  `app_name` varchar(255) DEFAULT NULL COMMENT '应用名称',
  `gmt_create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `sys_app_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gateway_api_define
-- ----------------------------
INSERT INTO `gateway_api_define` VALUES ('1', '/dds/**', 'dds', 'http://127.0.0.1:8088/', '1', '1', '0', '单点登录权限管理系统', '2018-11-28 10:52:16', null, '1');
INSERT INTO `gateway_api_define` VALUES ('2', '/zyls/**', 'zyls', 'http://dds.sofmit.cn/', '1', '1', '0', 'DDS', '2018-11-27 16:44:31', null, '84');

-- ----------------------------
-- Table structure for sys_app
-- ----------------------------
DROP TABLE IF EXISTS `sys_app`;
CREATE TABLE `sys_app` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL COMMENT '名称',
  `sort` int(11) NOT NULL COMMENT '排序',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  `isEnable` int(1) NOT NULL COMMENT '是否启用',
  `code` varchar(16) NOT NULL COMMENT '编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=86 DEFAULT CHARSET=utf8 COMMENT='应用表';

-- ----------------------------
-- Records of sys_app
-- ----------------------------
INSERT INTO `sys_app` VALUES ('1', '单点登录权限管理系统', '20', '2015-06-02 11:31:44', '1', 'smart-sso-server');
INSERT INTO `sys_app` VALUES ('81', 'Demo管理系统', '15', '2015-11-08 17:16:39', '1', 'smart-sso-demo');
INSERT INTO `sys_app` VALUES ('82', '内容管理系统', '10', '2015-11-08 17:16:39', '1', 'smart-cms');
INSERT INTO `sys_app` VALUES ('84', 'DDS', '1', '2018-10-22 16:16:15', '1', 'smart-dds');
INSERT INTO `sys_app` VALUES ('85', '订单系统', '1', '2018-11-27 09:26:41', '0', 'order-app');

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `appId` int(11) NOT NULL COMMENT '应用ID',
  `parentId` int(11) DEFAULT NULL COMMENT '父ID',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `url` varchar(255) NOT NULL COMMENT '权限URL',
  `sort` int(11) NOT NULL COMMENT '排序',
  `isMenu` int(1) NOT NULL COMMENT '是否菜单',
  `isEnable` int(1) NOT NULL COMMENT '是否启用',
  `icon` varchar(100) DEFAULT NULL COMMENT '图标',
  PRIMARY KEY (`id`),
  KEY `FK_SYS_PERM_REFERENCE_SYS_APP` (`appId`),
  CONSTRAINT `FK_SYS_PERM_REFERENCE_SYS_APP` FOREIGN KEY (`appId`) REFERENCES `sys_app` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8 COMMENT='权限表';

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('2', '1', null, '应用', '/admin/app', '39', '1', '1', 'fa-plus-circle blue');
INSERT INTO `sys_permission` VALUES ('3', '1', null, '用户', '/admin/user', '79', '1', '1', 'fa-user');
INSERT INTO `sys_permission` VALUES ('4', '1', null, '角色', '/admin/role', '59', '1', '1', 'fa-users');
INSERT INTO `sys_permission` VALUES ('5', '1', null, '权限', '/admin/permission', '29', '1', '1', 'fa-key');
INSERT INTO `sys_permission` VALUES ('6', '1', '2', '应用新增', '/admin/app/edit', '4', '0', '1', 'fa-plus-circle blue');
INSERT INTO `sys_permission` VALUES ('7', '1', '2', '应用禁用', '/admin/app/enable', '3', '0', '1', 'fa-lock orange');
INSERT INTO `sys_permission` VALUES ('8', '1', '2', '应用启用', '/admin/app/enable', '2', '0', '1', 'fa-unlock green');
INSERT INTO `sys_permission` VALUES ('9', '1', '2', '应用删除', '/admin/app/delete', '1', '0', '1', 'fa-trash-o red');
INSERT INTO `sys_permission` VALUES ('10', '1', '3', '用户新增', '/admin/user/edit', '6', '0', '1', 'fa-plus-circle blue');
INSERT INTO `sys_permission` VALUES ('11', '1', '3', '用户禁用', '/admin/user/enable', '5', '0', '1', 'fa-lock orange');
INSERT INTO `sys_permission` VALUES ('12', '1', '3', '用户启用', '/admin/user/enable', '4', '0', '1', 'fa-unlock green');
INSERT INTO `sys_permission` VALUES ('13', '1', '3', '用户删除', '/admin/user/delete', '3', '0', '1', 'fa-trash-o red');
INSERT INTO `sys_permission` VALUES ('14', '1', '3', '重置密码', '/admin/user/resetPassword', '2', '0', '1', 'fa-key grey');
INSERT INTO `sys_permission` VALUES ('16', '1', '4', '角色新增', '/admin/role/edit', '5', '0', '1', 'fa-plus-circle blue');
INSERT INTO `sys_permission` VALUES ('17', '1', '4', '角色禁用', '/admin/role/enable', '4', '0', '1', 'fa-lock orange');
INSERT INTO `sys_permission` VALUES ('18', '1', '4', '角色启用', '/admin/role/enable', '3', '0', '1', 'fa-unlock green');
INSERT INTO `sys_permission` VALUES ('19', '1', '4', '角色删除', '/admin/role/delete', '2', '0', '1', 'fa-trash-o red');
INSERT INTO `sys_permission` VALUES ('20', '1', '4', '角色授权', '/admin/role/allocate', '1', '0', '1', 'fa-cog grey');
INSERT INTO `sys_permission` VALUES ('22', '1', '2', '应用列表', '/admin/app/list', '5', '0', '1', '');
INSERT INTO `sys_permission` VALUES ('23', '1', '3', '用户列表', '/admin/user/list', '7', '0', '1', '');
INSERT INTO `sys_permission` VALUES ('24', '1', '4', '角色列表', '/admin/role/list', '6', '0', '1', '');
INSERT INTO `sys_permission` VALUES ('25', '1', '5', '权限树列表', '/admin/permission/nodes', '1', '0', '1', '');
INSERT INTO `sys_permission` VALUES ('26', '1', '2', '应用保存', '/admin/app/save', '1', '0', '1', '');
INSERT INTO `sys_permission` VALUES ('27', '1', '3', '用户保存', '/admin/user/save', '1', '0', '1', '');
INSERT INTO `sys_permission` VALUES ('28', '1', '4', '角色保存', '/admin/role/save', '1', '0', '1', '');
INSERT INTO `sys_permission` VALUES ('29', '1', '5', '权限保存', '/admin/permission/save', '1', '0', '1', '');
INSERT INTO `sys_permission` VALUES ('30', '1', '5', '权限删除', '/admin/permission/delete', '1', '0', '1', '');
INSERT INTO `sys_permission` VALUES ('33', '81', null, '菜单1', '/admin/men1', '100', '1', '1', 'fa-user');
INSERT INTO `sys_permission` VALUES ('35', '81', '33', '菜单1新增', '/admin/menu1/edit', '1', '0', '1', '');
INSERT INTO `sys_permission` VALUES ('36', '81', '33', '菜单1删除', '/admin/menu1/delete', '1', '0', '1', '');
INSERT INTO `sys_permission` VALUES ('39', '1', null, '导航栏', '/admin/admin/menu', '99', '0', '1', '');
INSERT INTO `sys_permission` VALUES ('41', '1', null, '个人中心', '/admin/profile', '89', '1', '1', 'fa fa-desktop');
INSERT INTO `sys_permission` VALUES ('42', '1', '41', '修改密码', '/admin/profile/savePassword', '1', '0', '1', '');
INSERT INTO `sys_permission` VALUES ('44', '82', null, '栏目管理', '/admin/channel', '100', '1', '1', 'fa fa-th-large');
INSERT INTO `sys_permission` VALUES ('47', '82', null, '底部菜单管理', '/admin/menu', '110', '1', '1', 'fa fa-list-alt');
INSERT INTO `sys_permission` VALUES ('48', '82', null, '文章管理', '/admin/article', '90', '1', '1', 'fa fa-file-text');
INSERT INTO `sys_permission` VALUES ('49', '82', null, '产品管理', '/admin/product', '70', '1', '1', 'fa fa-file-powerpoint-o');
INSERT INTO `sys_permission` VALUES ('50', '82', null, '产品规格', '/admin/spec', '75', '1', '1', 'fa fa-cubes');
INSERT INTO `sys_permission` VALUES ('55', '82', null, '首页幻灯片管理', '/admin/slide', '120', '1', '1', 'fa fa-sliders');
INSERT INTO `sys_permission` VALUES ('56', '82', null, '底部菜单配置', '/admin/channelMenu/edit', '105', '1', '1', 'fa fa-cog');
INSERT INTO `sys_permission` VALUES ('59', '81', null, '菜单2', '/admin/menu2', '90', '1', '1', '');
INSERT INTO `sys_permission` VALUES ('60', '1', '3', '用户授权', '/admin/userPermission', '1', '0', '1', '');
INSERT INTO `sys_permission` VALUES ('61', '84', null, '产品中心', 'order/addListPage.do', '1', '1', '1', 'fa-unlock green');
INSERT INTO `sys_permission` VALUES ('62', '1', null, '路由配置', '/admin/gateway', '1', '1', '1', '');
INSERT INTO `sys_permission` VALUES ('63', '1', '62', '服务新增', '/admin/user/edit', '1', '0', '1', '');
INSERT INTO `sys_permission` VALUES ('64', '1', '62', '服务禁用', '/admin/gateway/enable', '1', '0', '1', '');
INSERT INTO `sys_permission` VALUES ('65', '1', '62', '服务启用', '/admin/gateway/enable', '1', '0', '1', '');
INSERT INTO `sys_permission` VALUES ('66', '1', '62', '服务删除', '/admin/gateway/delete', '1', '0', '1', '');

-- ----------------------------
-- Table structure for sys_re_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_re_role_permission`;
CREATE TABLE `sys_re_role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleId` int(11) NOT NULL COMMENT '角色ID',
  `permissionId` int(11) NOT NULL COMMENT '权限ID',
  `appId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_SYS_RE_R_REFERENCE_SYS_PERM` (`permissionId`),
  KEY `FK_SYS_RE_R_REFERENCE_SYS_ROLE` (`roleId`),
  CONSTRAINT `FK_SYS_RE_R_REFERENCE_SYS_PERM` FOREIGN KEY (`permissionId`) REFERENCES `sys_permission` (`id`),
  CONSTRAINT `FK_SYS_RE_R_REFERENCE_SYS_ROLE` FOREIGN KEY (`roleId`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=553 DEFAULT CHARSET=utf8 COMMENT='角色权限表';

-- ----------------------------
-- Records of sys_re_role_permission
-- ----------------------------
INSERT INTO `sys_re_role_permission` VALUES ('345', '1', '33', '81');
INSERT INTO `sys_re_role_permission` VALUES ('346', '1', '35', '81');
INSERT INTO `sys_re_role_permission` VALUES ('347', '1', '36', '81');
INSERT INTO `sys_re_role_permission` VALUES ('348', '1', '59', '81');
INSERT INTO `sys_re_role_permission` VALUES ('349', '1', '55', '82');
INSERT INTO `sys_re_role_permission` VALUES ('350', '1', '47', '82');
INSERT INTO `sys_re_role_permission` VALUES ('351', '1', '56', '82');
INSERT INTO `sys_re_role_permission` VALUES ('352', '1', '44', '82');
INSERT INTO `sys_re_role_permission` VALUES ('353', '1', '48', '82');
INSERT INTO `sys_re_role_permission` VALUES ('354', '1', '50', '82');
INSERT INTO `sys_re_role_permission` VALUES ('355', '1', '49', '82');
INSERT INTO `sys_re_role_permission` VALUES ('486', '1', '61', '84');
INSERT INTO `sys_re_role_permission` VALUES ('518', '1', '39', '1');
INSERT INTO `sys_re_role_permission` VALUES ('519', '1', '41', '1');
INSERT INTO `sys_re_role_permission` VALUES ('520', '1', '42', '1');
INSERT INTO `sys_re_role_permission` VALUES ('521', '1', '3', '1');
INSERT INTO `sys_re_role_permission` VALUES ('522', '1', '23', '1');
INSERT INTO `sys_re_role_permission` VALUES ('523', '1', '10', '1');
INSERT INTO `sys_re_role_permission` VALUES ('524', '1', '11', '1');
INSERT INTO `sys_re_role_permission` VALUES ('525', '1', '12', '1');
INSERT INTO `sys_re_role_permission` VALUES ('526', '1', '13', '1');
INSERT INTO `sys_re_role_permission` VALUES ('527', '1', '14', '1');
INSERT INTO `sys_re_role_permission` VALUES ('528', '1', '27', '1');
INSERT INTO `sys_re_role_permission` VALUES ('529', '1', '4', '1');
INSERT INTO `sys_re_role_permission` VALUES ('530', '1', '24', '1');
INSERT INTO `sys_re_role_permission` VALUES ('531', '1', '16', '1');
INSERT INTO `sys_re_role_permission` VALUES ('532', '1', '17', '1');
INSERT INTO `sys_re_role_permission` VALUES ('533', '1', '18', '1');
INSERT INTO `sys_re_role_permission` VALUES ('534', '1', '19', '1');
INSERT INTO `sys_re_role_permission` VALUES ('535', '1', '20', '1');
INSERT INTO `sys_re_role_permission` VALUES ('536', '1', '28', '1');
INSERT INTO `sys_re_role_permission` VALUES ('537', '1', '2', '1');
INSERT INTO `sys_re_role_permission` VALUES ('538', '1', '22', '1');
INSERT INTO `sys_re_role_permission` VALUES ('539', '1', '6', '1');
INSERT INTO `sys_re_role_permission` VALUES ('540', '1', '7', '1');
INSERT INTO `sys_re_role_permission` VALUES ('541', '1', '8', '1');
INSERT INTO `sys_re_role_permission` VALUES ('542', '1', '9', '1');
INSERT INTO `sys_re_role_permission` VALUES ('543', '1', '26', '1');
INSERT INTO `sys_re_role_permission` VALUES ('544', '1', '5', '1');
INSERT INTO `sys_re_role_permission` VALUES ('545', '1', '25', '1');
INSERT INTO `sys_re_role_permission` VALUES ('546', '1', '29', '1');
INSERT INTO `sys_re_role_permission` VALUES ('547', '1', '30', '1');
INSERT INTO `sys_re_role_permission` VALUES ('548', '1', '62', '1');
INSERT INTO `sys_re_role_permission` VALUES ('549', '1', '63', '1');
INSERT INTO `sys_re_role_permission` VALUES ('550', '1', '64', '1');
INSERT INTO `sys_re_role_permission` VALUES ('551', '1', '65', '1');
INSERT INTO `sys_re_role_permission` VALUES ('552', '1', '66', '1');

-- ----------------------------
-- Table structure for sys_re_user_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_re_user_permission`;
CREATE TABLE `sys_re_user_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL COMMENT '用户ID',
  `permissionId` int(11) NOT NULL COMMENT '权限ID',
  `appId` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=545 DEFAULT CHARSET=utf8 COMMENT='用户权限表';

-- ----------------------------
-- Records of sys_re_user_permission
-- ----------------------------
INSERT INTO `sys_re_user_permission` VALUES ('445', '7', '55', '1');
INSERT INTO `sys_re_user_permission` VALUES ('446', '7', '47', '1');
INSERT INTO `sys_re_user_permission` VALUES ('447', '7', '56', '1');
INSERT INTO `sys_re_user_permission` VALUES ('448', '7', '33', '1');
INSERT INTO `sys_re_user_permission` VALUES ('449', '7', '35', '1');
INSERT INTO `sys_re_user_permission` VALUES ('450', '7', '36', '1');
INSERT INTO `sys_re_user_permission` VALUES ('451', '7', '44', '1');
INSERT INTO `sys_re_user_permission` VALUES ('452', '7', '39', '1');
INSERT INTO `sys_re_user_permission` VALUES ('453', '7', '48', '1');
INSERT INTO `sys_re_user_permission` VALUES ('454', '7', '59', '1');
INSERT INTO `sys_re_user_permission` VALUES ('455', '7', '41', '1');
INSERT INTO `sys_re_user_permission` VALUES ('456', '7', '42', '1');
INSERT INTO `sys_re_user_permission` VALUES ('457', '7', '3', '1');
INSERT INTO `sys_re_user_permission` VALUES ('458', '7', '3', '1');
INSERT INTO `sys_re_user_permission` VALUES ('459', '7', '23', '1');
INSERT INTO `sys_re_user_permission` VALUES ('460', '7', '23', '1');
INSERT INTO `sys_re_user_permission` VALUES ('461', '7', '10', '1');
INSERT INTO `sys_re_user_permission` VALUES ('462', '7', '10', '1');
INSERT INTO `sys_re_user_permission` VALUES ('463', '7', '11', '1');
INSERT INTO `sys_re_user_permission` VALUES ('464', '7', '11', '1');
INSERT INTO `sys_re_user_permission` VALUES ('465', '7', '12', '1');
INSERT INTO `sys_re_user_permission` VALUES ('466', '7', '12', '1');
INSERT INTO `sys_re_user_permission` VALUES ('467', '7', '13', '1');
INSERT INTO `sys_re_user_permission` VALUES ('468', '7', '13', '1');
INSERT INTO `sys_re_user_permission` VALUES ('469', '7', '14', '1');
INSERT INTO `sys_re_user_permission` VALUES ('470', '7', '14', '1');
INSERT INTO `sys_re_user_permission` VALUES ('471', '7', '27', '1');
INSERT INTO `sys_re_user_permission` VALUES ('472', '7', '27', '1');
INSERT INTO `sys_re_user_permission` VALUES ('473', '7', '60', '1');
INSERT INTO `sys_re_user_permission` VALUES ('474', '7', '60', '1');
INSERT INTO `sys_re_user_permission` VALUES ('475', '7', '50', '1');
INSERT INTO `sys_re_user_permission` VALUES ('476', '7', '49', '1');
INSERT INTO `sys_re_user_permission` VALUES ('477', '7', '4', '1');
INSERT INTO `sys_re_user_permission` VALUES ('478', '7', '24', '1');
INSERT INTO `sys_re_user_permission` VALUES ('479', '7', '16', '1');
INSERT INTO `sys_re_user_permission` VALUES ('480', '7', '17', '1');
INSERT INTO `sys_re_user_permission` VALUES ('481', '7', '18', '1');
INSERT INTO `sys_re_user_permission` VALUES ('482', '7', '19', '1');
INSERT INTO `sys_re_user_permission` VALUES ('483', '7', '20', '1');
INSERT INTO `sys_re_user_permission` VALUES ('484', '7', '28', '1');
INSERT INTO `sys_re_user_permission` VALUES ('485', '7', '2', '1');
INSERT INTO `sys_re_user_permission` VALUES ('486', '7', '22', '1');
INSERT INTO `sys_re_user_permission` VALUES ('487', '7', '6', '1');
INSERT INTO `sys_re_user_permission` VALUES ('488', '7', '7', '1');
INSERT INTO `sys_re_user_permission` VALUES ('489', '7', '8', '1');
INSERT INTO `sys_re_user_permission` VALUES ('490', '7', '9', '1');
INSERT INTO `sys_re_user_permission` VALUES ('491', '7', '26', '1');
INSERT INTO `sys_re_user_permission` VALUES ('492', '7', '5', '1');
INSERT INTO `sys_re_user_permission` VALUES ('493', '7', '5', '1');
INSERT INTO `sys_re_user_permission` VALUES ('494', '7', '25', '1');
INSERT INTO `sys_re_user_permission` VALUES ('495', '7', '25', '1');
INSERT INTO `sys_re_user_permission` VALUES ('496', '7', '29', '1');
INSERT INTO `sys_re_user_permission` VALUES ('497', '7', '29', '1');
INSERT INTO `sys_re_user_permission` VALUES ('498', '7', '30', '1');
INSERT INTO `sys_re_user_permission` VALUES ('499', '7', '30', '1');
INSERT INTO `sys_re_user_permission` VALUES ('530', '8', '39', '1');
INSERT INTO `sys_re_user_permission` VALUES ('531', '8', '41', '1');
INSERT INTO `sys_re_user_permission` VALUES ('532', '8', '42', '1');
INSERT INTO `sys_re_user_permission` VALUES ('533', '8', '3', '1');
INSERT INTO `sys_re_user_permission` VALUES ('534', '8', '23', '1');
INSERT INTO `sys_re_user_permission` VALUES ('535', '8', '10', '1');
INSERT INTO `sys_re_user_permission` VALUES ('536', '8', '11', '1');
INSERT INTO `sys_re_user_permission` VALUES ('537', '8', '12', '1');
INSERT INTO `sys_re_user_permission` VALUES ('538', '8', '13', '1');
INSERT INTO `sys_re_user_permission` VALUES ('539', '8', '14', '1');
INSERT INTO `sys_re_user_permission` VALUES ('540', '8', '27', '1');
INSERT INTO `sys_re_user_permission` VALUES ('541', '8', '5', '1');
INSERT INTO `sys_re_user_permission` VALUES ('542', '8', '25', '1');
INSERT INTO `sys_re_user_permission` VALUES ('543', '8', '29', '1');
INSERT INTO `sys_re_user_permission` VALUES ('544', '8', '30', '1');

-- ----------------------------
-- Table structure for sys_re_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_re_user_role`;
CREATE TABLE `sys_re_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL COMMENT '用户ID ',
  `roleId` int(11) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`),
  KEY `FK_SYS_RE_U_REFERENCE_SYS_USER` (`userId`),
  KEY `FK_SYS_RE_U_REFERENCE_SYS_ROLE` (`roleId`),
  CONSTRAINT `FK_SYS_RE_U_REFERENCE_SYS_ROLE` FOREIGN KEY (`roleId`) REFERENCES `sys_role` (`id`),
  CONSTRAINT `FK_SYS_RE_U_REFERENCE_SYS_USER` FOREIGN KEY (`userId`) REFERENCES `sys_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COMMENT='用户角色表';

-- ----------------------------
-- Records of sys_re_user_role
-- ----------------------------
INSERT INTO `sys_re_user_role` VALUES ('16', '2', '1');
INSERT INTO `sys_re_user_role` VALUES ('24', '6', '1');
INSERT INTO `sys_re_user_role` VALUES ('25', '6', '7');
INSERT INTO `sys_re_user_role` VALUES ('26', '5', '7');
INSERT INTO `sys_re_user_role` VALUES ('29', '8', '1');
INSERT INTO `sys_re_user_role` VALUES ('30', '8', '7');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '名称',
  `sort` int(11) NOT NULL COMMENT '排序',
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  `isEnable` int(1) NOT NULL COMMENT '是否启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '系统管理员', '999', '系统管理员', '1');
INSERT INTO `sys_role` VALUES ('7', '部门1', '1', '', '1');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(50) NOT NULL COMMENT '登录名',
  `password` varchar(100) NOT NULL COMMENT '密码(加密)',
  `lastLoginIp` varchar(20) DEFAULT NULL COMMENT '最后登录IP',
  `lastLoginTime` datetime DEFAULT NULL COMMENT '最后登录时间',
  `loginCount` int(11) NOT NULL COMMENT '登录总次数',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  `isEnable` int(1) NOT NULL COMMENT '是否启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('2', 'admin', '26524bdf4ea266f131566a89e8f4972c', '127.0.0.1', '2018-11-28 11:18:39', '411', '2015-06-02 11:31:56', '1');
INSERT INTO `sys_user` VALUES ('5', 'sso-admin', '55ef82a43906b3cda759a826e2fd8526', '127.0.0.1', '2018-10-22 10:59:15', '4', '2018-10-22 10:21:29', '1');
INSERT INTO `sys_user` VALUES ('6', 'sso-sso', '55ef82a43906b3cda759a826e2fd8526', '127.0.0.1', '2018-10-26 14:18:11', '3', '2018-10-22 14:30:57', '0');
INSERT INTO `sys_user` VALUES ('8', 'dds-admin', '26524bdf4ea266f131566a89e8f4972c', null, null, '0', '2018-11-26 17:32:11', '1');
