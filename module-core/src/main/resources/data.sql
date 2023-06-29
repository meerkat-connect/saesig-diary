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
VALUES (10, 'email@email.com', '1qaz2wsx!@', '네이버', '서정도', 'NORMAL', NOW(), NOW(), 1, NOW(), 1, 'Y', 'Y');

INSERT INTO role(id, name, upper_id, is_enabled, description, modified_at, modified_by, created_at, created_by)
VALUES (1, 'ROLE_USER', NULL, 'Y', '사용자 역할', NOW(), 1, NOW(), 1);

INSERT INTO role(id, name, upper_id, is_enabled, description, modified_at, modified_by, created_at, created_by)
VALUES (2, 'ROLE_ADMIN', NULL, 'Y', '관리자 역할', NOW(), 1, NOW(), 1);

INSERT INTO resource(id, name, url, http_method, is_enabled, type, depth, ord, upper_id, modified_at, modified_by, created_at, created_by)
VALUES (1, '회원관리', '/admin/members', 'GET', 'Y', 'MENU', 1, 1, NULL, NOW(), 1, NOW(), 1);

INSERT INTO resource(id, name, url, http_method, is_enabled, type, depth, ord, upper_id, modified_at, modified_by, created_at, created_by)
VALUES (2, '시스템관리', '/admin/system', 'GET', 'Y', 'MENU', 1, 2, NULL, NOW(), 1, NOW(), 1);

INSERT INTO resource(id, name, url, http_method, is_enabled, type, depth, ord, upper_id, modified_at, modified_by, created_at, created_by)
VALUES (3, '시스템관리 목록화면', '/admin/system/view.html', 'GET', 'Y', 'MENU', 2, 1, 2, NOW(), 1, NOW(), 1);

INSERT INTO resource(id, name, url, http_method, is_enabled, type, depth, ord, upper_id, modified_at, modified_by, created_at, created_by)
VALUES (4, '시스템관리 등록', '/admin/system/**', 'POST', 'Y', 'FUNCTION', 3, 1, 3, NOW(), 1, NOW(), 1);

INSERT INTO resource(id, name, url, http_method, is_enabled, type, depth, ord, upper_id, modified_at, modified_by, created_at, created_by)
VALUES (5, '시스템관리 수정', '/admin/system/**', 'PUT', 'Y', 'FUNCTION', 3, 2, 3, NOW(), 1, NOW(), 1);

INSERT INTO resource(id, name, url, http_method, is_enabled, type, depth, ord, upper_id, modified_at, modified_by, created_at, created_by)
VALUES (6, '회원관리 목록화면', '/admin/members/view.html', 'GET', 'Y', 'FUNCTION', 2, 1, 1, NOW(), 1, NOW(), 1);

