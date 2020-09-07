CREATE DATABASE  IF NOT EXISTS `elearn` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
USE `elearn`;
-- MySQL dump 10.13  Distrib 8.0.14, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: elearn
-- ------------------------------------------------------
-- Server version	8.0.13

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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `admin` (
  `email` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES ('viraj@gmail.com','admin');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `course` (
  `courseid` int(11) NOT NULL AUTO_INCREMENT,
  `coursename` varchar(45) NOT NULL,
  `description` varchar(45) NOT NULL,
  `image` varchar(45) NOT NULL,
  PRIMARY KEY (`courseid`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (26,'Python','Learn Python the hard way','/courses/Python/image/Python.jpg'),(32,'JavaScript','Learn Javascript            ','/courses/javaScript/image/javaScript.jpg'),(34,'Java','Learn Java            ','/courses/java/image/java.jpg'),(36,'C++','Learn C++            ','/courses/C++/image/C++.jpg');
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `module`
--

DROP TABLE IF EXISTS `module`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `module` (
  `moduleid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `courseid` int(11) NOT NULL,
  `modulerank` int(11) NOT NULL,
  PRIMARY KEY (`moduleid`),
  KEY `mcourseid_idx` (`courseid`),
  CONSTRAINT `mcourseid` FOREIGN KEY (`courseid`) REFERENCES `course` (`courseid`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `module`
--

LOCK TABLES `module` WRITE;
/*!40000 ALTER TABLE `module` DISABLE KEYS */;
INSERT INTO `module` VALUES (19,'Advanced',26,2),(20,'Introduction',32,1),(21,'Basics',34,1),(23,'Basics',26,1);
/*!40000 ALTER TABLE `module` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `timestamp`
--

DROP TABLE IF EXISTS `timestamp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `timestamp` (
  `videoid` int(11) NOT NULL,
  `userid` varchar(45) NOT NULL,
  KEY `tvideoid_idx` (`videoid`),
  KEY `tuserid_idx` (`userid`),
  CONSTRAINT `tuserid` FOREIGN KEY (`userid`) REFERENCES `user` (`email`),
  CONSTRAINT `tvideoid` FOREIGN KEY (`videoid`) REFERENCES `video` (`videoid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `timestamp`
--

LOCK TABLES `timestamp` WRITE;
/*!40000 ALTER TABLE `timestamp` DISABLE KEYS */;
/*!40000 ALTER TABLE `timestamp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `email` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `firstname` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  `occupation` varchar(45) NOT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('','','','',''),('anorak@gmail.com','qwe','Chintan','Rajput','Student'),('bruce@gmail.com','asd','ert','sdf','student'),('bruce47@gmail.com','qweasd','zxc','dg','student'),('chintan@gmail','qwe','Chintan ','Rajput','Student'),('chintanrajput22@outlook.com','qwe','Chintan','Rajput','Student'),('hinsulak@gmail.com','lak','lak','hinsu','student'),('Hinsulak2@gmail.com','lak','Lak','Hinsu','student'),('ironman@gmail.com','asd','qwe','rty','student'),('jamesbond@gmail.com','asd','james','bond','student'),('lakhinsu37@gmail.com','lakhinsu','Lak','Hinsu','Student'),('LightYagami@gmail','Misa','Light','Yagami','Student'),('rutvikjshah@gmail.com','rutvik12','rutvik','shah','student'),('shethkrushit101@gmail.com','hello','Krushit','Sheth','student'),('super@gmail.com','asd','asd','qwe','student'),('superman','asd','asd','qwe','student'),('superman@gmail.com','qwe','123','xcv','student'),('viraj@gmail','qwe','asd','dfg','qwerr'),('virajchavda31447@gmail.com','asd','viraj','chavda','student');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usertocourse`
--

DROP TABLE IF EXISTS `usertocourse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `usertocourse` (
  `email` varchar(45) NOT NULL,
  `courseid` int(11) NOT NULL,
  KEY `email_idx` (`email`),
  KEY `courseid_idx` (`courseid`),
  CONSTRAINT `courseid` FOREIGN KEY (`courseid`) REFERENCES `course` (`courseid`),
  CONSTRAINT `email` FOREIGN KEY (`email`) REFERENCES `user` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usertocourse`
--

LOCK TABLES `usertocourse` WRITE;
/*!40000 ALTER TABLE `usertocourse` DISABLE KEYS */;
INSERT INTO `usertocourse` VALUES ('viraj@gmail',26),('viraj@gmail',32),('lakhinsu37@gmail.com',26),('anorak@gmail.com',32),('anorak@gmail.com',26),('LightYagami@gmail',26),('LightYagami@gmail',32),('LightYagami@gmail',34),('anorak@gmail.com',34),('viraj@gmail',34),('shethkrushit101@gmail.com',26),('shethkrushit101@gmail.com',32),('shethkrushit101@gmail.com',34),('jamesbond@gmail.com',32),('jamesbond@gmail.com',26),('rutvikjshah@gmail.com',26),('rutvikjshah@gmail.com',32);
/*!40000 ALTER TABLE `usertocourse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `video`
--

DROP TABLE IF EXISTS `video`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `video` (
  `videoid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(95) NOT NULL,
  `moduleid` int(11) NOT NULL,
  `path` varchar(95) NOT NULL,
  `duration` varchar(95) NOT NULL,
  `courseid` int(11) NOT NULL,
  `videorank` int(11) NOT NULL,
  PRIMARY KEY (`videoid`),
  KEY `moduleid_idx` (`moduleid`),
  KEY `vcourseid_idx` (`courseid`),
  CONSTRAINT `moduleid` FOREIGN KEY (`moduleid`) REFERENCES `module` (`moduleid`),
  CONSTRAINT `vcourseid` FOREIGN KEY (`courseid`) REFERENCES `course` (`courseid`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `video`
--

LOCK TABLES `video` WRITE;
/*!40000 ALTER TABLE `video` DISABLE KEYS */;
INSERT INTO `video` VALUES (22,'What is Python',23,'courses/Python/video/Basics/What is Python.mp4','4:07',26,1),(23,'Class',19,'courses/Python/video/Advanced/Class.mp4','15:24',26,1),(24,'Lists',19,'courses/Python/video/Advanced/Lists.mp4','10:41',26,2);
/*!40000 ALTER TABLE `video` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'elearn'
--
/*!50003 DROP PROCEDURE IF EXISTS `login` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `login`(in p_email varchar(45),in p_password varchar(45),out p_result varchar(45))
BEGIN
	select email into p_result from user where email=p_email and password=p_password;
    IF email=p_result
    THEN
		set p_result=true;
	END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `register` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `register`(in p_email varchar(45),in p_password varchar(45),in p_firstname varchar(45),in p_lastname varchar(45),in p_occupation varchar(45))
BEGIN
	insert into user values(p_email,p_password,p_firstname,p_lastname,p_occupation);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-26 11:36:14
