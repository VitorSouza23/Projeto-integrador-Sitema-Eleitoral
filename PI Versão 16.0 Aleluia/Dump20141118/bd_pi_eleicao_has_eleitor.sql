CREATE DATABASE  IF NOT EXISTS `bd_pi` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `bd_pi`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: bd_pi
-- ------------------------------------------------------
-- Server version	5.6.16

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
-- Table structure for table `eleicao_has_eleitor`
--

DROP TABLE IF EXISTS `eleicao_has_eleitor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `eleicao_has_eleitor` (
  `eleicao_ideleicao` int(11) NOT NULL,
  `eleitor_idEleitor` int(11) NOT NULL,
  `eleitor_votou` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`eleicao_ideleicao`,`eleitor_idEleitor`),
  KEY `fk_eleicao_has_eleitor_eleitor_idx` (`eleitor_idEleitor`),
  KEY `fk_eleicao_has_eleitor_eleicao_idx` (`eleicao_ideleicao`),
  CONSTRAINT `fk_eleicao_has_eleitor_eleicao` FOREIGN KEY (`eleicao_ideleicao`) REFERENCES `eleicao` (`ideleicao`),
  CONSTRAINT `fk_eleicao_has_eleitor_eleitor` FOREIGN KEY (`eleitor_idEleitor`) REFERENCES `eleitor` (`idEleitor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eleicao_has_eleitor`
--

LOCK TABLES `eleicao_has_eleitor` WRITE;
/*!40000 ALTER TABLE `eleicao_has_eleitor` DISABLE KEYS */;
/*!40000 ALTER TABLE `eleicao_has_eleitor` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-11-18 17:20:55
