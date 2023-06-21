import { Component, OnInit } from '@angular/core';
import {AccountClass} from "../AccountClass";
import {ActivatedRoute, Router} from "@angular/router";
import {DataService} from "../../users/data.service";
import {AccountsService} from "../accounts.service";
import {UserClass} from "../../users/UserClass";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";



@Component({
  selector: 'app-account-create',
  templateUrl: './account-create.component.html',
  styleUrls: ['./account-create.component.css']
})
export class AccountCreateComponent implements OnInit
{
  newAccount:AccountClass = new AccountClass() ;
  accountUser:UserClass = new UserClass() ;
  createForm!:FormGroup ;
  submitted:boolean = false ;
  constructor( private router:Router,
               private activatedRoute:ActivatedRoute,
               private service:AccountsService, private userService:DataService, private builder:FormBuilder ) { }


  ngOnInit(): void
  {
    const id=this.activatedRoute.snapshot.paramMap.get('userid');
    console.log("id:",id);
    this.newAccount.userId = Number(id) ;
    this.newAccount.accountType="CHECKING" ;
    this.newAccount.accountClass="BASIC" ;
    this.newAccount.accountBalance = 0.0 ;
    this.userService.getUserById(Number(id)).subscribe(data=>this.accountUser=data);

    //this.form = this.builder.group()
    //let validators:Validator[] = [] ;
    //let asyncValidators:AsyncValidator[] = [] ;
    this.createForm = this.builder.group({
      accountName:['',Validators.required ], // ,Validators.email],
      accountNumber:['',Validators.required]
    });
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
      this.service.create(this.newAccount ).subscribe(x=>this.newAccount=x);

      // alert("Data Updated Successfully");
      console.log("account id - " + this.newAccount.id + ": Account creation completed");
      let newroute:string = '/account-summary/' + String(this.newAccount.id) ;
      this.router.navigate([newroute])
    }
  }
}
