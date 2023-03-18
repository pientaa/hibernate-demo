alter sequence author_id_seq restart with 1;
alter sequence contact_info_id_seq restart with 1;

insert into contact_info(address, email, phone)
values ('Test 1/1 12-345 Poznań', 'test@test.pl', '+48 512 345 678');

insert into author(first_name, last_name, contact_info_id)
values ('Jan', 'Nowak', 1);