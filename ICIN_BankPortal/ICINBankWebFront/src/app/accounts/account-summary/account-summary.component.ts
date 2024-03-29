import { Component, OnInit } from '@angular/core';
import {AccountClass} from "../AccountClass";
import {ActivatedRoute, Router} from "@angular/router";
import {AccountsService} from "../accounts.service";

@Component({
  selector: 'app-account-summary',
  templateUrl: './account-summary.component.html',
  styleUrls: ['./account-summary.component.css']
})
export class AccountSummaryComponent implements OnInit {

  id:string | null = null ;
  account:AccountClass = new AccountClass() ;
  constructor( private router:Router, private service:AccountsService,private activatedroute:ActivatedRoute ) {
  }
  ngOnInit(): void {
    //this.id=this.activatedroute.snapshot.paramMap.get('id');
    this.id=localStorage.getItem( "accountId") ;
    //this.id="1" ;
    this.service.getAccountById(Number(this.id)).subscribe(data=>this.account=data);
  }
}
