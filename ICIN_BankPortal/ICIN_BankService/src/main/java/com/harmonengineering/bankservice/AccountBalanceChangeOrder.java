package com.harmonengineering.bankservice;

import com.harmonengineering.entity.*;

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
    AccountCapacityRecord classTypeCaps ;
    AccountCapacityRecord accountCaps ;
    AccountCapacityRecord effectiveCaps ;

    public AccountBalanceChangeOrder() {} ;
//    public void setResourceProviders( TxLogRecordRepository txRepo, AccountRecordRepository acctRepo )
//    {
//        txRepository = txRepo ;
//        acctRepository = acctRepo ;
//
//    }
    public Long getTxId() { return TransactionID; }
    public void setTxId(Long transactionID) { TransactionID = transactionID; }

    //public BankServiceOrder manifestFactory() { return null ; }
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
        // now check for over-draft condition and handle according to classtype caps
        if( acctRecord.getAccountBalance() < 0.00 )
        {
            logger.info("<==== Overdraft condition detected ====>") ;
            if ( effectiveCaps.isOverdraftLimitEnabled() )
            {
                if ( -acctRecord.getAccountBalance() > effectiveCaps.getOverdraftLimit() ) {
                    logger.info("RECOMMENDED ACTION: Reject Transaction") ;
                } else logger.info( "RECOMMENDED ACTION: Apply Overdraft Charge: $" + effectiveCaps.getOverdraftFee()) ;
            }
            else logger.info("RECOMMENDED ACTION: Reject Transaction") ;
        }
        saveResources() ;
    }
    public void loadResources()
    {
        logger.info( "Loading Resources: " + TransactionID ) ;
        txRecord = resources.txLogRecordRepository.findById( TransactionID ).orElseThrow() ;
        acctRecord = resources.accountRecordRepository.findById( txRecord.getAccountId()).orElseThrow() ;
        classTypeCaps = resources.capacityRecordRepository.findByClassTypeId( acctRecord.getAccountClassType()) ;
        accountCaps = resources.capacityRecordRepository.findByAccountId( acctRecord.getID()) ;
        effectiveCaps = ( accountCaps == null ? classTypeCaps : accountCaps ) ;
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
        resources.txLogRecordRepository.save( txRecord ) ;
        resources.accountRecordRepository.save( acctRecord ) ;
    }
}
