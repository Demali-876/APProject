-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 14, 2021 at 03:11 PM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 7.4.24

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `grizzlydb`
--

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `CusName` varchar(50) DEFAULT NULL,
  `CusPassword` varchar(50) DEFAULT NULL,
  `CusAccBal` float DEFAULT NULL,
  `CusID` int(20) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`CusName`, `CusPassword`, `CusAccBal`, `CusID`) VALUES
('Andrew ', 'password', 100, 1903945);

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `EmpID` int(11) NOT NULL,
  `EmpName` varchar(50) DEFAULT NULL,
  `EmpPassword` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`EmpID`, `EmpName`, `EmpPassword`) VALUES
(101, 'Gaurav', '12345'),
(2343, 'joel powell', 'hyf65'),
(11987, ' blue boy ', 'tets'),
(13021, ' black man ', 'joeltest'),
(132426, 'Jack Brown', '3la37uj');

-- --------------------------------------------------------

--
-- Table structure for table `equipment`
--

CREATE TABLE `equipment` (
  `EquipID` int(11) NOT NULL,
  `EquipName` varchar(50) DEFAULT NULL,
  `EquipStatus` varchar(50) DEFAULT NULL,
  `EquipCategory` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `equipment`
--

INSERT INTO `equipment` (`EquipID`, `EquipName`, `EquipStatus`, `EquipCategory`) VALUES
(100, 'stage lights', 'available', 'lighting'),
(111, 'speakers', 'available', 'sound');

-- --------------------------------------------------------

--
-- Table structure for table `process`
--

CREATE TABLE `process` (
  `CusID` int(11) NOT NULL,
  `EmpID` int(11) NOT NULL,
  `EquipID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `request`
--

CREATE TABLE `request` (
  `RequestID` int(11) NOT NULL,
  `RequestDate` date DEFAULT NULL,
  `ReserveDate` date DEFAULT NULL,
  `ReturnDate` date DEFAULT NULL,
  `RequestStatus` varchar(50) DEFAULT NULL,
  `TransactionID` int(11) DEFAULT NULL,
  `RequestCost` float DEFAULT NULL,
  `CusID` int(11) NOT NULL,
  `EquipID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `request`
--

INSERT INTO `request` (`RequestID`, `RequestDate`, `ReserveDate`, `ReturnDate`, `RequestStatus`, `TransactionID`, `RequestCost`, `CusID`, `EquipID`) VALUES
(200, '2021-11-12', '2021-11-20', NULL, 'processed', 300, 500, 1903945, 100);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`CusID`);

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`EmpID`);

--
-- Indexes for table `equipment`
--
ALTER TABLE `equipment`
  ADD PRIMARY KEY (`EquipID`);

--
-- Indexes for table `process`
--
ALTER TABLE `process`
  ADD PRIMARY KEY (`CusID`,`EmpID`,`EquipID`),
  ADD KEY `fkEquipIdb` (`EquipID`);

--
-- Indexes for table `request`
--
ALTER TABLE `request`
  ADD PRIMARY KEY (`CusID`,`EquipID`,`RequestID`),
  ADD KEY `fkEquipID` (`EquipID`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `process`
--
ALTER TABLE `process`
  ADD CONSTRAINT `fkCusIdB` FOREIGN KEY (`CusID`) REFERENCES `customer` (`CusID`),
  ADD CONSTRAINT `fkEquipIdb` FOREIGN KEY (`EquipID`) REFERENCES `equipment` (`EquipID`);

--
-- Constraints for table `request`
--
ALTER TABLE `request`
  ADD CONSTRAINT `fkCusID` FOREIGN KEY (`CusID`) REFERENCES `customer` (`CusID`),
  ADD CONSTRAINT `fkEquipID` FOREIGN KEY (`EquipID`) REFERENCES `equipment` (`EquipID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
