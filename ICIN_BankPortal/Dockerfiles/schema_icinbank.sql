use icin_bank
drop table if exists icin_table ;
create table icin_table 
(
	ID BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT ,
	NAME VARCHAR(40) NOT NULL
) ;
