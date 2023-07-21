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
INSERT INTO role (name, description, creation_date, update_date) VALUES ("admin", "rol de administrador", NOW(), NOW());
INSERT INTO role (name, description, creation_date, update_date) VALUES ("user", "rol de usuario", NOW(), NOW());

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
  CONSTRAINT `FKqwdb2ejj8pvnovexfq172hej4`
    FOREIGN KEY (`role_id`)
    REFERENCES `virtualwallet`.`role` (`id`))
;

--INSERTS de Usuarios / Seed Data
INSERT INTO user (role_id, soft_delete, creation_date, update_date, email, first_name, last_name, password) VALUES (1, 0, NOW(), NOW(), "example@email.admin", "nameAdmin", "surnameAdmin", "1234a");
INSERT INTO user (role_id, soft_delete, creation_date, update_date, email, first_name, last_name, password) VALUES (2, 0, NOW(), NOW(), "example@email.user", "nameUser", "surnameUser", "1234u");


