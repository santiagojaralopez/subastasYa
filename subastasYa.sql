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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `anuncio`
--

LOCK TABLES `anuncio` WRITE;
/*!40000 ALTER TABLE `anuncio` DISABLE KEYS */;
INSERT INTO `anuncio` VALUES (12,'sdfsfdsdf','2022-06-23','2022-06-28',2,2,2,23333,61);
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
  `departamentoid` int NOT NULL DEFAULT '1',
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
INSERT INTO `ciudad` VALUES (1,'BOGOTA',1),(2,'MEDELLIN',1),(3,'CALI',1),(4,'BARRANQUILLA',1),(5,'CARTAGENA',1),(6,'SOLEDAD',1),(7,'CUCUTA',1),(8,'IBAGUE',1),(9,'SOACHA',1),(10,'VILLAVICENCIO',1),(11,'BUCARAMANGA',1),(12,'SANTA_MARTA',1),(13,'VALLEDUPAR',1),(14,'BELLO',1),(15,'PEREIRA',1),(16,'MONTERIA',1),(17,'PASTO',1),(18,'BUENAVENTURA',1),(19,'MANIZALES',1),(20,'NEIVA',1),(21,'PALMIRA',1),(22,'RIOHACHA',1),(23,'SINCELEJO',1),(24,'POPAYAN',1),(25,'ITAGÜI',1),(26,'FLORIDABLANCA',1),(27,'ENVIGADO',1),(28,'TULUA',1),(29,'SAN_ANDRES_DE_TUMACO',1),(30,'DOSQUEBRADAS',1),(31,'APARTADO',1),(32,'TUNJA',1),(33,'GIRON',1),(34,'URIBIA',1),(35,'BARRANCABERMEJA',1),(36,'FLORENCIA',1),(37,'TURBO',1),(38,'MAICAO',1),(39,'PIEDECUESTA',1),(40,'YOPAL',1);
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
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `configuracion`
--

LOCK TABLES `configuracion` WRITE;
/*!40000 ALTER TABLE `configuracion` DISABLE KEYS */;
INSERT INTO `configuracion` VALUES (1,'edad_registro',18),(2,'duracion_dias_anuncio',5),(3,'maximo_ofertas',3),(4,'maximo_anuncios',3);
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
-- Table structure for table `offer`
--

DROP TABLE IF EXISTS `offer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `offer` (
  `id_oferta` int NOT NULL AUTO_INCREMENT,
  `offer_value` double NOT NULL,
  `id_anuncio` int NOT NULL,
  `usuario_id` int NOT NULL,
  PRIMARY KEY (`id_oferta`),
  KEY `FKeelh8una8vbchuyw61mulehm8` (`id_anuncio`),
  KEY `FKjj95yvm5li060hquq2x9h4o1m` (`usuario_id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `offer`
--

LOCK TABLES `offer` WRITE;
/*!40000 ALTER TABLE `offer` DISABLE KEYS */;
INSERT INTO `offer` VALUES (6,500000,12,2),(7,871505,12,2),(4,95000,12,2);
/*!40000 ALTER TABLE `offer` ENABLE KEYS */;
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
  KEY `tipoproducto_idx` (`tipoproducto`),
  CONSTRAINT `tipoproducto` FOREIGN KEY (`tipoproducto`) REFERENCES `tipo_producto` (`idtipo_producto`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` VALUES (60,'erwerew','foto',2),(61,'dfddf','foto',2);
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
  `idtipo_producto` int NOT NULL AUTO_INCREMENT,
  `nombre_tipo` varchar(255) NOT NULL,
  `descripcion` varchar(255) NOT NULL,
  PRIMARY KEY (`idtipo_producto`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_producto`
--

LOCK TABLES `tipo_producto` WRITE;
/*!40000 ALTER TABLE `tipo_producto` DISABLE KEYS */;
INSERT INTO `tipo_producto` VALUES (1,'yytytytyty','tytytytytyt'),(2,'Bines Raices','Propiedades e inmuebles'),(3,'Prueba','Prueba'),(4,'22222','222222'),(5,'ewfwefwe','ewfwefwef'),(6,'qeqwwqqw','qweqweqwqqqqqq'),(7,'12312312','3123123123'),(11,'00000`','0000'),(12,'kjhg','kjhvjhgf');
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'admin@correo.com','Andres Mauricio','admin','$2a$10$ncL694rv15ZlNalh2anNhuBDc2oknCZnfaoJGSplFSxb/HiHrc4om',NULL,'Rodriguez','La Patria',0,'987635418','Cedula de Ciudadania','2003-10-10 10:10:10','Quindio'),(2,'sjaramillo1027@cue.edu.co','Santiago','sjaramillo','$2a$10$ClphykSqH.rw6uT7KjMeWesqMc6z6HMEILMoRDb/fT1PXhEkfa.86',NULL,'','',0,'','','2003-10-10 10:10:10',''),(3,'amorales18@mail.com','Arle Morales','amorales','$2a$10$lqWU4jCFINykny6VS5yqnedEMltRpnFbvmLqVByZ.HsEiZDYrOS3W',NULL,'','',0,'','','2003-10-10 10:10:10',''),(4,'fjaramillo@gmail.co','Felipe','fjaramillo','$2a$10$dyWW5keMQ6iO5m9YvR2aTO6O97wX8bhX0R1Jds4K2x0GXw.QzM476',NULL,'','',0,'','','2003-10-10 10:10:10',''),(5,'sarhachica@gmail.com','Sarha Chica','sarha chica','$2a$10$Zpbv2OWLgOM7i4GFmKExDOPAEcguKeK1sWhg6jslVCtnx63wgpUrG',NULL,'','',0,'','','2003-10-10 10:10:10',''),(6,'arleth64@hotmail.com','Arle','arleth','$2a$10$XPe4EgSfI2pa3EWFAi69dO1Pc9W.GJehTYDIE.FTJkQ.aza3PpudK',NULL,'','',0,'','','2003-10-10 10:10:10',''),(7,'mlopez705@cue.edu.co','mafe','mafe','$2a$10$g4p5kJjVpZU1gbeihCmQPOKx9T5Xmpnk1I6RSBXnWDCS4ISNVknP2',NULL,'','',0,'','','2003-10-10 10:10:10',''),(8,'sgallego1022@cue.edu.co','Santiago','santigg','$2a$10$TrCe8TDiyiz3A4q91rQsbOHlS8.Wa449ii3fv/reYqoVzCRJTaiTe',NULL,'Gallego Gil','cra 17 calle 10N',0,'1004961630','Cedula de Ciudadania','2003-02-10 00:00:00',''),(9,'betty@gmail.com','Beatriz34','bettylafeaaaaaxxxxxxx','$2a$10$RYcyHcTI6UBUoTy6K0VvEun4..WythwgHptFUDiRQUyQDq4nTnD32',NULL,'Pinzon Solano','cra 123',0,'1028492','Pasaporte','2003-02-10 00:00:00','Arauca');
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
INSERT INTO `usuario_rol` VALUES (1,1),(1,2),(2,2),(3,1),(3,2),(4,2),(5,2),(6,2),(7,2),(8,2),(9,2);
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

-- Dump completed on 2022-06-23  2:45:31
