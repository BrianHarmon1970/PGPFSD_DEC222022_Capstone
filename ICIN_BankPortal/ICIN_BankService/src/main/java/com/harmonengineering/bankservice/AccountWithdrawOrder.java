package com.harmonengineering.bankservice;

import com.harmonengineering.entity.AccountRecord;
import com.harmonengineering.entity.AccountRecordRepository;
import com.harmonengineering.entity.TxLogRecord;
import com.harmonengineering.entity.TxLogRecordRepository;
import org.slf4j.Logger;

import java.util.Optional;

public class AccountWithdrawOrder extends AccountBalanceChangeOrder
        implements BankServiceOrderInterface

{
    // account_id and or account number
    // cash amount for withdraw
//    private Long TransactionID ;    // decided to create the record on the client side as CREATED and allow
//                                    // for the client to pass the transaction record ID to indicate the action was taken.
//                                    // the summed amount is available on the record and all the account details too.
//
//    // resources account and transaction
//    TxLogRecordRepository txRepository ;
//    AccountRecordRepository acctRepository ;
//    TxLogRecord txRecord ;
//    AccountRecord acctRecord ;

//
//    public AccountWithdrawOrder() {} ;
//    public void setResourceProviders( TxLogRecordRepository txRepo, AccountRecordRepository acctRepo )
//    {
//        txRepository = txRepo ;
//        acctRepository = acctRepo ;
//
//    }
//    public Long getTxId() { return TransactionID; }
//    public void setTxId(Long transactionID) { TransactionID = transactionID; }
//
//    public BankServiceOrder manifestFactory() { return null ; }
//    public void fulfill() {
//        System.out.println( this.getClass().getName());
//        logger.info( "Received Transaction ID: " + TransactionID ) ;
//        processTransaction();
//    }
//    void processTransaction()
//    {
//        logger.info( "Processing Transaction ID: " + TransactionID ) ;
//
//        // get the records
//        loadResources();
//        updateResources() ;
//        saveResources() ;
//
//        //if ( newBalance >= 0 )
//        {
//
//
//        }
//    }
//    void loadResources()
//    {
//        logger.info( "Loading Resources: " + TransactionID ) ;
//        txRecord = txRepository.findById( TransactionID ).orElseThrow() ;
//        acctRecord = acctRepository.findById( txRecord.getAccountId()).orElseThrow() ;
//    }
    public boolean preVerify()
    {
        // need to check capacities and reject (return false) if not authorized
        return true ;
    }
    public void updateResources()
    {
        logger.info( "Updating Resources - Transaction ID: " + TransactionID ) ;
        Double newBalance = acctRecord.getAccountBalance() - txRecord.getTxAmount() ;
        acctRecord.setAccountBalance( newBalance ) ;
        txRecord.setTxStatus("TRANSACTION_STATUS_PENDING") ;

    }
//    void saveResources()
//    {
//        logger.info( "Saving Resources - Transaction ID: " + TransactionID ) ;
//        txRepository.save( txRecord ) ;
//        acctRepository.save( acctRecord ) ;
//    }
}