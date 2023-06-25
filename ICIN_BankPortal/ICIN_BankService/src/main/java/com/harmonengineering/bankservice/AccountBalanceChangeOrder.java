package com.harmonengineering.bankservice;

import com.harmonengineering.entity.AccountRecord;
import com.harmonengineering.entity.AccountRecordRepository;
import com.harmonengineering.entity.TxLogRecord;
import com.harmonengineering.entity.TxLogRecordRepository;

public class AccountBalanceChangeOrder extends BankServiceOrder
{
    Long TransactionID ;    // decided to create the record on the client side as CREATED and allow
    // for the client to pass the transaction record ID to indicate the action was taken.
    // the summed amount is available on the record and all the account details too.

    // resources account and transaction
  //  TxLogRecordRepository txRepository ;
  //  AccountRecordRepository acctRepository ;
    TxLogRecord txRecord ;
    AccountRecord acctRecord ;

    public AccountBalanceChangeOrder() {} ;
//    public void setResourceProviders( TxLogRecordRepository txRepo, AccountRecordRepository acctRepo )
//    {
//        txRepository = txRepo ;
//        acctRepository = acctRepo ;
//
//    }
    public Long getTxId() { return TransactionID; }
    public void setTxId(Long transactionID) { TransactionID = transactionID; }

    public BankServiceOrder manifestFactory() { return null ; }
    public void fulfill() {
        System.out.println( this.getClass().getName());
        logger.info( "Received Transaction ID: " + TransactionID ) ;
        processTransaction();
    }
    public void processTransaction()
    {
        logger.info( "Processing Transaction ID: " + TransactionID ) ;

        // get the records
        loadResources();
        updateResources() ;
        saveResources() ;

        //if ( newBalance >= 0 )
        {


        }
    }
    public void loadResources()
    {
        logger.info( "Loading Resources: " + TransactionID ) ;
        txRecord = txLogRecordRepository.findById( TransactionID ).orElseThrow() ;
        acctRecord = accountRecordRepository.findById( txRecord.getAccountId()).orElseThrow() ;
    }
    public void updateResources() {};
//    {
//        logger.info( "Updating Resources - Transaction ID: " + TransactionID ) ;
//        Double newBalance = acctRecord.getAccountBalance() - txRecord.getTxAmount() ;
//        acctRecord.setAccountBalance( newBalance ) ;
//        txRecord.setTxStatus("TRANSACTION_STATUS_PENDING") ;
//
//    }
    public void saveResources()
    {
        logger.info( "Saving Resources - Transaction ID: " + TransactionID ) ;
        txLogRecordRepository.save( txRecord ) ;
        accountRecordRepository.save( acctRecord ) ;
    }
}
