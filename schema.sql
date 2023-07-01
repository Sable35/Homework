create schema if not exists products_sobolev_ma;
create table products_sobolev_ma.clients (id bigserial not null, email varchar(255) not null, login varchar(255) not null, name varchar(255) not null, password varchar(255) not null, primary key (id));
create table products_sobolev_ma.products (amount integer not null, price numeric(38,2) not null, id bigserial not null, name varchar(255) not null, primary key (id));
create table products_sobolev_ma.products_clients (amount integer not null, client_id bigint not null, id bigserial not null, product_id bigint not null, primary key (id));
create table products_sobolev_ma.promocodes (discount_amount_percent integer not null, id bigserial not null, name varchar(255) not null, primary key (id));
alter table if exists products_sobolev_ma.products_clients add constraint FKao1nbcy2hlupuq5lxvkcwte5 foreign key (client_id) references products_sobolev_ma.clients;
alter table if exists products_sobolev_ma.products_clients add constraint FKrcw63930lb3ehsoj2y3el25q1 foreign key (product_id) references products_sobolev_ma.products;

