CREATE TABLE MEMBER (
    id	BIGINT	NOT NULL	COMMENT '회원아이디',
    email	VARCHAR(50)	NULL	COMMENT '아이디',
    password	VARCHAR(100)	NULL	COMMENT '비밀번호',
    signup_method	VARCHAR(20)	NULL	COMMENT '회원가입_수단_코드',
    nickname	VARCHAR(50)	NULL	COMMENT '닉네임',
    status	VARCHAR(20)	NULL	COMMENT '회원_상태_코드',
    joined_at	DATETIME	NULL	COMMENT '회원가입일',
    modified_at	DATETIME	NULL	COMMENT '수정일',
    modified_by	BIGINT	NULL	COMMENT '수정자아이디',
    created_at	DATETIME	NULL	COMMENT '등록일',
    created_by	BIGINT	NULL	COMMENT '등록자아이디'
);

ALTER TABLE MEMBER ADD CONSTRAINT `PK_MEMBER` PRIMARY KEY (
    id
);

