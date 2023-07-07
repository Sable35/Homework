create schema if not exists products_sobolev_ma;

create  table products_sobolev_ma.products
(
    id    integer generated always as identity
    primary key,
    name  varchar(255) not null,
    price numeric      not null,
    amount integer not null
    );

create table products_sobolev_ma.clients
(
    id       integer generated always as identity primary key ,
    name     varchar(255) not null,
    username varchar(255) not null,
    password varchar(255) not null,
    email varchar(255) not null
    );

create table products_sobolev_ma.products_clients
(   amount integer not null,
    id_product integer not null
    constraint products_id_fk
    references products_sobolev_ma.products,
    id_client    integer not null
    constraint clients_id_fk
    references products_sobolev_ma.clients
);

create  table products_sobolev_ma.promocodes
(
    name varchar(255) unique not null ,
    discount_amount_percent integer not null CONSTRAINT percent_amount CHECK (discount_amount_percent >= 0 and discount_amount_percent <= 100)
);
INSERT INTO sobolev_m_a.roles(name) VALUES('ROLE_USER');
INSERT INTO sobolev_m_a.roles(name) VALUES('ROLE_ADMIN');
insert into sobolev_m_a.user_roles
values (2, 2);