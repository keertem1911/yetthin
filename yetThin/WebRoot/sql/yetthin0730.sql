/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : yetthin

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2016-07-30 18:19:44
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `admin_name` varchar(255) NOT NULL,
  `admin_password` varchar(255) NOT NULL,
  `admin_power` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------

-- ----------------------------
-- Table structure for `phoneversion`
-- ----------------------------
DROP TABLE IF EXISTS `phoneversion`;
CREATE TABLE `phoneversion` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `version_code` varchar(255) DEFAULT NULL,
  `version_name` varchar(255) DEFAULT NULL,
  `apk_url` varchar(255) DEFAULT NULL,
  `explain` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of phoneversion
-- ----------------------------

-- ----------------------------
-- Table structure for `tempcode`
-- ----------------------------
DROP TABLE IF EXISTS `tempcode`;
CREATE TABLE `tempcode` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `verify_code` varchar(255) DEFAULT NULL,
  `end_time` datetime NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tempcode
-- ----------------------------

-- ----------------------------
-- Table structure for `userinfo`
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo` (
  `user_id` int(20) NOT NULL AUTO_INCREMENT,
  `phone_num` varchar(11) NOT NULL,
  `my_money` double DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `Jpush_id` varchar(255) DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  `idea_text` text,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userinfo
-- ----------------------------
INSERT INTO `userinfo` VALUES ('7', '18829290544', null, null, '99e2fd8f9715b578095d41debfed704a', '123123123', '0', '12312');
