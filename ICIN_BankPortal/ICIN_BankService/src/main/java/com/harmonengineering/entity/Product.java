package com.harmonengineering.entity;

import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "product")
@EnableTransactionManagement
public class Product
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name="ID")
    private Long id ;

    @Column( name="product_number")
    private String productNumber ;

    @Column( name="description" )
    private String description ;

    @Column( name="category" )
    private String category ;

    @Column( name="season")
    private String season ;

    @Column( name="brand")
    private String brand ;

    @Column( name="color")
    private String color ;



    @Column( name="price")
    private Double price ;

    @Column( name="discount" )
    private Double discount ;

    @Column( name="date_modified")
    private Date dateModified ;
    @Column( name="date_added")
    private Date dateAdded ;

    public Product() {}

    public void setId(Long id) { this.id = id; }
    public void setDescription(String description) { this.description = description; }
    public void  setCategory( String category ) { this.category = category ; }
    public void setPrice(Double price) { this.price = price; }
    public void setDateAdded(Date dateAdded) { this.dateAdded = dateAdded; }
    public void setSeason(String season) { this.season = season; }
    public void setBrand(String brand) { this.brand = brand; }
    public void setColor(String color) { this.color = color; }
    public void setDiscount(Double discount) { this.discount = discount; }
    public void setDateModified(Date dateModified) { this.dateModified = dateModified; }


    public Long getId() { return id; }
    public String getDescription() { return description; }
    public String getCategory() { return category ; }
    public Double getPrice() { return price; }
    public Date getDateAdded() { return dateAdded; }
    public String getSeason() { return season;}
    public String getBrand() { return brand; }
    public String getColor() { return color; }
    public Double getDiscount() { return discount; }
    public Date getDateModified() { return dateModified; }

}
