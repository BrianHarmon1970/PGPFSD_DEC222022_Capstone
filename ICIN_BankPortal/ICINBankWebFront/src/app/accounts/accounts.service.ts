import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {AccountClass} from "./AccountClass";

@Injectable({
  providedIn: 'root'
})
export class AccountsService
{
    securityUrl="http://localhost:8080/api/security/"
    accountUrl:string="http://localhost:8080/api/account/";
    bankUrl:string="http://localhost:8080/api/bank-service/"

    //inject the DI
    constructor(private http:HttpClient) { }

    //get all accounts
    getAllAccount():Observable<AccountClass[]>{
      return this.http.get<AccountClass[]>(this.accountUrl + "") ;
    }
    //get account by id
    getAccountById(id:number):Observable<AccountClass>{
      return this.http.get<AccountClass>(this.accountUrl+id);
    }
    //create record
    create(data:AccountClass ):Observable<any>{
      return this.http.post(this.accountUrl,data).pipe();
    }
    //deleteById
    deleteById(id:number){
      let myid=id;
      this.http.delete(this.accountUrl+myid).subscribe(data=>{
        return this.getAllAccount();
      });
    }
    //update account
    updateAccount( acct:AccountClass,id:number){
      return this.http.put(this.accountUrl+id, acct);
    }
}
