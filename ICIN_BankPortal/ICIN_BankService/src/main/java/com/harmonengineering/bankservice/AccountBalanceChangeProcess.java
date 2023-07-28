package com.harmonengineering.bankservice;

abstract class AccountBalanceChangeProcess extends BankServiceProcess implements BankServiceProcessInterface
{
    Long TransactionID ;

    public CAccountTransactionContext getServiceContext() { return (CAccountTransactionContext) super.getServiceContext() ; }
    AccountBalanceChangeOrder interfaceOrder ;
    AccountBalanceChangeProcess( BankServiceOrder order ) { assignInterfaceOrder( order ) ; }
    public AccountBalanceChangeProcess() {}

    public void assignInterfaceOrder( BankServiceOrder ticket )
    {
        interfaceOrder = (AccountBalanceChangeOrder) ticket ;
    }

    public BankServiceProcess manifestFactory() { return null ; }
    public void initProcess( BankServiceOrder order )
    {
        System.out.println( this.getClass().getName());
        assignInterfaceOrder( order ) ;
    }
    public boolean preValidate() { return true ; }
    public void loadResources()
    {
        getServiceContext().loadContext( interfaceOrder.getAccountId() ) ;
        TransactionID = getServiceContext().getTransaction().getID() ;
        getServiceContext().logger.info( "Loading Resources: " + TransactionID ) ;
        getServiceContext().getTransaction().setTxAmount( interfaceOrder.getTxAmount() ) ;

    }
    public void updateResources() {} // over-ridden - overloaded by subclass specialization ( withdraw and deposit )
    public void saveResources()
    {
        getServiceContext().logger.info( "Saving Resources - Transaction ID: " + TransactionID ) ;
        getServiceContext().saveContext();
    }
    public boolean  Validate() throws Exception
    {
        //_static.resources.logger.info( "Validate()" ) ;
        getServiceContext().logger.info( "Validate()" ) ;
        return true ;
    }
    public boolean  Verify()  throws Exception
    {
        getServiceContext().logger.info( "Verify()" ) ;

        if( getServiceContext().getAccount().getAccountBalance() < 0.00 )
        {
            getServiceContext().logger.info("<==== Overdraft condition detected ====>") ;
            if ( getServiceContext().getEffectiveCaps().isOverdraftLimitEnabled() )
            {
                if ( -getServiceContext().getAccount().getAccountBalance() > getServiceContext().getEffectiveCaps().getOverdraftLimit() ) {
                    getServiceContext().logger.info("RECOMMENDED ACTION: Reject Transaction") ;
                } else getServiceContext().logger.info( "RECOMMENDED ACTION: Apply Overdraft Charge: $" +
                        getServiceContext().getEffectiveCaps().getOverdraftFee()) ;
            }
            else getServiceContext().logger.info("RECOMMENDED ACTION: Reject Transaction") ;
        }
        return true ;
    }
    public boolean  postVerify() throws Exception
    {
        getServiceContext().logger.info( "postValidate()" ) ;
        return true ;
    }
}