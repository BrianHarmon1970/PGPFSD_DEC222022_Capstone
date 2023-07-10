package com.harmonengineering.bankservice;

import com.harmonengineering.entity.*;

abstract public class AccountBalanceChangeOrder extends BankServiceOrder
{
    Long TransactionID ;    // decided to create the record on the client side as CREATED and allow
    // for the client to pass the transaction record ID to indicate the action was taken.
    // the summed amount is available on the record and all the account details too.

    // resources account and transaction
    TxLogRecord txRecord ;
    AccountRecord acctRecord ;

    AccountCapacityRecord classTypeCaps ;
    AccountCapacityRecord accountCaps ;
    AccountCapacityRecord effectiveCaps ;

    public AccountBalanceChangeOrder() {} ;
    public Long getTxId() { return TransactionID; }
    public void setTxId(Long transactionID) { TransactionID = transactionID; }

    AccountBalanceChangeProcess balanceChangeProcess ; //= new AccountBalanceChangeProcess( this ) ;
    public BankServiceProcess manifestFactory() { return balanceChangeProcess ; }
    public void fulfill() {
        System.out.println( this.getClass().getName());
        _static.resources.logger.info( "Received Transaction ID: " + TransactionID ) ;
        balanceChangeProcess.assignInterfaceOrder( this ) ;
        balanceChangeProcess.fulfill();
    }
}


