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
    transactionUrl:string="http://localhost:8080/api/transaction/" ;

    //inject the DI
    constructor(private http:HttpClient) { }

    //get all accounts
    getAllAccountTransactions():Observable<AccountTransactionClass[]>{
    return this.http.get<AccountTransactionClass[]>(this.transactionUrl + "") ;
    }

    //get all transactions for given account ( by acct id )


    getAllAccountTransactionsForAccount( acctid:number ):Observable<AccountTransactionClass[]>
    {
        return this.http.get<AccountTransactionClass[]>(this.transactionUrl + "foraccount/" + acctid) ;
    }

    //get account by id
    getAccountTransactionById(id:number):Observable<AccountTransactionClass>{
    return this.http.get<AccountTransactionClass>(this.transactionUrl+id);
    }
    //create record
    createNewAccountTransaction(data:AccountTransactionClass ):Observable<any>{
    return this.http.post(this.transactionUrl,data).pipe();
    }
    //deleteById
    deleteById(id:number){
    let myid=id;
    this.http.delete(this.transactionUrl+myid).subscribe(data=>{
      return this.getAllAccountTransactions();
    });
  }
    //update user
    updateAccountTransaction( tx:AccountTransactionClass,id:number){
    return this.http.put(this.transactionUrl+id, tx);
  }
}
