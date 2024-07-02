import { Component, OnInit } from '@angular/core';
import {OrderService} from "../order.service";
import {Order} from "../order.class";
import {OrderItem} from "../order-item.class";
import {Model} from "../../model.class";

@Component({
  selector: 'app-order-submit',
  templateUrl: './order-submit.component.html',
  styleUrls: ['./order-submit.component.css']
})
export class OrderSubmitComponent implements OnInit {

  constructor(
    private service:OrderService,
    private userAccountsModel:Model
  )
  { }
  newOrderItem:OrderItem = new OrderItem() ;
  newOrder:Order = new Order() ;

  ngOnInit(): void
  {
  }
  setView( view:string ):void
  {
    this.userAccountsModel.selectedView = view ;
  }
  newItem(  ) : void
  {
    this.setView( "summary" ) ;
    // this.setRoute( this.userAccountsModel.selectAccountId,
    //   this.userAccountsModel.baseRoute ) ;
  }

}
