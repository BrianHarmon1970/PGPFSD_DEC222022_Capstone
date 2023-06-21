import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TransactionListComponent } from './transaction-list/transaction-list.component';
import { TransactionDetailComponent } from './transaction-detail/transaction-detail.component';
import { TransactionCreateComponent } from './transaction-create/transaction-create.component';

@NgModule({
  declarations: [
    TransactionListComponent,
    TransactionDetailComponent,
    TransactionCreateComponent,
  ],
  imports: [
    CommonModule
  ] ,
  exports: []
})
export class TransactionModule { }
