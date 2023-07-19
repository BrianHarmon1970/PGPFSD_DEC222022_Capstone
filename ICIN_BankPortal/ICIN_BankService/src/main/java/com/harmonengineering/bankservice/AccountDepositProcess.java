package com.harmonengineering.bankservice ;

public class AccountDepositProcess extends AccountBalanceChangeProcess
        implements BankServiceProcessInterface
{
    public boolean  Validate() throws Exception {
       // _static.resources.logger.info( "Processing Transaction ID: " + TransactionID ) ;
       // _static.resources.logger.info( "preVerify()" ) ;
        super.Validate() ;
        if ( !_static.accountTransactionContext.getEffectiveCaps().isDepositEnabled() )
            throw new Exception("Deposit capacity is not enabled on this account or account type") ;
        if ( !_static.accountTransactionContext.getEffectiveCaps().isAccountEnabled())
            throw new Exception( "This account or account type has been disabled" ) ;
        return true ;
    }
    public void updateResources()
    {
        _static.resources.logger.info( "Updating Resources - Transaction ID: " + TransactionID ) ;
        Double newBalance = _static.accountTransactionContext.getAccount().getAccountBalance() +
                _static.accountTransactionContext.getTransaction().getTxAmount() ;
        _static.accountTransactionContext.getAccount().setAccountBalance( newBalance ) ;
        _static.accountTransactionContext.getTransaction().setTxStatus("TRANSACTION_STATUS_PENDING") ;
    }
}