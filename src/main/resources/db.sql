# create database if not exists sai;

use sai;

drop table if exists stuinfo;
drop table if exists ainfo;
drop table if exists language;
drop table if exists user;

create table ainfo
(
    sid       varchar(255)                       not null
        primary key,
    level     int                       not null,
    direction varchar(255)              not null,
    other     varchar(255) default '无' null
);

create table language
(
    type varchar(255) not null,
    sid  varchar(10)      not null,
    primary key (type, sid)
);

create table stuinfo
(
    name       varchar(255) not null comment 'name',
    id         varchar(255)        not null
        primary key,
    email      varchar(255) not null,
    qq         varchar(255) not null,
    management varchar(1)   not null,
    introduce  varchar(900) not null
);

create table user
(
    username varchar(255) not null comment '用户名' primary key,
    password varchar(255) not null comment '密码',
    uid      int(10)      not null comment 'id'
);
insert into sai.user(username, password, uid) value ('root', '79f453511a18bc7145f9a613795e260a', 1);

