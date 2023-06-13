package com.harmonengineering.entity;
/*
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

public interface UserOrderRepository extends CrudRepository<UserOrder,Long>
{
//    @Query(value = "select order_id, product_id, quantity " +
//            "from (select o.ID as order_id, p.ID as product_id, oi.quantity as quantity" +
//            " from orders o, product p, order_items oi " +
//            " where oi.order_id = o.ID and oi.product_id = p.ID) as order_items",
//            nativeQuery = true )
    @Query(value ="select o.ID as order_id, p.ID as product_id, oi.quantity as quantity" +
            " from orders o, product p, order_items oi " +
            " where oi.order_id = o.ID and oi.product_id = p.ID",
            nativeQuery = true )
    List<OrderItem> getOrderItems() ;
    List<UserOrder> getUserOrdersByUserId( Long userId ) ;
}
*/