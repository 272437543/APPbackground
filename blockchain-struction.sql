/*
Navicat MySQL Data Transfer

Source Server         : drake
Source Server Version : 50022
Source Host           : localhost:3306
Source Database       : blockchain

Target Server Type    : MYSQL
Target Server Version : 50022
File Encoding         : 65001

Date: 2019-01-28 16:09:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL auto_increment,
  `username` varchar(255) character set utf8 NOT NULL,
  `time` varchar(255) character set utf8 default NULL,
  `place` varchar(255) character set utf8 collate utf8_unicode_ci default '',
  `words` varchar(255) character set utf8 collate utf8_unicode_ci default '',
  `image` varchar(255) character set utf8 collate utf8_unicode_ci default '',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for lottery_list
-- ----------------------------
DROP TABLE IF EXISTS `lottery_list`;
CREATE TABLE `lottery_list` (
  `id` int(11) NOT NULL auto_increment,
  `number` int(11) NOT NULL,
  `currency` varchar(255) character set utf8 NOT NULL,
  `total` int(11) default NULL,
  `remain` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for main_slide
-- ----------------------------
DROP TABLE IF EXISTS `main_slide`;
CREATE TABLE `main_slide` (
  `id` int(11) NOT NULL auto_increment,
  `title` varchar(255) character set utf8 default '',
  `image` varchar(255) character set utf8 default '',
  `href` varchar(255) character set utf8 default '',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for person_lottery
-- ----------------------------
DROP TABLE IF EXISTS `person_lottery`;
CREATE TABLE `person_lottery` (
  `id` int(11) NOT NULL auto_increment,
  `username` varchar(255) character set utf8 NOT NULL,
  `lottery_id` int(11) NOT NULL,
  `amount` int(11) NOT NULL,
  `time` varchar(255) character set utf8 default NULL,
  PRIMARY KEY  (`id`,`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for reply
-- ----------------------------
DROP TABLE IF EXISTS `reply`;
CREATE TABLE `reply` (
  `id` int(11) NOT NULL auto_increment,
  `comment_id` int(11) NOT NULL,
  `username` varchar(255) character set utf8 NOT NULL,
  `content` varchar(255) character set utf8 default '',
  PRIMARY KEY  (`id`,`comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL auto_increment,
  `username` varchar(255) character set utf8 NOT NULL,
  `password` varchar(255) character set utf8 NOT NULL,
  `icon` varchar(255) character set utf8 default '',
  `nickname` varchar(255) character set utf8 default '',
  `permission` varchar(255) character set utf8 collate utf8_unicode_ci default '',
  PRIMARY KEY  (`id`),
  UNIQUE KEY `username_unique` USING HASH (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for win_history
-- ----------------------------
DROP TABLE IF EXISTS `win_history`;
CREATE TABLE `win_history` (
  `id` int(11) NOT NULL auto_increment,
  `username` varchar(255) character set utf8 default NULL,
  `number` int(11) default NULL,
  `currency` varchar(255) character set utf8 default NULL,
  `time` varchar(255) character set utf8 default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
