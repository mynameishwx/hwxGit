use hwx;
create table book(

bookId  int   primary key  auto_increment,

bookName varchar(20)  NOT NULL,

bookCreate varchar(20) NOT NULL,

bookclass varchar(20) NOT NULL,

booktext  MEDIUMTEXT,

create_time datetime,

update_time datetime );