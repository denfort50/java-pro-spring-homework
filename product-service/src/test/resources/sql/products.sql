create table if not exists products
(
    id bigserial primary key,
    account_number bigint not null,
    balance double precision not null,
    product_type varchar(10) not null,
    user_id bigint references users(id)
);