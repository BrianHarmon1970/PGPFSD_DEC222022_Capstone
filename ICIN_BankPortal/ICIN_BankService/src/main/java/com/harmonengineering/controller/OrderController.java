package com.harmonengineering.controller;

import com.harmonengineering.entity.*;
import com.harmonengineering.icin_bankservice.IcinBankServiceApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200",
        methods = { RequestMethod.GET, RequestMethod.DELETE,
                RequestMethod.POST, RequestMethod.PUT } ,
        allowedHeaders = "*", maxAge = 3600 )
@RequestMapping( value = "${com.harmonengineering.icin_bank.order-root}")
@RestController
public class OrderController {
    private static final Logger logger = LoggerFactory.getLogger(IcinBankServiceApplication.class);
    UserOrderRepository userOrderRepository ;
    OrderItemRepository orderItemRepository ;

    public OrderController(

            UserOrderRepository uoRepo,
            OrderItemRepository oiRepo )
    {
        userOrderRepository = uoRepo ;
        orderItemRepository = oiRepo ;
    }

    // USER ORDER  CRUD REST API
    //
    @GetMapping(value = "", produces = "application/json; charset=UTF-8" )
    List<UserOrder> getOrders()
    {
        return (List<UserOrder> )userOrderRepository.findAll();
    }
    @GetMapping(value = "{id}", produces = "application/json; charset=UTF-8")
    UserOrder getOrder( @PathVariable Long id )
    {
        return userOrderRepository.findById( id ).orElseThrow();
    }

    @GetMapping(value = "/foruser/{user_id}", produces = "application/json; charset=UTF-8")
    List<UserOrder>
    getUserOrders( @PathVariable Long user_id )
    {
        return (List<UserOrder> )userOrderRepository.getUserOrdersByUserId( user_id );
    }

    @PostMapping("")
    public UserOrder addOrder(@RequestBody UserOrder order )
    { return userOrderRepository.save( order ) ; }

    @PutMapping("")
    public UserOrder updateOrder(@RequestBody UserOrder order )
    { return userOrderRepository.save( order ) ; }

    @DeleteMapping(value = "{id}", produces = "application/json; charset=UTF-8")

    void deleteUserOrder( @PathVariable Long id )
    {
        userOrderRepository.deleteById( id ) ;
    }


    // ORDERITEM CRUD REST API
    //
    @GetMapping(value = "orderitem", produces = "application/json; charset=UTF-8")
    List<OrderItem>
    getAllOrderItems()
    {
        return (List<OrderItem> )orderItemRepository.findAll();
    }

    @GetMapping(value = "orderitem/fororder/{order_id}", produces = "application/json; charset=UTF-8")
    List<OrderItem>
    getOrderItemsByOrderId( @PathVariable Long order_id )
    {
        return (List<OrderItem> )orderItemRepository.getOrderItemsByOrderId(order_id);
    }
    @GetMapping(value = "orderitem/{orderitem_id}", produces = "application/json; charset=UTF-8")
    OrderItem
    getOrderItemsById( @PathVariable Long orderitem_id )
    {
        return orderItemRepository.findById(orderitem_id).orElseThrow();
    }
    @PostMapping("orderitem")
    public OrderItem addOrderItem(@RequestBody OrderItem item )
    { return orderItemRepository.save( item ) ; }

    @PutMapping("orderitem")
    public OrderItem updateOrderItem(@RequestBody OrderItem item )
    { return orderItemRepository.save( item ) ; }

    @DeleteMapping( "orderitem/{order_id}")
    void deleteOrderItemsByOrderId(@PathVariable Long order_id )
    {
        orderItemRepository.deleteOrderItemsByOrderId( order_id ) ;
    }
}
