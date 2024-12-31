-- ---------- 2024년 12월 23일 회원관리
USE scit;

DROP TABLE IF EXISTS scit.tbl_user;

CREATE TABLE scit.tbl_user 
(
	seq         int AUTO_INCREMENT PRIMARY KEY
	, user_id   varchar(255) UNIQUE
	, user_pwd  varchar(255) NOT NULL
	, user_name varchar(255) NOT NULL
	, role      varchar(255) DEFAULT 'ROLE_USER' CHECK (role IN ('ROLE_USER', 'ROLE_ADMIN'))
);

COMMIT;

SELECT * FROM scit.tbl_user;