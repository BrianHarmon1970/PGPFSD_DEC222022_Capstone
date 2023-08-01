package com.harmonengineering.bankservice;

import com.harmonengineering.entity.OrderItem;
import com.harmonengineering.entity.UserOrder;

public class OrderSubmitProcess extends BankServiceProcess
    implements BankServiceProcessInterface
{
    OrderSubmitOrder interfaceOrder ;
//    orderId ; // out
//    userId ; // in or out
//    accountId ; // in
//    productId ; // in
//    quantity ;  // in

    public COrderServiceContext getServiceContext( ) { return (COrderServiceContext) super.getServiceContext() ; }
    public void initProcess(BankServiceOrder order)
    {
        interfaceOrder = (OrderSubmitOrder) order ;
    }
    public boolean preValidate() throws Exception { return false; }
    public void loadResources()
    {
        serviceContext.logger.info( "OrderSubmit: loadResources()");
        serviceContext.logger.info( "orderId: " + interfaceOrder.getOrderId()) ;
        serviceContext.logger.info( "userId: " + interfaceOrder.getUserId()) ;
        serviceContext.logger.info( "accountId: " + interfaceOrder.getAccountId()) ;
        serviceContext.logger.info( "productId: " + interfaceOrder.getProductId()) ;
        serviceContext.logger.info( "quantity: " + interfaceOrder.getQuantity()) ;
        loadContext( interfaceOrder ) ;
    }
    public void loadContext( OrderSubmitOrder so )
    {
        //managed_accountRecord.getRecordEntity().setID( so.getAccountId() ) ;
        getServiceContext().getAccount().setID( so.getAccountId() ) ;
        getServiceContext().getProductRecord().setId( so.getProductId() ) ;

        UserOrder order = new UserOrder() ;
        OrderItem item = new OrderItem() ;
        order.setAccountId( so.getAccountId() ) ;
        order.setUserId( so.getUserId() ) ;
        order = serviceContext.m_Resource.getUserOrderRepository().save( order ) ;
        getServiceContext().setUserOrderRecord( order ) ;

        item.setOrderId( order.getID() ) ;
        item.setProductId( so.getProductId() ) ;
        item.setQuantity( so.getQuantity() ) ;
        item = serviceContext.m_Resource.getOrderItemRepository().save( item ) ;
        getServiceContext().setOrderItemRecord( item ) ;

        getServiceContext().loadContext() ;
    }
    public boolean Validate() throws Exception { return false; }
    public void updateResources()
    {
        serviceContext.logger.info( "OrderSubmit: updateResources()");

    }
    public boolean Verify() throws Exception { return false; }
    public void saveResources()
    {
        serviceContext.logger.info( "OrderSubmit: saveResources()");
        // assign out params
        interfaceOrder.setID( getServiceContext().getUserOrderRecord().getID());
        interfaceOrder.setOrderId( getServiceContext().getUserOrderRecord().getID());
        interfaceOrder.setAccountId( getServiceContext().getUserOrderRecord().getAccountId());
    }
    public boolean postVerify() throws Exception {  return false;  }
}
