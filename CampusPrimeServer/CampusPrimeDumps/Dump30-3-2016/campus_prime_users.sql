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
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(45) DEFAULT NULL,
  `name` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `mobileNum` bigint(10) NOT NULL,
  `year` varchar(45) DEFAULT NULL,
  `department` varchar(45) DEFAULT NULL,
  `uniqueId` varchar(45) NOT NULL,
  `classOrSRoom` varchar(45) DEFAULT NULL,
  `isActive` int(11) DEFAULT '0',
  `isTeacher` int(11) DEFAULT '0',
  `isEmailVerified` int(11) DEFAULT '0',
  PRIMARY KEY (`userId`),
  UNIQUE KEY `mobileNum_UNIQUE` (`mobileNum`),
  UNIQUE KEY `userName_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'test@gmail.com','test','test1234',9895888778,'4','CS','324234','CS_A',0,0,1),(2,'tester123@gmail.com','myName','qwerty',9896888778,'4','CS','3434','CS_B',0,0,0),(4,'tes@gmail.com','myName1','qwerty',989688878,'4','CS','334','CS_B',0,0,0),(6,'linson@gmail.com','linson','',9896988778,'2','CS','3245234','CS_A',0,0,0),(7,'tessi@gmail.com','tessi','',9495888778,'3','CS','334234','CS_A',0,0,0),(9,'tessaa@gmail.com','tessaa','',9899588778,'4','CS','324934','CS_A',0,0,0),(10,'sdf@adr.com','asvsab','fcyhghyt',656564455,'4','fd','767','df',0,0,0),(11,'linsontest@gmail.com','Linson','test1234',989533339,'4','CS','45454','CS B',0,0,0),(19,'linsonalfred@gmail.com','tedt','test1234',9633772591,'2','vf','23','dsfsdf',0,0,1),(24,'jklinson@gmail.com','asdasd','test1234',8129192939,'s3','cs','28','asdas',0,0,0);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-03-30 20:27:40
