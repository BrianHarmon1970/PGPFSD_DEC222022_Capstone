import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {AccountClass} from "../AccountClass";
import {AccountsService} from "../accounts.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-account-edit',
  templateUrl: './account-edit.component.html',
  styleUrls: ['./account-edit.component.css']
})
export class AccountEditComponent implements OnInit {

  account!:AccountClass ; //= new AccountClass() ;
  id:string|null = "" ;
  editForm!:FormGroup ;
  submitted:boolean=false;
  constructor(private service:AccountsService,private activatedroute:ActivatedRoute, private builder:FormBuilder,private router:Router) { }

  ngOnInit(): void {

    //this.id=this.activatedroute.snapshot.paramMap.get('id');
    this.id = localStorage.getItem( "accountId" ) ;
    this.service.getAccountById(Number(this.id)).subscribe(x=>this.account=x);

    this.editForm=this.builder.group({
   //   accountClass:['',Validators.required],
      accountName:['',Validators.required ], // ,Validators.email],
      accountNumber:['',Validators.required],
    });
  }

  get form(){
    return this.editForm.controls;
  }

  onSubmit(){
    this.submitted=true;
    if(this.editForm.invalid)
      return;
    else
    {
      console.log( "id - " + this.id + ": Submitting changes" ) ;
      this.service.updateAccount(this.account,Number(this.id)).subscribe(
        x=>x=this.account=x ,
        () => { console.log("Error posting order") ; },
        () => {
          console.log("Success posting order");
          console.log("account id - " + this.account.id + ": Changes updated" );
          let newroute: string = '/user-account/' ;
          this.router.navigate([newroute]);
        }) ;
      //this.service.updateAccount(this.account,Number(this.id)).subscribe(x=>console.log(x));
      // alert("Data Updated Successfully");
      // console.log("id - " + this.id + ": Changes updated" ) ;
      // this.router.navigate(['accounts'])
    }
  }
}
