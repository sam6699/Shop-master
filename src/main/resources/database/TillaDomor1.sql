  /*
SQLyog Ultimate v12.14 (64 bit)
MySQL - 10.1.19-MariaDB : Database - marketdb
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`marketdb` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;

USE `marketdb`;

/*Table structure for table `client` */

DROP TABLE IF EXISTS `client`;

CREATE TABLE `client` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `companyName` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `director` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `address` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `client` */

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` text,
  `price` int(11) DEFAULT NULL,
  `measure_label` text,
  `measure_value` double DEFAULT NULL,
  `quantity` double DEFAULT NULL,
  `path` text,
  `product_type_id` int(11) DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `product_type_id` (`product_type_id`),
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`product_type_id`) REFERENCES `product_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;

/*Data for the table `product` */

insert  into `product`(`id`,`name`,`price`,`measure_label`,`measure_value`,`quantity`,`path`,`product_type_id`,`deleted`) values 

(20,'\"Голландский\" вакуум',40000,'кг',1,NULL,NULL,5,0),

(21,'\"Голландский\"',36000,'кг',1,NULL,NULL,5,0),

(22,'\"Столовый\"',18000,'кг',1,NULL,NULL,6,0),

(23,'\"Копченый\"',18000,'кг',1,NULL,NULL,6,0),

(24,'\"Янтарь\"',4000,'дона',1,NULL,NULL,6,0),

(25,'\"Лаззат\"',4000,'дона',1,NULL,NULL,6,0),

(26,'\"Янтарь\" коробка',120000,'коробка',1,NULL,NULL,6,0),

(27,'\"Лаззат\" коробка',120000,'коробка',1,NULL,NULL,6,0),

(28,'Брынза кукатли',28000,'кг',1,NULL,NULL,7,0),

(29,'Сут 3,2%',3200,'кг/л',1,NULL,NULL,7,0),

(30,'Катик',2500,'кг/л',1,NULL,NULL,7,0),

(31,'Катик 500гр',2500,'500гр/мл',1,NULL,NULL,7,0),

(32,'Катик 3,2',3000,'кг/л',1,NULL,NULL,7,0),

(33,'Катик 3,2% 500гр',2500,'500гр/мл',1,NULL,NULL,7,0),

(34,'Творог',14000,'кг',1,NULL,NULL,7,0),

(35,'Творог 500гр',7500,'дона',1,NULL,NULL,7,0),

(36,'Каймок 50% 480гр',12000,'дона',1,NULL,NULL,7,0),

(37,'Каймок 50% 240гр',6000,'дона',1,NULL,NULL,7,0),

(38,'Сметана 20% 480гр',10000,'дона',1,NULL,NULL,7,0),

(39,'Сметана 20% 240гр',5000,'дона',1,NULL,NULL,7,0),

(40,'Катик 960мл',3350,'дона',1,NULL,NULL,7,0),

(41,'Катик 3,2% 960мл',3750,'дона',1,NULL,NULL,7,0),

(42,'Ряженка 3,2% 960мл',3750,'дона',1,NULL,NULL,7,0),

(43,'Кефир 3,2% 960мл',3750,'дона',1,NULL,NULL,7,0),

(44,'Айрон 960мл',3350,'дона',1,NULL,NULL,7,0),

(45,'Катик 480мл',2250,'дона',1,NULL,NULL,7,0),

(46,'Катик 3,2% 480мл',2400,'дона',1,NULL,NULL,7,0),

(47,'Ряженка 3,2% 480мл',2400,'дона',1,NULL,NULL,7,0),

(48,'Кефир 3,2% 480мл',2400,'дона',1,NULL,NULL,7,0),

(49,NULL,NULL,'дона',1,NULL,NULL,7,0),

(50,'Айрон 480мл',2400,'дона',1,NULL,NULL,7,0),

(51,'Айрон',2500,'литр',1,NULL,NULL,7,0),

(52,'Чакида',7500,'кг',1,NULL,NULL,7,0),

(53,'Чакида 500гр',4000,'дона',1,NULL,NULL,7,0),

(54,'Сариег 240гр',10000,'200гр',1,NULL,NULL,7,0),

(55,'Сариег литр',50000,'кг',1,NULL,NULL,7,0),

(56,'Сырные палочки 40гр',2000,'дона',1,NULL,NULL,7,0);

/*Table structure for table `product_get_history` */

DROP TABLE IF EXISTS `product_get_history`;

CREATE TABLE `product_get_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `productId` int(11) DEFAULT NULL,
  `quantity` decimal(10,0) DEFAULT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `product_get_history` */

/*Table structure for table `product_type` */

DROP TABLE IF EXISTS `product_type`;

CREATE TABLE `product_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` text COLLATE utf8_bin,
  `deleted` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `product_type` */

insert  into `product_type`(`id`,`name`,`deleted`) values 

(5,'Каттик пишлок',NULL),

(6,'Эритилган пишлок',NULL),

(7,'Сут махсулотлари',NULL);

/*Table structure for table `sales` */

DROP TABLE IF EXISTS `sales`;

CREATE TABLE `sales` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `quantity` double DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `transaction_id` int(11) DEFAULT NULL,
  `client_id` int(11) DEFAULT NULL,
  `payed` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `product_id` (`product_id`),
  KEY `transaction_id` (`transaction_id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `sales` */

/*Table structure for table `transaction` */

DROP TABLE IF EXISTS `transaction`;

CREATE TABLE `transaction` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cash` int(11) DEFAULT NULL,
  `card` int(11) DEFAULT NULL,
  `sale_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `transaction` */

/*Table structure for table `typedimension` */

DROP TABLE IF EXISTS `typedimension`;

CREATE TABLE `typedimension` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dimensionName` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `typedimension` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
