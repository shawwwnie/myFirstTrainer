create table board_sysqna(
  sq_num number not null,
  sq_title varchar2(100) not null,
  sq_content clob not null,
  sq_hit number(5) default 0 not null,
  sq_reg_date date default sysdate not null,
  sq_modify_date date default sysdate not null,
  sq_file blob,
  sq_filename varchar2(100),
  sq_ip varchar2(40) not null,
  mem_num number not null,
  constraint boardsysqna_pk primary key (sq_num),
  constraint boardsysqna_fk foreign key (mem_num) references member(mem_num)
);
create sequence sq_num_seq;

create table board_sysqna_comment(
    sqc_num number not null,
    sq_comment varchar2(200) not null,
    sqc_reg_date date default sysdate not null,
    sqc_modify_date date default sysdate not null,
    sq_num number not null,
    mem_num number not null,
    constraint sqc_num_pk primary key (sqc_num),
    constraint mem_num_sqcfk foreign key (mem_num) references member(mem_num)
);
create sequence sqc_num_seq;