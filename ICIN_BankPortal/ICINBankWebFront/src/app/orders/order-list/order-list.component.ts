import { Component, OnInit } from '@angular/core';
import {Product} from "../../product/product.class";
import {ActivatedRoute, Router} from "@angular/router";
import {ProductService} from "../../product/product.service";
import {OrderService} from "../order.service";
import {Order} from "../order.class";

@Component({
  selector: 'app-order-list',
  templateUrl: './order-list.component.html',
  styleUrls: ['./order-list.component.css']
})
export class OrderListComponent implements OnInit {
  //acctid: string | null = null ;
  Orders:Order[] = [] ;
  constructor( private router:Router,
               private activatedRoute:ActivatedRoute,
               private service:OrderService ) {
  }
  ngOnInit(): void
  {
    this.service.getAllOrders( ).subscribe( Orders=>this.Orders=Orders) ;
  }
}
