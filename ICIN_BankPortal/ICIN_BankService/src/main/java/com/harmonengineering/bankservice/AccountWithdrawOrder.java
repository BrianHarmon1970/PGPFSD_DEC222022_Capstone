package com.harmonengineering.bankservice;

public class AccountWithdrawOrder extends AccountBalanceChangeOrder
        implements BankServiceOrderInterface
{
    public AccountWithdrawOrder()
    {
        super.balanceChangeProcess = new AccountWithdrawProcess() ;
        super.balanceChangeProcess.assignInterfaceOrder( this ) ;
    }
}