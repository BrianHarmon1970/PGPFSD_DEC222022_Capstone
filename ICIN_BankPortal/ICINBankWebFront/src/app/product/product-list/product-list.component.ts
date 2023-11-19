import { Component, OnInit } from '@angular/core';
import {Product} from "../product.class";
import {ActivatedRoute, ActivatedRouteSnapshot, Router} from "@angular/router";
import {ProductService} from "../product.service";

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {
  //acctid: string | null = null ;
  Products:Product[] = [] ;
  constructor( private router:Router,
               private activatedRoute:ActivatedRoute,
               private service:ProductService ) {
  }
  ngOnInit(): void
  {
    //this.service.getAllAccountTransactions().subscribe(result => this.accountTransactions = result);
    //
    // else {
    //this.acctid = this.activatedRoute.paramMap.get( "acctid" ) ;
    //const acctid = this.activatedRoute.snapshot.paramMap.get( "acctid" ) ;
    //let acid:string|null = localStorage.getItem( "accountId" ) ;
    //acid = acid == null ? "" : acid ;
    this.service.getAllProducts( ).subscribe( Products=>this.Products=Products) ;

    // }

  }
  DeleteProductById(id:number){
    //   //refresh the list once user is deleted
    //   this.accountTransactions=this.accountTransactions.filter(c=>c.id!=id);
    //   //delete user -- umm.. transaction log record
    //   this.service.deleteById(id);
    //   console.log("!!!!!Record Deleted!!!!!!");
  }
}
