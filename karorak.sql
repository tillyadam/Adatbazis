-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 20, 2021 at 08:55 PM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.4.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `karorak`
--

-- --------------------------------------------------------

--
-- Table structure for table `karorak`
--

CREATE TABLE `karorak` (
  `id` int(11) NOT NULL,
  `marka` varchar(128) COLLATE utf8_hungarian_ci NOT NULL,
  `modell` varchar(128) COLLATE utf8_hungarian_ci NOT NULL,
  `szin` varchar(128) COLLATE utf8_hungarian_ci NOT NULL,
  `ar` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- Dumping data for table `karorak`
--

INSERT INTO `karorak` (`id`, `marka`, `modell`, `szin`, `ar`) VALUES
(3, 'Omega', 'Speedmaster', 'Fekete, kék', 8000),
(7, 'Seiko', 'SRPB56', 'Kék', 1100),
(8, 'Orient', 'Kamatsu III', 'zöld', 220),
(9, 'Casio', 'G-Shock SR55', 'fekete', 150);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `karorak`
--
ALTER TABLE `karorak`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `karorak`
--
ALTER TABLE `karorak`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
