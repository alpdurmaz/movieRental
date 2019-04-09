CREATE DATABASE  IF NOT EXISTS `VIDEO_STORE` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
USE `VIDEO_STORE`;
-- MySQL dump 10.13  Distrib 8.0.14, for macos10.14 (x86_64)
--
-- Host: localhost    Database: VIDEO_STORE
-- ------------------------------------------------------
-- Server version	8.0.14

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (7),(7);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MOVIES`
--

DROP TABLE IF EXISTS `MOVIES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `MOVIES` (
  `M_ID` int(11) NOT NULL AUTO_INCREMENT,
  `M_TITLE` varchar(150) NOT NULL,
  `M_MAIN_ACTOR` varchar(100) NOT NULL,
  `M_YEAR` int(11) NOT NULL,
  `M_GENRE` varchar(150) NOT NULL,
  `M_PRICE` int(11) NOT NULL,
  PRIMARY KEY (`M_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MOVIES`
--

LOCK TABLES `MOVIES` WRITE;
/*!40000 ALTER TABLE `MOVIES` DISABLE KEYS */;
INSERT INTO `MOVIES` VALUES (12,'Rocky','Sylvester Stallone, Talia Shire, Burt Young, Carl Weathers',1976,'Drama, Sport',5),(13,'Interstellar','Ellen Burstyn, Matthew McConaughey, Mackenzie Foy, John Lithgow',2014,'Adventure, Drama, Sci-Fi',5),(14,'The Big Short','Ryan Gosling, Rudy Eisenzopf, Casey Groves, Charlie Talbert',2015,'Biography, Comedy, Drama, History',5);
/*!40000 ALTER TABLE `MOVIES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `RENTALS`
--

DROP TABLE IF EXISTS `RENTALS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `RENTALS` (
  `R_M_ID` int(11) DEFAULT NULL,
  `R_C_ID` int(11) DEFAULT NULL,
  `R_RENTAL_DATE` date DEFAULT NULL,
  `R_RETURN_DATE` date DEFAULT NULL,
  `RENTAL_ID` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`RENTAL_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RENTALS`
--

LOCK TABLES `RENTALS` WRITE;
/*!40000 ALTER TABLE `RENTALS` DISABLE KEYS */;
INSERT INTO `RENTALS` VALUES (1,101,'2019-03-02','2019-03-03',1),(2,102,'2019-03-01','2019-03-02',2),(3,103,'2019-02-27','2019-02-28',3),(4,104,'2019-02-24','2019-02-25',4),(5,105,'2019-03-04','2019-03-05',5),(2,106,'2019-02-01','2019-02-02',6),(1,101,'2019-01-01','2019-03-13',7),(2,101,'2019-02-01','2019-03-08',8),(3,101,'2019-02-01','2019-05-05',9),(1,101,'2019-03-05','2019-03-13',10),(1,101,'2019-03-05','2019-03-13',11),(1,101,'2019-03-05','2019-03-13',12),(5,102,'2019-03-07','2019-03-07',13),(5,102,'2019-03-07','2019-03-07',14),(5,102,'2019-03-07','2019-03-07',15),(5,102,'2019-03-07','2019-03-07',16),(5,102,'2019-03-07','2019-03-08',17),(5,102,'2019-03-08','2019-03-08',18),(5,102,'2019-03-08','2019-03-08',19),(1,101,'2019-03-08','2019-03-13',20),(2,101,'2019-03-08','2019-03-08',21),(2,101,'2019-03-08','2019-03-08',22),(2,101,'2019-03-08','2019-03-08',23),(1,555,'2050-01-01','1900-01-01',24),(1,101,'2019-03-13','2019-03-13',25),(1,101,'2019-03-13','2019-03-13',26),(5,101,'2019-03-13','2019-03-13',27),(2,101,'2019-03-13','2019-03-13',28),(3,101,'2019-03-13','2019-03-13',29),(5,101,'2019-03-14','1900-01-01',30),(1,101,'2019-03-14','1900-01-01',31),(2,101,'2019-03-14','1900-01-01',32),(3,101,'2019-03-14','1900-01-01',33),(1,101,'2019-03-14','1900-01-01',34),(3,102,'2019-03-14','1900-01-01',35),(13,101,'2019-03-19','1900-01-01',36),(12,101,'2019-03-19','2019-03-19',37),(12,101,'2019-03-19','2019-03-19',38),(14,101,'2019-03-19','1900-01-01',39),(12,101,'2019-03-19','2019-03-19',40),(12,101,'2019-03-19','2019-03-19',41),(13,101,'2019-03-19','1900-01-01',42),(14,102,'2019-03-19','1900-01-01',43),(12,101,'2019-03-20','1900-01-01',44),(14,101,'2019-03-20','1900-01-01',45),(12,101,'2019-03-21','1900-01-01',46),(14,101,'2019-03-22','1900-01-01',47),(12,101,'2019-03-22','1900-01-01',48),(12,101,'2019-03-22','1900-01-01',49),(1,101,'2019-03-28','1900-01-01',50),(12,1,'2019-03-28','2019-03-30',51),(14,1,'2019-03-28','2019-03-30',52),(12,1,'2019-03-29','2019-03-30',53),(12,1,'2019-03-30','2019-03-30',54),(14,1,'2019-03-30','2019-03-30',55),(13,1,'2019-03-30','2019-03-30',56),(12,1,'2019-03-30','2019-03-30',57),(12,1,'2019-03-30','2019-03-30',58),(12,1,'2019-03-30','2019-03-30',59),(12,1,'2019-03-30','2019-03-30',60),(13,1,'2019-03-30','2019-03-30',61),(12,1,'2019-03-30','2019-03-30',62),(12,1,'2019-03-30','2019-03-30',63),(12,1,'2019-03-30','2019-03-30',64),(13,1,'2019-03-30','2019-03-30',65),(12,1,'2019-03-30','2019-03-30',66),(13,1,'2019-03-30','2019-03-30',67),(14,1,'2019-03-30','2019-03-30',68),(14,1,'2019-03-30','2019-03-30',69),(12,1,'2019-03-30','2019-03-30',70),(13,1,'2019-03-30','2019-03-30',71),(12,1,'2019-03-30','2019-03-30',72),(13,1,'2019-03-30','2019-03-30',73),(14,1,'2019-03-30','2019-03-30',74),(12,1,'2019-03-30','2019-03-30',75),(12,1,'2019-03-30','2019-03-30',76),(12,1,'2019-03-30','2019-03-30',77),(13,1,'2019-03-30','2019-03-30',78),(14,1,'2019-03-30','2019-03-30',79),(14,1,'2019-03-30','2019-03-31',80),(14,1,'2019-03-30','2019-03-31',81),(14,1,'2019-03-30','2019-03-31',82),(14,1,'2019-03-30','2019-03-30',83),(12,1,'2019-03-30','2019-03-30',84),(13,1,'2019-03-30','2019-03-30',85),(13,1,'2019-03-30','2019-03-31',86),(14,1,'2019-03-31','2019-03-31',87),(12,1,'2019-03-31','2019-03-31',88),(13,1,'2019-03-31','1900-01-01',89),(12,3,'2019-04-04','1900-01-01',90);
/*!40000 ALTER TABLE `RENTALS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `role` (
  `role_id` int(11) NOT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ADMIN');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tokens`
--

DROP TABLE IF EXISTS `tokens`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tokens` (
  `t_user_id` int(11) DEFAULT NULL,
  `t_token` varchar(150) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tokens`
--

LOCK TABLES `tokens` WRITE;
/*!40000 ALTER TABLE `tokens` DISABLE KEYS */;
INSERT INTO `tokens` VALUES (1,'191403de-23e3-4be8-b04a-cb036e4de2a7'),(2,'3e1dc357-d7cb-4220-aacb-f084efb5308c');
/*!40000 ALTER TABLE `tokens` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL,
  `active` int(11) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,1,'alp@durmaz.com','durmaz','alp','$2a$10$g4pVR.9tmAqCY51QOfVnwei05o1cx76sPCU7mZKOe/7Md9is8N4em'),(2,1,'a@a.com','a','a','$2a$10$6ZZKthdwvxE2mtwigrPVOOzS6UNviajDyb1RwH369iexFCIs6g5TW'),(3,1,'soto@babi.com','aaa','aa','$2a$10$dbvIBEMq.sC9Id6XbS6CvevL3JnGtl9/ugpRliAW5dt5UAgxCgHiO'),(4,1,'alp@alp.com','durmaz','alp','$2a$10$6uqDFhQvaEB8UYD.gPfdiOn3JZvUeP201gicSxHX6lceaQXV19I/O'),(5,1,'soto@ayam.com','ayam','soto','$2a$10$iwnth9vkP10fgM1zb7AjFu0AsTojEjZeFLGBT.CdWQXC1/iAPvIde'),(6,1,'nasi@goreng.com','goreng','nasi','$2a$10$NXRgkWxjwOYWbxE3/zO6YOX3mZOjs2j0KtmTDj2eHB.ZbZE80faiS');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (3,1),(5,1),(6,1);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-04-04 10:41:21
