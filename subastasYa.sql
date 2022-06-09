CREATE DATABASE  IF NOT EXISTS `subastasya` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `subastasya`;
-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: subastasya
-- ------------------------------------------------------
-- Server version	8.0.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `anuncio`
--

DROP TABLE IF EXISTS `anuncio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `anuncio` (
  `id_anuncio` int NOT NULL,
  `descripcion` varchar(45) NOT NULL,
  `fecha_inicio` date NOT NULL,
  `fecha_fin` date NOT NULL,
  `usuario_id` int NOT NULL,
  `ciudad` int NOT NULL,
  `departamento` int NOT NULL,
  `estado` int NOT NULL,
  `valor` double DEFAULT NULL,
  `producto_id` int NOT NULL,
  PRIMARY KEY (`id_anuncio`),
  KEY `usuario_idUser_idx` (`usuario_id`),
  KEY `estadoid_idx` (`estado`),
  KEY `productoid_idx` (`producto_id`),
  KEY `ciudadid_idx` (`ciudad`),
  KEY `departamentoid_idx` (`departamento`),
  CONSTRAINT `ciudadid` FOREIGN KEY (`ciudad`) REFERENCES `ciudad` (`id_ciudad`),
  CONSTRAINT `departamentoid` FOREIGN KEY (`departamento`) REFERENCES `departamento` (`id_departamento`),
  CONSTRAINT `estadoid` FOREIGN KEY (`estado`) REFERENCES `estado` (`id_estado`),
  CONSTRAINT `productoid` FOREIGN KEY (`producto_id`) REFERENCES `producto` (`id`),
  CONSTRAINT `usuarioid` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `anuncio`
--

LOCK TABLES `anuncio` WRITE;
/*!40000 ALTER TABLE `anuncio` DISABLE KEYS */;
INSERT INTO `anuncio` VALUES (1,'carro ultimo modelo','2001-10-22','2006-10-22',2,1,14,2,20000000,1),(2,'gran celular samsung zflip','2002-10-22','2007-10-22',3,2,2,2,3800000,2);
/*!40000 ALTER TABLE `anuncio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ciudad`
--

DROP TABLE IF EXISTS `ciudad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ciudad` (
  `id_ciudad` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  PRIMARY KEY (`id_ciudad`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ciudad`
--

LOCK TABLES `ciudad` WRITE;
/*!40000 ALTER TABLE `ciudad` DISABLE KEYS */;
INSERT INTO `ciudad` VALUES (1,'BOGOTA'),(2,'MEDELLIN'),(3,'CALI'),(4,'BARRANQUILLA'),(5,'CARTAGENA'),(6,'SOLEDAD'),(7,'CUCUTA'),(8,'IBAGUE'),(9,'SOACHA'),(10,'VILLAVICENCIO'),(11,'BUCARAMANGA'),(12,'SANTA_MARTA'),(13,'VALLEDUPAR'),(14,'BELLO'),(15,'PEREIRA'),(16,'MONTERIA'),(17,'PASTO'),(18,'BUENAVENTURA'),(19,'MANIZALES'),(20,'NEIVA'),(21,'PALMIRA'),(22,'RIOHACHA'),(23,'SINCELEJO'),(24,'POPAYAN'),(25,'ITAGÜI'),(26,'FLORIDABLANCA'),(27,'ENVIGADO'),(28,'TULUA'),(29,'SAN_ANDRES_DE_TUMACO'),(30,'DOSQUEBRADAS'),(31,'APARTADO'),(32,'TUNJA'),(33,'GIRON'),(34,'URIBIA'),(35,'BARRANCABERMEJA'),(36,'FLORENCIA'),(37,'TURBO'),(38,'MAICAO'),(39,'PIEDECUESTA'),(40,'YOPAL');
/*!40000 ALTER TABLE `ciudad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `departamento`
--

DROP TABLE IF EXISTS `departamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `departamento` (
  `id_departamento` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  PRIMARY KEY (`id_departamento`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `departamento`
--

LOCK TABLES `departamento` WRITE;
/*!40000 ALTER TABLE `departamento` DISABLE KEYS */;
INSERT INTO `departamento` VALUES (1,'AMAZONAS'),(2,'ANTIOQUÍA'),(3,'ARAUCA'),(4,'ATLÁNTICO'),(5,'BOLÍVAR'),(6,'BOYACÁ'),(7,'CALDAS'),(8,'CAQUETÁ'),(9,'CASANARE'),(10,'CAUCA'),(11,'CESAR'),(12,'CHOCÓ'),(13,'CÓRDOBA'),(14,'CUNDINAMARCA'),(15,'GUAINÍA'),(16,'GUAVIARE'),(17,'HUILA'),(18,'LA_GUAJIRA'),(19,'MAGDALENA'),(20,'META'),(21,'NARIÑO'),(22,'NORTE_DE_SANTANDER'),(23,'PUTUMAYO'),(24,'QUINDÍO'),(25,'RISARALDA'),(26,'SAN_ANDRÉS_Y_PROVIDENCIA'),(27,'SANTANDER'),(28,'SUCRE'),(29,'TOLIMA'),(30,'VALLE_DEL_CAUCA'),(31,'VAUPÉS'),(32,'VICHADA');
/*!40000 ALTER TABLE `departamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estado`
--

DROP TABLE IF EXISTS `estado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estado` (
  `id_estado` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  PRIMARY KEY (`id_estado`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estado`
--

LOCK TABLES `estado` WRITE;
/*!40000 ALTER TABLE `estado` DISABLE KEYS */;
INSERT INTO `estado` VALUES (1,'BLOQUEADO'),(2,'ACTIVO'),(3,'INACTIVO');
/*!40000 ALTER TABLE `estado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `producto` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` VALUES (1,'carro'),(2,'celular'),(3,'tennis'),(4,'ipad');
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rol` (
  `id` int NOT NULL AUTO_INCREMENT,
  `rol_nombre` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_USER');
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_producto`
--

DROP TABLE IF EXISTS `tipo_producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipo_producto` (
  `idtipoProducto` int NOT NULL,
  `nombreTipo` varchar(255) NOT NULL,
  `descripcion` varchar(255) NOT NULL,
  PRIMARY KEY (`idtipoProducto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_producto`
--

LOCK TABLES `tipo_producto` WRITE;
/*!40000 ALTER TABLE `tipo_producto` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipo_producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `nombre_usuario` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `token_password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'admin@correo.com','admin','admin','$2a$10$ncL694rv15ZlNalh2anNhuBDc2oknCZnfaoJGSplFSxb/HiHrc4om',NULL),(2,'sjaramillo1027@cue.edu.co','Santiago','sjaramillo','$2a$10$ClphykSqH.rw6uT7KjMeWesqMc6z6HMEILMoRDb/fT1PXhEkfa.86',NULL),(3,'amorales18@mail.com','Arle Morales','amorales','$2a$10$lqWU4jCFINykny6VS5yqnedEMltRpnFbvmLqVByZ.HsEiZDYrOS3W',NULL),(4,'fjaramillo@gmail.co','Felipe','fjaramillo','$2a$10$dyWW5keMQ6iO5m9YvR2aTO6O97wX8bhX0R1Jds4K2x0GXw.QzM476',NULL),(5,'sarhachica@gmail.com','Sarha Chica','sarha chica','$2a$10$Zpbv2OWLgOM7i4GFmKExDOPAEcguKeK1sWhg6jslVCtnx63wgpUrG',NULL),(6,'arleth64@hotmail.com','Arle','arleth','$2a$10$XPe4EgSfI2pa3EWFAi69dO1Pc9W.GJehTYDIE.FTJkQ.aza3PpudK',NULL),(7,'mlopez705@cue.edu.co','mafe','mafe','$2a$10$g4p5kJjVpZU1gbeihCmQPOKx9T5Xmpnk1I6RSBXnWDCS4ISNVknP2',NULL);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario_rol`
--

DROP TABLE IF EXISTS `usuario_rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario_rol` (
  `usuario_id` int NOT NULL,
  `rol_id` int NOT NULL,
  PRIMARY KEY (`usuario_id`,`rol_id`),
  KEY `FK610kvhkwcqk2pxeewur4l7bd1` (`rol_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_rol`
--

LOCK TABLES `usuario_rol` WRITE;
/*!40000 ALTER TABLE `usuario_rol` DISABLE KEYS */;
INSERT INTO `usuario_rol` VALUES (1,1),(1,2),(2,2),(3,1),(3,2),(4,2),(5,2),(6,2),(7,2);
/*!40000 ALTER TABLE `usuario_rol` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-09 11:25:10
