-- 2024년 12월 5일 Fitness Project
create database scit;
use scit;
drop table if exists fitness;
create table fitness (
	id bigint primary key auto_increment
    , fname varchar(50) not null
    , gender char(1) default '1'
    , height decimal(6, 2)
    , weight decimal(6, 2)
    , join_date datetime default current_timestamp
);