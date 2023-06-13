package com.harmonengineering.entity;

import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
/*
@Entity
//@Table( name="product")
@EnableTransactionManagement
public class ProductOrder extends Product
{
  //  @Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
   // @Column( name="ID")
   // private Long ID ;
    @Column( name="user_id" )
    private Long    userId ;
    @Column( name="order_date" )
    private Date orderDate ;

    @OneToOne
    @JoinColumn( name="product_id")
    private UserOrder order ;

    @OneToOne
    @JoinColumn( name="product_id", referencedColumnName = "ID", updatable = false, insertable = false )
    private User user ;

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public List<OrderItem> getItems() { return items; }
    public void setItems(List<OrderItem> items) { this.items = items; }

    public void setID(Long ID) { this.ID = ID; }
    public void setUserId(Long userId) { this.userId = userId; }
    public void setOrderDate(Date orderDate) { this.orderDate = orderDate; }

    public Long getID() { return ID; }
    public Long getUserId() { return userId; }
    public Date getOrderDate() { return orderDate; }


}

*/
