package com.harmonengineering.bankservice;


import com.harmonengineering.entity.TxLogRecord;

abstract class AccountBalanceChangeProcess extends BankServiceProcess implements BankServiceProcessInterface
{
    Long TransactionID ;    // decided to create the record on the client side as CREATED and allow
    // for the client to pass the transaction record ID to indicate the action was taken.
    // the summed amount is available on the record and all the account details too.

    //public Long getTxId() { return TransactionID; }
    //public void setTxId(Long transactionID) { TransactionID = ((AccountBalanceChangeOrder) interfaceOrder).TransactionID; }

    BankServiceOrder interfaceOrder ;
    AccountBalanceChangeProcess( BankServiceOrder order ) { assignInterfaceOrder( order ) ; }
    public AccountBalanceChangeProcess() {}

    //Long UserID ;

    public void assignInterfaceOrder( BankServiceOrder ticket )
    {
        interfaceOrder = ticket ;
        TransactionID = ((AccountBalanceChangeOrder) interfaceOrder).TransactionID ;
    }

    public BankServiceProcess manifestFactory() { return null ; }
    public BankServiceOrder fulfill( BankServiceOrder order ) {
        System.out.println( this.getClass().getName());
        assignInterfaceOrder( order ) ;
        //TxLogRecord txRecord ;
        _static.resources.logger.info( "Received Transaction ID: " + TransactionID ) ;
        //txRecord = _static.resources.loadTxContext( TransactionID ) ;

        _static.resources.logger.info( "TXID: " + TransactionID ) ;
        //_static.resources.logger.info( "ACCTID: " + txRecord.getAccountId() ) ;

        processTransaction();
        return interfaceOrder ;
    }
    //    public void processTransaction()
//    {
//        preValidate() ;
//        // get the records
//        loadResources();
//        Validate()  ;
//        updateResources() ;
//        Verify() ;
//        saveResources() ;
//        postVerify() ;
//    }
    public boolean preValidate() { return true ; }
    public void loadResources()
    {
        _static.resources.logger.info( "Loading Resources: " + TransactionID ) ;
        _static.accountTransactionContext.loadTransactionContext( TransactionID ) ;
        //TxLogRecord txRecord ;
        //AccountRecord acctRecord ;

        // moved to pre-validate as the configuration values were needed to determine whether the process was
        // enabled for the exeucution of withdraw/deposit
        // no longer necessary to be checked in pre-validate.. moved to Validate.. however the data is already loaded

        //txRecord = _static.resources.loadTransactionRecord( TransactionID ) ;
        //acctRecord = _static.resources.loadAccountRecord( txRecord.getAccountId()) ;
    }
    public void updateResources() {} // over-ridden - overloaded by subclass specialization ( withdraw and deposit )
    public void saveResources()
    {
        _static.resources.logger.info( "Saving Resources - Transaction ID: " + TransactionID ) ;
        _static.accountTransactionContext.saveContext();
    }
    //    public boolean  preValidate() throws Exception {
//        _static.resources.logger.info( "Processing Transaction ID: " + TransactionID ) ;
//        _static.resources.logger.info( "preVerify()" ) ;
//        if ( !_static.resources.effectiveCaps.isDepositEnabled() )
//            throw new Exception("This account not enabled for Deposit") ;
//        if ( !_static.resources.effectiveCaps.isAccountEnabled())
//            throw new Exception( "Account Status: not enabled" ) ;
//        return true ;
//    }
    public boolean  Validate() throws Exception
    {
        _static.resources.logger.info( "Validate()" ) ;
        return true ;
    }
    public boolean  Verify()  throws Exception
    {
        _static.resources.logger.info( "Verify()" ) ;
        // now check for over-draft condition and handle according to classtype caps
        if( _static.accountTransactionContext.getAccount().getAccountBalance() < 0.00 )
        {
            _static.resources.logger.info("<==== Overdraft condition detected ====>") ;
            if ( _static.accountTransactionContext.getEffectiveCaps().isOverdraftLimitEnabled() )
            {
                if ( -_static.accountTransactionContext.getAccount().getAccountBalance() > _static.accountTransactionContext.getEffectiveCaps().getOverdraftLimit() ) {
                    _static.resources.logger.info("RECOMMENDED ACTION: Reject Transaction") ;
                } else _static.resources.logger.info( "RECOMMENDED ACTION: Apply Overdraft Charge: $" +
                        _static.accountTransactionContext.getEffectiveCaps().getOverdraftFee()) ;
            }
            else _static.resources.logger.info("RECOMMENDED ACTION: Reject Transaction") ;
        }
        return true ;
    }
    public boolean  postVerify() throws Exception
    {
        _static.resources.logger.info( "postValidate()" ) ;
        return true ;
    }
}