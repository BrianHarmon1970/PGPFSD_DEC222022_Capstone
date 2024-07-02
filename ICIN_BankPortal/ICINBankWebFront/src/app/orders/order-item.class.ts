import { Product } from "../product/product.class";

export class OrderItem
{
  // item_id      BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT ,
  // order_id	BIGINT(20) NOT NULL REFERENCES orders( ID ),
  // product_id BIGINT(20) NOT NULL REFERENCES product( ID ),
  // quantity	INTEGER NOT NULL,

  item_id:number = 0 ; //      BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT ,
  order_id:number = 0 ; //	BIGINT(20) NOT NULL REFERENCES orders( ID ),
  product_id:number = 0 ; //  BIGINT(20) NOT NULL REFERENCES product( ID ),
  quantity:number = 0 ; // 	INTEGER NOT NULL,

  product:Product = new Product() ;
}
