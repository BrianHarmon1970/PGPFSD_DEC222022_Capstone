import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {TransactionService} from "../transaction.service";
import {AccountTransactionClass} from "../AccountTransactionClass";

@Component({
  selector: 'app-transaction-detail',
  templateUrl: './transaction-detail.component.html',
  styleUrls: ['./transaction-detail.component.css']
})
export class TransactionDetailComponent implements OnInit {

  constructor(private router:Router,private activatedRoute:ActivatedRoute,private service:TransactionService) { }

  accountTransaction:AccountTransactionClass = new AccountTransactionClass() ;
  ngOnInit(): void {
    const id=this.activatedRoute.snapshot.paramMap.get('txid');
    console.log("id:",id);
    this.service.getAccountTransactionById(Number(id)).subscribe(data=>this.accountTransaction=data);
  }

}
