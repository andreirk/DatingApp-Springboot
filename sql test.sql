select count(distinct u.id) from users u left join photos on gender='female' and date_of_birth >= '1937-11-24 01:00:00';

select count(distinct u.id) from users u ;

select * from users u where u.gender='female';

select * from users u left join messages m on u.id=m.sender_id and m.sender_id=36;

insert into users(id, password,username)values(42, 'lolo', 'caca');

select * from users limit 4;

select * from users where id=4;

update users set name='lolaaaaa' where id=41;

delete from users wherer id=126;

create view user_view as select id, name, username, city, gender from users;

Postgres Complete Tutorial  midul jacob