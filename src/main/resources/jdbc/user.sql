drop table if exists user;
create table user(id integer primary key autoincrement,
                  prename text,
                  surname text,
                  password blob,
                  dayOfBirth text,
                  streetName text,
                  houseNumber integer,
                  postalCode integer,
                  city text)