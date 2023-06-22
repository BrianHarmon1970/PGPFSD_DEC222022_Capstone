import { Component, OnInit } from '@angular/core';
import {AccountTransactionClass} from "../../transaction/AccountTransactionClass";
import {ActivatedRoute, ActivatedRouteSnapshot, Router} from "@angular/router";
import {TransactionService} from "../../transaction/transaction.service";

@Component({
  selector: 'app-account-transactions',
  templateUrl: './account-transactions.component.html',
  styleUrls: ['./account-transactions.component.css']
})
export class AccountTransactionsComponent implements OnInit {
  //acctid: string | null = null ;
  accountTransactions:AccountTransactionClass[] = [] ;
  constructor( private router:Router,
               private activatedRoute:ActivatedRoute,
               private service:TransactionService ) {
  }
  ngOnInit(): void {


    //this.service.getAllAccountTransactions().subscribe(result => this.accountTransactions = result);
    //
    // else {
      //this.acctid = this.activatedRoute.paramMap.get( "acctid" ) ;
      const acctid = this.activatedRoute.snapshot.paramMap.get( "acctid" ) ;
      this.service.getAllAccountTransactionsForAccount( Number(acctid) ).subscribe( result=>this.accountTransactions=result) ;

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
