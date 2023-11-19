import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {ProductService} from "../product.service";
import {Product} from "../product.class";

@Component({
  selector: 'app-product-edit',
  templateUrl: './product-edit.component.html',
  styleUrls: ['./product-edit.component.css']
})
export class ProductEditComponent implements OnInit {
  product!:Product ; //= new AccountClass() ;
  id:string|null = "" ;
  editForm!:FormGroup ;
  submitted:boolean=false;
  constructor(private service:ProductService,private activatedroute:ActivatedRoute, private builder:FormBuilder,private router:Router) { }

  ngOnInit(): void {

    this.id=this.activatedroute.snapshot.paramMap.get('productid');
    //this.id = localStorage.getItem( "productId" ) ;
    this.service.getProductById(Number(this.id)).subscribe(x=>this.product=x);

     this.editForm=this.builder.group({
       productNumber:['','' ], //Validators.required],
       productDescription:['','' ], //Validators.required],
       productCategory:['','' ], //Validators.required],
     });
  }

  get form(){
    return this.editForm.controls;
  }

  onSubmit(){
    this.submitted=true;
    if(this.editForm.invalid)
      return;
    else
    {
      console.log( "id - " + this.id + ": Submitting changes" ) ;
     // this.service.updateProduct(this.product,Number(this.id)).subscribe(
      this.service.updateProduct(this.product).subscribe(
        x=>x=this.product=x ,
        () => { console.log("Error updating product") ; },
        () => {
          console.log("Success updating product");
          console.log("product id - " + this.product.id + ": Changes updated" );
          let newroute: string = '/main/products/' ;
          this.router.navigate([newroute]);
        }) ;
    }
  }
}
