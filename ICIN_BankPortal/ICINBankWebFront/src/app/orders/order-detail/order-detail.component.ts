import { Component, OnInit } from '@angular/core';
import {Order} from "../order.class";
import {ActivatedRoute, Router} from "@angular/router";
import {OrderService} from "../order.service";
import {OrderItem} from "../order-item.class";

@Component({
  selector: 'app-order-detail',
  templateUrl: './order-detail.component.html',
  styleUrls: ['./order-detail.component.css']
})

export class OrderDetailComponent implements OnInit {
  //acctid: string | null = null ;
  orderid: number = 0 ;
  Order:Order = new Order()  ;
  Items:OrderItem[] = [] ;
  constructor( private router:Router,
               private activatedRoute:ActivatedRoute,
               private service:OrderService ) {
  }
  ngOnInit(): void
  {
    const id = Number( this.activatedRoute.snapshot.paramMap.get( "orderid" )) ;
    this.service.getOrderById( id ).subscribe( Order=>this.Order=Order) ;
    this.service.getOrderItemsByOrderId( id ).subscribe( Items=>this.Items=Items ) ;
  }
}

