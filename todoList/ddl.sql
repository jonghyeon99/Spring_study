-- todoList
use scit;
create table todoList (
	seqno int primary key auto_increment
    , regdate datetime default CURRENT_TIMESTAMP
    , status varchar(10) CHECK (status IN ('완료', '진행', '지연'))
    , importance varchar(10) CHECK (importance IN ('높음', '보통', '낮음'))
    , categories varchar(6) default '개인' CHECK (categories IN ('개인', '회사'))
    , todo varchar(3000)
);