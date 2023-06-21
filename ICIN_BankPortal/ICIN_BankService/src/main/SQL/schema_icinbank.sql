
use icin_bank ;
drop table if exists icin_table ;
create table icin_table 
(
	ID BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT ,
	NAME VARCHAR(40) NOT NULL
) ;
drop table if exists user_types ;
create table user_types
(
    ID			BIGINT(20) 		PRIMARY KEY NOT NULL AUTO_INCREMENT,
    user_type	VARCHAR(64) 	UNIQUE KEY NOT NULL
) ;
INSERT INTO user_types ( user_type ) VALUES ( 'ADMIN' ) ;
INSERT INTO user_types ( user_type ) VALUES ( 'CUSTOMER' ) ;
INSERT INTO user_types ( user_type ) VALUES ( 'USER' ) ;

drop table if exists users ;
create table users
(
    ID 			BIGINT(20) 		PRIMARY KEY NOT NULL AUTO_INCREMENT,
    user_name	VARCHAR(128) 	UNIQUE KEY NOT NULL,
    user_pass	VARCHAR(128)	NOT NULL DEFAULT 'DEFAULT_PASS',
    user_type	VARCHAR(24)		NOT NULL DEFAULT 'USER',
    first_name	VARCHAR(128),
    last_name	VARCHAR(128),
    email		VARCHAR(128),
    phone_number VARCHAR(64),
    CONSTRAINT CHECK ( user_type IN (select user_type from user_types))
) ;
INSERT INTO users
(user_name, user_pass, user_type)
VALUES
    ( 'Admin@ICIN_Bank.com', 'Admin', 'ADMIN') ;

INSERT INTO users ( user_name, user_pass ) VALUES ( 'Joe.user@Joes.com','JoesPassword' ) ;
INSERT INTO users ( user_name, user_pass, first_name, last_name, email, phone_number )
VALUES
    ( 'Emporer1@byz.net', 'DEFAULT_PASS','Konstantine', 'Emperor', 'Emporer1@byz.net', '+9-999-999-9999' ),
    ( 'Bugz@wb.com', 'DEFAULT_PASS', 'Bugs', 'Bunny', 'whatsup@doc.net', '1-321-244-2666' ),
    ( 'Sam@wb.com', 'DEFAULT_PASS', 'Sam', 'Yosemite', 'varmintHunter@doublebarrel.net','349-435-2631' ),
    ( 'stevej@apple.com', 'DEFAULT_PASS', 'Steve', 'Jobbs', 'thebigguy@apple.com', '669-277-5311' ),
    ( 'stevew@apple.com', 'DEFAULT_PASS', 'Steve', 'Wozniak', 'thewozman@apple.com', '408-112-7753' ),
    ( 'alex@bell.com', 'DEFAULT_PASS', 'Alexander', 'Bell', 'AGBell@att.com', '1-111-111-1111' ),
    ( 'leo@inventorsrus.org', 'DEFAULT_PASS', 'Leonardo', 'Da Vinci', 'davinci@italy.telecom.net','1-234-554-3210' ) ;


# User Account -
# Tables - account, user_accounts, account_types, account_accts
# account table - record defining each account int he system
#  account id - record id
#  account type -- value constrained by values included in account_types table
#  user id // -- options - user_accounts linking table or simply provide the user id here
#  account_number - user/bank level assigned id number
#  account_balance - update with each transaction or made available as an aggregate sum at request time
#                        optionally linked to an account_balances table that is updated at request time
#
#                        account types table - defines the allowable values for account type in the account table
#                        id - record id
#                        account_type (CHECKING or SAVINGS) - definable by unique distinct record values

use icin_bank ;
DROP TABLE IF EXISTS account_classes ;
CREATE TABLE account_classes
(
    ID BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    account_class VARCHAR(128) UNIQUE KEY NOT NULL
) ;

INSERT INTO account_classes ( account_class ) VALUES ('MASTER') ;
INSERT INTO account_classes ( account_claSS ) VALUES ('SUB') ;
INSERT INTO account_classes ( account_class ) VALUES ('BASIC') ;

drop table if exists account_types ;
create table account_types
(
    ID				BIGINT(20) 		PRIMARY KEY NOT NULL AUTO_INCREMENT,
    account_type	VARCHAR(20) 	UNIQUE KEY NOT NULL
) ;
INSERT INTO account_types ( account_type ) VALUES ( 'CHECKING' ) ;
INSERT INTO account_types ( account_type ) VALUES ( 'SAVINGS' ) ;

DROP TABLE IF EXISTS transaction ;
DROP TABLE IF EXISTS account_subs ;
DROP TABLE IF EXISTS accounts ;
create table accounts
(
    ID 				BIGINT(20)		PRIMARY KEY NOT NULL AUTO_INCREMENT,
    account_class 	VARCHAR(20) 	NOT NULL DEFAULT 'BASIC' check ( account_type in (select * from account_classes )),
    account_type 	VARCHAR(20)   	NOT NULL check ( account_type in (select * from account_types) ),
    user_id     	BIGINT(20)      NOT NULL,
    account_number	VARCHAR(128)	UNIQUE KEY NOT NULL,
    account_name	VARCHAR(128) 	NOT NULL,
    account_balance FIXED(10,2)    	NOT NULL
) ;
#
# DROP TABLE IF EXISTS user_accounts ;
# create table user_accounts
# (
#     ID  BIGINT(20)  PRIMARY KEY NOT NULL AUTO_INCREMENT,
#     user_id BIGINT(20) NOT NULL ,
#     account_id BIGINT(20) NOT NULL,
#     CONSTRAINT FOREIGN KEY(user_id) REFERENCES users( ID ),
#     CONSTRAINT FOREIGN KEY(account_id) REFERENCES accounts( ID ),
#     CONSTRAINT UNIQUE KEY UA_KEY(user_id,account_id)
# ) ;

create table account_subs
(
    ID  BIGINT(20)  PRIMARY KEY NOT NULL AUTO_INCREMENT,
    account_id BIGINT(20) NOT NULL ,
    subaccount_id BIGINT(20) NOT NULL,
    CONSTRAINT FOREIGN KEY(account_id) REFERENCES accounts( ID ),
    CONSTRAINT FOREIGN KEY(subaccount_id) REFERENCES accounts( ID ),
    CONSTRAINT UNIQUE KEY AA_KEY(account_id,subaccount_id)
) ;
# Transaction Log - log of each account transaction processed
# 	id - record id
# 	time - timestamp of the transaction
# 	status	- pending cancelled complete
# 	account_id - id of the affected account... ( with two sides in some cases )
# 	Transaction_id - ( optional, super scale multi-record transaction encompassing the two sides of this log )
# 	Transaction_type ( DEPOSIT, WITHDRAWAL, CHECK, TRANSFER( in , out )
# 	Amount
# 	Amount_type ( Debit, Credit )

drop table if exists transaction_types ;
create table transaction_types
(
    ID				BIGINT(20) 		PRIMARY KEY NOT NULL AUTO_INCREMENT,
    tx_type			VARCHAR(20) 	UNIQUE KEY NOT NULL,
    tx_amount_type 	VARCHAR(20)
) ;

INSERT INTO transaction_types ( tx_type, tx_amount_type ) VALUES ( 'DEPOSIT', 'CREDIT' ) ;
INSERT INTO transaction_types ( tx_type, tx_amount_type ) VALUES ( 'WITHDRAW', 'DEBIT' ) ;
INSERT INTO transaction_types ( tx_type, tx_amount_type ) VALUES ( 'CHECK', 'DEBIT' ) ;
INSERT INTO transaction_types ( tx_type, tx_amount_type ) VALUES ( 'TRANSFER_IN', 'CREDIT' ) ;
INSERT INTO transaction_types ( tx_type, tx_amount_type ) VALUES ( 'TRANSFER_OUT', 'DEBIT' ) ;

drop table if exists transaction_states ;
CREATE TABLE transaction_states
(
    ID 	bigint(20) PRIMARY KEY AUTO_INCREMENT NOT NULL,
    transaction_state	VARCHAR(64) NOT NULL
) ;

INSERT INTO transaction_states (transaction_state)
VALUES

    ('TRANSACTION_STATUS_RECORDCREATED'),
    ('TRANSACTION_STATUS_CANCELLED'),
    ('TRANSACTION_STATUS_TIMEDOUT'),
    ('TRANSACTION_STATUS_PENDING'),
    ('TRANSACTION_STATUS_COMPLETE') ;

CREATE TABLE transaction
(
    ID	bigint(20) 		PRIMARY KEY AUTO_INCREMENT NOT NULL,
    creation_time		TIMESTAMP 	NOT NULL DEFAULT CURRENT_TIMESTAMP,
    statechange_time 	TIMESTAMP 	NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    tx_status			VARCHAR(40)  NOT NULL DEFAULT 'TRANSACTION_STATUS_RECORDCREATED',
    account_id			BIGINT(20)  NOT NULL,
    tx_type 			VARCHAR(60) NOT NULL ,
    tx_amount          	FIXED(10,2) NOT NULL ,
    FOREIGN KEY (account_id) REFERENCES accounts( ID ) ON DELETE CASCADE ,
    constraint TX_STATUS_CHECK check (transaction.tx_status in ( select transaction_states.transaction_state from transaction_states)),
    constraint TX_TYPE_CHECK check (transaction.tx_type in (select transaction_types.tx_type from transaction_types)))
) ;

# ID, account_class, account_type, user_id, account_number, account_name, account_balance
INSERT INTO accounts (account_class, account_type, user_id, account_number, account_name, account_balance)
VALUES
    ('BASIC', 'CHECKING', '8', '12311999999121111', 'XXX1007XXNEWACCT', '333.77'),
    ('BASIC', 'CHECKING', '4', '112223311411', 'BUGZ001', '0.00'),
    ('BASIC', 'CHECKING', '4', 'asdfasdf', 'asdfasdf', '0.00'),
    ('BASIC', 'CHECKING', '2', '2322233444555222111', 'Joe\'s Checking', '0.00'),
    ('BASIC', 'CHECKING', '6', '3453453451112', 'Steve Jobbs\' great new account', '0.00'),
    ('BASIC', 'CHECKING', '6', '111222116534521', 'Steve Jobbs\' better great new account', '0.00'),
    ('BASIC', 'CHECKING', '6', '444112211122333444', 'Steve Jobbs\' even better great new account', '0.00'),
    ('BASIC', 'CHECKING', '7', '552223332211666', 'Steve W\'s account', '0.00'),
    ('BASIC', 'CHECKING', '9', '12311122233354345', 'Leonardo\'s got a new account!', '0.00'),
    ('BASIC', 'CHECKING', '208', '111221113311145678', 'user account', '0.00'),
    ('BASIC', 'CHECKING', '208', '111222333', 'user 2nd account', '0.00'),
    ('BASIC', 'CHECKING', '208', '444232232311116543', 'user account - new', '0.00') ;

# ID, creation_time, statechange_time, tx_status, account_id, tx_type, tx_amount
INSERT INTO transaction ("creation_time","statechange_time","tx_status","account_id","tx_type","tx_amount")
VALUES
    ('2023-06-20 13:49:09', '2023-06-20 13:49:09', 'TRANSACTION_STATUS_RECORDCREATED', '3', 'WITHDRAW', '5120.50'),
    ('2023-06-20 13:57:04', '2023-06-20 13:57:04', 'TRANSACTION_STATUS_RECORDCREATED', '3', 'WITHDRAW', '42199.99'),
    ('2023-06-20 14:05:41', '2023-06-20 14:05:41', 'TRANSACTION_STATUS_RECORDCREATED', '6', 'WITHDRAW', '1123.00'),
    ('2023-06-20 14:13:49', '2023-06-20 14:13:49', 'TRANSACTION_STATUS_RECORDCREATED', '3', 'WITHDRAW', '5345.00'),
    ('2023-06-20 14:16:20', '2023-06-20 14:16:20', 'TRANSACTION_STATUS_RECORDCREATED', '3', 'WITHDRAW', '2342.00'),
    ('2023-06-20 14:26:34', '2023-06-20 14:26:34', 'TRANSACTION_STATUS_RECORDCREATED', '3', 'WITHDRAW', '123.00'),
    ('2023-06-20 14:36:17', '2023-06-20 14:36:17', 'TRANSACTION_STATUS_RECORDCREATED', '3', 'WITHDRAW', '321.00'),
    ('2023-06-20 14:42:37', '2023-06-20 14:42:37', 'TRANSACTION_STATUS_RECORDCREATED', '6', 'WITHDRAW', '42300.00'),
    ('2023-06-20 15:40:50', '2023-06-20 15:40:50', 'TRANSACTION_STATUS_RECORDCREATED', '8', 'WITHDRAW', '129999.00'),
    ('2023-06-20 16:19:02', '2023-06-20 16:19:02', 'TRANSACTION_STATUS_RECORDCREATED', '3', '', '123.12'),
    ('2023-06-20 16:28:10', '2023-06-20 16:28:10', 'TRANSACTION_STATUS_RECORDCREATED', '9', 'WITHDRAW', '120000.00'),
    ('2023-06-20 16:45:00', '2023-06-20 16:45:00', 'TRANSACTION_STATUS_RECORDCREATED', '7', 'DEPOSIT', '123000.00'),
    ('2023-06-20 17:02:46', '2023-06-20 17:02:46', 'TRANSACTION_STATUS_RECORDCREATED', '5', 'DEPOSIT', '123.00'),
    ('2023-06-20 17:52:56', '2023-06-20 17:52:56', 'TRANSACTION_STATUS_RECORDCREATED', '12', 'DEPOSIT', '43000.00') ;




DROP TABLE IF EXISTS security_roles ;
create table security_roles
(
    ID               BIGINT(20) PRIMARY KEY  NOT NULL AUTO_INCREMENT,
    role_name        VARCHAR(128) UNIQUE KEY NOT NULL,
    role_description VARCHAR(128)            NOT NULL DEFAULT 'NONE'
) ;

INSERT INTO security_roles (role_name,role_description)
    VALUES( 'ADMIN', 'General administrative authorities') ;

INSERT INTO security_roles (role_name,role_description)
    VALUES( 'USER', 'General user authorities') ;

drop table if exists security_user_roles ;
create table security_user_roles
(
    ID  BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    role_id BIGINT(20) NOT NULL,
    user_id BIGINT(20) NOT NULL
) ;

drop table if exists security_authorities ;

create table security_authorities
(
    ID			    BIGINT(20) 		PRIMARY KEY NOT NULL AUTO_INCREMENT,
    authority_name	VARCHAR(64) 	UNIQUE KEY NOT NULL,
    authority_description VARCHAR(128) DEFAULT 'NONE'
) ;
INSERT INTO security_authorities ( authority_name, authority_description )
    VALUES ( 'ADMIN', 'General administrative privileges' ),
           ( 'USER', 'General user privileges' ) ;

drop table if exists security_role_authorities ;
create table security_role_authorities
(
    ID 			BIGINT(20) 		PRIMARY KEY NOT NULL AUTO_INCREMENT,
    role_id     BIGINT(20)      NOT NULL,
    authority_id BIGINT         NOT NULL,
        CONSTRAINT CHECK ( security_role_authorities.role_id IN (select security_roles.ID from security_roles )
        CONSTRAINT CHECK ( authority_id IN (select ID from security_authorities )))
) ;
INSERT INTO security_role_authorities ( role_id, authority_id )
VALUES
(
    ((select ID from security_roles where security_roles.role_name = 'ADMIN'),
        (select ID from security_authorities where security_authorities.authority_name = 'ADMIN' )),
    ((select ID from security_roles where security_roles.role_name = 'USER'),
        (select ID from security_authorities where security_authorities.authority_name = 'USER' ))
) ;