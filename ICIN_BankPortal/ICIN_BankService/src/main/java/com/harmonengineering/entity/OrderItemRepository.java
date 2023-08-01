package com.harmonengineering.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import javax.transaction.Transactional;
import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
    @Query(value ="select oi.order_id , o.ID as order_id, " +
            "p.ID as product_id, " +
            "oi.quantity as quantity" +
            " from orders o, product p, order_items oi " +
            " where oi.order_id = o.ID " +
                "and oi.product_id = p.ID",
            nativeQuery = true )
    List<OrderItem> getItems() ;

    List<OrderItem> getOrderItemsByOrderId( Long order_id ) ;
//    @Query(value ="delete from order_items where order_id = ?",
//            nativeQuery = true )
    @Transactional void deleteOrderItemsByOrderId(Long order_id ) ;
}
