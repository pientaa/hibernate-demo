drop table if exists post cascade;
drop table if exists post_comment cascade;

create table post
(
    id      bigserial
        primary key,
    content varchar(255),
    title   varchar(255)
);

create table post_comment
(
    id      bigserial
        primary key,
    content varchar(255),
    post_id bigint
        constraint fkna4y825fdc5hw8aow65ijexm0
            references post
);