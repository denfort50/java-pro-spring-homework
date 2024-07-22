create schema if not exists main;

create table if not exists main.products
(
    id bigserial primary key,
    account_number bigint not null,
    balance double precision not null,
    product_type varchar(10) not null
);