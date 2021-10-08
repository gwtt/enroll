/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : localhost:3306
 Source Schema         : enroll

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : 65001

 Date: 03/10/2021 21:48:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for count_student
-- ----------------------------
DROP TABLE IF EXISTS `count_student`;
CREATE TABLE `count_student`  (
  `count` int(11) NULL DEFAULT NULL COMMENT '打卡数'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of count_student
-- ----------------------------
INSERT INTO `count_student` VALUES (1);

SET FOREIGN_KEY_CHECKS = 1;
