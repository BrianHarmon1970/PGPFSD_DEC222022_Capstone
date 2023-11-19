import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OrderSubmitComponent } from './order-submit/order-submit.component';
import { OrderUpdateComponent } from './order-update/order-update.component';
import { OrderDetailComponent } from './order-detail/order-detail.component';



@NgModule({
  declarations: [
    OrderSubmitComponent,
    OrderUpdateComponent,
    OrderDetailComponent
  ],
  imports: [
    CommonModule
  ],
  exports: [
    OrderDetailComponent
  ]
})
export class OrdersModule { }
