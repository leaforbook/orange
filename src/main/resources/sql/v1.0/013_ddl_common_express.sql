/*
Navicat MySQL Data Transfer

Source Server         : 本地MySQL1
Source Server Version : 80012
Source Host           : localhost:3306
Source Database       : orange

Target Server Type    : MYSQL
Target Server Version : 80012
File Encoding         : 65001

Date: 2018-11-21 14:25:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for common_express
-- ----------------------------
DROP TABLE IF EXISTS `common_express`;
CREATE TABLE `common_express` (
  `express_id` varchar(64) NOT NULL COMMENT '快递公司ID',
  `type` varchar(32) NOT NULL COMMENT '快递公司代码',
  `name` varchar(64) NOT NULL COMMENT '快递公司名称',
  `date_create` datetime NOT NULL COMMENT '创建时间',
  `date_update` datetime NOT NULL COMMENT '更新时间',
  `by_create` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `by_update` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新人',
  `data_status` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '1',
  PRIMARY KEY (`express_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
