-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 14, 2021 at 03:31 PM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 8.0.11

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
('Nicholai', 'password', 0, 100);

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
(1, 'Stage Lights', 'Unavailable', 'Lighting'),
(2, 'Speakers', 'Unavailable', 'Sound');

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
  `CusID` int(11) DEFAULT NULL,
  `EquipID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `request`
--

INSERT INTO `request` (`RequestID`, `RequestDate`, `ReserveDate`, `ReturnDate`, `RequestStatus`, `TransactionID`, `RequestCost`, `CusID`, `EquipID`) VALUES
(1, 'Sat Nov 13 12:08:54 COT 2021', 'Sat Nov 13 17:50:48 COT 2021', 'Sat Nov 13 17:50:48 COT 2021', '1', 1, 1, 100, 1),
(2, 'Sat Nov 13 12:08:54 COT 2021', 'Sat Nov 13 17:52:30 COT 2021', 'Sat Nov 13 17:52:30 COT 2021', '2', 2, 100, 100, 2),
(3, 'Fri Nov 19 00:00:00 COT 2021', '', '', 'Pending', 3, 0, 100, 2);

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
-- Indexes for table `request`
--
ALTER TABLE `request`
  ADD PRIMARY KEY (`RequestID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
