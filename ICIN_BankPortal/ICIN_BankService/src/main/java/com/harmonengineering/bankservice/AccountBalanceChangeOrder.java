package com.harmonengineering.bankservice;

import com.harmonengineering.entity.*;

public class AccountBalanceChangeOrder extends BankServiceOrder
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

    //public BankServiceOrder manifestFactory() { return null ; }
    public void fulfill() {
        System.out.println( this.getClass().getName());
        _static.resources.logger.info( "Received Transaction ID: " + TransactionID ) ;
        processTransaction();
    }
    public void processTransaction()
    {
        _static.resources.logger.info( "Processing Transaction ID: " + TransactionID ) ;
        // get the records
        loadResources();

        updateResources() ;
        // now check for over-draft condition and handle according to classtype caps
        if( acctRecord.getAccountBalance() < 0.00 )
        {
            _static.resources.logger.info("<==== Overdraft condition detected ====>") ;
            if ( effectiveCaps.isOverdraftLimitEnabled() )
            {
                if ( -acctRecord.getAccountBalance() > effectiveCaps.getOverdraftLimit() ) {
                   _static.resources.logger.info("RECOMMENDED ACTION: Reject Transaction") ;
                } else _static.resources.logger.info( "RECOMMENDED ACTION: Apply Overdraft Charge: $" + effectiveCaps.getOverdraftFee()) ;
            }
            else _static.resources.logger.info("RECOMMENDED ACTION: Reject Transaction") ;
        }
        saveResources() ;
    }
    public void loadResources()
    {
        _static.resources.logger.info( "Loading Resources: " + TransactionID ) ;
        txRecord = _static.resources.txLogRecordRepository.findById( TransactionID ).orElseThrow() ;
        acctRecord = _static.resources.accountRecordRepository.findById( txRecord.getAccountId()).orElseThrow() ;
        classTypeCaps = _static.resources.capacityRecordRepository.findByClassTypeId( acctRecord.getAccountClassType()) ;
        accountCaps = _static.resources.capacityRecordRepository.findByAccountId( acctRecord.getID()) ;
        effectiveCaps = ( accountCaps == null ? classTypeCaps : accountCaps ) ;
    }
    public void updateResources() {};
//    {
//        logger.info( "Updating Resources - Transaction ID: " + TransactionID ) ;
//        Double newBalance = acctRecord.getAccountBalance() - txRecord.getTxAmount() ;
//        acctRecord.setAccountBalance( newBalance ) ;
//        txRecord.setTxStatus("TRANSACTION_STATUS_PENDING") ;
//    }
    public void saveResources()
    {
        _static.resources.logger.info( "Saving Resources - Transaction ID: " + TransactionID ) ;
        _static.resources.txLogRecordRepository.save( txRecord ) ;
        _static.resources.accountRecordRepository.save( acctRecord ) ;
    }
}
