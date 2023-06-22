import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TransactionListComponent } from './transaction-list/transaction-list.component';
import { TransactionDetailComponent } from './transaction-detail/transaction-detail.component';
import { TransactionCreateComponent } from './transaction-create/transaction-create.component';
import {ActivatedRouteSnapshot, RouterModule} from "@angular/router";

@NgModule({
  declarations: [
    TransactionListComponent,
    TransactionDetailComponent,
    TransactionCreateComponent,
  ],
  imports: [
    CommonModule,
    RouterModule,
  ] ,
    exports: [

      TransactionListComponent,
      RouterModule

    ]
})
export class TransactionModule { }
