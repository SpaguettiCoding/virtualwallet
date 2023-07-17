CREATE SCHEMA IF NOT EXISTS `virtualwallet` ;
USE `virtualwallet` ;

CREATE TABLE IF NOT EXISTS `virtualwallet`.`role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `description` VARCHAR(255) NOT NULL,
  `creation_date` DATETIME(6) NOT NULL,
  `update_date` DATETIME(6) NOT NULL,
  PRIMARY KEY (`id`))
;

--INSERTS de roles / SEED DATA
INSERT INTO roles (name, description, creation_date, update_date) VALUES ("admin", "rol de administrador", NOW(), NOW());
INSERT INTO roles (name, description, creation_date, update_date) VALUES ("user", "rol de usuario", NOW(), NOW());

CREATE TABLE IF NOT EXISTS `virtualwallet`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `role_id` INT NULL DEFAULT NULL,
  `soft_delete` BIT(1) NOT NULL,
  `creation_date` DATETIME(6) NULL DEFAULT NULL,
  `update_date` DATETIME(6) NULL DEFAULT NULL,
  `email` VARCHAR(255) NULL DEFAULT NULL,
  `first_name` VARCHAR(255) NULL DEFAULT NULL,
  `last_name` VARCHAR(255) NULL DEFAULT NULL,
  `password` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UK_ntc01ead2d77ypev4qkp2x7si` (`role_id` ASC) VISIBLE,
  CONSTRAINT `FKqwdb2ejj8pvnovexfq172hej4`
    FOREIGN KEY (`role_id`)
    REFERENCES `virtualwallet`.`role` (`id`))
;


