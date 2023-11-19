export class Product {
  /*ID 				bigint(20) PRIMARY KEY AUTO_INCREMENT NOT NULL,
  product_number  VARCHAR(128) UNIQUE KEY NOT NULL,
  description 	VARCHAR(256) NOT NULL,
  category		VARCHAR(64) NOT NULL,
  season          VARCHAR(64) ,
  brand           VARCHAR(64) ,
  color           VARCHAR(64) ,
  discount		FIXED( 5,2 ) DEFAULT 0.00 ,
  price 			FIXED( 10,2 ) NOT NULL,
  date_modified   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  date_added		TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  constraint check (category IN (select category from categories))*/

  id:number = 0 ;
  productNumber:string = "" ;
  description:string = "" ;
  category:string = "" ;
  season:string = "" ;
  brand = "" ;
  color = "" ;
  discount:number = 100.00 ; //		FIXED( 5,2 ) DEFAULT 0.00 ,
  price:number = 12345678.12 ; // 			FIXED( 10,2 ) NOT NULL,
  dateModified:string = "" ; //   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  dateAdded:string = "" ; //		TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,

  //
  // INSERT INTO product ( category, product_number, description, price, discount )
  // VALUES
  // ( 'CHECKBOOK', 'CKB_001','Pink Swirl - Box of 10 Checkbooks',   10.0,   10.0 ) ,
  // ( 'CHECKBOOK', 'CKB_002','Old World - Box of 10 Checkbooks',    10.0,   10.0 ) ,
  // ( 'CHECKBOOK', 'CKB_003','US Patriot - Box of 10 Checkbooks',   10.0,   10.0 ) ,
  // ( 'CHECKBOOK', 'CKB_004','Butterflies - Box of 10 Checkbooks',  10.0,   10.0 ) ;
}
