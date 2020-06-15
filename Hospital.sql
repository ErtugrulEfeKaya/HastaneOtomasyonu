-- --------------------------------------------------------
-- Sunucu:                       127.0.0.1
-- Sunucu sürümü:                10.4.13-MariaDB - mariadb.org binary distribution
-- Sunucu İşletim Sistemi:       Win64
-- HeidiSQL Sürüm:               11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- hospital için veritabanı yapısı dökülüyor
CREATE DATABASE IF NOT EXISTS `hospital` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `hospital`;

-- tablo yapısı dökülüyor hospital.clinic
CREATE TABLE IF NOT EXISTS `clinic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- hospital.clinic: ~6 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `clinic` DISABLE KEYS */;
INSERT INTO `clinic` (`id`, `name`) VALUES
	(1, 'Göz Hastalıkları'),
	(2, 'Kalp Hastalıkları'),
	(4, 'Kulak Burun Boğaz'),
	(5, 'Cildiye '),
	(6, 'Dahiliye'),
	(7, 'Beyin Cerrahi Pol.');
/*!40000 ALTER TABLE `clinic` ENABLE KEYS */;

-- tablo yapısı dökülüyor hospital.randevu
CREATE TABLE IF NOT EXISTS `randevu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `doctor_id` int(11) NOT NULL,
  `doctor_name` varchar(255) NOT NULL,
  `hasta_id` int(11) NOT NULL,
  `hasta_name` varchar(255) NOT NULL,
  `app_date` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- hospital.randevu: ~1 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `randevu` DISABLE KEYS */;
INSERT INTO `randevu` (`id`, `doctor_id`, `doctor_name`, `hasta_id`, `hasta_name`, `app_date`) VALUES
	(1, 2, 'Ahmet Yıldız', 19, 'Şimal Yılmaz', '2020-06-05 10:00:00'),
	(2, 14, 'Damla Ok', 19, 'Şimal Yılmaz', '2020-06-12 10:00:00');
/*!40000 ALTER TABLE `randevu` ENABLE KEYS */;

-- tablo yapısı dökülüyor hospital.user
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tcno` varchar(15) CHARACTER SET utf8 NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `type` enum('bashekim','doktor','hasta') CHARACTER SET utf8 NOT NULL DEFAULT 'hasta',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;

-- hospital.user: ~8 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `tcno`, `password`, `name`, `type`) VALUES
	(1, '123', '123', 'Efe Kaya', 'bashekim'),
	(2, '1', '123', 'Ahmet Yıldız', 'doktor'),
	(3, '14587', 'qwe', 'Ayşe Koç', 'doktor'),
	(14, '1254', '123123', 'Damla Ok', 'doktor'),
	(15, '147', '213', 'Zeynep Kaya', 'doktor'),
	(18, '29035547724', 'cfdrg23s', 'Ertuğrul', 'hasta'),
	(19, '1234', '12345', 'Şimal Yılmaz', 'hasta'),
	(21, '3423646214', 'ahmet', 'Kerim Korkmaz', 'hasta');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

-- tablo yapısı dökülüyor hospital.whour
CREATE TABLE IF NOT EXISTS `whour` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `doctor_id` int(11) NOT NULL,
  `doctor_name` varchar(255) NOT NULL DEFAULT '',
  `wdate` varchar(255) NOT NULL DEFAULT '',
  `status` enum('a','p') NOT NULL DEFAULT 'a',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- hospital.whour: ~3 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `whour` DISABLE KEYS */;
INSERT INTO `whour` (`id`, `doctor_id`, `doctor_name`, `wdate`, `status`) VALUES
	(2, 2, 'Ahmet', '2020-06-05 10:00:00', 'p'),
	(6, 2, 'Ahmet', '2020-06-19 10:00:00', 'a'),
	(7, 2, 'Ahmet', '2020-06-20 12:00:00', 'a'),
	(9, 14, 'Damla Ok', '2020-06-12 10:00:00', 'p');
/*!40000 ALTER TABLE `whour` ENABLE KEYS */;

-- tablo yapısı dökülüyor hospital.worker
CREATE TABLE IF NOT EXISTS `worker` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `clinic_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;

-- hospital.worker: ~18 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `worker` DISABLE KEYS */;
INSERT INTO `worker` (`id`, `clinic_id`, `user_id`) VALUES
	(1, 2, 2),
	(2, 1, 2),
	(3, 1, 3),
	(4, 5, 14),
	(5, 4, 14),
	(7, 4, 15),
	(8, 2, 15),
	(9, 5, 15),
	(10, 6, 2),
	(11, 6, 3),
	(13, 6, 14),
	(15, 7, 2),
	(18, 8, 14),
	(19, 7, 3),
	(20, 4, 3),
	(21, 1, 14),
	(22, 7, 14),
	(23, 7, 15);
/*!40000 ALTER TABLE `worker` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
