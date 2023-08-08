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

CREATE TABLE IF NOT EXISTS `virtualwallet`.`credit_card` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `amount` DOUBLE NOT NULL,
  `amount_available` DOUBLE NOT NULL,
  `closing_date` DATETIME(6) NULL DEFAULT NULL,
  `creation_date` DATETIME(6) NULL DEFAULT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `soft_delete` BIT(1) NOT NULL,
  `account_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKimolliig2ewssb3jm63n1dv03` (`account_id` ASC) VISIBLE,
  CONSTRAINT `FKimolliig2ewssb3jm63n1dv03`
    FOREIGN KEY (`account_id`)
    REFERENCES `virtualwallet`.`account` (`id`))
   ;

--INSERTS de Usuarios / Seed Data
INSERT INTO user (role_id, soft_delete, creation_date, update_date, email, first_name, last_name, password) VALUES (1, 0, NOW(), NOW(), "example@email.admin", "nameAdmin", "surnameAdmin", "1234a");
INSERT INTO user (role_id, soft_delete, creation_date, update_date, email, first_name, last_name, password) VALUES (2, 0, NOW(), NOW(), "example@email.user", "nameUser", "surnameUser", "1234u");
INSERT INTO user (role_id, soft_delete, creation_date, update_date, email, first_name, last_name, password) VALUES (1, 0, NOW(), NOW(), "Ingrid@email.admin", "Ingrid", "Cuadra", "1234a");
INSERT INTO user (role_id, soft_delete, creation_date, update_date, email, first_name, last_name, password) VALUES (1, 0, NOW(), NOW(), "Facu@email.admin", "Facu", "Monsegur", "1234b");
INSERT INTO user (role_id, soft_delete, creation_date, update_date, email, first_name, last_name, password) VALUES (1, 0, NOW(), NOW(), "Fer@email.admin", "Fer", "Silvero", "1234c");
INSERT INTO user (role_id, soft_delete, creation_date, update_date, email, first_name, last_name, password) VALUES (1, 0, NOW(), NOW(), "Juli@email.admin", "Juli", "Di Rocco", "1234d");
INSERT INTO user (role_id, soft_delete, creation_date, update_date, email, first_name, last_name, password) VALUES (1, 0, NOW(), NOW(), "Juan@email.admin", "Juan", "Rodriguez", "1234e");
INSERT INTO user (role_id, soft_delete, creation_date, update_date, email, first_name, last_name, password) VALUES (2, 0, NOW(), NOW(), "Daro@email.user", "Dario", "Raducci", "1234f");
INSERT INTO user (role_id, soft_delete, creation_date, update_date, email, first_name, last_name, password) VALUES (2, 0, NOW(), NOW(), "Juanca@email.user", "Juan Carlos", "Ojeda", "1234g");
INSERT INTO user (role_id, soft_delete, creation_date, update_date, email, first_name, last_name, password) VALUES (2, 0, NOW(), NOW(), "Jira@email.user", "Jira", "Atlassian", "1234h");
INSERT INTO user (role_id, soft_delete, creation_date, update_date, email, first_name, last_name, password) VALUES (2, 0, NOW(), NOW(), "Git@email.user", "Git", "Microsoft", "1234i");
INSERT INTO user (role_id, soft_delete, creation_date, update_date, email, first_name, last_name, password) VALUES (2, 0, NOW(), NOW(), "Java@email.user", "Java", "Oracle", "1234j");


CREATE TABLE `transactions` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `amount` DOUBLE NOT NULL,
  `description` VARCHAR(255) DEFAULT NULL,
  `transaction_date` DATETIME(6) DEFAULT NULL,
  `transaction_type` VARCHAR(8) NOT NULL,
  `account_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfyn6ksis1qglevn2liiqirx75` (`account_id`),
  CONSTRAINT `FKfyn6ksis1qglevn2liiqirx75` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`)
)

