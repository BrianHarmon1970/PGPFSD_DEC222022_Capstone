package com.harmonengineering.bankservice;

public class AccountWithdrawProcess extends AccountBalanceChangeProcess
        implements BankServiceProcessInterface
{
    public boolean  Validate() throws Exception {
        super.Validate() ;
        if ( !getServiceContext().getEffectiveCaps().isWithdrawEnabled() )
            throw new Exception("Withdraw capacity is not enabled on this account or account type") ;
        if ( !getServiceContext().getEffectiveCaps().isAccountEnabled())
            throw new Exception( "This account or account type has been disabled." ) ;
        return true ;
    }
    public void updateResources()
    {
        getServiceContext().logger.info( "Updating Resources - Transaction ID: " + TransactionID ) ;
        Double newBalance = getServiceContext().getAccount().getAccountBalance() -
                getServiceContext().getTransaction().getTxAmount() ;
        getServiceContext().getAccount().setAccountBalance( newBalance ) ;
        getServiceContext().getTransaction().setTxStatus("TRANSACTION_STATUS_PENDING") ;
        getServiceContext().getTransaction().setTxType("WITHDRAW");
    }
}
