\connect covid19_db app_admin

ALTER TABLE videos ADD FOREIGN KEY (channel_id) REFERENCES channel (id) MATCH FULL;