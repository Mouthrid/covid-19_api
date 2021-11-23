ALTER DATABASE covid19_db SET timezone TO 'UTC';

REVOKE USAGE ON SCHEMA public FROM PUBLIC;
REVOKE CREATE ON SCHEMA public FROM PUBLIC;

GRANT USAGE ON SCHEMA public to app_admin;
GRANT CREATE ON SCHEMA public to app_admin;

GRANT USAGE ON SCHEMA public to app_user;
GRANT USAGE ON SCHEMA public to app_readonly;

