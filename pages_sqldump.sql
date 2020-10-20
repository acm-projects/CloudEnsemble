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
  `band_name` varchar(100) NOT NULL,
  `date_created` varchar(100) NOT NULL,
  PRIMARY KEY (`band_name`),
  FULLTEXT KEY `band_name` (`band_name`)
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
  FULLTEXT KEY `clip_name` (`clip_name`,`clip_uploader`),
  FULLTEXT KEY `clip_name_2` (`clip_name`),
  FULLTEXT KEY `clip_uploader` (`clip_uploader`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clips`
--

LOCK TABLES `clips` WRITE;
/*!40000 ALTER TABLE `clips` DISABLE KEYS */;
INSERT INTO `clips` VALUES ('1','Elizabeth','windows start','2020/10/18','1'),('10','MusicQueen1324','blues beat','2020/10/18','4'),('11','MusicQueen1324','Let it Go solo','2020/10/18','3'),('2','Meinhard','alarm','2020/10/18','2'),('3','Joseph','circle of life','2020/10/18','2'),('4','Elizabeth','Guitar jam','2020/10/18','2'),('5','Meinhard','piano cords in d minor','2020/10/18','3'),('6','Joseph','alarms and music','2020/10/18','2'),('7','Elizabeth','The Minstrel Boy','2020/10/18','1'),('8','Meinhard','Johnny Boy','2020/10/18','2'),('9','Joseph','Chaminade','2020/10/18','2');
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
  UNIQUE KEY `email_UNIQUE` (`email`),
  FULLTEXT KEY `user_name` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `credentials`
--

LOCK TABLES `credentials` WRITE;
/*!40000 ALTER TABLE `credentials` DISABLE KEYS */;
INSERT INTO `credentials` VALUES ('Elizabeth','elizabeth@gmail.com','1','clements','1'),('Francisca','francisca@gmail.com','1','li','1'),('Jocelyn','jocelyn@gmail.com','1','heckenkamp','1'),('Joseph','joseph@gmail.com','1','prichard','1'),('Meinhard','meinhard@gmail.com','1','capucao','1'),('MusicQueen1324','queen@gmail.com','1','queen','1'),('username','email','default_profile_pic.png','c27957325185ad3d05e85e044ca41d826d08a9e59a941c4de88a19925b29a47792f53e3dd5b82365c200104c7fecea0c5cc54fbc58c6020f345b94d3fe73ba6f','wQWX0D3VH2hWKdRSxGYaJa/9XxY=');
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
INSERT INTO `samples` VALUES ('fb0100cc-0907-11eb-9432-75bc2b73bb7c26','test_track','434c8890-08f9-11eb-bc80-ef49b8f384af25',51,3);
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
  `tag_type` int NOT NULL,
  FULLTEXT KEY `tag_id` (`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tags`
--

LOCK TABLES `tags` WRITE;
/*!40000 ALTER TABLE `tags` DISABLE KEYS */;
INSERT INTO `tags` VALUES ('1','bells',0),('1','windows',0),('2','guitar',0),('2','rock',0),('3','animals',0),('3','life',0),('3','tim rice',0),('4','rock',0),('4','guitar',0),('5','piano',0),('5','bells',0),('6','animals',0),('6','rock',0),('6','classical',0),('7','irish',0),('7','flute',0),('7','violin',0),('8','irish',0),('8','violin',0),('9','classical',0),('9','flute',0),('9','chaminade',0),('101','brass',0),('101','trumpet',0),('101','patriotic',0),('101','Jamie Salisbury',0),('101','American',0),('102','jazz',0),('102','saxophone',0),('102','Joe Garland',0),('102','Wingy Manone',0),('103','star wars',0),('103','bells',0),('103','synthetic',0),('104','drumline',0),('104','drumline',0),('104','cadence',0),('105','piano',0),('105','pop',0),('105','voice',0),('105','Queen',0),('106','bagpipes',0),('106','ABBA',0),('107','classical',0),('107','strings',0),('108','folk',0),('108','patriotic',0),('108','American',0),('108','whistles',0),('108','voice',0),('108','guitar',0),('109','American',0),('109','patriotic',0),('109','voice',0),('109','Lee Greenwood',0),('110','jazz',0),('110','guitar',0),('110','bass',0),('111','irish',0),('111','bagpipes',0),('111','flute',0),('111','tin whistle',0),('111','folk',0),('112','strings',0),('112','classical',0),('113','jazz',0),('113','saxophone',0),('113','trumpet',0),('113','Miles Davis',0),('113','bass',0),('113','drumset',0),('114','jazz',0),('114','Duke Ellington',0),('115','jazz',0),('114','George Gershwin',0),('116','guitar',0),('117','folk',0),('117','irish',0),('117','voice',0),('10','jazz',0),('10','drumset',0),('10','blues',0),('11','voice',0),('11','frozen',0),('118','Queen',0),('118','rock',0),('118','rock',0),('118','voice',0),('119','Queen',0),('119','rock',0),('119','rock',0),('119','voice',0);
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
  FULLTEXT KEY `track_uploader` (`track_uploader`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tracks`
--

LOCK TABLES `tracks` WRITE;
/*!40000 ALTER TABLE `tracks` DISABLE KEYS */;
INSERT INTO `tracks` VALUES ('101','Elizabeth','Star Spangled Banner brass arrangement','10/18/20','1'),('102','Francisca','In the Mood','10/18/20','1'),('103','Meinhard','Star Wars Theme Remix','10/18/20','1'),('104','Jocelyn','Drumline Cadence','10/18/20','1'),('105','Joseph','Bohemian Rhapsody','10/18/20','1'),('106','Elizabeth','Dancing Queen on bagpipes','10/18/20','1'),('107','Francisca','Mozart String Quartet No 19','10/18/20','1'),('108','Jocelyn','Yankee Doodle','10/18/20','1'),('109','Meinhard','God Bless the USA','10/18/20','1'),('110','Joseph','Miscellaneous jazz jam','10/18/20','1'),('111','Jocelyn','The Minstrel Boy','10/18/20','1'),('112','Francisca','Shostakovich string quartet','10/18/20','1'),('113','Meinhard','So What','10/18/20','1'),('114','Jocelyn','In a Sentimental Mood','10/18/20','1'),('115','Elizabeth','Rhapsody in Blue','10/18/20','1'),('116','Meinhard','Duet for two guitars','10/18/20','1'),('117','Francisca','The Irish Rover','10/18/20','1'),('118','MusicQueen1324','Another One Bites the Dust','10/18/20','1'),('119','MusicQueen1324','We Will Rock You','10/18/20','1'),('eeac43cb-0907-11eb-9432-cbc288c34e2d25','username','test_track','2020/10/07 20:45:23','0');
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

-- Dump completed on 2020-10-20 13:35:24
