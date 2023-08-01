package com.harmonengineering.bankservice;

import com.harmonengineering.entity.OrderItem;
import com.harmonengineering.entity.UserOrder;
import org.springframework.data.domain.Example;

import java.util.List;
import java.util.Objects;

public class OrderUpdateProcess extends BankServiceProcess
    implements BankServiceProcessInterface

{
    OrderUpdateOrder interfaceOrder ;

    public COrderServiceContext getServiceContext( ) { return (COrderServiceContext) super.getServiceContext() ; }
    public void initProcess(BankServiceOrder order)
    {
        interfaceOrder = (OrderUpdateOrder) order ;

    }
    public boolean preValidate() throws Exception { return false; }
    public void loadResources()
    {
        serviceContext.logger.info( "OrderUpdate: loadResources()");
        serviceContext.logger.info( "orderId: " + interfaceOrder.getOrderId()) ;
        serviceContext.logger.info( "userId: " + interfaceOrder.getUserId()) ;
        serviceContext.logger.info( "accountId: " + interfaceOrder.getAccountId()) ;
        //serviceContext.logger.info( "productId: " + interfaceOrder.getProductId()) ;
        //serviceContext.logger.info( "quantity: " + interfaceOrder.getQuantity()) ;
        serviceContext.logger.info( "orderStatus: " + interfaceOrder.getOrderStatus()) ;
        loadContext( interfaceOrder ) ;
    }
    public void loadContext( OrderUpdateOrder uo )
    {
        getServiceContext().getAccount().setID( uo.getAccountId() ) ;

        OrderItem item = new OrderItem() ;
        item.setOrderId( uo.getOrderId() ) ;
        Example<OrderItem> example = Example.of( item ) ;
        List<OrderItem> list = getServiceContext().m_Resource.getOrderItemRepository().findAll( example ) ;
        item = list.get( 0 ) ;

        getServiceContext().getUserOrderRecord().setID( item.getOrderId() ) ;
        getServiceContext().getProductRecord().setId( item.getProductId()) ;
        getServiceContext().setOrderItemRecord( item ) ;

        getServiceContext().loadContext() ;
    }
    public boolean Validate() throws Exception
    {
        if(!Objects.equals(getServiceContext().getUserOrderRecord().getAccountId(), getServiceContext().getAccount().getID()))
            throw new Exception( "AccountId != Order AccountId" ) ;
        return true ;
    }
    public void updateResources()
    {
        serviceContext.logger.info( "OrderUpdate: updateResources()");
        serviceContext.logger.info( "newOrderStatus: " + interfaceOrder.getNewOrderStatus()) ;
        UserOrder order = getServiceContext().getUserOrderRecord();
        interfaceOrder.setPrevOrderStatus( order.getOrderStatus() );
        order.setOrderStatus(interfaceOrder.getNewOrderStatus());
    }
    public boolean Verify() throws Exception { return false; }
    public void saveResources()
    {
        serviceContext.logger.info( "OrderUpdate: saveResources()");
        serviceContext.logger.info( "prevOrderStatus: " + interfaceOrder.getPrevOrderStatus()) ;
        serviceContext.saveContext();
        // assign out params
        interfaceOrder.setID( getServiceContext().getUserOrderRecord().getID());
        interfaceOrder.setOrderId( getServiceContext().getUserOrderRecord().getID());
        interfaceOrder.setOrderStatus( getServiceContext().getUserOrderRecord().getOrderStatus());
        interfaceOrder.setAccountId( getServiceContext().getUserOrderRecord().getAccountId());
    }
    public boolean postVerify() throws Exception {  return false;  }
}
