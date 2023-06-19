package com.harmonengineering.bankservice;

public class AccountWithdrawOrder extends BankServiceOrder
        implements BankServiceOrderInterface

{
    // account_id and or account number
    // cash amount for withdraw
    public BankServiceOrder manifestFactory() { return null ; }
    public void fulfill() { System.out.println( this.getClass().getName());}
}