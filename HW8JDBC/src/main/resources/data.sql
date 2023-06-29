create schema if not exists products_sobolev_ma;
create schema if not exists products_sobolev_ma;

create  table products_sobolev_ma.products
(
    id    integer generated always as identity
    primary key,
    name  varchar(255) not null,
    price numeric      not null
    );

create table products_sobolev_ma.clients
(
    id       integer generated always as identity primary key ,
    name     varchar(255) not null,
    username varchar(255) not null,
    password varchar(255) not null,
    email varchar(255) not null,
    promocode varchar(255)
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
