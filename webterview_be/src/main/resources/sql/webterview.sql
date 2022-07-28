DROP SCHEMA IF EXISTS `webterview`;
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
  `UserNo` INT,
  `BoardType` INT NULL DEFAULT NULL,
  `BoardTitle` VARCHAR(45) NULL DEFAULT NULL,
  `BoardContent` VARCHAR(1000) NULL DEFAULT NULL,
  `BoardRegDate` DATETIME NULL, 
  `BoardUpdate` DATETIME NULL, 
  PRIMARY KEY (`BoardNo`),
  INDEX `fk_Board_User1_idx` (`UserNo` ASC) VISIBLE,
  CONSTRAINT `fk_Board_User1`
	FOREIGN KEY (`UserNo`)
    REFERENCES `webterview`.`user` (`UserNo`)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
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
  `UserNo` INT,
  `CommentAnswer` VARCHAR(100) NULL,
  `CommentRegDate` DATETIME NULL, 
  PRIMARY KEY (`CommentNo`),
  INDEX `fk_Comment_Board1_idx` (`BoardNo` ASC) VISIBLE,
  INDEX `fk_Comment_User1_idx` (`UserNo` ASC) VISIBLE,
  CONSTRAINT `fk_Comment_Board1`
    FOREIGN KEY (`BoardNo`)
    REFERENCES `webterview`.`board` (`BoardNo`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Comment_User1`
	FOREIGN KEY (`UserNo`)
    REFERENCES `webterview`.`user` (`UserNo`)
    ON DELETE SET NULL
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
  `UserRole` VARCHAR(45) NOT NULL DEFAULT "2",
  `UserEmail` VARCHAR(45) UNIQUE NOT NULL,
  `UserPw` VARCHAR(100) NOT NULL,
  `UserName` VARCHAR(45) NOT NULL,
  `UserPhone` VARCHAR(15) NOT NULL,
  `UserDept` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`UserNo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `webterview`.`rater`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `webterview`.`rater` ;

CREATE TABLE IF NOT EXISTS `webterview`.`rater` (
  `RaterNo` INT NOT NULL AUTO_INCREMENT,
  `UserNo` INT NOT NULL,
  `RoomNo` INT NULL DEFAULT NULL,
  `RaterEmail` VARCHAR(45) NOT NULL,
  `RaterName` VARCHAR(10) NOT NULL,
  `RaterPhone` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`RaterNo`),
  INDEX `fk_Rater_user1_idx` (`UserNo` ASC) VISIBLE,
  INDEX `fk_Rater_room1_idx` (`RoomNo` ASC) VISIBLE,
  CONSTRAINT `fk_Rater_user1`
    FOREIGN KEY (`UserNo`)
    REFERENCES `webterview`.`user` (`UserNo`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
CONSTRAINT `fk_Rater_room1`
    FOREIGN KEY (`RoomNo`)
    REFERENCES `webterview`.`room` (`RoomNo`)
    ON DELETE SET NULL
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
  `GroupStartDate` DATETIME NULL,
  `GroupCode` VARCHAR(45),
  `GroupEndDate` DATETIME NULL,
  `GroupBlind` BOOLEAN DEFAULT false,
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
  `EvaluationQuestion` VARCHAR(150) NULL DEFAULT NULL,
  `EvaluationMaxScore` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`EvaluationNo`),
  INDEX `fk_evaluation_group1_idx` (`GroupNo` ASC) VISIBLE,
  CONSTRAINT `fk_evaluation_group1`
    FOREIGN KEY (`GroupNo`)
    REFERENCES `webterview`.`group` (`GroupNo`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `webterview`.`grade`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `webterview`.`grade` ;

CREATE TABLE IF NOT EXISTS `webterview`.`grade` (
  `GradeNo` INT NOT NULL AUTO_INCREMENT,
  `RaterNo` INT NOT NULL,
  `ApplicantNo` INT NOT NULL,
  `EvaluationNo` INT NOT NULL,
  `GradeScore` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`GradeNo`),
  INDEX `fk_grade_rater1_idx` (`RaterNo` ASC) VISIBLE,
  INDEX `fk_grade_applicant1_idx` (`ApplicantNo` ASC) VISIBLE,
  INDEX `fk_grade_evaluation1_idx` (`EvaluationNo` ASC) VISIBLE,
  CONSTRAINT `fk_grade_rater1`
    FOREIGN KEY (`RaterNo`)
    REFERENCES `webterview`.`Rater` (`RaterNo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_grade_applicant1`
    FOREIGN KEY (`ApplicantNo`)
    REFERENCES `webterview`.`applicant` (`ApplicantNo`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_grade_evaluation1`
    FOREIGN KEY (`EvaluationNo`)
    REFERENCES `webterview`.`evaluation` (`EvaluationNo`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
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
  `RoomCode` VARCHAR(45),
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
-- Table `webterview`.`applicant`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `webterview`.`applicant` ;

CREATE TABLE IF NOT EXISTS `webterview`.`applicant` (
  `ApplicantNo` INT NOT NULL AUTO_INCREMENT,
  `RoomNo` INT NOT NULL,
  `ApplicantOrder` INT NOT NULL,
  `ApplicantName` VARCHAR(45) NOT NULL,
  `ApplicantEmail` VARCHAR(45) NOT NULL,
  `ApplicantAge` INT NULL DEFAULT NULL,
  `ApplicantUniv` VARCHAR(45) NULL DEFAULT NULL,
  `ApplicantGPA` FLOAT NULL DEFAULT NULL,
  `ApplicantLicense` VARCHAR(100) NULL DEFAULT NULL,
  `ApplicantLang` VARCHAR(100) NULL DEFAULT NULL,
  `ApplicantUnique` VARCHAR(45) NULL DEFAULT NULL,
  `ApplicantDate` DATETIME NULL DEFAULT NULL,
  `ApplicantRank` INT NOT NULL DEFAULT 1,
  `ApplicantPhone` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`ApplicantNo`),
  INDEX `fk_Applicant_room1_idx` (`RoomNo` ASC) VISIBLE,
  CONSTRAINT `fk_Applicant_room1`
    FOREIGN KEY (`RoomNo`)
    REFERENCES `webterview`.`room` (`RoomNo`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Table `webterview`.`fileinfo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `webterview`.`fileinfo` ;

CREATE TABLE IF NOT EXISTS `webterview`.`fileinfo` (
  `FileNo` INT NOT NULL AUTO_INCREMENT,
  `ApplicantNo` INT NOT NULL,
  `SaveFolder` VARCHAR(45) NULL,
  `OriginFileName` VARCHAR(50) NULL,
  `SaveFileName` VARCHAR(50) NULL,
  PRIMARY KEY (`FileNo`),
  INDEX `fk_Fileinfo_applicant1_idx` (`ApplicantNo` ASC) VISIBLE,
  CONSTRAINT `fk_Fileinfo_applicant1`
    FOREIGN KEY (`ApplicantNo`)
    REFERENCES `webterview`.`applicant` (`ApplicantNo`)
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
  `ApplicantNo` INT NOT NULL,
  `ResumeQuestion` VARCHAR(45) NULL DEFAULT NULL,
  `ResumeAnswer` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`ResumeNo`),
  INDEX `fk_Resume_Applicant_idx` (`ApplicantNo` ASC) VISIBLE,
  CONSTRAINT `fk_Resume_Applicant`
    FOREIGN KEY (`ApplicantNo`)
    REFERENCES `webterview`.`Applicant` (`ApplicantNo`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

USE `webterview` ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;