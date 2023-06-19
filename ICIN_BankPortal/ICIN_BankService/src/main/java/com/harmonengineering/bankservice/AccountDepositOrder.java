package com.harmonengineering.bankservice;


public class AccountDepositOrder extends BankServiceOrder
        implements BankServiceOrderInterface
{
    // account id and or account number
    // cash amount
    // check amount
    // list of checks - define check
    public BankServiceOrder manifestFactory()  { return null ; }
    public void fulfill()
    {
        System.out.println( this.getClass().getName());
    }
}
