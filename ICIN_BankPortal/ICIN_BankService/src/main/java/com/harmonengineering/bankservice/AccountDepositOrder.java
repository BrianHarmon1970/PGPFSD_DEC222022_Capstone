package com.harmonengineering.bankservice;

public class AccountDepositOrder extends AccountBalanceChangeOrder
        implements BankServiceOrderInterface
{
    public void updateResources()
    {
        _static.resources.logger.info( "Updating Resources - Transaction ID: " + TransactionID ) ;
        Double newBalance = acctRecord.getAccountBalance() + txRecord.getTxAmount() ;
        acctRecord.setAccountBalance( newBalance ) ;
        txRecord.setTxStatus("TRANSACTION_STATUS_PENDING") ;

    }
}