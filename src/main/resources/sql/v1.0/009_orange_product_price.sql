/*
Navicat MySQL Data Transfer

Source Server         : 本地MySQL1
Source Server Version : 80012
Source Host           : localhost:3306
Source Database       : orange

Target Server Type    : MYSQL
Target Server Version : 80012
File Encoding         : 65001

Date: 2018-11-05 19:12:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for orange_product_price
-- ----------------------------
DROP TABLE IF EXISTS `orange_product_price`;
CREATE TABLE `orange_product_price` (
  `price_id` varchar(64) NOT NULL,
  `product_id` varchar(64) NOT NULL,
  `attribute_json` json DEFAULT NULL,
  `in_price` decimal(10,2) NOT NULL,
  `out_min_price` decimal(10,2) NOT NULL,
  `out_max_price` decimal(10,2) NOT NULL,
  `date_create` datetime NOT NULL COMMENT '创建时间',
  `date_update` datetime NOT NULL COMMENT '更新时间',
  `by_create` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `by_update` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新人',
  `data_status` varchar(2) NOT NULL DEFAULT '1',
  PRIMARY KEY (`price_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
