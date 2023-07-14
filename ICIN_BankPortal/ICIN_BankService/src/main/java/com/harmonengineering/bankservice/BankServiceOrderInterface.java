package com.harmonengineering.bankservice;

interface BankServiceOrderInterface
{
    BankServiceProcess manifestFactory() ;

    //void rejectTransaction() ;

}
interface BankServiceProcessInterface
{
    public BankServiceOrder fulfill( BankServiceOrder order )  ;

    public void processTransaction() ;
    public boolean preValidate() throws Exception;
    public void loadResources() ;
    public boolean Validate() throws Exception;
    public void updateResources();
    public boolean Verify() throws Exception ;
    public void saveResources() ;
    public boolean postVerify() throws Exception ;
}
