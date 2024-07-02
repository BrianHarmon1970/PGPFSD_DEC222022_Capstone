import { Component, OnInit } from '@angular/core';
import {AccountTransactionClass} from "../../transaction/AccountTransactionClass";
import {AccountClass} from "../AccountClass";
import {TransactionService} from "../../transaction/transaction.service";
import {AccountsService} from "../accounts.service";
import {ActivatedRoute, Router} from "@angular/router";
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {BankServiceService} from "../../bank-service/bank-service.service";
import {BankServiceOrder} from "../../bank-service/bank-service-order";
import {Model} from "../../model.class";

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
//                private txservice:TransactionService,
                private acctService:AccountsService,
                private bankService:BankServiceService,
                public userAccountsModel:Model
                ){ }

    ngOnInit(): void
    {
      this.account.accountNumber = this.userAccountsModel.selectAccountId.toString() ;
      this.accountId = this.userAccountsModel.selectAccountId  ;
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
    // getBaseRoute( defaultRoute:string ) : string
    // {
    //   let baseRoute = this.userAccountsModel.baseRoute ;
    //   baseRoute = baseRoute == null ? defaultRoute : baseRoute ;
    //   return baseRoute ;
    // }
  navigate( route:string ) : void
  {
    this.router.navigate( [ route ] ) ;
    window.location.reload() ;
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
        console.log( "user-id", this.userAccountsModel.user.id ) ;

        this.bankService.postWithdrawOrder( order ).subscribe(
          x=>order=x ,
          ()=>{ console.log( "Error posting order")},
          ()=>
          {
             // this.navigate (this.getBaseRoute("main/user-accounts/" +
             //  this.userAccountsModel.user.id)) ;
            //this.router.navigate([this.getBaseRoute("main/user-accounts/" + this.accountId)]);
            //this.navigate( this.userAccountsModel.baseRoute ) ;

            this.userAccountsModel.selectedView = this.userAccountsModel.baseView ;
            console.log( "Success posting order") ;
          });

        console.log( "order-post", order ) ;
      }
    }
}
