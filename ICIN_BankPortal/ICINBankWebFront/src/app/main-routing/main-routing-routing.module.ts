import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MainContactComponent } from '../main/main-contact/main-contact.component';
import { MainInvoiceComponent } from '../main/main-invoice/main-invoice.component';
import { MainOrdersComponent } from '../main/main-orders/main-orders.component';
import { MainProductsComponent } from '../main/main-products/main-products.component';
import { MainComponent } from '../main/main.component';
import { authGuard } from '../security/security-authguard';
import {MainUsersComponent} from "../main/main-users/main-users.component";
import {MainAccountsComponent} from "../main/main-accounts/main-accounts.component";
import {SecurityModule} from "../security/security.module";
import {SecurityLogoutComponent} from "../security/security-logout/security-logout.component";

const routes: Routes = [
  {path:"main", component:MainComponent, canActivate: [authGuard]},
  {path:"main/contact", component:MainContactComponent, canActivate: [authGuard]},
  {path:"main/invoice", component:MainInvoiceComponent, canActivate: [authGuard]},
  {path:"main/orders", component:MainOrdersComponent, canActivate: [authGuard]},
  // {path:"main/portfolio", component:MainPortfolioComponent, canActivate: [authGuard]},
  {path:"main/users", component:MainUsersComponent, canActivate: [authGuard]},

  {path:"main/products", component:MainProductsComponent, canActivate: [authGuard]},
  {path:"main/accounts", component:MainAccountsComponent, canActivate: [authGuard]},
  {path:"main/logout", component:SecurityLogoutComponent, canActivate: [authGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes),SecurityModule],
  exports: [RouterModule]
})
export class MainRoutingRoutingModule { }
