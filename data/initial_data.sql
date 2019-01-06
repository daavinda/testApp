-- user role permission

--  permissions
INSERT INTO system_permission (id, description, permission_name, depends_on_permission_id) VALUES (1, '', 'ROLE_LOGIN', null);
INSERT INTO system_permission (id, description, permission_name, depends_on_permission_id) VALUES (2, '', 'ROLE_MANAGE_SELLING', null);
INSERT INTO system_permission (id, description, permission_name, depends_on_permission_id) VALUES (3, '', 'ROLE_MANAGE_BUYING', null);
INSERT INTO system_permission (id, description, permission_name, depends_on_permission_id) VALUES (4, '', 'ROLE_MANAGE_PAYMENT', null);
INSERT INTO system_permission (id, description, permission_name, depends_on_permission_id) VALUES (5, '', 'ROLE_MANAGE_EXPENSE', null);
INSERT INTO system_permission (id, description, permission_name, depends_on_permission_id) VALUES (6, '', 'ROLE_MANAGE_CR', null);
INSERT INTO system_permission (id, description, permission_name, depends_on_permission_id) VALUES (7, '', 'ROLE_MANAGE_BUYER', null);
INSERT INTO system_permission (id, description, permission_name, depends_on_permission_id) VALUES (8, '', 'ROLE_MANAGE_SELLER', null);
INSERT INTO system_permission (id, description, permission_name, depends_on_permission_id) VALUES (9, '', 'ROLE_MANAGE_ITEM', null);
INSERT INTO system_permission (id, description, permission_name, depends_on_permission_id) VALUES (10, '', 'ROLE_MANAGE_EXPENSE_TYPE', null);
INSERT INTO system_permission (id, description, permission_name, depends_on_permission_id) VALUES (11, '', 'ROLE_MANAGE_FREEZER', null);
INSERT INTO system_permission (id, description, permission_name, depends_on_permission_id) VALUES (12, '', 'ROLE_MANAGE_REMOVAL', null);
INSERT INTO system_permission (id, description, permission_name, depends_on_permission_id) VALUES (13, '', 'ROLE_MANAGE_REPORT', null);
INSERT INTO system_permission (id, description, permission_name, depends_on_permission_id) VALUES (14, '', 'ROLE_MANAGE_ROLE', null);
INSERT INTO system_permission (id, description, permission_name, depends_on_permission_id) VALUES (15, '', 'ROLE_MANAGE_USER', null);

-- user role
INSERT INTO system_user_role (id, name) VALUES (1, 'ADMIN');

-- ADMIN Permissions
INSERT INTO system_role_permission (id, permission_id) VALUES (1, 1);
INSERT INTO system_role_permission (id, permission_id) VALUES (1, 2);
INSERT INTO system_role_permission (id, permission_id) VALUES (1, 3);
INSERT INTO system_role_permission (id, permission_id) VALUES (1, 4);
INSERT INTO system_role_permission (id, permission_id) VALUES (1, 5);
INSERT INTO system_role_permission (id, permission_id) VALUES (1, 6);
INSERT INTO system_role_permission (id, permission_id) VALUES (1, 7);
INSERT INTO system_role_permission (id, permission_id) VALUES (1, 8);
INSERT INTO system_role_permission (id, permission_id) VALUES (1, 9);
INSERT INTO system_role_permission (id, permission_id) VALUES (1, 10);
INSERT INTO system_role_permission (id, permission_id) VALUES (1, 11);
INSERT INTO system_role_permission (id, permission_id) VALUES (1, 12);
INSERT INTO system_role_permission (id, permission_id) VALUES (1, 13);
INSERT INTO system_role_permission (id, permission_id) VALUES (1, 14);
INSERT INTO system_role_permission (id, permission_id) VALUES (1, 15);

-- users
INSERT INTO system_user (id, username, password, state, role_id) VALUES (1, 'admin','$2a$10$nSLDupAfFuPhNrtLfnNk4.xwGCeO3yCXdm1ckmp.FVqkl3VIO1l2m', 'ACTIVE', 1);