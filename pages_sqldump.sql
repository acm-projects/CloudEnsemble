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
-- Table structure for table `bands`
--

DROP TABLE IF EXISTS `bands`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bands` (
  `band_id` int NOT NULL AUTO_INCREMENT,
  `band_name` varchar(100) NOT NULL,
  `date_created` date NOT NULL,
  PRIMARY KEY (`band_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
  `band_id` int NOT NULL,
  `user_name` varchar(100) NOT NULL,
  KEY `band_id` (`band_id`),
  KEY `user_name` (`user_name`),
  CONSTRAINT `bands_members_ibfk_1` FOREIGN KEY (`band_id`) REFERENCES `bands` (`band_id`),
  CONSTRAINT `bands_members_ibfk_2` FOREIGN KEY (`user_name`) REFERENCES `credentials` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bands_members`
--

LOCK TABLES `bands_members` WRITE;
/*!40000 ALTER TABLE `bands_members` DISABLE KEYS */;
/*!40000 ALTER TABLE `bands_members` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clips`
--

DROP TABLE IF EXISTS `clips`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clips` (
  `clip_key` varchar(500) NOT NULL,
  `clip_uploader` varchar(100) NOT NULL,
  `clip_name` varchar(100) NOT NULL,
  `date_uploaded` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`clip_key`),
  KEY `clip_uploader` (`clip_uploader`),
  CONSTRAINT `clips_ibfk_1` FOREIGN KEY (`clip_uploader`) REFERENCES `credentials` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clips`
--

LOCK TABLES `clips` WRITE;
/*!40000 ALTER TABLE `clips` DISABLE KEYS */;
INSERT INTO `clips` VALUES ('6b2e25d2-0067-11eb-84b6-a5ff373173f6','username_test','sound_testing.wav','2020-09-27 02:16:15'),('9356c3de-0386-11eb-89f7-ebfd53bd0672','username_test','Windows_snd.mp3','2020-10-01 01:36:50'),('9fce6edd-046b-11eb-900a-59103fe2c6be','username_test','Come As You Are.mp3','2020-10-02 04:56:31'),('c0295398-012a-11eb-9672-85303f65d6cd','username_test','sound_test.wav','2020-09-28 01:34:29'),('f8132f6d-0391-11eb-8260-eb04af04ff1d','username_test','Windows_sound.mp3','2020-10-01 02:58:24');
/*!40000 ALTER TABLE `clips` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clips_tags`
--

DROP TABLE IF EXISTS `clips_tags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clips_tags` (
  `id` int NOT NULL AUTO_INCREMENT,
  `clip_key` varchar(500) NOT NULL,
  `tag_id` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `clip_key` (`clip_key`),
  KEY `tag_id` (`tag_id`),
  CONSTRAINT `clips_tags_ibfk_1` FOREIGN KEY (`clip_key`) REFERENCES `clips` (`clip_key`),
  CONSTRAINT `clips_tags_ibfk_2` FOREIGN KEY (`tag_id`) REFERENCES `tags` (`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clips_tags`
--

LOCK TABLES `clips_tags` WRITE;
/*!40000 ALTER TABLE `clips_tags` DISABLE KEYS */;
/*!40000 ALTER TABLE `clips_tags` ENABLE KEYS */;
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
  `pic_key` varchar(1000) DEFAULT NULL,
  `password` varchar(1000) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  `salt` varchar(100) NOT NULL,
  PRIMARY KEY (`user_name`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `credentials`
--

LOCK TABLES `credentials` WRITE;
/*!40000 ALTER TABLE `credentials` DISABLE KEYS */;
INSERT INTO `credentials` VALUES ('another_username','another_email','default_profile_pic.png','a1de8b63bc3b0905be12553963d5f9b51ef27eae81696916123a95997d4a9b58cc07e5d434846d88ba9705d8fd6cfb32848f3348fe0915551ab45b70cfaa73d',NULL,'lYKKohjPY48u63AQrp+vhpOodbc='),('username_test','email_test','default_profile_pic.png','3134fdf8b85b1f9e8571fb81ff7301d248b18a0711cb05dcfc5b9a5f44105755691f390d64c1b7ecbcfa6063bb93f93731b56d58af137d502906856e80399e68',NULL,'9Z0s/+n5YzhNR4r//swNowijdaE=');
/*!40000 ALTER TABLE `credentials` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `followers`
--

DROP TABLE IF EXISTS `followers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `followers` (
  `id` int NOT NULL AUTO_INCREMENT,
  `follower` varchar(100) NOT NULL,
  `following` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `follower` (`follower`),
  KEY `following` (`following`),
  CONSTRAINT `followers_ibfk_1` FOREIGN KEY (`follower`) REFERENCES `credentials` (`user_name`),
  CONSTRAINT `followers_ibfk_2` FOREIGN KEY (`following`) REFERENCES `credentials` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `followers`
--

LOCK TABLES `followers` WRITE;
/*!40000 ALTER TABLE `followers` DISABLE KEYS */;
INSERT INTO `followers` VALUES (1,'username_test','another_username');
/*!40000 ALTER TABLE `followers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tag_types`
--

DROP TABLE IF EXISTS `tag_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tag_types` (
  `type_id` int NOT NULL AUTO_INCREMENT,
  `type_name` varchar(50) NOT NULL,
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tag_types`
--

LOCK TABLES `tag_types` WRITE;
/*!40000 ALTER TABLE `tag_types` DISABLE KEYS */;
INSERT INTO `tag_types` VALUES (1,'Genre'),(2,'Artist'),(3,'Instrument'),(4,'Other');
/*!40000 ALTER TABLE `tag_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tags`
--

DROP TABLE IF EXISTS `tags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tags` (
  `tag_id` varchar(50) NOT NULL,
  `tag_type` int NOT NULL,
  PRIMARY KEY (`tag_id`),
  UNIQUE KEY `tag_id` (`tag_id`),
  KEY `tag_type` (`tag_type`),
  CONSTRAINT `tags_ibfk_1` FOREIGN KEY (`tag_type`) REFERENCES `tag_types` (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tags`
--

LOCK TABLES `tags` WRITE;
/*!40000 ALTER TABLE `tags` DISABLE KEYS */;
/*!40000 ALTER TABLE `tags` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-10-07 20:24:07
