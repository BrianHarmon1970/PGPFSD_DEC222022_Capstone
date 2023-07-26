package com.harmonengineering.bankservice;

import javax.transaction.Transactional;

abstract public class BankServiceProcess
    implements BankServiceProcessInterface {
    private String type;
    protected Long ID;

    CBankServiceContext serviceContext ;

    public BankServiceProcess() { }

//    public void fulfill() {
//        System.out.println(this.getClass().getName());
//        System.out.println("BTW, I'm Super!!!");
//        throw new RuntimeException("BankServiceOrder - super - not overloaded by sub");
//    }
    public BankServiceOrder fulfill(BankServiceOrder order )
    {
        initProcess( order ) ;
        processTransaction();
        return order ;
    } ;
    //protected void rejectTransaction() throws Exception { throw new Exception("Unhandled TxRejection" ) ; }
    @Transactional public void processTransaction()
    {
        try
        {
            preValidate();
            loadResources();
            Validate();
            updateResources();
            Verify();
            saveResources();
            postVerify();
        }
        catch ( Exception e )
        {
            //_static.resources.logger.warn( "Caught exception: " + e.getMessage() ) ;
            serviceContext.logger.warn( "Caught exception: " + e.getMessage() ) ;
        }
    }

    public CBankServiceContext getServiceContext() {  return serviceContext; }
    public void setServiceContext( CBankServiceContext ctx ) { serviceContext = ctx ; }
    //    public void rejectTransaction() {}
}

