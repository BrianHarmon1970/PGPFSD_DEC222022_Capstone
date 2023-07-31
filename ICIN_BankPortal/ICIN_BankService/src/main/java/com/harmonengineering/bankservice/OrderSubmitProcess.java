package com.harmonengineering.bankservice;

public class OrderSubmitProcess extends BankServiceProcess
    implements BankServiceProcessInterface
{
    OrderSubmitOrder interfaceOrder ;

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

    }    public boolean postVerify() throws Exception {  return false;  }
}
