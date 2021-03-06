drop table if exists tbl_employee CASCADE;
drop table if exists tbl_person CASCADE;
drop table if exists tbl_position CASCADE;
drop sequence if exists seq_employee;
drop sequence if exists seq_person;
drop sequence if exists seq_position;
create sequence seq_employee start with 1 increment by 5;
create sequence seq_person start with 1 increment by 5;
create sequence seq_position start with 1 increment by 5;
create table tbl_employee (id bigint not null, salary decimal(19,2), person_id bigint, position_id integer, primary key (id));
create table tbl_person (id bigint not null, address varchar(255), cellphone varchar(255), city_name varchar(255), last_name varchar(255), name varchar(255), primary key (id));
create table tbl_position (id integer not null, name varchar(255), primary key (id));
alter table tbl_employee add constraint FKixj658kn3qxri7q9msqtix9sf foreign key (person_id) references tbl_person;
alter table tbl_employee add constraint FK4xgfuli8wvp75itbwqt0bujff foreign key (position_id) references tbl_position;