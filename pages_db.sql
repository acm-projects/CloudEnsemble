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
INSERT INTO `access_objects` VALUES ('eeac43cb-0907-11eb-9432-cbc288c34e2d25',1,'joseph',0);
/*!40000 ALTER TABLE `access_objects` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bands`
--

DROP TABLE IF EXISTS `bands`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bands` (
  `band_name` varchar(100) NOT NULL,
  `date_created` varchar(100) NOT NULL,
  PRIMARY KEY (`band_name`)
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
  `band_name` varchar(100) NOT NULL,
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
INSERT INTO `clip_boards` VALUES ('b53eede7-0f5c-11eb-89fc-49a05fab562d26','eeac43cb-0907-11eb-9432-cbc288c34e2d25','434c8890-08f9-11eb-bc80-ef49b8f384af25',7),('b6601558-0f5c-11eb-89fc-cdd42e5bee9027','eeac43cb-0907-11eb-9432-cbc288c34e2d25','434c8890-08f9-11eb-bc80-ef49b8f384af25',3),('b71a3d99-0f5c-11eb-89fc-51b721abde0b28','eeac43cb-0907-11eb-9432-cbc288c34e2d25','434c8890-08f9-11eb-bc80-ef49b8f384af25',8);
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
  PRIMARY KEY (`clip_key`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clips`
--

LOCK TABLES `clips` WRITE;
/*!40000 ALTER TABLE `clips` DISABLE KEYS */;
INSERT INTO `clips` VALUES ('5e289a01-08f9-11eb-bc80-3b151f7de11027','[Anonymous]','Come As You Are.mp3','2020/10/07 19:01:16'),('b5689053-0b50-11eb-b7a5-7790c78566b027','username','windows','2020/10/10 18:31:25'),('d792b6f6-08f8-11eb-8960-b97a0371e07f28','username','Windows_snd.mp3','2020/10/07 18:57:23');
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
  `pic_key` varchar(1000) NOT NULL,
  `password` varchar(1000) NOT NULL,
  `salt` varchar(100) NOT NULL,
  PRIMARY KEY (`user_name`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `credentials`
--

LOCK TABLES `credentials` WRITE;
/*!40000 ALTER TABLE `credentials` DISABLE KEYS */;
INSERT INTO `credentials` VALUES ('account','jeh190000@utdallas.edu','default_profile_pic.png','16490c944dd10373b4e01c6fc6089b16538ba4d0953f110623d05063fb93c734b20974217e4d181021fe756a6a474e1998dba25a035383190f69d13a26cb6982','ghpui3f7rx7hP6osMOzWoR2a7Ug='),('hello','hello@gmail.com','default_profile_pic.png','e7ede323efec279babb92a38ab0b2b1572c61e2eace3f2cf4573c210a752e07c9cfd0d8e2f28f7588d1be04fe82dcb6e8965fb83f658b0bb1cddeae17ad15567','jFhZwoxgM5Cs+KPrZyVfQXyFflw='),('hi','hi@gmail.com','default_profile_pic.png','f9512020f4984d8fec9ffb12a6d7e82e20add3774191f89cc888362d6a870d1438600a46c661e2f4c8c14a7213b4491a8e1d7d815607f8f8032a2a329114eb70','D4VL3FVx6TBUSHqcrSp8gniHQVk='),('joseph','email1','default_profile_pic.png','8453515575bf44645daba8c21a5f2af0f43ce762172329840bfbf815dbab64406c2b0fdae064d419075932b44452595519ba08ec1c0071fe894f64d0460de584','1JSPNuaC7q4KintSTn6soW9lckk='),('test','test@gmail.com','default_profile_pic.png','da9d4828a9deb7927ee62e293aa989c20a80e52cbdc798da3d7efc03270de059206d6285c4a12cacfaafc538c5afa5602b84f0a5f8636d4971606658e5d79cd3','glMazhpxIKIVT1Ir87c5ok20Wdw='),('username','email','default_profile_pic.png','c27957325185ad3d05e85e044ca41d826d08a9e59a941c4de88a19925b29a47792f53e3dd5b82365c200104c7fecea0c5cc54fbc58c6020f345b94d3fe73ba6f','wQWX0D3VH2hWKdRSxGYaJa/9XxY=');
/*!40000 ALTER TABLE `credentials` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `followers`
--

DROP TABLE IF EXISTS `followers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `followers` (
  `follower` varchar(100) NOT NULL,
  `following` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `followers`
--

LOCK TABLES `followers` WRITE;
/*!40000 ALTER TABLE `followers` DISABLE KEYS */;
/*!40000 ALTER TABLE `followers` ENABLE KEYS */;
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
INSERT INTO `samples` VALUES ('39d2266c-0b68-11eb-9408-2930f48690f322','eeac43cb-0907-11eb-9432-cbc288c34e2d25','d792b6f6-08f8-11eb-8960-b97a0371e07f28',2,1),('f6e7e38b-0b60-11eb-9282-5ff01c8c762327','eeac43cb-0907-11eb-9432-cbc288c34e2d25','eeac43cb-0907-11eb-9432-cbc288c34e2d25',1,2),('fb0100cc-0907-11eb-9432-75bc2b73bb7c26','eeac43cb-0907-11eb-9432-cbc288c34e2d25','434c8890-08f9-11eb-bc80-ef49b8f384af25',51,3);
/*!40000 ALTER TABLE `samples` ENABLE KEYS */;
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
  `tag_type` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tags`
--

LOCK TABLES `tags` WRITE;
/*!40000 ALTER TABLE `tags` DISABLE KEYS */;
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
  PRIMARY KEY (`track_key`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tracks`
--

LOCK TABLES `tracks` WRITE;
/*!40000 ALTER TABLE `tracks` DISABLE KEYS */;
INSERT INTO `tracks` VALUES ('eeac43cb-0907-11eb-9432-cbc288c34e2d25','username','test_track','2020/10/07 20:45:23','0');
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

-- Dump completed on 2020-10-18 12:56:43
