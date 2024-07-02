import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OrderSubmitComponent } from './order-submit/order-submit.component';
import { OrderUpdateComponent } from './order-update/order-update.component';
import { OrderDetailComponent } from './order-detail/order-detail.component';
import { OrderListComponent } from './order-list/order-list.component';
import {RouterModule} from "@angular/router";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {ProductModule} from "../product/product.module";



@NgModule({
  declarations: [
    OrderSubmitComponent,
    OrderUpdateComponent,
    OrderDetailComponent,
    OrderListComponent
  ],
  imports: [
    ProductModule,
    CommonModule,
    RouterModule,
    FormsModule,
    ReactiveFormsModule
  ],
    exports: [
        OrderDetailComponent,
        OrderListComponent,
        OrderSubmitComponent
    ]
})
export class OrdersModule { }
