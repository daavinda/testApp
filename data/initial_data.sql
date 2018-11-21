-- user role permission

--  permissions
INSERT INTO system_permission (id, description, permission_name, depends_on_permission_id) VALUES (1, 'User login permission', 'ROLE_LOGIN', null);
INSERT INTO system_permission (id, description, permission_name, depends_on_permission_id) VALUES (2, 'User login permission', 'ROLE_MANAGE_APPLICATION', null);
INSERT INTO system_permission (id, description, permission_name, depends_on_permission_id) VALUES (3, 'User login permission', 'ROLE_MANAGE_SUBSCRIBER', null);
INSERT INTO system_permission (id, description, permission_name, depends_on_permission_id) VALUES (4, 'User login permission', 'ROLE_MANAGE_ROLE', null);
INSERT INTO system_permission (id, description, permission_name, depends_on_permission_id) VALUES (5, 'User login permission', 'ROLE_MANAGE_USER', null);

-- user role
INSERT INTO system_user_role (id, name) VALUES (1, 'ADMIN');

-- ADMIN Permissions
INSERT INTO system_role_permission (id, permission_id) VALUES (1, 1);

-- users
-- INSERT INTO system_user (id, username, password, state, role_id) VALUES (1, 'admin','$2a$10$nSLDupAfFuPhNrtLfnNk4.xwGCeO3yCXdm1ckmp.FVqkl3VIO1l2m', 'ACTIVE', 1);