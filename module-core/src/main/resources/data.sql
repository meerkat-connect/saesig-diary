INSERT INTO member(id, email, password, signup_method, nickname, status, joined_at, modified_at, modified_by, created_at, created_by, service_agreement, privacy_agreement)
VALUES (1, 'email1@email.com', '{bcrypt}$2a$10$qlVoO2ynDB1hCxQnEFf1iu5aQgtQoJhm47V6iJmIwV0oc2u134HD6', 'EMAIL', '서정도1', 'NORMAL', NOW(), NOW(), 1, NOW(), 1, 'Y', 'Y');

INSERT INTO member(id, email, password, signup_method, nickname, status, joined_at, modified_at, modified_by, created_at, created_by, service_agreement, privacy_agreement)
VALUES (2, 'email2@email.com', '$2a$10$qlVoO2ynDB1hCxQnEFf1iu5aQgtQoJhm47V6iJmIwV0oc2u134HD6', 'EMAIL', '서정도2', 'NORMAL', NOW(), NOW(), 1, NOW(), 1, 'Y', 'Y');

INSERT INTO member(id, email, password, signup_method, nickname, status, joined_at, modified_at, modified_by, created_at, created_by, service_agreement, privacy_agreement)
VALUES (3, 'email3@email.com', '$2a$10$qlVoO2ynDB1hCxQnEFf1iu5aQgtQoJhm47V6iJmIwV0oc2u134HD6', 'EMAIL', '서정도3', 'NORMAL', NOW(), NOW(), 1, NOW(), 1, 'Y', 'Y');

INSERT INTO member(id, email, password, signup_method, nickname, status, joined_at, modified_at, modified_by, created_at, created_by, service_agreement, privacy_agreement)
VALUES (4, 'email4@email.com', '$2a$10$qlVoO2ynDB1hCxQnEFf1iu5aQgtQoJhm47V6iJmIwV0oc2u134HD6', 'EMAIL', '서정도4', 'NORMAL', NOW(), NOW(), 1, NOW(), 1, 'Y', 'Y');

INSERT INTO member(id, email, password, signup_method, nickname, status, joined_at, modified_at, modified_by, created_at, created_by, service_agreement, privacy_agreement)
VALUES (5, 'email5@email.com', '$2a$10$qlVoO2ynDB1hCxQnEFf1iu5aQgtQoJhm47V6iJmIwV0oc2u134HD6', 'SOCIAL', '서정도5', 'NORMAL', NOW(), NOW(), 1, NOW(), 1, 'Y', 'Y');

INSERT INTO member(id, email, password, signup_method, nickname, status, joined_at, modified_at, modified_by, created_at, created_by, service_agreement, privacy_agreement)
VALUES (6, 'email6@email.com', '$2a$10$qlVoO2ynDB1hCxQnEFf1iu5aQgtQoJhm47V6iJmIwV0oc2u134HD6', 'SOCIAL', '서정도6', 'NORMAL', NOW(), NOW(), 1, NOW(), 1, 'Y', 'Y');

INSERT INTO member(id, email, password, signup_method, nickname, status, joined_at, modified_at, modified_by, created_at, created_by, service_agreement, privacy_agreement)
VALUES (7, 'email7@email.com', '$2a$10$qlVoO2ynDB1hCxQnEFf1iu5aQgtQoJhm47V6iJmIwV0oc2u134HD6', 'SOCIAL', '서정도7', 'NORMAL', NOW(), NOW(), 1, NOW(), 1, 'Y', 'Y');

INSERT INTO member(id, email, password, signup_method, nickname, status, joined_at, modified_at, modified_by, created_at, created_by, service_agreement, privacy_agreement)
VALUES (8, 'email8@email.com', '$2a$10$qlVoO2ynDB1hCxQnEFf1iu5aQgtQoJhm47V6iJmIwV0oc2u134HD6', 'SOCIAL', '서정도8', 'NORMAL', NOW(), NOW(), 1, NOW(), 1, 'Y', 'Y');

INSERT INTO member(id, email, password, signup_method, nickname, status, joined_at, modified_at, modified_by, created_at, created_by, service_agreement, privacy_agreement)
VALUES (9, 'email9@email.com', '$2a$10$qlVoO2ynDB1hCxQnEFf1iu5aQgtQoJhm47V6iJmIwV0oc2u134HD6', 'SOCIAL', '서정도9', 'NORMAL', NOW(), NOW(), 1, NOW(), 1, 'Y', 'Y');

INSERT INTO role(id, name, upper_id, is_enabled, description, modified_at, modified_by, created_at, created_by)
VALUES (1, 'ROLE_USER', NULL, 'Y', '사용자 역할', NOW(), 1, NOW(), 1);

INSERT INTO role(id, name, upper_id, is_enabled, description, modified_at, modified_by, created_at, created_by)
VALUES (2, 'ROLE_ADMIN', NULL, 'Y', '관리자 역할', NOW(), 1, NOW(), 1);

INSERT INTO role(id, name, upper_id, is_enabled, description, modified_at, modified_by, created_at, created_by)
VALUES (3, 'ROLE_GUEST', NULL, 'Y', '게스트 역할', NOW(), 1, NOW(), 1);

INSERT INTO member_role(id, member_id,role_id, modified_at, modified_by, created_at, created_by)
VALUES(1, 1, 2, NOW(),1,NOW(),1);

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

INSERT INTO resource(id, name, url, http_method, is_enabled, type, depth, ord, upper_id, modified_at, modified_by, created_at, created_by)
VALUES (7, 'DashBoard', '/admin', 'GET', 'Y', 'MENU', 1, 1, NULL, NOW(), 1, NOW(), 1);

INSERT INTO role_resource(id, role_id, resource_id, modified_at, modified_by, created_at, created_by)
VALUES (1, 1, 1, NOW(), 1, NOW(), 1);

INSERT INTO role_resource(id, role_id, resource_id, modified_at, modified_by, created_at, created_by)
VALUES (2, 1, 2, NOW(), 1, NOW(), 1);

INSERT INTO role_resource(id, role_id, resource_id, modified_at, modified_by, created_at, created_by)
VALUES (3, 1, 3, NOW(), 1, NOW(), 1);

INSERT INTO role_resource(id, role_id, resource_id, modified_at, modified_by, created_at, created_by)
VALUES (4, 1, 4, NOW(), 1, NOW(), 1);

INSERT INTO role_resource(id, role_id, resource_id, modified_at, modified_by, created_at, created_by)
VALUES (5, 1, 5, NOW(), 1, NOW(), 1);


INSERT INTO faq(id, category, title, content, ord, is_enabled, modified_at, modified_by, created_at, created_by)
VALUES (1, 'TYPE_A', 'Lorem ipsum dolor sit amet', 'content1',  1, 'Y', NOW(), 1, NOW(), 1);

INSERT INTO faq(id, category, title, content, ord, is_enabled, modified_at, modified_by, created_at, created_by)
VALUES (2, 'TYPE_A', 'Pellentesque erat arcu', 'content2', 2, 'Y', NOW(), 1, NOW(), 1);

INSERT INTO faq(id, category, title, content, ord, is_enabled, modified_at, modified_by, created_at, created_by)
VALUES (3, 'TYPE_C', 'Nam vitae tellus lectus. Sed porttit', 'content3', 3, 'Y', NOW(), 1, NOW(), 1);

INSERT INTO faq(id, category, title, content, ord, is_enabled, modified_at, modified_by, created_at, created_by)
VALUES (4, 'TYPE_D', 'nec massa eu faucibus', 'content4', 4, 'Y', NOW(), 1, NOW(), 1);

INSERT INTO faq(id, category, title, content, ord, is_enabled, modified_at, modified_by, created_at, created_by)
VALUES (5, 'TYPE_E', 'Fusce eget turpis lorem', 'content5', 5, 'Y', NOW(), 1, NOW(), 1);

INSERT INTO policy(id,category,title,content,is_enabled,modified_at, modified_by, created_at, created_by)
VALUES (1,'TYPE_A','약관 제목', '약관 내용','Y',NOW(), 1, NOW(), 1);

INSERT INTO policy(id,category,title,content,is_enabled,modified_at, modified_by, created_at, created_by)
VALUES (2,'TYPE_B','정책 제목', '정책 내용','Y',NOW(), 1, NOW(), 1);