create table feed(
  feed_num number not null,/*글번호*/
  feed_type number not null,/*1:식단, 2:운동*/
  feed_content clob not null,/*글 내용*/
  feed_reg_date date not null,/*작성일*/
  feed_file blob,/*업로드한 파일*/
  feed_filename varchar2(150),/*업로드한 파일명*/
  feed_ip varchar2(40) not null,/*ip*/
  mem_num number not null,/*업로드한 회원의 mem_num*/
  mem_id varchar2(20) not null,/*업로드한 회원의 mem_id*/
  feed_auth number(5) not null,/*글 공유범위 0:나만보기,1:트레이너만,2:팔로워만,3:전체공개*/
  constraint feed_pk primary key (feed_num),
  constraint feed_mem_num_fk foreign key (mem_num) references member (mem_num),
  constraint feed_mem_id_fk foreign key (mem_id) references member (mem_id)
);
create sequence feed_num;

create table feed_comment(
    feedc_num number not null,
    feedc_comment varchar2(50) not null,
    feedc_reg_date date default sysdate not null,
    feedc_modify_date date default sysdate not null,
    feed_num number not null,
    mem_num number not null,
    constraint feedc_num_pk primary key (feedc_num),
    constraint feed_num_fk1 foreign key (feed_num) references feed(feed_num),
    constraint mem_num_fk1 foreign key (mem_num) references member(mem_num)
);
create sequence feed_num_seq;

create table follower(
	follower_num number not null,/*팔로우번호(보드의 글번호와 같은 개념)*/
	mem_num number not null,/*현재 로그인한 회원의 mem_num*/
	follower_to number not null,/*로그인한 회원이 팔로우하는 회원의 mem_num*/
	constraint follow_num_pk primary key (follower_num),
	constraint follow_mem_num_fk foreign key (mem_num) references member (mem_num)
);

create sequence follower_number;

create table training(
	training_num number not null,/*트레이닝 관계 번호(보드의 글번호와 같은 개념)*/
	mem_num number not null,/*현재 로그인한 회원의 mem_num*/
	training_to number not null,/*트레이닝 받는 회원의 mem_num*/
	constraint training_num_pk primary key (training_num),
	constraint training_mem_num_fk foreign key (mem_num) references member (mem_num)
);

create sequence training_number;
