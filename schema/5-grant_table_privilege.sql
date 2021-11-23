\connect covid19_db app_admin

GRANT SELECT, INSERT, UPDATE, DELETE, REFERENCES ON TABLE news to app_user;
GRANT SELECT, INSERT, UPDATE, DELETE, REFERENCES ON TABLE video to app_user;
GRANT SELECT, INSERT, UPDATE, DELETE, REFERENCES ON TABLE channel to app_user;

GRANT SELECT ON TABLE news to app_readonly;
GRANT SELECT ON TABLE video to app_readonly;
GRANT SELECT ON TABLE channel to app_readonly;
