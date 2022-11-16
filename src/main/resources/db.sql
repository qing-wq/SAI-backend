create database if not exists sai;

create table ainfo
(
    sid       int                       not null
        primary key,
    level     int                       not null,
    direction varchar(255)              not null,
    other     varchar(255) default '无' null
);

create table language
(
    type varchar(255) not null,
    sid  int(10)      not null,
    primary key (type, sid)
);

create table stuinfo
(
    name       varchar(255) not null comment 'name',
    id         int          not null
        primary key,
    email      varchar(255) not null,
    qq         varchar(255) not null,
    management varchar(1)   not null,
    introduce  varchar(900) not null
);
