package com.harmonengineering.bankservice;


public class AccountDepositOrder extends BankServiceOrder
        implements BankServiceOrderInterface
{

    // account id and or account number
    // cash amount
    // check amount
    // list of checks - define check
    private Long TransactionID ;    // decided to create the record on the client side as CREATED and allow
    // for the client to pass the transaction record ID to indicate the action was taken.
    // the summed amount is available on the record and all the account details too.

    public AccountDepositOrder() {} ;
    public Long getTxId() { return TransactionID; }
    public void setTxId(Long transactionID) { TransactionID = transactionID; }

    public BankServiceOrder manifestFactory()  { return null ; }
    public void fulfill()
    {
        System.out.println( this.getClass().getName());
        logger.info( "Received Transaction ID: " + TransactionID ) ;
    }
}
