-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema nhn_academy_21
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema nhn_academy_21
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `nhn_academy_21` DEFAULT CHARACTER SET utf8 ;
USE `nhn_academy_21` ;

-- -----------------------------------------------------
-- Table `nhn_academy_21`.`status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nhn_academy_21`.`status` (
  `status_id` INT NOT NULL AUTO_INCREMENT,
  `status_name` VARCHAR(10) CHARACTER SET 'utf8' NOT NULL,
  PRIMARY KEY (`status_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `nhn_academy_21`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nhn_academy_21`.`users` (
  `user_index` INT NOT NULL AUTO_INCREMENT,
  `user_id` VARCHAR(20) CHARACTER SET 'utf8' NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `email` VARCHAR(40) CHARACTER SET 'utf8' NOT NULL,
  `status_id` INT NOT NULL,
  PRIMARY KEY (`user_index`),
  INDEX `fk_users_status_idx` (`status_id` ASC) VISIBLE,
  CONSTRAINT `fk_users_status`
    FOREIGN KEY (`status_id`)
    REFERENCES `nhn_academy_21`.`status` (`status_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
