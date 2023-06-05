CREATE TABLE `member`
(
    `id`                          BIGINT AUTO_INCREMENT NOT NULL COMMENT '회원 일련번호',
    `email`                       VARCHAR(50)           NOT NULL COMMENT '이메일',
    `password`                    VARCHAR(100)          NOT NULL COMMENT '비밀번호',
    `signup_method`               VARCHAR(20)           NULL COMMENT '가입 수단',
    `nickname`                    VARCHAR(50)           NULL COMMENT '닉네임',
    `status`                      VARCHAR(20)           NOT NULL COMMENT '상태',
    `joined_at`                   DATETIME              NOT NULL COMMENT '가입일',
    `modified_at`                 DATETIME              NOT NULL COMMENT '수정일',
    `modified_by`                 BIGINT                NOT NULL COMMENT '수정자 일련번호',
    `created_at`                  DATETIME              NOT NULL COMMENT '등록일',
    `created_by`                  BIGINT                NOT NULL COMMENT '등록자 일련번호',
    `service_agreement`           CHAR(1)               NOT NULL COMMENT '서비스 이용약관 동의 여부',
    `privacy_agreement`           CHAR(1)               NOT NULL COMMENT '개인정보 수집 및 이용동의',
    `location_service_agreement`  CHAR(1)               NOT NULL DEFAULT 'N' COMMENT '위치 기반 서비스 이용동의',
    `marketing_service_agreement` CHAR(1)               NOT NULL DEFAULT 'N' COMMENT '마케팅 서비스 이용동의'
);

ALTER TABLE `member`
    ADD CONSTRAINT `PK_MEMBER` PRIMARY KEY (`id`);

CREATE TABLE `file`
(
    `id`          BIGINT AUTO_INCREMENT NOT NULL COMMENT '파일 일련번호',
    `group_id`    BIGINT                NOT NULL COMMENT '파일 그룹 일련번호',
    `saved_name`  VARCHAR(500)          NOT NULL COMMENT '저장 파일 이름',
    `origin_name` VARCHAR(500)          NOT NULL COMMENT '원본 파일 이름',
    `extension`   VARCHAR(20)           NOT NULL COMMENT '확장자',
    `size`        BIGINT                NOT NULL COMMENT '크기',
    `ord`         INTEGER               NULL COMMENT '순서',
    `modified_at` DATETIME              NOT NULL COMMENT '수정일',
    `modified_by` BIGINT                NOT NULL COMMENT '수정자 아이디',
    `created_at`  DATETIME              NOT NULL COMMENT '등록일',
    `created_by`  BIGINT                NOT NULL COMMENT '등록자 아이디'
);

ALTER TABLE `file`
    ADD CONSTRAINT `PK_FILE` PRIMARY KEY (`id`);

CREATE TABLE `file_group`
(
    `id`             BIGINT AUTO_INCREMENT NOT NULL COMMENT '파일 그룹 일련번호',
    `directory_path` VARCHAR(200)          NULL COMMENT '디렉토리 경로명',
    `modified_at`    DATETIME              NOT NULL COMMENT '수정일',
    `modified_by`    BIGINT                NOT NULL COMMENT '수정자 아이디',
    `created_at`     DATETIME              NOT NULL COMMENT '등록일',
    `created_by`     BIGINT                NOT NULL COMMENT '등록자 아이디'
);

ALTER TABLE `file_group`
    ADD CONSTRAINT `PK_FILE_GROUP` PRIMARY KEY (`id`);

CREATE TABLE `resource`
(
    `id`          BIGINT AUTO_INCREMENT NOT NULL COMMENT '자원 일련번호',
    `name`        VARCHAR(100)          NOT NULL COMMENT '이름',
    `url`         VARCHAR(500)          NOT NULL COMMENT '자원 URL',
    `http_method` VARCHAR(10)           NOT NULL COMMENT 'HTTP 메소드',
    `is_enabled`  CHAR(1)               NOT NULL DEFAULT 'Y' COMMENT '사용여부',
    `type`        VARCHAR(10)           NOT NULL COMMENT '유형',
    `depth`       INTEGER               NULL COMMENT '깊이',
    `ord`         INTEGER               NULL COMMENT '순서',
    `upper_id`    BIGINT                NULL COMMENT '상위 자원 일련번호',
    `modified_at` DATETIME              NOT NULL COMMENT '수정일',
    `modified_by` BIGINT                NOT NULL COMMENT '수정자 일련번호',
    `created_at`  DATETIME              NOT NULL COMMENT '등록일',
    `created_by`  BIGINT                NOT NULL COMMENT '등록자 일련번호'
);

ALTER TABLE `resource`
    ADD CONSTRAINT `PK_RESOURCE` PRIMARY KEY (`id`);

CREATE TABLE `role`
(
    `id`          BIGINT AUTO_INCREMENT NOT NULL COMMENT '자원 일련번호',
    `name`        VARCHAR(100)          NULL COMMENT '이름',
    `upper_id`    BIGINT                NULL COMMENT '상위 역할 일련번호',
    `is_enabled`  CHAR(1)               NOT NULL DEFAULT 'Y' COMMENT '사용여부',
    `description` VARCHAR(500)          NULL COMMENT '권한설명',
    `modified_at` DATETIME              NOT NULL COMMENT '수정일',
    `modified_by` BIGINT                NOT NULL COMMENT '수정자 일련번호',
    `created_at`  DATETIME              NOT NULL COMMENT '등록일',
    `created_by`  BIGINT                NOT NULL COMMENT '등록자 일련번호'
);

ALTER TABLE `role`
    ADD CONSTRAINT `PK_ROLE` PRIMARY KEY (`id`);

CREATE TABLE `member_role`
(
    `id`          BIGINT AUTO_INCREMENT NOT NULL COMMENT '회원 역할 일련번호',
    `member_id`   BIGINT                NOT NULL COMMENT '회원 일련번호',
    `role_id`     BIGINT                NOT NULL COMMENT '자원 일련번호',
    `modified_at` DATETIME              NOT NULL COMMENT '수정일',
    `modified_by` BIGINT                NOT NULL COMMENT '수정자 일련번호',
    `created_at`  DATETIME              NOT NULL COMMENT '등록일',
    `created_by`  BIGINT                NOT NULL COMMENT '등록자 일련번호'
);

ALTER TABLE `member_role`
    ADD CONSTRAINT `PK_MEMBER_ROLE` PRIMARY KEY (`id`);


CREATE TABLE `role_resource`
(
    `id`          BIGINT AUTO_INCREMENT NOT NULL COMMENT '역할 자원 일련번호',
    `role_id`     BIGINT                NOT NULL COMMENT '역할 일련번호',
    `resource_id` BIGINT                NOT NULL COMMENT '자원 일련번호',
    `modified_at` DATETIME              NOT NULL COMMENT '수정일',
    `modified_by` BIGINT                NOT NULL COMMENT '수정자 일련번호',
    `created_at`  DATETIME              NOT NULL COMMENT '등록일',
    `created_by`  BIGINT                NOT NULL COMMENT '등록자 일련번호'
);

ALTER TABLE `role_resource`
    ADD CONSTRAINT `PK_ROLE_RESOURCE` PRIMARY KEY (`id`);

