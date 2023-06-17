import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { AppComponent } from 'src/app/app.component';
import { SecurityLogindataComponent } from '../security-logindata/security-logindata.component';

@Component({
  selector: 'app-security-signin',
  templateUrl: './security-signin.component.html',
  styleUrls: ['./security-signin.component.css','../security.module.common.css']
})
export class SecuritySigninComponent implements OnInit 
{
  app:AppComponent ; 
  router:Router ;
  loginData:SecurityLogindataComponent ;
  //LoginForm:FormGroup = new FormGroup({});
  LoginForm:FormGroup; 
  constructor( app:AppComponent, router:Router, 
    loginData:SecurityLogindataComponent,
    formBuilder:FormBuilder ) 
  { 
    this.app = app ;
    this.router = router ;
    this.loginData = loginData ;
    this.LoginForm = formBuilder.group('',null) ;
    
  }

  ngOnInit(): void 
  {
    this.LoginForm = new FormGroup({
      name: new FormControl(this.loginData.loginName, [
        Validators.required
        //Validators.minLength(4),
      ]),
      password: new FormControl(this.loginData.password, 
        [ Validators.required,
          Validators.minLength(6)
        ])
      //power: new FormControl(this.hero.power, Validators.required)
    }); 
  }
  
  get name() { return this.LoginForm.get('name') ; }
  get password() { return this.LoginForm.get('password'); } 

  onSubmit(): void 
  {
    this.signIn() ;
  }
  signIn():void
  {
    console.log( this.LoginForm ) ;
    if(  !(this.LoginForm.get('name')?.invalid ||
          this.LoginForm.get('password')?.invalid) )
    {
      this.loginData.loginToken = true ; 
      this.app.selectMainNavbar() ;
      this.router.navigate(['main']) ;
    }
  } 
}
function forbiddenNameValidator(arg0: RegExp): import("@angular/forms").ValidatorFn {
  throw new Error('Function not implemented.');
}

