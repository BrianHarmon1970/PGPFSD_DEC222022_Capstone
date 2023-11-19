import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Product} from "./product.class";
import {AccountClass} from "../accounts/AccountClass";

@Injectable({
  providedIn: 'root'
})
export class ProductService
{
  securityUrl="http://localhost:8080/api/security/"
  accountUrl:string="http://localhost:8080/api/account/";
  orderUrl:string="http://localhost:8080/api/order/"
  productUrl:string="http://localhost:8080/api/product/"
  bankUrl:string="http://localhost:8080/api/bank-service/"

  //inject the DI
  constructor(private http:HttpClient) { }

  getAllProducts():Observable<Product[]>
  {
    return this.http.get<Product[]>(this.productUrl + "") ;
  }
  //get product by id
  getProductById(id:number | null ):Observable<Product>
  {
    return this.http.get<Product>(this.productUrl+id);
  }
  //create record
  createOrder(data:Product ):Observable<any>
  {
    return this.http.post(this.productUrl,data).pipe();
  }
  //deleteById
  deleteOrderById(id:number | null )
  {
    let myid=id;
    this.http.delete(this.productUrl+myid).subscribe(data=>
    {
      return this.getAllProducts() ;
    });
  }
  //update
  updateProduct( product:Product ):Observable<any>
  {
    //return this.http.put<Product>(this.productUrl+id, product);
    return this.http.put<Product>(this.productUrl + product.id.toString(), product);
  }

  // updateProduct( product:Product,id:number | null ):Observable<any>
  // {
  //   //return this.http.put<Product>(this.productUrl+id, product);
  //   return this.http.put<Product>(this.productUrl+id, product);
  // }
}
