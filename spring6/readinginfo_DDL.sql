-- -------------- 2024/12/20 (개인 독서 노트)
USE scit;

DROP TABLE IF EXISTS scit.reading_note;
DROP TABLE IF EXISTS scit.book;

-- 도서정보 테이블
CREATE TABLE scit.book 
(
	book_seq      int          AUTO_INCREMENT PRIMARY KEY
	, title       varchar(200) NOT NULL
	, writer      varchar(200) NOT NULL
	, publisher   varchar(200) NOT NULL 
	, purchase_date datetime   DEFAULT current_timestamp 
	, price       int          DEFAULT 0 
);

-- 독서기록 테이블
CREATE TABLE reading_note
(
	reading_seq   int AUTO_INCREMENT PRIMARY KEY 
	, read_status varchar(50) CHECK (read_status IN ('읽는 중', '읽음'))
	, read_date   datetime
	, book_review varchar(1000)
	, book_seq    int REFERENCES book(book_seq) ON DELETE CASCADE 
);
