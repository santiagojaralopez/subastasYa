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
  `id_anuncio` int NOT NULL AUTO_INCREMENT,
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
  CONSTRAINT `ciudadid` FOREIGN KEY (`ciudad`) REFERENCES `ciudad` (`id_ciudad`),
  CONSTRAINT `estadoid` FOREIGN KEY (`estado`) REFERENCES `estado` (`id_estado`),
  CONSTRAINT `productoid` FOREIGN KEY (`producto_id`) REFERENCES `producto` (`id`),
  CONSTRAINT `usuarioid` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `anuncio`
--

LOCK TABLES `anuncio` WRITE;
/*!40000 ALTER TABLE `anuncio` DISABLE KEYS */;
INSERT INTO `anuncio` VALUES (1,'carro ultimo modelo','2001-10-22','2006-10-22',2,1,14,2,20000000,1),(2,'gran celular samsung zflip','2002-10-22','2007-10-22',3,2,2,2,3800000,2),(4,'ropaaaaaa','2022-06-15','2006-10-22',2,1,2,2,20000000,1),(5,'weqwe','2022-06-15','2022-06-15',9,30,0,2,12,10),(6,'3445235','2022-06-15','2022-06-15',9,30,0,2,122222,41);
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
  `departamentoid` int DEFAULT NULL,
  PRIMARY KEY (`id_ciudad`),
  KEY `departamentoid_idx` (`departamentoid`),
  CONSTRAINT `departamentoid` FOREIGN KEY (`departamentoid`) REFERENCES `departamento` (`id_departamento`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ciudad`
--

LOCK TABLES `ciudad` WRITE;
/*!40000 ALTER TABLE `ciudad` DISABLE KEYS */;
INSERT INTO `ciudad` VALUES (1,'BOGOTA',NULL),(2,'MEDELLIN',NULL),(3,'CALI',NULL),(4,'BARRANQUILLA',NULL),(5,'CARTAGENA',NULL),(6,'SOLEDAD',NULL),(7,'CUCUTA',NULL),(8,'IBAGUE',NULL),(9,'SOACHA',NULL),(10,'VILLAVICENCIO',NULL),(11,'BUCARAMANGA',NULL),(12,'SANTA_MARTA',NULL),(13,'VALLEDUPAR',NULL),(14,'BELLO',NULL),(15,'PEREIRA',NULL),(16,'MONTERIA',NULL),(17,'PASTO',NULL),(18,'BUENAVENTURA',NULL),(19,'MANIZALES',NULL),(20,'NEIVA',NULL),(21,'PALMIRA',NULL),(22,'RIOHACHA',NULL),(23,'SINCELEJO',NULL),(24,'POPAYAN',NULL),(25,'ITAGÜI',NULL),(26,'FLORIDABLANCA',NULL),(27,'ENVIGADO',NULL),(28,'TULUA',NULL),(29,'SAN_ANDRES_DE_TUMACO',NULL),(30,'DOSQUEBRADAS',NULL),(31,'APARTADO',NULL),(32,'TUNJA',NULL),(33,'GIRON',NULL),(34,'URIBIA',NULL),(35,'BARRANCABERMEJA',NULL),(36,'FLORENCIA',NULL),(37,'TURBO',NULL),(38,'MAICAO',NULL),(39,'PIEDECUESTA',NULL),(40,'YOPAL',NULL);
/*!40000 ALTER TABLE `ciudad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `configuracion`
--

DROP TABLE IF EXISTS `configuracion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `configuracion` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `valor` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `configuracion`
--

LOCK TABLES `configuracion` WRITE;
/*!40000 ALTER TABLE `configuracion` DISABLE KEYS */;
/*!40000 ALTER TABLE `configuracion` ENABLE KEYS */;
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
  `id` int NOT NULL,
  PRIMARY KEY (`id_departamento`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `departamento`
--

LOCK TABLES `departamento` WRITE;
/*!40000 ALTER TABLE `departamento` DISABLE KEYS */;
INSERT INTO `departamento` VALUES (1,'AMAZONAS',0),(2,'ANTIOQUÍA',0),(3,'ARAUCA',0),(4,'ATLÁNTICO',0),(5,'BOLÍVAR',0),(6,'BOYACÁ',0),(7,'CALDAS',0),(8,'CAQUETÁ',0),(9,'CASANARE',0),(10,'CAUCA',0),(11,'CESAR',0),(12,'CHOCÓ',0),(13,'CÓRDOBA',0),(14,'CUNDINAMARCA',0),(15,'GUAINÍA',0),(16,'GUAVIARE',0),(17,'HUILA',0),(18,'LA_GUAJIRA',0),(19,'MAGDALENA',0),(20,'META',0),(21,'NARIÑO',0),(22,'NORTE_DE_SANTANDER',0),(23,'PUTUMAYO',0),(24,'QUINDÍO',0),(25,'RISARALDA',0),(26,'SAN_ANDRÉS_Y_PROVIDENCIA',0),(27,'SANTANDER',0),(28,'SUCRE',0),(29,'TOLIMA',0),(30,'VALLE_DEL_CAUCA',0),(31,'VAUPÉS',0),(32,'VICHADA',0);
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
  `foto_producto` varchar(255) DEFAULT NULL,
  `tipoproducto` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `tipoproducto` (`tipoproducto`),
  CONSTRAINT `producto_ibfk_1` FOREIGN KEY (`tipoproducto`) REFERENCES `tipo_producto` (`idtipoProducto`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` VALUES (1,'carro',NULL,NULL),(2,'celular',NULL,NULL),(3,'tennis',NULL,NULL),(4,'ipad',NULL,NULL),(6,'zapato','fewfwef',NULL),(10,'qweqw','hola',NULL),(29,'zzz','hola',NULL),(30,'hhh','hola',NULL),(31,'zxzxzxzx','hola',NULL),(32,'zxzxzxzxh','hola',NULL),(33,'bvvbvbv','hola',NULL),(34,'jujuju','hola',NULL),(35,'jujujuttytyty','hola',NULL),(36,'lololo','hola',NULL),(37,'popopopop','hola',NULL),(38,'uyuyuy','hola',NULL),(39,'tytytyty','hola',NULL),(40,'234234234','hola',NULL),(41,'test 400','hola',NULL);
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
  `id` int NOT NULL,
  `nombre_tipo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idtipoProducto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_producto`
--

LOCK TABLES `tipo_producto` WRITE;
/*!40000 ALTER TABLE `tipo_producto` DISABLE KEYS */;
INSERT INTO `tipo_producto` VALUES (1,'Tecnologia','Productos tecnologicos',0,NULL),(2,'Bines Raices','Propiedades e inmuebles',0,NULL);
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
  `apellido` varchar(255) NOT NULL,
  `direccion` varchar(255) NOT NULL,
  `estado_usuario` int NOT NULL,
  `numerodoc` varchar(255) NOT NULL,
  `tipo_documento` varchar(255) NOT NULL,
  `fechanacto` datetime NOT NULL,
  `departamento` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `estado_usuario` (`estado_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'admin@correo.com','admin','admin','$2a$10$ncL694rv15ZlNalh2anNhuBDc2oknCZnfaoJGSplFSxb/HiHrc4om',NULL,'','',0,'','','2003-10-10 10:10:10',''),(2,'sjaramillo1027@cue.edu.co','Santiago','sjaramillo','$2a$10$ClphykSqH.rw6uT7KjMeWesqMc6z6HMEILMoRDb/fT1PXhEkfa.86',NULL,'Jaramillo','Calarca',0,'1193309974','Cedula de Ciudadania','2003-10-10 10:10:10','Quindio'),(3,'amorales@gmail.com','Arle','amorales','$2a$10$lqWU4jCFINykny6VS5yqnedEMltRpnFbvmLqVByZ.HsEiZDYrOS3W',NULL,'Morales','',0,'356874524','Cedula de Ciudadania','2003-10-10 10:10:10','Quindio'),(4,'fjaramillo@gmail.com','Felipe','fjaramillo','$2a$10$dyWW5keMQ6iO5m9YvR2aTO6O97wX8bhX0R1Jds4K2x0GXw.QzM476',NULL,'Jaramillo','',0,'951321650','Cedula de Ciudadania','2003-10-10 10:10:10','Huila'),(5,'sarhachica@gmail.com','Sarha','schica','$2a$10$Zpbv2OWLgOM7i4GFmKExDOPAEcguKeK1sWhg6jslVCtnx63wgpUrG',NULL,'Chica','',0,'1010236587','Cedula de Ciudadania','2003-10-10 10:10:10','Quindio'),(6,'arleth64@hotmail.com','Arle','arleth','$2a$10$XPe4EgSfI2pa3EWFAi69dO1Pc9W.GJehTYDIE.FTJkQ.aza3PpudK',NULL,'','',0,'','','2003-10-10 10:10:10',''),(7,'mlopez705@cue.edu.co','mafe','mafe','$2a$10$g4p5kJjVpZU1gbeihCmQPOKx9T5Xmpnk1I6RSBXnWDCS4ISNVknP2',NULL,'','',0,'','','2003-10-10 10:10:10',''),(8,'sgallego1022@cue.edu.co','Santiago','santigg','$2a$10$TrCe8TDiyiz3A4q91rQsbOHlS8.Wa449ii3fv/reYqoVzCRJTaiTe',NULL,'Gallego Gil','cra 17 calle 10N',0,'1004961630','Cedula de Ciudadania','2003-02-10 00:00:00',''),(9,'betty@gmail.com','Beatriz','bettylafea','$2a$10$RYcyHcTI6UBUoTy6K0VvEun4..WythwgHptFUDiRQUyQDq4nTnD32',NULL,'Pinzon Solano','cra 123',0,'1028492','Pasaporte','2003-02-10 00:00:00',''),(10,'josalazar@cue.edu.co','Jhon Oscar','josalazar','$2a$10$njxwQeGgn9Q3dW.V7qE1qO/nmSAK3w.mP1YCvRWzTySMduAIbGooS',NULL,'Salazar Orozco','Carrera 50',2,'1094','Cedula de Ciudadania','1989-06-13 00:00:00','Valle del cauca');
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
INSERT INTO `usuario_rol` VALUES (1,1),(1,2),(2,2),(3,1),(3,2),(4,2),(5,2),(6,2),(7,2),(8,2),(9,2),(10,2);
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

-- Dump completed on 2022-06-19 20:35:44
