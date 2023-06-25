package com.harmonengineering.bankservice;

interface BankServiceOrderInterface
{
    public BankServiceOrder manifestFactory() ;
    public void fulfill() ;
    public void processTransaction() ;
    public void loadResources() ;
    public void updateResources();
    public void saveResources() ;

}
