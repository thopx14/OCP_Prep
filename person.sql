drop table if exists person;
create table person (id integer primary key, name text, password blob);
insert into person (name) values ('Klaus');
insert into person (name) values ('Hans');
insert into person (name) values ('Maus');
insert into person (name) values ('Jerry');
insert into person (name) values ('Franz');
