import { Component, OnInit } from '@angular/core';
import {AccountClass} from "../AccountClass";
import {Router} from "@angular/router";
import {AccountsService} from "../accounts.service";

@Component({
  selector: 'app-account-list',
  templateUrl: './account-list.component.html',
  styleUrls: ['./account-list.component.css']
})
export class AccountListComponent implements OnInit {

  accounts:AccountClass[] = [] ;
  constructor( private router:Router, private service:AccountsService ) {
  }
  ngOnInit(): void {
    this.service.getAllAccount().subscribe(result=>this.accounts=result);
  }
  DeleteAccountById(id:number | null ){
    //refresh the list once user is deleted
    this.accounts=this.accounts.filter(c=>c.id!=id);
    //delete user
    this.service.deleteById(id);
    console.log("Account Deleted");
  }
}
