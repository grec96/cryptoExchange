--liquibase formatted sql

--changeset Valery Zelenetski:init DB
----------------------------------------------------------------------------------
create schema if not exists currency;

create sequence sq_coin start with 1;

create table coin
(
    id     bigint not null,
    symbol varchar(255),
    primary key (id)
);

create sequence sq_coin_price start with 1;
create table coin_price
(
    id      bigint not null,
    date    timestamp,
    price   double,
    coin_id bigint,
    primary key (id)
);

create sequence sq_notify start with 1;
create table notify
(
    id           bigint not null,
    price_notify double,
    user_name    varchar(255),
    coin_id      bigint,
    primary key (id)
);

alter table coin_price
    add constraint FK_coinprice_on_coin foreign key (coin_id) references coin (id);

alter table notify
    add constraint FK_notify_on_coin foreign key (coin_id) references coin (id);

insert into coin
values (80, 'ETH');
insert into coin
values (90, 'BTC');
insert into coin
values (48543, 'SOL');