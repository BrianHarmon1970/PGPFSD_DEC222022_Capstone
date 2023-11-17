import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OrderSubmitComponent } from './order-submit/order-submit.component';
import { OrderUpdateComponent } from './order-update/order-update.component';



@NgModule({
  declarations: [
    OrderSubmitComponent,
    OrderUpdateComponent
  ],
  imports: [
    CommonModule
  ]
})
export class OrdersModule { }
