/* BOARD 테이블 생성 */
CREATE TABLE `board` (
     `id` int(11) NOT NULL AUTO_INCREMENT,
     `title` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
     `content` varchar(4000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
     `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
     PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/* 스케쥴 테이블 생성 */
CREATE TABLE `schedule_msg` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `msg` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
    `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
