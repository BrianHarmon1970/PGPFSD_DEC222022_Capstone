package com.harmonengineering.bankservice;

interface BankServiceOrderInterface
{
    public BankServiceOrder manifestFactory() ;
    public void fulfill() ;

    public void processTransaction() ;

    //public void preProcess() ;
    public void loadResources() ;
    //public void verify() ;
    public void updateResources();
    //public void validate() ;
    public void saveResources() ;
    //public void postProcess() ;

}
