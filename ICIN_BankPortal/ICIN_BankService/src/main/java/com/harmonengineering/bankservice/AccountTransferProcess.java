package com.harmonengineering.bankservice;

public class AccountTransferProcess extends BankServiceProcess
        implements BankServiceProcessInterface
{
    AccountTransferOrder interfaceOrder ;

    public void assignInterfaceOrder( AccountTransferOrder ticket )
    {      interfaceOrder = ticket ;    }

    public CAccountDualTransactionContext getServiceContext() { return (CAccountDualTransactionContext) super.getServiceContext() ; }
    @Override public void initProcess( BankServiceOrder order )
    {
        getServiceContext().logger.info("account-transfer service:") ;
        assignInterfaceOrder( (AccountTransferOrder) order ) ;
    }
    public boolean preValidate() throws Exception {
        if ( !getServiceContext().m_Resource.getAccountRepository().existsById( interfaceOrder.primaryAccountId ) )
            throw new Exception( "Invalid primary account id." ) ;
        if ( !getServiceContext().m_Resource.getAccountRepository().existsById( interfaceOrder.secondaryAccountId ) )
            throw new Exception( "Invalid secondary account id." ) ;
        if ( !getServiceContext().m_Resource.getAccountRepository().existsById( interfaceOrder.masterAccountId ) )
            throw new Exception( "Invalid master account id." ) ;
        return true ;
    }
    public void loadResources()
    {
        getServiceContext().logger.info( "Loading resources..." ) ;
        getServiceContext().loadContext( interfaceOrder.getPrimaryAccountId() , interfaceOrder.getSecondaryAccountId() ) ;
        getServiceContext().getPrimaryTransaction().setTxAmount( interfaceOrder.getTransferAmount() ) ;
        getServiceContext().getSecondaryTransaction().setTxAmount( interfaceOrder.getTransferAmount() ) ;
    }
    public boolean Validate() throws Exception
    {
        if ( !getServiceContext().primaryTxContext.getAccountCapacity().isAccountEnabled())
            throw new Exception( "Primary account or account type has been disabled." ) ;
        if ( !getServiceContext().secondaryTxContext.getAccountCapacity().isAccountEnabled())
            throw new Exception( "Secondary account or account type has been disabled." ) ;

        if ( !getServiceContext().primaryTxContext.getAccountCapacity().isTransferEnabled() )
            throw new Exception( "Primary account or account type does not allow Transfers") ;
        if ( !getServiceContext().secondaryTxContext.getAccountCapacity().isTransferEnabled() )
            throw new Exception( "Secondary account or account type does not allow Transfers") ;

        return true ;
    }
    public void updateResources()
    {
        getServiceContext().logger.info( "Updating resources..." ) ;
        getServiceContext().getPrimaryTransaction().setTxStatus("TRANSACTION_STATUS_PENDING");
        getServiceContext().getSecondaryTransaction().setTxStatus("TRANSACTION_STATUS_PENDING");
        getServiceContext().getPrimaryTransaction().setTxType("TRANSFER_OUT") ;
        getServiceContext().getSecondaryTransaction().setTxType("TRANSFER_IN");

        Double primaryBalance = getServiceContext().getPrimaryAccount().getAccountBalance() ;
        Double secondaryBalance = getServiceContext().getSeconcdaryAccount().getAccountBalance() ;

        primaryBalance -= interfaceOrder.getTransferAmount() ;
        secondaryBalance += interfaceOrder.getTransferAmount() ;

        if ( primaryBalance < 0 )
        {
            getServiceContext().logger.info( "Insufficient funds for transfer" ) ;
            getServiceContext().logger.info( "suggested action: Reject Transaction" ) ;
        }
        getServiceContext().getPrimaryAccount().setAccountBalance( primaryBalance ) ;
        getServiceContext().getSeconcdaryAccount().setAccountBalance( secondaryBalance )  ;

        getServiceContext().getPrimaryTransaction().setTxDescription( "TRANSFER TO " +
                getServiceContext().getSeconcdaryAccount().getAccountNumber() ) ;
        getServiceContext().getSecondaryTransaction().setTxDescription( "TRANSFER FROM " +
                getServiceContext().getPrimaryAccount().getAccountNumber() ) ;
    }
    public boolean Verify() throws Exception {  return true ;  }
    public void saveResources()
    {
        getServiceContext().logger.info( "Saving resources..." ) ;
        getServiceContext().saveContext() ;
        getServiceContext().saveContext() ;
        // outs
        interfaceOrder.setPrimaryTxId( getServiceContext().getMasterTransaction().getPrimaryTransactionId() ) ;
        interfaceOrder.setSecondaryTxId( getServiceContext().getMasterTransaction().getSecondaryTransactionId() );
        interfaceOrder.setId( getServiceContext().getMasterTransaction().getID() ); ;
    }
    public boolean postVerify() throws Exception
    {
        //throw new Exception("invalidate") ;
        return true ;
    }
}
