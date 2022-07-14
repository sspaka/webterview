-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema webterview
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `webterview` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `webterview` ;

-- -----------------------------------------------------
-- Table `webterview`.`board`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `webterview`.`board` ;

CREATE TABLE IF NOT EXISTS `webterview`.`board` (
  `BoardNo` INT NOT NULL AUTO_INCREMENT,
  `UserNo` INT NOT NULL,
  `BoardType` INT NULL DEFAULT NULL,
  `BoardTitle` VARCHAR(45) NULL DEFAULT NULL,
  `BoardContent` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`BoardNo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `webterview`.`comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `webterview`.`comment` ;

CREATE TABLE IF NOT EXISTS `webterview`.`comment` (
  `CommentNo` INT NOT NULL AUTO_INCREMENT,
  `BoardNo` INT NOT NULL,
  `CommentAnswer` VARCHAR(45) NULL,
  PRIMARY KEY (`CommentNo`),
  INDEX `fk_Comment_Board1_idx` (`BoardNo` ASC) VISIBLE,
  CONSTRAINT `fk_Comment_Board1`
    FOREIGN KEY (`BoardNo`)
    REFERENCES `webterview`.`board` (`BoardNo`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `webterview`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `webterview`.`user` ;

CREATE TABLE IF NOT EXISTS `webterview`.`user` (
  `UserNo` INT NOT NULL AUTO_INCREMENT,
  `UserRole` VARCHAR(45) NOT NULL,
  `UserEmail` VARCHAR(45) NOT NULL,
  `UserPassword` VARCHAR(45) NOT NULL,
  `UserName` VARCHAR(45) NOT NULL,
  `UserPhone` VARCHAR(11) NOT NULL,
  `UserDepartment` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`UserNo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `webterview`.`estimator`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `webterview`.`estimator` ;

CREATE TABLE IF NOT EXISTS `webterview`.`estimator` (
  `EstimatorNo` INT NOT NULL,
  `UserNo` INT NOT NULL,
  `RoomNo` INT NULL DEFAULT NULL,
  `EstimatorEmail` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`EstimatorNo`),
  INDEX `fk_estimator_user1_idx` (`UserNo` ASC) VISIBLE,
  CONSTRAINT `fk_estimator_user1`
    FOREIGN KEY (`UserNo`)
    REFERENCES `webterview`.`user` (`UserNo`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `webterview`.`group`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `webterview`.`group` ;

CREATE TABLE IF NOT EXISTS `webterview`.`group` (
  `GroupNo` INT NOT NULL AUTO_INCREMENT,
  `UserNo` INT NOT NULL,
  `GroupStartDate` DATE NULL,
  `GroupEndDate` DATE NULL,
  PRIMARY KEY (`GroupNo`),
  INDEX `fk_group_user1_idx` (`UserNo` ASC) VISIBLE,
  CONSTRAINT `fk_group_user1`
    FOREIGN KEY (`UserNo`)
    REFERENCES `webterview`.`user` (`UserNo`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `webterview`.`evaluation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `webterview`.`evaluation` ;

CREATE TABLE IF NOT EXISTS `webterview`.`evaluation` (
  `EvaluationNo` INT NOT NULL AUTO_INCREMENT,
  `GroupNo` INT NOT NULL,
  `EstimatorNo` INT NOT NULL,
  `EvaluationQuestion` VARCHAR(45) NULL DEFAULT NULL,
  `EvaluationScore` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`EvaluationNo`),
  INDEX `fk_evaluation_group1_idx` (`GroupNo` ASC) VISIBLE,
  INDEX `fk_evaluation_estimator1_idx` (`EstimatorNo` ASC) VISIBLE,
  CONSTRAINT `fk_evaluation_group1`
    FOREIGN KEY (`GroupNo`)
    REFERENCES `webterview`.`group` (`GroupNo`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_evaluation_estimator1`
    FOREIGN KEY (`EstimatorNo`)
    REFERENCES `webterview`.`estimator` (`EstimatorNo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `webterview`.`room`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `webterview`.`room` ;

CREATE TABLE IF NOT EXISTS `webterview`.`room` (
  `RoomNo` INT NOT NULL AUTO_INCREMENT,
  `GroupNo` INT NOT NULL,
  PRIMARY KEY (`RoomNo`),
  INDEX `fk_room_group1_idx` (`GroupNo` ASC) VISIBLE,
  CONSTRAINT `fk_room_group1`
    FOREIGN KEY (`GroupNo`)
    REFERENCES `webterview`.`group` (`GroupNo`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `webterview`.`interviewer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `webterview`.`interviewer` ;

CREATE TABLE IF NOT EXISTS `webterview`.`interviewer` (
  `InterviewerNo` INT NOT NULL AUTO_INCREMENT,
  `RoomNo` INT NOT NULL,
  `InterviewerName` VARCHAR(45) NOT NULL,
  `InterviewerAge` INT NULL DEFAULT NULL,
  `InterviewerUniversity` VARCHAR(45) NULL DEFAULT NULL,
  `InterviewerGPA` FLOAT NULL DEFAULT NULL,
  `InterviewerCertificate` VARCHAR(45) NULL DEFAULT NULL,
  `InterviewerLanguage` VARCHAR(45) NULL DEFAULT NULL,
  `InterviewerUniqueness` VARCHAR(45) NULL DEFAULT NULL,
  `InterviewerDate` DATE NULL DEFAULT NULL,
  `InterviewerRank` INT NOT NULL DEFAULT 1,
  PRIMARY KEY (`InterviewerNo`),
  INDEX `fk_interviewer_room1_idx` (`RoomNo` ASC) VISIBLE,
  CONSTRAINT `fk_interviewer_room1`
    FOREIGN KEY (`RoomNo`)
    REFERENCES `webterview`.`room` (`RoomNo`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `webterview`.`resume`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `webterview`.`resume` ;

CREATE TABLE IF NOT EXISTS `webterview`.`resume` (
  `ResumeNo` INT NOT NULL AUTO_INCREMENT,
  `InterviewerNo` INT NOT NULL,
  `ResumeQuestion` VARCHAR(45) NULL DEFAULT NULL,
  `ResumeAnswer` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`ResumeNo`),
  INDEX `fk_Resume_Interviewer_idx` (`InterviewerNo` ASC) VISIBLE,
  CONSTRAINT `fk_Resume_Interviewer`
    FOREIGN KEY (`InterviewerNo`)
    REFERENCES `webterview`.`interviewer` (`InterviewerNo`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

USE `webterview` ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
