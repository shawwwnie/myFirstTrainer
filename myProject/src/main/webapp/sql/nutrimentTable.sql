create table nutriment(
	food_num number not null,
	food_name varchar2(20) not null,
	food_g number not null,
	food_kcal number not null,
	food_fat number not null,
	food_col number not null,
	food_na number not null,
	food_carb number not null,
	food_df number not null,
	food_sugars number not null,
	food_protein number not null,
  constraint nutriment_pk primary key (food_num)
);
create sequence nutriment_seq;