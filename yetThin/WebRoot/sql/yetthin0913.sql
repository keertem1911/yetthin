/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : yetthin

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2016-09-13 20:03:19
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'root', '657a5015e9b41c7a6c4ac0d736af8d02', '8');

-- ----------------------------
-- Table structure for `bardata`
-- ----------------------------
DROP TABLE IF EXISTS `bardata`;
CREATE TABLE `bardata` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dateTime` date DEFAULT '0000-00-00',
  `open` varchar(20) DEFAULT '''0''',
  `height` varchar(20) DEFAULT '''0''',
  `low` varchar(20) DEFAULT '''0''',
  `close` varchar(20) DEFAULT '''0''',
  `ystClose` varchar(20) DEFAULT '''0''',
  `volume` varchar(20) DEFAULT '''0''',
  `matchItems` varchar(20) DEFAULT '''0''',
  `sId` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14721 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bardata
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of phoneversion
-- ----------------------------

-- ----------------------------
-- Table structure for `symbolindex`
-- ----------------------------
DROP TABLE IF EXISTS `symbolindex`;
CREATE TABLE `symbolindex` (
  `sid` int(10) NOT NULL,
  `sname` varchar(20) NOT NULL,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of symbolindex
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
  `Jpush_type` varchar(255) DEFAULT '''0,0,0''',
  `token_id` varchar(20) NOT NULL DEFAULT '',
  `verify_email` varchar(255) NOT NULL DEFAULT '',
  `register_time` varchar(255) DEFAULT '',
  `email_status` varchar(255) NOT NULL DEFAULT '"0"',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userinfo
-- ----------------------------
INSERT INTO `userinfo` VALUES ('ea193c352fda49de8e34f319ec411960', '18829290541', '0', '', '20ce4464f745a902143adbd11a07278b', '110', '1', '123123', '0', '', 'false', '', '0');
