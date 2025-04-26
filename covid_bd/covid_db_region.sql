-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: covid_db
-- ------------------------------------------------------
-- Server version	8.4.0

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
-- Table structure for table `region`
--

DROP TABLE IF EXISTS `region`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `region` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `iso` varchar(10) DEFAULT NULL,
  `lat` varchar(255) DEFAULT NULL,
  `lon` varchar(255) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=219 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `region`
--

LOCK TABLES `region` WRITE;
/*!40000 ALTER TABLE `region` DISABLE KEYS */;
INSERT INTO `region` VALUES (1,'China','CHN',NULL,NULL,NULL),(2,'Taipei and environs','TWN',NULL,NULL,NULL),(3,'US','USA',NULL,NULL,NULL),(4,'Japan','JPN',NULL,NULL,NULL),(5,'Thailand','THA',NULL,NULL,NULL),(6,'Korea, South','KOR',NULL,NULL,NULL),(7,'Singapore','SGP',NULL,NULL,NULL),(8,'Philippines','PHL',NULL,NULL,NULL),(9,'Malaysia','MYS',NULL,NULL,NULL),(10,'Vietnam','VNM',NULL,NULL,NULL),(11,'Australia','AUS',NULL,NULL,NULL),(12,'Mexico','MEX',NULL,NULL,NULL),(13,'Brazil','BRA',NULL,NULL,NULL),(14,'Colombia','COL',NULL,NULL,NULL),(15,'France','FRA',NULL,NULL,NULL),(16,'Nepal','NPL',NULL,NULL,NULL),(17,'Canada','CAN',NULL,NULL,NULL),(18,'Cambodia','KHM',NULL,NULL,NULL),(19,'Sri Lanka','LKA',NULL,NULL,NULL),(20,'Cote d\'Ivoire','CIV',NULL,NULL,NULL),(21,'Germany','DEU',NULL,NULL,NULL),(22,'Finland','FIN',NULL,NULL,NULL),(23,'United Arab Emirates','ARE',NULL,NULL,NULL),(24,'India','IND',NULL,NULL,NULL),(25,'Italy','ITA',NULL,NULL,NULL),(26,'United Kingdom','GBR',NULL,NULL,NULL),(27,'Russia','RUS',NULL,NULL,NULL),(28,'Sweden','SWE',NULL,NULL,NULL),(29,'Spain','ESP',NULL,NULL,NULL),(30,'Belgium','BEL',NULL,NULL,NULL),(31,'Others','Others',NULL,NULL,NULL),(32,'Egypt','EGY',NULL,NULL,NULL),(33,'Iran','IRN',NULL,NULL,NULL),(34,'Israel','ISR',NULL,NULL,NULL),(35,'Lebanon','LBN',NULL,NULL,NULL),(36,'Iraq','IRQ',NULL,NULL,NULL),(37,'Oman','OMN',NULL,NULL,NULL),(38,'Afghanistan','AFG',NULL,NULL,NULL),(39,'Bahrain','BHR',NULL,NULL,NULL),(40,'Kuwait','KWT',NULL,NULL,NULL),(41,'Austria','AUT',NULL,NULL,NULL),(42,'Algeria','DZA',NULL,NULL,NULL),(43,'Croatia','HRV',NULL,NULL,NULL),(44,'Switzerland','CHE',NULL,NULL,NULL),(45,'Pakistan','PAK',NULL,NULL,NULL),(46,'Georgia','GEO',NULL,NULL,NULL),(47,'Greece','GRC',NULL,NULL,NULL),(48,'North Macedonia','MKD',NULL,NULL,NULL),(49,'Norway','NOR',NULL,NULL,NULL),(50,'Romania','ROU',NULL,NULL,NULL),(51,'Denmark','DNK',NULL,NULL,NULL),(52,'Estonia','EST',NULL,NULL,NULL),(53,'Netherlands','NLD',NULL,NULL,NULL),(54,'San Marino','SMR',NULL,NULL,NULL),(55,'Azerbaijan','AZE',NULL,NULL,NULL),(56,'Belarus','BLR',NULL,NULL,NULL),(57,'Iceland','ISL',NULL,NULL,NULL),(58,'Lithuania','LTU',NULL,NULL,NULL),(59,'New Zealand','NZL',NULL,NULL,NULL),(60,'Nigeria','NGA',NULL,NULL,NULL),(61,'Ireland','IRL',NULL,NULL,NULL),(62,'Luxembourg','LUX',NULL,NULL,NULL),(63,'Monaco','MCO',NULL,NULL,NULL),(64,'Qatar','QAT',NULL,NULL,NULL),(65,'Ecuador','ECU',NULL,NULL,NULL),(66,'Czechia','CZE',NULL,NULL,NULL),(67,'Armenia','ARM',NULL,NULL,NULL),(68,'Dominican Republic','DOM',NULL,NULL,NULL),(69,'Indonesia','IDN',NULL,NULL,NULL),(70,'Portugal','PRT',NULL,NULL,NULL),(71,'Andorra','AND',NULL,NULL,NULL),(72,'Latvia','LVA',NULL,NULL,NULL),(73,'Morocco','MAR',NULL,NULL,NULL),(74,'Saudi Arabia','SAU',NULL,NULL,NULL),(75,'Senegal','SEN',NULL,NULL,NULL),(76,'Argentina','ARG',NULL,NULL,NULL),(77,'Chile','CHL',NULL,NULL,NULL),(78,'Jordan','JOR',NULL,NULL,NULL),(79,'Ukraine','UKR',NULL,NULL,NULL),(80,'Saint Barthelemy','BLM',NULL,NULL,NULL),(81,'Hungary','HUN',NULL,NULL,NULL),(82,'Faroe Islands','FRO',NULL,NULL,NULL),(83,'Gibraltar','GIB',NULL,NULL,NULL),(84,'Liechtenstein','LIE',NULL,NULL,NULL),(85,'Poland','POL',NULL,NULL,NULL),(86,'Tunisia','TUN',NULL,NULL,NULL),(87,'West Bank and Gaza','PSE',NULL,NULL,NULL),(88,'Bosnia and Herzegovina','BIH',NULL,NULL,NULL),(89,'Slovenia','SVN',NULL,NULL,NULL),(90,'South Africa','ZAF',NULL,NULL,NULL),(91,'Bhutan','BTN',NULL,NULL,NULL),(92,'Cameroon','CMR',NULL,NULL,NULL),(93,'Costa Rica','CRI',NULL,NULL,NULL),(94,'Peru','PER',NULL,NULL,NULL),(95,'Serbia','SRB',NULL,NULL,NULL),(96,'Slovakia','SVK',NULL,NULL,NULL),(97,'Togo','TGO',NULL,NULL,NULL),(98,'Holy See','VAT',NULL,NULL,NULL),(99,'French Guiana','GUF',NULL,NULL,NULL),(100,'Malta','MLT',NULL,NULL,NULL),(101,'Martinique','MTQ',NULL,NULL,NULL),(102,'Bulgaria','BGR',NULL,NULL,NULL),(103,'Maldives','MDV',NULL,NULL,NULL),(104,'Bangladesh','BGD',NULL,NULL,NULL),(105,'Moldova','MDA',NULL,NULL,NULL),(106,'Paraguay','PRY',NULL,NULL,NULL),(107,'Albania','ALB',NULL,NULL,NULL),(108,'Cyprus','CYP',NULL,NULL,NULL),(109,'Brunei','BRN',NULL,NULL,NULL),(110,'Macao SAR','MAC',NULL,NULL,NULL),(111,'Saint Martin','MAF',NULL,NULL,NULL),(112,'Burkina Faso','BFA',NULL,NULL,NULL),(113,'Channel Islands','GGY-JEY',NULL,NULL,NULL),(114,'Mongolia','MNG',NULL,NULL,NULL),(115,'Panama','PAN',NULL,NULL,NULL),(116,'Cruise Ship','cruise',NULL,NULL,NULL),(117,'Taiwan*','TWN',NULL,NULL,NULL),(118,'Bolivia','BOL',NULL,NULL,NULL),(119,'Honduras','HND',NULL,NULL,NULL),(120,'Congo (Kinshasa)','COD',NULL,NULL,NULL),(121,'Jamaica','JAM',NULL,NULL,NULL),(122,'Reunion','REU',NULL,NULL,NULL),(123,'Turkey','TUR',NULL,NULL,NULL),(124,'Cuba','CUB',NULL,NULL,NULL),(125,'Guyana','GUY',NULL,NULL,NULL),(126,'Kazakhstan','KAZ',NULL,NULL,NULL),(127,'Cayman Islands','CYM',NULL,NULL,NULL),(128,'Guadeloupe','GLP',NULL,NULL,NULL),(129,'Ethiopia','ETH',NULL,NULL,NULL),(130,'Sudan','SDN',NULL,NULL,NULL),(131,'Guinea','GIN',NULL,NULL,NULL),(132,'Antigua and Barbuda','ATG',NULL,NULL,NULL),(133,'Aruba','ABW',NULL,NULL,NULL),(134,'Kenya','KEN',NULL,NULL,NULL),(135,'Uruguay','URY',NULL,NULL,NULL),(136,'Ghana','GHA',NULL,NULL,NULL),(137,'Jersey','JEY',NULL,NULL,NULL),(138,'Namibia','NAM',NULL,NULL,NULL),(139,'Seychelles','SYC',NULL,NULL,NULL),(140,'Trinidad and Tobago','TTO',NULL,NULL,NULL),(141,'Venezuela','VEN',NULL,NULL,NULL),(142,'Curacao','CUW',NULL,NULL,NULL),(143,'Eswatini','SWZ',NULL,NULL,NULL),(144,'Gabon','GAB',NULL,NULL,NULL),(145,'Guatemala','GTM',NULL,NULL,NULL),(146,'Guernsey','GGY',NULL,NULL,NULL),(147,'Mauritania','MRT',NULL,NULL,NULL),(148,'Rwanda','RWA',NULL,NULL,NULL),(149,'Saint Lucia','LCA',NULL,NULL,NULL),(150,'Saint Vincent and the Grenadines','VCT',NULL,NULL,NULL),(151,'Suriname','SUR',NULL,NULL,NULL),(152,'Kosovo','RKS',NULL,NULL,NULL),(153,'Central African Republic','CAF',NULL,NULL,NULL),(154,'Congo (Brazzaville)','COG',NULL,NULL,NULL),(155,'Equatorial Guinea','GNQ',NULL,NULL,NULL),(156,'Uzbekistan','UZB',NULL,NULL,NULL),(157,'Guam','GUM',NULL,NULL,NULL),(158,'Puerto Rico','PRI',NULL,NULL,NULL),(159,'Benin','BEN',NULL,NULL,NULL),(160,'Greenland','GRL',NULL,NULL,NULL),(161,'Liberia','LBR',NULL,NULL,NULL),(162,'Mayotte','MYT',NULL,NULL,NULL),(163,'Somalia','SOM',NULL,NULL,NULL),(164,'Tanzania','TZA',NULL,NULL,NULL),(165,'Bahamas','BHS',NULL,NULL,NULL),(166,'Barbados','BRB',NULL,NULL,NULL),(167,'Montenegro','MNE',NULL,NULL,NULL),(168,'Gambia','GMB',NULL,NULL,NULL),(169,'Kyrgyzstan','KGZ',NULL,NULL,NULL),(170,'Mauritius','MUS',NULL,NULL,NULL),(171,'Zambia','ZMB',NULL,NULL,NULL),(172,'Djibouti','DJI',NULL,NULL,NULL),(173,'Chad','TCD',NULL,NULL,NULL),(174,'El Salvador','SLV',NULL,NULL,NULL),(175,'Fiji','FJI',NULL,NULL,NULL),(176,'Nicaragua','NIC',NULL,NULL,NULL),(177,'Madagascar','MDG',NULL,NULL,NULL),(178,'Haiti','HTI',NULL,NULL,NULL),(179,'Angola','AGO',NULL,NULL,NULL),(180,'Cabo Verde','CPV',NULL,NULL,NULL),(181,'Niger','NER',NULL,NULL,NULL),(182,'Papua New Guinea','PNG',NULL,NULL,NULL),(183,'Zimbabwe','ZWE',NULL,NULL,NULL),(184,'Timor-Leste','TLS',NULL,NULL,NULL),(185,'Eritrea','ERI',NULL,NULL,NULL),(186,'Uganda','UGA',NULL,NULL,NULL),(187,'Dominica','DMA',NULL,NULL,NULL),(188,'Grenada','GRD',NULL,NULL,NULL),(189,'Mozambique','MOZ',NULL,NULL,NULL),(190,'Syria','SYR',NULL,NULL,NULL),(191,'Belize','BLZ',NULL,NULL,NULL),(192,'Laos','LAO',NULL,NULL,NULL),(193,'Libya','LBY',NULL,NULL,NULL),(194,'Diamond Princess','NA-SHIP-DP',NULL,NULL,NULL),(195,'Guinea-Bissau','GNB',NULL,NULL,NULL),(196,'Mali','MLI',NULL,NULL,NULL),(197,'Saint Kitts and Nevis','KNA',NULL,NULL,NULL),(198,'Botswana','BWA',NULL,NULL,NULL),(199,'Burundi','BDI',NULL,NULL,NULL),(200,'Sierra Leone','SLE',NULL,NULL,NULL),(201,'Burma','MMR',NULL,NULL,NULL),(202,'Malawi','MWI',NULL,NULL,NULL),(203,'South Sudan','SSD',NULL,NULL,NULL),(204,'Western Sahara','ESH',NULL,NULL,NULL),(205,'Sao Tome and Principe','STP',NULL,NULL,NULL),(206,'Yemen','YEM',NULL,NULL,NULL),(207,'Comoros','COM',NULL,NULL,NULL),(208,'Tajikistan','TJK',NULL,NULL,NULL),(209,'Lesotho','LSO',NULL,NULL,NULL),(210,'Solomon Islands','SLB',NULL,NULL,NULL),(211,'Marshall Islands','MHL',NULL,NULL,NULL),(212,'Vanuatu','VUT',NULL,NULL,NULL),(213,'Samoa','WSM',NULL,NULL,NULL),(214,'Kiribati','KIR',NULL,NULL,NULL),(215,'Palau','PLW',NULL,NULL,NULL),(216,'Tonga','TON',NULL,NULL,NULL),(217,'Nauru','NRU',NULL,NULL,NULL),(218,'Tuvalu','TUV',NULL,NULL,NULL);
/*!40000 ALTER TABLE `region` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-26 17:16:30
