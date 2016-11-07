/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : yetthin

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2016-10-24 09:15:07
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `tb_concept`
-- ----------------------------
DROP TABLE IF EXISTS `tb_concept`;
CREATE TABLE `tb_concept` (
  `concept_id` varchar(255) NOT NULL,
  `concept_name` varchar(40) DEFAULT NULL,
  `concept_create_time` varchar(20) DEFAULT NULL,
  `concept_heat_level` double(100,0) DEFAULT '0',
  PRIMARY KEY (`concept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_concept
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_disscussinfo`
-- ----------------------------
DROP TABLE IF EXISTS `tb_disscussinfo`;
CREATE TABLE `tb_disscussinfo` (
  `discussinfo_id` varchar(255) NOT NULL,
  `discussinfo_sendperson_id` varchar(255) NOT NULL,
  `discussinfo_content` text,
  `discussinfo_create_time` varchar(20) DEFAULT NULL,
  `discussinfo_higher_id` varchar(255) DEFAULT NULL,
  `discussinfo_group_id` varchar(255) NOT NULL,
  PRIMARY KEY (`discussinfo_id`),
  KEY `discussinfo_group_id` (`discussinfo_group_id`),
  KEY `tb_disscussinfo_ibfk_1` (`discussinfo_sendperson_id`),
  CONSTRAINT `tb_disscussinfo_ibfk_1` FOREIGN KEY (`discussinfo_sendperson_id`) REFERENCES `tb_user` (`user_id`),
  CONSTRAINT `tb_disscussinfo_ibfk_2` FOREIGN KEY (`discussinfo_group_id`) REFERENCES `tb_group` (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_disscussinfo
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_fundaccount`
-- ----------------------------
DROP TABLE IF EXISTS `tb_fundaccount`;
CREATE TABLE `tb_fundaccount` (
  `fundaccount_id` varchar(255) NOT NULL,
  `fundaccount_num` varchar(50) DEFAULT NULL,
  `fundaccount_user_id` varchar(255) DEFAULT NULL,
  `fundaccount_create_department` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`fundaccount_id`),
  KEY `fundaccount_user_id` (`fundaccount_user_id`),
  CONSTRAINT `tb_fundaccount_ibfk_1` FOREIGN KEY (`fundaccount_user_id`) REFERENCES `tb_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_fundaccount
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_group`
-- ----------------------------
DROP TABLE IF EXISTS `tb_group`;
CREATE TABLE `tb_group` (
  `group_id` varchar(255) NOT NULL,
  `group_name` varchar(100) DEFAULT NULL,
  `group_create_id` varchar(255) NOT NULL,
  `group_create_time` varchar(20) NOT NULL,
  `group_init_money` double(100,0) DEFAULT NULL,
  `group_evaluate_level` char(1) DEFAULT NULL,
  `group_income_totle` double(100,0) DEFAULT NULL,
  `group_strategy_id` varchar(255) DEFAULT NULL,
  `group_refer_index` varchar(255) DEFAULT NULL,
  `group_emotion_index` double(100,0) DEFAULT '0',
  `group_warning_level` double(100,0) DEFAULT '0',
  `group_media_attention_rate` double(100,0) DEFAULT '0',
  `group_netizen_attention_rate` double(100,0) DEFAULT '0',
  PRIMARY KEY (`group_id`),
  KEY `tb_group_ibfk_2` (`group_strategy_id`),
  KEY `tb_group_ibfk_1` (`group_create_id`),
  CONSTRAINT `tb_group_ibfk_1` FOREIGN KEY (`group_create_id`) REFERENCES `tb_user` (`user_id`),
  CONSTRAINT `tb_group_ibfk_2` FOREIGN KEY (`group_strategy_id`) REFERENCES `tb_strategy` (`strategy_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_group
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_profitdata`
-- ----------------------------
DROP TABLE IF EXISTS `tb_profitdata`;
CREATE TABLE `tb_profitdata` (
  `profitdata_id` varchar(255) NOT NULL,
  `profitdata_time` varchar(11) DEFAULT NULL,
  `profitdata_group_id` varchar(255) DEFAULT NULL,
  `profitdata_share_fund_num` double(100,0) DEFAULT NULL,
  `profitdata_stock` double(100,0) DEFAULT NULL,
  PRIMARY KEY (`profitdata_id`),
  KEY `profitdata_group_id` (`profitdata_group_id`),
  CONSTRAINT `tb_profitdata_ibfk_1` FOREIGN KEY (`profitdata_group_id`) REFERENCES `tb_group` (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_profitdata
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_reasonword`
-- ----------------------------
DROP TABLE IF EXISTS `tb_reasonword`;
CREATE TABLE `tb_reasonword` (
  `reasonword_id` varchar(255) NOT NULL,
  `reasonword_group_id` varchar(255) NOT NULL,
  `reasonword_content` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`reasonword_id`),
  KEY `reasonword_group_id` (`reasonword_group_id`),
  CONSTRAINT `tb_reasonword_ibfk_1` FOREIGN KEY (`reasonword_group_id`) REFERENCES `tb_group` (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_reasonword
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_share`
-- ----------------------------
DROP TABLE IF EXISTS `tb_share`;
CREATE TABLE `tb_share` (
  `share_id` varchar(255) NOT NULL,
  `share_group_id` varchar(255) NOT NULL,
  `share_stocklable_id` varchar(255) NOT NULL,
  `share_start_fund` double(100,0) NOT NULL DEFAULT '0',
  `share_current_num` bigint(20) NOT NULL DEFAULT '0',
  `share_current_income` double(100,0) NOT NULL DEFAULT '0',
  PRIMARY KEY (`share_id`),
  KEY `share_group_id` (`share_group_id`),
  KEY `share_stocklable_id` (`share_stocklable_id`),
  CONSTRAINT `tb_share_ibfk_2` FOREIGN KEY (`share_stocklable_id`) REFERENCES `tb_stocklable` (`stocklable_id`),
  CONSTRAINT `tb_share_ibfk_1` FOREIGN KEY (`share_group_id`) REFERENCES `tb_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_share
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_sharechange`
-- ----------------------------
DROP TABLE IF EXISTS `tb_sharechange`;
CREATE TABLE `tb_sharechange` (
  `sharechange_id` varchar(255) NOT NULL,
  `sharechange_share_id` varchar(255) DEFAULT NULL,
  `sharechange_time` varchar(21) DEFAULT NULL,
  `sharechange_price` double(100,0) DEFAULT NULL,
  `sharechange_dir` varchar(1) DEFAULT NULL,
  `sharechange_num` int(11) DEFAULT NULL,
  PRIMARY KEY (`sharechange_id`),
  KEY `sharechange_share_id` (`sharechange_share_id`),
  CONSTRAINT `tb_sharechange_ibfk_1` FOREIGN KEY (`sharechange_share_id`) REFERENCES `tb_share` (`share_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_sharechange
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_stocklable`
-- ----------------------------
DROP TABLE IF EXISTS `tb_stocklable`;
CREATE TABLE `tb_stocklable` (
  `stocklable_id` varchar(255) NOT NULL,
  `stocklable_code` varchar(100) DEFAULT NULL,
  `stocklable_name` varchar(100) DEFAULT NULL,
  `stocklable_type` char(1) DEFAULT NULL,
  `stocklable_market` char(1) DEFAULT NULL,
  `stocklable_trade` int(11) DEFAULT NULL,
  `stocklable_status` char(1) DEFAULT NULL,
  PRIMARY KEY (`stocklable_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_stocklable
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_stocklable2concept`
-- ----------------------------
DROP TABLE IF EXISTS `tb_stocklable2concept`;
CREATE TABLE `tb_stocklable2concept` (
  `stocklable2concept_id` varchar(255) NOT NULL,
  `stocklable2concept_sl_id` varchar(255) NOT NULL,
  `stocklable2concept_cc_id` varchar(255) NOT NULL,
  PRIMARY KEY (`stocklable2concept_id`),
  KEY `stocklable2concept_sl_id` (`stocklable2concept_sl_id`),
  KEY `stocklable2concept_cc_id` (`stocklable2concept_cc_id`),
  CONSTRAINT `tb_stocklable2concept_ibfk_2` FOREIGN KEY (`stocklable2concept_cc_id`) REFERENCES `tb_concept` (`concept_id`),
  CONSTRAINT `tb_stocklable2concept_ibfk_1` FOREIGN KEY (`stocklable2concept_sl_id`) REFERENCES `tb_stocklable` (`stocklable_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_stocklable2concept
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_strategy`
-- ----------------------------
DROP TABLE IF EXISTS `tb_strategy`;
CREATE TABLE `tb_strategy` (
  `strategy_id` varchar(255) NOT NULL,
  `strategy_name` varchar(100) DEFAULT NULL,
  `strategy_type` char(1) DEFAULT NULL,
  `strategy_invest_cycle` char(1) DEFAULT NULL,
  `strategy_fund_size` char(1) DEFAULT NULL,
  PRIMARY KEY (`strategy_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_strategy
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_user`
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `user_id` varchar(255) NOT NULL DEFAULT '1',
  `user_phone` varchar(11) NOT NULL,
  `user_name` varchar(20) DEFAULT NULL,
  `user_password` varchar(20) NOT NULL,
  `user_firm` varchar(200) DEFAULT NULL,
  `user_vip_flag` varchar(2) DEFAULT '0',
  `user_register_time` varchar(20) NOT NULL,
  `user_trade_password` varchar(20) DEFAULT NULL,
  `user_communi_password` varchar(20) DEFAULT NULL,
  `user_income_totle` double DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
