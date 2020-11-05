-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: pages_db
-- ------------------------------------------------------
-- Server version	8.0.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `access_objects`
--

DROP TABLE IF EXISTS `access_objects`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `access_objects` (
  `object_key` varchar(1000) NOT NULL,
  `access_type` int NOT NULL,
  `accessor_id` varchar(100) NOT NULL,
  `accessor_type` int NOT NULL,
  PRIMARY KEY (`object_key`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `access_objects`
--

LOCK TABLES `access_objects` WRITE;
/*!40000 ALTER TABLE `access_objects` DISABLE KEYS */;
/*!40000 ALTER TABLE `access_objects` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bands`
--

DROP TABLE IF EXISTS `bands`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bands` (
  `band_id` varchar(1000) NOT NULL,
  `band_name` varchar(100) NOT NULL,
  `date_created` varchar(100) NOT NULL,
  `band_founder` varchar(100) NOT NULL,
  PRIMARY KEY (`band_id`),
  FULLTEXT KEY `band_name` (`band_id`),
  FULLTEXT KEY `band_name_2` (`band_id`),
  FULLTEXT KEY `band_name_3` (`band_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bands`
--

LOCK TABLES `bands` WRITE;
/*!40000 ALTER TABLE `bands` DISABLE KEYS */;
/*!40000 ALTER TABLE `bands` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bands_members`
--

DROP TABLE IF EXISTS `bands_members`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bands_members` (
  `band_id` varchar(1000) NOT NULL,
  `band_member` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bands_members`
--

LOCK TABLES `bands_members` WRITE;
/*!40000 ALTER TABLE `bands_members` DISABLE KEYS */;
/*!40000 ALTER TABLE `bands_members` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clip_boards`
--

DROP TABLE IF EXISTS `clip_boards`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clip_boards` (
  `element_key` varchar(100) NOT NULL,
  `track_key` varchar(100) NOT NULL,
  `clip_key` varchar(100) NOT NULL,
  `ele_order` int NOT NULL,
  PRIMARY KEY (`element_key`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clip_boards`
--

LOCK TABLES `clip_boards` WRITE;
/*!40000 ALTER TABLE `clip_boards` DISABLE KEYS */;
/*!40000 ALTER TABLE `clip_boards` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clips`
--

DROP TABLE IF EXISTS `clips`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clips` (
  `clip_key` varchar(1000) NOT NULL,
  `clip_uploader` varchar(100) NOT NULL,
  `clip_name` varchar(100) NOT NULL,
  `date_uploaded` varchar(100) NOT NULL,
  `access` varchar(45) NOT NULL,
  PRIMARY KEY (`clip_key`),
  FULLTEXT KEY `clip_name` (`clip_name`),
  FULLTEXT KEY `clip_uploader` (`clip_uploader`),
  FULLTEXT KEY `clip_name_2` (`clip_name`),
  FULLTEXT KEY `clip_uploader_2` (`clip_uploader`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clips`
--

LOCK TABLES `clips` WRITE;
/*!40000 ALTER TABLE `clips` DISABLE KEYS */;
INSERT INTO `clips` VALUES ('2faf1e5e-15bd-11eb-a250-71d7d79a1bd230','joseph','windows','2020/10/24 00:53:06','1'),('3aed1edf-1c72-11eb-8680-4d80395a329e31','prichard','windows_test','2020/11/01 12:44:11','1'),('6c464283-15c0-11eb-8e1d-f7d7a0fe53da28','joseph','<><>','2020/10/24 01:16:16','1'),('d8219b92-15bf-11eb-8e1d-9770083f07b523','joseph','\"\"\"\"\"\"\"\"\"\"','2020/10/24 01:12:08','1');
/*!40000 ALTER TABLE `clips` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `credentials`
--

DROP TABLE IF EXISTS `credentials`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `credentials` (
  `user_name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(1000) NOT NULL,
  `salt` varchar(100) NOT NULL,
  PRIMARY KEY (`user_name`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  FULLTEXT KEY `user_name` (`user_name`),
  FULLTEXT KEY `user_name_2` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `credentials`
--

LOCK TABLES `credentials` WRITE;
/*!40000 ALTER TABLE `credentials` DISABLE KEYS */;
INSERT INTO `credentials` VALUES ('joseph','jeh190000@utdallas.edu','45dc5350ea0e5ea605c30de73797fe896f2f762eb681903afba2306011ad7fd0b515c8a7e8968337315a851812a6a8e1f31be8b69ee47547b7ff2d22633f087b','rzToX7ewrOjK8CMBtWWJ73Wq/Po='),('prichard','Ltprichard2014@gmail.com','650f39107fc2b4284715ae369f7ad78e56c25cdca2856f2d0acd4f7db9b8f35456a0cd25dd76df6537ddbec3004f4123b3c86daf86f930b33783d6e552fde1a4','O7Rf5zk6AAgTTi5VBz/j2gsML3Y=');
/*!40000 ALTER TABLE `credentials` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profiles`
--

DROP TABLE IF EXISTS `profiles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `profiles` (
  `user_name` varchar(100) NOT NULL,
  `pic_key` varchar(1000) NOT NULL,
  `description` varchar(1000) NOT NULL,
  PRIMARY KEY (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profiles`
--

LOCK TABLES `profiles` WRITE;
/*!40000 ALTER TABLE `profiles` DISABLE KEYS */;
INSERT INTO `profiles` VALUES ('joseph','d6ec9356-11c5-11eb-b4ca-f3e18995f1f023','Hello, I\'m new to Cloud Ensemble!'),('prichard','default_profile_pic.png','Hello, I\'m new to Cloud Ensemble!');
/*!40000 ALTER TABLE `profiles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `samples`
--

DROP TABLE IF EXISTS `samples`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `samples` (
  `sample_key` varchar(1000) NOT NULL,
  `track_key` varchar(1000) NOT NULL,
  `clip_key` varchar(1000) NOT NULL,
  `time` int NOT NULL,
  `rack` int NOT NULL,
  PRIMARY KEY (`sample_key`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `samples`
--

LOCK TABLES `samples` WRITE;
/*!40000 ALTER TABLE `samples` DISABLE KEYS */;
INSERT INTO `samples` VALUES ('3ddce11f-15bd-11eb-a250-c5cb024fd6cc31','5668ef6d-15ba-11eb-a250-435e36ae024923','2faf1e5e-15bd-11eb-a250-71d7d79a1bd230',20,4);
/*!40000 ALTER TABLE `samples` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stars`
--

DROP TABLE IF EXISTS `stars`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stars` (
  `user_name` varchar(100) NOT NULL,
  `object_starred` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stars`
--

LOCK TABLES `stars` WRITE;
/*!40000 ALTER TABLE `stars` DISABLE KEYS */;
/*!40000 ALTER TABLE `stars` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tags`
--

DROP TABLE IF EXISTS `tags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tags` (
  `object_key` varchar(1000) NOT NULL,
  `tag_id` varchar(50) NOT NULL,
  FULLTEXT KEY `tag_id` (`tag_id`),
  FULLTEXT KEY `tag_id_2` (`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tags`
--

LOCK TABLES `tags` WRITE;
/*!40000 ALTER TABLE `tags` DISABLE KEYS */;
INSERT INTO `tags` VALUES ('2faf1e5e-15bd-11eb-a250-71d7d79a1bd230','Rock'),('caad7622-1637-11eb-8d0d-d7166a54d1ec29','Rock'),('d8219b92-15bf-11eb-8e1d-9770083f07b523','Pop');
/*!40000 ALTER TABLE `tags` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tracks`
--

DROP TABLE IF EXISTS `tracks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tracks` (
  `track_key` varchar(1000) NOT NULL,
  `track_uploader` varchar(100) NOT NULL,
  `track_name` varchar(100) NOT NULL,
  `date_uploaded` varchar(100) NOT NULL,
  `access` varchar(45) NOT NULL,
  PRIMARY KEY (`track_key`),
  FULLTEXT KEY `track_name` (`track_name`),
  FULLTEXT KEY `track_uploader` (`track_uploader`),
  FULLTEXT KEY `track_name_2` (`track_name`),
  FULLTEXT KEY `track_uploader_2` (`track_uploader`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tracks`
--

LOCK TABLES `tracks` WRITE;
/*!40000 ALTER TABLE `tracks` DISABLE KEYS */;
INSERT INTO `tracks` VALUES ('5668ef6d-15ba-11eb-a250-435e36ae024923','joseph','track','2020/10/24 00:32:42','1'),('a563a5e1-15bf-11eb-8e1d-a5d8fa287cd930','joseph','\"\"\"\"','2020/10/24 01:10:42','1'),('caad7622-1637-11eb-8d0d-d7166a54d1ec29','joseph','<script>     alert(\"HACKED\") </script> ','2020/10/24 15:30:44','1');
/*!40000 ALTER TABLE `tracks` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-11-01 13:28:47
