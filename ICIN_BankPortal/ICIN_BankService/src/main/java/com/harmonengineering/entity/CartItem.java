package com.harmonengineering.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

//@Entity
public class CartItem
{
 //   @Id
 //   @GeneratedValue
    private Long id ;
 //   @OneToOne
    private Product product ;
    private Long quantity ;

    public  CartItem() {}
    public CartItem( Product p , Long q ) { this.product = p ; this.quantity = q ; }

    public Product getProduct() { return product; }
    public Long getQuantity() { return quantity; }

    public void setProduct(Product product) { this.product = product; }
    public void setQuantity(Long quantity) { this.quantity = quantity; }
}
