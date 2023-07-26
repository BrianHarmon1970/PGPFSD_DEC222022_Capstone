package com.harmonengineering.bankservice;

public class AccountWithdrawOrder extends AccountBalanceChangeOrder
        implements BankServiceOrderInterface
{
    public AccountWithdrawOrder()
    {
        //super.balanceChangeProcess = new AccountWithdrawProcess() ;
        //super.balanceChangeProcess = _static.resources.getAccountWithdrawProcess() ;
        //super.balanceChangeProcess.assignInterfaceOrder( this ) ;
    }
}