/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : yetthin

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2016-10-26 20:36:43
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'root', '657a5015e9b41c7a6c4ac0d736af8d02', '8');
INSERT INTO `admin` VALUES ('2', 'admin', 'ceb56789e2370abfb8ba187b20492ac7', '8');

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
) ENGINE=InnoDB AUTO_INCREMENT=14941 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bardata
-- ----------------------------
INSERT INTO `bardata` VALUES ('14721', '2016-09-14', '1006.3', '1075.73', '1000.0', '1075.73', '0', '0', '0', '79');
INSERT INTO `bardata` VALUES ('14722', '2016-09-14', '1013.72', '1110.64', '1000.0', '1110.64', '0', '0', '0', '77');
INSERT INTO `bardata` VALUES ('14723', '2016-09-14', '1008.35', '1089.39', '1000.0', '1089.39', '0', '0', '0', '58');
INSERT INTO `bardata` VALUES ('14724', '2016-09-14', '1006.36', '1081.71', '1000.0', '1081.71', '0', '0', '0', '56');
INSERT INTO `bardata` VALUES ('14725', '2016-09-14', '1000.14', '1072.23', '998.89', '1072.23', '0', '0', '0', '18');
INSERT INTO `bardata` VALUES ('14726', '2016-09-14', '1009.8', '1080.75', '1000.0', '1080.75', '0', '0', '0', '15');
INSERT INTO `bardata` VALUES ('14727', '2016-09-14', '1004.8', '1071.37', '1000.0', '1071.37', '0', '0', '0', '33');
INSERT INTO `bardata` VALUES ('14728', '2016-09-14', '1008.47', '1103.66', '1000.0', '1103.66', '0', '0', '0', '13');
INSERT INTO `bardata` VALUES ('14729', '2016-09-14', '1002.13', '1068.55', '999.69', '1068.55', '0', '0', '0', '14');
INSERT INTO `bardata` VALUES ('14730', '2016-09-14', '1016.9', '1153.62', '999.62', '1153.62', '0', '0', '0', '11');
INSERT INTO `bardata` VALUES ('14731', '2016-09-14', '1001.01', '1049.83', '999.84', '1049.83', '0', '0', '0', '12');
INSERT INTO `bardata` VALUES ('14732', '2016-09-14', '1000.85', '1071.11', '1000.0', '1071.05', '0', '0', '0', '20');
INSERT INTO `bardata` VALUES ('14733', '2016-09-14', '1001.16', '1045.09', '1000.0', '1045.09', '0', '0', '0', '43');
INSERT INTO `bardata` VALUES ('14734', '2016-09-14', '1006.79', '1060.66', '1000.0', '1060.66', '0', '0', '0', '42');
INSERT INTO `bardata` VALUES ('14735', '2016-09-14', '1004.35', '1018.33', '1000.0', '1018.33', '0', '0', '0', '41');
INSERT INTO `bardata` VALUES ('14736', '2016-09-14', '1003.45', '1058.44', '1000.0', '1058.35', '0', '0', '0', '40');
INSERT INTO `bardata` VALUES ('14737', '2016-09-14', '1009.13', '1145.23', '1000.0', '1145.23', '0', '0', '0', '64');
INSERT INTO `bardata` VALUES ('14738', '2016-09-14', '1001.93', '1067.28', '998.91', '1067.28', '0', '0', '0', '82');
INSERT INTO `bardata` VALUES ('14739', '2016-09-14', '1006.47', '1040.8', '1000.0', '1040.8', '0', '0', '0', '83');
INSERT INTO `bardata` VALUES ('14740', '2016-09-14', '1006.46', '1100.96', '1000.0', '1100.73', '0', '0', '0', '62');
INSERT INTO `bardata` VALUES ('14741', '2016-09-14', '1002.02', '1053.93', '1000.0', '1053.93', '0', '0', '0', '81');
INSERT INTO `bardata` VALUES ('14742', '2016-09-14', '1000.09', '1048.74', '1000.0', '1048.74', '0', '0', '0', '60');
INSERT INTO `bardata` VALUES ('14743', '2016-09-14', '1003.35', '1081.6', '1000.0', '1081.6', '0', '0', '0', '87');
INSERT INTO `bardata` VALUES ('14744', '2016-09-14', '1003.18', '1057.86', '1000.0', '1057.86', '0', '0', '0', '61');
INSERT INTO `bardata` VALUES ('14745', '2016-09-14', '1004.02', '1096.54', '1000.0', '1096.54', '0', '0', '0', '84');
INSERT INTO `bardata` VALUES ('14746', '2016-09-14', '998.18', '1078.2', '998.18', '1076.12', '0', '0', '0', '68');
INSERT INTO `bardata` VALUES ('14747', '2016-09-14', '1000.0', '1026.04', '1000.0', '1026.04', '0', '0', '0', '44');
INSERT INTO `bardata` VALUES ('14748', '2016-09-14', '1026.68', '1097.42', '1000.0', '1097.42', '0', '0', '0', '24');
INSERT INTO `bardata` VALUES ('14749', '2016-09-14', '1000.0', '1028.43', '1000.0', '1028.43', '0', '0', '0', '28');
INSERT INTO `bardata` VALUES ('14750', '2016-09-14', '1000.25', '1053.81', '1000.0', '1053.81', '0', '0', '0', '3');
INSERT INTO `bardata` VALUES ('14751', '2016-09-14', '1002.65', '1063.63', '999.42', '1063.63', '0', '0', '0', '2');
INSERT INTO `bardata` VALUES ('14752', '2016-09-14', '1001.39', '1039.27', '999.93', '1039.27', '0', '0', '0', '1');
INSERT INTO `bardata` VALUES ('14753', '2016-09-14', '1003.41', '1077.71', '1000.0', '1077.71', '0', '0', '0', '10');
INSERT INTO `bardata` VALUES ('14754', '2016-09-14', '1002.83', '1079.02', '1000.0', '1079.02', '0', '0', '0', '6');
INSERT INTO `bardata` VALUES ('14755', '2016-09-14', '1017.89', '1064.43', '1000.0', '1064.43', '0', '0', '0', '5');
INSERT INTO `bardata` VALUES ('14756', '2016-09-14', '1000.27', '1009.88', '1000.0', '1009.88', '0', '0', '0', '32');
INSERT INTO `bardata` VALUES ('14757', '2016-09-14', '1002.32', '1101.4', '1000.0', '1101.4', '0', '0', '0', '31');
INSERT INTO `bardata` VALUES ('14758', '2016-09-14', '1003.16', '1072.23', '1000.0', '1072.23', '0', '0', '0', '51');
INSERT INTO `bardata` VALUES ('14759', '2016-09-14', '1010.62', '1058.81', '1000.0', '1058.81', '0', '0', '0', '70');
INSERT INTO `bardata` VALUES ('14760', '2016-09-14', '997.63', '1028.12', '997.28', '1028.12', '0', '0', '0', '71');
INSERT INTO `bardata` VALUES ('14761', '2016-09-14', '1005.42', '1053.98', '1000.0', '1053.43', '0', '0', '0', '88');
INSERT INTO `bardata` VALUES ('14762', '2016-09-14', '1001.9', '1071.27', '1000.0', '1071.27', '0', '0', '0', '75');
INSERT INTO `bardata` VALUES ('14763', '2016-09-14', '1001.64', '1058.05', '999.81', '1058.05', '0', '0', '0', '50');
INSERT INTO `bardata` VALUES ('14764', '2016-09-14', '1004.73', '1046.0', '1000.0', '1046.0', '0', '0', '0', '89');
INSERT INTO `bardata` VALUES ('14765', '2016-09-19', '1000.0', '3572.4', '1000.0', '3572.4', '0', '0', '0', '79');
INSERT INTO `bardata` VALUES ('14766', '2016-09-19', '1000.0', '6265.84', '1000.0', '6265.84', '0', '0', '0', '77');
INSERT INTO `bardata` VALUES ('14767', '2016-09-19', '1000.0', '6275.37', '1000.0', '6275.37', '0', '0', '0', '58');
INSERT INTO `bardata` VALUES ('14768', '2016-09-19', '1000.0', '4647.83', '1000.0', '4647.83', '0', '0', '0', '56');
INSERT INTO `bardata` VALUES ('14769', '2016-09-19', '1000.0', '12145.98', '1000.0', '12145.98', '0', '0', '0', '18');
INSERT INTO `bardata` VALUES ('14770', '2016-09-19', '1000.0', '6501.3', '1000.0', '6501.3', '0', '0', '0', '15');
INSERT INTO `bardata` VALUES ('14771', '2016-09-19', '1000.0', '3803.24', '1000.0', '3803.24', '0', '0', '0', '33');
INSERT INTO `bardata` VALUES ('14772', '2016-09-19', '1000.0', '6862.13', '1000.0', '6862.13', '0', '0', '0', '13');
INSERT INTO `bardata` VALUES ('14773', '2016-09-19', '1000.0', '5692.55', '1000.0', '5692.55', '0', '0', '0', '14');
INSERT INTO `bardata` VALUES ('14774', '2016-09-19', '1000.0', '3598.59', '1000.0', '3598.59', '0', '0', '0', '11');
INSERT INTO `bardata` VALUES ('14775', '2016-09-19', '1000.0', '2337.91', '1000.0', '2337.91', '0', '0', '0', '12');
INSERT INTO `bardata` VALUES ('14776', '2016-09-19', '1000.0', '6615.75', '1000.0', '6615.75', '0', '0', '0', '20');
INSERT INTO `bardata` VALUES ('14777', '2016-09-19', '1000.0', '3200.14', '1000.0', '3200.14', '0', '0', '0', '43');
INSERT INTO `bardata` VALUES ('14778', '2016-09-19', '1000.0', '9631.82', '1000.0', '9631.82', '0', '0', '0', '42');
INSERT INTO `bardata` VALUES ('14779', '2016-09-19', '1000.0', '3632.55', '1000.0', '3632.55', '0', '0', '0', '41');
INSERT INTO `bardata` VALUES ('14780', '2016-09-19', '1000.0', '3784.27', '1000.0', '3784.27', '0', '0', '0', '40');
INSERT INTO `bardata` VALUES ('14781', '2016-09-19', '1000.0', '7813.32', '1000.0', '7813.32', '0', '0', '0', '64');
INSERT INTO `bardata` VALUES ('14782', '2016-09-19', '1000.0', '3540.11', '1000.0', '3540.11', '0', '0', '0', '82');
INSERT INTO `bardata` VALUES ('14783', '2016-09-19', '1000.0', '5172.34', '1000.0', '5172.34', '0', '0', '0', '83');
INSERT INTO `bardata` VALUES ('14784', '2016-09-19', '1000.0', '5026.67', '1000.0', '5026.67', '0', '0', '0', '62');
INSERT INTO `bardata` VALUES ('14785', '2016-09-19', '1000.0', '4487.15', '1000.0', '4487.15', '0', '0', '0', '81');
INSERT INTO `bardata` VALUES ('14786', '2016-09-19', '1000.0', '3023.96', '1000.0', '3023.96', '0', '0', '0', '60');
INSERT INTO `bardata` VALUES ('14787', '2016-09-19', '1000.0', '6878.31', '1000.0', '6878.31', '0', '0', '0', '87');
INSERT INTO `bardata` VALUES ('14788', '2016-09-19', '1000.0', '5597.73', '1000.0', '5597.73', '0', '0', '0', '61');
INSERT INTO `bardata` VALUES ('14789', '2016-09-19', '1000.0', '2253.18', '1000.0', '2253.18', '0', '0', '0', '84');
INSERT INTO `bardata` VALUES ('14790', '2016-09-19', '1000.0', '7747.56', '1000.0', '7747.56', '0', '0', '0', '68');
INSERT INTO `bardata` VALUES ('14791', '2016-09-19', '1000.0', '1949.36', '1000.0', '1949.36', '0', '0', '0', '44');
INSERT INTO `bardata` VALUES ('14792', '2016-09-19', '1000.0', '4276.44', '1000.0', '4276.44', '0', '0', '0', '24');
INSERT INTO `bardata` VALUES ('14793', '2016-09-19', '1000.0', '1736.42', '1000.0', '1736.42', '0', '0', '0', '28');
INSERT INTO `bardata` VALUES ('14794', '2016-09-19', '1000.0', '3863.2', '1000.0', '3863.2', '0', '0', '0', '3');
INSERT INTO `bardata` VALUES ('14795', '2016-09-19', '1000.0', '5839.34', '1000.0', '5839.34', '0', '0', '0', '2');
INSERT INTO `bardata` VALUES ('14796', '2016-09-19', '1000.0', '5832.27', '1000.0', '5832.27', '0', '0', '0', '1');
INSERT INTO `bardata` VALUES ('14797', '2016-09-19', '1000.0', '4074.57', '1000.0', '4074.57', '0', '0', '0', '10');
INSERT INTO `bardata` VALUES ('14798', '2016-09-19', '1000.0', '6271.66', '1000.0', '6271.66', '0', '0', '0', '6');
INSERT INTO `bardata` VALUES ('14799', '2016-09-19', '1000.0', '2780.99', '1000.0', '2780.99', '0', '0', '0', '32');
INSERT INTO `bardata` VALUES ('14800', '2016-09-19', '1000.0', '3969.43', '1000.0', '3969.43', '0', '0', '0', '5');
INSERT INTO `bardata` VALUES ('14801', '2016-09-19', '1000.0', '8441.1', '1000.0', '8441.1', '0', '0', '0', '31');
INSERT INTO `bardata` VALUES ('14802', '2016-09-19', '1000.0', '5084.06', '1000.0', '5084.06', '0', '0', '0', '51');
INSERT INTO `bardata` VALUES ('14803', '2016-09-19', '1000.0', '8847.61', '1000.0', '8847.61', '0', '0', '0', '70');
INSERT INTO `bardata` VALUES ('14804', '2016-09-19', '1000.0', '3932.77', '1000.0', '3932.77', '0', '0', '0', '71');
INSERT INTO `bardata` VALUES ('14805', '2016-09-19', '1000.0', '4137.09', '1000.0', '4137.09', '0', '0', '0', '88');
INSERT INTO `bardata` VALUES ('14806', '2016-09-19', '1000.0', '5326.24', '1000.0', '5326.24', '0', '0', '0', '75');
INSERT INTO `bardata` VALUES ('14807', '2016-09-19', '1000.0', '6060.93', '1000.0', '6060.93', '0', '0', '0', '50');
INSERT INTO `bardata` VALUES ('14808', '2016-09-19', '1000.0', '5551.15', '1000.0', '5551.15', '0', '0', '0', '89');
INSERT INTO `bardata` VALUES ('14809', '2016-09-19', '1000.0', '3572.4', '1000.0', '3572.4', '0', '0', '0', '79');
INSERT INTO `bardata` VALUES ('14810', '2016-09-19', '1000.0', '6265.84', '1000.0', '6265.84', '0', '0', '0', '77');
INSERT INTO `bardata` VALUES ('14811', '2016-09-19', '1000.0', '6275.37', '1000.0', '6275.37', '0', '0', '0', '58');
INSERT INTO `bardata` VALUES ('14812', '2016-09-19', '1000.0', '4647.83', '1000.0', '4647.83', '0', '0', '0', '56');
INSERT INTO `bardata` VALUES ('14813', '2016-09-19', '1000.0', '12145.98', '1000.0', '12145.98', '0', '0', '0', '18');
INSERT INTO `bardata` VALUES ('14814', '2016-09-19', '1000.0', '6501.3', '1000.0', '6501.3', '0', '0', '0', '15');
INSERT INTO `bardata` VALUES ('14815', '2016-09-19', '1000.0', '3803.24', '1000.0', '3803.24', '0', '0', '0', '33');
INSERT INTO `bardata` VALUES ('14816', '2016-09-19', '1000.0', '6862.13', '1000.0', '6862.13', '0', '0', '0', '13');
INSERT INTO `bardata` VALUES ('14817', '2016-09-19', '1000.0', '5692.55', '1000.0', '5692.55', '0', '0', '0', '14');
INSERT INTO `bardata` VALUES ('14818', '2016-09-19', '1000.0', '3598.59', '1000.0', '3598.59', '0', '0', '0', '11');
INSERT INTO `bardata` VALUES ('14819', '2016-09-19', '1000.0', '2337.91', '1000.0', '2337.91', '0', '0', '0', '12');
INSERT INTO `bardata` VALUES ('14820', '2016-09-19', '1000.0', '6615.75', '1000.0', '6615.75', '0', '0', '0', '20');
INSERT INTO `bardata` VALUES ('14821', '2016-09-19', '1000.0', '3200.14', '1000.0', '3200.14', '0', '0', '0', '43');
INSERT INTO `bardata` VALUES ('14822', '2016-09-19', '1000.0', '9631.82', '1000.0', '9631.82', '0', '0', '0', '42');
INSERT INTO `bardata` VALUES ('14823', '2016-09-19', '1000.0', '3632.55', '1000.0', '3632.55', '0', '0', '0', '41');
INSERT INTO `bardata` VALUES ('14824', '2016-09-19', '1000.0', '3784.27', '1000.0', '3784.27', '0', '0', '0', '40');
INSERT INTO `bardata` VALUES ('14825', '2016-09-19', '1000.0', '7813.32', '1000.0', '7813.32', '0', '0', '0', '64');
INSERT INTO `bardata` VALUES ('14826', '2016-09-19', '1000.0', '3540.11', '1000.0', '3540.11', '0', '0', '0', '82');
INSERT INTO `bardata` VALUES ('14827', '2016-09-19', '1000.0', '5172.34', '1000.0', '5172.34', '0', '0', '0', '83');
INSERT INTO `bardata` VALUES ('14828', '2016-09-19', '1000.0', '5026.67', '1000.0', '5026.67', '0', '0', '0', '62');
INSERT INTO `bardata` VALUES ('14829', '2016-09-19', '1000.0', '4487.15', '1000.0', '4487.15', '0', '0', '0', '81');
INSERT INTO `bardata` VALUES ('14830', '2016-09-19', '1000.0', '3023.96', '1000.0', '3023.96', '0', '0', '0', '60');
INSERT INTO `bardata` VALUES ('14831', '2016-09-19', '1000.0', '6878.31', '1000.0', '6878.31', '0', '0', '0', '87');
INSERT INTO `bardata` VALUES ('14832', '2016-09-19', '1000.0', '5597.73', '1000.0', '5597.73', '0', '0', '0', '61');
INSERT INTO `bardata` VALUES ('14833', '2016-09-19', '1000.0', '2253.18', '1000.0', '2253.18', '0', '0', '0', '84');
INSERT INTO `bardata` VALUES ('14834', '2016-09-19', '1000.0', '7747.56', '1000.0', '7747.56', '0', '0', '0', '68');
INSERT INTO `bardata` VALUES ('14835', '2016-09-19', '1000.0', '1949.36', '1000.0', '1949.36', '0', '0', '0', '44');
INSERT INTO `bardata` VALUES ('14836', '2016-09-19', '1000.0', '4276.44', '1000.0', '4276.44', '0', '0', '0', '24');
INSERT INTO `bardata` VALUES ('14837', '2016-09-19', '1000.0', '1736.42', '1000.0', '1736.42', '0', '0', '0', '28');
INSERT INTO `bardata` VALUES ('14838', '2016-09-19', '1000.0', '3863.2', '1000.0', '3863.2', '0', '0', '0', '3');
INSERT INTO `bardata` VALUES ('14839', '2016-09-19', '1000.0', '5839.34', '1000.0', '5839.34', '0', '0', '0', '2');
INSERT INTO `bardata` VALUES ('14840', '2016-09-19', '1000.0', '5832.27', '1000.0', '5832.27', '0', '0', '0', '1');
INSERT INTO `bardata` VALUES ('14841', '2016-09-19', '1000.0', '4074.57', '1000.0', '4074.57', '0', '0', '0', '10');
INSERT INTO `bardata` VALUES ('14842', '2016-09-19', '1000.0', '6271.66', '1000.0', '6271.66', '0', '0', '0', '6');
INSERT INTO `bardata` VALUES ('14843', '2016-09-19', '1000.0', '2780.99', '1000.0', '2780.99', '0', '0', '0', '32');
INSERT INTO `bardata` VALUES ('14844', '2016-09-19', '1000.0', '3969.43', '1000.0', '3969.43', '0', '0', '0', '5');
INSERT INTO `bardata` VALUES ('14845', '2016-09-19', '1000.0', '8441.1', '1000.0', '8441.1', '0', '0', '0', '31');
INSERT INTO `bardata` VALUES ('14846', '2016-09-19', '1000.0', '5084.06', '1000.0', '5084.06', '0', '0', '0', '51');
INSERT INTO `bardata` VALUES ('14847', '2016-09-19', '1000.0', '8847.61', '1000.0', '8847.61', '0', '0', '0', '70');
INSERT INTO `bardata` VALUES ('14848', '2016-09-19', '1000.0', '3932.77', '1000.0', '3932.77', '0', '0', '0', '71');
INSERT INTO `bardata` VALUES ('14849', '2016-09-19', '1000.0', '4137.09', '1000.0', '4137.09', '0', '0', '0', '88');
INSERT INTO `bardata` VALUES ('14850', '2016-09-19', '1000.0', '5326.24', '1000.0', '5326.24', '0', '0', '0', '75');
INSERT INTO `bardata` VALUES ('14851', '2016-09-19', '1000.0', '6060.93', '1000.0', '6060.93', '0', '0', '0', '50');
INSERT INTO `bardata` VALUES ('14852', '2016-09-19', '1000.0', '5551.15', '1000.0', '5551.15', '0', '0', '0', '89');
INSERT INTO `bardata` VALUES ('14853', '2016-09-20', '1000.0', '4826.75', '1000.0', '4826.75', '0', '0', '0', '79');
INSERT INTO `bardata` VALUES ('14854', '2016-09-20', '1000.0', '11166.24', '1000.0', '11166.24', '0', '0', '0', '77');
INSERT INTO `bardata` VALUES ('14855', '2016-09-20', '1000.0', '7051.68', '1000.0', '7051.68', '0', '0', '0', '58');
INSERT INTO `bardata` VALUES ('14856', '2016-09-20', '1000.0', '5807.15', '1000.0', '5807.15', '0', '0', '0', '56');
INSERT INTO `bardata` VALUES ('14857', '2016-09-20', '1000.0', '18303.76', '1000.0', '18303.76', '0', '0', '0', '18');
INSERT INTO `bardata` VALUES ('14858', '2016-09-20', '1000.0', '10608.11', '1000.0', '10608.11', '0', '0', '0', '15');
INSERT INTO `bardata` VALUES ('14859', '2016-09-20', '1000.0', '4666.28', '1000.0', '4666.28', '0', '0', '0', '33');
INSERT INTO `bardata` VALUES ('14860', '2016-09-20', '1000.0', '9735.79', '1000.0', '9735.79', '0', '0', '0', '13');
INSERT INTO `bardata` VALUES ('14861', '2016-09-20', '1000.0', '8336.44', '1000.0', '8336.44', '0', '0', '0', '14');
INSERT INTO `bardata` VALUES ('14862', '2016-09-20', '1000.0', '4890.22', '1000.0', '4890.22', '0', '0', '0', '11');
INSERT INTO `bardata` VALUES ('14863', '2016-09-20', '1000.0', '3547.57', '1000.0', '3547.57', '0', '0', '0', '12');
INSERT INTO `bardata` VALUES ('14864', '2016-09-20', '1000.0', '9712.5', '1000.0', '9712.5', '0', '0', '0', '20');
INSERT INTO `bardata` VALUES ('14865', '2016-09-20', '1000.0', '4165.41', '1000.0', '4165.41', '0', '0', '0', '43');
INSERT INTO `bardata` VALUES ('14866', '2016-09-20', '1000.0', '12429.87', '1000.0', '12429.87', '0', '0', '0', '42');
INSERT INTO `bardata` VALUES ('14867', '2016-09-20', '1000.0', '4141.89', '1000.0', '4141.89', '0', '0', '0', '41');
INSERT INTO `bardata` VALUES ('14868', '2016-09-20', '1000.0', '5427.28', '1000.0', '5427.28', '0', '0', '0', '40');
INSERT INTO `bardata` VALUES ('14869', '2016-09-20', '1000.0', '9956.4', '1000.0', '9956.4', '0', '0', '0', '64');
INSERT INTO `bardata` VALUES ('14870', '2016-09-20', '1000.0', '4695.45', '1000.0', '4695.45', '0', '0', '0', '82');
INSERT INTO `bardata` VALUES ('14871', '2016-09-20', '1000.0', '7658.1', '1000.0', '7658.1', '0', '0', '0', '83');
INSERT INTO `bardata` VALUES ('14872', '2016-09-20', '1000.0', '7258.31', '1000.0', '7258.31', '0', '0', '0', '62');
INSERT INTO `bardata` VALUES ('14873', '2016-09-20', '1000.0', '6685.93', '1000.0', '6685.93', '0', '0', '0', '81');
INSERT INTO `bardata` VALUES ('14874', '2016-09-20', '1000.0', '3697.51', '1000.0', '3697.51', '0', '0', '0', '60');
INSERT INTO `bardata` VALUES ('14875', '2016-09-20', '1000.0', '9170.41', '1000.0', '9170.41', '0', '0', '0', '87');
INSERT INTO `bardata` VALUES ('14876', '2016-09-20', '1000.0', '9938.47', '1000.0', '9938.47', '0', '0', '0', '61');
INSERT INTO `bardata` VALUES ('14877', '2016-09-20', '1000.0', '2827.32', '1000.0', '2827.32', '0', '0', '0', '84');
INSERT INTO `bardata` VALUES ('14878', '2016-09-20', '1000.0', '11896.94', '1000.0', '11896.94', '0', '0', '0', '68');
INSERT INTO `bardata` VALUES ('14879', '2016-09-20', '1000.0', '2462.02', '1000.0', '2462.02', '0', '0', '0', '44');
INSERT INTO `bardata` VALUES ('14880', '2016-09-20', '1000.0', '5927.13', '1000.0', '5927.13', '0', '0', '0', '24');
INSERT INTO `bardata` VALUES ('14881', '2016-09-20', '1000.0', '1860.14', '1000.0', '1860.14', '0', '0', '0', '28');
INSERT INTO `bardata` VALUES ('14882', '2016-09-20', '1000.0', '5063.59', '1000.0', '5063.59', '0', '0', '0', '3');
INSERT INTO `bardata` VALUES ('14883', '2016-09-20', '1000.0', '7709.81', '1000.0', '7709.81', '0', '0', '0', '2');
INSERT INTO `bardata` VALUES ('14884', '2016-09-20', '1000.0', '10040.85', '1000.0', '10040.85', '0', '0', '0', '1');
INSERT INTO `bardata` VALUES ('14885', '2016-09-20', '1000.0', '5199.63', '1000.0', '5199.63', '0', '0', '0', '10');
INSERT INTO `bardata` VALUES ('14886', '2016-09-20', '1000.0', '8754.73', '1000.0', '8754.73', '0', '0', '0', '6');
INSERT INTO `bardata` VALUES ('14887', '2016-09-20', '1000.0', '3345.55', '1000.0', '3345.55', '0', '0', '0', '32');
INSERT INTO `bardata` VALUES ('14888', '2016-09-20', '1000.0', '5687.3', '1000.0', '5687.3', '0', '0', '0', '5');
INSERT INTO `bardata` VALUES ('14889', '2016-09-20', '1000.0', '13920.35', '1000.0', '13920.35', '0', '0', '0', '31');
INSERT INTO `bardata` VALUES ('14890', '2016-09-20', '1000.0', '6451.96', '1000.0', '6451.96', '0', '0', '0', '51');
INSERT INTO `bardata` VALUES ('14891', '2016-09-20', '1000.0', '13427.42', '1000.0', '13427.42', '0', '0', '0', '70');
INSERT INTO `bardata` VALUES ('14892', '2016-09-20', '1000.0', '4874.9', '1000.0', '4874.9', '0', '0', '0', '71');
INSERT INTO `bardata` VALUES ('14893', '2016-09-20', '1000.0', '5681.54', '1000.0', '5681.54', '0', '0', '0', '88');
INSERT INTO `bardata` VALUES ('14894', '2016-09-20', '1000.0', '7200.21', '1000.0', '7200.21', '0', '0', '0', '75');
INSERT INTO `bardata` VALUES ('14895', '2016-09-20', '1000.0', '7993.26', '1000.0', '7993.26', '0', '0', '0', '50');
INSERT INTO `bardata` VALUES ('14896', '2016-09-20', '1000.0', '7039.08', '1000.0', '7039.08', '0', '0', '0', '89');
INSERT INTO `bardata` VALUES ('14897', '2016-09-27', '1000.0', '9097.59', '110.74', '2818.12', '0', '0', '0', '79');
INSERT INTO `bardata` VALUES ('14898', '2016-09-27', '1000.0', '12541.35', '545.45', '918.33', '0', '0', '0', '77');
INSERT INTO `bardata` VALUES ('14899', '2016-09-27', '1000.0', '141793.02', '758.2', '26101.49', '0', '0', '0', '58');
INSERT INTO `bardata` VALUES ('14900', '2016-09-27', '1000.0', '7626.26', '83.45', '516.27', '0', '0', '0', '56');
INSERT INTO `bardata` VALUES ('14901', '2016-09-27', '1000.0', '34548.35', '581.07', '5433.63', '0', '0', '0', '18');
INSERT INTO `bardata` VALUES ('14902', '2016-09-27', '1000.0', '13491.98', '216.02', '744.91', '0', '0', '0', '15');
INSERT INTO `bardata` VALUES ('14903', '2016-09-27', '1000.0', '22138.48', '101.75', '1446.77', '0', '0', '0', '33');
INSERT INTO `bardata` VALUES ('14904', '2016-09-27', '1000.0', '15612.4', '269.0', '1312.73', '0', '0', '0', '13');
INSERT INTO `bardata` VALUES ('14905', '2016-09-27', '1000.0', '13235.65', '576.38', '576.38', '0', '0', '0', '14');
INSERT INTO `bardata` VALUES ('14906', '2016-09-27', '1000.0', '5320.92', '42.11', '497.54', '0', '0', '0', '11');
INSERT INTO `bardata` VALUES ('14907', '2016-09-27', '1000.0', '3547.57', '101.4', '137.58', '0', '0', '0', '12');
INSERT INTO `bardata` VALUES ('14908', '2016-09-27', '1000.0', '10828.89', '53.2', '686.07', '0', '0', '0', '20');
INSERT INTO `bardata` VALUES ('14909', '2016-09-27', '1000.0', '4165.41', '94.54', '334.62', '0', '0', '0', '43');
INSERT INTO `bardata` VALUES ('14910', '2016-09-27', '1000.0', '63536.4', '321.3', '3345.68', '0', '0', '0', '42');
INSERT INTO `bardata` VALUES ('14911', '2016-09-27', '1000.0', '6106.51', '168.79', '676.32', '0', '0', '0', '41');
INSERT INTO `bardata` VALUES ('14912', '2016-09-27', '1000.0', '5427.28', '125.12', '200.83', '0', '0', '0', '40');
INSERT INTO `bardata` VALUES ('14913', '2016-09-27', '1000.0', '24897.24', '273.34', '1696.33', '0', '0', '0', '64');
INSERT INTO `bardata` VALUES ('14914', '2016-09-27', '1000.0', '7094.59', '321.36', '783.72', '0', '0', '0', '82');
INSERT INTO `bardata` VALUES ('14915', '2016-09-27', '1000.0', '7658.1', '134.0', '134.0', '0', '0', '0', '83');
INSERT INTO `bardata` VALUES ('14916', '2016-09-27', '1000.0', '18681.75', '362.39', '2405.16', '0', '0', '0', '62');
INSERT INTO `bardata` VALUES ('14917', '2016-09-27', '1000.0', '16424.93', '93.47', '6276.32', '0', '0', '0', '81');
INSERT INTO `bardata` VALUES ('14918', '2016-09-27', '1000.0', '3697.51', '257.79', '257.79', '0', '0', '0', '60');
INSERT INTO `bardata` VALUES ('14919', '2016-09-27', '1000.0', '9170.41', '78.63', '639.13', '0', '0', '0', '87');
INSERT INTO `bardata` VALUES ('14920', '2016-09-27', '1000.0', '9938.47', '168.76', '585.11', '0', '0', '0', '61');
INSERT INTO `bardata` VALUES ('14921', '2016-09-27', '1000.0', '2827.32', '45.09', '285.0', '0', '0', '0', '84');
INSERT INTO `bardata` VALUES ('14922', '2016-09-27', '1000.0', '20363.3', '129.21', '792.52', '0', '0', '0', '68');
INSERT INTO `bardata` VALUES ('14923', '2016-09-27', '1000.0', '23154.66', '1000.0', '7012.31', '0', '0', '0', '44');
INSERT INTO `bardata` VALUES ('14924', '2016-09-27', '1000.0', '12670.88', '102.98', '1159.61', '0', '0', '0', '24');
INSERT INTO `bardata` VALUES ('14925', '2016-09-27', '1000.0', '240355.66', '1000.0', '31302.7', '0', '0', '0', '28');
INSERT INTO `bardata` VALUES ('14926', '2016-09-27', '1000.0', '7010.91', '166.92', '367.12', '0', '0', '0', '3');
INSERT INTO `bardata` VALUES ('14927', '2016-09-27', '1000.0', '11061.71', '88.05', '894.26', '0', '0', '0', '2');
INSERT INTO `bardata` VALUES ('14928', '2016-09-27', '1000.0', '28347.37', '178.13', '1776.38', '0', '0', '0', '1');
INSERT INTO `bardata` VALUES ('14929', '2016-09-27', '1000.0', '5199.63', '78.15', '308.94', '0', '0', '0', '10');
INSERT INTO `bardata` VALUES ('14930', '2016-09-27', '1000.0', '18186.5', '267.54', '1023.25', '0', '0', '0', '6');
INSERT INTO `bardata` VALUES ('14931', '2016-09-27', '1000.0', '3345.55', '156.38', '202.37', '0', '0', '0', '32');
INSERT INTO `bardata` VALUES ('14932', '2016-09-27', '1000.0', '8843.33', '84.68', '314.34', '0', '0', '0', '5');
INSERT INTO `bardata` VALUES ('14933', '2016-09-27', '1000.0', '16565.67', '311.39', '2519.62', '0', '0', '0', '31');
INSERT INTO `bardata` VALUES ('14934', '2016-09-27', '1000.0', '74163.22', '131.28', '4407.47', '0', '0', '0', '51');
INSERT INTO `bardata` VALUES ('14935', '2016-09-27', '1000.0', '13427.42', '68.15', '1133.47', '0', '0', '0', '70');
INSERT INTO `bardata` VALUES ('14936', '2016-09-27', '1000.0', '63218.5', '187.4', '7875.58', '0', '0', '0', '71');
INSERT INTO `bardata` VALUES ('14937', '2016-09-27', '1000.0', '5681.54', '195.39', '386.45', '0', '0', '0', '88');
INSERT INTO `bardata` VALUES ('14938', '2016-09-27', '1000.0', '58094.53', '395.08', '3315.09', '0', '0', '0', '75');
INSERT INTO `bardata` VALUES ('14939', '2016-09-27', '1000.0', '10783.66', '74.49', '873.75', '0', '0', '0', '50');
INSERT INTO `bardata` VALUES ('14940', '2016-09-27', '1000.0', '7039.08', '549.74', '549.74', '0', '0', '0', '89');

-- ----------------------------
-- Table structure for `headpic`
-- ----------------------------
DROP TABLE IF EXISTS `headpic`;
CREATE TABLE `headpic` (
  `id` int(10) NOT NULL,
  `hrefUrl` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of headpic
-- ----------------------------
INSERT INTO `headpic` VALUES ('1', 'www.baidu.com,www.baidu.com');
INSERT INTO `headpic` VALUES ('2', 'www.baidu.com,www.baidu.com');
INSERT INTO `headpic` VALUES ('3', 'www.baidu.com,www.baidu.com');

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
