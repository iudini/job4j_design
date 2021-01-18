create table rule (
    id serial primary key,
    rule varchar(2000)
);
create table role (
    id serial primary key,
    role varchar(255)
);
create table users (
    id serial primary key,
    username varchar(255),
    role_id int references role(id)
);
create table role_rules (
    id serial primary key,
    role_id int references role(id),
    rule_id int references rule(id)
);
create table category (
    id serial primary key,
    name varchar(255)
);
create table state (
    id serial primary key,
    state varchar(255)
);
create table item (
    id serial primary key,
    item_name varchar(255),
    username_id int references users(id),
    category_id int references category(id),
    state_id int references state(id)
);
create table attaches (
    id serial primary key,
    file_name varchar(255),
    item_id int references item(id)
);
create table comments (
    id serial primary key,
    comment varchar(2000),
    item_id int references item(id)
);