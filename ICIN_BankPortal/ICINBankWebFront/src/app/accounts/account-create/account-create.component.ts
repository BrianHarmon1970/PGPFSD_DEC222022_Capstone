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
import {AccountCapacity} from "../../bank-service/account-capacity";

@Component({
  selector: 'app-account-create',
  templateUrl: './account-create.component.html',
  styleUrls: ['./account-create.component.css']
})
export class AccountCreateComponent implements OnInit
{
  newAccount:AccountClass = new AccountClass() ;
  accountUser:UserClass = new UserClass() ;
  userAccountList:AccountClass[] = [] ;
  accountClasstypes:AccountClasstype[] = []  ;
  selectedClasstype:AccountClasstype = new AccountClasstype() ;
  selectedMasterAccount:AccountClass  = new AccountClass() ;
  selectedClasstypeCapacity:AccountCapacity = new AccountCapacity() ;
  createForm!:FormGroup ;
  userid:string | null = null  ;
  submitted:boolean = false ;
  constructor( private router:Router,
               private activatedRoute:ActivatedRoute,
               private accountService:AccountsService,
               private userService:DataService,
               private bankService:BankServiceService,
               private builder:FormBuilder ) { }

  // create table from web front
  // 1. collect configuration data and determine when a user is selecting primary and present accordingly,
  // when a user is selecting secondary and present accordingly, when a user is selecting BASIC and present accordingly
  // PRIMARY - can be master
  // SECONDARY - can be sub
  // BASIC - can be neither master nor sub
  // can be master and can be sub, possible but undefined. THERE are no default implementations of this.
  // 2. if a sub account type is selected, provide the user with a list of qualifying master accounts from the identified
  // userId account list to link to.
  // 3. if a master account type is selected, provide the user with a list of qualifying sub accounts from the identified
  // userId account list to link to. alternatively dont do anything. a master account can be created and selected
  // afterward as the sub account is defined.
  // 4. update the bank-service data service to provide the expanded configuration data and to get accounts by master and
  // by sub and by user... i hope this was made available... if not will need to implement what is necessary.
  // 5. provide the account to be created as well as the account to be linked in the service order record on the request
  // call. (5:57)

  ngOnInit(): void
  {
    this.bankService.getAllAccountClassTypeDefs().subscribe(
      x=>x=this.accountClasstypes=x,
      null,
      ()=>{
          this.selectedClasstype = this.accountClasstypes[0] ;
        this.bankService.getCapacityForClasstype( this.selectedClasstype.id ).subscribe( x=>this.selectedClasstypeCapacity=x )
      }) ;

    this.userid=this.activatedRoute.snapshot.paramMap.get('userid');
    //console.log("userid:",this.userid);
    this.newAccount.userId = Number(this.userid) ;
    this.newAccount.accountType="CHECKING" ;
    this.newAccount.accountClass="BASIC" ;
    this.newAccount.accountBalance = 0.0 ;
    this.userService.getUserById(Number(this.userid)).subscribe(data=>this.accountUser=data);
    this.accountService.getAllMasterAccountsForUser(Number(this.userid)).subscribe(
      x=>this.userAccountList=x
    ) ;


    //this.form = this.builder.group()
    //let validators:Validator[] = [] ;
    //let asyncValidators:AsyncValidator[] = [] ;


    // get the defined account types

    this.createForm = this.builder.group({
      accountName:['',Validators.required ], // ,Validators.email],
      accountNumber:['',Validators.required],
      selectedClasstype:[''],
      accountClasstypes:[''],
      selectedClasstypeCapacity:[''],
      selectedMasterAccount:['']
    });
    //this.bankService.getCapacityForClasstype( this.selectedClasstype.Id ).subscribe( x=>this.selectedClasstypeCapacity=x )
    //this.bankService.getCapacityForClasstype( 3 ).subscribe( x=>this.selectedClasstypeCapacity=x )


    console.log( this.accountClasstypes[0]) ;
    console.log( this.accountClasstypes[1]) ;
    // let ctrls:AbstractControl<FormControl>[] = {
    //   accountName:['',Validators.required ], // ,Validators.email],
    //   accountNumber:['',Validators.required]
    // } ;
    // this.builder.group( ctrls ) ;

  }
  get form() { return this.createForm.controls ; }
  getCap():AccountCapacity
  {
    this.bankService.getCapacityForClasstype( Number(this.selectedClasstype.accountClassTypeId) ).subscribe( x=>this.selectedClasstypeCapacity=x )
    return this.selectedClasstypeCapacity ;
  }
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
      order.masterAccountID = this.selectedMasterAccount.id ;

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
