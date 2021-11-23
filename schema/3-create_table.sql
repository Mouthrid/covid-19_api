\connect covid19_db app_admin

--the script to remove all tables in the database
/*
DROP TABLE IF EXISTS news CASCADE;
DROP TABLE IF EXISTS video CASCADE;
DROP TABLE IF EXISTS channel CASCADE;
*/

CREATE TABLE news (
  id varchar(1000) PRIMARY KEY not null,
  title varchar(1000) not null,
  news_url varchar(1000) not null,
  img_url varchar(1000) not null,
  description varchar(1000) not null,
  news_time timestamp with time zone not null default current_timestamp,
  crawl_time timestamp with time zone not null default current_timestamp
);

CREATE TABLE video (
  id varchar(1000) PRIMARY KEY not null,
  title varchar(1000) not null,
  video_url varchar(1000) not null,
  img_url varchar(1000) not null,
  view_state varchar(1000) not null,
  duration varchar(1000) not null,
  view_count int not null default 0,
  channel_id varchar(1000) not null
);

CREATE TABLE channel (
  id varchar(1000) PRIMARY KEY not null,
  name varchar(1000) not null,
  img_url varchar(1000) not null
);

