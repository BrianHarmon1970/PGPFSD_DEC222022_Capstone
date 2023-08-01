package com.harmonengineering.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>
{
    @Query( value="select p.ID as ID, description , category, " +
            "price, date_added , season, brand, color, discount, date_modified " +
            "from orders o, product p, order_items oi " +
            "where oi.order_id = o.ID and oi.product_id = p.ID", nativeQuery = true )
    List<Product> getProductPurchases() ;
}
