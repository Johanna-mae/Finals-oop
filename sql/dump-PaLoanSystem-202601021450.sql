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

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `Client` (
  `client_id` int(11) NOT NULL AUTO_INCREMENT,
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
  `tin` varchar(100) NOT NULL,
  `civil_status` enum('Single','Married','Widowed','Separated') NOT NULL,
  `employment_status` enum('Employed','Self-Employed','Unemployed','Retired') NOT NULL,
  `employeer_name` varchar(100) DEFAULT NULL,
  `monthly_income` decimal(12,2) NOT NULL,
  `date_registered` timestamp NOT NULL,
  `account_status` enum('Active','Suspended','Closed') NOT NULL,
  PRIMARY KEY (`client_id`),
  UNIQUE KEY `tin_unique` (`tin`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Document`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `Document` (
  `document_id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `application_id` int(11) unsigned zerofill DEFAULT NULL,
  `document_type` enum('Valid ID','Proof of Income','ITR','Proof of Billing','Employment Certificate','Business Permit','Collateral Documents') NOT NULL,
  `file_path` varchar(100) NOT NULL,
  `upload_date` timestamp NOT NULL,
  `verified_by_employee_id` int(11) unsigned zerofill DEFAULT NULL,
  `verification_status` enum('Pending','Verified','Rejected') NOT NULL,
  PRIMARY KEY (`document_id`),
  KEY `Document_Loan_Application_FK` (`application_id`),
  KEY `Document_Employee_FK` (`verified_by_employee_id`),
  CONSTRAINT `Document_Employee_FK` FOREIGN KEY (`verified_by_employee_id`) REFERENCES `Employee` (`employee_id`),
  CONSTRAINT `Document_Loan_Application_FK` FOREIGN KEY (`application_id`) REFERENCES `Loan_Application` (`application_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Employee`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `Employee` (
  `employee_id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `username` varchar(16) DEFAULT NULL,
  `password` varchar(16) DEFAULT NULL,
  `first_name` varchar(64) DEFAULT NULL,
  `last_name` varchar(64) DEFAULT NULL,
  `role` enum('Loan_Officer','Manager','Cashier','Admin') DEFAULT NULL,
  `email` varchar(64) DEFAULT NULL,
  `date_hired` date DEFAULT NULL,
  `is_active` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`employee_id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Loan`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `Loan` (
  `loan_id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `principal_amount` decimal(12,2) NOT NULL,
  `interest_rate` decimal(12,2) NOT NULL,
  `interest_type` enum('Simple','Compound') NOT NULL,
  `terms_months` int(4) NOT NULL,
  `monthly_payment` decimal(12,2) NOT NULL COMMENT 'calculated',
  `total_interest` decimal(12,2) NOT NULL COMMENT 'calculated',
  `total_amount_payable` decimal(12,2) NOT NULL COMMENT 'calculated',
  `outstanding_balance` decimal(12,2) NOT NULL COMMENT 'calculated',
  `loan_start_date` date NOT NULL,
  `loan_end_date` date NOT NULL,
  `disbursement_date` date NOT NULL,
  `status` enum('Active','Paid_Off','Defaulted','Restructured') NOT NULL,
  `creadted_date` date NOT NULL,
  `loan_type_id` int(11) DEFAULT NULL,
  `client_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`loan_id`),
  KEY `Loan_Loan_Type_FK` (`loan_type_id`),
  KEY `Loan_Client_FK` (`client_id`),
  CONSTRAINT `Loan_Client_FK` FOREIGN KEY (`client_id`) REFERENCES `Client` (`client_id`),
  CONSTRAINT `Loan_Loan_Type_FK` FOREIGN KEY (`loan_type_id`) REFERENCES `Loan_Type` (`loan_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Loan_Application`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `Loan_Application` (
  `application_id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `requested_amount` decimal(12,2) NOT NULL,
  `requested_term_months` int(4) NOT NULL,
  `purpose` text NOT NULL,
  `application_date` timestamp NOT NULL,
  `status` enum('Pending','Under Review','Approved','Rejected','Cancelled') NOT NULL,
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Loan_Type`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `Loan_Type` (
  `loan_type_id` int(10) NOT NULL AUTO_INCREMENT,
  `type_name` enum('Personal','Auto','Housing','Business','Education') NOT NULL,
  `description` text DEFAULT NULL,
  `interest_calculation` enum('Simple','Compound') NOT NULL,
  `annual_interest_rate` decimal(12,2) NOT NULL,
  `min_amount` decimal(12,2) NOT NULL,
  `max_amount` decimal(12,2) NOT NULL,
  `min_term_months` int(4) NOT NULL,
  `max_term_months` int(4) NOT NULL,
  `required_documents` text NOT NULL,
  `collateral_required` tinyint(1) NOT NULL,
  `is_Active` tinyint(1) NOT NULL,
  PRIMARY KEY (`loan_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Payment`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `Payment` (
  `payment_id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
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

-- Dump completed on 2026-01-02 14:50:27
