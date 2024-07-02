import { Component, OnInit } from '@angular/core';
import {AccountTransactionClass} from "../../transaction/AccountTransactionClass";
import {ActivatedRoute, ActivatedRouteSnapshot, Router} from "@angular/router";
import {TransactionService} from "../../transaction/transaction.service";
import {Model} from "../../model.class";

@Component({
  selector: 'app-account-transactions',
  templateUrl: './account-transactions.component.html',
  styleUrls: ['./account-transactions.component.css']
})
export class AccountTransactionsComponent implements OnInit {
  //acctid: string | null = null ;
  accountTransactions:AccountTransactionClass[] = [] ;
  constructor( private router:Router,
               //private activatedRoute:ActivatedRoute,
               private service:TransactionService,
               private userAccountsModel:Model
               ) {
  }
  ngOnInit(): void
  {
    //this.service.getAllAccountTransactions().subscribe(result => this.accountTransactions = result);
    //
    // else {
      //this.acctid = this.activatedRoute.paramMap.get( "acctid" ) ;
      //const acctid = this.activatedRoute.snapshot.paramMap.get( "acctid" ) ;
      // let acid:string|null = localStorage.getItem( "accountId" ) ;
      // acid = acid == null ? "" : acid ;

      let acid:string|null = this.userAccountsModel.selectAccountId.toString() ;
      //this.account.accountNumber = this.userAccountsModel.selectAccountId.toString() ;
      //this.accountId = this.userAccountsModel.selectAccountId  ;
      this.service.getAllAccountTransactionsForAccount(
        Number(acid) ).subscribe( result=>this.accountTransactions=result) ;

    // }
  }
   DeleteTransactionById(id:number){
  //   //refresh the list once user is deleted
  //   this.accountTransactions=this.accountTransactions.filter(c=>c.id!=id);
  //   //delete user -- umm.. transaction log record
  //   this.service.deleteById(id);
  //   console.log("!!!!!Record Deleted!!!!!!");
   }
}
