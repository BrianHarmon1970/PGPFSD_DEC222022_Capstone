import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MainOrdersComponent } from './main-orders/main-orders.component';
import { MainInvoiceComponent } from './main-invoice/main-invoice.component';
import { MainProductsComponent } from './main-products/main-products.component';
import { MainContactComponent } from './main-contact/main-contact.component';
import { MainPortfolioComponent } from './main-portfolio/main-portfolio.component';
import { SecurityLogoutComponent } from '../security/security-logout/security-logout.component';
import { MainNavbarComponent } from './main-navbar/main-navbar.component';
import { MainComponent } from './main.component';
import { MainRoutingRoutingModule } from '../main-routing/main-routing-routing.module';
import { SecurityModule } from '../security/security.module';
import {UserlistComponent} from "../userlist/userlist.component";
import {UserdetailsComponent} from "../userdetails/userdetails.component";
import {EditUserComponent} from "../edit-user/edit-user.component";
import {AppModule} from "../app.module";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import { MainUsersComponent } from './main-users/main-users.component';




@NgModule({
  declarations: [
    MainOrdersComponent,
    MainInvoiceComponent,
    MainProductsComponent,
    MainContactComponent,
    MainPortfolioComponent,
    MainNavbarComponent,
    MainComponent,

    UserlistComponent,
    UserdetailsComponent,
    EditUserComponent,
    MainUsersComponent

  ],
  imports: [
    CommonModule,
    MainRoutingRoutingModule,
    SecurityModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  exports: [ MainNavbarComponent,MainComponent ]
})
export class MainModule { } ;

