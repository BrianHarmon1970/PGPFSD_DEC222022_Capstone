package com.harmonengineering.bankservice;

public class OrderUpdateOrder extends  BankServiceOrder
    implements BankServiceOrderInterface
{
    private Long orderId ;
    private Long userId ;
    private Long accountId ;
    private String orderStatus ;
    private String newOrderStatus ;
    private String prevOrderStatus ;

    public OrderUpdateOrder() {}

    public Long getOrderId() { return orderId; }
    public void setOrderId(Long orderId) { this.orderId = orderId; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getAccountId() { return accountId; }
    public void setAccountId(Long accountId) { this.accountId = accountId; }

    public String getOrderStatus() { return orderStatus; }
    public void setOrderStatus(String orderStatus) { this.orderStatus = orderStatus; }

    public String getNewOrderStatus() { return newOrderStatus; }
    public void setNewOrderStatus(String newOrderStatus) { this.newOrderStatus = newOrderStatus; }

    public String getPrevOrderStatus() { return prevOrderStatus; }
    public void setPrevOrderStatus(String prevOrderStatus) { this.prevOrderStatus = prevOrderStatus; }
}
