/*
SQLyog Community v11.51 (64 bit)
MySQL - 5.7.14 : Database - nst
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`nst` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `nst`;

/*Table structure for table `aktivnost` */

DROP TABLE IF EXISTS `aktivnost`;

CREATE TABLE `aktivnost` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `Naziv` varchar(100) DEFAULT NULL,
  `Oznaka` varchar(100) DEFAULT NULL,
  `Opis` varchar(100) DEFAULT NULL,
  `IdProcesa` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `IdProcesa` (`IdProcesa`),
  CONSTRAINT `aktivnost_ibfk_1` FOREIGN KEY (`IdProcesa`) REFERENCES `proces` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

/*Data for the table `aktivnost` */

insert  into `aktivnost`(`Id`,`Naziv`,`Oznaka`,`Opis`,`IdProcesa`) values (1,'activity1','ASD','activity 1 is used for....',2),(2,'activity 2','qwe','activity 2 is used for',2),(3,'activity 2.1.1','act','description',4),(8,'activity','activity','Activity is for...',2),(12,'1111a','1111a','1111activity',100),(13,'1112a','1112a','1112activity',100),(14,'act1','act1','act1 desc',105);

/*Table structure for table `dokument` */

DROP TABLE IF EXISTS `dokument`;

CREATE TABLE `dokument` (
  `IdDokumenta` bigint(20) NOT NULL AUTO_INCREMENT,
  `Naziv` varchar(100) DEFAULT NULL,
  `DatumKreiranja` date DEFAULT NULL,
  `Napomena` varchar(1000) DEFAULT NULL,
  `IdTipaDokumenta` bigint(20) DEFAULT NULL,
  `IdAktivnosti` bigint(20) DEFAULT NULL,
  `Fajl` text,
  PRIMARY KEY (`IdDokumenta`),
  KEY `IdTipaDokumenta` (`IdTipaDokumenta`),
  KEY `IdAktivnosti` (`IdAktivnosti`),
  CONSTRAINT `dokument_ibfk_1` FOREIGN KEY (`IdTipaDokumenta`) REFERENCES `tipdokumenta` (`IdTipaDokumenta`),
  CONSTRAINT `dokument_ibfk_2` FOREIGN KEY (`IdAktivnosti`) REFERENCES `aktivnost` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

/*Data for the table `dokument` */

insert  into `dokument`(`IdDokumenta`,`Naziv`,`DatumKreiranja`,`Napomena`,`IdTipaDokumenta`,`IdAktivnosti`,`Fajl`) values (7,'doc1','2017-04-04','doc1',1,12,'C:\\wamp\\www\\DMS\\files\\pkontroler.txt'),(8,'doc2','2017-04-04','d2',1,12,'C:\\wamp\\www\\DMS\\files\\WEBEX SESSION.pdf'),(16,'b4','2017-04-05','b4',1,14,'C:\\wamp\\www\\DMS\\files\\addico hr.txt');

/*Table structure for table `podsistem` */

DROP TABLE IF EXISTS `podsistem`;

CREATE TABLE `podsistem` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `Naziv` varchar(100) DEFAULT NULL,
  `Oznaka` varchar(100) DEFAULT NULL,
  `Opis` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

/*Data for the table `podsistem` */

insert  into `podsistem`(`Id`,`Naziv`,`Oznaka`,`Opis`) values (1,'SubsystemTest1','TST1','Test 1'),(2,'SubsystemTest2','TST2','Test 2'),(13,'SubsystemTest3','asd','Default description'),(14,'Subsystem3','Sub3','For checking data about marketing'),(15,'SubsystemTest4','ST4','Sybsystem test 4 description edit data ');

/*Table structure for table `proces` */

DROP TABLE IF EXISTS `proces`;

CREATE TABLE `proces` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `Naziv` varchar(100) DEFAULT NULL,
  `Oznaka` varchar(100) DEFAULT NULL,
  `Opis` varchar(100) DEFAULT NULL,
  `Nivo` bigint(20) DEFAULT NULL,
  `IdNadProcesa` bigint(20) DEFAULT NULL,
  `IdPodsistema` bigint(20) DEFAULT NULL,
  `Primitivan` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `IdPodsistema` (`IdPodsistema`),
  KEY `IdNadProcesa` (`IdNadProcesa`),
  CONSTRAINT `proces_ibfk_1 ` FOREIGN KEY (`IdPodsistema`) REFERENCES `podsistem` (`Id`),
  CONSTRAINT `proces_ibfk_2` FOREIGN KEY (`IdNadProcesa`) REFERENCES `proces` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=106 DEFAULT CHARSET=latin1;

/*Data for the table `proces` */

insert  into `proces`(`Id`,`Naziv`,`Oznaka`,`Opis`,`Nivo`,`IdNadProcesa`,`IdPodsistema`,`Primitivan`) values (1,'proces1.1','p1','opis procesa 1',1,NULL,1,0),(2,'subproces1.1','asd','subprocesid.1.1',2,1,1,1),(3,'proces2','p2','p2',1,NULL,1,0),(4,'Subprocess2.1','SBPR1','Process is for...',2,3,1,1),(10,'root','123','123asd',1,NULL,1,0),(11,'dete root','dr','swte',1,NULL,1,0),(12,'dr','dr','dr',1,NULL,1,0),(13,'1','1','1',2,10,1,1),(97,'1','1','1 root',1,NULL,1,0),(98,'11','11','11 second kid',2,97,1,0),(99,'111','111','111 almost primitive',3,98,1,0),(100,'1111','1111','1111 primitive',4,99,1,1),(101,'root1','r1','root1 description',1,NULL,1,0),(102,'root11','r11','r11 description',2,101,1,0),(103,'root111','r111','r111',3,102,1,0),(104,'r1111','r1111','r1111',4,103,1,0),(105,'r11111','r11111','r11111',5,104,1,1);

/*Table structure for table `rola` */

DROP TABLE IF EXISTS `rola`;

CREATE TABLE `rola` (
  `IdRole` bigint(20) NOT NULL,
  `NazivRole` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`IdRole`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `rola` */

insert  into `rola`(`IdRole`,`NazivRole`) values (1,'SUPERADMIN'),(2,'ADMIN'),(3,'USER');

/*Table structure for table `tipdokumenta` */

DROP TABLE IF EXISTS `tipdokumenta`;

CREATE TABLE `tipdokumenta` (
  `IdTipaDokumenta` bigint(20) NOT NULL AUTO_INCREMENT,
  `NazivTipa` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`IdTipaDokumenta`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `tipdokumenta` */

insert  into `tipdokumenta`(`IdTipaDokumenta`,`NazivTipa`) values (1,'Narudzbenica'),(2,'Nalog za placanje'),(4,'Biografija');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `username` varchar(20) NOT NULL,
  `password` varchar(20) DEFAULT NULL,
  `ime` varchar(20) DEFAULT NULL,
  `prezime` varchar(20) DEFAULT NULL,
  `IdPodsistema` bigint(20) DEFAULT NULL,
  `IdRole` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`username`),
  KEY `IdPodsistema` (`IdPodsistema`),
  KEY `IdRole` (`IdRole`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`IdPodsistema`) REFERENCES `podsistem` (`Id`),
  CONSTRAINT `user_ibfk_2` FOREIGN KEY (`IdRole`) REFERENCES `rola` (`IdRole`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `user` */

insert  into `user`(`username`,`password`,`ime`,`prezime`,`IdPodsistema`,`IdRole`) values ('admin','admin','admin','admin',1,2),('dules','dulesavic','Dusan','Dusan',2,2),('superadmin','superadmin','super','admin',NULL,1),('user','user','user','user',1,3);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
