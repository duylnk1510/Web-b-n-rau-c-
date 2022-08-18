use master
go

if exists (select * from sys.databases where name=N'asmjv5')
	drop database asmjv5
go

create database asmjv5
go
use asmjv5
go

create table Accounts(
	Username nvarchar(50) primary key not null, 
	Password nvarchar(50) not null,
	Fullname nvarchar(50) not null,
	Email nvarchar(50) not null,
	Photo nvarchar(50) not null,
	Activated bit not null,
	Admin bit not null
)


create table Orders(
	Id bigint identity(1,1) primary key not null,
	Username nvarchar(50) not null,
	CreateDate datetime not null,
	Address nvarchar(100) not null,
	Paid bit not null,
	foreign key (Username) references Accounts(Username)
)

create table Categories(
	Id char(4) primary key not null,
	Name nvarchar(50) not null,
)

create table Products(
	Id int identity(1,1) primary key not null,
	Name nvarchar(50) not null,
	Image nvarchar(50) not null,
	Price float not null,
	CreateDate datetime not null,
	Available bit not null,
	CategoryId char(4) not null,
	foreign key (CategoryId) references Categories(Id)
)

create table OrderDetails(
	Id bigint identity(1,1) primary key not null,
	OrderId bigint not null,
	ProductId int not null,
	Price float not null,
	Quantity int not null,
	foreign key (OrderId) references Orders(Id),
	foreign key (ProductId) references Products(Id)
)

use asmjv5

insert into Accounts(Username, Password, Fullname, Email, Photo, Activated, Admin)
values ('admin', '123', N'Nguyễn Văn Tèo', 'teonv@gmail', 'admin.jpg', 1, 1),
	   ('user1', '1234', N'Nguyễn Thị Diu sờ', 'user1@gmail', 'user1.jpg', 1, 0),
	   ('user2', '1234', N'Nguyễn Thị Diu sờ 2', 'user2@gmail', 'user2.jpg', 1, 0)

insert into Categories (Id, Name)
values (N'1000', N'Rau'),
	   (N'1001', N'Củ'),
	   (N'1002', N'Trái Cây')

insert into Products (Name, Image, Price, CreateDate, Available, CategoryId)	
values (N'Rau muống', N'rau_muong.jpg', 6.000, CAST(N'2001-12-29' AS Date), 1, N'1000'),
	   (N'Rau ngót', N'rau_ngot.jpg', 5.000, CAST(N'2001-12-29' AS Date), 1, N'1000'),
	   (N'Rau dền', N'rau_den.jpg', 4.000, CAST(N'2001-12-29' AS Date), 1, N'1000'),
	   (N'Rau mông tơi', N'mong_toi.jpg', 10.000, CAST(N'2001-12-29' AS Date), 1, N'1000'),
	   (N'Rau diep ca', N'rau_diep_ca.jpg', 8.000, CAST(N'2021-05-11' AS Date), 1, N'1000'),
	   (N'Củ cải trắng', N'cu_cai_trang.jpg', 15.000, CAST(N'2021-04-23' AS Date), 1, N'1001'),
	   (N'Củ cà rốt', N'cu_carot.jpg', 16.000, CAST(N'2011-05-07' AS Date), 1, N'1001'),
	   (N'Củ gừng', N'cu_gung.jpg', 12.000, CAST(N'2011-05-07' AS Date), 1, N'1001'),
	   (N'Củ sắn', N'cu_san.jpg', 11.000, CAST(N'2011-05-07' AS Date), 1, N'1001'),
	   (N'Dâu', N'dau.jpg', 50.000, CAST(N'2003-03-06' AS Date), 1, N'1002'),
	   (N'Đu đủ', N'dudu.jpg', 20.000, CAST(N'2004-05-02' AS Date), 1, N'1002'),
	   (N'Mảng cầu', N'mang_cau.jpg', 22.000, CAST(N'2012-11-29' AS Date), 1, N'1002'),
	   (N'Cherry', N'cherry.jpg', 55.000, CAST(N'2014-12-29' AS Date), 1, N'1002'),
	   (N'Ổi', N'oi.jpg', 13.000, CAST(N'2014-12-29' AS Date), 1, N'1002'),
	   (N'Thơm', N'thom.jpg', 18.000, CAST(N'2014-12-29' AS Date), 1, N'1002')

insert into Orders(Username, CreateDate, Address, Paid)
values ('user1', '2022-02-02', '123 Quang Trung', 1)

insert into OrderDetails(OrderId, ProductId, Price, Quantity)
values (1, 1, 10.000, 1),
	   (1, 2, 8.000, 2)

use asmjv5

select * from Accounts where Fullname Like N'Văn%' 

select * from OrderDetails where OrderId =20
select * from Accounts
select * from Products

select OrderId,  from OrderDetails
group by OrderId
having sum(Quantity) > 5
select * from Orders
select * from OrderDetails where OrderId = 14

select Orders.* from Orders inner join Accounts on Orders.Username = Accounts.Username
where Accounts.Fullname Like N'Ngu%'

select sum(od.Price * od.Price) as 'sum' from Orders as o
inner join OrderDetails as od on od.OrderId = o.id
where year(o.CreateDate) = 2022 and month(o.CreateDate) = 5

where 

