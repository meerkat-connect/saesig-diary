-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE =
        'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema saesig
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `saesig`;

-- -----------------------------------------------------
-- Schema saesig
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `saesig` DEFAULT CHARACTER SET utf8mb4;
USE `saesig`;

-- -----------------------------------------------------
-- Table `saesig`.`BATCH_JOB_INSTANCE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `saesig`.`BATCH_JOB_INSTANCE`;

CREATE TABLE IF NOT EXISTS `saesig`.`BATCH_JOB_INSTANCE`
(
    `JOB_INSTANCE_ID` BIGINT(20)   NOT NULL,
    `VERSION`         BIGINT(20)   NULL DEFAULT NULL,
    `JOB_NAME`        VARCHAR(100) NOT NULL,
    `JOB_KEY`         VARCHAR(32)  NOT NULL,
    PRIMARY KEY (`JOB_INSTANCE_ID`),
    UNIQUE INDEX `JOB_INST_UN` (`JOB_NAME` ASC, `JOB_KEY` ASC) VISIBLE
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `saesig`.`BATCH_JOB_EXECUTION`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `saesig`.`BATCH_JOB_EXECUTION`;

CREATE TABLE IF NOT EXISTS `saesig`.`BATCH_JOB_EXECUTION`
(
    `JOB_EXECUTION_ID`           BIGINT(20)    NOT NULL,
    `VERSION`                    BIGINT(20)    NULL DEFAULT NULL,
    `JOB_INSTANCE_ID`            BIGINT(20)    NOT NULL,
    `CREATE_TIME`                DATETIME(6)   NOT NULL,
    `START_TIME`                 DATETIME(6)   NULL DEFAULT NULL,
    `END_TIME`                   DATETIME(6)   NULL DEFAULT NULL,
    `STATUS`                     VARCHAR(10)   NULL DEFAULT NULL,
    `EXIT_CODE`                  VARCHAR(2500) NULL DEFAULT NULL,
    `EXIT_MESSAGE`               VARCHAR(2500) NULL DEFAULT NULL,
    `LAST_UPDATED`               DATETIME(6)   NULL DEFAULT NULL,
    `JOB_CONFIGURATION_LOCATION` VARCHAR(2500) NULL DEFAULT NULL,
    PRIMARY KEY (`JOB_EXECUTION_ID`),
    INDEX `JOB_INST_EXEC_FK` (`JOB_INSTANCE_ID` ASC) VISIBLE,
    CONSTRAINT `JOB_INST_EXEC_FK`
        FOREIGN KEY (`JOB_INSTANCE_ID`)
            REFERENCES `saesig`.`BATCH_JOB_INSTANCE` (`JOB_INSTANCE_ID`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `saesig`.`BATCH_JOB_EXECUTION_CONTEXT`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `saesig`.`BATCH_JOB_EXECUTION_CONTEXT`;

CREATE TABLE IF NOT EXISTS `saesig`.`BATCH_JOB_EXECUTION_CONTEXT`
(
    `JOB_EXECUTION_ID`   BIGINT(20)    NOT NULL,
    `SHORT_CONTEXT`      VARCHAR(2500) NOT NULL,
    `SERIALIZED_CONTEXT` TEXT          NULL DEFAULT NULL,
    PRIMARY KEY (`JOB_EXECUTION_ID`),
    CONSTRAINT `JOB_EXEC_CTX_FK`
        FOREIGN KEY (`JOB_EXECUTION_ID`)
            REFERENCES `saesig`.`BATCH_JOB_EXECUTION` (`JOB_EXECUTION_ID`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `saesig`.`BATCH_JOB_EXECUTION_PARAMS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `saesig`.`BATCH_JOB_EXECUTION_PARAMS`;

CREATE TABLE IF NOT EXISTS `saesig`.`BATCH_JOB_EXECUTION_PARAMS`
(
    `JOB_EXECUTION_ID` BIGINT(20)   NOT NULL,
    `TYPE_CD`          VARCHAR(6)   NOT NULL,
    `KEY_NAME`         VARCHAR(100) NOT NULL,
    `STRING_VAL`       VARCHAR(250) NULL DEFAULT NULL,
    `DATE_VAL`         DATETIME(6)  NULL DEFAULT NULL,
    `LONG_VAL`         BIGINT(20)   NULL DEFAULT NULL,
    `DOUBLE_VAL`       DOUBLE       NULL DEFAULT NULL,
    `IDENTIFYING`      CHAR(1)      NOT NULL,
    INDEX `JOB_EXEC_PARAMS_FK` (`JOB_EXECUTION_ID` ASC) VISIBLE,
    CONSTRAINT `JOB_EXEC_PARAMS_FK`
        FOREIGN KEY (`JOB_EXECUTION_ID`)
            REFERENCES `saesig`.`BATCH_JOB_EXECUTION` (`JOB_EXECUTION_ID`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `saesig`.`BATCH_JOB_EXECUTION_SEQ`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `saesig`.`BATCH_JOB_EXECUTION_SEQ`;

CREATE TABLE IF NOT EXISTS `saesig`.`BATCH_JOB_EXECUTION_SEQ`
(
    `ID`         BIGINT(20) NOT NULL,
    `UNIQUE_KEY` CHAR(1)    NOT NULL,
    UNIQUE INDEX `UNIQUE_KEY_UN` (`UNIQUE_KEY` ASC)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `saesig`.`BATCH_JOB_SEQ`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `saesig`.`BATCH_JOB_SEQ`;

CREATE TABLE IF NOT EXISTS `saesig`.`BATCH_JOB_SEQ`
(
    `ID`         BIGINT(20) NOT NULL,
    `UNIQUE_KEY` CHAR(1)    NOT NULL,
    UNIQUE INDEX `UNIQUE_KEY_UN` (`UNIQUE_KEY` ASC)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `saesig`.`BATCH_STEP_EXECUTION`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `saesig`.`BATCH_STEP_EXECUTION`;

CREATE TABLE IF NOT EXISTS `saesig`.`BATCH_STEP_EXECUTION`
(
    `STEP_EXECUTION_ID`  BIGINT(20)    NOT NULL,
    `VERSION`            BIGINT(20)    NOT NULL,
    `STEP_NAME`          VARCHAR(100)  NOT NULL,
    `JOB_EXECUTION_ID`   BIGINT(20)    NOT NULL,
    `START_TIME`         DATETIME(6)   NOT NULL,
    `END_TIME`           DATETIME(6)   NULL DEFAULT NULL,
    `STATUS`             VARCHAR(10)   NULL DEFAULT NULL,
    `COMMIT_COUNT`       BIGINT(20)    NULL DEFAULT NULL,
    `READ_COUNT`         BIGINT(20)    NULL DEFAULT NULL,
    `FILTER_COUNT`       BIGINT(20)    NULL DEFAULT NULL,
    `WRITE_COUNT`        BIGINT(20)    NULL DEFAULT NULL,
    `READ_SKIP_COUNT`    BIGINT(20)    NULL DEFAULT NULL,
    `WRITE_SKIP_COUNT`   BIGINT(20)    NULL DEFAULT NULL,
    `PROCESS_SKIP_COUNT` BIGINT(20)    NULL DEFAULT NULL,
    `ROLLBACK_COUNT`     BIGINT(20)    NULL DEFAULT NULL,
    `EXIT_CODE`          VARCHAR(2500) NULL DEFAULT NULL,
    `EXIT_MESSAGE`       VARCHAR(2500) NULL DEFAULT NULL,
    `LAST_UPDATED`       DATETIME(6)   NULL DEFAULT NULL,
    PRIMARY KEY (`STEP_EXECUTION_ID`),
    INDEX `JOB_EXEC_STEP_FK` (`JOB_EXECUTION_ID` ASC) VISIBLE,
    CONSTRAINT `JOB_EXEC_STEP_FK`
        FOREIGN KEY (`JOB_EXECUTION_ID`)
            REFERENCES `saesig`.`BATCH_JOB_EXECUTION` (`JOB_EXECUTION_ID`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `saesig`.`BATCH_STEP_EXECUTION_CONTEXT`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `saesig`.`BATCH_STEP_EXECUTION_CONTEXT`;

CREATE TABLE IF NOT EXISTS `saesig`.`BATCH_STEP_EXECUTION_CONTEXT`
(
    `STEP_EXECUTION_ID`  BIGINT(20)    NOT NULL,
    `SHORT_CONTEXT`      VARCHAR(2500) NOT NULL,
    `SERIALIZED_CONTEXT` TEXT          NULL DEFAULT NULL,
    PRIMARY KEY (`STEP_EXECUTION_ID`),
    CONSTRAINT `STEP_EXEC_CTX_FK`
        FOREIGN KEY (`STEP_EXECUTION_ID`)
            REFERENCES `saesig`.`BATCH_STEP_EXECUTION` (`STEP_EXECUTION_ID`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `saesig`.`BATCH_STEP_EXECUTION_SEQ`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `saesig`.`BATCH_STEP_EXECUTION_SEQ`;

CREATE TABLE IF NOT EXISTS `saesig`.`BATCH_STEP_EXECUTION_SEQ`
(
    `ID`         BIGINT(20) NOT NULL,
    `UNIQUE_KEY` CHAR(1)    NOT NULL,
    UNIQUE INDEX `UNIQUE_KEY_UN` (`UNIQUE_KEY` ASC)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `saesig`.`SPRING_SESSION`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `saesig`.`SPRING_SESSION`;

CREATE TABLE IF NOT EXISTS `saesig`.`SPRING_SESSION`
(
    `PRIMARY_ID`            CHAR(36)     NOT NULL,
    `SESSION_ID`            CHAR(36)     NOT NULL,
    `CREATION_TIME`         BIGINT(20)   NOT NULL,
    `LAST_ACCESS_TIME`      BIGINT(20)   NOT NULL,
    `MAX_INACTIVE_INTERVAL` INT(11)      NOT NULL,
    `EXPIRY_TIME`           BIGINT(20)   NOT NULL,
    `PRINCIPAL_NAME`        VARCHAR(100) NULL DEFAULT NULL,
    PRIMARY KEY (`PRIMARY_ID`),
    INDEX `SPRING_SESSION_IX3` (`PRINCIPAL_NAME` ASC) VISIBLE
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    ROW_FORMAT = DYNAMIC;


-- -----------------------------------------------------
-- Table `saesig`.`SPRING_SESSION_ATTRIBUTES`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `saesig`.`SPRING_SESSION_ATTRIBUTES`;

CREATE TABLE IF NOT EXISTS `saesig`.`SPRING_SESSION_ATTRIBUTES`
(
    `SESSION_PRIMARY_ID` CHAR(36)     NOT NULL,
    `ATTRIBUTE_NAME`     VARCHAR(200) NOT NULL,
    `ATTRIBUTE_BYTES`    BLOB         NOT NULL,
    PRIMARY KEY (`SESSION_PRIMARY_ID`, `ATTRIBUTE_NAME`),
    CONSTRAINT `SPRING_SESSION_ATTRIBUTES_FK`
        FOREIGN KEY (`SESSION_PRIMARY_ID`)
            REFERENCES `saesig`.`SPRING_SESSION` (`PRIMARY_ID`)
            ON DELETE CASCADE
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    ROW_FORMAT = DYNAMIC;


-- -----------------------------------------------------
-- Table `saesig`.`admin_access_log`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `saesig`.`admin_access_log`;

CREATE TABLE IF NOT EXISTS `saesig`.`admin_access_log`
(
    `id`            BIGINT(20)    NOT NULL AUTO_INCREMENT COMMENT '로그 일련번호',
    `ip`            VARCHAR(100)  NOT NULL COMMENT '아이피',
    `action`        VARCHAR(100)  NOT NULL COMMENT '액션',
    `resource_name` VARCHAR(500)  NOT NULL COMMENT '자원 명',
    `resource_url`  VARCHAR(500)  NOT NULL COMMENT '자원 URL',
    `resource_id`   BIGINT(20)    NOT NULL COMMENT '자원 일련번호',
    `message`       VARCHAR(1000) NULL DEFAULT NULL COMMENT '로그 메시지',
    `user_agent`    VARCHAR(500)  NOT NULL COMMENT '브라우저 정보',
    `created_at`    DATE          NOT NULL COMMENT '등록일',
    `created_by`    BIGINT(20)    NULL DEFAULT NULL COMMENT '등록자 일련번호',
    `modified_at`   DATE          NOT NULL COMMENT '수정일',
    `modified_by`   BIGINT(20)    NULL DEFAULT NULL COMMENT '수정자 일련번호',
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 4
    DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `saesig`.`adopt`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `saesig`.`adopt`;

CREATE TABLE IF NOT EXISTS `saesig`.`adopt`
(
    `id`                  BIGINT(20)     NOT NULL AUTO_INCREMENT COMMENT '분양 일련번호',
    `adopt_member_id`     BIGINT(20)     NULL     DEFAULT NULL COMMENT '예약(분양)자 일련번호',
    `hits`                BIGINT(20)     NOT NULL DEFAULT 0 COMMENT '조회수',
    `title`               VARCHAR(200)   NOT NULL COMMENT '제목',
    `content`             VARCHAR(1000)  NOT NULL COMMENT '내용',
    `gender`              VARCHAR(10)    NOT NULL COMMENT '성별',
    `age`                 INT(11)        NULL     DEFAULT NULL COMMENT '나이',
    `age_category`        VARCHAR(20)    NOT NULL COMMENT '나이 상태',
    `status`              VARCHAR(20)    NOT NULL COMMENT '분양상태',
    `is_deleted`          VARCHAR(1)     NOT NULL DEFAULT 'N' COMMENT '삭제 여부',
    `is_castrated`        VARCHAR(1)     NULL     DEFAULT NULL COMMENT '중성화여부',
    `responsibility_cost` DECIMAL(19, 4) NULL     DEFAULT NULL COMMENT '책임비',
    `etc_content`         VARCHAR(1000)  NULL     DEFAULT NULL COMMENT '기타 내용',
    `animal_division1_id` BIGINT(20)     NOT NULL COMMENT '동물_대분류 일련번호',
    `animal_division2_id` BIGINT(20)     NOT NULL COMMENT '동물_중분류 일련번호',
    `image_file_group_id` BIGINT(20)     NULL     DEFAULT NULL COMMENT '이미지 파일 그룹 일련번호',
    `sido`                VARCHAR(20)    NOT NULL COMMENT '지역_시도',
    `sigungu`             VARCHAR(20)    NOT NULL COMMENT '지역_시군구',
    `stop_reason`         VARCHAR(1000)  NULL     DEFAULT NULL COMMENT '분양중지 사유',
    `stop_category`       VARCHAR(20)    NULL     DEFAULT NULL COMMENT '분양 중지 유형',
    `modified_at`         DATETIME       NOT NULL COMMENT '수정일',
    `modified_by`         BIGINT(20)     NOT NULL COMMENT '수정자 일련번호',
    `created_at`          DATETIME       NOT NULL COMMENT '등록일',
    `created_by`          BIGINT(20)     NOT NULL COMMENT '등록자 일련번호',
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `saesig`.`adopt_history`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `saesig`.`adopt_history`;

CREATE TABLE IF NOT EXISTS `saesig`.`adopt_history`
(
    `id`            BIGINT(20)    NOT NULL AUTO_INCREMENT COMMENT '분양 상태 일련번호',
    `adopt_id`      BIGINT(20)    NOT NULL COMMENT '분양 일련번호',
    `before_status` VARCHAR(20)   NOT NULL COMMENT '분양 상태 변경 전 값',
    `after_status`  VARCHAR(20)   NOT NULL COMMENT '분양 상태 변경 후 값',
    `reason`        VARCHAR(1000) NULL DEFAULT NULL COMMENT '수정 사유',
    `member_id`     VARCHAR(1000) NOT NULL COMMENT '변경회원 일련번호',
    `modified_at`   DATETIME      NOT NULL COMMENT '수정일',
    `modified_by`   BIGINT(20)    NOT NULL COMMENT '수정자 일련번호',
    `created_at`    DATETIME      NOT NULL COMMENT '등록일',
    `created_by`    BIGINT(20)    NOT NULL COMMENT '등록자 일련번호',
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `saesig`.`adopt_interest`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `saesig`.`adopt_interest`;

CREATE TABLE IF NOT EXISTS `saesig`.`adopt_interest`
(
    `id`          BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '분양 관심 일련번호',
    `adopt_id`    BIGINT(20) NOT NULL COMMENT '분양 일련번호',
    `member_id`   BIGINT(20) NOT NULL COMMENT '회원 일련번호',
    `created_at`  DATETIME   NOT NULL COMMENT '등록일',
    `created_by`  BIGINT(20) NOT NULL COMMENT '등록자 일련번호',
    `modified_at` DATETIME   NOT NULL COMMENT '수정일',
    `modified_by` BIGINT(20) NOT NULL COMMENT '수정자 일련번호',
    PRIMARY KEY (`id`),
    INDEX `adopt_id` (`adopt_id` ASC) VISIBLE,
    CONSTRAINT `adopt_interest_ibfk_1`
        FOREIGN KEY (`adopt_id`)
            REFERENCES `saesig`.`adopt` (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `saesig`.`adopt_report`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `saesig`.`adopt_report`;

CREATE TABLE IF NOT EXISTS `saesig`.`adopt_report`
(
    `id`          BIGINT(20)    NOT NULL AUTO_INCREMENT COMMENT '신고 일련번호',
    `adopt_id`    BIGINT(20)    NOT NULL COMMENT '분양 일련번호',
    `category`    VARCHAR(20)   NOT NULL COMMENT '유형',
    `content`     VARCHAR(1000) NULL DEFAULT NULL COMMENT '내용',
    `member_id`   BIGINT(20)    NOT NULL COMMENT '신고자 회원 일련번호',
    `modified_at` DATETIME      NOT NULL COMMENT '수정일',
    `modified_by` BIGINT(20)    NOT NULL COMMENT '수정자 일련번호',
    `created_at`  DATETIME      NOT NULL COMMENT '등록일',
    `created_by`  BIGINT(20)    NOT NULL COMMENT '등록자 일련번호',
    PRIMARY KEY (`id`),
    INDEX `adopt_id` (`adopt_id` ASC) VISIBLE,
    CONSTRAINT `adopt_report_ibfk_1`
        FOREIGN KEY (`adopt_id`)
            REFERENCES `saesig`.`adopt` (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `saesig`.`vaccine`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `saesig`.`vaccine`;

CREATE TABLE IF NOT EXISTS `saesig`.`vaccine`
(
    `id`   BIGINT(20)  NOT NULL AUTO_INCREMENT COMMENT '백신 일련번호',
    `name` VARCHAR(20) NOT NULL COMMENT '백신 이름',
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `saesig`.`adopt_vaccine`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `saesig`.`adopt_vaccine`;

CREATE TABLE IF NOT EXISTS `saesig`.`adopt_vaccine`
(
    `id`          BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '분양 백신 일련번호',
    `adopt_id`    BIGINT(20) NOT NULL COMMENT '분양 일련번호',
    `vaccine_id`  BIGINT(20) NOT NULL COMMENT '백신 일련번호',
    `modified_at` DATETIME   NOT NULL COMMENT '수정일',
    `modified_by` BIGINT(20) NOT NULL COMMENT '수정자 일련번호',
    `created_at`  DATETIME   NOT NULL COMMENT '등록일',
    `created_by`  BIGINT(20) NOT NULL COMMENT '등록자 일련번호',
    PRIMARY KEY (`id`),
    INDEX `adopt_id` (`adopt_id` ASC) VISIBLE,
    INDEX `vaccine_id` (`vaccine_id` ASC) VISIBLE,
    CONSTRAINT `adopt_vaccine_ibfk_1`
        FOREIGN KEY (`adopt_id`)
            REFERENCES `saesig`.`adopt` (`id`),
    CONSTRAINT `adopt_vaccine_ibfk_2`
        FOREIGN KEY (`vaccine_id`)
            REFERENCES `saesig`.`vaccine` (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `saesig`.`animal_division1`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `saesig`.`animal_division1`;

CREATE TABLE IF NOT EXISTS `saesig`.`animal_division1`
(
    `id`       BIGINT(20)  NOT NULL AUTO_INCREMENT COMMENT '동물 대분류 일련번호',
    `category` VARCHAR(20) NOT NULL COMMENT '유형',
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `saesig`.`animal_division2`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `saesig`.`animal_division2`;

CREATE TABLE IF NOT EXISTS `saesig`.`animal_division2`
(
    `id`                  BIGINT(20)  NOT NULL AUTO_INCREMENT COMMENT '동물 중분류 일련번호',
    `animal_division1_id` BIGINT(20)  NOT NULL COMMENT '동물 대분류 일련번호',
    `category`            VARCHAR(20) NOT NULL COMMENT '유형',
    PRIMARY KEY (`id`),
    INDEX `animal_division1_id` (`animal_division1_id` ASC) VISIBLE,
    CONSTRAINT `animal_division2_ibfk_1`
        FOREIGN KEY (`animal_division1_id`)
            REFERENCES `saesig`.`animal_division1` (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `saesig`.`banner`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `saesig`.`banner`;

CREATE TABLE IF NOT EXISTS `saesig`.`banner`
(
    `id`                  BIGINT(20)   NOT NULL AUTO_INCREMENT COMMENT '배너 일련번호',
    `title`               VARCHAR(200) NOT NULL COMMENT '제목',
    `exposure_location`   VARCHAR(20)  NOT NULL COMMENT '배너 노출위치',
    `image_file_group_id` BIGINT(20)   NOT NULL DEFAULT 0 COMMENT '이미지 파일 그룹 일련번호',
    `url`                 VARCHAR(200) NULL     DEFAULT NULL COMMENT '링크 URL',
    `ord`                 BIGINT(20)   NULL     DEFAULT NULL COMMENT '순서',
    `is_enabled`          VARCHAR(1)   NOT NULL DEFAULT 'N' COMMENT '사용 여부',
    `created_at`          DATETIME     NOT NULL COMMENT '등록일',
    `created_by`          BIGINT(20)   NOT NULL COMMENT '등록자 일련번호',
    `modified_at`         DATETIME     NOT NULL COMMENT '수정일',
    `modified_by`         BIGINT(20)   NOT NULL COMMENT '수정자 일련번호',
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `saesig`.`member`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `saesig`.`member`;

CREATE TABLE IF NOT EXISTS `saesig`.`member`
(
    `id`                          BIGINT(20)   NOT NULL AUTO_INCREMENT COMMENT '회원 일련번호',
    `email`                       VARCHAR(50)  NOT NULL COMMENT '이메일',
    `password`                    VARCHAR(200) NULL     DEFAULT NULL COMMENT '비밀번호',
    `prev_password`               VARCHAR(200) NULL     DEFAULT NULL COMMENT '직전비밀번호',
    `signup_method`               VARCHAR(20)  NULL     DEFAULT NULL COMMENT '가입 수단',
    `nickname`                    VARCHAR(50)  NULL     DEFAULT NULL COMMENT '닉네임',
    `mobile_number`               VARCHAR(20)  NULL     DEFAULT NULL COMMENT '전화번호',
    `status`                      VARCHAR(20)  NOT NULL COMMENT '상태',
    `last_logged_at`              DATETIME     NULL     DEFAULT NULL COMMENT '마지막 접속일',
    `failed_login_attempt`        INT(11)      NOT NULL DEFAULT 0,
    `service_agreement`           VARCHAR(1)   NOT NULL COMMENT '서비스 이용약관 동의 여부',
    `location_service_agreement`  VARCHAR(1)   NOT NULL DEFAULT 'N' COMMENT '위치 기반 서비스 이용동의',
    `privacy_agreement`           VARCHAR(1)   NOT NULL COMMENT '개인정보 수집 및 이용동의',
    `password_modified_at`        DATETIME     NULL     DEFAULT NULL COMMENT '비밀번호 마지막 수정일',
    `marketing_service_agreement` VARCHAR(1)   NOT NULL DEFAULT 'N' COMMENT '마케팅 서비스 이용동의',
    `deleted_at`                  DATETIME     NULL     DEFAULT NULL COMMENT '탈퇴일',
    `modified_at`                 DATETIME     NOT NULL COMMENT '수정일',
    `modified_by`                 BIGINT(20)   NULL     DEFAULT NULL COMMENT '수정자 일련번호',
    `created_at`                  DATETIME     NOT NULL COMMENT '등록일',
    `created_by`                  BIGINT(20)   NULL     DEFAULT NULL COMMENT '등록자 일련번호',
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 3
    DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `saesig`.`blocked_member`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `saesig`.`blocked_member`;

CREATE TABLE IF NOT EXISTS `saesig`.`blocked_member`
(
    `id`                BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '회원 차단 일련번호',
    `member_id`         BIGINT(20) NOT NULL COMMENT '회원 일련번호',
    `blocked_member_id` BIGINT(20) NOT NULL COMMENT '차단 대상 회원 일련번호',
    `modified_at`       DATETIME   NOT NULL COMMENT '수정일',
    `modified_by`       BIGINT(20) NOT NULL COMMENT '수정자 일련번호',
    `created_at`        DATETIME   NOT NULL COMMENT '등록일',
    `created_by`        BIGINT(20) NOT NULL COMMENT '등록자 일련번호',
    PRIMARY KEY (`id`),
    INDEX `member_id` (`member_id` ASC) VISIBLE,
    CONSTRAINT `blocked_member_ibfk_1`
        FOREIGN KEY (`member_id`)
            REFERENCES `saesig`.`member` (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `saesig`.`board`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `saesig`.`board`;

CREATE TABLE IF NOT EXISTS `saesig`.`board`
(
    `id`           BIGINT(20)    NOT NULL AUTO_INCREMENT COMMENT '게시판 일련번호',
    `name`         VARCHAR(200)  NULL     DEFAULT NULL COMMENT '이름',
    `description`  VARCHAR(2000) NULL     DEFAULT NULL COMMENT '내용',
    `use_category` VARCHAR(1)    NOT NULL DEFAULT 'N' COMMENT '유형 사용 여부',
    `use_board`    VARCHAR(1)    NOT NULL DEFAULT 'Y' COMMENT '게시판 사용 여부',
    `use_fix`      VARCHAR(1)    NOT NULL DEFAULT 'N' COMMENT '공지 사용 여부',
    `modified_at`  DATETIME      NOT NULL COMMENT '수정일',
    `modified_by`  BIGINT(20)    NOT NULL COMMENT '수정자 일련번호',
    `created_at`   DATETIME      NOT NULL COMMENT '등록일',
    `created_by`   BIGINT(20)    NOT NULL COMMENT '등록자 일련번호',
    `category`     VARCHAR(20)   NULL     DEFAULT NULL COMMENT '게시판 유형 ',
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 2
    DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `saesig`.`chat_open_reason`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `saesig`.`chat_open_reason`;

CREATE TABLE IF NOT EXISTS `saesig`.`chat_open_reason`
(
    `id`          BIGINT(20)    NOT NULL AUTO_INCREMENT COMMENT '채팅이력 열람 사유 일련번호',
    `member_id`   BIGINT(20)    NOT NULL COMMENT '열람자 일련번호',
    `category`    VARCHAR(20)   NOT NULL COMMENT '열람 사유 유형',
    `etc_reason`  VARCHAR(1000) NULL DEFAULT NULL COMMENT '기타 사유',
    `modified_at` DATETIME      NOT NULL COMMENT '수정일',
    `modified_by` BIGINT(20)    NOT NULL COMMENT '수정자 일련번호',
    `created_at`  DATETIME      NOT NULL COMMENT '등록일',
    `created_by`  BIGINT(20)    NOT NULL COMMENT '등록자 일련번호',
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `saesig`.`chatting_room`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `saesig`.`chatting_room`;

CREATE TABLE IF NOT EXISTS `saesig`.`chatting_room`
(
    `id`         BIGINT(20)  NOT NULL AUTO_INCREMENT COMMENT '채팅방 회원 일련번호',
    `title`      VARCHAR(20) NOT NULL COMMENT '채팅방 제목',
    `chat_id`    BIGINT(20)  NOT NULL COMMENT '채팅방 일련번호',
    `adopt_id`   BIGINT(20)  NOT NULL COMMENT '채팅방 연관 게시글 일련번호',
    `member_id`  BIGINT(20)  NOT NULL COMMENT '회원 일련번호',
    `created_at` DATETIME    NULL DEFAULT CURRENT_TIMESTAMP() COMMENT '등록일',
    `created_by` BIGINT(20)  NULL DEFAULT NULL COMMENT '등록자 일련번호',
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `saesig`.`diary`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `saesig`.`diary`;

CREATE TABLE IF NOT EXISTS `saesig`.`diary`
(
    `id`                  BIGINT(20)    NOT NULL AUTO_INCREMENT COMMENT '일기 일련번호',
    `title`               VARCHAR(200)  NOT NULL COMMENT '제목',
    `content`             VARCHAR(2000) NOT NULL COMMENT '내용',
    `image_file_group_id` BIGINT(20)    NULL     DEFAULT NULL COMMENT '이미지 파일 그룹 일련번호',
    `weather_category`    VARCHAR(20)   NOT NULL COMMENT '날씨 유형',
    `category`            VARCHAR(20)   NOT NULL COMMENT '카테고리',
    `status`              VARCHAR(20)   NOT NULL COMMENT '상태',
    `is_secret`           VARCHAR(1)    NOT NULL DEFAULT 'N' COMMENT '비밀 여부',
    `is_deleted`          VARCHAR(1)    NOT NULL DEFAULT 'N' COMMENT '삭제 여부',
    `hits`                INT(11)       NOT NULL DEFAULT 0 COMMENT '조회수',
    `created_at`          DATETIME      NOT NULL COMMENT '등록일',
    `created_by`          BIGINT(20)    NOT NULL COMMENT '등록자 일련번호',
    `modified_at`         DATETIME      NOT NULL COMMENT '수정일',
    `modified_by`         BIGINT(20)    NOT NULL COMMENT '수정자 일련번호',
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `saesig`.`diary_comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `saesig`.`diary_comment`;

CREATE TABLE IF NOT EXISTS `saesig`.`diary_comment`
(
    `id`          BIGINT(20)    NOT NULL AUTO_INCREMENT COMMENT '댓글 일련번호',
    `diary_id`    BIGINT        NOT NULL COMMENT '일기 일련번호',
    `upper_id`    BIGINT(20)    NULL COMMENT '상위 댓글 일련번호',
    `content`     VARCHAR(1000) NOT NULL COMMENT '내용',
    `is_deleted`  VARCHAR(1)    NOT NULL COMMENT '삭제 여부',
    `depth`       INT(11)       NOT NULL COMMENT '깊이',
    `created_at`  DATETIME      NOT NULL COMMENT '등록일',
    `created_by`  BIGINT(20)    NOT NULL COMMENT '등록자 일련번호',
    `modified_at` DATETIME      NOT NULL COMMENT '수정일',
    `modified_by` BIGINT(20)    NOT NULL COMMENT '수정자 일련번호',
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `saesig`.`diary_interest`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `saesig`.`diary_interest`;

CREATE TABLE IF NOT EXISTS `saesig`.`diary_interest`
(
    `id`          BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '일기 관심 일련번호',
    `diary_id`    BIGINT(20) NOT NULL COMMENT '일기 일련번호',
    `member_id`   BIGINT(20) NOT NULL COMMENT '회원 일련번호',
    `created_at`  DATETIME   NOT NULL COMMENT '등록일',
    `created_by`  BIGINT(20) NOT NULL COMMENT '등록자 일련번호',
    `modified_at` DATETIME   NOT NULL COMMENT '수정일',
    `modified_by` BIGINT(20) NOT NULL COMMENT '수정자 일련번호',
    PRIMARY KEY (`id`),
    INDEX `diary_id` (`diary_id` ASC) VISIBLE,
    CONSTRAINT `diary_interest_ibfk_1`
        FOREIGN KEY (`diary_id`)
            REFERENCES `saesig`.`diary` (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `saesig`.`diary_report`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `saesig`.`diary_report`;

CREATE TABLE IF NOT EXISTS `saesig`.`diary_report`
(
    `id`          BIGINT(20)    NOT NULL AUTO_INCREMENT COMMENT '신고 일련번호',
    `diary_id`    BIGINT(20)    NOT NULL COMMENT '일기 일련번호',
    `category`    VARCHAR(20)   NOT NULL COMMENT '유형',
    `content`     VARCHAR(1000) NULL DEFAULT NULL COMMENT '내용',
    `member_id`   BIGINT(20)    NOT NULL COMMENT '신고자 회원 일련번호',
    `modified_at` DATETIME      NOT NULL COMMENT '수정일',
    `modified_by` BIGINT(20)    NOT NULL COMMENT '수정자 일련번호',
    `created_at`  DATETIME      NOT NULL COMMENT '등록일',
    `created_by`  BIGINT(20)    NOT NULL COMMENT '등록자 일련번호',
    PRIMARY KEY (`id`),
    INDEX `diary_id` (`diary_id` ASC) VISIBLE,
    CONSTRAINT `diary_report_ibfk_1`
        FOREIGN KEY (`diary_id`)
            REFERENCES `saesig`.`diary` (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `saesig`.`tag`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `saesig`.`tag`;

CREATE TABLE IF NOT EXISTS `saesig`.`tag`
(
    `id`   BIGINT(20)  NOT NULL AUTO_INCREMENT COMMENT '태그 일련번호',
    `name` VARCHAR(20) NOT NULL COMMENT '태그 이름',
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `saesig`.`diary_tag`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `saesig`.`diary_tag`;

CREATE TABLE IF NOT EXISTS `saesig`.`diary_tag`
(
    `id`          BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '일기 태그 일련번호',
    `diary_id`    BIGINT(20) NOT NULL COMMENT '일기 일련번호',
    `tag_id`      BIGINT(20) NOT NULL COMMENT '태그 일련번호',
    `created_at`  DATETIME   NOT NULL COMMENT '등록일',
    `created_by`  BIGINT(20) NOT NULL COMMENT '등록자 일련번호',
    `modified_at` DATETIME   NOT NULL COMMENT '수정일',
    `modified_by` BIGINT(20) NOT NULL COMMENT '수정자 일련번호',
    PRIMARY KEY (`id`),
    INDEX `diary_id` (`diary_id` ASC) VISIBLE,
    INDEX `tag_id` (`tag_id` ASC) VISIBLE,
    CONSTRAINT `diary_tag_ibfk_1`
        FOREIGN KEY (`diary_id`)
            REFERENCES `saesig`.`diary` (`id`),
    CONSTRAINT `diary_tag_ibfk_2`
        FOREIGN KEY (`tag_id`)
            REFERENCES `saesig`.`tag` (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `saesig`.`dormant_member`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `saesig`.`dormant_member`;

CREATE TABLE IF NOT EXISTS `saesig`.`dormant_member`
(
    `id`                          BIGINT(20)   NOT NULL COMMENT '휴면 회원 일련번호',
    `member_id`                   BIGINT(20)   NOT NULL COMMENT '회원 일련번호',
    `email`                       VARCHAR(50)  NOT NULL COMMENT '이메일',
    `password`                    VARCHAR(200) NULL     DEFAULT NULL COMMENT '비밀번호',
    `prev_password`               VARCHAR(200) NULL     DEFAULT NULL COMMENT '비밀번호',
    `signup_method`               VARCHAR(20)  NULL     DEFAULT NULL COMMENT '가입 수단',
    `mobile_number`               VARCHAR(20)  NULL     DEFAULT NULL COMMENT '전화번호',
    `nickname`                    VARCHAR(50)  NULL     DEFAULT NULL COMMENT '닉네임',
    `status`                      VARCHAR(20)  NOT NULL COMMENT '상태',
    `last_logged_at`              DATETIME     NOT NULL COMMENT '마지막 접속일',
    `failed_login_attempt`        INT(11)      NOT NULL DEFAULT 0,
    `service_agreement`           VARCHAR(1)   NOT NULL COMMENT '서비스 이용약관 동의 여부',
    `location_service_agreement`  VARCHAR(1)   NOT NULL DEFAULT 'N' COMMENT '위치 기반 서비스 이용동의',
    `privacy_agreement`           VARCHAR(1)   NOT NULL COMMENT '개인정보 수집 및 이용동의',
    `password_modified_at`        DATETIME     NOT NULL COMMENT '비밀번호 마지막 수정일',
    `marketing_service_agreement` VARCHAR(1)   NOT NULL DEFAULT 'N' COMMENT '마케팅 서비스 이용동의',
    `modified_at`                 DATETIME     NOT NULL COMMENT '수정일',
    `modified_by`                 BIGINT(20)   NULL     DEFAULT NULL COMMENT '수정자 일련번호',
    `created_at`                  DATETIME     NOT NULL COMMENT '등록일',
    `created_by`                  BIGINT(20)   NULL     DEFAULT NULL COMMENT '등록자 일련번호'
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `saesig`.`faq`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `saesig`.`faq`;

CREATE TABLE IF NOT EXISTS `saesig`.`faq`
(
    `id`          BIGINT(20)   NOT NULL AUTO_INCREMENT COMMENT 'FAQ 일련번호',
    `category`    VARCHAR(20)  NOT NULL COMMENT '유형',
    `title`       VARCHAR(300) NOT NULL COMMENT '제목',
    `content`     LONGTEXT     NOT NULL COMMENT '내용',
    `ord`         BIGINT(20)   NOT NULL COMMENT '순서',
    `is_enabled`  CHAR(1)      NOT NULL DEFAULT 'Y' COMMENT '사용 여부',
    `created_at`  DATETIME     NOT NULL COMMENT '등록일',
    `created_by`  BIGINT(20)   NOT NULL COMMENT '등록자 일련번호',
    `modified_at` DATETIME     NOT NULL COMMENT '수정일',
    `modified_by` BIGINT(20)   NOT NULL COMMENT '수정자 일련번호',
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `saesig`.`file_group`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `saesig`.`file_group`;

CREATE TABLE IF NOT EXISTS `saesig`.`file_group`
(
    `id`          BIGINT(20)   NOT NULL AUTO_INCREMENT COMMENT '파일 그룹 일련번호',
    `path`        VARCHAR(200) NOT NULL COMMENT '경로명',
    `modified_at` DATETIME     NOT NULL COMMENT '수정일',
    `modified_by` BIGINT(20)   NOT NULL COMMENT '수정자 일련번호',
    `created_at`  DATETIME     NOT NULL COMMENT '등록일',
    `created_by`  BIGINT(20)   NOT NULL COMMENT '등록자 일련번호',
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `saesig`.`file`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `saesig`.`file`;

CREATE TABLE IF NOT EXISTS `saesig`.`file`
(
    `id`          BIGINT(20)   NOT NULL AUTO_INCREMENT COMMENT '파일 일련번호',
    `group_id`    BIGINT(20)   NOT NULL COMMENT '파일 그룹 일련번호',
    `saved_name`  VARCHAR(500) NOT NULL COMMENT '저장 파일 이름',
    `origin_name` VARCHAR(500) NOT NULL COMMENT '원본 파일 이름',
    `extension`   VARCHAR(20)  NOT NULL COMMENT '확장자',
    `size`        BIGINT(20)   NOT NULL COMMENT '크기',
    `ord`         INT(11)      NULL DEFAULT NULL COMMENT '순서',
    `modified_at` DATETIME     NOT NULL COMMENT '수정일',
    `modified_by` BIGINT(20)   NOT NULL COMMENT '수정자 일련번호',
    `created_at`  DATETIME     NOT NULL COMMENT '등록일',
    `created_by`  BIGINT(20)   NOT NULL COMMENT '등록자 일련번호',
    PRIMARY KEY (`id`),
    INDEX `group_id` (`group_id` ASC) VISIBLE,
    CONSTRAINT `file_ibfk_1`
        FOREIGN KEY (`group_id`)
            REFERENCES `saesig`.`file_group` (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `saesig`.`inquiry`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `saesig`.`inquiry`;

CREATE TABLE IF NOT EXISTS `saesig`.`inquiry`
(
    `id`             BIGINT(20)    NOT NULL AUTO_INCREMENT COMMENT '문의 일련번호',
    `category`       VARCHAR(20)   NOT NULL COMMENT '유형',
    `incoming_email` VARCHAR(50)   NOT NULL COMMENT '수신 이메일',
    `status`         VARCHAR(20)   NOT NULL COMMENT '상태',
    `title`          VARCHAR(300)  NOT NULL COMMENT '제목',
    `content`        VARCHAR(2000) NOT NULL COMMENT '내용',
    `is_deleted`     CHAR(1)       NOT NULL DEFAULT 'N' COMMENT '삭제 여부',
    `created_at`     DATETIME      NOT NULL COMMENT '등록일',
    `created_by`     BIGINT(20)    NOT NULL COMMENT '등록자 일련번호',
    `modified_at`    DATETIME      NOT NULL COMMENT '수정일',
    `modified_by`    BIGINT(20)    NOT NULL COMMENT '수정자 일련번호',
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `saesig`.`inquiry_answer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `saesig`.`inquiry_answer`;

CREATE TABLE IF NOT EXISTS `saesig`.`inquiry_answer`
(
    `id`          BIGINT(20)    NOT NULL AUTO_INCREMENT COMMENT '답변 일련번호',
    `inquiry_id`  BIGINT(20)    NOT NULL COMMENT '문의 일련번호',
    `title`       VARCHAR(300)  NOT NULL COMMENT '제목',
    `content`     VARCHAR(2000) NOT NULL COMMENT '내용',
    `created_at`  DATETIME      NOT NULL COMMENT '등록일',
    `created_by`  BIGINT(20)    NOT NULL COMMENT '등록자 일련번호',
    `modified_at` DATETIME      NOT NULL COMMENT '수정일',
    `modified_by` BIGINT(20)    NOT NULL COMMENT '수정자 일련번호',
    PRIMARY KEY (`id`),
    INDEX `inquiry_id` (`inquiry_id` ASC) VISIBLE,
    CONSTRAINT `inquiry_answer_ibfk_1`
        FOREIGN KEY (`inquiry_id`)
            REFERENCES `saesig`.`inquiry` (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `saesig`.`role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `saesig`.`role`;

CREATE TABLE IF NOT EXISTS `saesig`.`role`
(
    `id`          BIGINT(20)   NOT NULL AUTO_INCREMENT COMMENT '역할 일련번호',
    `name`        VARCHAR(100) NULL     DEFAULT NULL COMMENT '이름',
    `upper_id`    BIGINT(20)   NULL     DEFAULT NULL COMMENT '상위 역할 일련번호',
    `is_enabled`  CHAR(1)      NOT NULL DEFAULT 'Y' COMMENT '사용여부',
    `description` VARCHAR(500) NULL     DEFAULT NULL COMMENT '권한설명',
    `modified_at` DATETIME     NOT NULL COMMENT '수정일',
    `modified_by` BIGINT(20)   NOT NULL COMMENT '수정자 일련번호',
    `created_at`  DATETIME     NOT NULL COMMENT '등록일',
    `created_by`  BIGINT(20)   NOT NULL COMMENT '등록자 일련번호',
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 2
    DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `saesig`.`member_role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `saesig`.`member_role`;

CREATE TABLE IF NOT EXISTS `saesig`.`member_role`
(
    `id`          BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '회원 역할 일련번호',
    `member_id`   BIGINT(20) NOT NULL COMMENT '회원 일련번호',
    `role_id`     BIGINT(20) NOT NULL COMMENT '자원 일련번호',
    `modified_at` DATETIME   NOT NULL COMMENT '수정일',
    `modified_by` BIGINT(20) NOT NULL COMMENT '수정자 일련번호',
    `created_at`  DATETIME   NOT NULL COMMENT '등록일',
    `created_by`  BIGINT(20) NOT NULL COMMENT '등록자 일련번호',
    PRIMARY KEY (`id`),
    INDEX `member_id` (`member_id` ASC) VISIBLE,
    INDEX `role_id` (`role_id` ASC) VISIBLE,
    CONSTRAINT `member_role_ibfk_1`
        FOREIGN KEY (`member_id`)
            REFERENCES `saesig`.`member` (`id`),
    CONSTRAINT `member_role_ibfk_2`
        FOREIGN KEY (`role_id`)
            REFERENCES `saesig`.`role` (`id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 2
    DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `saesig`.`policy`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `saesig`.`policy`;

CREATE TABLE IF NOT EXISTS `saesig`.`policy`
(
    `id`          BIGINT(20)   NOT NULL AUTO_INCREMENT COMMENT '약관 및 정책 일련번호',
    `category`    VARCHAR(20)  NOT NULL COMMENT '유형',
    `title`       VARCHAR(200) NOT NULL COMMENT '제목',
    `content`     LONGTEXT     NOT NULL COMMENT '내용',
    `modified_at` DATETIME     NOT NULL COMMENT '수정일',
    `modified_by` BIGINT(20)   NOT NULL COMMENT '수정자 일련번호',
    `created_at`  DATETIME     NOT NULL COMMENT '등록일',
    `created_by`  BIGINT(20)   NOT NULL COMMENT '등록자 일련번호',
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `saesig`.`popup`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `saesig`.`popup`;

CREATE TABLE IF NOT EXISTS `saesig`.`popup`
(
    `id`                  BIGINT(20)   NOT NULL AUTO_INCREMENT COMMENT '팝업 일련번호',
    `title`               VARCHAR(200) NOT NULL COMMENT '제목',
    `exposure_location`   VARCHAR(20)  NOT NULL COMMENT '팝업 노출위치',
    `target`              VARCHAR(20)  NOT NULL COMMENT '공지 대상 코드(A:전체, S: 선택)',
    `target_role_id`      BIGINT(20)   NOT NULL COMMENT '공지 대상 역할 일련번호',
    `start_date`          DATE         NOT NULL COMMENT '팝업 시작일',
    `end_date`            DATE         NOT NULL COMMENT '팝업 종료일',
    `image_file_group_id` BIGINT(20)   NOT NULL COMMENT '이미지 파일 그룹 일련번호',
    `url`                 VARCHAR(200) NULL     DEFAULT NULL COMMENT '링크 URL',
    `button_option`       VARCHAR(20)  NOT NULL COMMENT '버튼 옵션',
    `ord`                 BIGINT(20)   NULL     DEFAULT NULL COMMENT '순서',
    `is_enabled`          VARCHAR(1)   NOT NULL DEFAULT 'N' COMMENT '사용 여부',
    `created_at`          DATETIME     NOT NULL COMMENT '등록일',
    `created_by`          BIGINT(20)   NOT NULL COMMENT '등록자 일련번호',
    `modified_at`         DATETIME     NOT NULL COMMENT '수정일',
    `modified_by`         BIGINT(20)   NOT NULL COMMENT '수정자 일련번호',
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `saesig`.`post`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `saesig`.`post`;

CREATE TABLE IF NOT EXISTS `saesig`.`post`
(
    `id`                   BIGINT(20)   NOT NULL AUTO_INCREMENT COMMENT '게시글 일련번호',
    `board_id`             BIGINT(20)   NOT NULL COMMENT '게시판 일련번호',
    `category`             VARCHAR(20)  NOT NULL COMMENT '유형',
    `title`                VARCHAR(200) NOT NULL COMMENT '제목',
    `content`              LONGTEXT     NOT NULL COMMENT '내용',
    `attach_file_group_id` BIGINT(20)   NULL     DEFAULT NULL COMMENT '첨부 파일 그룹 일련번호',
    `is_deleted`           VARCHAR(1)   NOT NULL DEFAULT 'N' COMMENT '삭제 여부',
    `hits`                 INT(11)      NOT NULL DEFAULT 0 COMMENT '조회수',
    `is_fixed`             VARCHAR(1)   NOT NULL DEFAULT 'N' COMMENT '공지 여부',
    `fix_start_date`       DATETIME     NULL     DEFAULT NULL COMMENT '공지 시작 일시',
    `fix_end_date`         DATETIME     NULL     DEFAULT NULL COMMENT '공지 종료 일시',
    `modified_at`          DATETIME     NOT NULL COMMENT '수정일',
    `modified_by`          BIGINT(20)   NOT NULL COMMENT '수정자 일련번호',
    `created_at`           DATETIME     NOT NULL COMMENT '등록일',
    `created_by`           BIGINT(20)   NOT NULL COMMENT '등록자 일련번호',
    PRIMARY KEY (`id`),
    INDEX `board_id` (`board_id` ASC) VISIBLE,
    CONSTRAINT `post_ibfk_1`
        FOREIGN KEY (`board_id`)
            REFERENCES `saesig`.`board` (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `saesig`.`resource`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `saesig`.`resource`;

CREATE TABLE IF NOT EXISTS `saesig`.`resource`
(
    `id`                  BIGINT(20)   NOT NULL AUTO_INCREMENT COMMENT '자원 일련번호',
    `name`                VARCHAR(100) NOT NULL COMMENT '이름',
    `url`                 VARCHAR(500) NULL     DEFAULT NULL COMMENT '자원 URL',
    `http_method`         VARCHAR(10)  NULL     DEFAULT NULL COMMENT 'HTTP 메소드',
    `is_enabled`          CHAR(1)      NOT NULL DEFAULT 'Y' COMMENT '사용여부',
    `is_login_disallowed` CHAR(1)      NOT NULL DEFAULT 'N' COMMENT '비로그인 허용 여부',
    `category`            VARCHAR(20)  NOT NULL COMMENT '분류',
    `type`                VARCHAR(20)  NOT NULL COMMENT '유형',
    `style_class`         VARCHAR(500) NULL     DEFAULT NULL COMMENT '스타일 클래스',
    `depth`               INT(11)      NULL     DEFAULT NULL COMMENT '깊이',
    `ord`                 INT(11)      NULL     DEFAULT NULL COMMENT '순서',
    `upper_id`            BIGINT(20)   NULL     DEFAULT NULL COMMENT '상위 자원 일련번호',
    `modified_at`         DATETIME     NOT NULL COMMENT '수정일',
    `modified_by`         BIGINT(20)   NOT NULL COMMENT '수정자 일련번호',
    `created_at`          DATETIME     NOT NULL COMMENT '등록일',
    `created_by`          BIGINT(20)   NOT NULL COMMENT '등록자 일련번호',
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 24
    DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `saesig`.`role_resource`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `saesig`.`role_resource`;

CREATE TABLE IF NOT EXISTS `saesig`.`role_resource`
(
    `id`          BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '역할 자원 일련번호',
    `role_id`     BIGINT(20) NOT NULL COMMENT '역할 일련번호',
    `resource_id` BIGINT(20) NOT NULL COMMENT '자원 일련번호',
    `modified_at` DATETIME   NOT NULL COMMENT '수정일',
    `modified_by` BIGINT(20) NOT NULL COMMENT '수정자 일련번호',
    `created_at`  DATETIME   NOT NULL COMMENT '등록일',
    `created_by`  BIGINT(20) NOT NULL COMMENT '등록자 일련번호',
    PRIMARY KEY (`id`),
    INDEX `role_id` (`role_id` ASC) VISIBLE,
    INDEX `resource_id` (`resource_id` ASC) VISIBLE,
    CONSTRAINT `role_resource_ibfk_1`
        FOREIGN KEY (`role_id`)
            REFERENCES `saesig`.`role` (`id`),
    CONSTRAINT `role_resource_ibfk_2`
        FOREIGN KEY (`resource_id`)
            REFERENCES `saesig`.`resource` (`id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 24
    DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `saesig`.`send_template`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `saesig`.`send_template`;

CREATE TABLE IF NOT EXISTS `saesig`.`send_template`
(
    `id`          BIGINT(20)   NOT NULL AUTO_INCREMENT COMMENT '템플릿 일련번호',
    `method`      VARCHAR(20)  NOT NULL COMMENT '발송 수단',
    `title`       VARCHAR(300) NOT NULL COMMENT '제목',
    `content`     TEXT         NOT NULL COMMENT '내용',
    `category`    VARCHAR(20)  NOT NULL COMMENT '발송 유형',
    `is_enabled`  CHAR(1)      NOT NULL DEFAULT 'Y' COMMENT '사용 여부',
    `time_point`  VARCHAR(20)  NOT NULL COMMENT '발송 시점',
    `created_at`  DATETIME     NOT NULL COMMENT '등록일',
    `created_by`  BIGINT(20)   NOT NULL COMMENT '등록자 일련번호',
    `modified_at` DATETIME     NOT NULL COMMENT '수정일',
    `modified_by` BIGINT(20)   NOT NULL COMMENT '수정자 일련번호',
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `saesig`.`send_history`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `saesig`.`send_history`;

CREATE TABLE IF NOT EXISTS `saesig`.`send_history`
(
    `id`               BIGINT(20)  NOT NULL AUTO_INCREMENT COMMENT '발송이력 일련번호',
    `send_template_id` BIGINT(20)  NOT NULL COMMENT '템플릿 일련번호',
    `content`          TEXT        NOT NULL COMMENT '발송내용',
    `recipient_id`     BIGINT(20)  NOT NULL COMMENT '수신자 일련번호',
    `recipient_email`  VARCHAR(50) NOT NULL COMMENT '수신자 이메일',
    `sender_id`        BIGINT(20)  NOT NULL COMMENT '발신자 일련번호',
    `sender_email`     VARCHAR(50) NOT NULL COMMENT '발신자 이메일',
    `sended_at`        DATETIME    NOT NULL COMMENT '발송일시',
    `created_at`       DATETIME    NOT NULL COMMENT '등록일',
    `created_by`       BIGINT(20)  NOT NULL COMMENT '등록자 일련번호',
    `modified_at`      DATETIME    NOT NULL COMMENT '수정일',
    `modified_by`      BIGINT(20)  NOT NULL COMMENT '수정자 일련번호',
    PRIMARY KEY (`id`),
    INDEX `send_template_id` (`send_template_id` ASC) VISIBLE,
    CONSTRAINT `send_history_ibfk_1`
        FOREIGN KEY (`send_template_id`)
            REFERENCES `saesig`.`send_template` (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4;

-- -----------------------------------------------------
-- Table `saesig`.`chatting_room`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `saesig`.`chatting_room`;

CREATE TABLE IF NOT EXISTS `chatting_room`
(
    `id`         BIGINT      NOT NULL AUTO_INCREMENT COMMENT '채팅방 회원 일련번호',
    `title`      varchar(20) NOT NULL COMMENT '채팅방 제목',
    `chat_id`    BIGINT      NOT NULL COMMENT '채팅방 일련번호',
    `adopt_id`   BIGINT      NOT NULL COMMENT '채팅방 연관 게시글 일련번호',
    `member_id`  BIGINT      NOT NULL COMMENT '회원 일련번호',
    `created_at` DATETIME DEFAULT NOW() COMMENT '등록일',
    `created_by` BIGINT COMMENT '등록자 일련번호',
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4;

-- -----------------------------------------------------
-- Table `saesig`.`chatting_room`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `diary_comment_interest`;

CREATE TABLE `diary_comment_interest`
(
    `id`          BIGINT AUTO_INCREMENT NOT NULL COMMENT '일련번호',
    `comment_id`  BIGINT                NOT NULL COMMENT '댓글 일련번호',
    `member_id`   BIGINT                NOT NULL COMMENT '회원 일련번호',
    `created_at`  DATETIME              NOT NULL COMMENT '등록일',
    `created_by`  BIGINT                NOT NULL COMMENT '등록자 일련번호',
    `modified_at` DATETIME              NOT NULL COMMENT '수정일',
    `modified_by` BIGINT                NOT NULL COMMENT '수정자 일련번호',
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4;

SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;
