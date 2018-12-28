/*
Navicat MySQL Data Transfer

Source Server         : 本地MySQL1
Source Server Version : 80012
Source Host           : localhost:3306
Source Database       : orange

Target Server Type    : MYSQL
Target Server Version : 80012
File Encoding         : 65001

Date: 2018-11-01 17:08:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for orange_order
-- ----------------------------
DROP TABLE IF EXISTS `orange_order`;
CREATE TABLE `orange_order` (
  `order_id` varchar(64) NOT NULL,
  `user_id` varchar(64) NOT NULL,
  `product_id` varchar(64) NOT NULL,
  `price_id` varchar(64) NOT NULL,
  `freight_id` varchar(64) NOT NULL,
  `address_id` varchar(64) NULL,
  `amount` decimal(10,0) NOT NULL,
  `actual_unit_price` decimal(10,0) NOT NULL,
  `total_price` decimal(10,0) NOT NULL,
  `actual_freight` decimal(10,0) NOT NULL,
  `total_cost` decimal(10,0) NOT NULL,
  `delivery_date` datetime NULL,
  `order_status` varchar(2) NOT NULL,
  `date_create` datetime NOT NULL COMMENT '创建时间',
  `date_update` datetime NOT NULL COMMENT '更新时间',
  `by_create` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `by_update` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新人',
  `data_status` varchar(2) NOT NULL DEFAULT '1',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
