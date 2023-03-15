create table author
(
    id         bigserial
        primary key,
    first_name varchar(255),
    last_name  varchar(255),
    address    varchar(255),
    email      varchar(255),
    phone      varchar(255)
);

create table post
(
    id        bigserial
        primary key,
    content   varchar(255),
    title     varchar(255),
    author_id bigint
);


alter table if exists post
    add constraint fk_author_id
        foreign key (author_id)
            references author;