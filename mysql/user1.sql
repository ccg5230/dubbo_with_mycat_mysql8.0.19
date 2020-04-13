/*
Navicat MySQL Data Transfer

Source Server         : master
Source Server Version : 80019
Source Host           : localhost:3307
Source Database       : mycat-test

Target Server Type    : MYSQL
Target Server Version : 80019
File Encoding         : 65001

Date: 2020-04-13 13:39:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user1
-- ----------------------------
DROP TABLE IF EXISTS `user1`;
CREATE TABLE `user1` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `real_name` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '真实名称',
  `mobile` varchar(11) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '手机号码',
  `password` varchar(20) COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建日期',
  `update_time` datetime(3) DEFAULT NULL COMMENT '修改日期',
  `del_flag` char(1) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '删除标记 1:删除;0:未删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户表';
