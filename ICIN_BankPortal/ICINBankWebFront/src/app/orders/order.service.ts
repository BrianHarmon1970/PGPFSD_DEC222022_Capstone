import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {AccountClass} from "../accounts/AccountClass";
import {Order} from "./order.class";
import {Product} from "../product/product.class";
import {OrderItem} from "./order-item.class";

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  securityUrl="http://localhost:8080/api/security/"
  accountUrl:string="http://localhost:8080/api/account/";
  orderUrl:string="http://localhost:8080/api/order/"
  orderItemUrl:string="http://localhost:8080/api/order/orderitem/"
  bankUrl:string="http://localhost:8080/api/bank-service/"

  //inject the DI
  constructor(private http:HttpClient) { }

  getAllOrders():Observable<Order[]>{
    return this.http.get<Order[]>(this.orderUrl + "") ;
  }
  //get all orders for speicified user
  getAllOrdersForUser( userid:number | null ):Observable<Order[]>
  {
    return this.http.get<Order[]>(this.orderUrl + "foruser/" + userid ) ;
  }
  //get order by id
  getOrderById(id:number | null ):Observable<Order>{
    return this.http.get<Order>(this.orderUrl+id);
  }
  //create record
  createOrder(data:Order ):Observable<any>{
    return this.http.post(this.orderUrl,data).pipe();
  }
  //deleteById
  deleteOrderById(id:number | null ){
    let myid=id;
    this.http.delete(this.orderUrl+myid).subscribe(data=>{
      return this.getAllOrders() ;
    });
  }
  //update account
  updateOrder( order:Order,id:number | null ):Observable<any>{
    return this.http.put<Order>(this.orderUrl+id, order);
  }
  //-----------  order items ------------------------------


  getAllOrderItems():Observable<OrderItem[]>
  {
    return this.http.get<OrderItem[]>(this.orderItemUrl + "") ;
  }
  //get product by id
  getOrderItemById(id:number | null ):Observable<OrderItem>
  {
    return this.http.get<OrderItem>(this.orderItemUrl+id);
  }
  getOrderItemsByOrderId( id:number | null):Observable<OrderItem[]>
  {
    return this.http.get<OrderItem[]>(this.orderItemUrl+"fororder/"+id) ;
  }
  //create record
  createOrderItem(data:OrderItem ):Observable<any>
  {
    return this.http.post(this.orderItemUrl,data).pipe();
  }
  //deleteById
  deleteOrderItemById(id:number | null )
  {
    let myid=id;
    this.http.delete(this.orderItemUrl+myid).subscribe(data=>
    {
      return this.getAllOrderItems() ;
    });
  }
  //update
  updateOrderItem( orderItem:Product,id:number | null ):Observable<any>
  {
    return this.http.put<OrderItem>(this.orderItemUrl+id, orderItem );
  }

}
