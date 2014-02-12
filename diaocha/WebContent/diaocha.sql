/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50150
Source Host           : localhost:3306
Source Database       : diaocha

Target Server Type    : MYSQL
Target Server Version : 50150
File Encoding         : 65001

Date: 2014-01-22 14:25:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admins`
-- ----------------------------
DROP TABLE IF EXISTS `admins`;
CREATE TABLE `admins` (
  `a_id` int(11) NOT NULL AUTO_INCREMENT,
  `a_user` varchar(50) DEFAULT NULL,
  `a_pass` varchar(50) DEFAULT NULL,
  `a_email` varchar(100) DEFAULT NULL,
  `a_phone` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`a_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admins
-- ----------------------------
INSERT INTO `admins` VALUES ('1', 'admin', '123456', '123123@qq.com', '15524757633');
INSERT INTO `admins` VALUES ('12', 'zhaodi', '123456', '12312@qq.com', '15524757633');
INSERT INTO `admins` VALUES ('13', 'sss', '123456', '122222@qq.com', '15524757633');

-- ----------------------------
-- Table structure for `answersheet`
-- ----------------------------
DROP TABLE IF EXISTS `answersheet`;
CREATE TABLE `answersheet` (
  `as_id` int(11) NOT NULL AUTO_INCREMENT,
  `s_id` int(11) NOT NULL,
  `as_result` varchar(8000) DEFAULT NULL,
  `as_postdate` date DEFAULT NULL,
  `as_userIP` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`as_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of answersheet
-- ----------------------------
INSERT INTO `answersheet` VALUES ('16', '32', '11:as=0;', '2014-01-18', '127.0.0.1');
INSERT INTO `answersheet` VALUES ('17', '32', '16:as=2,3;', '2014-01-18', '127.0.0.1');
INSERT INTO `answersheet` VALUES ('18', '32', '16:as=0;', '2014-01-18', '127.0.0.1');
INSERT INTO `answersheet` VALUES ('19', '32', '16:as=1;', '2014-01-18', '127.0.0.1');
INSERT INTO `answersheet` VALUES ('20', '34', '17:as=0;&@@&18:as=0,3;', '2014-01-18', '127.0.0.1');
INSERT INTO `answersheet` VALUES ('21', '30', '17:as=1;&@@&18:as=0;&@@&19:as=1,3;&@@&21:as=3;', '2014-01-22', '127.0.0.1');
INSERT INTO `answersheet` VALUES ('22', '34', '22:as=0;&@@&23:as=0,2;', '2014-01-22', '127.0.0.1');
INSERT INTO `answersheet` VALUES ('23', '35', '24:as=0,1;', '2014-01-22', '127.0.0.1');
INSERT INTO `answersheet` VALUES ('24', '30', '17:as=0;&@@&18:as=0;&@@&19:as=3;&@@&21:as=1;', '2014-01-22', '127.0.0.1');

-- ----------------------------
-- Table structure for `config`
-- ----------------------------
DROP TABLE IF EXISTS `config`;
CREATE TABLE `config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `c_siteName` varchar(500) DEFAULT NULL,
  `c_siteURL` varchar(500) DEFAULT NULL,
  `c_isOpen` tinyint(1) DEFAULT NULL,
  `c_closeWord` varchar(1000) DEFAULT NULL,
  `copyright` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of config
-- ----------------------------
INSERT INTO `config` VALUES ('1', '东软问卷调查管理系统', 'http://localhost:8080/diaocha', '0', '数据维护', 'Copyright&copy;2013');

-- ----------------------------
-- Table structure for `link`
-- ----------------------------
DROP TABLE IF EXISTS `link`;
CREATE TABLE `link` (
  `l_id` int(11) NOT NULL AUTO_INCREMENT,
  `l_url` varchar(500) DEFAULT NULL,
  `l_name` varchar(50) DEFAULT NULL,
  `l_img` varchar(500) DEFAULT NULL,
  `l_info` varchar(1000) DEFAULT NULL,
  `l_isLock` tinyint(1) DEFAULT NULL,
  `l_addtime` date DEFAULT NULL,
  PRIMARY KEY (`l_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of link
-- ----------------------------
INSERT INTO `link` VALUES ('3', 'http://hao123.com', 'hao123网站', null, '这是一个很好的网站', '0', '2013-11-29');
INSERT INTO `link` VALUES ('4', 'http://hao123.com', '好123网站', null, '这是一个很好的网站', '0', '2013-11-29');
INSERT INTO `link` VALUES ('5', 'http://hao123.com', 'hao123网站', null, '这是一个很好的网站', '0', '2013-11-29');
INSERT INTO `link` VALUES ('7', 'http://hao123.com', 'hao123网站', null, '这是一个很好的网站', '0', '2013-11-29');
INSERT INTO `link` VALUES ('12', 'www.baud.com', '东软', null, '访问东软', '0', '2013-11-29');
INSERT INTO `link` VALUES ('14', 'http://hao123.com', '好123网站', null, '123', '0', '2014-01-18');
INSERT INTO `link` VALUES ('16', 'http://hao123.com', '345', null, '346', '0', '2014-01-18');

-- ----------------------------
-- Table structure for `question`
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question` (
  `q_id` int(11) NOT NULL AUTO_INCREMENT,
  `s_id` int(11) NOT NULL,
  `q_type` int(11) DEFAULT NULL,
  `q_head` varchar(1000) DEFAULT NULL,
  `q_body` varchar(8000) DEFAULT NULL,
  `q_result` varchar(1000) DEFAULT NULL,
  `q_img` varchar(1000) DEFAULT NULL,
  `q_jdtz` varchar(1000) DEFAULT NULL,
  `q_order` int(11) DEFAULT NULL,
  PRIMARY KEY (`q_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of question
-- ----------------------------
INSERT INTO `question` VALUES ('17', '30', '1', '你饭后的活动是什么', '踢球&$$&散步&$$&跑步&$$&游泳', '2,1,0,0', null, 'null&null&null&null', '0');
INSERT INTO `question` VALUES ('18', '30', '2', '你饭后去的地方', '公园&$$&家里呆着&$$&马路&$$&广场', '3,0,0,1', null, 'null&null&null&null', '0');
INSERT INTO `question` VALUES ('19', '30', '2', '123123', '3245&$$&456&$$&576&$$&567', '0,1,0,2', null, 'null&null&null&null', '0');
INSERT INTO `question` VALUES ('21', '30', '1', 'asd', '123&$$&123&$$&3245&$$&1234', '0,1,0,1', null, 'null&null&null&null', '0');
INSERT INTO `question` VALUES ('22', '34', '1', '你喜欢的健身运动', '跑步&$$&跳远&$$&游泳&$$&跳舞', '1,0,0,0', null, 'null&null&null&null', '0');
INSERT INTO `question` VALUES ('23', '34', '2', '你经常参加体育锻炼的地方', '后院&$$&体育馆&$$&学校&$$&广场', '1,0,1,0', null, 'null&null&null&null', '0');
INSERT INTO `question` VALUES ('24', '35', '2', '你经常参加体育锻炼的地方', '广场&$$&游泳馆&$$&体育馆&$$&学校操场', '1,1,0,0', null, 'null&null&null&null', '0');

-- ----------------------------
-- Table structure for `survey`
-- ----------------------------
DROP TABLE IF EXISTS `survey`;
CREATE TABLE `survey` (
  `s_id` int(11) NOT NULL AUTO_INCREMENT,
  `s_name` varchar(100) DEFAULT NULL,
  `s_desc` varchar(500) DEFAULT NULL,
  `s_author` varchar(100) DEFAULT NULL,
  `s_img` varchar(1000) DEFAULT NULL,
  `s_createDate` date DEFAULT NULL,
  `s_password` varchar(100) DEFAULT NULL,
  `s_isOpen` tinyint(1) DEFAULT NULL,
  `s_expireDate` date DEFAULT NULL,
  `s_isAudited` tinyint(1) DEFAULT NULL,
  `s_usehits` int(11) DEFAULT NULL,
  PRIMARY KEY (`s_id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of survey
-- ----------------------------
INSERT INTO `survey` VALUES ('30', '大连东软公司', '12312234', '宋树义', null, '2014-01-18', null, '1', '2014-02-17', '1', '5');
INSERT INTO `survey` VALUES ('32', '调查个人爱好阿萨德', '安心想信息阿萨德', '大连诚高嘻嘻嘻', null, '2014-01-18', null, '1', '2014-02-17', '1', '4');
INSERT INTO `survey` VALUES ('34', '体育爱好调查', '调查现在人们的体育爱好', '宋树义', null, '2014-01-18', null, '1', '2014-02-17', '1', '2');
INSERT INTO `survey` VALUES ('35', '图片问卷', '图片问卷描述', '宋树义', '1390357040195.gif', '2014-01-22', null, '1', '2014-02-21', '1', '1');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) NOT NULL,
  `nickname` varchar(50) DEFAULT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('14', '123123@qq.com', '赵迪', '123456');
INSERT INTO `user` VALUES ('15', '12312@qq.com', '小赵', '123456');
INSERT INTO `user` VALUES ('16', 'songshuyi.2008@163.com', '123', '123456');
