import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';
import { SecuritySigninComponent } from './security/security-signin/security-signin.component';
import { SecuritySignupComponent } from './security/security-signup/security-signup.component';
import { PortfolioLandingComponent } from './portfolio-landing/portfolio-landing.component';
import { MainPortfolioComponent } from './main/main-portfolio/main-portfolio.component';
import { MainComponent } from './main/main.component';
import { MainRoutingRoutingModule } from './main-routing/main-routing-routing.module';
import { SecurityLogoutComponent } from './security/security-logout/security-logout.component';
import { authGuard } from './security/security-authguard';
import {DashboardComponent} from "./dashboard/dashboard.component";
import {EditUserComponent} from "./users/edit-user/edit-user.component";
import {UserdetailsComponent} from "./users/userdetails/userdetails.component";
import {UserlistComponent} from "./users/userlist/userlist.component";
import {AccountListComponent} from "./accounts/account-list/account-list.component";
import {AccountDetailComponent} from "./accounts/account-detail/account-detail.component";
import {AccountEditComponent} from "./accounts/account-edit/account-edit.component";
import {AccountCreateComponent} from "./accounts/account-create/account-create.component";
import {AccountSummaryComponent} from "./accounts/account-summary/account-summary.component";
import {AccountDepositComponent} from "./accounts/account-deposit/account-deposit.component";
import {AccountWithdrawComponent} from "./accounts/account-withdraw/account-withdraw.component";
import {TransactionDetailComponent} from "./transaction/transaction-detail/transaction-detail.component";
import {TransactionListComponent} from "./transaction/transaction-list/transaction-list.component";
import {AccountTransactionsComponent} from "./accounts/account-transactions/account-transactions.component";
import {UserAccountsComponent} from "./accounts/user-accounts/user-accounts.component";
import {AccountTransferComponent} from "./accounts/account-transfer/account-transfer.component";



const routes: Routes = [
  {path:'',component:AppComponent},
  {"path":"users",component:UserlistComponent},
  {"path":"details/:id",component:UserdetailsComponent},
  {"path":"edit/:id",component:EditUserComponent},

  {"path":"accounts",component:AccountListComponent},

  {"path":"account-details/:id",component:AccountDetailComponent },
  {"path":"account-edit/:id",component:AccountEditComponent },
  {"path":"account-create/:userid",component:AccountCreateComponent },

  {"path":"user-accounts/:userid",component:UserAccountsComponent },
  {"path":"account-summary/:id",component:AccountSummaryComponent },
  {"path":"account-deposit/:accountid",component:AccountDepositComponent },
  {"path":"account-withdraw/:accountid",component:AccountWithdrawComponent },
  {"path":"account-transfer/:userid",component:AccountTransferComponent } ,

  {"path":"transaction-error",component:DashboardComponent},
  {"path":"transaction-list",component:TransactionListComponent},
  {"path":"account-transactions/:acctid",component:AccountTransactionsComponent},
  //{"path":"transaction-list/:acctId",component:TransactionListComponent},
  {"path":"transaction-detail/:txid",component:TransactionDetailComponent},


  {"path":"dashboard",component:DashboardComponent} ,

  {path:'',component:PortfolioLandingComponent},
  {path:'signin', component:SecuritySigninComponent},
  {path:'signup', component:SecuritySignupComponent},
  {path:'logout', component:SecurityLogoutComponent},
  {path:'portfolio_landing', component:PortfolioLandingComponent},
  {path:'portfolio', component:MainPortfolioComponent },
  {path:'main', component:MainComponent, canActivate: [authGuard]},
  {path:"**", component:PortfolioLandingComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes),
    MainRoutingRoutingModule],
  exports: [RouterModule]
})
export class AppRoutingModule { }
