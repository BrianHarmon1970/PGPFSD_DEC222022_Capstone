package com.harmonengineering.beans;

import com.harmonengineering.beans.CartItem;
import com.harmonengineering.entity.Product;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//@Entity
//@Table( name="user_cart" )
//@EnableTransactionManagement
public class UserCart
{
    public static final Long defaultUserId = 2L ;
    @Id
 //   @Column( name="user_id")
    private Long userId ;


    //    @Column( name="product_id" )
    //private Long productId ;
    //@Column( name="quantity" )
    //private Long quantity ;
    //@OneToMany
    private List<CartItem> m_Items ;

    public UserCart() {
        m_Items = new ArrayList<CartItem>();
        userId = defaultUserId;
    }

    public void add(Product product , Long quantity ) { add( new CartItem( product, quantity)) ;} ;
    public void add( CartItem cartItem ) { m_Items.add( cartItem ) ; }


    public List<CartItem> getItems() { return m_Items; }
    public void setM_Items( List<CartItem> Items) { this.m_Items = Items; }

    public void setUserId(Long userId) { this.userId = userId; }
    //public void setProductId(Long productId) { this.productId = productId; }
    //public void setQuantity(Long quantity) { this.quantity = quantity; }

    public Long getUserId() { return userId; }
    //public Long getProductId() { return productId; }
    //public Long getQuantity() { return quantity; }
}
