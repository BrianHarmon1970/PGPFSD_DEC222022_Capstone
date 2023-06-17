import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from '../app.component';
import { MainContactComponent } from '../main/main-contact/main-contact.component';
import { MainInvoiceComponent } from '../main/main-invoice/main-invoice.component';
import { MainOrdersComponent } from '../main/main-orders/main-orders.component';
import { MainPortfolioComponent } from '../main/main-portfolio/main-portfolio.component';
import { MainProductsComponent } from '../main/main-products/main-products.component';
import { MainComponent } from '../main/main.component';
import { MainModule } from '../main/main.module';
import { authGuard } from '../security/security-authguard';
import { SecurityLogoutComponent } from '../security/security-logout/security-logout.component';
import {MainUsersComponent} from "../main/main-users/main-users.component";

const routes: Routes = [
  {path:"main", component:MainComponent, canActivate: [authGuard]},
  {path:"main/contact", component:MainContactComponent, canActivate: [authGuard]},
  {path:"main/invoice", component:MainInvoiceComponent, canActivate: [authGuard]},
  {path:"main/orders", component:MainOrdersComponent, canActivate: [authGuard]},
  // {path:"main/portfolio", component:MainPortfolioComponent, canActivate: [authGuard]},
  {path:"main/users", component:MainUsersComponent, canActivate: [authGuard]},

  {path:"main/products", component:MainProductsComponent, canActivate: [authGuard]},
  {path:"main/logout", component:SecurityLogoutComponent, canActivate: [authGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MainRoutingRoutingModule { }
