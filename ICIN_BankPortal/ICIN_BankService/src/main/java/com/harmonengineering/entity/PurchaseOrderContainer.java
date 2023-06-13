package com.harmonengineering.entity;

import java.util.List;

public class PurchaseOrderContainer
{
    private UserOrder order ;
    private List<OrderItem> orderItems ;

    public PurchaseOrderContainer( UserOrder uo, List<OrderItem> orderItemList )
    {
        order = uo ; orderItems = orderItemList ;
    }
    public UserOrder getOrder() { return order; }
    public void setOrder(UserOrder order) { this.order = order; }
    public List<OrderItem> getOrderItems() { return orderItems; }
    public void setOrderItems(List<OrderItem> orderItems) { this.orderItems = orderItems; }
}