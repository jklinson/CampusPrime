-- MySQL dump 10.13  Distrib 5.7.9, for Win32 (AMD64)
--
-- Host: localhost    Database: campus_prime
-- ------------------------------------------------------
-- Server version	5.6.28-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `writeups`
--

DROP TABLE IF EXISTS `writeups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `writeups` (
  `writeUpId` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(10000) DEFAULT NULL,
  `description` varchar(10000) DEFAULT NULL,
  `type` varchar(100) DEFAULT NULL,
  `audienceId` int(11) DEFAULT NULL,
  `isAproved` int(11) DEFAULT NULL,
  `fileId` int(11) DEFAULT NULL,
  `publishedDate` varchar(100) DEFAULT NULL,
  `publishedBy` int(11) NOT NULL,
  PRIMARY KEY (`writeUpId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `writeups`
--

LOCK TABLES `writeups` WRITE;
/*!40000 ALTER TABLE `writeups` DISABLE KEYS */;
INSERT INTO `writeups` VALUES (1,'tres','sdfsdfd','Literatur',1,1,15,'1457965368464',1),(2,'test','asdsddsf df df','Literatur',1,1,16,'1457965690551',1),(3,'dsf','sdfsdf dfdvdf fs f','Literatur',1,1,17,'1457976363900',1),(4,'sdfsdf','dsfsdfsdfsdf','Literatur',1,1,18,'1457976550405',1),(5,'asdf','sdfdsfsdf','Literatur',1,1,19,'1457976666189',1),(6,'testad','asdad sd vadg sdg gsdsdg','Drawing',1,1,26,'1458116318619',1);
/*!40000 ALTER TABLE `writeups` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-03-30 20:27:41
