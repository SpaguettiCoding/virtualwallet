CREATE SCHEMA IF NOT EXISTS `virtualwallet`;
USE `virtualwallet` ;

CREATE TABLE IF NOT EXISTS `virtualwallet`.`roles` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `creation_date` DATETIME(6) NULL DEFAULT NULL,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  `name` ENUM('ADMIN', 'USER') NULL DEFAULT NULL,
  `update_date` DATETIME(6) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
;

CREATE TABLE IF NOT EXISTS `virtualwallet`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `creation_date` DATETIME(6) NULL DEFAULT NULL,
  `email` VARCHAR(255) NULL DEFAULT NULL,
  `first_name` VARCHAR(255) NULL DEFAULT NULL,
  `last_name` VARCHAR(255) NULL DEFAULT NULL,
  `password` VARCHAR(255) NULL DEFAULT NULL,
  `soft_delete` BIT(1) NOT NULL,
  `update_date` DATETIME(6) NULL DEFAULT NULL,
  `roles_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UK_ntc01ead2d77ypev4qkp2x7si` (`roles_id` ASC) VISIBLE,
  CONSTRAINT `FKqwdb2ejj8pvnovexfq172hej4`
    FOREIGN KEY (`roles_id`)
    REFERENCES `virtualwallet`.`roles` (`id`))
;

CREATE TABLE IF NOT EXISTS `virtualwallet`.`account` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `balance` DOUBLE NOT NULL,
  `creation_date` DATETIME(6) NULL DEFAULT NULL,
  `currency` ENUM('ARS', 'USD') NULL DEFAULT NULL,
  `soft_delete` BIT(1) NOT NULL,
  `transaction_limit` DOUBLE NOT NULL,
  `update_date` DATETIME(6) NULL DEFAULT NULL,
  `user_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK7m8ru44m93ukyb61dfxw0apf6` (`user_id` ASC) VISIBLE,
  CONSTRAINT `FK7m8ru44m93ukyb61dfxw0apf6`
    FOREIGN KEY (`user_id`)
    REFERENCES `virtualwallet`.`user` (`id`))
;

CREATE TABLE IF NOT EXISTS `virtualwallet`.`credit_cards` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `amount` DOUBLE NOT NULL,
  `amount_available` DOUBLE NOT NULL,
  `closing_date` DATETIME(6) NULL DEFAULT NULL,
  `creation_date` DATETIME(6) NULL DEFAULT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `soft_delete` BIT(1) NOT NULL,
  `account_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKe6c0yl4710kq2vmi9gh2ndfp2` (`account_id` ASC) VISIBLE,
  CONSTRAINT `FKe6c0yl4710kq2vmi9gh2ndfp2`
    FOREIGN KEY (`account_id`)
    REFERENCES `virtualwallet`.`account` (`id`))
;

CREATE TABLE IF NOT EXISTS `virtualwallet`.`cryptos` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `amount` DOUBLE NOT NULL,
  `closing_date` DATETIME(6) NULL DEFAULT NULL,
  `creation_date` DATETIME(6) NULL DEFAULT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `soft_delete` BIT(1) NOT NULL,
  `account_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKoslovis6l435nbossdxjsjqyq` (`account_id` ASC) VISIBLE,
  CONSTRAINT `FKoslovis6l435nbossdxjsjqyq`
    FOREIGN KEY (`account_id`)
    REFERENCES `virtualwallet`.`account` (`id`))
;

CREATE TABLE IF NOT EXISTS `virtualwallet`.`fixed_term_deposits` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `amount` DOUBLE NOT NULL,
  `closing_date` DATETIME(6) NULL DEFAULT NULL,
  `creation_date` DATETIME(6) NULL DEFAULT NULL,
  `interest` DOUBLE NOT NULL,
  `account_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKbwskiqleq9f6vjdb81woebop2` (`account_id` ASC) VISIBLE,
  CONSTRAINT `FKbwskiqleq9f6vjdb81woebop2`
    FOREIGN KEY (`account_id`)
    REFERENCES `virtualwallet`.`account` (`id`))
;

CREATE TABLE IF NOT EXISTS `virtualwallet`.`transactions` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `amount` DOUBLE NOT NULL,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  `transaction_date` DATETIME(6) NULL DEFAULT NULL,
  `type` ENUM('DEPOSIT', 'INCOME', 'PAYMENT') NULL DEFAULT NULL,
  `account_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKfyn6ksis1qglevn2liiqirx75` (`account_id` ASC) VISIBLE,
  CONSTRAINT `FKfyn6ksis1qglevn2liiqirx75`
    FOREIGN KEY (`account_id`)
    REFERENCES `virtualwallet`.`account` (`id`))
;

CREATE TABLE IF NOT EXISTS `virtualwallet`.`transactions_credit_card` (
  `transactions_id` INT NOT NULL,
  `credit_card_id` INT NOT NULL,
  INDEX `FKmgat3uvo8olesh76784ds12s3` (`credit_card_id` ASC) VISIBLE,
  INDEX `FKb9pq0kdasn211dx4uqhdk1bf1` (`transactions_id` ASC) VISIBLE,
  CONSTRAINT `FKb9pq0kdasn211dx4uqhdk1bf1`
    FOREIGN KEY (`transactions_id`)
    REFERENCES `virtualwallet`.`transactions` (`id`),
  CONSTRAINT `FKmgat3uvo8olesh76784ds12s3`
    FOREIGN KEY (`credit_card_id`)
    REFERENCES `virtualwallet`.`credit_cards` (`id`))
;

CREATE TABLE IF NOT EXISTS `virtualwallet`.`transactions_cryptos` (
  `transactions_id` INT NOT NULL,
  `cryptos_id` INT NOT NULL,
  INDEX `FKe06uiitfjy3jk9uaqtsuhqy66` (`cryptos_id` ASC) VISIBLE,
  INDEX `FKfacv9yhie6sb44ivpwr3tqiro` (`transactions_id` ASC) VISIBLE,
  CONSTRAINT `FKe06uiitfjy3jk9uaqtsuhqy66`
    FOREIGN KEY (`cryptos_id`)
    REFERENCES `virtualwallet`.`cryptos` (`id`),
  CONSTRAINT `FKfacv9yhie6sb44ivpwr3tqiro`
    FOREIGN KEY (`transactions_id`)
    REFERENCES `virtualwallet`.`transactions` (`id`))
;

CREATE TABLE IF NOT EXISTS `virtualwallet`.`transactions_fixed_term_deposits` (
  `transactions_id` INT NOT NULL,
  `fixed_term_deposits_id` INT NOT NULL,
  INDEX `FKkp3auvlquk4stewda0olkwiqf` (`fixed_term_deposits_id` ASC) VISIBLE,
  INDEX `FKsuixvnggppvct5cxifw3c59h2` (`transactions_id` ASC) VISIBLE,
  CONSTRAINT `FKkp3auvlquk4stewda0olkwiqf`
    FOREIGN KEY (`fixed_term_deposits_id`)
    REFERENCES `virtualwallet`.`fixed_term_deposits` (`id`),
  CONSTRAINT `FKsuixvnggppvct5cxifw3c59h2`
    FOREIGN KEY (`transactions_id`)
    REFERENCES `virtualwallet`.`transactions` (`id`))
;
