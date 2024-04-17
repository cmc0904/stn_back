-- --------------------------------------------------------
-- 호스트:                          192.168.5.29
-- 서버 버전:                        8.3.0 - MySQL Community Server - GPL
-- 서버 OS:                        Linux
-- HeidiSQL 버전:                  12.6.0.6765
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- stn_infotech 데이터베이스 구조 내보내기
CREATE DATABASE IF NOT EXISTS `stn_infotech` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `stn_infotech`;

-- 테이블 stn_infotech.stn_board 구조 내보내기
CREATE TABLE IF NOT EXISTS `stn_board` (
  `idx` int NOT NULL AUTO_INCREMENT,
  `title` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `detail` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `writerId` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `createAt` datetime NOT NULL,
  `private` int NOT NULL,
  PRIMARY KEY (`idx`),
  KEY `FK_stn_board_stn_users` (`writerId`) USING BTREE,
  CONSTRAINT `FK_stn_board_stn_users` FOREIGN KEY (`writerId`) REFERENCES `stn_users` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 테이블 데이터 stn_infotech.stn_board:~7 rows (대략적) 내보내기
INSERT INTO `stn_board` (`idx`, `title`, `detail`, `writerId`, `createAt`, `private`) VALUES
	(21, 'awdawdaw', 'awdawdaw', 'user', '2024-04-17 15:52:25', 0),
	(22, 'dwadawd', 'awdawdaw', 'user', '2024-04-17 15:52:33', 0),
	(24, 'asdasd', 'asdadasd', 'anscks2350', '2024-04-17 15:55:35', 0),
	(29, 'ㅁㅈㅇㅁㅈ', 'ㅈㅁㅇㅁㅈ', 'user1', '2024-04-17 16:18:57', 0),
	(30, '파일 첨부 테스트 입니다', '안녕하세요~~~~~~~~~~~~~~~~~~', 'user1', '2024-04-17 16:21:43', 0),
	(31, 'ㅁㅈㅇㅁㅈㅇ', 'ㅁㅈㅇㅁㅈㅇ', 'user1', '2024-04-17 16:25:32', 0),
	(32, 'ㅁㅈㅇㅁㅈㅇㅇㅇㅇㅇㅇㅇㅇ', 'ㅇㅇㅇㅇㅇㅇ', 'user1', '2024-04-17 16:25:52', 0);

-- 테이블 stn_infotech.stn_board_comment 구조 내보내기
CREATE TABLE IF NOT EXISTS `stn_board_comment` (
  `idx` int NOT NULL AUTO_INCREMENT,
  `boardIdx` int NOT NULL,
  `comment` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `writerId` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `createAt` datetime NOT NULL,
  PRIMARY KEY (`idx`),
  KEY `FK_stn_board_comment_stn_board` (`boardIdx`) USING BTREE,
  KEY `FK_stn_board_comment_stn_users` (`writerId`) USING BTREE,
  CONSTRAINT `FK_stn_board_comment_stn_board_new` FOREIGN KEY (`boardIdx`) REFERENCES `stn_board` (`idx`) ON DELETE CASCADE,
  CONSTRAINT `FK_stn_board_comment_stn_users` FOREIGN KEY (`writerId`) REFERENCES `stn_users` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 테이블 데이터 stn_infotech.stn_board_comment:~1 rows (대략적) 내보내기
INSERT INTO `stn_board_comment` (`idx`, `boardIdx`, `comment`, `writerId`, `createAt`) VALUES
	(9, 29, 'awdawdawd', 'user1', '2024-04-17 16:28:46');

-- 테이블 stn_infotech.stn_board_files 구조 내보내기
CREATE TABLE IF NOT EXISTS `stn_board_files` (
  `idx` int NOT NULL AUTO_INCREMENT,
  `boardIdx` int NOT NULL,
  `fileName` varchar(304) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`idx`),
  KEY `FK_stn_board_files_stn_board` (`boardIdx`),
  CONSTRAINT `FK_stn_board_files_stn_board` FOREIGN KEY (`boardIdx`) REFERENCES `stn_board` (`idx`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 테이블 데이터 stn_infotech.stn_board_files:~2 rows (대략적) 내보내기
INSERT INTO `stn_board_files` (`idx`, `boardIdx`, `fileName`) VALUES
	(11, 29, '557ef0a7-8ef7-4e0a-aa9c-46c0d9f875f3=awd (2) (1).jpg'),
	(12, 30, 'c0abb4ab-c654-4d08-9e19-de4dbdcc6653=images.jfif');

-- 테이블 stn_infotech.stn_board_reader 구조 내보내기
CREATE TABLE IF NOT EXISTS `stn_board_reader` (
  `int` int NOT NULL AUTO_INCREMENT,
  `reader` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `boardIdx` int NOT NULL,
  PRIMARY KEY (`int`),
  KEY `FK_stn_board_reader_stn_board_new` (`boardIdx`),
  CONSTRAINT `FK_stn_board_reader_stn_board_new` FOREIGN KEY (`boardIdx`) REFERENCES `stn_board` (`idx`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 테이블 데이터 stn_infotech.stn_board_reader:~10 rows (대략적) 내보내기
INSERT INTO `stn_board_reader` (`int`, `reader`, `boardIdx`) VALUES
	(32, 'user', 22),
	(33, 'user', 21),
	(34, 'anscks2350', 22),
	(35, 'anscks2350', 24),
	(36, 'user', 24),
	(37, 'junho', 24),
	(39, 'user1', 29),
	(40, 'user1', 30),
	(41, 'user1', 32),
	(42, 'user1', 31);

-- 테이블 stn_infotech.stn_faq 구조 내보내기
CREATE TABLE IF NOT EXISTS `stn_faq` (
  `idx` int NOT NULL AUTO_INCREMENT,
  `question` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `answer` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`idx`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 테이블 데이터 stn_infotech.stn_faq:~1 rows (대략적) 내보내기
INSERT INTO `stn_faq` (`idx`, `question`, `answer`) VALUES
	(1, 'wdawdawdddd', 'dawdawawdawdawd');

-- 테이블 stn_infotech.stn_repair 구조 내보내기
CREATE TABLE IF NOT EXISTS `stn_repair` (
  `idx` int NOT NULL AUTO_INCREMENT,
  `customerUserId` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `problemTitle` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `problemComment` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `createAt` datetime NOT NULL,
  PRIMARY KEY (`idx`),
  KEY `FK_stn_repair_stn_users` (`customerUserId`) USING BTREE,
  CONSTRAINT `FK_stn_repair_stn_users` FOREIGN KEY (`customerUserId`) REFERENCES `stn_users` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 테이블 데이터 stn_infotech.stn_repair:~8 rows (대략적) 내보내기
INSERT INTO `stn_repair` (`idx`, `customerUserId`, `problemTitle`, `problemComment`, `createAt`) VALUES
	(1, 'anscks2350', '안녕하세요~', '안녕하세용~', '2024-04-17 14:47:27'),
	(2, 'anscks2350', '테스트11', '테스트211', '2024-04-17 15:19:00'),
	(3, 'anscks2350', '테스트22', '테스트22', '2024-04-17 15:19:08'),
	(4, 'anscks2350', '테스트33', '테스트44', '2024-04-17 15:19:14'),
	(5, 'anscks2350', '테스트55', '테스트55', '2024-04-17 15:19:21'),
	(6, 'user1', 'ㅈㅇㅁㅁㅈㅇㅁㅈㅇ', 'ㅁㅈㅇㅁㅈㅇㅁㅈ', '2024-04-17 15:25:02'),
	(7, 'user1', 'ㅈㅁㅇㅁㅈ', 'ㅁㅈㅇㅁㅈㅇㅇㅁㅈㅇㅁㅈㅇㅁㅈㅇㅁㅈㅇㅁㅈㅇㅇ', '2024-04-17 15:27:19'),
	(8, 'user1', 'ㅁㅈㅇㅁㅈㅇㅁㅈㅇㅁㅈㅇㅁㅈㅇ', 'ㅁㅈㅇㅁㅈㅇㅁㅈ', '2024-04-17 16:25:04');

-- 테이블 stn_infotech.stn_repair_result 구조 내보내기
CREATE TABLE IF NOT EXISTS `stn_repair_result` (
  `idx` int NOT NULL AUTO_INCREMENT,
  `repairIdx` int NOT NULL,
  `adminId` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `finished` int NOT NULL DEFAULT '0',
  `visitDate` datetime NOT NULL,
  PRIMARY KEY (`idx`),
  KEY `FK_stn_repaire_result_stn_repair` (`repairIdx`) USING BTREE,
  CONSTRAINT `FK_stn_repaire_result_stn_repair` FOREIGN KEY (`repairIdx`) REFERENCES `stn_repair` (`idx`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 테이블 데이터 stn_infotech.stn_repair_result:~7 rows (대략적) 내보내기
INSERT INTO `stn_repair_result` (`idx`, `repairIdx`, `adminId`, `finished`, `visitDate`) VALUES
	(1, 1, 'anscks2350', 1, '2024-04-17 15:24:00'),
	(2, 4, 'anscks2350', 1, '2024-04-17 15:24:00'),
	(3, 3, 'awdaw', 0, '2024-04-27 15:32:00'),
	(4, 6, 'awdawdawd', 1, '2024-04-17 18:28:00'),
	(5, 7, 'anscks2350', 1, '2024-04-17 15:35:00'),
	(6, 8, 'anscks2350', 1, '2024-04-17 16:35:00'),
	(7, 5, 'anscks2350', 1, '2024-04-17 16:29:00');

-- 테이블 stn_infotech.stn_roles 구조 내보내기
CREATE TABLE IF NOT EXISTS `stn_roles` (
  `userId` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `role` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`userId`,`role`),
  CONSTRAINT `FK_stn_roles_stn_users` FOREIGN KEY (`userId`) REFERENCES `stn_users` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 테이블 데이터 stn_infotech.stn_roles:~8 rows (대략적) 내보내기
INSERT INTO `stn_roles` (`userId`, `role`) VALUES
	('anscks2350', 'Admin'),
	('anscks2350', 'user'),
	('awdaw', 'Admin'),
	('awdawdaw', 'Admin'),
	('awdawdawd', 'Admin'),
	('junho', 'Admin'),
	('user', 'Admin'),
	('user1', 'User');

-- 테이블 stn_infotech.stn_users 구조 내보내기
CREATE TABLE IF NOT EXISTS `stn_users` (
  `userId` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `email` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `address` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `phone` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `gender` enum('F','M') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `createAt` datetime NOT NULL,
  PRIMARY KEY (`userId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 테이블 데이터 stn_infotech.stn_users:~7 rows (대략적) 내보내기
INSERT INTO `stn_users` (`userId`, `password`, `name`, `email`, `address`, `phone`, `gender`, `createAt`) VALUES
	('anscks2350', '$2a$10$tvr5Dw7BEjM7hXQkw1uN9eViIKVY1gDp9d26JgtNRH/asBaEYu/cm', '최문형', 'anscks2350@naver.com', '경기 성남시 중원구 시민로 77', '01037924983', 'M', '2024-04-17 14:44:48'),
	('awdaw', '$2a$10$MSMNXTw0Gu1UCovsrwFeAe/rhLGrK0WbcQtSRYTtdCeae/4edg85G', '박준호', 'kai061204@gmail.co', '부산 강서구 명지오션시티1로 28', '01026891204', 'M', '2024-04-17 15:22:54'),
	('awdawdaw', '$2a$10$3azq.2tsHLzfchOcoAfiS.8u/JTCEooXsQqo7LsKhSn3RFJwN65UO', '박준호', 'kai061204@gmail.com', '서울 강남구 자곡로 16', '01026891204', 'M', '2024-04-17 15:19:54'),
	('awdawdawd', '$2a$10$Se214B7e33f5HEw6Od1Mje0gbrkSAEAGTkrPQlU2WRvJrKJDZVV8W', 'junho', 'kai061204@gmail.co', '서울 강동구 양재대로 1299', '01026891204', 'M', '2024-04-17 15:20:29'),
	('junho', '$2a$10$7UVKMjPwGYKiJg.22Hqec.iAtVMbWYj2KpMogXKgEV/5jPFK/ZjQ.', '박준호', 'kai061204@gmail.com', '경기 성남시 분당구 대왕판교로 364', '01026891204', 'M', '2024-04-17 14:46:10'),
	('user', '$2a$10$if..ncUqyXCT24bCvh2Fpu0wZeMCMMVGHy2Iz8gof/61ep068Stsu', '박준호', 'kai061204@gmail.com', '서울 용산구 유엔빌리지길 1', '01026891204', 'M', '2024-04-17 14:48:08'),
	('user1', '$2a$10$pV43mnQ/c3Y8IrNClF.NVesQjTXmYLOEOhOzXKfAIYfbgj5BhbxPG', '박준호호호', 'kai061204@gmail.com', '충남 홍성군 홍성읍 아문길 20', '01026891204', 'M', '2024-04-17 15:18:03');

-- 트리거 stn_infotech.add_user_to_roles 구조 내보내기
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `add_user_to_roles` AFTER INSERT ON `stn_users` FOR EACH ROW BEGIN
    INSERT INTO stn_roles (userId, role)
    VALUES (NEW.userId, 'User');
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
