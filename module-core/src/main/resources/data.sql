INSERT INTO member(id, email, password, signup_method, nickname, status, joined_at, modified_at, modified_by, created_at, created_by, service_agreement, privacy_agreement)
VALUES (1, 'email@email.com', '1qaz2wsx!@', '이메일', '서정도', 'NORMAL', NOW(), NOW(), 1, NOW(), 1, 'Y', 'Y');

INSERT INTO member(id, email, password, signup_method, nickname, status, joined_at, modified_at, modified_by, created_at, created_by, service_agreement, privacy_agreement)
VALUES (2, 'email@email.com', '1qaz2wsx!@', '카카오', '서정도', 'NORMAL', NOW(), NOW(), 1, NOW(), 1, 'Y', 'Y');

INSERT INTO member(id, email, password, signup_method, nickname, status, joined_at, modified_at, modified_by, created_at, created_by, service_agreement, privacy_agreement)
VALUES (3, 'email@email.com', '1qaz2wsx!@', '네이버', '서정도', 'NORMAL', NOW(), NOW(), 1, NOW(), 1, 'Y', 'Y');

INSERT INTO member(id, email, password, signup_method, nickname, status, joined_at, modified_at, modified_by, created_at, created_by, service_agreement, privacy_agreement)
VALUES (4, 'email@email.com', '1qaz2wsx!@', '구글', '서정도', 'NORMAL', NOW(), NOW(), 1, NOW(), 1, 'Y', 'Y');

INSERT INTO member(id, email, password, signup_method, nickname, status, joined_at, modified_at, modified_by, created_at, created_by, service_agreement, privacy_agreement)
VALUES (5, 'email@email.com', '1qaz2wsx!@', '이메일', '서정도', 'NORMAL', NOW(), NOW(), 1, NOW(), 1, 'Y', 'Y');

INSERT INTO member(id, email, password, signup_method, nickname, status, joined_at, modified_at, modified_by, created_at, created_by, service_agreement, privacy_agreement)
VALUES (6, 'email@email.com', '1qaz2wsx!@', '카카오', '서정도', 'NORMAL', NOW(), NOW(), 1, NOW(), 1, 'Y', 'Y');

INSERT INTO member(id, email, password, signup_method, nickname, status, joined_at, modified_at, modified_by, created_at, created_by, service_agreement, privacy_agreement)
VALUES (7, 'email@email.com', '1qaz2wsx!@', '네이버', '서정도', 'NORMAL', NOW(), NOW(), 1, NOW(), 1, 'Y', 'Y');

INSERT INTO member(id, email, password, signup_method, nickname, status, joined_at, modified_at, modified_by, created_at, created_by, service_agreement, privacy_agreement)
VALUES (8, 'email@email.com', '1qaz2wsx!@', '구글', '서정도', 'NORMAL', NOW(), NOW(), 1, NOW(), 1, 'Y', 'Y');

INSERT INTO member(id, email, password, signup_method, nickname, status, joined_at, modified_at, modified_by, created_at, created_by, service_agreement, privacy_agreement)
VALUES (9, 'email@email.com', '1qaz2wsx!@', '구글', '서정도', 'NORMAL', NOW(), NOW(), 1, NOW(), 1, 'Y', 'Y');

INSERT INTO member(id, email, password, signup_method, nickname, status, joined_at, modified_at, modified_by, created_at, created_by, service_agreement, privacy_agreement)
VALUES (10, 'email@email.com', '1qaz2wsx!@', '네이버', '서정도', 'NORMAL', NOW(), NOW(), 1, NOW(), 1, 'Y', 'Y');

INSERT INTO role(id, name, upper_id, is_enabled, description, modified_at, modified_by, created_at, created_by)
VALUES (1, 'ROLE_USER', NULL, 'Y', '사용자 역할', NOW(), 1, NOW(), 1);

INSERT INTO role(id, name, upper_id, is_enabled, description, modified_at, modified_by, created_at, created_by)
VALUES (2, 'ROLE_ADMIN', NULL, 'Y', '관리자 역할', NOW(), 1, NOW(), 1);

INSERT INTO resource(id, name, url, http_method, is_enabled, type, depth, ord, upper_id, modified_at, modified_by, created_at, created_by)
VALUES (1, '관리자 메인화면', '/', 'GET', 'Y', 'MENU', 1, 1, NULL, NOW(), 1, NOW(), 1);

INSERT INTO resource(id, name, url, http_method, is_enabled, type, depth, ord, upper_id, modified_at, modified_by, created_at, created_by)
VALUES (2, '관리자 회원관리 메인화면', '/admin/users', 'GET', 'Y', 'MENU', 1, 1, NULL, NOW(), 1, NOW(), 1);

INSERT INTO resource(id, name, url, http_method, is_enabled, type, depth, ord, upper_id, modified_at, modified_by, created_at, created_by)
VALUES (3, '자원관리 메인화면', '/admin/resources', 'GET', 'Y', 'MENU', 1, 1, NULL, NOW(), 1, NOW(), 1);

INSERT INTO resource(id, name, url, http_method, is_enabled, type, depth, ord, upper_id, modified_at, modified_by, created_at, created_by)
VALUES (4, '자원관리 등록', '/admin/resources', 'POST', 'Y', 'MENU', 2, 1, 3, NOW(), 1, NOW(), 1);

INSERT INTO resource(id, name, url, http_method, is_enabled, type, depth, ord, upper_id, modified_at, modified_by, created_at, created_by)
VALUES (5, '자원관리 등록 > 하위', '/admin/resources/under', 'GET', 'Y', 'MENU', 2, 1, 4, NOW(), 1, NOW(), 1);

INSERT INTO resource(id, name, url, http_method, is_enabled, type, depth, ord, upper_id, modified_at, modified_by, created_at, created_by)
VALUES (6, '자원관리 등록 > 하위 > 하위', '/admin/resources/under/under', 'GET', 'Y', 'FUNCTION', 3, 1, 5, NOW(), 1, NOW(), 1);

