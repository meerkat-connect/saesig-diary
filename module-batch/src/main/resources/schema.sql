CREATE TABLE `member`
(
    `id`                          BIGINT AUTO_INCREMENT NOT NULL COMMENT '회원 일련번호',
    `email`                       VARCHAR(50)           NOT NULL COMMENT '이메일',
    `password`                    VARCHAR(200)          NULL COMMENT '비밀번호',
    `prev_password`               VARCHAR(200)          NULL COMMENT '직전비밀번호',
    `signup_method`               VARCHAR(20)           NULL COMMENT '가입 수단',
    `nickname`                    VARCHAR(50)           NULL COMMENT '닉네임',
    `mobile_number`               VARCHAR(20)           NULL COMMENT '전화번호',
    `status`                      VARCHAR(20)           NOT NULL COMMENT '상태',
    `last_logged_at`              DATETIME              NULL COMMENT '마지막 접속일',
    `failed_login_attempt`        INTEGER               NOT NULL DEFAULT '0' COMMENT '로그인 실패 횟수',
    `service_agreement`           VARCHAR(1)            NOT NULL COMMENT '서비스 이용약관 동의 여부',
    `location_service_agreement`  VARCHAR(1)            NOT NULL DEFAULT 'N' COMMENT '위치 기반 서비스 이용동의',
    `privacy_agreement`           VARCHAR(1)            NOT NULL COMMENT '개인정보 수집 및 이용동의',
    `password_modified_at`        DATETIME              NULL COMMENT '비밀번호 마지막 수정일',
    `marketing_service_agreement` VARCHAR(1)            NOT NULL DEFAULT 'N' COMMENT '마케팅 서비스 이용동의',
    `deleted_at`                  DATETIME              NULL COMMENT '탈퇴일',
    `modified_at`                 DATETIME              NOT NULL COMMENT '수정일',
    `modified_by`                 BIGINT                NULL COMMENT '수정자 일련번호',
    `created_at`                  DATETIME              NOT NULL COMMENT '등록일',
    `created_by`                  BIGINT                NULL COMMENT '등록자 일련번호'
);
