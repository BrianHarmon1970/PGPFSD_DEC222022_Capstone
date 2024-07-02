import { Component, OnInit } from '@angular/core';
import {Product} from "../product.class";
import {ActivatedRoute, Router} from "@angular/router";
import {ProductService} from "../product.service";
import {Model} from "../../model.class";
import {AccountClass} from "../../accounts/AccountClass";
import {AccountsService} from "../../accounts/accounts.service";

@Component({
  selector: 'app-product-view',
  templateUrl: './product-view.component.html',
  styleUrls: ['./product-view.component.css']
})
export class ProductViewComponent implements OnInit
{
  product:Product = new Product()  ;
  accountsModel:Model = new Model() ;
  selectedAccountId:number | null = null ;
  selectedAccount:AccountClass = new AccountClass ;
  constructor( private router:Router,
               private activatedRoute:ActivatedRoute,
               private service:ProductService,
               private accountService:AccountsService ) {
  }
  ngOnInit(): void
  {
    const pid = Number( this.activatedRoute.snapshot.paramMap.get( "productid" )) ;
    this.service.getProductById( pid ).subscribe( Product=>this.product=Product) ;

    this.accountsModel.loadModel() ;

    this.selectedAccountId = this.accountsModel.selectAccountId ;
    this.accountService.getAccountById( this.selectedAccountId ).subscribe( Account=>this.selectedAccount=Account) ;

    // this.selectedAccount = this.accountsModel.selectedAccount() ;

  }
  submitOrder() : void
  {

  }
}
