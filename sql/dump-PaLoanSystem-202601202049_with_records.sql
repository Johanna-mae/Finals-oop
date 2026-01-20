/*M!999999\- enable the sandbox mode */ 
-- MariaDB dump 10.19-12.1.2-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: PaLoanSystem
-- ------------------------------------------------------
-- Server version	12.1.2-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*M!100616 SET @OLD_NOTE_VERBOSITY=@@NOTE_VERBOSITY, NOTE_VERBOSITY=0 */;

--
-- Table structure for table `Client`
--

DROP TABLE IF EXISTS `Client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `Client` (
  `client_id` int(11) NOT NULL AUTO_INCREMENT,
  `client_reference_number` varchar(12) DEFAULT NULL,
  `first_name` varchar(75) NOT NULL,
  `last_name` varchar(75) NOT NULL,
  `middle_name` varchar(75) DEFAULT NULL,
  `phone_number` varchar(15) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `date_of_birth` date NOT NULL,
  `address_line` varchar(100) NOT NULL,
  `barangay` varchar(75) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `province` varchar(50) DEFAULT NULL,
  `zip_code` int(4) DEFAULT NULL,
  `valid_id_type` enum('UMID','SSS','PhilHealth','Driver_License','Passport') DEFAULT NULL,
  `valid_id_number` varchar(100) DEFAULT NULL,
  `tin` varchar(100) DEFAULT NULL,
  `civil_status` enum('Single','Married','Widowed','Separated') NOT NULL,
  `employment_status` enum('Employed','Self-Employed','Unemployed','Student','Retired') NOT NULL,
  `employer_name` varchar(100) DEFAULT NULL,
  `monthly_income` enum('Below ₱10,000','₱10,000-₱20,000','₱20,001-₱30,000','₱30,001-₱40,000','₱40,001-₱50,000','₱50,001-₱100,000','Above ₱100,000') NOT NULL,
  `date_registered` timestamp NOT NULL,
  `account_status` enum('Active','Suspended','Closed') NOT NULL,
  PRIMARY KEY (`client_id`),
  UNIQUE KEY `tin_unique` (`tin`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Client`
--

LOCK TABLES `Client` WRITE;
/*!40000 ALTER TABLE `Client` DISABLE KEYS */;
set autocommit=0;
INSERT INTO `Client` VALUES
(1,'CLI-85584','Vich Andrei','Cortez','Tekistekis','09605445134','vichandreicortez49@gmail.com','2006-09-04','B21 L48 38th, The Legian 2C Subd.','Carsadang Bago 1','Imus','Cavite',NULL,'UMID','0001-0000002-3',NULL,'Single','Student','','Below ₱10,000','2026-01-19 15:04:13','Active'),
(3,'CLI-63716','Mark John','Fernandez','G','09111111111','markjohnfernandez@gmail.com','2005-12-30','10th','saanmansabacoor','Bacoor','Cavite',NULL,'Passport','AC111111111',NULL,'Single','Student','','Below ₱10,000','2026-01-20 01:21:39','Active');
/*!40000 ALTER TABLE `Client` ENABLE KEYS */;
UNLOCK TABLES;
commit;

--
-- Table structure for table `Document`
--

DROP TABLE IF EXISTS `Document`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `Document` (
  `document_id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `document_reference_number` varchar(12) DEFAULT NULL,
  `application_id` int(11) unsigned zerofill DEFAULT NULL,
  `document_type` enum('Valid ID','Proof of Income','ITR','Proof of Billing','Employment Certificate','Business Permit','Collateral Documents') NOT NULL,
  `file_path` varchar(255) NOT NULL,
  `upload_date` timestamp NOT NULL,
  `verified_by_employee_id` int(11) unsigned zerofill DEFAULT NULL,
  `verification_status` enum('For Approval','Verified','Rejected') NOT NULL,
  PRIMARY KEY (`document_id`),
  KEY `Document_Loan_Application_FK` (`application_id`),
  KEY `Document_Employee_FK` (`verified_by_employee_id`),
  CONSTRAINT `Document_Employee_FK` FOREIGN KEY (`verified_by_employee_id`) REFERENCES `Employee` (`employee_id`),
  CONSTRAINT `Document_Loan_Application_FK` FOREIGN KEY (`application_id`) REFERENCES `Loan_Application` (`application_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Document`
--

LOCK TABLES `Document` WRITE;
/*!40000 ALTER TABLE `Document` DISABLE KEYS */;
set autocommit=0;
INSERT INTO `Document` VALUES
(00000000003,'DOC-0178810',00000000003,'Valid ID','/home/vichan/PaLoanSystem/Documents/LAP-3-bg-loan-image.png','2026-01-20 02:51:05',NULL,'For Approval'),
(00000000004,'DOC-0223221',00000000003,'Proof of Income','/home/vichan/PaLoanSystem/Documents/LAP-3-MariaDB_troubleshooting_guide_claude.ai.md','2026-01-20 02:51:05',NULL,'For Approval'),
(00000000005,'DOC-0716859',00000000004,'Collateral Documents','/home/vichan/PaLoanSystem/Documents/LAP-4-systemd-blame-output.txt','2026-01-20 03:04:29',NULL,'For Approval'),
(00000000006,'DOC-0591994',00000000004,'Employment Certificate','/home/vichan/PaLoanSystem/Documents/LAP-4-MariaDB_troubleshooting_guide_claude.ai.md','2026-01-20 03:04:29',NULL,'For Approval');
/*!40000 ALTER TABLE `Document` ENABLE KEYS */;
UNLOCK TABLES;
commit;

--
-- Table structure for table `Employee`
--

DROP TABLE IF EXISTS `Employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `Employee` (
  `employee_id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `employee_reference_number` varchar(12) DEFAULT NULL,
  `username` varchar(16) DEFAULT NULL,
  `first_name` varchar(64) DEFAULT NULL,
  `last_name` varchar(64) DEFAULT NULL,
  `role` enum('Loan_Officer','Manager','Cashier','Admin') DEFAULT NULL,
  `email` varchar(64) DEFAULT NULL,
  `date_hired` date DEFAULT NULL,
  `is_active` tinyint(1) DEFAULT NULL,
  `password_hash` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`employee_id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `Employee_ref_no_UNIQUE` (`employee_reference_number`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Employee`
--

LOCK TABLES `Employee` WRITE;
/*!40000 ALTER TABLE `Employee` DISABLE KEYS */;
set autocommit=0;
INSERT INTO `Employee` VALUES
(00000000001,NULL,'admin',NULL,NULL,'Admin',NULL,NULL,1,'$2a$10$BJtb0b0ESn4WV6O.ZjZuFO0YpeW.qOyf9GWeJs5pnDzOmn4JOar7.'),
(00000000010,'EMP-21220','vccortez49','Vich Andrei','Cortez','Admin','vccortez49@gmail.com','2026-01-20',1,'$2a$12$Wds/H9bJUmM8WXk4mS/Ez.xjxVEvUdKF0BDF3ZDHOsYjiFSNBGhbu'),
(00000000011,'EMP-69410','jhtomadong41','Johanna Mae','Tomadong','Manager','jhtomadong41@gmail.com','2026-01-20',1,'$2a$12$Tya9sWhSbmNdF02hL7MrK.Glmd.VubEzjGFmeqomqXymr0SA0.ALe'),
(00000000012,'EMP-77989','mjfernandez41','Mark John','Fernandez','Loan_Officer','mjfernandez41','2026-01-20',1,'$2a$12$FsRBWz8gG2TkLQZ9XC/l4Ok.LV2YF3SH1om22wHzByJu79JJTWVC.'),
(00000000013,'EMP-83172','jmebale86','Jordan Manuel','Ebale','Cashier','jmebale86','2026-01-20',1,'$2a$12$tSZ6F9NLToBohBTG23/vMepoyGeFCY6EgpqlyWKIuaHm9CfHydflW'),
(00000000014,'EMP-21825','test1','fntest','lntest','Cashier','test@yahoo.com','2026-01-20',0,'$2a$12$WCItFyKr3m.8M/jTgwgrI.3Z775GB58E3ESU32Xk3eBnSi61n/Y4G');
/*!40000 ALTER TABLE `Employee` ENABLE KEYS */;
UNLOCK TABLES;
commit;

--
-- Table structure for table `Loan`
--

DROP TABLE IF EXISTS `Loan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `Loan` (
  `loan_id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `loan_reference_number` varchar(12) DEFAULT NULL,
  `principal_amount` decimal(12,2) NOT NULL,
  `interest_rate` decimal(12,2) NOT NULL,
  `terms_months` int(4) NOT NULL,
  `monthly_payment` decimal(12,2) NOT NULL COMMENT 'calculated',
  `total_interest` decimal(12,2) NOT NULL COMMENT 'calculated',
  `total_amount_payable` decimal(12,2) NOT NULL COMMENT 'calculated',
  `outstanding_balance` decimal(12,2) NOT NULL COMMENT 'calculated',
  `loan_start_date` date NOT NULL,
  `loan_end_date` date NOT NULL,
  `disbursement_date` date DEFAULT NULL,
  `status` enum('Active','Paid_Off','Defaulted','Restructured') NOT NULL,
  `created_date` date NOT NULL,
  `loan_type_id` int(11) DEFAULT NULL,
  `client_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`loan_id`),
  KEY `Loan_Loan_Type_FK` (`loan_type_id`),
  KEY `Loan_Client_FK` (`client_id`),
  CONSTRAINT `Loan_Client_FK` FOREIGN KEY (`client_id`) REFERENCES `Client` (`client_id`),
  CONSTRAINT `Loan_Loan_Type_FK` FOREIGN KEY (`loan_type_id`) REFERENCES `Loan_Type` (`loan_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Loan`
--

LOCK TABLES `Loan` WRITE;
/*!40000 ALTER TABLE `Loan` DISABLE KEYS */;
set autocommit=0;
INSERT INTO `Loan` VALUES
(00000000001,'LON-AT-30475',150000.00,12.00,12,14000.00,18000.00,168000.00,168000.00,'2026-01-20','2027-01-20',NULL,'Active','2026-01-20',2,3);
/*!40000 ALTER TABLE `Loan` ENABLE KEYS */;
UNLOCK TABLES;
commit;

--
-- Table structure for table `Loan_Application`
--

DROP TABLE IF EXISTS `Loan_Application`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `Loan_Application` (
  `application_id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `loan_application_reference_number` varchar(12) DEFAULT NULL,
  `requested_amount` decimal(12,2) NOT NULL,
  `requested_term_months` int(4) NOT NULL,
  `purpose` text NOT NULL,
  `application_date` timestamp NOT NULL,
  `status` enum('For Approval','Under Review','Approved','Rejected','Cancelled') NOT NULL,
  `review_date` date DEFAULT NULL,
  `rejection_reason` text DEFAULT NULL,
  `approval_notes` text DEFAULT NULL,
  `client_id` int(11) DEFAULT NULL,
  `loan_type_id` int(10) DEFAULT NULL,
  `employee_id` int(11) unsigned zerofill DEFAULT NULL,
  PRIMARY KEY (`application_id`),
  KEY `Loan_Application_Client_FK` (`client_id`),
  KEY `Loan_Application_Loan_Type_FK` (`loan_type_id`),
  KEY `Loan_Application_Employee_FK` (`employee_id`),
  CONSTRAINT `Loan_Application_Client_FK` FOREIGN KEY (`client_id`) REFERENCES `Client` (`client_id`),
  CONSTRAINT `Loan_Application_Employee_FK` FOREIGN KEY (`employee_id`) REFERENCES `Employee` (`employee_id`),
  CONSTRAINT `Loan_Application_Loan_Type_FK` FOREIGN KEY (`loan_type_id`) REFERENCES `Loan_Type` (`loan_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Loan_Application`
--

LOCK TABLES `Loan_Application` WRITE;
/*!40000 ALTER TABLE `Loan_Application` DISABLE KEYS */;
set autocommit=0;
INSERT INTO `Loan_Application` VALUES
(00000000003,'LNA-PS-50267',50000.00,36,'2 day trip to Japan','2026-01-20 02:51:04','Under Review',NULL,NULL,NULL,1,1,NULL),
(00000000004,'LNA-AT-63636',150000.00,12,'for new motorcycle','2026-01-20 03:04:27','Approved','2026-01-20','','vroom vroom go go',3,2,NULL);
/*!40000 ALTER TABLE `Loan_Application` ENABLE KEYS */;
UNLOCK TABLES;
commit;

--
-- Table structure for table `Loan_Type`
--

DROP TABLE IF EXISTS `Loan_Type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `Loan_Type` (
  `loan_type_id` int(10) NOT NULL AUTO_INCREMENT,
  `type_name` enum('Personal','Auto','Housing','Business','Education') NOT NULL,
  `description` text DEFAULT NULL,
  `annual_interest_rate` decimal(12,2) NOT NULL,
  `min_amount` decimal(12,2) NOT NULL,
  `max_amount` decimal(12,2) NOT NULL,
  `min_term_months` int(4) NOT NULL,
  `max_term_months` int(4) NOT NULL,
  `required_documents` text NOT NULL,
  `collateral_required` tinyint(1) NOT NULL,
  `is_Active` tinyint(1) NOT NULL,
  PRIMARY KEY (`loan_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Loan_Type`
--

LOCK TABLES `Loan_Type` WRITE;
/*!40000 ALTER TABLE `Loan_Type` DISABLE KEYS */;
set autocommit=0;
INSERT INTO `Loan_Type` VALUES
(1,'Personal',NULL,15.00,5000.00,500000.00,6,36,'None',0,1),
(2,'Auto',NULL,12.00,100000.00,3000000.00,12,60,'Application Form, Proof of Billing',1,1),
(3,'Housing',NULL,8.00,50000.00,10000000.00,60,300,'Governent IDs, Proof of income',1,1),
(4,'Business',NULL,18.00,50000.00,5000000.00,12,120,'Business Document',0,1),
(5,'Education',NULL,10.00,10000.00,500000.00,12,60,'Proof of Enrollment',0,1);
/*!40000 ALTER TABLE `Loan_Type` ENABLE KEYS */;
UNLOCK TABLES;
commit;

--
-- Table structure for table `Payment`
--

DROP TABLE IF EXISTS `Payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `Payment` (
  `payment_id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `payment_reference_number` varchar(12) DEFAULT NULL,
  `payment_date` timestamp NOT NULL,
  `due_date` date NOT NULL,
  `amount_paid` decimal(12,2) NOT NULL,
  `principal_paid` decimal(12,2) NOT NULL,
  `interest_paid` decimal(12,2) NOT NULL,
  `penalty_fee` decimal(12,2) NOT NULL,
  `payment_method` enum('Cash','Bank Transfer','Check','GCash','PayMaya') NOT NULL,
  `reference_number` varchar(100) DEFAULT NULL,
  `remarks` text DEFAULT NULL,
  `processed_by_employee_id` int(11) unsigned zerofill DEFAULT NULL,
  `loan_id` int(11) unsigned zerofill DEFAULT NULL,
  PRIMARY KEY (`payment_id`),
  KEY `Payment_Employee_FK` (`processed_by_employee_id`),
  KEY `Payment_Loan_FK` (`loan_id`),
  CONSTRAINT `Payment_Employee_FK` FOREIGN KEY (`processed_by_employee_id`) REFERENCES `Employee` (`employee_id`),
  CONSTRAINT `Payment_Loan_FK` FOREIGN KEY (`loan_id`) REFERENCES `Loan` (`loan_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Payment`
--

LOCK TABLES `Payment` WRITE;
/*!40000 ALTER TABLE `Payment` DISABLE KEYS */;
set autocommit=0;
/*!40000 ALTER TABLE `Payment` ENABLE KEYS */;
UNLOCK TABLES;
commit;

--
-- Dumping routines for database 'PaLoanSystem'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*M!100616 SET NOTE_VERBOSITY=@OLD_NOTE_VERBOSITY */;

-- Dump completed on 2026-01-20 20:49:04
