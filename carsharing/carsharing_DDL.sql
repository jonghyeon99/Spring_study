-- 2024년 12월 27일 SCIT 46기 Car Sharing with Login Project
USE scit;

-- 테이블 삭제
DROP TABLE IF EXISTS scit.sharing_order;
DROP TABLE IF EXISTS scit.sharing_car;
DROP TABLE IF EXISTS scit.sharing_user;

-- 1) 회원 정보 테이블
CREATE TABLE scit.sharing_user
(
    user_id    varchar(50)      NOT NULL 
    , user_pwd varchar(100)     
    , user_nm  varchar(50)      NOT NULL
    , roles    varchar(50)      DEFAULT 'ROLE_USER' 
    , CONSTRAINT user_user_id_pk PRIMARY KEY(user_id)
    , CONSTRAINT user_roles_ck CHECK(roles IN ('ROLE_USER', 'ROLE_ADMIN'))
);

-- 2) 차량 정보 테이블
CREATE TABLE scit.sharing_car
(
    car_seq    int              AUTO_INCREMENT NOT NULL  
    , car_type varchar(50)      NOT NULL 
    , car_status char(1)        DEFAULT '1' 
     , CONSTRAINT car_car_seq_pk PRIMARY KEY(car_seq)
     , CONSTRAINT user_car_status_ck CHECK (car_status IN ('0', '1'))
);

-- 3) 주문 정보 테이블
CREATE TABLE scit.sharing_order
(
    order_seq    int            AUTO_INCREMENT NOT NULL 
    , user_id    varchar(50)    
    , car_seq     int 
    , sharing_status char(1)    DEFAULT '1' 
    , sharing_date   date       DEFAULT (current_date)    -- current_date는 괄호를 해야 오류가 없다.
    , CONSTRAINT order_order_seq_pk PRIMARY KEY(order_seq)
    , CONSTRAINT order_user_id_fk FOREIGN KEY(user_id) REFERENCES scit.sharing_user(user_id)
    , CONSTRAINT order_car_seq_fk FOREIGN KEY(car_seq) REFERENCES scit.sharing_car(car_seq)
    , CONSTRAINT order_car_seq_uq UNIQUE (car_seq)
    , CONSTRAINT order_car_status_ck CHECK (sharing_status IN ('0', '1'))
);


-- 자동차 삽입
insert into scit.sharing_car (car_type) values ('모닝');
insert into scit.sharing_car (car_type) values ('레이');
insert into scit.sharing_car (car_type) values ('코나');
insert into scit.sharing_car (car_type) values ('투싼');
insert into scit.sharing_car (car_type) values ('K3');
insert into scit.sharing_car (car_type) values ('K5');
insert into scit.sharing_car (car_type) values ('아반떼');
insert into scit.sharing_car (car_type) values ('쏘나타');

COMMIT;
SELECT * FROM scit.sharing_user;
SELECT * FROM scit.sharing_car;
SELECT * FROM scit.sharing_order;

-- 차량 예약 정보 확인
select o.user_id, o.car_seq
       , o.sharing_status, o.sharing_date, c.car_type
from sharing_car c join sharing_order o
where c.car_seq =  o.car_seq;
