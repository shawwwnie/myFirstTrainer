create table hwboard(
hw_num number not null,
hw_title varchar2(50) not null,
hw_link varchar2(500) not null,
hw_part varchar2(10) not null,
hw_kcal number not null
);

create SEQUENCE hwboard_seq;

insert into hwboard values (hwboard_seq.nextval, '땅크부부  하체 홈트','NDsjmxTROEo','하체',150);

insert into hwboard values (hwboard_seq.nextval, '다노티비 전신 홈트','cMkZ6A7wngk','전신',150);

insert into hwboard values (hwboard_seq.nextval, '권혁 가슴 홈트','c_5ENJWekbQ','가슴',150);