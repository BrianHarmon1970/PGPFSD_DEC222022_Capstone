import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PortfolioLandingComponent } from './portfolio-landing/portfolio-landing.component';
import { AppNavbarComponent } from './app-navbar/app-navbar.component';
import { MainNavbarComponent } from './main/main-navbar/main-navbar.component';
import { SecurityModule } from './security/security.module';
import { MainModule } from './main/main.module';
import { SecurityLogoutComponent } from './security/security-logout/security-logout.component';
import { AppCopyrightComponent } from './app-copyright/app-copyright.component';
import { SecurityLogindataComponent } from './security/security-logindata/security-logindata.component';
import {UserlistComponent} from "./userlist/userlist.component";
import {UserdetailsComponent} from "./userdetails/userdetails.component";
import {EditUserComponent} from "./edit-user/edit-user.component";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";

@NgModule({
  declarations: [
    AppComponent,
    PortfolioLandingComponent,
    AppNavbarComponent,
    AppCopyrightComponent,

    // UserlistComponent,
    // UserdetailsComponent,
    // EditUserComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MainModule,
    SecurityModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [
    SecurityLogoutComponent,
    SecurityLogindataComponent
  ],
  exports: [
    // UserlistComponent ,
    // UserdetailsComponent,
    // EditUserComponent,
    // //HttpClientModule
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
