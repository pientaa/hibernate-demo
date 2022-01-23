drop table if exists post cascade;
drop table if exists post_comment cascade;

create table post
(
    id    bigserial not null,
    title varchar(255),
    primary key (id)
);

create table post_comment
(
    id      bigserial not null,
    review  varchar(255),
    post_id int8,
    primary key (id)
);

alter table post_comment
    add constraint FKna4y825fdc5hw8aow65ijexm0
        foreign key (post_id)
            references post;