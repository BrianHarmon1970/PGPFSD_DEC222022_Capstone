import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {AccountClass} from "../accounts/AccountClass";
import {BankServiceOrder} from "./bank-service-order";

@Injectable({
  providedIn: 'root'
})

export class BankServiceService
{
  securityUrl="http://localhost:8080/api/security/"
  accountUrl:string="http://localhost:8080/api/account/";
  bankUrl:string="http://localhost:8080/api/bank-service/"

  //inject the DI
  constructor(private http:HttpClient) { }
  //
  // //get all accounts
  // getAllAccount():Observable<AccountClass[]>{
  //   return this.http.get<AccountClass[]>(this.accountUrl + "") ;
  // }
  // //get account by id
  // getAccountById(id:number):Observable<AccountClass>{
  //   return this.http.get<AccountClass>(this.accountUrl+id);
  // }
   //create record
   putOrder(data:BankServiceOrder ):Observable<any>{
     return this.http.post(this.bankUrl+"account-withdraw",data).pipe();
   }
  // //deleteById
  // deleteById(id:number){
  //   let myid=id;
  //   this.http.delete(this.accountUrl+myid).subscribe(data=>{
  //     return this.getAllAccount();
  //   });
  // }
  //update account
  postAccountCreateOrder( order:BankServiceOrder ):Observable<any>{
    let url = this.bankUrl+"account-create" ;
    // console.log( "post order", order ) ;
    // console.log( "url", url ) ;
    return this.postOrder( url, order ) ;
    //return this.http.post( url, order);
    //ttp://localhost:8080/api/bank-service/account-withdraw'/
  }
  postWithdrawOrder( order:BankServiceOrder ):Observable<any>{
    let url = this.bankUrl+"account-withdraw" ;
    // console.log( "post order", order ) ;
    // console.log( "url", url ) ;
    return this.postOrder( url, order ) ;
    //return this.http.post( url, order);
    //ttp://localhost:8080/api/bank-service/account-withdraw'/
  }
  postDepositOrder( order:BankServiceOrder ):Observable<any>{
    let url = this.bankUrl+"account-deposit" ;
    // console.log( "post order", order ) ;
    // console.log( "url", url ) ;
    return this.postOrder( url, order ) ;
    //return this.http.post( url, order);
    //ttp://localhost:8080/api/bank-service/account-withdraw'/
  }
  postOrder( url:string, order:BankServiceOrder ):Observable<any>{
    console.log( "post order", order ) ;
    //let url = this.bankUrl+"account-withdraw" ;
    console.log( "url", url ) ;
    return this.http.post( url, order);
    //ttp://localhost:8080/api/bank-service/account-withdraw'/
  }
}
