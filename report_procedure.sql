delimiter $$
drop procedure if exists report $$ 
create procedure report(in startdate date,in enddate date,in departname varchar(10))
begin
	declare _date date;
    declare done int default -1;
	declare date_cursor cursor for select adddate(startdate, numlist.id) as 'date' from (SELECT n1.i + n10.i*10 + n100.i*100 AS id FROM num n1 cross join num as n10 cross join num as n100) as numlist where adddate(startdate, numlist.id) <= enddate;
    declare continue handler for not found set done = 1;
	drop table if exists t_report;
    create temporary table t_report(reportid int primary key auto_increment,departname varchar(20),`date` date,income double,expense double);
    open date_cursor;
    cursor_loop:loop
		 fetch date_cursor into _date;
        if done = 1 then
        leave cursor_loop;
        end if;
		insert into t_report(departname,`date`,income)
        select departname,businessdate `date`,dailyincome income from department natural join income
        where department.departname = departname and businessdate = _date;
		if(!exists(select incomeid from income where businessdate=_date) and exists(select billid from bill where billdate = _date))
        then  
        insert into t_report(departname,`date`,expense)
        select departname,billdate `date`, sum(amount) expense from department,bill,billdetails
        where department.departid = billdetails.departid and bill.billid = billdetails.billid and ddepartment.epartname = departname
        and billdate = _date;
        else
       drop table if exists t_reportid;
        create temporary table t_reportid as select reportid from t_report where t_report.departname=departname and t_report.`date`=_date;
        update t_report,(select sum(amount) expense from department,bill,billdetails where department.departid = billdetails.departid 
        and bill.billid = billdetails.billid and department.departname = departname 
        and billdate = _date) s1
        set t_report.expense=s1.expense
        where t_report.reportid in (select reportid from t_reportid s2);
		end if;
    end loop cursor_loop;                  
	close date_cursor; 
end$$
delimiter ;
call report('2017-02-03','2017-02-09','meat');
select * from t_report;
select * from t_reportid;