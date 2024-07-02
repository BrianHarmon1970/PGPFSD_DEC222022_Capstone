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
import {UserlistComponent} from "../users/userlist/userlist.component";
import {UserdetailsComponent} from "../users/userdetails/userdetails.component";
import {EditUserComponent} from "../users/edit-user/edit-user.component";
import {AccountListComponent} from "../accounts/account-list/account-list.component";
import {AccountDetailComponent} from "../accounts/account-detail/account-detail.component";
import {AccountEditComponent} from "../accounts/account-edit/account-edit.component";
import {AccountCreateComponent} from "../accounts/account-create/account-create.component";
import {UserAccountsComponent} from "../accounts/user-accounts/user-accounts.component";
import {AccountSummaryComponent} from "../accounts/account-summary/account-summary.component";
import {AccountDepositComponent} from "../accounts/account-deposit/account-deposit.component";
import {AccountWithdrawComponent} from "../accounts/account-withdraw/account-withdraw.component";
import {AccountTransferComponent} from "../accounts/account-transfer/account-transfer.component";
import {DashboardComponent} from "../dashboard/dashboard.component";
import {TransactionListComponent} from "../transaction/transaction-list/transaction-list.component";
import {AccountTransactionsComponent} from "../accounts/account-transactions/account-transactions.component";
import {TransactionDetailComponent} from "../transaction/transaction-detail/transaction-detail.component";
import {ProductViewComponent} from "../product/product-view/product-view.component";
import {ProductDetailComponent} from "../product/product-detail/product-detail.component";
import {ProductEditComponent} from "../product/product-edit/product-edit.component";
import {OrderDetailComponent} from "../orders/order-detail/order-detail.component";
import {OrderSubmitComponent} from "../orders/order-submit/order-submit.component";
import {OrderUpdateComponent} from "../orders/order-update/order-update.component";

const routes: Routes = [
  {path:"main", component:MainComponent, canActivate: [authGuard]},
  {path:"main/contact", component:MainContactComponent, canActivate: [authGuard]},
  {path:"main/invoice", component:MainInvoiceComponent, canActivate: [authGuard]},
  {path:"main/orders", component:MainOrdersComponent, canActivate: [authGuard]},
  // {path:"main/portfolio", component:MainPortfolioComponent, canActivate: [authGuard]},
  {path:"main/users", component:MainUsersComponent, canActivate: [authGuard]},

  {path:"main/products", component:MainProductsComponent, canActivate: [authGuard]},
  {path:"main/accounts", component:MainAccountsComponent, canActivate: [authGuard]},
  {path:"main/logout", component:SecurityLogoutComponent, canActivate: [authGuard] },

  {"path":"main/users",component:UserlistComponent, canActivate: [authGuard]},
  {"path":"main/details/:id",component:UserdetailsComponent, canActivate: [authGuard]},
  {"path":"main/edit/:id",component:EditUserComponent, canActivate: [authGuard]},

  {"path":"main/accounts",component:AccountListComponent, canActivate: [authGuard]},

  {"path":"main/account-details/:id",component:AccountDetailComponent , canActivate: [authGuard]},
  {"path":"main/account-edit/:id",component:AccountEditComponent , canActivate: [authGuard]},
  {"path":"main/account-create/:userid",component:AccountCreateComponent , canActivate: [authGuard]},

  {"path":"main/user-accounts/:userid",component:UserAccountsComponent , canActivate: [authGuard]},
  {"path":"main/user-accounts",component:UserAccountsComponent , canActivate: [authGuard]},

  {"path":"main/account-summary/:id",component:AccountSummaryComponent , canActivate: [authGuard]},
  {"path":"main/account-summary",component:AccountSummaryComponent , canActivate: [authGuard]},

  {"path":"main/account-deposit/:accountid",component:AccountDepositComponent , canActivate: [authGuard]},
  {"path":"main/account-withdraw/:accountid",component:AccountWithdrawComponent , canActivate: [authGuard]},
  {"path":"main/account-transfer/:userid",component:AccountTransferComponent , canActivate: [authGuard]} ,

  {"path":"main/transaction-error",component:DashboardComponent, canActivate: [authGuard]},
  {"path":"main/transaction-list",component:TransactionListComponent , canActivate: [authGuard]},
  {"path":"main/account-transactions/:acctid",component:AccountTransactionsComponent, canActivate: [authGuard]},
  //{"path":"transaction-list/:acctId",component:TransactionListComponent, canActivate: [authGuard]},
  {"path":"main/transaction-detail/:txid",component:TransactionDetailComponent, canActivate: [authGuard]},

  {"path":"main/product-view/:productid",component:ProductViewComponent, canActivate: [authGuard]},
  {"path":"main/product-detail/:productid",component:ProductDetailComponent, canActivate: [authGuard]},
  {"path":"main/product-edit/:productid",component:ProductEditComponent, canActivate: [authGuard]},

  {"path":"main/order-detail/:orderid",component:OrderDetailComponent, canActivate: [authGuard]},
  {"path":"main/order-submit",component:OrderSubmitComponent, canActivate: [authGuard]},
  {"path":"main/order-edit/:orderid",component:OrderUpdateComponent, canActivate: [authGuard]},

];

@NgModule({
  imports: [RouterModule.forChild(routes),SecurityModule],
  exports: [RouterModule]
})
export class MainRoutingRoutingModule { }
