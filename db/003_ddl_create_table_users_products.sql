create table if not exists main.users_products (
    id bigserial primary key,
    user_id bigint references main.users(id),
    product_id bigint references main.products(id)
);
