-- MySQL dump 10.13  Distrib 8.0.11, for macos10.13 (x86_64)
--
-- Host: localhost    Database: easy_form
-- ------------------------------------------------------
-- Server version	8.0.11

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8mb4 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `b_answer`
--

DROP TABLE IF EXISTS `b_answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `b_answer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `form_key` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '表单key',
  `answer_details` json DEFAULT NULL COMMENT '答案详情',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `source` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '来源',
  `commit_ip` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '提交IP',
  `complete_time` bigint(20) DEFAULT NULL COMMENT '完成时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `b_answer`
--

LOCK TABLES `b_answer` WRITE;
/*!40000 ALTER TABLE `b_answer` DISABLE KEYS */;
/*!40000 ALTER TABLE `b_answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `b_form`
--

DROP TABLE IF EXISTS `b_form`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `b_form` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `form_name` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '表单名称',
  `form_key` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '表单key',
  `user_id` bigint(20) DEFAULT NULL COMMENT '创建者id',
  `form_status` tinyint(4) DEFAULT NULL COMMENT '表单状态',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `evaluate_logic` varchar(1000) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '反馈逻辑',
  `evaluate_ui` json DEFAULT NULL COMMENT '反馈ui',
  `form_items` json DEFAULT NULL COMMENT '表单项',
  `form_type` tinyint(4) DEFAULT NULL COMMENT '表单类型',
  `description` text COLLATE utf8mb4_bin COMMENT '描述',
  `write_count` bigint(20) DEFAULT NULL COMMENT '填写人数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `b_form`
--

LOCK TABLES `b_form` WRITE;
/*!40000 ALTER TABLE `b_form` DISABLE KEYS */;
/*!40000 ALTER TABLE `b_form` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `b_user`
--

DROP TABLE IF EXISTS `b_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `b_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '账号',
  `password` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '密码',
  `phone` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '电话号码',
  `email` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '邮箱',
  `avatar` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '头像',
  `is_admin` tinyint(4) DEFAULT NULL COMMENT '是否为管理员',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `signature` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '个性签名',
  `nickname` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '昵称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `b_user`
--

LOCK TABLES `b_user` WRITE;
/*!40000 ALTER TABLE `b_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `b_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'easy_form'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-06 16:23:30
