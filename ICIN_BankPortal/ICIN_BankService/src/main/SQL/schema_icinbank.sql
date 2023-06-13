
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