-- CarSharing
USE scit;

-- Create SHARING_USER table
drop table if exists scit.SHARING_ORDER;
drop table if exists scit.SHARING_USER;
drop table if exists scit.SHARING_CAR;

CREATE TABLE scit.SHARING_USER (
    USER_ID VARCHAR(50) PRIMARY KEY,
    USER_PW VARCHAR(100) NOT NULL,
    USER_NM VARCHAR(50) NOT NULL,
    ROLES VARCHAR(50) DEFAULT 'ROLE_USER',
    CHECK (ROLES IN ('ROLE_USER', 'ROLE_ADMIN'))
);

-- Create SHARING_CAR table
CREATE TABLE scit.SHARING_CAR (
    CAR_SEQ INT AUTO_INCREMENT PRIMARY KEY,
    CAR_TYPE VARCHAR(50) NOT NULL,
    CAR_STATUS CHAR(1) DEFAULT '0',
    CHECK (CAR_STATUS IN ('0', '1'))
);

-- Create SHARING_ORDER table
CREATE TABLE scit.SHARING_ORDER (
    ORDER_SEQ INT AUTO_INCREMENT PRIMARY KEY,
    USER_ID VARCHAR(20),
    CAR_ID INT,
    SHARING_STATUS CHAR(1) DEFAULT '0',
    SHARING_DATE datetime DEFAULT current_timestamp,
       constraint sharing_order_UID FOREIGN KEY (USER_ID) REFERENCES scit.SHARING_USER(USER_ID),
       constraint sharing_order_CID FOREIGN KEY (CAR_ID) REFERENCES scit.SHARING_CAR(CAR_SEQ),
       CHECK (SHARING_STATUS IN ('0', '1'))
);

insert into sharing_car (car_type) values ('모닝');
insert into sharing_car (car_type) values ('레이');
insert into sharing_car (car_type) values ('코나');
insert into sharing_car (car_type) values ('투싼');
insert into sharing_car (car_type) values ('K3');
insert into sharing_car (car_type) values ('K5');
insert into sharing_car (car_type) values ('아반떼');
insert into sharing_car (car_type) values ('쏘나타');
commit;


select * from scit.SHARING_USER;
select * from scit.SHARING_CAR;
select * from scit.SHARING_ORDER;