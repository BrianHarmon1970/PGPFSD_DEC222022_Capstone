
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { SecuritySigninComponent } from './security-signin/security-signin.component';
import { SecuritySignupComponent } from './security-signup/security-signup.component';
import { SecurityLogoutComponent } from './security-logout/security-logout.component';
import { NgModule } from '@angular/core';
import { SecurityLogindataComponent } from './security-logindata/security-logindata.component';



@NgModule({
  declarations: [
    SecuritySigninComponent,
    SecuritySignupComponent,
    SecurityLogoutComponent,
    SecurityLogindataComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule
  ]
})
export class SecurityModule { }
