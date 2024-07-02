import { Component, OnInit } from '@angular/core';
import {AccountClass} from "../AccountClass";
import {ActivatedRoute, Router} from "@angular/router";
import {AccountsService} from "../accounts.service";
import {Model} from "../../model.class";

@Component({
  selector: 'app-account-summary',
  templateUrl: './account-summary.component.html',
  styleUrls: ['./account-summary.component.css']
})
export class AccountSummaryComponent implements OnInit {

  id:string | null = null ;
  account:AccountClass = new AccountClass() ;
  constructor( private router:Router,
               private service:AccountsService,
               private activatedroute:ActivatedRoute,
               public userAccountsModel:Model ) {
  }
  ngOnInit(): void {
    //this.id=this.activatedroute.snapshot.paramMap.get('id');
    this.id=localStorage.getItem( "accountId") ;
    //this.id="1" ;
    this.id=this.userAccountsModel.selectAccountId.toString() ;
    this.service.getAccountById(Number(this.id)).subscribe(data=>this.account=data);
  }
}
