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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `b_answer`
--

LOCK TABLES `b_answer` WRITE;
/*!40000 ALTER TABLE `b_answer` DISABLE KEYS */;
INSERT INTO `b_answer` VALUES (3,'1721446301578694656','[{\"key\": \"easy1710732057574\", \"type\": \"SortText\", \"label\": \"该组件为短输入框\", \"value\": \"12默认值\", \"require\": true}, {\"key\": \"easy1710732071818\", \"type\": \"LongText\", \"label\": \"该组件为长输入框\", \"value\": \"默认值长输入框12\", \"require\": true}, {\"key\": \"easy1710732077372\", \"type\": \"eRadio\", \"label\": \"单选框\", \"value\": \"value-1\", \"require\": true}, {\"key\": \"easy1710732095635\", \"type\": \"eCheckBox\", \"label\": \"多选框\", \"value\": [\"value-1\", \"value-2\", \"value-3\"], \"require\": true}, {\"key\": \"easy1710732142252\", \"type\": \"eSelector\", \"label\": \"选择器\", \"value\": \"value-2\", \"require\": true}, {\"key\": \"easy1710732648449\", \"type\": \"eStar\", \"label\": \"评价组件\", \"value\": 5, \"require\": true}, {\"key\": \"easy1710732896845\", \"type\": \"eSlider\", \"label\": \"滑块\", \"value\": \"64\", \"require\": true}, {\"key\": \"easy1710732882985\", \"type\": \"eDate\", \"label\": \"日期\", \"value\": \"2018-10-17\", \"require\": true}]','2024-06-19 09:10:56','其他',NULL,NULL),(4,'1721446301578694656','[{\"key\": \"easy1710732057574\", \"type\": \"SortText\", \"label\": \"该组件为短输入框\", \"value\": \"默认值\", \"require\": true}, {\"key\": \"easy1710732071818\", \"type\": \"LongText\", \"label\": \"该组件为长输入框\", \"value\": \"默认值长输入框\", \"require\": true}, {\"key\": \"easy1710732077372\", \"type\": \"eRadio\", \"label\": \"单选框\", \"value\": \"value-2\", \"require\": true}, {\"key\": \"easy1710732095635\", \"type\": \"eCheckBox\", \"label\": \"多选框\", \"value\": [\"value-1\", \"value-2\"], \"require\": true}, {\"key\": \"easy1710732142252\", \"type\": \"eSelector\", \"label\": \"选择器\", \"value\": \"value-1\", \"require\": true}, {\"key\": \"easy1710732648449\", \"type\": \"eStar\", \"label\": \"评价组件\", \"value\": 4, \"require\": true}, {\"key\": \"easy1710732896845\", \"type\": \"eSlider\", \"label\": \"滑块\", \"value\": \"61\", \"require\": true}, {\"key\": \"easy1710732882985\", \"type\": \"eDate\", \"label\": \"日期\", \"value\": \"2018-10-10\", \"require\": true}]','2024-06-19 09:19:00','其他',NULL,NULL),(5,'1721446301578694656','[{\"key\": \"easy1710732057574\", \"type\": \"SortText\", \"label\": \"该组件为短输入框\", \"value\": \"默认值\", \"require\": true}, {\"key\": \"easy1710732071818\", \"type\": \"LongText\", \"label\": \"该组件为长输入框\", \"value\": \"默认值长输入框\", \"require\": true}, {\"key\": \"easy1710732077372\", \"type\": \"eRadio\", \"label\": \"单选框\", \"value\": \"value-1\", \"require\": true}, {\"key\": \"easy1710732095635\", \"type\": \"eCheckBox\", \"label\": \"多选框\", \"value\": [\"value-1\", \"value-2\"], \"require\": true}, {\"key\": \"easy1710732142252\", \"type\": \"eSelector\", \"label\": \"选择器\", \"value\": \"value-1\", \"require\": true}, {\"key\": \"easy1710732648449\", \"type\": \"eStar\", \"label\": \"评价组件\", \"value\": 4, \"require\": true}, {\"key\": \"easy1710732896845\", \"type\": \"eSlider\", \"label\": \"滑块\", \"value\": \"61\", \"require\": true}, {\"key\": \"easy1710732882985\", \"type\": \"eDate\", \"label\": \"日期\", \"value\": \"2018-10-10\", \"require\": true}]','2024-06-19 09:19:40','其他',NULL,NULL),(6,'1721446301578694656','[{\"key\": \"easy1710732057574\", \"type\": \"SortText\", \"label\": \"该组件为短输入框\", \"value\": \"默认值\", \"require\": true}, {\"key\": \"easy1710732071818\", \"type\": \"LongText\", \"label\": \"该组件为长输入框\", \"value\": \"默认值长输入框\", \"require\": true}, {\"key\": \"easy1710732077372\", \"type\": \"eRadio\", \"label\": \"单选框\", \"value\": \"value-1\", \"require\": true}, {\"key\": \"easy1710732095635\", \"type\": \"eCheckBox\", \"label\": \"多选框\", \"value\": [\"value-1\", \"value-2\"], \"require\": true}, {\"key\": \"easy1710732142252\", \"type\": \"eSelector\", \"label\": \"选择器\", \"value\": \"value-1\", \"require\": true}, {\"key\": \"easy1710732648449\", \"type\": \"eStar\", \"label\": \"评价组件\", \"value\": 4, \"require\": true}, {\"key\": \"easy1710732896845\", \"type\": \"eSlider\", \"label\": \"滑块\", \"value\": \"61\", \"require\": true}, {\"key\": \"easy1710732882985\", \"type\": \"eDate\", \"label\": \"日期\", \"value\": \"2018-10-10\", \"require\": true}]','2024-06-19 09:27:07','其他',NULL,NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `b_form`
--

LOCK TABLES `b_form` WRITE;
/*!40000 ALTER TABLE `b_form` DISABLE KEYS */;
INSERT INTO `b_form` VALUES (1,'随意问答','1721446301578694656',1,NULL,'2023-11-06 16:35:55','2023-11-06 16:35:55',NULL,NULL,'[{\"component\": \"Heading\", \"attributes\": {\"key\": \"easy1712892087961\", \"title\": \"主标题\", \"subtitle\": \"副标题\"}}, {\"component\": \"eDescription\", \"attributes\": {\"key\": \"easy1710731877211\", \"label\": \"Description\", \"require\": false, \"defaultValue\": \"组件测试：编号001\"}}, {\"component\": \"SortText\", \"attributes\": {\"key\": \"easy1710732057574\", \"label\": \"该组件为短输入框\", \"require\": true, \"defaultValue\": \"默认值\"}}, {\"component\": \"LongText\", \"attributes\": {\"key\": \"easy1710732071818\", \"label\": \"该组件为长输入框\", \"require\": true, \"defaultValue\": \"默认值长输入框\"}}, {\"component\": \"eRadio\", \"attributes\": {\"key\": \"easy1710732077372\", \"max\": 1, \"min\": 1, \"label\": \"单选框\", \"require\": true, \"defaultValue\": \"\", \"radioOptions\": [{\"radioLabel\": \"选项label1\", \"radioValue\": \"value-1\"}, {\"radioLabel\": \"选项label2\", \"radioValue\": \"value-2\"}, {\"radioLabel\": \"选项label3\", \"radioValue\": \"value-3\"}]}}, {\"component\": \"eSelector\", \"attributes\": {\"key\": \"easy1710732142252\", \"max\": 1, \"min\": 1, \"label\": \"选择器\", \"require\": true, \"defaultValue\": \"value-1\", \"radioOptions\": [{\"radioLabel\": \"选项label1\", \"radioValue\": \"value-1\"}, {\"radioLabel\": \"选项label2\", \"radioValue\": \"value-2\"}, {\"radioLabel\": \"选项label3\", \"radioValue\": \"value-3\"}]}}, {\"component\": \"eDivider\", \"attributes\": {\"key\": \"easy1710732157396\", \"label\": \"divider2023\", \"require\": false, \"defaultValue\": \"#a7ce74\"}}, {\"component\": \"eStar\", \"attributes\": {\"key\": \"easy1710732648449\", \"max\": 5, \"label\": \"评价组件\", \"require\": true, \"defaultValue\": 4}}, {\"component\": \"ePicture\", \"attributes\": {\"key\": \"easy1710732878081\", \"label\": \"网络图片\", \"require\": false, \"defaultValue\": \"https://cdn.pixabay.com/photo/2013/03/06/01/01/water-90781_1280.jpg\"}}, {\"component\": \"eSlider\", \"attributes\": {\"key\": \"easy1710732896845\", \"max\": 100, \"min\": 0, \"step\": 1, \"label\": \"滑块\", \"require\": true, \"defaultValue\": \"61\"}}, {\"component\": \"eDate\", \"attributes\": {\"key\": \"easy1710732882985\", \"label\": \"日期\", \"require\": true, \"defaultValue\": \"2018-10-10\"}}, {\"component\": \"eCheckBox\", \"attributes\": {\"key\": \"easy1718765654541\", \"max\": 2, \"min\": 1, \"label\": \"多选框\", \"require\": false, \"defaultValue\": [], \"radioOptions\": [{\"radioLabel\": \"选项label1\", \"radioValue\": \"value-1\"}, {\"radioLabel\": \"选项label2\", \"radioValue\": \"value-2\"}, {\"radioLabel\": \"选项label3\", \"radioValue\": \"value-3\"}]}}]',0,NULL,NULL);
/*!40000 ALTER TABLE `b_form` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sa_user`
--

DROP TABLE IF EXISTS `sa_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sa_user` (
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sa_user`
--

LOCK TABLES `sa_user` WRITE;
/*!40000 ALTER TABLE `sa_user` DISABLE KEYS */;
INSERT INTO `sa_user` VALUES (1,'pooler','E3D6AvknCIokPVKmZ7iepA==',NULL,NULL,NULL,NULL,'2023-11-06 16:33:22','2023-11-06 16:33:22',NULL,NULL);
/*!40000 ALTER TABLE `sa_user` ENABLE KEYS */;
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

-- Dump completed on 2024-06-19 16:38:25
