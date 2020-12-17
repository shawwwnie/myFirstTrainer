create table board_tranqna(
  tq_num number not null,
  tq_title varchar2(100) not null,
  tq_content clob not null,
  tq_hit number(5) default 0 not null,
  tq_reg_date date default sysdate not null,
  tq_modify_date date default sysdate not null,
  tq_file blob,
  tq_filename varchar2(100),
  tq_ip varchar2(40) not null,
  tq_type number not null,
  mem_num number not null,
  constraint boardtranqna_pk primary key (tq_num),
  constraint boardtranqna_fk foreign key (mem_num) references member(mem_num)
);
create sequence board_tranqna_seq;

create table board_tranqna_comment(
    tqc_num number not null,
    tq_comment varchar2(200) not null,
    tqc_reg_date date default sysdate not null,
    tqc_modify_date date default sysdate not null,
    tq_num number not null,
    mem_num number not null,
    constraint tqc_num_pk primary key (tqc_num),
    constraint mem_num_tqcfk foreign key (mem_num) references member(mem_num)
);
create sequence tqc_num_seq;

create table board_tranqna_comment_reply(
    rtqc_num number not null,
    rtqc_comment varchar2(150) not null,
    rtqc_date date default sysdate not null,
    tqc_num number not null,
    mem_num number not null,
    constraint rtqc_num_pk primary key (rtqc_num),
    constraint mem_num_rtqcfk foreign key (mem_num) references member    
);
create sequence rtqc_num_seq;