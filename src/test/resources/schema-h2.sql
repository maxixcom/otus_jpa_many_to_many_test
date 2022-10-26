create sequence category_seq start with 10;

create table category
(
    id   bigint default next value for category_seq not null primary key,
    name varchar not null
);

--

create sequence item_seq start with 10;

create table item
(
    id     bigint  default next value for item_seq not null primary key,
    name   varchar not null,
    weight bigint  default 0,
    price  decimal default 0.0
);

--

create sequence item_category_seq start with 10;

create table item_category
(
    id          bigint default next value for item_category_seq not null primary key,
    item_id     bigint not null,
    category_id bigint not null,
    is_main     boolean
);
