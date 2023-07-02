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
  //get all accounts for speicified user
  getAllAccountForUser( userid:number | null ):Observable<AccountClass[]>
  {
    return this.http.get<AccountClass[]>(this.accountUrl + "foruser/" + userid ) ;
  }
  //get all Master accounts for speicified user
  getAllMasterAccountsForUser( userid:number | null ):Observable<AccountClass[]>
  {
    return this.http.get<AccountClass[]>(this.accountUrl + "masters-for-user/" + userid ) ;
  }
    //get account by id
    getAccountById(id:number | null ):Observable<AccountClass>{
      return this.http.get<AccountClass>(this.accountUrl+id);
    }
    //create record
    create(data:AccountClass ):Observable<any>{
      return this.http.post(this.accountUrl,data).pipe();
    }
    //deleteById
    deleteById(id:number | null ){
      let myid=id;
      this.http.delete(this.accountUrl+myid).subscribe(data=>{
        return this.getAllAccount();
      });
    }
    //update account
    updateAccount( acct:AccountClass,id:number | null ):Observable<any>{
      return this.http.put<AccountClass>(this.accountUrl+id, acct);
    }
}
