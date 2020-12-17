
create table member(
  mem_num number not null,
  mem_id varchar2(20) unique not null,
  mem_auth number(1),/*0관리자,1일반회원,2트레이너,3탈퇴회원*/
  constraint member_pk primary key (mem_num)
);

create table trainer_detail(
  mem_num number not null,
  g_addr varchar2(100),
  career clob,
  mem_gender number not null,/*1:여성회원,2:남성회원*/
  exp varchar2(10) not null,
  mem_name varchar2(30) not null,
  mem_pw varchar2(30) not null,
  mem_cell varchar2(15) not null,
  mem_email varchar2(50) not null,
  mem_pic blob,
  mem_picName varchar2(50),
  mem_zipcode varchar2(7),
  mem_addr1 varchar2(50),
  mem_addr2 varchar2(50),
  mem_intro clob,
  mem_date date default sysdate not null,
  constraint trainerer_detail_pk primary key (mem_num),
  constraint trainer_detail_fk foreign key (mem_num) references member (mem_num)
);

create table member_detail(
  mem_num number not null,
  t_num number(10),
  mem_gender number not null,/*1:여성회원,2:남성회원*/
  mem_name varchar2(30) not null,
  mem_pw varchar2(30) not null,
  mem_cell varchar2(15) not null,
  mem_email varchar2(50) not null,
  mem_pic blob,
  mem_picName varchar2(50),
  mem_zipcode varchar2(7),
  mem_addr1 varchar2(50),
  mem_addr2 varchar2(50),
  mem_intro clob,
  mem_date date default sysdate not null,
  constraint member_detail_pk primary key (mem_num),
  constraint member_detail_fk foreign key (mem_num) references member (mem_num),
  constraint member_detail_trainer_fk foreign key (t_num) references trainer_detail (mem_num)
);

create sequence member_seq;