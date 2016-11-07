/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : yetthin

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2016-08-09 17:20:08
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------

-- ----------------------------
-- Table structure for `phoneversion`
-- ----------------------------
DROP TABLE IF EXISTS `phoneversion`;
CREATE TABLE `phoneversion` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `version_code` varchar(255) DEFAULT '1',
  `version_name` varchar(255) DEFAULT NULL,
  `apk_url` varchar(255) DEFAULT NULL,
  `Eexplain` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of phoneversion
-- ----------------------------

-- ----------------------------
-- Table structure for `userinfo`
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo` (
  `user_id` varchar(255) NOT NULL,
  `phone_num` varchar(11) NOT NULL DEFAULT '',
  `my_money` double DEFAULT '0',
  `email` varchar(255) DEFAULT '',
  `password` varchar(255) NOT NULL DEFAULT '',
  `Jpush_id` varchar(255) DEFAULT '',
  `Jpush_status` tinyint(1) DEFAULT '1',
  `idea_text` text,
  `Jpush_type` varchar(255) DEFAULT '',
  `token_id` varchar(20) NOT NULL DEFAULT '',
  `verify_email` varchar(255) NOT NULL DEFAULT '',
  `register_time` varchar(255) DEFAULT '',
  `email_status` varchar(255) NOT NULL DEFAULT '"0"',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userinfo
-- ----------------------------
INSERT INTO `userinfo` VALUES ('a579190bddb34b169bb2c21ffc68ee07', '18829290541', '0', '', '5edde955a6410aa24b0f228ad8629862', '', '1', '', '', '', 'false', '', '0');
INSERT INTO `userinfo` VALUES ('db90522522df4ec7980f9ea6023882f1', '18829290542', '0', '', '3a1599e6590364b147a8b9967de9955c', '', '1', '111', '', '', 'false', '', '0');
INSERT INTO `userinfo` VALUES ('f730740afa6c48c792a910fa7f70fd72', '18829290543', '0', '', '3575793e98f8b0d342e1457fe527cb11', '', '1', '', '', '', 'false', '', '0');
