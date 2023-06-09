package com.harmonengineering.bankservice;

public class AccountWithdrawProcess extends AccountBalanceChangeProcess
        implements BankServiceProcessInterface
{
    public boolean  Validate() throws Exception {
        //_static.resources.logger.info( "Processing Transaction ID: " + TransactionID ) ;
        //_static.resources.logger.info( "preVerify()" ) ;
        super.Validate() ;
        if ( !_static.resources.getEffectiveCaps().isWithdrawEnabled() )
            throw new Exception("Withdraw capacity is not enabled on this account or account type") ;
        if ( !_static.resources.getEffectiveCaps().isAccountEnabled())
            throw new Exception( "This account or account type has been disabled." ) ;
        return true ;
    }
    public void updateResources()
    {
        _static.resources.logger.info( "Updating Resources - Transaction ID: " + TransactionID ) ;
        Double newBalance = _static.resources.getAccount().getAccountBalance() - _static.resources.getTransaction().getTxAmount() ;
        _static.resources.getAccount().setAccountBalance( newBalance ) ;
        _static.resources.getTransaction().setTxStatus("TRANSACTION_STATUS_PENDING") ;
    }
}
