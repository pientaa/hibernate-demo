alter sequence author_id_seq restart with 1;
alter sequence contact_info_id_seq restart with 1;

insert into contact_info(address, email, phone)
values ('ul. Test 1/1 Poznań 61-737', 'jan.nowak@test.pl', '+48 123 456 789');

insert into author(first_name, last_name, contact_info_id)
values ('Jan', 'Nowak', 1);