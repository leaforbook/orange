/*
Navicat MySQL Data Transfer

Source Server         : 本地MySQL1
Source Server Version : 80012
Source Host           : localhost:3306
Source Database       : orange

Target Server Type    : MYSQL
Target Server Version : 80012
File Encoding         : 65001

Date: 2018-11-05 19:11:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for orange_product
-- ----------------------------
DROP TABLE IF EXISTS `orange_product`;
CREATE TABLE `orange_product` (
  `product_id` varchar(64) NOT NULL,
  `user_id` varchar(64) NOT NULL,
  `product_name` varchar(255) NOT NULL,
  `product_desc` varchar(255) DEFAULT NULL,
  `price_attribute` json DEFAULT NULL,
  `freight_attribute` json DEFAULT NULL,
  `date_create` datetime NOT NULL COMMENT '创建时间',
  `date_update` datetime NOT NULL COMMENT '更新时间',
  `by_create` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `by_update` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新人',
  `data_status` varchar(2) NOT NULL DEFAULT '1',
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
