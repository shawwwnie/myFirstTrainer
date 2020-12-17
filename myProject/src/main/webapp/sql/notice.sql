create table notice(
    notice_num number not null,
    board_num number,
    writer_memnum number not null,
    reply_mem_num number not null,
    writer_board clob,
    board_comment varchar2(200),
    reg_date Date default sysdate not null,
    notice_comment varchar2(100) not null,
    return_url varchar2(100),
    check_date Date,
    constraint notice_num_pk primary key (notice_num)   
);
create sequence notice_num_seq;