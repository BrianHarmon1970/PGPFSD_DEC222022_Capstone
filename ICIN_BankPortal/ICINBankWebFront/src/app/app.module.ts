import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PortfolioLandingComponent } from './portfolio-landing/portfolio-landing.component';
import { AppNavbarComponent } from './app-navbar/app-navbar.component';
import { SecurityModule } from './security/security.module';
import { MainModule } from './main/main.module';
import { SecurityLogoutComponent } from './security/security-logout/security-logout.component';
import { AppCopyrightComponent } from './app-copyright/app-copyright.component';
import { SecurityLogindataComponent } from './security/security-logindata/security-logindata.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {DashboardComponent} from "./dashboard/dashboard.component";
import {RouterModule} from "@angular/router";
import { ProductModule } from './product/product.module';
import { OrdersModule } from './orders/orders.module';

@NgModule({
  declarations: [
    AppComponent,
    PortfolioLandingComponent,
    AppNavbarComponent,
    AppCopyrightComponent,
    DashboardComponent

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
    HttpClientModule,
    RouterModule,
    ProductModule,
    OrdersModule
  ],
  providers: [
    SecurityLogoutComponent,
    SecurityLogindataComponent
  ],
  exports: [
     // UserlistComponent ,
     // UserdetailsComponent,
     // EditUserComponent,
     //HttpClientModule
    AppComponent,
    PortfolioLandingComponent,
    AppNavbarComponent,
    AppCopyrightComponent,
    DashboardComponent

  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
