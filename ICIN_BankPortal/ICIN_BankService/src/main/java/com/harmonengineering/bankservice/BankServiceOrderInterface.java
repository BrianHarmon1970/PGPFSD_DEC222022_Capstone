package com.harmonengineering.bankservice;

interface BankServiceOrderInterface
{
    public BankServiceOrder manifestFactory() ;
    public void fulfill() ;

    public void processTransaction() ;
    public void rejectTransaction() ;

    public boolean preVerify() ;
    public void loadResources() ;
    //public boolean verify() ;
    public void updateResources();
    //public boolean validate() ;
    public void saveResources() ;
    //public boolean postValidate() ;

}
