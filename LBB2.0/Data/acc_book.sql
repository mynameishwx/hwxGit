use hwx;
create table  acc_book(
id   int   primary key  auto_increment,
bookid  int  not null,
accid varchar(20) not null,
createtime  datetime,
time int ,
state varchar(10)
)