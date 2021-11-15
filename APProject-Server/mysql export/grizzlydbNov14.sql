-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 15, 2021 at 02:32 AM
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
('Nicholai', 'password', 0, 100),
('Bobby', 'password', 0, 150);

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
(200, 'Ablen', 'pass'),
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
(1, 'Stage Lights', 'available', 'Lighting'),
(2, 'Speakers', 'available', 'Sound'),
(3, 'generator', 'available', 'Power'),
(4, 'plastic table ', 'available', 'staging'),
(5, 'Heavy Duty Generator', 'Unavailable', 'power');

-- --------------------------------------------------------

--
-- Table structure for table `process`
--

CREATE TABLE `process` (
  `CusID` int(11) NOT NULL,
  `EquipID` int(11) NOT NULL,
  `EmpID` int(11) NOT NULL,
  `requestID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `request`
--

CREATE TABLE `request` (
  `RequestID` int(11) NOT NULL,
  `RequestDate` varchar(50) DEFAULT NULL,
  `ReserveDate` varchar(50) DEFAULT NULL,
  `ReturnDate` varchar(50) DEFAULT NULL,
  `RequestStatus` varchar(50) DEFAULT NULL,
  `TransactionID` int(11) DEFAULT NULL,
  `RequestCost` int(11) DEFAULT NULL,
  `CusID` int(11) NOT NULL,
  `EquipID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `request`
--

INSERT INTO `request` (`RequestID`, `RequestDate`, `ReserveDate`, `ReturnDate`, `RequestStatus`, `TransactionID`, `RequestCost`, `CusID`, `EquipID`) VALUES
(1, 'Sat Nov 13 12:08:54 COT 2021', 'Sat Nov 13 17:50:48 COT 2021', 'Sat Nov 13 17:50:48 COT 2021', '1', 1, 1, 100, 1),
(2, 'Sat Nov 13 12:08:54 COT 2021', 'Sat Nov 13 17:52:30 COT 2021', 'Sat Nov 13 17:52:30 COT 2021', '2', 2, 100, 100, 2),
(3, 'Fri Nov 19 00:00:00 COT 2021', '', '', 'Pending', 3, 0, 100, 2),
(6, 'Fri Jan 21 00:00:00 GMT-05:00 2022', '', '', 'Pending', 6, 0, 100, 2),
(4, 'Fri Nov 19 00:00:00 GMT-05:00 2021', 'Sat Nov 20 00:00:00 GMT-05:00 2021', 'Thu Nov 25 00:00:00 GMT-05:00 2021', '', 4, 200, 100, 3),
(5, 'Tue Dec 21 00:00:00 GMT-05:00 2021', '', '', 'Pending', 5, 0, 100, 3),
(7, 'Tue Dec 14 00:00:00 GMT-05:00 2021', 'Sat Nov 20 00:00:00 GMT-05:00 2021', 'Mon Feb 14 00:00:00 GMT-05:00 2022', '', 7, 500, 100, 5);

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
  ADD PRIMARY KEY (`CusID`,`EquipID`,`EmpID`,`requestID`),
  ADD KEY `fkEquipmentt` (`EquipID`),
  ADD KEY `fkEmployee` (`EmpID`);

--
-- Indexes for table `request`
--
ALTER TABLE `request`
  ADD PRIMARY KEY (`CusID`,`EquipID`,`RequestID`),
  ADD KEY `fkEquipment` (`EquipID`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `process`
--
ALTER TABLE `process`
  ADD CONSTRAINT `fkCustomert` FOREIGN KEY (`CusID`) REFERENCES `customer` (`CusID`),
  ADD CONSTRAINT `fkEmployee` FOREIGN KEY (`EmpID`) REFERENCES `employee` (`EmpID`),
  ADD CONSTRAINT `fkEquipmentt` FOREIGN KEY (`EquipID`) REFERENCES `equipment` (`EquipID`);

--
-- Constraints for table `request`
--
ALTER TABLE `request`
  ADD CONSTRAINT `fkCustomer` FOREIGN KEY (`CusID`) REFERENCES `customer` (`CusID`),
  ADD CONSTRAINT `fkEquipment` FOREIGN KEY (`EquipID`) REFERENCES `equipment` (`EquipID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
