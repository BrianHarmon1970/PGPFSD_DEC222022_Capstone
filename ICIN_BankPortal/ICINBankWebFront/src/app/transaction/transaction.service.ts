import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {AccountClass} from "../accounts/AccountClass";
import {AccountTransactionClass} from "./AccountTransactionClass";

@Injectable({
  providedIn: 'root'
})
export class TransactionService
{
    securityUrl="http://localhost:8080/api/security/" ;
    accountUrl:string="http://localhost:8080/api/account/";
    bankUrl:string="http://localhost:8080/api/bank-service/" ;
    transactionUrl:string="http://localhost:8080/api/transaction" ;

    //inject the DI
    constructor(private http:HttpClient) { }

    //get all accounts
    getAllAccountTransaction():Observable<AccountTransactionClass[]>{
    return this.http.get<AccountTransactionClass[]>(this.transactionUrl + "") ;
    }

    //get account by id
    getAccountTransactionById(id:number):Observable<AccountTransactionClass>{
    return this.http.get<AccountTransactionClass>(this.accountUrl+id);
    }
    //create record
    createNewAccountTransaction(data:AccountTransactionClass ):Observable<any>{
    return this.http.post(this.transactionUrl,data).pipe();
    }
    //deleteById
    deleteById(id:number){
    let myid=id;
    this.http.delete(this.accountUrl+myid).subscribe(data=>{
      return this.getAllAccountTransaction();
    });
  }
    //update user
    updateAccountTransaction( acct:AccountClass,id:number){
    return this.http.put(this.accountUrl+id, acct);
  }
}
