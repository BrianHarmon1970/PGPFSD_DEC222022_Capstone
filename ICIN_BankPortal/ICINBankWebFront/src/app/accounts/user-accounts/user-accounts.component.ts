import { Component, OnInit } from '@angular/core';
import {AccountClass} from "../AccountClass";
import {ActivatedRoute, Router} from "@angular/router";
import {AccountsService} from "../accounts.service";

@Component({
  selector: 'app-user-accounts',
  templateUrl: './user-accounts.component.html',
  styleUrls: ['./user-accounts.component.css']
})
export class UserAccountsComponent implements OnInit {
  accounts:AccountClass[] = [] ;
  constructor(
    private router:Router,
    private activatedRoute:ActivatedRoute,
    private service:AccountsService ) {  }
  ngOnInit(): void
  {
    const userid = this.activatedRoute.snapshot.paramMap.get("userid") ;

    this.service.getAllAccountForUser(Number(userid)).subscribe(result=>this.accounts=result);
  }
  DeleteAccountById(id:number | null ){
    //refresh the list once user is deleted
    this.accounts=this.accounts.filter(c=>c.id!=id);
    //delete user
    this.service.deleteById(id);
    console.log("Account Deleted");
  }
}
