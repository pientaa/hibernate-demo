alter sequence author_id_seq restart with 1;

insert into author(first_name, last_name, address, email, phone)
values ('Jan', 'Nowak', 'ul. Test 1/1 Poznań 61-737', 'jan.nowak@test.pl', '+48 123 456 789');