package com.harmonengineering.bankservice;

import javax.transaction.Transactional;

abstract public class BankServiceProcess
    implements BankServiceProcessInterface {
    private String type;
    protected Long ID;

    CBankServiceContext serviceContext ;
    public BankServiceProcess() { }
    public BankServiceOrder fulfill(BankServiceOrder order )
    {
        initProcess( order ) ;
        try { processTransaction(); }
        catch ( Exception e ) { serviceContext.logger.warn( "Caught exception: " + e.getMessage() ) ; }
        return order ;
    } ;
    //protected void rejectTransaction() throws Exception { throw new Exception("Unhandled TxRejection" ) ; }
    @Transactional public void processTransaction() throws Exception {
//        try
//        {
            preValidate();
            loadResources();
            Validate();
            updateResources();
            Verify();
            saveResources();
            postVerify();
//        }
//        catch ( Exception e )
//        {
//            //_static.resources.logger.warn( "Caught exception: " + e.getMessage() ) ;
//            serviceContext.logger.warn( "Caught exception: " + e.getMessage() ) ;
//        }
    }
    public CBankServiceContext getServiceContext() {  return serviceContext; }
    public void setServiceContext( CBankServiceContext ctx ) { serviceContext = ctx ; }
    //    public void rejectTransaction() {}
}

