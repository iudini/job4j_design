create table devices(
    id serial primary key,
    name varchar(255),
    price float
);
create table people(
    id serial primary key,
    name varchar(255)
);
create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);
insert into people(name) values ('Petr'), ('Ivan'), ('Anton');
insert into devices(name, price) values ('iphone', 77000), ('ipad', 45000), ('xiaomi', 12000), ('sony', 14000), ('nokia', 8800);
insert into devices_people(device_id, people_id) values (1, 1), (2, 1), (3, 2), (4, 3), (5, 3);

select avg(price) from  devices;
select p.name, avg(d.price) from people as p
                                     join devices_people dp on p.id = dp.people_id
                                     join devices d on d.id = dp.device_id
group by p.name
having avg(price)>11500;