create table departments (
    id serial primary key,
    name varchar(255)
);
create table employees (
    id serial primary key,
    name varchar(255),
    department_id int references departments(id)
);
insert into departments(name) values ('pto'), ('mts'), ('electric');
insert into employees(name, department_id) values ('Petr', 1), ('Ivan', 1), ('Gleb', 1), ('Maria', 2), ('Anna', 2), ('Vadim', 3);
insert into employees(name) values ('Alex'), ('Igor');

--2. Выполнить запросы с left, right, full, cross соединениями
select * from employees e left join departments d on d.id = e.department_id;
select * from employees e right join departments d on d.id = e.department_id;
select * from employees e full join departments d on d.id = e.department_id;
select * from employees e cross join departments d;

--3. Используя left join найти работников, которые не относятся ни к одну из департаментов
select * from employees e left join departments d on d.id = e.department_id where department_id is null;

--4. Используя left и right join написать запросы, которые давали бы одинаковый результат.
select * from employees e left join departments d on d.id = e.department_id;
select * from departments d right join employees e on d.id = e.department_id;

--5. Создать таблицу teens с атрибутами name, gender и заполнить ее. Используя cross join составить все возможные разнополые пары
create table teens (
    name varchar(255),
    gender varchar(255)
);
insert into teens(name, gender) values ('Nick', 'Male'), ('Olga', 'Female'), ('Bob', 'Male'), ('Daria', 'Female');
select t1.name as Male, t2.name as Female from teens t1 cross join teens t2 where t1.gender = 'Male' and t2.gender = 'Female';
