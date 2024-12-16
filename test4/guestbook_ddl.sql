-- Guestbook (Ajax) --
-- 24년 12월 16일
USE scit;
DROP TABLE IF EXISTS guestbook;
CREATE TABLE guestbook (
	seqno INT PRIMARY KEY AUTO_INCREMENT,
    guest_name VARCHAR(50) NOT NULL,
    guest_pwd VARCHAR(50) NOT NULL,
    content VARCHAR(1000) NOT NULL,
    regdate DATETIME DEFAULT CURRENT_TIMESTAMP
);
COMMIT;
SELECT * FROM guestbook;