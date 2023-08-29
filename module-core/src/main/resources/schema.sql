SET foreign_key_checks = 0;

DROP TABLE IF EXISTS `member`;

CREATE TABLE `member`
(
    `id`                          BIGINT AUTO_INCREMENT NOT NULL COMMENT '회원 일련번호',
    `email`                       VARCHAR(50)           NOT NULL COMMENT '이메일',
    `password`                    VARCHAR(200)          NOT NULL COMMENT '비밀번호',
    `prev_password`               VARCHAR(200)          NULL COMMENT '비밀번호',
    `signup_method`               VARCHAR(20)           NULL COMMENT '가입 수단',
    `nickname`                    VARCHAR(50)           NULL COMMENT '닉네임',
    `status`                      VARCHAR(20)           NOT NULL COMMENT '상태',
    `joined_at`                   DATETIME              NOT NULL COMMENT '가입일',
    `service_agreement`           VARCHAR(1)            NOT NULL COMMENT '서비스 이용약관 동의 여부',
    `location_service_agreement`  VARCHAR(1)            NOT NULL DEFAULT 'N' COMMENT '위치 기반 서비스 이용동의',
    `privacy_agreement`           VARCHAR(1)            NOT NULL COMMENT '개인정보 수집 및 이용동의',
    `password_modified_at`        DATETIME              NOT NULL COMMENT '비밀번호 마지막 수정일',
    `marketing_service_agreement` VARCHAR(1)            NOT NULL DEFAULT 'N' COMMENT '마케팅 서비스 이용동의',
    `modified_at`                 DATETIME              NOT NULL COMMENT '수정일',
    `modified_by`                 BIGINT                NULL COMMENT '수정자 일련번호',
    `created_at`                  DATETIME              NOT NULL COMMENT '등록일',
    `created_by`                  BIGINT                NULL COMMENT '등록자 일련번호'
);

DROP TABLE IF EXISTS `adopt`;

CREATE TABLE `adopt`
(
    `id`                  BIGINT AUTO_INCREMENT NOT NULL COMMENT '분양 일련번호',
    `adopt_member_id`     BIGINT                NULL COMMENT '예약(분양)자 일련번호',
    `hits`                BIGINT                NOT NULL DEFAULT 0 COMMENT '조회수',
    `title`               VARCHAR(200)          NOT NULL COMMENT '제목',
    `content`             VARCHAR(1000)         NOT NULL COMMENT '내용',
    `gender`              VARCHAR(10)           NOT NULL COMMENT '성별',
    `age`                 INTEGER               NULL COMMENT '나이',
    `age_category`        VARCHAR(20)           NOT NULL COMMENT '나이 상태',
    `status`              VARCHAR(20)           NOT NULL COMMENT '분양상태',
    `is_deleted`          VARCHAR(1)            NOT NULL DEFAULT 'N' COMMENT '삭제 여부',
    `is_castrated`        VARCHAR(1)            NULL COMMENT '중성화여부',
    `responsibility_cost` NUMERIC(19, 4)        NULL COMMENT '책임비',
    `etc_content`         VARCHAR(1000)         NULL COMMENT '기타 내용',
    `animal_division1_id` BIGINT                NOT NULL COMMENT '동물_대분류 일련번호',
    `animal_division2_id` BIGINT                NOT NULL COMMENT '동물_중분류 일련번호',
    `image_file_group_id` BIGINT                NULL COMMENT '이미지 파일 그룹 일련번호',
    `sido`                VARCHAR(20)           NOT NULL COMMENT '지역_시도',
    `sigungu`             VARCHAR(20)           NOT NULL COMMENT '지역_시군구',
    `stop_reason`         VARCHAR(1000)         NULL COMMENT '분양중지 사유',
    `stop_category`       VARCHAR(20)           NULL COMMENT '분양 중지 유형',
    `modified_at`         DATETIME              NOT NULL COMMENT '수정일',
    `modified_by`         BIGINT                NOT NULL COMMENT '수정자 일련번호',
    `created_at`          DATETIME              NOT NULL COMMENT '등록일',
    `created_by`          BIGINT                NOT NULL COMMENT '등록자 일련번호'
);

DROP TABLE IF EXISTS `adopt_report`;

CREATE TABLE `adopt_report`
(
    `id`          BIGINT AUTO_INCREMENT NOT NULL COMMENT '신고 일련번호',
    `adopt_id`    BIGINT                NOT NULL COMMENT '분양 일련번호',
    `category`    VARCHAR(20)           NOT NULL COMMENT '유형',
    `content`     VARCHAR(1000)         NULL COMMENT '내용',
    `member_id`   BIGINT                NOT NULL COMMENT '신고자 회원 일련번호',
    `modified_at` DATETIME              NOT NULL COMMENT '수정일',
    `modified_by` BIGINT                NOT NULL COMMENT '수정자 일련번호',
    `created_at`  DATETIME              NOT NULL COMMENT '등록일',
    `created_by`  BIGINT                NOT NULL COMMENT '등록자 일련번호'
);

DROP TABLE IF EXISTS `resource`;

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

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role`
(
    `id`          BIGINT AUTO_INCREMENT NOT NULL COMMENT '역할 일련번호',
    `name`        VARCHAR(100)          NULL COMMENT '이름',
    `upper_id`    BIGINT                NULL COMMENT '상위 역할 일련번호',
    `is_enabled`  CHAR(1)               NOT NULL DEFAULT 'Y' COMMENT '사용여부',
    `description` VARCHAR(500)          NULL COMMENT '권한설명',
    `modified_at` DATETIME              NOT NULL COMMENT '수정일',
    `modified_by` BIGINT                NOT NULL COMMENT '수정자 일련번호',
    `created_at`  DATETIME              NOT NULL COMMENT '등록일',
    `created_by`  BIGINT                NOT NULL COMMENT '등록자 일련번호'
);

DROP TABLE IF EXISTS `role_resource`;

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

DROP TABLE IF EXISTS `file`;

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
    `modified_by` BIGINT                NOT NULL COMMENT '수정자 일련번호',
    `created_at`  DATETIME              NOT NULL COMMENT '등록일',
    `created_by`  BIGINT                NOT NULL COMMENT '등록자 일련번호'
);

DROP TABLE IF EXISTS `file_group`;

CREATE TABLE `file_group`
(
    `id`          BIGINT AUTO_INCREMENT NOT NULL COMMENT '파일 그룹 일련번호',
    `path`        VARCHAR(200)          NOT NULL COMMENT '경로명',
    `modified_at` DATETIME              NOT NULL COMMENT '수정일',
    `modified_by` BIGINT                NOT NULL COMMENT '수정자 일련번호',
    `created_at`  DATETIME              NOT NULL COMMENT '등록일',
    `created_by`  BIGINT                NOT NULL COMMENT '등록자 일련번호'
);

DROP TABLE IF EXISTS `member_role`;

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

DROP TABLE IF EXISTS `adopt_interest`;

CREATE TABLE `adopt_interest`
(
    `id`          BIGINT AUTO_INCREMENT NOT NULL COMMENT '분양 관심 일련번호',
    `adopt_id`    BIGINT                NOT NULL COMMENT '분양 일련번호',
    `member_id`   BIGINT                NOT NULL COMMENT '회원 일련번호',
    `created_at`  DATETIME              NOT NULL COMMENT '등록일',
    `created_by`  BIGINT                NOT NULL COMMENT '등록자 일련번호',
    `modified_at` DATETIME              NOT NULL COMMENT '수정일',
    `modified_by` BIGINT                NOT NULL COMMENT '수정자 일련번호'
);

DROP TABLE IF EXISTS `banner`;

CREATE TABLE `banner`
(
    `id`                  BIGINT AUTO_INCREMENT NOT NULL COMMENT '배너 일련번호',
    `title`               VARCHAR(200)          NOT NULL COMMENT '제목',
    `image_file_group_id` BIGINT                NOT NULL COMMENT '이미지 파일 그룹 일련번호',
    `url`                 VARCHAR(200)          NULL COMMENT '링크 URL',
    `ord`                 BIGINT                NOT NULL COMMENT '순서',
    `is_enabled`          VARCHAR(1)            NOT NULL DEFAULT 'N' COMMENT '사용 여부',
    `created_at`          DATETIME              NOT NULL COMMENT '등록일',
    `created_by`          BIGINT                NOT NULL COMMENT '등록자 일련번호',
    `modified_at`         DATETIME              NOT NULL COMMENT '수정일',
    `modified_by`         BIGINT                NOT NULL COMMENT '수정자 일련번호'
);

DROP TABLE IF EXISTS `animal_division1`;

CREATE TABLE `animal_division1`
(
    `id`       BIGINT AUTO_INCREMENT NOT NULL COMMENT '동물 대분류 일련번호',
    `category` VARCHAR(20)           NOT NULL COMMENT '유형'
);

DROP TABLE IF EXISTS `animal_division2`;

CREATE TABLE `animal_division2`
(
    `id`                  BIGINT AUTO_INCREMENT NOT NULL COMMENT '동물 중분류 일련번호',
    `animal_division1_id` BIGINT                NOT NULL COMMENT '동물 대분류 일련번호',
    `category`            VARCHAR(20)           NOT NULL COMMENT '유형'
);

DROP TABLE IF EXISTS `inquiry`;

CREATE TABLE `inquiry`
(
    `id`             BIGINT AUTO_INCREMENT NOT NULL COMMENT '문의 일련번호',
    `category`       VARCHAR(20)           NOT NULL COMMENT '유형',
    `incoming_email` VARCHAR(50)           NOT NULL COMMENT '수신 이메일',
    `status`         VARCHAR(20)           NOT NULL COMMENT '상태',
    `title`          VARCHAR(300)          NOT NULL COMMENT '제목',
    `content`        VARCHAR(2000)         NOT NULL COMMENT '내용',
    `is_deleted`     CHAR(1)               NOT NULL DEFAULT 'N' COMMENT '삭제 여부',
    `created_at`     DATETIME              NOT NULL COMMENT '등록일',
    `created_by`     BIGINT                NOT NULL COMMENT '등록자 일련번호',
    `modified_at`    DATETIME              NOT NULL COMMENT '수정일',
    `modified_by`    BIGINT                NOT NULL COMMENT '수정자 일련번호'
);

DROP TABLE IF EXISTS `inquiry_answer`;

CREATE TABLE `inquiry_answer`
(
    `id`          BIGINT AUTO_INCREMENT NOT NULL COMMENT '답변 일련번호',
    `inquiry_id`  BIGINT                NOT NULL COMMENT '문의 일련번호',
    `title`       VARCHAR(300)          NOT NULL COMMENT '제목',
    `content`     VARCHAR(2000)         NOT NULL COMMENT '내용',
    `created_at`  DATETIME              NOT NULL COMMENT '등록일',
    `created_by`  BIGINT                NOT NULL COMMENT '등록자 일련번호',
    `modified_at` DATETIME              NOT NULL COMMENT '수정일',
    `modified_by` BIGINT                NOT NULL COMMENT '수정자 일련번호'
);

DROP TABLE IF EXISTS `faq`;

CREATE TABLE `faq`
(
    `id`          BIGINT AUTO_INCREMENT NOT NULL COMMENT 'FAQ 일련번호',
    `category`    VARCHAR(20)           NOT NULL COMMENT '유형',
    `title`       VARCHAR(300)          NOT NULL COMMENT '제목',
    `content`     LONGTEXT              NOT NULL COMMENT '내용',
    `ord`         BIGINT                NOT NULL COMMENT '순서',
    `is_enabled`  CHAR(1)               NOT NULL DEFAULT 'Y' COMMENT '사용 여부',
    `created_at`  DATETIME              NOT NULL COMMENT '등록일',
    `created_by`  BIGINT                NOT NULL COMMENT '등록자 일련번호',
    `modified_at` DATETIME              NOT NULL COMMENT '수정일',
    `modified_by` BIGINT                NOT NULL COMMENT '수정자 일련번호'
);

DROP TABLE IF EXISTS `send_history`;

CREATE TABLE `send_history`
(
    `id`               BIGINT AUTO_INCREMENT NOT NULL COMMENT '발송이력 일련번호',
    `send_template_id` BIGINT                NOT NULL COMMENT '템플릿 일련번호',
    `content`          TEXT                  NOT NULL COMMENT '발송내용',
    `recipient_id`     BIGINT                NOT NULL COMMENT '수신자 일련번호',
    `recipient_email`  VARCHAR(50)           NOT NULL COMMENT '수신자 이메일',
    `sender_id`        BIGINT                NOT NULL COMMENT '발신자 일련번호',
    `sender_email`     VARCHAR(50)           NOT NULL COMMENT '발신자 이메일',
    `sended_at`        DATETIME              NOT NULL COMMENT '발송일시',
    `created_at`       DATETIME              NOT NULL COMMENT '등록일',
    `created_by`       BIGINT                NOT NULL COMMENT '등록자 일련번호',
    `modified_at`      DATETIME              NOT NULL COMMENT '수정일',
    `modified_by`      BIGINT                NOT NULL COMMENT '수정자 일련번호'
);

DROP TABLE IF EXISTS `send_template`;

CREATE TABLE `send_template`
(
    `id`          BIGINT AUTO_INCREMENT NOT NULL COMMENT '템플릿 일련번호',
    `method`      VARCHAR(20)           NOT NULL COMMENT '발송 수단',
    `title`       VARCHAR(300)          NOT NULL COMMENT '제목',
    `content`     TEXT                  NOT NULL COMMENT '내용',
    `category`    VARCHAR(20)           NOT NULL COMMENT '발송 유형',
    `is_enabled`  CHAR(1)               NOT NULL DEFAULT 'Y' COMMENT '사용 여부',
    `time_point`  VARCHAR(20)           NOT NULL COMMENT '발송 시점',
    `created_at`  DATETIME              NOT NULL COMMENT '등록일',
    `created_by`  BIGINT                NOT NULL COMMENT '등록자 일련번호',
    `modified_at` DATETIME              NOT NULL COMMENT '수정일',
    `modified_by` BIGINT                NOT NULL COMMENT '수정자 일련번호'
);

DROP TABLE IF EXISTS `blocked_member`;

CREATE TABLE `blocked_member`
(
    `id`                BIGINT AUTO_INCREMENT NOT NULL COMMENT '회원 차단 일련번호',
    `member_id`         BIGINT                NOT NULL COMMENT '회원 일련번호',
    `blocked_member_id` BIGINT                NOT NULL COMMENT '차단 대상 회원 일련번호',
    `modified_at`       DATETIME              NOT NULL COMMENT '수정일',
    `mofified_at`       BIGINT                NOT NULL COMMENT '수정자 일련번호',
    `created_at`        DATETIME              NOT NULL COMMENT '등록일',
    `created_by`        BIGINT                NOT NULL COMMENT '등록자 일련번호'
);

DROP TABLE IF EXISTS `diary`;

CREATE TABLE `diary`
(
    `id`                  BIGINT AUTO_INCREMENT NOT NULL COMMENT '일기 일련번호',
    `title`               VARCHAR(200)          NOT NULL COMMENT '제목',
    `content`             VARCHAR(2000)         NOT NULL COMMENT '내용',
    `image_file_group_id` BIGINT                NULL COMMENT '이미지 파일 그룹 일련번호',
    `weather_category`    VARCHAR(20)           NOT NULL COMMENT '날씨 유형',
    `is_secret`           VARCHAR(1)            NOT NULL DEFAULT 'N' COMMENT '비밀 여부',
    `is_deleted`          VARCHAR(1)            NOT NULL DEFAULT 'N' COMMENT '삭제 여부',
    `hits`                INTEGER               NOT NULL DEFAULT 0 COMMENT '조회수',
    `tag_group_id`        BIGINT                NOT NULL COMMENT '태그 그룹 일련번호',
    `created_at`          DATETIME              NOT NULL COMMENT '등록일',
    `created_by`          BIGINT                NOT NULL COMMENT '등록자 일련번호',
    `modified_at`         DATETIME              NOT NULL COMMENT '수정일',
    `modified_by`         BIGINT                NOT NULL COMMENT '수정자 일련번호'
);

DROP TABLE IF EXISTS `diary_comment`;

CREATE TABLE `diary_comment`
(
    `id`          BIGINT AUTO_INCREMENT NOT NULL COMMENT '댓글 일련번호',
    `upper_id`    BIGINT                NOT NULL COMMENT '상위 댓글 일련번호',
    `content`     VARCHAR(1000)         NOT NULL COMMENT '내용',
    `is_deleted`  VARCHAR(1)            NOT NULL COMMENT '삭제 여부',
    `depth`       INTEGER               NOT NULL COMMENT '깊이',
    `created_at`  DATETIME              NOT NULL COMMENT '등록일',
    `created_by`  BIGINT                NOT NULL COMMENT '등록자 일련번호',
    `modified_at` DATETIME              NOT NULL COMMENT '수정일',
    `modified_by` BIGINT                NOT NULL COMMENT '수정자 일련번호'
);

DROP TABLE IF EXISTS `policy`;

CREATE TABLE `policy`
(
    `id`          BIGINT AUTO_INCREMENT NOT NULL COMMENT '약관 및 정책 일련번호',
    `category`    VARCHAR(20)           NOT NULL COMMENT '유형',
    `title`       VARCHAR(200)          NOT NULL COMMENT '제목',
    `content`     LONGTEXT              NOT NULL COMMENT '내용',
    `modified_at` DATETIME              NOT NULL COMMENT '수정일',
    `modified_by` BIGINT                NOT NULL COMMENT '수정자 일련번호',
    `created_at`  DATETIME              NOT NULL COMMENT '등록일',
    `created_by`  BIGINT                NOT NULL COMMENT '등록자 일련번호'
);

DROP TABLE IF EXISTS `board`;

CREATE TABLE `board`
(
    `id`           BIGINT AUTO_INCREMENT NOT NULL COMMENT '게시판 일련번호',
    `name`         VARCHAR(200)          NOT NULL COMMENT '이름',
    `description`  VARCHAR(2000)         NULL COMMENT '내용',
    `use_category` VARCHAR(1)            NOT NULL DEFAULT 'N' COMMENT '분류 사용 여부',
    `use_board`    VARCHAR(1)            NOT NULL DEFAULT 'Y' COMMENT '게시판 사용 여부',
    `use_fix`      VARCHAR(1)            NOT NULL DEFAULT 'N' COMMENT '공지 사용 여부',
    `modified_at`  DATETIME              NOT NULL COMMENT '수정일',
    `modified_by`  BIGINT                NOT NULL COMMENT '수정자 일련번호',
    `created_at`   DATETIME              NOT NULL COMMENT '등록일',
    `created_by`   BIGINT                NOT NULL COMMENT '등록자 일련번호'
);

DROP TABLE IF EXISTS `diary_report`;

CREATE TABLE `diary_report`
(
    `id`          BIGINT AUTO_INCREMENT NOT NULL COMMENT '신고 일련번호',
    `diary_id`    BIGINT                NOT NULL COMMENT '일기 일련번호',
    `category`    VARCHAR(20)           NOT NULL COMMENT '유형',
    `content`     VARCHAR(1000)         NULL COMMENT '내용',
    `member_id`   BIGINT                NOT NULL COMMENT '신고자 회원 일련번호',
    `modified_at` DATETIME              NOT NULL COMMENT '수정일',
    `modified_by` BIGINT                NOT NULL COMMENT '수정자 일련번호',
    `created_at`  DATETIME              NOT NULL COMMENT '등록일',
    `created_by`  BIGINT                NOT NULL COMMENT '등록자 일련번호'
);

DROP TABLE IF EXISTS `popup`;

CREATE TABLE `popup`
(
    `id`                  BIGINT AUTO_INCREMENT NOT NULL COMMENT '팝업 일련번호',
    `title`               VARCHAR(200)          NOT NULL COMMENT '제목',
    `resource_id`         BIGINT                NOT NULL COMMENT '자원 아이디',
    `target`              VARCHAR(20)           NOT NULL COMMENT '공지 대상 코드(A:전체, S: 선택)',
    `target_role_id`      BIGINT                NOT NULL COMMENT '공지 대상 역할 일련번호',
    `start_date`          DATE                  NOT NULL COMMENT '팝업 시작일',
    `end_date`            DATE                  NOT NULL COMMENT '팝업 종료일',
    `image_file_group_id` BIGINT                NOT NULL COMMENT '이미지 파일 그룹 일련번호',
    `url`                 VARCHAR(200)          NULL COMMENT '링크 URL',
    `button_option`       VARCHAR(20)           NOT NULL COMMENT '버튼 옵션',
    `created_at`          DATETIME              NOT NULL COMMENT '등록일',
    `created_by`          BIGINT                NOT NULL COMMENT '등록자 일련번호',
    `modified_at`         DATETIME              NOT NULL COMMENT '수정일',
    `modified_by`         BIGINT                NOT NULL COMMENT '수정자 일련번호'
);

DROP TABLE IF EXISTS `post`;

CREATE TABLE `post`
(
    `id`                   BIGINT AUTO_INCREMENT NOT NULL COMMENT '게시글 일련번호',
    `board_id`             BIGINT                NOT NULL COMMENT '게시판 일련번호',
    `title`                VARCHAR(200)          NOT NULL COMMENT '제목',
    `content`              LONGTEXT              NOT NULL COMMENT '내용',
    `attach_file_group_id` BIGINT                NULL COMMENT '첨부 파일 그룹 일련번호',
    `is_deleted`           VARCHAR(1)            NOT NULL DEFAULT 'N' COMMENT '삭제 여부',
    `hits`                 INTEGER               NOT NULL DEFAULT 0 COMMENT '조회수',
    `is_fixed`             VARCHAR(1)            NOT NULL DEFAULT 'N' COMMENT '공지 여부',
    `fix_start_date`       DATETIME              NULL COMMENT '공지 시작 일시',
    `fix_end_date`         DATETIME              NULL COMMENT '공지 종료 일시',
    `modified_at`          DATETIME              NOT NULL COMMENT '수정일',
    `modified_by`          BIGINT                NOT NULL COMMENT '수정자 일련번호',
    `created_at`           DATETIME              NOT NULL COMMENT '등록일',
    `created_by`           BIGINT                NOT NULL COMMENT '등록자 일련번호'
);

DROP TABLE IF EXISTS `diary_interest`;

CREATE TABLE `diary_interest`
(
    `id`          BIGINT AUTO_INCREMENT NOT NULL COMMENT '일기 관심 일련번호',
    `diary_id`    BIGINT                NOT NULL COMMENT '일기 일련번호',
    `member_id`   BIGINT                NOT NULL COMMENT '회원 일련번호',
    `created_at`  DATETIME              NOT NULL COMMENT '등록일',
    `created_by`  BIGINT                NOT NULL COMMENT '등록자 일련번호',
    `modified_at` DATETIME              NOT NULL COMMENT '수정일',
    `modified_by` BIGINT                NOT NULL COMMENT '수정자 일련번호'
);

DROP TABLE IF EXISTS `chat_open_reason`;

CREATE TABLE `chat_open_reason`
(
    `id`          BIGINT AUTO_INCREMENT NOT NULL COMMENT '채팅이력 열람 사유 일련번호',
    `member_id`   BIGINT                NOT NULL COMMENT '열람자 일련번호',
    `category`    VARCHAR(20)           NOT NULL COMMENT '열람 사유 유형',
    `etc_reason`  VARCHAR(1000)         NOT NULL COMMENT '기타 사유',
    `modified_at` DATETIME              NOT NULL COMMENT '수정일',
    `modified_by` BIGINT                NOT NULL COMMENT '수정자 일련번호',
    `created_at`  DATETIME              NOT NULL COMMENT '등록일',
    `created_by`  BIGINT                NOT NULL COMMENT '등록자 일련번호'
);

DROP TABLE IF EXISTS `diary_tag`;

CREATE TABLE `diary_tag`
(
    `id`          BIGINT AUTO_INCREMENT NOT NULL COMMENT '일기 태그 일련번호',
    `diary_id`    BIGINT                NOT NULL COMMENT '일기 일련번호',
    `tag_id`      BIGINT                NOT NULL COMMENT '태그 일련번호',
    `created_at`  DATETIME              NOT NULL COMMENT '등록일',
    `created_by`  BIGINT                NOT NULL COMMENT '등록자 일련번호',
    `modified_at` DATETIME              NOT NULL COMMENT '수정일',
    `modified_by` BIGINT                NOT NULL COMMENT '수정자 일련번호'
);

DROP TABLE IF EXISTS `tag`;

CREATE TABLE `tag`
(
    `id`   BIGINT AUTO_INCREMENT NOT NULL COMMENT '태그 일련번호',
    `name` VARCHAR(20)           NOT NULL COMMENT '태그 이름'
);

DROP TABLE IF EXISTS `vaccine`;

CREATE TABLE `vaccine`
(
    `id`   BIGINT AUTO_INCREMENT NOT NULL COMMENT '백신 일련번호',
    `name` VARCHAR(20)           NOT NULL COMMENT '백신 이름'
);

DROP TABLE IF EXISTS `adopt_vaccine`;

CREATE TABLE `adopt_vaccine`
(
    `id`          BIGINT AUTO_INCREMENT NOT NULL COMMENT '분양 백신 일련번호',
    `adopt_id`    BIGINT                NOT NULL COMMENT '분양 일련번호',
    `vaccine_id`  BIGINT                NOT NULL COMMENT '백신 일련번호',
    `modified_at` DATETIME              NOT NULL COMMENT '수정일',
    `modified_by` BIGINT                NOT NULL COMMENT '수정자 일련번호',
    `created_at`  DATETIME              NOT NULL COMMENT '등록일',
    `created_by`  BIGINT                NOT NULL COMMENT '등록자 일련번호'
);

DROP TABLE IF EXISTS `manager_notice_board`;

CREATE TABLE `manager_notice_board` (
    `id`	        BIGINT	        NOT NULL	COMMENT '관리자게시판 일련번호',
    `category`	    VARCHAR(20)	    NOT NULL	COMMENT '유형',
    `title`	        VARCHAR(200)	NOT NULL	COMMENT '제목',
    `content`	    LONGTEXT	    NOT NULL	COMMENT '내용',
    `hits`	        INTEGER	        NOT NULL	DEFAULT 0	COMMENT '조회수',
    `is_deleted`	VARCHAR(1)	    NOT NULL	DEFAULT 'N'	COMMENT '삭제여부',
    `modified_at`	DATETIME	    NOT NULL	COMMENT '수정일',
    `modified_by`	BIGINT	        NOT NULL	COMMENT '수정자 일련번호',
    `created_at`	DATETIME	    NOT NULL	COMMENT '등록일',
    `created_by`	BIGINT	        NOT NULL	COMMENT '등록자 일련번호'
);


ALTER TABLE `member`
    ADD CONSTRAINT `PK_MEMBER` PRIMARY KEY (
                                            `id`
        );

ALTER TABLE `adopt`
    ADD CONSTRAINT `PK_ADOPT` PRIMARY KEY (
                                           `id`
        );

ALTER TABLE `adopt_report`
    ADD CONSTRAINT `PK_ADOPT_REPORT` PRIMARY KEY (
                                                  `id`
        );

ALTER TABLE `resource`
    ADD CONSTRAINT `PK_RESOURCE` PRIMARY KEY (
                                              `id`
        );

ALTER TABLE `role`
    ADD CONSTRAINT `PK_ROLE` PRIMARY KEY (
                                          `id`
        );

ALTER TABLE `role_resource`
    ADD CONSTRAINT `PK_ROLE_RESOURCE` PRIMARY KEY (
                                                   `id`
        );

ALTER TABLE `file`
    ADD CONSTRAINT `PK_FILE` PRIMARY KEY (
                                          `id`
        );

ALTER TABLE `file_group`
    ADD CONSTRAINT `PK_FILE_GROUP` PRIMARY KEY (
                                                `id`
        );

ALTER TABLE `member_role`
    ADD CONSTRAINT `PK_MEMBER_ROLE` PRIMARY KEY (
                                                 `id`
        );

ALTER TABLE `adopt_interest`
    ADD CONSTRAINT `PK_ADOPT_INTEREST` PRIMARY KEY (
                                                    `id`
        );

ALTER TABLE `banner`
    ADD CONSTRAINT `PK_BANNER` PRIMARY KEY (
                                            `id`
        );

ALTER TABLE `animal_division1`
    ADD CONSTRAINT `PK_ANIMAL_DIVISION1` PRIMARY KEY (
                                                      `id`
        );

ALTER TABLE `animal_division2`
    ADD CONSTRAINT `PK_ANIMAL_DIVISION2` PRIMARY KEY (
                                                      `id`
        );

ALTER TABLE `inquiry`
    ADD CONSTRAINT `PK_INQUIRY` PRIMARY KEY (
                                             `id`
        );

ALTER TABLE `inquiry_answer`
    ADD CONSTRAINT `PK_INQUIRY_ANSWER` PRIMARY KEY (
                                                    `id`
        );

ALTER TABLE `faq`
    ADD CONSTRAINT `PK_FAQ` PRIMARY KEY (
                                         `id`
        );

ALTER TABLE `send_history`
    ADD CONSTRAINT `PK_SEND_HISTORY` PRIMARY KEY (
                                                  `id`
        );

ALTER TABLE `send_template`
    ADD CONSTRAINT `PK_SEND_TEMPLATE` PRIMARY KEY (
                                                   `id`
        );

ALTER TABLE `blocked_member`
    ADD CONSTRAINT `PK_BLOCKED_MEMBER` PRIMARY KEY (
                                                    `id`
        );

ALTER TABLE `diary`
    ADD CONSTRAINT `PK_DIARY` PRIMARY KEY (
                                           `id`
        );

ALTER TABLE `diary_comment`
    ADD CONSTRAINT `PK_DIARY_COMMENT` PRIMARY KEY (
                                                   `id`
        );

ALTER TABLE `policy`
    ADD CONSTRAINT `PK_POLICY` PRIMARY KEY (
                                            `id`
        );

ALTER TABLE `board`
    ADD CONSTRAINT `PK_BOARD` PRIMARY KEY (
                                           `id`
        );

ALTER TABLE `diary_report`
    ADD CONSTRAINT `PK_DIARY_REPORT` PRIMARY KEY (
                                                  `id`
        );

ALTER TABLE `popup`
    ADD CONSTRAINT `PK_POPUP` PRIMARY KEY (
                                           `id`
        );

ALTER TABLE `post`
    ADD CONSTRAINT `PK_POST` PRIMARY KEY (
                                          `id`
        );

ALTER TABLE `diary_interest`
    ADD CONSTRAINT `PK_DIARY_INTEREST` PRIMARY KEY (
                                                    `id`
        );

ALTER TABLE `chat_open_reason`
    ADD CONSTRAINT `PK_CHAT_OPEN_REASON` PRIMARY KEY (
                                                      `id`
        );

ALTER TABLE `diary_tag`
    ADD CONSTRAINT `PK_DIARY_TAG` PRIMARY KEY (
                                               `id`
        );

ALTER TABLE `tag`
    ADD CONSTRAINT `PK_TAG` PRIMARY KEY (
                                         `id`
        );

ALTER TABLE `vaccine`
    ADD CONSTRAINT `PK_VACCINE` PRIMARY KEY (
                                             `id`
        );

ALTER TABLE `adopt_vaccine`
    ADD CONSTRAINT `PK_ADOPT_VACCINE` PRIMARY KEY (
                                                   `id`
        );

ALTER TABLE `adopt_report`
    ADD CONSTRAINT `FK_adopt_TO_adopt_report_1` FOREIGN KEY (
                                                             `adopt_id`
        )
        REFERENCES `adopt` (
                            `id`
            );

ALTER TABLE `role_resource`
    ADD CONSTRAINT `FK_role_TO_role_resource_1` FOREIGN KEY (
                                                             `role_id`
        )
        REFERENCES `role` (
                           `id`
            );

ALTER TABLE `role_resource`
    ADD CONSTRAINT `FK_resource_TO_role_resource_1` FOREIGN KEY (
                                                                 `resource_id`
        )
        REFERENCES `resource` (
                               `id`
            );

ALTER TABLE `file`
    ADD CONSTRAINT `FK_file_group_TO_file_1` FOREIGN KEY (
                                                          `group_id`
        )
        REFERENCES `file_group` (
                                 `id`
            );

ALTER TABLE `member_role`
    ADD CONSTRAINT `FK_member_TO_member_role_1` FOREIGN KEY (
                                                             `member_id`
        )
        REFERENCES `member` (
                             `id`
            );

ALTER TABLE `member_role`
    ADD CONSTRAINT `FK_role_TO_member_role_1` FOREIGN KEY (
                                                           `role_id`
        )
        REFERENCES `role` (
                           `id`
            );

ALTER TABLE `adopt_interest`
    ADD CONSTRAINT `FK_adopt_TO_adopt_interest_1` FOREIGN KEY (
                                                               `adopt_id`
        )
        REFERENCES `adopt` (
                            `id`
            );

ALTER TABLE `animal_division2`
    ADD CONSTRAINT `FK_animal_division1_TO_animal_division2_1` FOREIGN KEY (
                                                                            `animal_division1_id`
        )
        REFERENCES `animal_division1` (
                                       `id`
            );

ALTER TABLE `inquiry_answer`
    ADD CONSTRAINT `FK_inquiry_TO_inquiry_answer_1` FOREIGN KEY (
                                                                 `inquiry_id`
        )
        REFERENCES `inquiry` (
                              `id`
            );

ALTER TABLE `send_history`
    ADD CONSTRAINT `FK_send_template_TO_send_history_1` FOREIGN KEY (
                                                                     `send_template_id`
        )
        REFERENCES `send_template` (
                                    `id`
            );

ALTER TABLE `blocked_member`
    ADD CONSTRAINT `FK_member_TO_blocked_member_1` FOREIGN KEY (
                                                                `member_id`
        )
        REFERENCES `member` (
                             `id`
            );

ALTER TABLE `diary_report`
    ADD CONSTRAINT `FK_diary_TO_diary_report_1` FOREIGN KEY (
                                                             `diary_id`
        )
        REFERENCES `diary` (
                            `id`
            );

ALTER TABLE `post`
    ADD CONSTRAINT `FK_board_TO_post_1` FOREIGN KEY (
                                                     `board_id`
        )
        REFERENCES `board` (
                            `id`
            );

ALTER TABLE `diary_interest`
    ADD CONSTRAINT `FK_diary_TO_diary_interest_1` FOREIGN KEY (
                                                               `diary_id`
        )
        REFERENCES `diary` (
                            `id`
            );

ALTER TABLE `diary_tag`
    ADD CONSTRAINT `FK_diary_TO_diary_tag_1` FOREIGN KEY (
                                                          `diary_id`
        )
        REFERENCES `diary` (
                            `id`
            );

ALTER TABLE `diary_tag`
    ADD CONSTRAINT `FK_tag_TO_diary_tag_1` FOREIGN KEY (
                                                        `tag_id`
        )
        REFERENCES `tag` (
                          `id`
            );

ALTER TABLE `adopt_vaccine`
    ADD CONSTRAINT `FK_adopt_TO_adopt_vaccine_1` FOREIGN KEY (
                                                              `adopt_id`
        )
        REFERENCES `adopt` (
                            `id`
            );

ALTER TABLE `adopt_vaccine`
    ADD CONSTRAINT `FK_vaccine_TO_adopt_vaccine_1` FOREIGN KEY (
                                                                `vaccine_id`
        )
        REFERENCES `vaccine` (
                              `id`
            );

ALTER TABLE `manager_notice_board`
    ADD CONSTRAINT `PK_MANAGER_NOTICE_BOARD` PRIMARY KEY (
                                                         `id`
        );

SET foreign_key_checks = 1;
