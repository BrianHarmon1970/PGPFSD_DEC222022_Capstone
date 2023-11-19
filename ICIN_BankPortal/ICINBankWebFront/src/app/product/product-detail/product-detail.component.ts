import { Component, OnInit } from '@angular/core';
import {Product} from "../product.class";
import {ActivatedRoute, Router} from "@angular/router";
import {ProductService} from "../product.service";

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css']
})

export class ProductDetailComponent implements OnInit {
  product:Product = new Product()  ;
  constructor( private router:Router,
               private activatedRoute:ActivatedRoute,
               private service:ProductService ) {
  }
  ngOnInit(): void
  {
    const pid = Number( this.activatedRoute.snapshot.paramMap.get( "productid" )) ;
    this.service.getProductById( pid ).subscribe( Product=>this.product=Product) ;
  }
}
