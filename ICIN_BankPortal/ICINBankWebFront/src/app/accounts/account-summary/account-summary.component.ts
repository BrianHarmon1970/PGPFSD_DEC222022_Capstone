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

  id:string | null = "0" ;
  account:AccountClass = new AccountClass() ;
  constructor( private router:Router, private service:AccountsService,private activatedroute:ActivatedRoute ) {
  }
  ngOnInit(): void {
    this.id=this.activatedroute.snapshot.paramMap.get('id');
    this.service.getAccountById(Number(this.id)).subscribe(data=>this.account=data);
  }
}
