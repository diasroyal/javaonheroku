use myDB;
drop table if exists bookDB;

create table bookDB (
  bookName varchar(50) not null,
  author varchar(50) not null,
  isbn varchar(100), 
  price varchar(20),
  description text,  
  

  primary key (isbn)
);
