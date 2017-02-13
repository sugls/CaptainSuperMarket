create table bill (
	billid int primary key auto_increment,
    billdate date not null,
    billduedate date,
    vendorid int,
    paidflag boolean,
    foreign key(vendorid) references vendor(vendorid)
);
create table billdetails (
	billid int,
    departid int,
    amount double,
    foreign key(billid) references bill(billid),
    foreign key(departid) references department(departid),
    primary key(billid,departid)
);