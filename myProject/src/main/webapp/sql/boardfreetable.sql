create table board_free(
  free_num number not null,
  free_title varchar2(100) not null,
  free_content clob not null,
  free_hit number(5) default 0 not null,
  free_reg_date date default sysdate not null,
  free_modify_date date default sysdate not null,
  free_file blob,
  free_filename varchar2(100),
  free_ip varchar2(40) not null,
  free_type number not null,
  mem_num number not null,
  constraint boardfree_pk primary key (free_num),
  constraint boardfree_fk foreign key (mem_num) references member(mem_num)
);
create sequence board_free_seq;

create table board_free_comment(
    freec_num number not null,
    free_comment varchar2(200) not null,
    freec_reg_date date default sysdate not null,
    freec_modify_date date default sysdate not null,
    free_num number not null,
    mem_num number not null,
    constraint freec_num_pk primary key (freec_num),
    constraint free_num_fk foreign key (free_num) references board_free(free_num),
    constraint mem_num_fk foreign key (mem_num) references member(mem_num)
);
create sequence freec_num_seq;

create table board_free_comment_reply(
    rfreec_num number not null,
    rfreec_comment varchar2(150) not null,
    rfreec_date date default sysdate not null,
    freec_num number not null,
    mem_num number not null,
    constraint rfreec_num_pk primary key (rfreec_num),
    constraint freec_num_rfk foreign key (freec_num) references board_free_comment,
    constraint mem_num_rfk foreign key (mem_num) references member    
);
create sequence rfreec_num_seq;

