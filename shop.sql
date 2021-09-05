/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.23 : Database - shopproject
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`shopproject` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `shopproject`;

/*Table structure for table `client` */

DROP TABLE IF EXISTS `client`;

CREATE TABLE `client` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) DEFAULT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `contactNo` varchar(255) DEFAULT NULL,
  `bank` varchar(255) DEFAULT NULL,
  `company` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `client` */

insert  into `client`(`id`,`first_name`,`middle_name`,`last_name`,`address`,`contactNo`,`bank`,`company`) values (1,'John','carl','Mathew','Los Angles','44521586','SBP','Iron'),(2,'Maggie','Simth','Soga','Argentina','41562831','GPP','Papers'),(3,'Yadav','Munesh','Tripalli','India Bombay','41586354','ISL','INTE');

/*Table structure for table `loan_paid` */

DROP TABLE IF EXISTS `loan_paid`;

CREATE TABLE `loan_paid` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `payment_type` varchar(255) DEFAULT NULL,
  `amount_paid` varchar(255) DEFAULT NULL,
  `chequeNum` varchar(255) DEFAULT NULL,
  `date_paid` date DEFAULT NULL,
  `payment_id` int(11) DEFAULT NULL,
  `balance` int(8) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `payment_id` (`payment_id`),
  CONSTRAINT `loan_paid_ibfk_1` FOREIGN KEY (`payment_id`) REFERENCES `payment` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

/*Data for the table `loan_paid` */

insert  into `loan_paid`(`id`,`payment_type`,`amount_paid`,`chequeNum`,`date_paid`,`payment_id`,`balance`) values (1,'Cash','10',' ','2021-01-19',1,80),(2,'Cash','40',' ','2021-01-06',1,20),(3,'Cash','10',' ','2021-01-06',1,45),(4,'Cash','10',' ','2021-01-06',1,50),(5,'Cash','20',' ','2021-01-13',2,44),(6,'Cash','5',' ','2021-01-13',2,67),(7,'Cash','5',' ','2021-01-06',5,34),(8,'Cash','5',' ','2021-01-06',5,85),(9,'Cash','5',' ','2021-01-06',5,104),(10,'Cash','10.0',' ','2021-01-20',5,33),(11,'Cash','40.0',' ','2021-01-20',4,22),(12,'Cash','90.0',' ','2021-02-11',4,10),(13,'Cash','5.0',' ','2021-09-15',1,NULL);

/*Table structure for table `login` */

DROP TABLE IF EXISTS `login`;

CREATE TABLE `login` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `login` */

insert  into `login`(`id`,`username`,`password`) values (1,'admin','admin1234');

/*Table structure for table `payment` */

DROP TABLE IF EXISTS `payment`;

CREATE TABLE `payment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `payment_type` varchar(255) DEFAULT NULL,
  `customer_id` varchar(255) DEFAULT NULL,
  `Price_per_Product` double(6,2) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `quantity` int(7) DEFAULT NULL,
  `date_approved` varchar(255) DEFAULT NULL,
  `duration` int(6) DEFAULT NULL,
  `pay_num` varchar(255) DEFAULT NULL,
  `total` double(6,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `payment_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `payment` */

insert  into `payment`(`id`,`payment_type`,`customer_id`,`Price_per_Product`,`product_id`,`quantity`,`date_approved`,`duration`,`pay_num`,`total`) values (1,'Loan','2',40.00,1,4,'08-11-2020',1,'21234567',155.00),(2,'Cash','1',30.00,4,2,'04-11-2020',1,'',60.00),(3,'Cash','2',40.00,1,4,'08-12-2020',1,'',160.00),(4,'Cash','2',50.00,4,2,'01-11-2020',1,'',100.00),(5,'Loan','1',50.00,1,2,'08-10-2020',1,'87659087',10.00);

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_no` varchar(255) DEFAULT NULL,
  `brand` varchar(255) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `store_quantity` int(6) DEFAULT NULL,
  `warehouse_quantity` int(6) DEFAULT NULL,
  `capital` double(8,3) DEFAULT NULL,
  `price` double(8,3) DEFAULT NULL,
  `supplier` varchar(255) DEFAULT NULL,
  `product_code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `product` */

insert  into `product`(`id`,`product_no`,`brand`,`category`,`description`,`store_quantity`,`warehouse_quantity`,`capital`,`price`,`supplier`,`product_code`) values (1,'11','KFC','Food','Sweet Foods',100,200,10000.000,20.000,'John','231'),(2,'12','OPTP','Food','Cripy chips',400,800,6000.000,25.000,'Breck','232'),(4,'1132','Sting','Beverage','Sweet Drink',192,100,4000.000,25.000,'Sting Maker','201');

/*Table structure for table `stock` */

DROP TABLE IF EXISTS `stock`;

CREATE TABLE `stock` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_code` varchar(255) DEFAULT NULL,
  `arrival_date` date DEFAULT NULL,
  `transaction` int(11) DEFAULT NULL,
  `price` double(6,2) DEFAULT NULL,
  `store_quantity` int(6) DEFAULT NULL,
  `warehouse_quantity` int(6) DEFAULT NULL,
  `sales_invoiceNo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `stock` */

insert  into `stock`(`id`,`product_code`,`arrival_date`,`transaction`,`price`,`store_quantity`,`warehouse_quantity`,`sales_invoiceNo`) values (1,'201','2020-06-11',2000,20.00,502,998,'1314'),(4,'231','2020-11-09',100,20.00,600,900,'1315'),(5,'233','2020-12-09',1500,25.00,700,1200,'1316');

/*Table structure for table `transferred_stock` */

DROP TABLE IF EXISTS `transferred_stock`;

CREATE TABLE `transferred_stock` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stock_id` int(11) DEFAULT NULL,
  `store_name` varchar(255) DEFAULT NULL,
  `quantity` int(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `transferred_stock` */

insert  into `transferred_stock`(`id`,`stock_id`,`store_name`,`quantity`) values (1,5,'A Store',400),(2,5,'A Store',100),(3,1,'anv',2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
