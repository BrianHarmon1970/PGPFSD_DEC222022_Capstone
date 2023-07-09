package com.harmonengineering.bankservice;

public class AccountWithdrawOrder extends AccountBalanceChangeOrder
        implements BankServiceOrderInterface

{
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
        _static.resources.logger.info( "Updating Resources - Transaction ID: " + TransactionID ) ;
        Double newBalance = acctRecord.getAccountBalance() - txRecord.getTxAmount() ;
        acctRecord.setAccountBalance( newBalance ) ;
        txRecord.setTxStatus("TRANSACTION_STATUS_PENDING") ;
    }
}