-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 31-05-2026 a las 22:19:52
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `clubbuceo`
--

CREATE DATABASE IF NOT EXISTS `clubbuceo` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE `clubbuceo`;
-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `buceadores`
--

CREATE TABLE `buceadores` (
  `id_buceador` int(11) NOT NULL,
  `dni` varchar(9) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `apellidos` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `telefono` int(9) NOT NULL,
  `fecha_nacimiento` date NOT NULL,
  `fecha_alta` date NOT NULL,
  `titulacion` enum('OpenWater','Advance','Rescue','Otro') NOT NULL,
  `organizacion` enum('PADI','SSI','FEDAS','CMAS') NOT NULL,
  `num_inmersiones` int(11) NOT NULL DEFAULT 0,
  `grupo_sanguineo` varchar(5) NOT NULL,
  `alergias` varchar(255) DEFAULT 'Ninguna',
  `fecha_reconocimiento` date NOT NULL,
  `compania_seguro` varchar(100) NOT NULL,
  `fecha_cad_seguro` date NOT NULL,
  `cont_emerg_nombre` varchar(150) NOT NULL,
  `cont_emerg_telf` int(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `buceadores`
--

INSERT INTO `buceadores` (`id_buceador`, `dni`, `nombre`, `apellidos`, `email`, `telefono`, `fecha_nacimiento`, `fecha_alta`, `titulacion`, `organizacion`, `num_inmersiones`, `grupo_sanguineo`, `alergias`, `fecha_reconocimiento`, `compania_seguro`, `fecha_cad_seguro`, `cont_emerg_nombre`, `cont_emerg_telf`) VALUES
(1, '12345678B', 'Juan', 'Garcia Lopez', 'juan.garcia@gmail.com', 123456789, '1990-05-14', '2023-01-10', 'Advance', 'PADI', 87, 'A+', 'Ninguna', '2024-11-20', 'Mapfre', '2025-12-31', 'Maria Lopez', 698111222),
(2, '87654321B', 'Laura', 'Martinez Ruiz', 'laura.martinez@hotmail.com', 634987654, '1985-03-22', '2021-06-15', 'Otro', 'SSI', 30, 'O-', 'Paracetamol', '2024-09-05', 'Axa', '2026-03-14', 'Jose Tomas', 611154325),
(3, '12345678C', 'Ahmed', 'Ben Salah', 'ahmed.bensalah@gmail.com', 123246345, '2000-08-30', '2024-04-02', 'OpenWater', 'CMAS', 15, 'B+', 'Latex, Ibuprofeno', '2024-04-01', 'Allianz', '2025-04-01', 'Fatima Ben Salah', 655444555);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `buceadores`
--
ALTER TABLE `buceadores`
  ADD PRIMARY KEY (`id_buceador`),
  ADD UNIQUE KEY `dni` (`dni`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `buceadores`
--
ALTER TABLE `buceadores`
  MODIFY `id_buceador` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
