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

@NgModule({
  declarations: [
    AccountListComponent,
    AccountDetailComponent,
    AccountEditComponent,
    AccountCreateComponent,
    AccountSummaryComponent
  ],
  exports: [
    AccountListComponent,
    AccountDetailComponent,
    AccountEditComponent,
    AccountCreateComponent,
    AccountSummaryComponent
    // FormsModule,
    // ReactiveFormsModule
  ],
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
    UsersModule,
    ReactiveFormsModule
  ]
})
export class AccountsModule { }
