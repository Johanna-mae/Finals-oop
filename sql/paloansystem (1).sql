-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 17, 2026 at 10:42 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `paloansystem`
--

-- --------------------------------------------------------

--
-- Table structure for table `client`
--

CREATE TABLE `client` (
  `client_id` int(11) NOT NULL,
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
  `date_registered` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `account_status` enum('Active','Suspended','Closed') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `document`
--

CREATE TABLE `document` (
  `document_id` int(11) UNSIGNED ZEROFILL NOT NULL,
  `application_id` int(11) UNSIGNED ZEROFILL DEFAULT NULL,
  `document_type` enum('Valid ID','Proof of Income','ITR','Proof of Billing','Employment Certificate','Business Permit','Collateral Documents') NOT NULL,
  `file_path` varchar(100) NOT NULL,
  `upload_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `verified_by_employee_id` int(11) UNSIGNED ZEROFILL DEFAULT NULL,
  `verification_status` enum('Pending','Verified','Rejected') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `employee_id` int(11) UNSIGNED ZEROFILL NOT NULL,
  `username` varchar(16) DEFAULT NULL,
  `password_hash` varchar(255) DEFAULT NULL,
  `first_name` varchar(64) DEFAULT NULL,
  `last_name` varchar(64) DEFAULT NULL,
  `role` enum('Loan_Officer','Manager','Cashier','Admin') DEFAULT NULL,
  `email` varchar(64) DEFAULT NULL,
  `date_hired` date DEFAULT NULL,
  `is_active` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`employee_id`, `username`, `password_hash`, `first_name`, `last_name`, `role`, `email`, `date_hired`, `is_active`) VALUES
(00000000007, 'jordan', 'jordan123', 'jordan', 'ebale', 'Admin', 'jordanmanuel@gmail.com', '2026-01-01', 1);

-- --------------------------------------------------------

--
-- Table structure for table `loan`
--

CREATE TABLE `loan` (
  `loan_id` int(11) UNSIGNED ZEROFILL NOT NULL,
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
  `client_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `loan_application`
--

CREATE TABLE `loan_application` (
  `application_id` int(11) UNSIGNED ZEROFILL NOT NULL,
  `requested_amount` decimal(12,2) NOT NULL,
  `requested_term_months` int(4) NOT NULL,
  `purpose` text NOT NULL,
  `application_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `status` enum('Pending','Under Review','Approved','Rejected','Cancelled') NOT NULL,
  `review_date` date DEFAULT NULL,
  `rejection_reason` text DEFAULT NULL,
  `approval_notes` text DEFAULT NULL,
  `client_id` int(11) DEFAULT NULL,
  `loan_type_id` int(10) DEFAULT NULL,
  `employee_id` int(11) UNSIGNED ZEROFILL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `loan_type`
--

CREATE TABLE `loan_type` (
  `loan_type_id` int(10) NOT NULL,
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
  `is_Active` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE `payment` (
  `payment_id` int(11) UNSIGNED ZEROFILL NOT NULL,
  `payment_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `due_date` date NOT NULL,
  `amount_paid` decimal(12,2) NOT NULL,
  `principal_paid` decimal(12,2) NOT NULL,
  `interest_paid` decimal(12,2) NOT NULL,
  `penalty_fee` decimal(12,2) NOT NULL,
  `payment_method` enum('Cash','Bank Transfer','Check','GCash','PayMaya') NOT NULL,
  `reference_number` varchar(100) DEFAULT NULL,
  `remarks` text DEFAULT NULL,
  `processed_by_employee_id` int(11) UNSIGNED ZEROFILL DEFAULT NULL,
  `loan_id` int(11) UNSIGNED ZEROFILL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`client_id`),
  ADD UNIQUE KEY `tin_unique` (`tin`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `document`
--
ALTER TABLE `document`
  ADD PRIMARY KEY (`document_id`),
  ADD KEY `Document_Loan_Application_FK` (`application_id`),
  ADD KEY `Document_Employee_FK` (`verified_by_employee_id`);

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`employee_id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `loan`
--
ALTER TABLE `loan`
  ADD PRIMARY KEY (`loan_id`),
  ADD KEY `Loan_Loan_Type_FK` (`loan_type_id`),
  ADD KEY `Loan_Client_FK` (`client_id`);

--
-- Indexes for table `loan_application`
--
ALTER TABLE `loan_application`
  ADD PRIMARY KEY (`application_id`),
  ADD KEY `Loan_Application_Client_FK` (`client_id`),
  ADD KEY `Loan_Application_Loan_Type_FK` (`loan_type_id`),
  ADD KEY `Loan_Application_Employee_FK` (`employee_id`);

--
-- Indexes for table `loan_type`
--
ALTER TABLE `loan_type`
  ADD PRIMARY KEY (`loan_type_id`);

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`payment_id`),
  ADD KEY `Payment_Employee_FK` (`processed_by_employee_id`),
  ADD KEY `Payment_Loan_FK` (`loan_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `client`
--
ALTER TABLE `client`
  MODIFY `client_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `document`
--
ALTER TABLE `document`
  MODIFY `document_id` int(11) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `employee`
--
ALTER TABLE `employee`
  MODIFY `employee_id` int(11) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `loan`
--
ALTER TABLE `loan`
  MODIFY `loan_id` int(11) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `loan_application`
--
ALTER TABLE `loan_application`
  MODIFY `application_id` int(11) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `loan_type`
--
ALTER TABLE `loan_type`
  MODIFY `loan_type_id` int(10) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `payment`
--
ALTER TABLE `payment`
  MODIFY `payment_id` int(11) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `document`
--
ALTER TABLE `document`
  ADD CONSTRAINT `Document_Employee_FK` FOREIGN KEY (`verified_by_employee_id`) REFERENCES `employee` (`employee_id`),
  ADD CONSTRAINT `Document_Loan_Application_FK` FOREIGN KEY (`application_id`) REFERENCES `loan_application` (`application_id`);

--
-- Constraints for table `loan`
--
ALTER TABLE `loan`
  ADD CONSTRAINT `Loan_Client_FK` FOREIGN KEY (`client_id`) REFERENCES `client` (`client_id`),
  ADD CONSTRAINT `Loan_Loan_Type_FK` FOREIGN KEY (`loan_type_id`) REFERENCES `loan_type` (`loan_type_id`);

--
-- Constraints for table `loan_application`
--
ALTER TABLE `loan_application`
  ADD CONSTRAINT `Loan_Application_Client_FK` FOREIGN KEY (`client_id`) REFERENCES `client` (`client_id`),
  ADD CONSTRAINT `Loan_Application_Employee_FK` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`employee_id`),
  ADD CONSTRAINT `Loan_Application_Loan_Type_FK` FOREIGN KEY (`loan_type_id`) REFERENCES `loan_type` (`loan_type_id`);

--
-- Constraints for table `payment`
--
ALTER TABLE `payment`
  ADD CONSTRAINT `Payment_Employee_FK` FOREIGN KEY (`processed_by_employee_id`) REFERENCES `employee` (`employee_id`),
  ADD CONSTRAINT `Payment_Loan_FK` FOREIGN KEY (`loan_id`) REFERENCES `loan` (`loan_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
