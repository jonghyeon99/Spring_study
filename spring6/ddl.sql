-- -------------24.12.20 (개인 독서 노트)
use scit;
drop table if exists scit.book;
-- 도서 정보 테이블
create table scit.book (
	book_seq int primary key auto_increment,
	title varchar(200) not null,
	writer varchar(200) not null,
	publisher varchar(200) not null,
	purchase_date datetime default current_timestamp,
	price int default 0
);

DROP TABLE IF EXISTS scit.reading_note;
-- 독서 기록 테이블
CREATE TABLE scit.reading_note(
	reading_seq int primary key auto_increment,
	read_status varchar(50) check(read_status in ('읽는 중', '읽음')),
	read_date datetime,
	book_review varchar(1000),
	book_seq int REFERENCES book(book_seq) ON DELETE CASCADE
);