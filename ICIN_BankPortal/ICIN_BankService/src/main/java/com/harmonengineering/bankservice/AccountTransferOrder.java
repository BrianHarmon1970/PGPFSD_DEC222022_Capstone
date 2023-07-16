package com.harmonengineering.bankservice;

public class AccountTransferOrder extends AccountBalanceChangeOrder
        implements BankServiceOrderInterface
{
    @Override
    public BankServiceProcess manifestFactory() {
        return new AccountTransferProcess() ;
    }
}
