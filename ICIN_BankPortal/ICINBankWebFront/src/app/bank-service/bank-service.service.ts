import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {AccountClass} from "../accounts/AccountClass";
import {BankServiceOrder} from "./bank-service-order";
import {AccountClasstype} from "./account-classtype.AccountClassType";
import {AccountCapacity} from "./account-capacity";

@Injectable({
  providedIn: 'root'
})

export class BankServiceService
{
  securityUrl="http://localhost:8080/api/security/"
  accountUrl:string="http://localhost:8080/api/account/";
  bankUrl:string="http://localhost:8080/api/bank-service/"
  bankSystemConfigUrl:string = this.bankUrl + "system-configuration/"

  //inject the DI
  constructor(private http:HttpClient) { }

  // get the configured account types
  getAllAccountClassTypeDefs():Observable<AccountClasstype[]>{
    return this.http.get<AccountClasstype[]>(this.bankSystemConfigUrl + "account-classtypes") ;
  }
  // get capacity for AccountClassType
  getCapacityForClasstype( classtypeId:number ):Observable<any>
  {
    return this.http.get( this.bankSystemConfigUrl + "account-capacity/classtype/" + classtypeId )
  }
  // getCapacityByTagname( tagname:string ):Observale<any>
  // {
  //   return this.http.get( this.bankSystemConfigUrl + "account-capacity/classtype/" + classtypeId )
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
