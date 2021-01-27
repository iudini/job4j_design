create table brand (
    id serial primary key,
    name varchar(255),
    country varchar(255)
);
create table model (
    id serial primary key,
    name varchar(255),
    fuel varchar(255),
    brand int references brand(id)
);
insert into brand(name, country) values ('bmw', 'Germany');
insert into brand(name, country) values ('audi', 'Germany');
insert into brand(name, country) values ('lada', 'Russia');
insert into model(name, brand, fuel) values ('5-series', 1, 'gasoline');
insert into model(name, brand, fuel) values ('6-series', 1, 'diesel');
insert into model(name, brand, fuel) values ('3-series', 1, 'gasoline');
insert into model(name, brand, fuel) values ('a3', 2, 'diesel');
insert into model(name, brand, fuel) values ('a4', 2, 'diesel');
insert into model(name, brand, fuel) values ('a1', 2, 'diesel');
insert into model(name, brand, fuel) values ('priora', 3, 'gasoline');
insert into model(name, brand, fuel) values ('vesta', 3, 'gasoline');
insert into model(name, brand, fuel) values ('granta', 3, 'gasoline');
select b.name as Марка, m.name as Модель from model as m inner join brand b on b.id = m.brand;
select b.country as Страна, b.name as Марка, m.name as Модель, m.fuel as Топливо from brand as b inner join model m on b.id = m.brand;
select b.name as Марка, m.name as Модель from model as m inner join brand b on b.id = m.brand where fuel = 'diesel';