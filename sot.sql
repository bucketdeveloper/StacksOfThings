CREATE DATABASE  IF NOT EXISTS `things` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `things`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win64 (x86_64)
--
-- Host: localhost    Database: things
-- ------------------------------------------------------
-- Server version	5.6.21-log

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
-- Table structure for table `fill`
--

DROP TABLE IF EXISTS `fill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fill` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `thingName` varchar(256) DEFAULT NULL,
  `thingId` int(11) DEFAULT NULL,
  `matchName` varchar(256) DEFAULT NULL,
  `matchImageUrl` varchar(512) DEFAULT NULL,
  `matchId` int(11) DEFAULT NULL,
  `matchQty` varchar(200) DEFAULT NULL,
  `thingQty` varchar(200) DEFAULT NULL,
  `verb` varchar(45) DEFAULT NULL,
  `nsfw` int(11) DEFAULT NULL,
  `creation_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2433 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fill`
--

LOCK TABLES `fill` WRITE;
/*!40000 ALTER TABLE `fill` DISABLE KEYS */;
INSERT INTO `fill` VALUES (2386,'Penny',12,'Poodle (Adult Standard)','https://upload.wikimedia.org/wikipedia/commons/thumb/4/4c/Poodle%2C_cropped.JPG/1024px-Poodle%2C_cropped.JPG',23,'0.031',NULL,'ALA',0,'2015-09-29 12:42:18'),(2387,'5 Gallon Bucket',16,'Penny','http://www.usmint.gov/images/mint_programs/circulatingCoins/Penny-obverse.png',12,'450',NULL,'ACA',0,'2015-09-29 12:42:20'),(2388,'Penny',12,'Penny','http://www.usmint.gov/images/mint_programs/circulatingCoins/Penny-obverse.png',12,'1',NULL,'ATA',0,'2015-09-29 12:42:21'),(2389,'Empty Milk Jug',20,'Penny','http://www.usmint.gov/images/mint_programs/circulatingCoins/Penny-obverse.png',12,'22-3/4',NULL,'AHA',0,'2015-09-29 12:42:21'),(2390,'Penny',12,'Penny','http://www.usmint.gov/images/mint_programs/circulatingCoins/Penny-obverse.png',12,'1',NULL,'AHA',0,'2015-09-29 12:42:21'),(2391,'5 Gallon Bucket',16,'Sears/Willis Tower, Chicago','http://www.aviewoncities.com/img/chicago/kveus3960s.jpg',24,'0.005',NULL,'AWA',0,'2015-09-29 12:42:21'),(2392,'Statue of Liberty',22,'Pint Glass','http://img1.foodservicewarehouse.com/Prd/500SQ/LibbeyGlassware_15141.jpg',14,'302',NULL,'ATA',0,'2015-09-29 12:42:22'),(2393,'Penny',12,'Pint Glass','http://img1.foodservicewarehouse.com/Prd/500SQ/LibbeyGlassware_15141.jpg',14,'0.214',NULL,'ARA',0,'2015-09-29 12:42:24'),(2394,'Empty Milk Jug',20,'Pint Glass','http://img1.foodservicewarehouse.com/Prd/500SQ/LibbeyGlassware_15141.jpg',14,'0.500',NULL,'AHA',0,'2015-09-29 12:42:24'),(2395,'Statue of Liberty',22,'Pint Glass','http://img1.foodservicewarehouse.com/Prd/500SQ/LibbeyGlassware_15141.jpg',14,'65,714',NULL,'ACA',0,'2015-09-29 12:42:24'),(2396,'Empty Milk Jug',20,'Pint Glass','http://img1.foodservicewarehouse.com/Prd/500SQ/LibbeyGlassware_15141.jpg',14,'14-1/3',NULL,'AVA',0,'2015-09-29 12:42:24'),(2397,'Penny',12,'Pint Glass','http://img1.foodservicewarehouse.com/Prd/500SQ/LibbeyGlassware_15141.jpg',14,'0.214',NULL,'AWA',0,'2015-09-29 12:42:25'),(2398,'Full Milk Jug',15,'Pint Glass','http://img1.foodservicewarehouse.com/Prd/500SQ/LibbeyGlassware_15141.jpg',14,'14-1/2',NULL,'AVA',0,'2015-09-29 12:42:25'),(2399,'Empty Milk Jug',20,'Pint Glass','http://img1.foodservicewarehouse.com/Prd/500SQ/LibbeyGlassware_15141.jpg',14,'1-2/3',NULL,'ATA',0,'2015-09-29 12:42:25'),(2400,'Penny',12,'Pint Glass','http://img1.foodservicewarehouse.com/Prd/500SQ/LibbeyGlassware_15141.jpg',14,'0.003',NULL,'ACA',0,'2015-09-29 12:42:25'),(2401,'Empty Milk Jug',20,'Pint Glass','http://img1.foodservicewarehouse.com/Prd/500SQ/LibbeyGlassware_15141.jpg',14,'0.940',NULL,'ACA',0,'2015-09-29 12:42:25'),(2402,'Penny',12,'Pint Glass','http://img1.foodservicewarehouse.com/Prd/500SQ/LibbeyGlassware_15141.jpg',14,'0.214',NULL,'AWA',0,'2015-09-29 12:42:25'),(2403,'Penny',12,'Pint Glass','http://img1.foodservicewarehouse.com/Prd/500SQ/LibbeyGlassware_15141.jpg',14,'0.002',NULL,'AVA',0,'2015-09-29 12:42:26'),(2404,'Penny',12,'Poodle (Adult Standard)','https://upload.wikimedia.org/wikipedia/commons/thumb/4/4c/Poodle%2C_cropped.JPG/1024px-Poodle%2C_cropped.JPG',23,'0.000',NULL,'ACA',0,'2015-09-29 12:42:28'),(2405,'Penny',12,'Statue of Liberty','http://i.istockimg.com/file_thumbview_approve/16661041/5/stock-photo-16661041-statue-of-liberty-new-york.jpg',22,'0.000',NULL,'AVA',0,'2015-09-29 12:42:29'),(2406,'Penny',12,'Empty Milk Jug','https://tctechcrunch2011.files.wordpress.com/2012/05/milk.jpg?w=400',20,'0.125',NULL,'AWA',0,'2015-09-29 12:42:29'),(2407,'Penny',12,'Statue of Liberty','http://i.istockimg.com/file_thumbview_approve/16661041/5/stock-photo-16661041-statue-of-liberty-new-york.jpg',22,'0.003',NULL,'ALA',0,'2015-09-29 12:42:29'),(2408,'Penny',12,'\"D\" Battery','http://www.blasterstool.com/images/products/detail/energizer_D__67602_std.jpg',13,'0.010',NULL,'ACA',0,'2015-09-29 12:42:29'),(2409,'Penny',12,'Pint Glass','http://img1.foodservicewarehouse.com/Prd/500SQ/LibbeyGlassware_15141.jpg',14,'0.214',NULL,'ARA',0,'2015-09-29 12:42:29'),(2410,'Penny',12,'\"D\" Battery','http://www.blasterstool.com/images/products/detail/energizer_D__67602_std.jpg',13,'0.017',NULL,'AHA',0,'2015-09-29 12:42:30'),(2411,'Penny',12,'Empty Milk Jug','https://tctechcrunch2011.files.wordpress.com/2012/05/milk.jpg?w=400',20,'X / 0 = :-(',NULL,'ARA',0,'2015-09-29 12:42:30'),(2412,'Penny',12,'\"D\" Battery','http://www.blasterstool.com/images/products/detail/energizer_D__67602_std.jpg',13,'0.025',NULL,'ATA',0,'2015-09-29 12:42:30'),(2413,'Penny',12,'\"D\" Battery','http://www.blasterstool.com/images/products/detail/energizer_D__67602_std.jpg',13,'0.577',NULL,'ALA',0,'2015-09-29 12:42:31'),(2414,'Penny',12,'\"D\" Battery','http://www.blasterstool.com/images/products/detail/energizer_D__67602_std.jpg',13,'0.008',NULL,'AVA',0,'2015-09-29 12:42:31'),(2415,'Penny',12,'\"D\" Battery','http://www.blasterstool.com/images/products/detail/energizer_D__67602_std.jpg',13,'0.025',NULL,'ATA',0,'2015-09-29 12:42:31'),(2416,'Penny',12,'\"D\" Battery','http://www.blasterstool.com/images/products/detail/energizer_D__67602_std.jpg',13,'0.010',NULL,'ACA',0,'2015-09-29 12:42:32'),(2417,'Penny',12,'Statue of Liberty','http://i.istockimg.com/file_thumbview_approve/16661041/5/stock-photo-16661041-statue-of-liberty-new-york.jpg',22,'0.000',NULL,'AHA',0,'2015-09-29 12:42:32'),(2418,'Penny',12,'Statue of Liberty','http://i.istockimg.com/file_thumbview_approve/16661041/5/stock-photo-16661041-statue-of-liberty-new-york.jpg',22,'0.003',NULL,'ALA',0,'2015-09-29 12:42:33'),(2419,'Penny',12,'Empty Milk Jug','https://tctechcrunch2011.files.wordpress.com/2012/05/milk.jpg?w=400',20,'0.125',NULL,'AWA',0,'2015-09-29 12:42:33'),(2420,'Penny',12,'\"D\" Battery','http://www.blasterstool.com/images/products/detail/energizer_D__67602_std.jpg',13,'0.577',NULL,'AWA',0,'2015-09-29 12:42:33'),(2421,'Penny',12,'\"D\" Battery','http://www.blasterstool.com/images/products/detail/energizer_D__67602_std.jpg',13,'0.017',NULL,'AHA',0,'2015-09-29 12:42:34'),(2422,'Penny',12,'\"D\" Battery','http://www.blasterstool.com/images/products/detail/energizer_D__67602_std.jpg',13,'0.577',NULL,'AWA',0,'2015-09-29 12:42:34'),(2423,'Penny',12,'Empty Milk Jug','https://tctechcrunch2011.files.wordpress.com/2012/05/milk.jpg?w=400',20,'X / 0 = :-(',NULL,'ARA',0,'2015-09-29 12:42:34'),(2424,'Penny',12,'5 Gallon Bucket','http://www.usplastic.com/catalog/images/products/Buckets/sku/3826psku.jpg',16,'0.000',NULL,'AVA',0,'2015-09-29 12:42:35'),(2425,'Penny',12,'Empty Milk Jug','https://tctechcrunch2011.files.wordpress.com/2012/05/milk.jpg?w=400',20,'0.000',NULL,'AVA',0,'2015-09-29 12:42:35'),(2426,'Penny',12,'\"D\" Battery','http://www.blasterstool.com/images/products/detail/energizer_D__67602_std.jpg',13,'0.017',NULL,'AHA',0,'2015-09-29 12:42:35'),(2427,'Penny',12,'\"D\" Battery','http://www.blasterstool.com/images/products/detail/energizer_D__67602_std.jpg',13,'0.017',NULL,'AHA',0,'2015-09-29 12:42:35'),(2428,'Penny',12,'Pint Glass','http://img1.foodservicewarehouse.com/Prd/500SQ/LibbeyGlassware_15141.jpg',14,'0.003',NULL,'ACA',0,'2015-09-29 12:42:36'),(2429,'Penny',12,'\"D\" Battery','http://www.blasterstool.com/images/products/detail/energizer_D__67602_std.jpg',13,'0.577',NULL,'ARA',0,'2015-09-29 12:42:36'),(2430,'Penny',12,'\"D\" Battery','http://www.blasterstool.com/images/products/detail/energizer_D__67602_std.jpg',13,'0.008',NULL,'AVA',0,'2015-09-29 12:42:36'),(2431,'Penny',12,'Sears/Willis Tower, Chicago','http://www.aviewoncities.com/img/chicago/kveus3960s.jpg',24,'X / 0 = :-(',NULL,'AVA',0,'2015-09-29 12:42:36'),(2432,'Penny',12,'Statue of Liberty','http://i.istockimg.com/file_thumbview_approve/16661041/5/stock-photo-16661041-statue-of-liberty-new-york.jpg',22,'0.000',NULL,'AVA',0,'2015-09-29 12:42:36');
/*!40000 ALTER TABLE `fill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `thing`
--

DROP TABLE IF EXISTS `thing`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `thing` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL,
  `description` longtext,
  `height` double NOT NULL,
  `width` double NOT NULL,
  `length` double NOT NULL,
  `weight` double NOT NULL,
  `volume` double NOT NULL,
  `year` int(11) DEFAULT NULL,
  `nsfw` int(11) NOT NULL DEFAULT '1',
  `originalPrice` double DEFAULT NULL,
  `currentPrice` double DEFAULT NULL,
  `creation_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `modification_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `imageUrl` varchar(512) DEFAULT NULL,
  `radius` double DEFAULT NULL,
  `diff` double DEFAULT NULL,
  `pluralName` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thing`
--

LOCK TABLES `thing` WRITE;
/*!40000 ALTER TABLE `thing` DISABLE KEYS */;
INSERT INTO `thing` VALUES (12,'Penny','See a Penny, pick it up. All that day you\'ll have good luck. ',0.0598,0.75,0.75,0.0881,0.026,1792,0,0.01,0.01,'2015-09-24 14:13:37','2015-09-28 21:17:32','http://www.usmint.gov/images/mint_programs/circulatingCoins/Penny-obverse.png',0.375,NULL,'Pennies'),(13,'\"D\" Battery','Standard \"D\" sized battery.',2.42,1.3,1.3,5.1,3.21,1898,0,0,1,'2015-09-24 14:18:53','2015-09-28 21:16:53','http://www.blasterstool.com/images/products/detail/energizer_D__67602_std.jpg',0.65,NULL,'\"D\" Batteries'),(14,'Pint Glass','Empty Pint Glass. Fancy a pint?',6,3.5,3.5,4,16,1800,0,1,3.5,'2015-09-24 14:31:59','2015-09-28 21:16:35','http://img1.foodservicewarehouse.com/Prd/500SQ/LibbeyGlassware_15141.jpg',1.75,NULL,'Pint Glasses'),(15,'Full Milk Jug','Your standard plastic Milk jug',10,6,6,136,231,1964,0,1,3.29,'2015-09-24 16:16:20','2015-09-27 14:56:21','https://tctechcrunch2011.files.wordpress.com/2012/05/milk.jpg?w=400',0,NULL,'Full Milk Jugs'),(16,'5 Gallon Bucket','Sometimes called a \"painters pail\", and available at Hardware Stores',17.5,11.8,11.8,0.5,1155,1970,0,1,4.5,'2015-09-24 16:20:51','2015-09-28 21:16:00','http://www.usplastic.com/catalog/images/products/Buckets/sku/3826psku.jpg',5.9,NULL,'5 Gallon Buckets'),(17,'Baseball','Leather-wrapped ball of elastics. ',2.9,2.9,2.9,5,12.77,1839,0,0.25,0.85,'2015-09-24 16:38:09','2015-09-28 21:15:21','https://d2t1xqejof9utc.cloudfront.net/screenshots/pics/221cb5e3514ba254286a2593e3b85563/original.jpg',1.45,NULL,'Baseballs'),(20,'Empty Milk Jug','Your standard plastic Milk jug',10,6,6,2,230,1964,0,1,3.29,'2015-09-24 16:16:20','2015-09-27 14:56:06','https://tctechcrunch2011.files.wordpress.com/2012/05/milk.jpg?w=400',0,NULL,'Empty Milk Jugs'),(21,'Human Penis (erect)','https://en.wikipedia.org/wiki/Human_penis_size',5.17,4.5,5.17,2.99,61.3,1,1,0.25,1,'2015-09-25 20:02:35','2015-09-28 21:14:54','https://upload.wikimedia.org/wikipedia/commons/thumb/e/e7/Erect_penis_measurement.png/220px-Erect_penis_measurement.png',0.73,NULL,'Human Penises (erect)'),(22,'Statue of Liberty','http://www.rotorooter.com/blog/what-is-the-statue-of-liberty-worth-in-scrap-metal, http://www.factmonster.com/ipka/A0101054.html',1812,162.96,282.96,312000,83553424.8192,1876,0,250000,230000,'2015-09-25 20:34:24','2015-09-29 11:36:46','http://i.istockimg.com/file_thumbview_approve/16661041/5/stock-photo-16661041-statue-of-liberty-new-york.jpg',0,NULL,'Statues of Liberty'),(23,'Poodle (Adult Standard)','An adult, American Standard yipping machine.',21,8,24,960,0,1887,0,0,1500,'2015-09-27 17:17:46','2015-09-27 17:19:13','https://upload.wikimedia.org/wikipedia/commons/thumb/4/4c/Poodle%2C_cropped.JPG/1024px-Poodle%2C_cropped.JPG',0,NULL,'Poodle (Adult Standard)'),(24,'Sears/Willis Tower, Chicago','Formerly known as the Sears Tower, the Willis Tower remains one of the largest buildings in the US. ',20748,2340,2340,7120000000,0,1974,0,186000000,186000000,'2015-09-28 14:16:04',NULL,'http://www.aviewoncities.com/img/chicago/kveus3960s.jpg',0,NULL,'Sears Towers');
/*!40000 ALTER TABLE `thing` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-09-29 12:44:29
