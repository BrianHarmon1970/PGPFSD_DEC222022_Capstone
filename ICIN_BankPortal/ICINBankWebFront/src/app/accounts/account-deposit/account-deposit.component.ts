import { Component, OnInit } from '@angular/core';
import {AccountTransactionClass} from "../../transaction/AccountTransactionClass";
import {AccountClass} from "../AccountClass";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {TransactionService} from "../../transaction/transaction.service";
import {AccountsService} from "../accounts.service";
import {BankServiceService} from "../../bank-service/bank-service.service";
import {BankServiceOrder} from "../../bank-service/bank-service-order";

@Component({
  selector: 'app-account-deposit',
  templateUrl: './account-deposit.component.html',
  styleUrls: ['./account-deposit.component.css']
})
export class AccountDepositComponent implements OnInit {

  //acctTransaction:AccountTransactionClass = new AccountTransactionClass() ;
  txType:string | null = null ;
  txAmount:number | null = null ;
  account:AccountClass = new AccountClass() ;
  depositForm!:FormGroup ;
  accountId:number = 0 ;
  submitted:boolean = false ;
  constructor(private router:Router,
              private activatedRoute:ActivatedRoute,
              private builder:FormBuilder,
              private txservice:TransactionService,private acctService:AccountsService,
              private bankService:BankServiceService){ }

  ngOnInit(): void {
    this.accountId = Number(this.activatedRoute.snapshot.paramMap.get("accountid"));
    this.acctService.getAccountById(this.accountId).subscribe(x => this.account = x,
      () => console.log("Error getting account in withdraw") ,
      ()=> this.ngInitComplete())
  }
  ngInitComplete():void
  {
    let accountNumber:FormControl = new FormControl<any>( this.account.accountNumber ) ;
    let txType:FormControl = new FormControl<any>('DEPOSIT')  ;
    let txAmount:FormControl = new FormControl<any>('0.00',Validators.required)  ;
    this.depositForm = this.builder.group( { accountNumber,txType,txAmount} );

    this.txType='DEPOSIT' ;
    this.txAmount= 0.00 ;

    console.log( "this.accountId", this.accountId ) ;
    console.log( "account", this.account ) ;
  }
  get form()
  {
    return this.depositForm.controls ;
  }
  onSubmit():void
  {
    let order:BankServiceOrder = new BankServiceOrder();
    this.submitted = true ;
    if (this.depositForm.invalid)
      return;
    else
    {
      order.txAmount = this.txAmount ;
      order.accountId = this.accountId ;
      order.accountID = this.accountId ;
      console.log( "order-pre", order ) ;
      this.bankService.postDepositOrder( order ).subscribe
      (
          x=>order=x ,
          ()=>{ console.log( "Error posting order")},
          ()=>
          {
            this.router.navigate(['/account-summary/' + this.accountId]);
            console.log( "Success posting order") ;
          }
      );
      console.log( "order-post", order ) ;
    }
  }
}
