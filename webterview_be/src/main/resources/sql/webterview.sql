-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema webterview
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `webterview` ;

-- -----------------------------------------------------
-- Schema webterview
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `webterview` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `webterview` ;

-- -----------------------------------------------------
-- Table `webterview`.`User`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `webterview`.`User` ;

CREATE TABLE IF NOT EXISTS `webterview`.`User` (
  `UserNo` INT NOT NULL AUTO_INCREMENT,
  `UserRole` VARCHAR(45) NOT NULL DEFAULT '2',
  `UserEmail` VARCHAR(45) NOT NULL,
  `UserPw` VARCHAR(100) NOT NULL,
  `UserName` VARCHAR(45) NOT NULL,
  `UserPhone` VARCHAR(15) NOT NULL,
  `UserDept` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`UserNo`),
  UNIQUE INDEX `UserEmail` (`UserEmail` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 39
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `webterview`.`Group`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `webterview`.`Group` ;

CREATE TABLE IF NOT EXISTS `webterview`.`Group` (
  `GroupNo` INT NOT NULL AUTO_INCREMENT,
  `UserNo` INT NOT NULL,
  `GroupStartDate` DATETIME NULL DEFAULT NULL,
  `GroupEndDate` DATETIME NULL DEFAULT NULL,
  `GroupBlind` TINYINT(1) NULL DEFAULT '0',
  PRIMARY KEY (`GroupNo`),
  INDEX `fk_group_user1_idx` (`UserNo` ASC) VISIBLE,
  CONSTRAINT `fk_group_user1`
    FOREIGN KEY (`UserNo`)
    REFERENCES `webterview`.`User` (`UserNo`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 700
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `webterview`.`Room`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `webterview`.`Room` ;

CREATE TABLE IF NOT EXISTS `webterview`.`Room` (
  `RoomNo` INT NOT NULL AUTO_INCREMENT,
  `GroupNo` INT NOT NULL,
  `RoomCode` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`RoomNo`),
  INDEX `fk_room_group1_idx` (`GroupNo` ASC) VISIBLE,
  CONSTRAINT `fk_room_group1`
    FOREIGN KEY (`GroupNo`)
    REFERENCES `webterview`.`Group` (`GroupNo`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 2729
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `webterview`.`Applicant`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `webterview`.`Applicant` ;

CREATE TABLE IF NOT EXISTS `webterview`.`Applicant` (
  `ApplicantNo` INT NOT NULL AUTO_INCREMENT,
  `RoomNo` INT NULL DEFAULT NULL,
  `ApplicantName` VARCHAR(45) NOT NULL,
  `ApplicantEmail` VARCHAR(45) NOT NULL,
  `ApplicantAge` INT NULL DEFAULT NULL,
  `ApplicantUniv` VARCHAR(45) NULL DEFAULT NULL,
  `ApplicantGPA` FLOAT NULL DEFAULT NULL,
  `ApplicantLicense` VARCHAR(100) NULL DEFAULT NULL,
  `ApplicantLang` VARCHAR(100) NULL DEFAULT NULL,
  `ApplicantDate` DATETIME NULL DEFAULT NULL,
  `ApplicantPhone` VARCHAR(15) NOT NULL,
  `GroupNo` INT NULL DEFAULT NULL,
  `ApplicantFile` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`ApplicantNo`),
  INDEX `fk_Applicant_room1_idx` (`RoomNo` ASC) VISIBLE,
  INDEX `GroupNo` (`GroupNo` ASC) VISIBLE,
  CONSTRAINT `Applicant_ibfk_1`
    FOREIGN KEY (`GroupNo`)
    REFERENCES `webterview`.`Group` (`GroupNo`),
  CONSTRAINT `fk_Applicant_room1`
    FOREIGN KEY (`RoomNo`)
    REFERENCES `webterview`.`Room` (`RoomNo`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 1537
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `webterview`.`Board`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `webterview`.`Board` ;

CREATE TABLE IF NOT EXISTS `webterview`.`Board` (
  `BoardNo` INT NOT NULL AUTO_INCREMENT,
  `UserNo` INT NULL DEFAULT NULL,
  `BoardType` INT NULL DEFAULT NULL,
  `BoardTitle` VARCHAR(45) NULL DEFAULT NULL,
  `BoardContent` VARCHAR(1000) NULL DEFAULT NULL,
  `BoardRegDate` DATETIME NULL DEFAULT NULL,
  `BoardUpdate` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`BoardNo`),
  INDEX `fk_Board_User1_idx` (`UserNo` ASC) VISIBLE,
  CONSTRAINT `fk_Board_User1`
    FOREIGN KEY (`UserNo`)
    REFERENCES `webterview`.`User` (`UserNo`)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 54
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `webterview`.`Comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `webterview`.`Comment` ;

CREATE TABLE IF NOT EXISTS `webterview`.`Comment` (
  `CommentNo` INT NOT NULL AUTO_INCREMENT,
  `BoardNo` INT NOT NULL,
  `UserNo` INT NULL DEFAULT NULL,
  `CommentAnswer` VARCHAR(100) NULL DEFAULT NULL,
  `CommentRegDate` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`CommentNo`),
  INDEX `fk_Comment_Board1_idx` (`BoardNo` ASC) VISIBLE,
  INDEX `fk_Comment_User1_idx` (`UserNo` ASC) VISIBLE,
  CONSTRAINT `fk_Comment_Board1`
    FOREIGN KEY (`BoardNo`)
    REFERENCES `webterview`.`Board` (`BoardNo`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Comment_User1`
    FOREIGN KEY (`UserNo`)
    REFERENCES `webterview`.`User` (`UserNo`)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 88
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `webterview`.`Evaluation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `webterview`.`Evaluation` ;

CREATE TABLE IF NOT EXISTS `webterview`.`Evaluation` (
  `EvaluationNo` INT NOT NULL AUTO_INCREMENT,
  `GroupNo` INT NOT NULL,
  `EvaluationQuestion` VARCHAR(150) NULL DEFAULT NULL,
  `EvaluationType` INT NULL DEFAULT '1',
  PRIMARY KEY (`EvaluationNo`),
  INDEX `fk_evaluation_group1_idx` (`GroupNo` ASC) VISIBLE,
  CONSTRAINT `fk_evaluation_group1`
    FOREIGN KEY (`GroupNo`)
    REFERENCES `webterview`.`Group` (`GroupNo`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 1017
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `webterview`.`FileInfo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `webterview`.`FileInfo` ;

CREATE TABLE IF NOT EXISTS `webterview`.`FileInfo` (
  `FileNo` INT NOT NULL AUTO_INCREMENT,
  `ApplicantNo` INT NULL DEFAULT NULL,
  `SaveFolder` VARCHAR(45) NULL DEFAULT NULL,
  `OriginFileName` VARCHAR(50) NULL DEFAULT NULL,
  `SaveFileName` VARCHAR(50) NULL DEFAULT NULL,
  PRIMARY KEY (`FileNo`),
  INDEX `fk_Fileinfo_applicant1_idx` (`ApplicantNo` ASC) VISIBLE,
  CONSTRAINT `fk_Fileinfo_applicant1`
    FOREIGN KEY (`ApplicantNo`)
    REFERENCES `webterview`.`Applicant` (`ApplicantNo`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 18
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `webterview`.`Rater`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `webterview`.`Rater` ;

CREATE TABLE IF NOT EXISTS `webterview`.`Rater` (
  `RaterNo` INT NOT NULL AUTO_INCREMENT,
  `UserNo` INT NOT NULL,
  `RoomNo` INT NULL DEFAULT NULL,
  `RaterEmail` VARCHAR(45) NOT NULL,
  `RaterName` VARCHAR(10) NOT NULL,
  `RaterPhone` VARCHAR(15) NOT NULL,
  `GroupNo` INT NULL DEFAULT NULL,
  PRIMARY KEY (`RaterNo`),
  INDEX `fk_Rater_user1_idx` (`UserNo` ASC) VISIBLE,
  INDEX `fk_Rater_room1_idx` (`RoomNo` ASC) VISIBLE,
  INDEX `fk_Rater_group1` (`GroupNo` ASC) VISIBLE,
  CONSTRAINT `fk_Rater_group1`
    FOREIGN KEY (`GroupNo`)
    REFERENCES `webterview`.`Group` (`GroupNo`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Rater_room1`
    FOREIGN KEY (`RoomNo`)
    REFERENCES `webterview`.`Room` (`RoomNo`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Rater_user1`
    FOREIGN KEY (`UserNo`)
    REFERENCES `webterview`.`User` (`UserNo`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 1290
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `webterview`.`Grade`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `webterview`.`Grade` ;

CREATE TABLE IF NOT EXISTS `webterview`.`Grade` (
  `GradeNo` INT NOT NULL AUTO_INCREMENT,
  `RaterNo` INT NULL DEFAULT NULL,
  `ApplicantNo` INT NOT NULL,
  `EvaluationNo` INT NOT NULL,
  `GradeScore` INT NULL DEFAULT '0',
  `GradeText` VARCHAR(500) NULL DEFAULT NULL,
  PRIMARY KEY (`GradeNo`),
  INDEX `fk_grade_rater1_idx` (`RaterNo` ASC) VISIBLE,
  INDEX `fk_grade_applicant1_idx` (`ApplicantNo` ASC) VISIBLE,
  INDEX `fk_grade_evaluation1_idx` (`EvaluationNo` ASC) VISIBLE,
  CONSTRAINT `fk_grade_applicant1`
    FOREIGN KEY (`ApplicantNo`)
    REFERENCES `webterview`.`Applicant` (`ApplicantNo`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_grade_evaluation1`
    FOREIGN KEY (`EvaluationNo`)
    REFERENCES `webterview`.`Evaluation` (`EvaluationNo`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_grade_rater1`
    FOREIGN KEY (`RaterNo`)
    REFERENCES `webterview`.`Rater` (`RaterNo`))
ENGINE = InnoDB
AUTO_INCREMENT = 857
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `webterview`.`Resume`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `webterview`.`Resume` ;

CREATE TABLE IF NOT EXISTS `webterview`.`Resume` (
  `ResumeNo` INT NOT NULL AUTO_INCREMENT,
  `ApplicantNo` INT NOT NULL,
  `ResumeQuestion` VARCHAR(200) NULL DEFAULT NULL,
  `ResumeAnswer` VARCHAR(3000) NULL DEFAULT NULL,
  PRIMARY KEY (`ResumeNo`),
  INDEX `fk_Resume_Applicant_idx` (`ApplicantNo` ASC) VISIBLE,
  CONSTRAINT `fk_Resume_Applicant`
    FOREIGN KEY (`ApplicantNo`)
    REFERENCES `webterview`.`Applicant` (`ApplicantNo`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 1775
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
