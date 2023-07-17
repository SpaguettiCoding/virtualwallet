CREATE SCHEMA IF NOT EXISTS `virtualwallet` ;
USE `virtualwallet` ;

CREATE TABLE IF NOT EXISTS `virtualwallet`.`roles` (
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

