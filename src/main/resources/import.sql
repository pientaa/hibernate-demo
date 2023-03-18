alter sequence author_id_seq restart with 1;

insert into author(first_name, last_name)
values ('Jan', 'Nowak');