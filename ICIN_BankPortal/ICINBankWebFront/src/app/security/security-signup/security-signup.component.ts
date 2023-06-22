import { Component, OnInit } from '@angular/core';
import { AppComponent } from 'src/app/app.component';
import { Router } from '@angular/router';
import { FormGroup, FormBuilder, FormControl, Validators, ValidatorFn, AbstractControl, ValidationErrors } from '@angular/forms';
import { SecurityLogindataComponent } from '../security-logindata/security-logindata.component';
import { ValidationError } from 'webpack';

@Component({
  selector: 'app-security-signup',
  templateUrl: './security-signup.component.html',
  styleUrls: ['./security-signup.component.css','../security.module.common.css' ]
})
export class SecuritySignupComponent implements OnInit {
  app:AppComponent ;
  router:Router ;
  loginData:SecurityLogindataComponent ;
  //LoginForm:FormGroup = new FormGroup({});
  LoginForm:FormGroup;
  formBuilder:FormBuilder ;

  constructor( app:AppComponent, router:Router,
    loginData:SecurityLogindataComponent,
    formBuilder:FormBuilder )

  {
    this.app = app ;
    this.router = router ;
    this.loginData = loginData ;
    this.formBuilder = formBuilder ;
    this.LoginForm = this.formBuilder.group('',null) ;
  }

  ngOnInit(): void
  {
    // this.LoginForm = new FormGroup({
    //   name: new FormControl(this.loginData.loginName,
    //   [ Validators.required ]),
    //   password: new FormControl(this.loginData.password,
    //   [
    //     Validators.required,
    //     Validators.minLength(6)
    //   ]),
    // },{validator: this.checkIfMatchingPasswords('password', 'confirmPass')});

     this.LoginForm = new FormGroup(
      {
        name: new FormControl(this.loginData.loginName,[ Validators.required ]),
        password: new FormControl(this.loginData.password,[ Validators.required, Validators.minLength(6)]),
        password_confirmation: new FormControl( this.loginData.password_confirmation,
          [ Validators.required])
      }, { validators: mismatchValidator});
    }

  get name() { return this.LoginForm.get('name') ; }
  get password() { return this.LoginForm.get('password'); }
  get password_confirmation() { return this.LoginForm.get('password_confirmation'); }

  onSubmit(): void
  {
    console.log( this.LoginForm ) ;
    if(  !(this.LoginForm.get('name')?.invalid ||
          this.LoginForm.get('password')?.invalid) ||
          this.LoginForm.get('password_confirmation')?.invalid)
    {
      this.loginData.loginToken = true ;
      this.signUp() ;
    }
  }
  signUp():void
  {
    if(  !(this.LoginForm.get('name')?.invalid ||
    this.LoginForm.get('password')?.invalid) ||
    this.LoginForm.get('password_confirmation')?.errors?.['mismatch'])
    {
      this.loginData.loginToken = true ;
      this.app.selectMainNavbar() ;
      this.router.navigate(['main']) ;
    }
  }
  onSignin():void
  {
    this.router.navigate(['signin'])
  }
}
export const mismatchValidator : ValidatorFn = ( control: AbstractControl ) :
  ValidationErrors| null =>
  {
    const password:string = control.get('password')?.value ;
    const password_confirmation:string = control.get('password_confirmation')?.value ;

    console.log( password,password_confirmation ) ;
    if ( password === password_confirmation )  return null ;
      else return { mismatch: true }  ;
  }

