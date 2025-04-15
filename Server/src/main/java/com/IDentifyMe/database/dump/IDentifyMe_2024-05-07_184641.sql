-- MariaDB dump 10.19-11.3.2-MariaDB, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: IDentifyMe
-- ------------------------------------------------------
-- Server version	8.3.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Documents`
--

DROP TABLE IF EXISTS `Documents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Documents` (
  `DocumentID` int NOT NULL AUTO_INCREMENT,
  `RequestID` int DEFAULT NULL,
  `DocumentType` varchar(50) DEFAULT NULL,
  `DocumentPath` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`DocumentID`),
  KEY `RequestID` (`RequestID`),
  CONSTRAINT `Documents_ibfk_1` FOREIGN KEY (`RequestID`) REFERENCES `Requests` (`RequestID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Documents`
--

/*!40000 ALTER TABLE `Documents` DISABLE KEYS */;
INSERT INTO `Documents` VALUES
(1,1,'Transcript','/documents/transcript_stu001.pdf'),
(2,2,'ID Copy','/documents/id_copy_stu002.pdf'),
(3,3,'Receipt','/documents/receipt_stu003.pdf'),
(4,4,'Transcript','/documents/transcript_stu004.pdf'),
(5,5,'ID Copy','/documents/id_copy_stu005.pdf'),
(6,6,'Receipt','/documents/receipt_stu006.pdf'),
(7,7,'Transcript','/documents/transcript_stu007.pdf'),
(8,8,'ID Copy','/documents/id_copy_stu008.pdf'),
(9,9,'Receipt','/documents/receipt_stu009.pdf'),
(10,10,'Transcript','/documents/transcript_stu010.pdf');
/*!40000 ALTER TABLE `Documents` ENABLE KEYS */;

--
-- Table structure for table `FinanceDepartment`
--

DROP TABLE IF EXISTS `FinanceDepartment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `FinanceDepartment` (
  `EmployeeID` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) DEFAULT NULL,
  `Email` varchar(100) DEFAULT NULL,
  `Password` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`EmployeeID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FinanceDepartment`
--

/*!40000 ALTER TABLE `FinanceDepartment` DISABLE KEYS */;
INSERT INTO `FinanceDepartment` VALUES
(1,'John Smith','john.smith@finance.com','financePass123'),
(2,'Emily Johnson','emily.johnson@finance.com','emilyFinance456'),
(3,'David Brown','david.brown@finance.com','davidPass789'),
(4,'Emma Taylor','emma.taylor@finance.com','emmaFinance123'),
(5,'Daniel Martinez','daniel.martinez@finance.com','danielFinance456'),
(6,'Olivia Wilson','olivia.wilson@finance.com','oliviaFinance789'),
(7,'William Garcia','william.garcia@finance.com','williamFinance123'),
(8,'Sophia Hernandez','sophia.hernandez@finance.com','sophiaFinance456'),
(9,'James Rodriguez','james.rodriguez@finance.com','jamesFinance789'),
(10,'Alexis Lopez','alexis.lopez@finance.com','alexisFinance123');
/*!40000 ALTER TABLE `FinanceDepartment` ENABLE KEYS */;

--
-- Table structure for table `IDReplacementDepartment`
--

DROP TABLE IF EXISTS `IDReplacementDepartment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `IDReplacementDepartment` (
  `EmployeeID` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) DEFAULT NULL,
  `Email` varchar(100) DEFAULT NULL,
  `Password` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`EmployeeID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `IDReplacementDepartment`
--

/*!40000 ALTER TABLE `IDReplacementDepartment` DISABLE KEYS */;
INSERT INTO `IDReplacementDepartment` VALUES
(1,'Alex Brown','alex.brown@idreplacement.com','idPass789'),
(2,'Sophia Wilson','sophia.wilson@idreplacement.com','sophiaID123'),
(3,'Ethan Thomas','ethan.thomas@idreplacement.com','ethanPass456'),
(4,'Madison Hall','madison.hall@idreplacement.com','madisonID789'),
(5,'Ava Young','ava.young@idreplacement.com','avaPass123'),
(6,'Noah Clark','noah.clark@idreplacement.com','noahID456'),
(7,'Isabella King','isabella.king@idreplacement.com','isabellaPass789'),
(8,'Mason Walker','mason.walker@idreplacement.com','masonID123'),
(9,'Sophia Hall','sophia.hall@idreplacement.com','sophiaPass456'),
(10,'Jackson Adams','jackson.adams@idreplacement.com','jacksonID789');
/*!40000 ALTER TABLE `IDReplacementDepartment` ENABLE KEYS */;

--
-- Table structure for table `PaymentDetails`
--

DROP TABLE IF EXISTS `PaymentDetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PaymentDetails` (
  `PaymentID` int NOT NULL AUTO_INCREMENT,
  `RequestID` int DEFAULT NULL,
  `Amount` decimal(10,2) DEFAULT NULL,
  `ReceiptNumber` varchar(50) DEFAULT NULL,
  `PaymentDate` date DEFAULT NULL,
  `PaymentVerificationDate` date DEFAULT NULL,
  `BankName` varchar(50) DEFAULT NULL,
  `status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`PaymentID`),
  KEY `RequestID` (`RequestID`),
  CONSTRAINT `PaymentDetails_ibfk_1` FOREIGN KEY (`RequestID`) REFERENCES `Requests` (`RequestID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PaymentDetails`
--

/*!40000 ALTER TABLE `PaymentDetails` DISABLE KEYS */;
INSERT INTO `PaymentDetails` VALUES
(1,3,150.00,'REC123456','2024-05-01','2024-05-02','ABC Bank','Verified'),
(2,6,200.00,'REC789012','2024-05-02','2024-05-03','XYZ Bank','Verified'),
(3,9,100.00,'REC345678','2024-05-03','2024-05-04','PQR Bank','Verified');
/*!40000 ALTER TABLE `PaymentDetails` ENABLE KEYS */;

--
-- Table structure for table `Requests`
--

DROP TABLE IF EXISTS `Requests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Requests` (
  `RequestID` int NOT NULL,
  `StudentID` varchar(20) DEFAULT NULL,
  `RequestType` varchar(50) DEFAULT NULL,
  `RequestDate` date DEFAULT NULL,
  `Status` varchar(50) DEFAULT NULL,
  `AppointmentDate` date DEFAULT NULL,
  PRIMARY KEY (`RequestID`),
  KEY `StudentID` (`StudentID`),
  CONSTRAINT `Requests_ibfk_1` FOREIGN KEY (`StudentID`) REFERENCES `Students` (`StudentID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Requests`
--

/*!40000 ALTER TABLE `Requests` DISABLE KEYS */;
INSERT INTO `Requests` VALUES
(1,'stu001','Transcript Request','2024-04-25','Pending','2024-05-10'),
(2,'stu002','ID Replacement','2024-04-26','In Progress','2024-05-15'),
(3,'stu003','Fee Payment','2024-04-27','Completed','2024-05-05'),
(4,'stu004','Transcript Request','2024-04-28','Pending','2024-05-12'),
(5,'stu005','ID Replacement','2024-04-29','In Progress','2024-05-20'),
(6,'stu006','Fee Payment','2024-04-30','Completed','2024-05-08'),
(7,'stu007','Transcript Request','2024-05-01','Pending','2024-05-14'),
(8,'stu008','ID Replacement','2024-05-02','In Progress','2024-05-18'),
(9,'stu009','Fee Payment','2024-05-03','Completed','2024-05-06'),
(10,'stu010','Transcript Request','2024-05-04','Pending','2024-05-16');
/*!40000 ALTER TABLE `Requests` ENABLE KEYS */;

--
-- Table structure for table `Students`
--

DROP TABLE IF EXISTS `Students`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Students` (
  `StudentID` varchar(20) NOT NULL,
  `Name` varchar(100) DEFAULT NULL,
  `Email` varchar(100) DEFAULT NULL,
  `Password` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`StudentID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Students`
--

/*!40000 ALTER TABLE `Students` DISABLE KEYS */;
INSERT INTO `Students` VALUES
('stu001','John Doe','john.doe@example.com','password123'),
('stu002','Jane Smith','jane.smith@example.com','secret456'),
('stu003','Michael Johnson','michael.johnson@example.com','pass123'),
('stu004','Emily Brown','emily.brown@example.com','emily123'),
('stu005','William Taylor','william.taylor@example.com','willPass789'),
('stu006','Emma Martinez','emma.martinez@example.com','emmaPass456'),
('stu007','Daniel Wilson','daniel.wilson@example.com','danielPass321'),
('stu008','Olivia Garcia','olivia.garcia@example.com','oliviaPass789'),
('stu009','James Hernandez','james.hernandez@example.com','jamesPass123'),
('stu010','Sophia Rodriguez','sophia.rodriguez@example.com','sophiaPass456'),
('user','user','user@user.com','user');
/*!40000 ALTER TABLE `Students` ENABLE KEYS */;

--
-- Dumping routines for database 'IDentifyMe'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-07 18:46:52
