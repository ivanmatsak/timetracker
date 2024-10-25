create table account (
id bigint not null,
credentialsexpired boolean not null,
enabled boolean not null,
expired boolean not null,
locked boolean not null,
password varchar(255) not null,
username varchar(255) not null,
primary key (id));

create table account_role (
account_id bigint not null,
role_id bigint not null,
primary key (account_id, role_id));

create table project (
id integer not null,
description varchar(255),
name varchar(255),
time varchar(255),
primary key (id));

create table role (
id bigint not null,
code varchar(255),
name varchar(255),
primary key (id));

alter table if exists account drop constraint if exists UKgex1lmaqpg0ir5g1f5eftyaa1;

alter table if exists account add constraint UKgex1lmaqpg0ir5g1f5eftyaa1 unique (username);

create sequence account_seq start with 1 increment by 50;
create sequence project_seq start with 1 increment by 50;
create sequence role_seq start with 1 increment by 50;
alter table if exists account_role add constraint FKrs2s3m3039h0xt8d5yhwbuyam foreign key (role_id) references role;
alter table if exists account_role add constraint FK1f8y4iy71kb1arff79s71j0dh foreign key (account_id) references account;

