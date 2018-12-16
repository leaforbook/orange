/*
Navicat MySQL Data Transfer

Source Server         : leaforbook
Source Server Version : 80013
Source Host           : localhost:3306
Source Database       : orange

Target Server Type    : MYSQL
Target Server Version : 80013
File Encoding         : 65001

Date: 2018-12-15 15:13:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for orange_product_price
-- ----------------------------
DROP TABLE IF EXISTS `orange_product_price`;
CREATE TABLE `orange_product_price` (
  `price_id` varchar(64) NOT NULL,
  `product_id` varchar(64) NOT NULL,
  `is_grounding` varchar(2) DEFAULT '1' COMMENT '1已上架 2未上架',
  `attribute_value` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `in_price` decimal(10,2) DEFAULT NULL,
  `out_min_price` decimal(10,2) DEFAULT NULL,
  `out_max_price` decimal(10,2) DEFAULT NULL,
  `date_create` datetime NOT NULL COMMENT '创建时间',
  `date_update` datetime NOT NULL COMMENT '更新时间',
  `by_create` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `by_update` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新人',
  `data_status` varchar(2) NOT NULL DEFAULT '1',
  PRIMARY KEY (`price_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
