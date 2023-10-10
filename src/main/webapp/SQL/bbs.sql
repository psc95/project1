--bbs.sql
create table bbs(
 bbs_no number(38) primary key --게시물번호
 ,bbs_name varchar2(100) not null --작성자
 ,bbs_title varchar2(200) not null --제목
 ,bbs_pwd varchar2(20) not null --비밀번호
 ,bbs_cont varchar2(4000) not null --내용
 ,bbs_file varchar2(200) --이진파일명
 ,bbs_hit number(38) default 0 --조회수
 ,bbs_ref number(38) --글 그룹번호
 ,bbs_step number(38) -- 첫번째 답변글 1,두번째 답변글 2,즉
 --원본글과 답변글을 구분하는 번호값,몇번째 답변글인가를 알려줌
 ,bbs_level number(38) -- 답변글 정렬순서
 ,bbs_date date --글 등록날짜 
);

alter table bbs modify (bbs_cont varchar2(4000));

select * from bbs order by bbs_no desc;

delete from bbs where bbs_no=3;
commit;

drop table bbs;
drop sequence bbs_no_Seq;

--bbs_no_seq 시퀀스 생성
create sequence bbs_no_seq
start with 1
increment by 1
nocache
nocycle;

drop sequence bbs_no_seq;

delete from bbs where bbs_no=28;

commit;