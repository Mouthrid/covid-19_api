\connect covid19_db app_admin

ALTER TABLE video ADD FOREIGN KEY (channel_id) REFERENCES channel (channel_id) MATCH FULL;
