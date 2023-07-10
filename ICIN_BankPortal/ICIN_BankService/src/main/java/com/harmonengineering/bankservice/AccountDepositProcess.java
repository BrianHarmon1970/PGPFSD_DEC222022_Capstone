package com.harmonengineering.bankservice ;

public class AccountDepositProcess extends AccountBalanceChangeProcess
        implements BankServiceProcessInterface
{
    public boolean  preValidate() throws Exception {
        _static.resources.logger.info( "Processing Transaction ID: " + TransactionID ) ;
        _static.resources.logger.info( "preVerify()" ) ;
        if ( !_static.resources.effectiveCaps.isDepositEnabled() )
            throw new Exception("Deposit capacity is not enabled on this account or account type") ;
        if ( !_static.resources.effectiveCaps.isAccountEnabled())
            throw new Exception( "This account or account type has been disabled" ) ;
        return true ;
    }
    public void updateResources()
    {
        _static.resources.logger.info( "Updating Resources - Transaction ID: " + TransactionID ) ;
        Double newBalance = _static.resources.acctRecord.getAccountBalance() + _static.resources.txRecord.getTxAmount() ;
        _static.resources.acctRecord.setAccountBalance( newBalance ) ;
        _static.resources.txRecord.setTxStatus("TRANSACTION_STATUS_PENDING") ;
    }
}