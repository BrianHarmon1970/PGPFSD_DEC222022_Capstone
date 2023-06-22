import { Component, OnInit } from '@angular/core';
import {AccountClass} from "../../accounts/AccountClass";
import {ActivatedRouteSnapshot, Router} from "@angular/router";
import {AccountsService} from "../../accounts/accounts.service";
import {AccountTransactionClass} from "../AccountTransactionClass";
import {TransactionService} from "../transaction.service";

@Component({
  selector: 'app-transaction-list',
  templateUrl: './transaction-list.component.html',
  styleUrls: ['./transaction-list.component.css']
})
export class TransactionListComponent implements OnInit
{
  //acctid: string | null = null ;
  accountTransactions:AccountTransactionClass[] = [] ;
  constructor( private router:Router,
               //private activatedRoute:ActivatedRouteSnapshot,
               private service:TransactionService ) {
  }
  ngOnInit(): void {


      this.service.getAllAccountTransactions().subscribe(result => this.accountTransactions = result);
    //
    // else {
    //   this.acctid = this.activatedRoute.paramMap.get( "acctid" ) ;
    //   this.service.getAllAccountTransactionsForAccount( Number(this.acctid) ).subscribe( result=>this.accountTransactions=result) ;
    //
    // }

  }
  DeleteTransactionById(id:number){
    //refresh the list once user is deleted
    this.accountTransactions=this.accountTransactions.filter(c=>c.id!=id);
    //delete user -- umm.. transaction log record
    this.service.deleteById(id);
    console.log("!!!!!Record Deleted!!!!!!");
  }
}
