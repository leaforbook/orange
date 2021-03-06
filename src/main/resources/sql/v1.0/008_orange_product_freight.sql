/*
Navicat MySQL Data Transfer

Source Server         : leaforbook
Source Server Version : 80013
Source Host           : localhost:3306
Source Database       : orange

Target Server Type    : MYSQL
Target Server Version : 80013
File Encoding         : 65001

Date: 2018-12-15 15:13:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for orange_product_freight
-- ----------------------------
DROP TABLE IF EXISTS `orange_product_freight`;
CREATE TABLE `orange_product_freight` (
  `freight_id` varchar(64) NOT NULL,
  `product_id` varchar(64) NOT NULL,
  `is_free` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '1' COMMENT '1不包邮 2包邮',
  `attribute_value` varchar(1024) DEFAULT NULL,
  `freight_price` decimal(10,2) DEFAULT NULL,
  `date_create` datetime NOT NULL COMMENT '创建时间',
  `date_update` datetime NOT NULL COMMENT '更新时间',
  `by_create` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `by_update` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新人',
  `data_status` varchar(2) NOT NULL DEFAULT '1',
  PRIMARY KEY (`freight_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
