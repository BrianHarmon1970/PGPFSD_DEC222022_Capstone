"use strict" ;
import { Component, OnInit } from '@angular/core';
import { DataService } from '../data.service';
import { UserClass } from '../UserClass';
import {FormBuilder,FormGroup,Validators} from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.css']
})
export class EditUserComponent implements OnInit {

  user:UserClass = new UserClass();
  id:string|null = "" ;
  registerForm!:FormGroup ;
  submitted:boolean=false;
  constructor(private service:DataService,private activatedroute:ActivatedRoute, private builder:FormBuilder,private router:Router) { }


  ngOnInit(): void {

    this.id=this.activatedroute.snapshot.paramMap.get('id');
    this.service.getUserById(Number(this.id)).subscribe(x=>this.user=x);

    this.registerForm=this.builder.group({
      userName:['',Validators.required],
      firstName:['',Validators.required],
      lastName:['',Validators.required],
      emailAddress:['',Validators.required ], // ,Validators.email],
      phoneNumber:['',Validators.required],
      //login:['',Validators.required],
      //password:['',Validators.required],

    });
  }

  get form(){
    return this.registerForm.controls;
  }

  onSubmit(){
    this.submitted=true;
    if(this.registerForm.invalid)
      return;
    else
      {
        console.log( "id - " + this.id + ": Submitting changes" ) ;
        this.service.updateUser(this.user,Number(this.id)).subscribe(x=>console.log(x));
       // alert("Data Updated Successfully");
        console.log("id - " + this.id + ": Changes updated" ) ;
        this.router.navigate(['users'])
      }
  }

}
