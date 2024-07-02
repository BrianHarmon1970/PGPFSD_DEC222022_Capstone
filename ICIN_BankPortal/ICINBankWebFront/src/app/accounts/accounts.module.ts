import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AccountListComponent } from './account-list/account-list.component';
import { AccountDetailComponent } from './account-detail/account-detail.component';
import { AccountEditComponent } from './account-edit/account-edit.component';
import {RouterModule} from "@angular/router";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { AccountCreateComponent } from './account-create/account-create.component';
import {UsersModule} from "../users/users.module";
import { AccountSummaryComponent } from './account-summary/account-summary.component';
import { AccountWithdrawComponent } from './account-withdraw/account-withdraw.component';
import { AccountDepositComponent } from './account-deposit/account-deposit.component';
import { AccountTransactionsComponent } from './account-transactions/account-transactions.component';
import {TransactionModule} from "../transaction/transaction.module";
import { UserAccountsComponent } from './user-accounts/user-accounts.component';
import { AccountTransferComponent } from './account-transfer/account-transfer.component';
import {AppComponent} from "../app.component";
import {OrdersModule} from "../orders/orders.module";
import {AppModule} from "../app.module";




@NgModule({
  declarations: [
    AccountListComponent,
    AccountDetailComponent,
    AccountEditComponent,
    AccountCreateComponent,
    AccountSummaryComponent,
    AccountWithdrawComponent,
    AccountDepositComponent,
    AccountTransactionsComponent,
    UserAccountsComponent,
    AccountTransferComponent
  ],
  exports: [
    AccountListComponent,
    AccountDetailComponent,
    AccountEditComponent,
    AccountCreateComponent,
    AccountSummaryComponent,
    AccountTransactionsComponent,
    UserAccountsComponent,
    // FormsModule,
    // ReactiveFormsModule
  ],
  imports: [

    CommonModule,
    RouterModule,
    FormsModule,
    UsersModule,
    TransactionModule,
    OrdersModule,
    ReactiveFormsModule,


  ],

})
export class AccountsModule { }
