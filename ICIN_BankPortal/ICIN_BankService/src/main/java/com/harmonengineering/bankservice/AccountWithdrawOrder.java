package com.harmonengineering.bankservice;

import org.slf4j.Logger;

public class AccountWithdrawOrder extends BankServiceOrder
        implements BankServiceOrderInterface

{
    // account_id and or account number
    // cash amount for withdraw
    private Long TransactionID ;    // decided to create the record on the client side as CREATED and allow
                                    // for the client to pass the transaction record ID to indicate the action was taken.
                                    // the summed amount is available on the record and all the account details too.
    public AccountWithdrawOrder() {} ;
    public Long getTxId() { return TransactionID; }
    public void setTxId(Long transactionID) { TransactionID = transactionID; }

    public BankServiceOrder manifestFactory() { return null ; }
    public void fulfill() {
        System.out.println( this.getClass().getName());
        logger.info( "Received Transaction ID: " + TransactionID ) ;
    }
}