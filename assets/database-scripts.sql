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

CREATE TABLE IF NOT EXISTS `virtualwallet`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `roles_id` INT NULL DEFAULT NULL,
  `soft_delete` BIT(1) NOT NULL,
  `creation_date` DATETIME(6) NULL DEFAULT NULL,
  `update_date` DATETIME(6) NULL DEFAULT NULL,
  `email` VARCHAR(255) NULL DEFAULT NULL,
  `first_name` VARCHAR(255) NULL DEFAULT NULL,
  `last_name` VARCHAR(255) NULL DEFAULT NULL,
  `password` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UK_ntc01ead2d77ypev4qkp2x7si` (`roles_id` ASC) VISIBLE,
  CONSTRAINT `FKqwdb2ejj8pvnovexfq172hej4`
    FOREIGN KEY (`roles_id`)
    REFERENCES `virtualwallet`.`roles` (`id`))
;

