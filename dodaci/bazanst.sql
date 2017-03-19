/*
SQLyog Community v11.51 (64 bit)
MySQL - 5.7.11 : Database - nst
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
  `Id` bigint(20) NOT NULL,
  `Naziv` varchar(100) DEFAULT NULL,
  `Oznaka` varchar(100) DEFAULT NULL,
  `Opis` varchar(100) DEFAULT NULL,
  `IdProcesa` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `IdProcesa` (`IdProcesa`),
  CONSTRAINT `aktivnost_ibfk_1` FOREIGN KEY (`IdProcesa`) REFERENCES `proces` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `aktivnost` */

insert  into `aktivnost`(`Id`,`Naziv`,`Oznaka`,`Opis`,`IdProcesa`) values (1,'asdd','ASD','asd',2),(2,'qwer','qwe','qweer',2);

/*Table structure for table `dokument` */

DROP TABLE IF EXISTS `dokument`;

CREATE TABLE `dokument` (
  `IdDokumenta` bigint(20) NOT NULL,
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
  CONSTRAINT `dokument_ibfk_2` FOREIGN KEY (`IdAktivnosti`) REFERENCES `aktivnost` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `dokument` */

/*Table structure for table `podsistem` */

DROP TABLE IF EXISTS `podsistem`;

CREATE TABLE `podsistem` (
  `Id` bigint(20) NOT NULL,
  `Naziv` varchar(100) DEFAULT NULL,
  `Oznaka` varchar(100) DEFAULT NULL,
  `Opis` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `podsistem` */

insert  into `podsistem`(`Id`,`Naziv`,`Oznaka`,`Opis`) values (1,'SubsystemTest1','TST1','Test 1'),(2,'SubsystemTest2','TST2','policajac sa petlovog brda'),(3,'Subsystem3','SBS3','subssubs'),(4,'new subsystem','NSS','new subsystem test'),(5,'reik','RK','creo en ti'),(6,'podsistemn','pds','sjdknd');

/*Table structure for table `proces` */

DROP TABLE IF EXISTS `proces`;

CREATE TABLE `proces` (
  `Id` bigint(20) NOT NULL,
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
  CONSTRAINT `proces_ibfk_1` FOREIGN KEY (`IdPodsistema`) REFERENCES `podsistem` (`Id`),
  CONSTRAINT `proces_ibfk_2` FOREIGN KEY (`IdNadProcesa`) REFERENCES `proces` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `proces` */

insert  into `proces`(`Id`,`Naziv`,`Oznaka`,`Opis`,`Nivo`,`IdNadProcesa`,`IdPodsistema`,`Primitivan`) values (1,'proces1','p1','opis procesa 1',1,NULL,2,0),(2,'asd','asd','asdd',2,1,2,1),(3,'proces2','p2','p2',1,NULL,2,0),(4,'Subprocess1','SBPR1','Process is for...',2,3,2,1),(5,'test1','tst1','Process is for...',2,3,2,0),(6,'test2','tst2','Process is for...',2,1,2,1);

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
  `IdTipaDokumenta` bigint(20) NOT NULL,
  `NazivTipa` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`IdTipaDokumenta`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tipdokumenta` */

insert  into `tipdokumenta`(`IdTipaDokumenta`,`NazivTipa`) values (1,'Narudzbenica'),(2,'Nalog za placanje');

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

insert  into `user`(`username`,`password`,`ime`,`prezime`,`IdPodsistema`,`IdRole`) values ('admin','admin','admin','admin',1,2),('asd','asdasdasd','asd','asd',1,3),('cehm','mirko2017','Mirko','Ceh',6,2),('dules','duledule','Dusan','Savic',5,2),('lazak','lazak','Laza','Kostic',3,2),('mau','maumaumau','mau','mau',4,3),('milicap','milicamilica','Milica','Pavlovic',1,3),('mmilic','milosmilic','Milos','Milic',1,3),('necaceh','lepoticaizver','Nevena','Ceh',2,3),('superadmin','superadmin','super','admin',NULL,1),('user','user','user','user',1,3),('vericac','necaboka','Verica','Ceh',5,2),('vulepejovic','Vulecar','Vukasin','Pejovic',4,3),('yoya91','lavcipele','Jovana','Milosavljevic',1,3),('zorank','24minuta','Zoran','Kesic',4,2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;