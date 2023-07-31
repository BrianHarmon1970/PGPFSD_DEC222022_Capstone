package com.harmonengineering.bankservice;

import com.harmonengineering.entity.OrderItem;

import java.util.List;

public class OrderSubmitOrder extends BankServiceOrder
    implements BankServiceOrderInterface
{
    private Long orderId ; // out
    private Long userId ; // in or out
    private Long accountId ; // in
    private Long productId ; // in
    private Long quantity ;  // in
    List<OrderItem> orderItems ; // in ( not sure how this works through Ajax between here(Java server)
                                 //      and there (Angular web client))

    public OrderSubmitOrder() {}

    public List<OrderItem> getOrderItems() { return orderItems; }
    public void setOrderItems(List<OrderItem> orderItems) { this.orderItems = orderItems; }

    public Long getOrderId() { return orderId; }
    public void setOrderId(Long orderId) { this.orderId = orderId; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getAccountId() {  return accountId; }
    public void setAccountId(Long accountId) { this.accountId = accountId; }

    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }

    public Long getQuantity() { return quantity; }
    public void setQuantity(Long quantity) { this.quantity = quantity; }
}
