-- MySQL dump 10.13  Distrib 5.6.19, for Win64 (x86_64)
--
-- Host: localhost    Database: test
-- ------------------------------------------------------
-- Server version	5.6.19

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
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customers` (
  `cid` int(50) NOT NULL AUTO_INCREMENT,
  `login` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `fname` varchar(50) NOT NULL,
  `lname` varchar(50) NOT NULL,
  `age` int(20) NOT NULL,
  `address` varchar(100) NOT NULL,
  `gender` char(1) NOT NULL,
  `balance` float(7,2) DEFAULT NULL,
  `checklimit` tinyint(1) DEFAULT '0',
  `credit` int(20) DEFAULT '0',
  `cash` tinyint(1) DEFAULT '0',
  `paid` tinyint(1) DEFAULT '0',
  `active` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`cid`),
  UNIQUE KEY `login` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES (1,'bert','111','berty','liang',25,'9 eton street markham','m',300.00,1,0,0,0,1),(2,'noah234','123','noah','sung',20,'375 lawson road scarborough','m',0.00,0,0,1,0,1),(3,'jin','111','jin','jin',25,'1265 military trail toronto','m',0.00,0,12345678,0,0,1),(4,'jack1234','567','jack','bouer',50,'563 spadina crescent toronto','m',100.00,1,0,0,0,1),(5,'melody','123','melo','dy',20,'350 victoria street toronto','f',500.00,1,0,0,0,1),(6,'kai','111','kai','song',21,'785 milner ave scarborough','m',0.00,0,0,1,0,1),(7,'kobe','123','kobe','bryant',30,'2867 ellesmere road toronto','m',1000.00,1,0,0,0,1),(8,'eva','123','eva','liang',19,'1265 military trail toronto','f',200.00,1,0,0,0,1),(9,'bonny','111','bonny','lee',20,'941 progress avenue toronto','f',400.00,1,0,0,0,1),(10,'jane','123','jane','sung',21,'1750 finch avenue east toronto','f',0.00,0,0,1,0,1);
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `delivpeople`
--

DROP TABLE IF EXISTS `delivpeople`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `delivpeople` (
  `dID` int(50) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `address` varchar(100) NOT NULL,
  `workHrs` float(6,2) NOT NULL,
  `salary` float(5,2) NOT NULL,
  `transInfo` varchar(50) DEFAULT NULL,
  `vehicle` varchar(50) DEFAULT NULL,
  `cost` float(4,2) DEFAULT NULL,
  `location` varchar(50) DEFAULT NULL,
  `shortD` float(5,2) NOT NULL,
  `medD` float(5,2) DEFAULT NULL,
  `longD` float(5,2) DEFAULT NULL,
  PRIMARY KEY (`dID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `delivpeople`
--

LOCK TABLES `delivpeople` WRITE;
/*!40000 ALTER TABLE `delivpeople` DISABLE KEYS */;
INSERT INTO `delivpeople` VALUES (1,'Bertina Liang','346 Yonge Street Toronto',24.00,9.00,'Honda','car',4.00,'bloor',3.00,5.00,9.00),(2,'Betty Jin','761 Bay Street Toronto',2.00,20.00,'Yamaha','motorcycle',5.00,'yonge',2.00,4.00,9.00),(3,'Tyron Monay','1923 Yonge Street Toronto',8.00,18.00,'Hotwheel','scooter',2.00,'ghetto plaza',4.00,5.00,10.00),(4,'Tough Man','1923 Yonge Street Toronto',7.00,30.00,'Hummer','jeep',5.00,'dizzy desert',4.00,8.00,12.00),(5,'Captain Norris','9390 Sheppard Avenue East Scarborough',14.00,10.00,'Patton','tank',8.00,'gunz forest',5.00,9.00,13.00),(6,'Luna Speed','346 Yonge Street Toronto',20.00,8.00,'GTR','car',8.00,'laguna',4.00,8.00,10.00),(7,'Lil Wong','761 Bay Street Toronto',3.00,5.00,'Hotwheel','skateboard',2.00,'chinatown',3.00,5.00,10.00),(8,'Drift King','1923 Yonge Street Toronto',8.00,12.00,'Camaro','car',7.00,'rick track',3.00,7.00,9.00),(9,'Cray Guy','1923 Yonge Street Toronto',18.00,8.00,'Honda','motorcycle',3.00,'spadina',2.00,4.00,8.00),(10,'Long Dee','9390 Sheppard Avenue East Scarborough',10.00,10.00,'Giant','bicycle',4.00,'sheppard',3.00,5.00,0.00),(11,'Long Leggy','9390 Sheppard Avenue East Scarborough',5.00,8.00,'walk','NULL',0.00,'sheppard',6.00,0.00,0.00);
/*!40000 ALTER TABLE `delivpeople` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nonpizza`
--

DROP TABLE IF EXISTS `nonpizza`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `nonpizza` (
  `oid` int(50) DEFAULT NULL,
  `pid` int(20) DEFAULT NULL,
  `quantity` int(20) DEFAULT NULL,
  `subtotal` float(6,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nonpizza`
--

LOCK TABLES `nonpizza` WRITE;
/*!40000 ALTER TABLE `nonpizza` DISABLE KEYS */;
INSERT INTO `nonpizza` VALUES (1,6,2,14.00),(2,1,1,7.00),(2,26,3,30.00),(3,29,1,3.00),(4,6,1,7.00),(5,1,2,14.00);
/*!40000 ALTER TABLE `nonpizza` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `oid` int(50) NOT NULL AUTO_INCREMENT,
  `cid` int(50) DEFAULT NULL,
  `orderTime` datetime DEFAULT NULL,
  `deliverTime` datetime DEFAULT NULL,
  `total` float(7,2) DEFAULT NULL,
  `standing` varchar(10) DEFAULT NULL,
  `paid` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,3,'2014-07-18 17:30:00','2014-07-18 06:00:00',56.00,NULL,0),(2,1,'2014-07-20 20:15:00','2014-07-21 12:22:00',60.00,NULL,0),(3,1,'2014-07-21 12:15:00','2014-07-21 20:45:00',40.00,NULL,0),(4,2,'2014-07-22 13:45:00','2014-07-22 15:20:00',66.00,NULL,0),(5,4,'2014-07-22 14:15:00','2014-07-22 16:10:00',80.00,NULL,0),(6,2,'2014-07-23 13:45:00','2014-07-23 14:14:00',45.00,NULL,0);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pizzaorder`
--

DROP TABLE IF EXISTS `pizzaorder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pizzaorder` (
  `pizzaID` int(50) NOT NULL AUTO_INCREMENT,
  `pid` int(20) DEFAULT NULL,
  `oID` int(50) DEFAULT NULL,
  `quantity` int(20) DEFAULT NULL,
  `subtotal` float(6,2) DEFAULT NULL,
  PRIMARY KEY (`pizzaID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pizzaorder`
--

LOCK TABLES `pizzaorder` WRITE;
/*!40000 ALTER TABLE `pizzaorder` DISABLE KEYS */;
INSERT INTO `pizzaorder` VALUES (1,1,1,2,24.00),(2,4,1,1,13.25),(3,5,2,2,11.00),(4,4,2,1,23.00),(5,5,2,3,12.00),(6,34,4,4,45.24),(7,35,5,2,25.00),(8,1,6,1,12.00),(9,35,6,2,32.00);
/*!40000 ALTER TABLE `pizzaorder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `products` (
  `pid` int(50) NOT NULL AUTO_INCREMENT,
  `description` varchar(100) NOT NULL,
  `category` varchar(50) DEFAULT NULL,
  `manufacturer` varchar(50) DEFAULT NULL,
  `supplier` varchar(30) DEFAULT NULL,
  `price` float(7,2) DEFAULT NULL,
  `taxable` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'thin crust pizza','pizza','pizza nova','poop foods',10.00,1),(2,'buffalo wings','side','wonka','M&M',7.00,1),(3,'cheddar','topping','pizza nova','poop foods',2.00,1),(4,'cheese crust pizza','pizza','mario','luigi',5.00,1),(5,'thick crust pizza','pizza','mario','king kong',5.00,1),(6,'bbq wings','side','ricardo','ching foods',7.00,1),(7,'poutine','side','mohammad halal','halal foods',7.00,1),(8,'pepsi','drink','walmart','coca cola',1.00,1),(9,'steak and cheese','topping','pizza pizza','luigi',9.00,1),(10,'teriyaki chicken','topping','hondo wok','terry japs',8.00,1),(11,'apple juice','drink','walmart','minuet maid',3.00,1),(12,'hot banana peppers','topping','pizza pizza','poop foods',1.00,1),(13,'pineapple','topping','pizza hut','poop foods',1.00,1),(14,'roma tomatoes','topping','pizza pizza','poop foods',1.00,1),(15,'red onions','topping','pizza hut','king kong',1.00,1),(16,'buffalo chicken','topping','pizza pizza','king kong',2.00,1),(17,'chipotle chicken','topping','pizza hut','poop foods',2.00,1),(18,'chipotle steak','topping','pizza pizza','poop foods',2.00,1),(19,'smoked ham','topping','pizza hut','poop foods',2.00,1),(20,'pepperoni','topping','pizza pizza','hondo wok',2.00,1),(21,'tuna','topping','pizza hut','ricardo ching foods',2.00,1),(22,'hawaiian shrimp','topping','piiza hut','ricardo ching foods',2.00,1),(23,'spicy italian sausage','topping','pizza pizza','hondo wok',2.00,1),(24,'extra cheese','topping','pizza pizza','hondo wok',1.00,1),(25,'goat cheese','topping','pizza pizza','ricardo ching',1.00,1),(26,'chicken bites','side','pizza pizza','hondo wok',5.00,1),(27,'shrimp','side','pizza hut','ricardo ching',10.00,1),(28,'penne bolognese','side','pizza pizza','ricardo ching',7.00,1),(29,'garlic toast','side','pizza hut','ricardo ching',3.00,1),(30,'bacon caesar salad','side','pizza hut','hondo wok',5.00,1),(31,'asian chicken salad','side','pizza pizza','poop foods',5.00,1),(32,'coke','drink','walmart','coca cola',1.00,1),(33,'ice tea','drink','walmart','coca cola',1.00,1),(34,'non cheese crust','pizza','pizza pizza','poop foods',2.00,1),(35,'double cheese crust','pizza','pizza hut','king kong',5.00,1),(36,'milk','drink','wonka','M&M',2.00,1),(37,'water','drink','wonka','luigi',1.00,1),(38,'diet coke','drink','walmart','coca cola',2.00,1);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sitems`
--

DROP TABLE IF EXISTS `sitems`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sitems` (
  `sid` int(50) NOT NULL,
  `pizzaID` int(50) DEFAULT NULL,
  `top1` int(20) DEFAULT NULL,
  `top2` int(20) DEFAULT NULL,
  `top3` int(20) DEFAULT NULL,
  `pQuantity` int(5) DEFAULT NULL,
  `side` int(20) DEFAULT NULL,
  `sQuantity` int(5) DEFAULT NULL,
  `drink` int(20) DEFAULT NULL,
  `dQuantity` int(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sitems`
--

LOCK TABLES `sitems` WRITE;
/*!40000 ALTER TABLE `sitems` DISABLE KEYS */;
INSERT INTO `sitems` VALUES (1,35,22,0,0,1,0,0,11,1),(1,4,20,0,0,1,0,0,33,1),(2,1,19,3,0,2,2,3,0,0),(3,1,14,21,12,1,31,1,36,1),(4,0,0,0,0,0,30,1,11,2),(5,5,17,15,0,1,0,0,8,2);
/*!40000 ALTER TABLE `sitems` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sorders`
--

DROP TABLE IF EXISTS `sorders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sorders` (
  `oid` int(50) NOT NULL,
  `sid` int(50) DEFAULT NULL,
  `quantity` int(20) DEFAULT NULL,
  `subtotal` float(7,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sorders`
--

LOCK TABLES `sorders` WRITE;
/*!40000 ALTER TABLE `sorders` DISABLE KEYS */;
INSERT INTO `sorders` VALUES (1,2,1,23.95),(2,5,2,32.12),(3,3,4,50.21),(4,4,1,12.22),(5,1,3,60.02);
/*!40000 ALTER TABLE `sorders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `specials`
--

DROP TABLE IF EXISTS `specials`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `specials` (
  `sid` int(50) NOT NULL AUTO_INCREMENT,
  `sName` varchar(50) NOT NULL,
  `sprice` float(5,2) DEFAULT NULL,
  `startDate` datetime DEFAULT NULL,
  `endDate` datetime DEFAULT NULL,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `specials`
--

LOCK TABLES `specials` WRITE;
/*!40000 ALTER TABLE `specials` DISABLE KEYS */;
INSERT INTO `specials` VALUES (1,'the real american combo',20.00,'2014-07-01 00:00:00','2014-07-07 23:59:59'),(2,'game on combo',20.00,'2014-07-08 00:00:00','2014-07-14 23:59:59'),(3,'couple combo',14.00,'2014-07-15 00:00:00','2014-07-21 23:59:59'),(4,'poutine juiced up',8.00,'2014-07-22 00:00:00','2014-07-28 23:59:59'),(5,'thats so cheezy',12.00,'2014-07-29 00:00:00','2014-08-04 23:59:59');
/*!40000 ALTER TABLE `specials` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ticket` (
  `tid` int(50) NOT NULL,
  `did` int(20) DEFAULT NULL,
  `timeDisp` datetime DEFAULT NULL,
  `timeArr` datetime DEFAULT NULL,
  `timeRet` datetime DEFAULT NULL,
  PRIMARY KEY (`tid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
INSERT INTO `ticket` VALUES (1,1,'2014-07-10 10:00:00','2014-07-10 10:20:00','2014-07-10 10:40:00'),(2,2,'2014-07-12 20:00:00','2014-07-12 20:30:00','2014-07-12 21:00:00'),(3,3,'2014-07-13 05:15:00','2014-07-13 05:30:00','2014-07-13 05:45:00'),(4,4,'2014-07-15 17:05:00','2014-07-15 17:35:00','2014-07-15 18:05:00');
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `toppings`
--

DROP TABLE IF EXISTS `toppings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `toppings` (
  `pizzaID` int(20) DEFAULT NULL,
  `pid` int(20) DEFAULT NULL,
  `subtotal` float(5,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `toppings`
--

LOCK TABLES `toppings` WRITE;
/*!40000 ALTER TABLE `toppings` DISABLE KEYS */;
INSERT INTO `toppings` VALUES (1,3,1.00),(2,13,1.00),(2,25,1.00),(3,17,2.00),(4,22,2.00),(5,24,1.00),(6,9,9.00),(7,14,1.00),(7,15,1.00),(7,12,1.00),(8,23,2.00),(9,10,8.00),(9,20,2.00),(9,18,2.00);
/*!40000 ALTER TABLE `toppings` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-01-25 15:49:40
