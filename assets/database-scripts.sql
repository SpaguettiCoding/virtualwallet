CREATE SCHEMA IF NOT EXISTS `virtualwallet` ;
USE `virtualwallet` ;

CREATE TABLE IF NOT EXISTS `virtualwallet`.`roles` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `creation_date` DATETIME(6) NOT NULL,
  `update_date` DATETIME(6) NOT NULL,
  `description` VARCHAR(255) NOT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
;

