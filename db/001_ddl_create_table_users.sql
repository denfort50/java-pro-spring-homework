create schema if not exists main;

create table if not exists main.users
(
    id bigserial primary key,
    username varchar(255) unique
);
