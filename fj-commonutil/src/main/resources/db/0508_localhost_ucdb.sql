/*
Navicat MySQL Data Transfer

Source Server         : 本机
Source Server Version : 50722
Source Host           : localhost:3306
Source Database       : ucdb

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2018-05-08 15:49:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for uc_group_info
-- ----------------------------
DROP TABLE IF EXISTS `uc_group_info`;
CREATE TABLE `uc_group_info` (
  `gID` varchar(32) NOT NULL COMMENT '主键',
  `gName` varchar(50) NOT NULL COMMENT '用户组名称',
  `parentID` varchar(32) NOT NULL COMMENT '父级用户组ID',
  `gStatus` char(1) DEFAULT NULL COMMENT '用户组状态',
  `rights` text COMMENT '用户组权限',
  `createDte` varchar(24) NOT NULL COMMENT '创建时间',
  `editDte` varchar(24) NOT NULL COMMENT '编辑时间',
  `operatorID` varchar(32) NOT NULL COMMENT '创建人',
  `gRow1` varchar(10) NOT NULL COMMENT '扩展字段1',
  `gRow2` varchar(32) NOT NULL COMMENT '扩展字段2',
  `gRow3` varchar(100) NOT NULL COMMENT '扩展字段3',
  PRIMARY KEY (`gID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户组信息表';

-- ----------------------------
-- Records of uc_group_info
-- ----------------------------

-- ----------------------------
-- Table structure for uc_menu_info
-- ----------------------------
DROP TABLE IF EXISTS `uc_menu_info`;
CREATE TABLE `uc_menu_info` (
  `menuID` varchar(32) NOT NULL COMMENT '主键',
  `menuName` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `menuUrl` varchar(100) DEFAULT NULL COMMENT '菜单地址',
  `parentID` varchar(32) DEFAULT NULL COMMENT '父级菜单ID',
  `menuRank` int(8) DEFAULT NULL COMMENT '菜单序号',
  `menuStatus` char(1) DEFAULT NULL COMMENT '菜单状态',
  `createDte` varchar(24) DEFAULT NULL COMMENT '创建时间',
  `editDte` varchar(24) DEFAULT NULL COMMENT '编辑时间',
  `operatorID` varchar(32) DEFAULT NULL COMMENT '创建人',
  `menuRow1` varchar(10) DEFAULT NULL COMMENT '扩展字段1',
  `menuRow2` varchar(32) DEFAULT NULL COMMENT '扩展字段2',
  `menuRow3` varchar(100) DEFAULT NULL COMMENT '扩展字段3',
  PRIMARY KEY (`menuID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限菜单信息表';

-- ----------------------------
-- Records of uc_menu_info
-- ----------------------------

-- ----------------------------
-- Table structure for uc_user_group
-- ----------------------------
DROP TABLE IF EXISTS `uc_user_group`;
CREATE TABLE `uc_user_group` (
  `ugID` varchar(32) NOT NULL COMMENT '主键',
  `userID` varchar(32) DEFAULT NULL COMMENT '用户ID',
  `gID` varchar(32) DEFAULT NULL COMMENT '用户组ID',
  `createDte` varchar(24) DEFAULT NULL COMMENT '创建时间',
  `valiDte` varchar(24) DEFAULT NULL COMMENT '有效期',
  `ugStatus` char(1) DEFAULT NULL COMMENT '状态 0-正常 F-过期 1-异常',
  PRIMARY KEY (`ugID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户绑定用户组表';

-- ----------------------------
-- Records of uc_user_group
-- ----------------------------

-- ----------------------------
-- Table structure for uc_user_info
-- ----------------------------
DROP TABLE IF EXISTS `uc_user_info`;
CREATE TABLE `uc_user_info` (
  `userID` varchar(32) NOT NULL COMMENT '主键',
  `userAccount` varchar(32) NOT NULL COMMENT '用户账号',
  `userPass` varchar(32) NOT NULL COMMENT '密码',
  `createDte` varchar(24) NOT NULL COMMENT '创建时间',
  `userIcon` varchar(250) DEFAULT NULL COMMENT '用户头像',
  `userStatus` char(1) NOT NULL COMMENT '用户状态 0-正常，D-删除，1-停用/注销',
  `valiDte` varchar(24) DEFAULT NULL COMMENT '有效期',
  `operatorID` varchar(32) DEFAULT NULL COMMENT '创建人/介绍人',
  `ownerID` varchar(32) DEFAULT NULL COMMENT '归属',
  `userRow1` varchar(10) DEFAULT NULL COMMENT '扩展字段1',
  `userRow2` varchar(32) DEFAULT NULL COMMENT '扩展字段2',
  `userRow3` varchar(100) DEFAULT NULL COMMENT '扩展字段3',
  PRIMARY KEY (`userID`),
  UNIQUE KEY `userAccount` (`userAccount`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息表';

-- ----------------------------
-- Records of uc_user_info
-- ----------------------------
