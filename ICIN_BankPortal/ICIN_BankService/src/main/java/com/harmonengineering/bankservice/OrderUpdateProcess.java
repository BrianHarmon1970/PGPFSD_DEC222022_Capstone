package com.harmonengineering.bankservice;

public class OrderUpdateProcess extends BankServiceProcess
    implements BankServiceProcessInterface

{
    OrderUpdateOrder interfaceOrder ;

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
    }
    public boolean Validate() throws Exception { return false; }
    public void updateResources()
    {
        serviceContext.logger.info( "OrderUpdate: updateResources()");
        serviceContext.logger.info( "newOrderStatus: " + interfaceOrder.getNewOrderStatus()) ;
    }
    public boolean Verify() throws Exception { return false; }
    public void saveResources()
    {
        serviceContext.logger.info( "OrderUpdate: saveResources()");
        serviceContext.logger.info( "prevOrderStatus: " + interfaceOrder.getPrevOrderStatus()) ;
    }
    public boolean postVerify() throws Exception {  return false;  }
}
