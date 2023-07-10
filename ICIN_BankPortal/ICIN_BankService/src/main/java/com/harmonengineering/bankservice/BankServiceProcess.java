package com.harmonengineering.bankservice;

abstract public class BankServiceProcess
    implements BankServiceProcessInterface {
    private String type;
    protected Long ID;

    public BankServiceProcess() { }

    public void fulfill() {
        System.out.println(this.getClass().getName());
        System.out.println("BTW, I'm Super!!!");
        throw new RuntimeException("BankServiceOrder - super - not overloaded by sub");
    }
    //protected void rejectTransaction() throws Exception { throw new Exception("Unhandled TxRejection" ) ; }
    public void processTransaction()
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
            _static.resources.logger.warn( "Caught exception: " + e.getMessage() ) ;

        }
    }
//    public void rejectTransaction() {}
//    public boolean preVerify() { return true ; }


}

