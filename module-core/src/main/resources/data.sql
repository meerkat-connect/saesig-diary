INSERT INTO member(id, email, password, signup_method, nickname, status, joined_at, modified_at, modified_by, created_at, created_by, service_agreement, privacy_agreement)
VALUES (1, 'email@email.com', '1qaz2wsx!@', '이메일', '서정도', 'NORMAL', NOW(), NOW(), 1, NOW(), 1, 'Y', 'Y');

INSERT INTO send_template
VALUES (1,1,1,1,1,'Y','-',NOW(),1,NOW(),1);