package com.harmonengineering.entity;

import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table( name="order_items")
//@EnableTransactionManagement
public class OrderItem
{
    //@Id
    //@GeneratedValue
    //@Column( name="item_id")
    //UUID itemId ;

//    @Id
//    @GeneratedValue( strategy = GenerationType.AUTO)
//    Long itemId ;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "item_id" )              private Long ID ;

    @Column( name="order_id")
    private Long orderId ;
    @Column( name="product_id" )
    private Long productId ;
    @Column( name="quantity" )
    private Long quantity ;

   /* @OneToMany
    @JoinTable(
            name = "order_items",
            inverseJoinColumns = @JoinColumn(name = "order_id", referencedColumnName = "order_id"),
            joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    )
    private List<OrderItem> items ;

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }*/
    @OneToOne
    @JoinColumn( name="product_id", referencedColumnName = "ID" , insertable = false, updatable = false)
    private Product product ;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

//    @OneToOne
//    @JoinColumn( name="order_id", referencedColumnName = "ID" , insertable = false, updatable = false)
//    private UserOrder order ;

//    public UserOrder getOrder() {
//        return order;
//    }
//
//    public void setOrder(UserOrder order) {
//        this.order = order;
//    }


    public OrderItem() {}

    public Long getOrderId() { return orderId; }
    public Long getProductId() { return productId; }
    public Long getQuantity() { return quantity; }

    public void setOrderId(Long orderId) { this.orderId = orderId; }
    public void setProductId(Long productId) { this.productId = productId; }
    public void setQuantity(Long quantity) { this.quantity = quantity; }
}
