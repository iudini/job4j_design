create database town;
create table buildings(
    id serial primary key,
    name varchar(255),
    street text,
    number integer
);
insert into buildings(name, street, number) VALUES ('Kazan', 'Lenina', 12);
update buildings set street = 'Valeeva';
delete from buildings;