create table product (
    id serial primary key,
    name varchar(255),
    type_id int references type(id),
    expired_date date,
    price float
);
create table type (
    id serial primary key,
    name varchar(255)
);

insert into type(name) values ('СЫР'), ('МОЛОКО');
insert into product(name, type_id, expired_date, price) VALUES ('гауда', 1, '2021-02-22', 100);
insert into product(name, type_id, expired_date, price) VALUES ('хохланд', 1, '2021-02-23', 200);
insert into product(name, type_id, expired_date, price) VALUES ('дружба', 1, '2021-01-22', 150);
insert into product(name, type_id, expired_date, price) VALUES ('зеленое мороженное', 2, '2021-01-23', 50);

select * from product p
    join type t on t.id = p.type_id
where t.name = 'СЫР';

select * from product where name like '%мороженное%';

select * from product where expired_date >= date_trunc('month', current_date + interval '1 month')
                        and expired_date < date_trunc('month', current_date + interval '2 month');

select * from product order by price desc limit 1;

select t.name, count(type_id) from type t
    join product p on t.id = p.type_id
group by t.name;

select * from product p
    join type t on t.id =p.type_id
where t.name in ('СЫР', 'МОЛОКО');

select t.name, count(type_id) from type t
    join product p on t.id = p.type_id
group by t.name
having count(type_id) < 10;

select t.name, p.name from product p
    join type t on t.id = p.type_id;

