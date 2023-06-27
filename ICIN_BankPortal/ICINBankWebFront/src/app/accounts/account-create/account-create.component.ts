import { Component, OnInit } from '@angular/core';
import {AccountClass} from "../AccountClass";
import {ActivatedRoute, Router} from "@angular/router";
import {DataService} from "../../users/data.service";
import {AccountsService} from "../accounts.service";
import {UserClass} from "../../users/UserClass";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {BankServiceService} from "../../bank-service/bank-service.service";
import {BankServiceOrder} from "../../bank-service/bank-service-order";
import {AccountClasstype} from "../../bank-service/account-classtype.AccountClassType";

@Component({
  selector: 'app-account-create',
  templateUrl: './account-create.component.html',
  styleUrls: ['./account-create.component.css']
})
export class AccountCreateComponent implements OnInit
{
  newAccount:AccountClass = new AccountClass() ;
  accountUser:UserClass = new UserClass() ;
  accountClasstypes:AccountClasstype[] = []  ;
  selectedClasstype:AccountClasstype = new AccountClasstype() ;
  createForm!:FormGroup ;
  userid:string | null = null  ;
  submitted:boolean = false ;
  constructor( private router:Router,
               private activatedRoute:ActivatedRoute,
               //private service:AccountsService,
               private userService:DataService,
               private bankService:BankServiceService,
               private builder:FormBuilder ) { }


  ngOnInit(): void
  {
    this.bankService.getAllAccountClassTypeDefs().subscribe(
      x=>x=this.accountClasstypes=x,
      null,
      ()=>{ this.selectedClasstype = this.accountClasstypes[0] ;} ) ;

    this.userid=this.activatedRoute.snapshot.paramMap.get('userid');
    //console.log("userid:",this.userid);
    this.newAccount.userId = Number(this.userid) ;
    this.newAccount.accountType="CHECKING" ;
    this.newAccount.accountClass="BASIC" ;
    this.newAccount.accountBalance = 0.0 ;
    this.userService.getUserById(Number(this.userid)).subscribe(data=>this.accountUser=data);

    //this.form = this.builder.group()
    //let validators:Validator[] = [] ;
    //let asyncValidators:AsyncValidator[] = [] ;


    // get the defined account types



    this.createForm = this.builder.group({
      accountName:['',Validators.required ], // ,Validators.email],
      accountNumber:['',Validators.required],
      selectedClasstype:[''],
      accountClasstypes:['']
    });



    console.log( this.accountClasstypes[0]) ;
    console.log( this.accountClasstypes[1]) ;
    // let ctrls:AbstractControl<FormControl>[] = {
    //   accountName:['',Validators.required ], // ,Validators.email],
    //   accountNumber:['',Validators.required]
    // } ;
    // this.builder.group( ctrls ) ;

  }
  get form() { return this.createForm.controls ; }

  onSubmit() : void {
    this.submitted = true;
    console.log("Submitted");
    if (this.createForm.invalid)
      return;
    else {
      console.log("account id - " + this.newAccount.id + ": Submitting create");
      //this.service.createUserAccount(this.accountUser, Number(this.id)).subscribe(x => console.log(x));
      //this.service.create(this.newAccount ).subscribe(x=>this.newAccount=x);

      let order:BankServiceOrder = new BankServiceOrder();
      order.Type = "account-create" ; // not used
      order.ID = this.newAccount.id  ;
      order.userID = this.newAccount.userId ;
      order.accountType = this.newAccount.accountType ;
      order.accountClass = this.newAccount.accountClass ;
      order.accountName = this.newAccount.accountName ;
      order.accountNumber = this.newAccount.accountNumber ;
      order.accountBalance = this.newAccount.accountBalance ;
      //order.accountClassTypeTag = 'PRIMARY-CHECKING' ;
      order.accountClassTypeTag = this.selectedClasstype.idTagname ;

      console.log( "order: ", order ) ;
      console.log( "newAccount: ", this.newAccount ) ;
      console.log( "posting create order.") ;
      this.bankService.postAccountCreateOrder( order ).subscribe(
      x=>x=this.newAccount=x ,
      () => { console.log("Error posting order") ; },
        () => {
          console.log("Success posting order");
          console.log("account id - " + this.newAccount.id + ": Account creation completed");
          let newroute: string = '/account-summary/' + String(this.newAccount.id);
          this.router.navigate([newroute]);
        }
    ) ;

      // alert("Data Updated Successfully");
      // console.log("account id - " + this.newAccount.id + ": Account creation completed");
      // let newroute:string = '/account-summary/' + String(this.newAccount.id) ;
      // this.router.navigate([newroute])
    }
  }
}
