insert into user_details(id,birth_date,name)
values(1001,current_date(),'Tien');
insert into user_details(id,birth_date,name)
values(1002,current_date(),'Dat');
insert into user_details(id,birth_date,name)
values(1003,current_date(),'Huy');

insert into post(id,description,user_id)
values(100,'How to learn C#',1001);
insert into post(id,description,user_id)
values(101,'How to use AWS',1001);
insert into post(id,description,user_id)
values(102,'How to deloy Spring',1002);
insert into post(id,description,user_id)
values(103,'How to make apple pie',1002);