-- Database PR-Ojek

SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS `order`;
DROP TABLE IF EXISTS `driver_prefloc`;
DROP TABLE IF EXISTS `driver`;
DROP TABLE IF EXISTS `user`;
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE IF NOT EXISTS `user` (
	`id`     INT         NOT NULL AUTO_INCREMENT,
    `name`        VARCHAR(50) NOT NULL,
	`email`       VARCHAR(50) NOT NULL,
	`phone`       VARCHAR(20) NOT NULL,
    `username`    VARCHAR(50) NOT NULL,
    `password`    VARCHAR(20) NOT NULL,
    `status`      VARCHAR(10) NOT NULL,
	`secret`	  VARCHAR(12) DEFAULT NULL,
	`pict`        MEDIUMBLOB  DEFAULT NULL,

	PRIMARY KEY (`id`)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `driver` (
`driver_id`     INT             NOT NULL,
`total_score`   DOUBLE(50,1)    NOT NULL DEFAULT '0.0',
`votes`         INT             NOT NULL DEFAULT '0',

PRIMARY KEY (`driver_id`)
) ENGINE=InnoDB;
