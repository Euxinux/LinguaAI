ALTER TABLE user_roles DROP CONSTRAINT IF EXISTS user_roles_user_id_fk;
ALTER TABLE user_roles DROP CONSTRAINT IF EXISTS user_roles_role_id_fk;
DROP TABLE IF EXISTS user_roles;