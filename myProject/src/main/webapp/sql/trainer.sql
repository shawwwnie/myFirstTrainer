create table matching(
mat_num number,
mem_num number not null,
mat_id varchar2(40),
mat_from number,
mat_to number, 
constraint matching_num_pk primary key (mem_num),
constraint matching_mem_num_fk foreign key (mem_num) references member_detail (mem_num)
);

create sequence mat_seq; 

/*트레이너 리스트 댓글 테이블*/
create table board_tl_comment(
    tlc_num number not null, /*primary key*/
    tl_comment varchar2(100) not null, /*댓글 내용*/
    tlc_reg_date date default sysdate not null, /*댓글 작성일*/
    tlc_modify_date date default sysdate not null, /*댓글 수정일*/
    tl_mem_num number not null, /*트레이너 넘버(트레이너 리스트 디테일 글 주인)*/
    writer_mem_num number not null, /*댓글단 회원의 mem_num*/
    constraint tlc_num_pk primary key (tlc_num)
);
create sequence tlc_num_seq;