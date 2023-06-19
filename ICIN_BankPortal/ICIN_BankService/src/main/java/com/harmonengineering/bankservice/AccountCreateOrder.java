package com.harmonengineering.bankservice;


public class AccountCreateOrder extends BankServiceOrder
        implements BankServiceOrderInterface
{
    // user id
    // account number
    // account name
    // account type
    public BankServiceOrder manifestFactory()  { return null ; };
    public void fulfill() { System.out.println( this.getClass().getName()  );} ;
}
