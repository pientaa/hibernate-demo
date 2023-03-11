drop table if exists post cascade;
create table post
(
    id      bigserial
        primary key,
    content varchar(255),
    title   varchar(255)
);