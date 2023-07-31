package com.harmonengineering.entity;

import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table( name="user_cart")
//@EnableTransactionManagement
public class UserCartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")       private Long ID;
    @Column(name = "user_id")   private Long userId ;
    @Column(name = "product_id") private Long productId ;
    @Column(name = "quantity")  private int quantity ;
    @Column(name = "date_added") private Date dateAdded ;

    public UserCartItem() {}

    public Long getID() { return ID; }
    public void setID(Long ID) { this.ID = ID; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public Date getDateAdded() { return dateAdded; }
    public void setDateAdded(Date dateAdded) { this.dateAdded = dateAdded; }
}
