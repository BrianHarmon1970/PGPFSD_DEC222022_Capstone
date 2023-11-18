import { Component, OnInit } from '@angular/core';
import {AccountTransactionClass} from "../../transaction/AccountTransactionClass";
import {AccountClass} from "../AccountClass";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {TransactionService} from "../../transaction/transaction.service";
import {AccountsService} from "../accounts.service";
import {BankServiceService} from "../../bank-service/bank-service.service";
import {BankServiceOrder} from "../../bank-service/bank-service-order";
import {Model} from "../../model.class";

@Component({
  selector: 'app-account-transfer',
  templateUrl: './account-transfer.component.html',
  styleUrls: ['./account-transfer.component.css']
})
export class AccountTransferComponent implements OnInit
{

  masterAccount:AccountClass = new AccountClass() ;
  primaryAccount:AccountClass = new AccountClass() ;
  secondaryAccount:AccountClass = new AccountClass() ;
  userAccounts:AccountClass[] = [] ;
  userMasterAccounts:AccountClass[] = [] ;
  transferAmount:number = 0.00 ;

  //account:AccountClass = new AccountClass() ;
  transferForm!:FormGroup ;
  userId:number = 0 ;
  submitted:boolean = false ;
  accountsmodel:Model = new Model() ;
  private orderReturn:BankServiceOrder = new BankServiceOrder() ;

  constructor(private router:Router,
              private activatedRoute:ActivatedRoute,
              private builder:FormBuilder,
              private acctService:AccountsService,
              private bankService:BankServiceService){ }

  ngOnInit(): void {
    let masterAccountNumber:FormControl = new FormControl<any>( this.masterAccount.accountNumber, Validators.required ) ;
    let primaryAccountNumber:FormControl = new FormControl<any>( this.primaryAccount.accountNumber, Validators.required ) ;
    let secondaryAccountNumber:FormControl = new FormControl<any>( this.secondaryAccount.accountNumber, Validators.required ) ;
    let txAmount:FormControl = new FormControl<any>('0.00',Validators.required)  ;
    this.transferForm = this.builder.group( { masterAccountNumber, primaryAccountNumber, secondaryAccountNumber, txAmount} );

    this.accountsmodel.loadModel() ;
    this.userId = this.accountsmodel.selectedUserId ;


   // this.userId = Number(this.activatedRoute.snapshot.paramMap.get("userid"));
    this.acctService.getAllAccountForUser(this.userId).subscribe(x => this.userAccounts = x,
      () => console.log("Error getting account(s) for user - transfer") ,
      ()=> this.initMasterAccounts())
  }
  initMasterAccounts():void
  {
    this.acctService.getAllMasterAccountsForUser(this.userId).subscribe(x => this.userMasterAccounts = x,
      () => console.log("Error getting master account(s) for user - transfer") ,
      ()=> this.ngInitComplete())
  }
  ngInitComplete():void
  {
    console.log( "this.userId", this.userId ) ;
  }
  get formx()
  {
    return this.transferForm.controls ;
  }

  onSubmit():void
  {
    let order:BankServiceOrder = new BankServiceOrder();
    this.submitted = true ;
    if (this.transferForm.invalid)
      return;

    else
    {
        order.masterAccountId = this.masterAccount.id ;
        order.primaryAccountId = this.primaryAccount.id ;
        order.secondaryAccountId = this.secondaryAccount.id ;
        order.transferAmount = this.transferAmount ;
        console.log("order:", order ) ;
      console.log("master:", this.masterAccount ) ;
      console.log("primary:", this.primaryAccount ) ;
      console.log("secondary:", this.secondaryAccount ) ;
        this.bankService.postTransferOrder( order ).subscribe(
          x=>order=x ,
          ()=>{ console.log( "Error posting order")},
          ()=>{
            // this.router.navigate(['/account-summary/' + order.primaryAccountId]);
            this.router.navigate(['/user-accounts/' + this.userId ]);
            //this.router.navigate([ this.accountsmodel.baseRoute ]);

            console.log( "Success posting order") ;
          }
        ) ;
    }
    //this.router.navigate(['/account-summary/' + order.primaryAccountId]) ;
  }
}
