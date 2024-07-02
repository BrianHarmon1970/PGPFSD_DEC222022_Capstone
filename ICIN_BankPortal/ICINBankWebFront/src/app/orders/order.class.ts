import { OrderItem } from "./order-item.class";

export class Order
{
  // ID			BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  // user_id		BIGINT(20) NOT NULL REFERENCES users(ID),
  // account_id  BIGINT(20) NOT NULL REFERENCES accounts(ID),
  // order_date	TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  // order_status VARCHAR(64) DEFAULT 'ORDERSTATUS_CREATED',

  id:number = 0 ;       //			BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  userId:number = 0 ;   // user_id		BIGINT(20) NOT NULL REFERENCES users(ID),
  accountId:number = 0 ;  // account_id  BIGINT(20) NOT NULL REFERENCES accounts(ID),
  orderDate:string = "" ; //order_date	TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  orderStatus:string = "" // order_status VARCHAR(64) DEFAULT 'ORDERSTATUS_CREATED',

  OrderItem:OrderItem[] = [] ;
}
