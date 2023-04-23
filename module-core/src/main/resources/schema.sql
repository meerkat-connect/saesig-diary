CREATE TABLE `member`
(
    `id`            BIGINT       NOT NULL COMMENT '회원 일련번호',
    `email`         VARCHAR(50)  NOT NULL COMMENT '이메일',
    `password`      VARCHAR(100) NOT NULL COMMENT '비밀번호',
    `signup_method` VARCHAR(20)  NULL COMMENT '가입 수단',
    `nickname`      VARCHAR(50)  NULL COMMENT '닉네임',
    `status`        VARCHAR(20)  NOT NULL COMMENT '상태',
    `joined_at`     DATETIME     NOT NULL COMMENT '가입일',
    `modified_at`   DATETIME     NOT NULL COMMENT '수정일',
    `modified_by`   BIGINT       NOT NULL COMMENT '수정자 일련번호',
    `created_at`    DATETIME     NOT NULL COMMENT '등록일',
    `created_by`    BIGINT       NOT NULL COMMENT '등록자 일련번호'
);

ALTER TABLE `member`
    ADD CONSTRAINT `PK_MEMBER` PRIMARY KEY (`id`);

CREATE TABLE `file`
(
    `id`          BIGINT       NOT NULL COMMENT '파일 일련번호',
    `group_id`    BIGINT       NOT NULL COMMENT '파일 그룹 일련번호',
    `path`        VARCHAR(500) NOT NULL COMMENT '경로',
    `saved_name`  VARCHAR(500) NOT NULL COMMENT '저장 파일 이름',
    `origin_name` VARCHAR(500) NOT NULL COMMENT '원본 파일 이름',
    `extension`   VARCHAR(20)  NOT NULL COMMENT '확장자',
    `size`        INTEGER      NOT NULL COMMENT '크기',
    `ord`         SMALLINT     NULL COMMENT '순서',
    `modified_at` DATETIME     NOT NULL COMMENT '수정일',
    `modified_by` BIGINT       NULL COMMENT '수정자 아이디',
    `created_at`  DATETIME     NOT NULL COMMENT '등록일',
    `created_by`  BIGINT       NULL COMMENT '등록자 아이디'
);

ALTER TABLE `file`
    ADD CONSTRAINT `PK_FILE` PRIMARY KEY (`id`);


CREATE TABLE `file`
(
    `id`          BIGINT       NOT NULL COMMENT '파일 일련번호',
    `group_id`    BIGINT       NOT NULL COMMENT '파일 그룹 일련번호',
    `path`        VARCHAR(500) NOT NULL COMMENT '경로',
    `saved_name`  VARCHAR(500) NOT NULL COMMENT '저장 파일 이름',
    `origin_name` VARCHAR(500) NOT NULL COMMENT '원본 파일 이름',
    `extension`   VARCHAR(20)  NOT NULL COMMENT '확장자',
    `size`        INTEGER      NOT NULL COMMENT '크기',
    `ord`         SMALLINT     NULL COMMENT '순서',
    `modified_at` DATETIME     NOT NULL COMMENT '수정일',
    `modified_by` BIGINT       NOT NULL COMMENT '수정자 아이디',
    `created_at`  DATETIME     NOT NULL COMMENT '등록일',
    `created_by`  BIGINT       NOT NULL COMMENT '등록자 아이디'
);

ALTER TABLE `file`
    ADD CONSTRAINT `PK_FILE` PRIMARY KEY (`id`);

CREATE TABLE `file_group`
(
    `id`             BIGINT       NOT NULL COMMENT '파일 그룹 일련번호',
    `directory_path` VARCHAR(200) NULL COMMENT '디렉토리 경로명',
    `board_id`       INTEGER      NULL COMMENT '게시판 아이디',
    `modified_at`    DATETIME     NOT NULL COMMENT '수정일',
    `modified_by`    BIGINT       NOT NULL COMMENT '수정자 아이디',
    `created_at`     DATETIME     NOT NULL COMMENT '등록일',
    `created_by`     BIGINT       NOT NULL COMMENT '등록자 아이디'
);

ALTER TABLE `file_group`
    ADD CONSTRAINT `PK_FILE_GROUP` PRIMARY KEY (`id`);





