import { Component, OnInit } from '@angular/core';
import {AccountTransactionClass} from "../../transaction/AccountTransactionClass";
import {AccountClass} from "../AccountClass";
import {TransactionService} from "../../transaction/transaction.service";
import {AccountsService} from "../accounts.service";
import {ActivatedRoute, Router} from "@angular/router";
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {BankServiceService} from "../../bank-service/bank-service.service";
import {BankServiceOrder} from "../../bank-service/bank-service-order";

@Component({
  selector: 'app-account-withdraw',
  templateUrl: './account-withdraw.component.html',
  styleUrls: ['./account-withdraw.component.css']
})
export class AccountWithdrawComponent implements OnInit
{
    txAmount: number | null = null ;
    txType: string | null = null ;
    account:AccountClass = new AccountClass() ;
    withdrawForm!:FormGroup ;
    accountId:number = 0 ;
    submitted:boolean = false ;
    constructor(private router:Router,
                private activatedRoute:ActivatedRoute,
                private builder:FormBuilder,
                private txservice:TransactionService,private acctService:AccountsService,
                private bankService:BankServiceService ) { }

    ngOnInit(): void {
      //this.accountId = Number(this.activatedRoute.snapshot.paramMap.get("accountid"));
      this.accountId = Number(localStorage.getItem("accountId" )) ;
      this.acctService.getAccountById(this.accountId).subscribe(x => this.account = x,
        () => console.log("Error getting account in withdraw") ,
        ()=> this.ngInitComplete())
    }
    ngInitComplete():void
    {
      this.txType='WITHDRAW' ;

      let accountNumber:FormControl = new FormControl( this.account.accountNumber ) ;
      let txType:FormControl = new FormControl('WITHDRAW')  ;
      let txAmount:FormControl = new FormControl('0.00')  ;
      this.withdrawForm = new FormGroup({ accountNumber,txType,txAmount})

      console.log( "this.accountId", this.accountId ) ;
      console.log( "account", this.account ) ;
    }
    get form()
    {
      return this.withdrawForm.controls ;
    }
    getBaseRoute( defaultRoute:string ) : string
    {
      let baseRoute = localStorage.getItem( "baseRoute" ) ;
      baseRoute = baseRoute == null ? defaultRoute : baseRoute ;
      return baseRoute ;
    }
    onSubmit():void
    {
      let order:BankServiceOrder = new BankServiceOrder();
      this.submitted = true ;
      if (this.withdrawForm.invalid)
        return;
      else
      {
        console.log( "this.accountId", this.accountId ) ;
        console.log( "account", this.account ) ;

        order.accountId = this.accountId ;
        order.txAmount = this.txAmount ;
        console.log( "order-pre", order ) ;
        this.bankService.postWithdrawOrder( order ).subscribe(
          x=>order=x ,
          ()=>{ console.log( "Error posting order")},
          ()=>
          {
            this.router.navigate([this.getBaseRoute("/account-summary/" + this.accountId)]);
            console.log( "Success posting order") ;
          });
        console.log( "order-post", order ) ;
      }
    }
}
