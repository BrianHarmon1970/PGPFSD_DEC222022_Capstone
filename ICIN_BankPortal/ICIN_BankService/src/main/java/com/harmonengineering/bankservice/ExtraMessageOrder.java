package com.harmonengineering.bankservice;

public class ExtraMessageOrder extends BankServiceOrder implements BankServiceOrderInterface
{
    public ExtraMessageOrder() {}

    private String extramessage = "" ;
    private String firstMessage ;

    public String getMessage() { return this.firstMessage ; }
    public void setMessage( String message ) { this.firstMessage = message ; }

    public String getExtra() { return this.extramessage; }
    public void setExtra( String extramessage )
    {
        this.extramessage = extramessage ;
    }
    //    public void From( BankServiceOrder order )
//    {
//        extramessage = order.getExtra() ;
//    }
    public BankServiceOrder manifestFactory() { return null ; } ;
    public void fulfill()
    {
        String type = "nothing";
        //type = bankService.getRequestType( serviceOrder ) ;
        //BankServiceOrder order =
        //        bankService.builder( serviceOrder ) ;

        System.out.println( this.getClass().getName()  );
        String exMessage = this.getExtra() ;
        String message = this.getMessage() ;
        //String message = bankService.serviceOrder( serviceOrder ) ;
        //String message = serviceOrder.getMessage() ;
        logger.info( "message : " + message + exMessage + "\ntype: " + type ) ;
        logger.info( "serviceOrder.toString(): " + this.toString()) ;
        ///logger.info( "serviceOrder.getMessage(): " + serviceOrder.getMessage()) ;
        //return serviceOrder.HelloMessage ;
        //return serviceOrder.getMessage() ;

        // logger.info( "Message from /api/bank-service: " + serviceOrder.HelloMessage ) ;
        //return message ;
    }
    public void processTransaction() {} ;
    @Override public void loadResources() {}
    @Override public void updateResources() {}
    @Override public void saveResources() { }
}
