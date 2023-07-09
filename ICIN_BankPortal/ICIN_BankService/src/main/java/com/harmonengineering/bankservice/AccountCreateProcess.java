package com.harmonengineering.bankservice;


import com.harmonengineering.entity.AccountCapacityRecord;
import com.harmonengineering.entity.AccountRecord;

class AccountCreateProcess extends BankServiceProcess //implements BankServiceOrderInterface
{
    AccountCreateOrder interfaceOrder ;
    private AccountRecord m_newAccount ;
    AccountCreateProcess( AccountCreateOrder order ) { interfaceOrder = order ; }
    Long UserID ;
    public void assignInterfaceOrder( AccountCreateOrder ticket ) { interfaceOrder = ticket ; }
    public void fulfill()
    {
        System.out.println( this.getClass().getName());
        interfaceOrder.setAccountID( null )  ;
        UserID = interfaceOrder.getUserID() ;

        System.out.println( this.getClass().getName() + " UserID " + UserID );
        _static.resources.logger.info( this.getClass().getName()) ;
        _static.resources.logger.info( "Received Order, User ID: " + UserID ) ;
        processTransaction();
    } ;
    public void processTransaction()
    {
        System.out.println( "proccesTransaction" ) ;
        _static.resources.logger.info( "Processing User ID: " + UserID ) ;

        // begin transaction
        // get the records
        loadResources();
        // validate verify - check compatibility and capabilities etc..
        updateResources() ;
        saveResources() ;
        // commit transaction
        // or catch rollback transaction.
    } ;
    //@Override
    public void loadResources()
    {
        _static.resources.logger.info( "Loading Resources, User ID: " + UserID ) ;

        // presently only need to verify that the user exists.
        // should also check whether the user can create account...
        // potentially want to validate the authority of the requesting user
        // as well.

        //if ( !userRepository.existsById( UserID ))

        if ( !_static.resources.userRepository.existsById(UserID))
            throw new RuntimeException( "UserID does not Exist in AccountCreate (service order): UserID - " + UserID )  ;
//        UserRepositoryResource userRepoRsrc = new UserRepositoryResource() ;
//        userRepoRsrc.setRepository(resources.userRepository);
//        if ( !userRepoRsrc.getRepository().existsById(UserID) )
//            throw new RuntimeException( "UserID does not Exist in AccountCreate (service order): UserID - " + UserID )  ;

        // at this time need to derive the id of classtype from the passed string value(s)
        Long typeid = _static.resources.resolveTypeId( interfaceOrder.getAccountClassTypeTag() ) ;
        interfaceOrder.setAccountClassType( typeid ) ;

        // now that have the classtype and the capabilites resource
        // get the caps and verify the transaction validity and account capacity for it.
        /// perhaps here for  now just get it and print it's values on the log
        AccountCapacityRecord cap = _static.resources.getCapacity( typeid ) ;
        //AccountCapacityRecord cap = resources.capacityRecordRepository.findByClassTypeId( interfaceOrder.getAccountClassType()) ;
//        logger.info( cap.getIdTagname() + ": " + "can be master -> " + cap.isCanBeMasterEnabled()) ;
//        logger.info( cap.getIdTagname() + ": " + "can be sub -> " + cap.isCanBeSubEnabled()) ;
//        logger.info( cap.getIdTagname() + ": " + "account fee enabled: " + cap.isAccountFeeEnabled()) ;
//        logger.info( cap.getIdTagname() + ": " + "Checking -> " + cap.isCheckingEnabled()) ;
//        logger.info( cap.getIdTagname() + ": " + "Check Limit -> " + cap.isCheckLimitEnabled()) ;
//        logger.info( cap.getIdTagname() + ": " + "Interest enabled -> " + cap.isInterestEnabled()) ;
//        logger.info( cap.getIdTagname() + ": " + "Overdraft Limit enabled -> " + cap.isOverdraftLimitEnabled()) ;
//
//        logger.info( cap.getIdTagname() + ": " + "Account Enabled -> " + cap.isAccountEnabled())  ;
//        logger.info( cap.getIdTagname() + ": " + "Withdraw Enabled -> " + cap.isWithdrawEnabled())  ;
//        logger.info( cap.getIdTagname() + ": " + "Deposit Enabled -> " + cap.isDepositEnabled() )  ;
//        logger.info( cap.getIdTagname() + ": " + "Transfer Enabled -> " + cap.isTransferEnabled())  ;
//        logger.info( cap.getIdTagname() + ": " + "IntraAccount Transfer Enabled -> " + cap.isIntraAccountTransferEnabled()) ;
//        logger.info( cap.getIdTagname() + ": " + "InterAccount Transfer Enabled -> " + cap.isInterAccountTransferEnabled())  ;
//        logger.info( cap.getIdTagname() + ": " + "InterBank Transfer Enabled -> " + cap.isInterBankTransferEnabled())  ;

//        if ( cap.isAccountFeeEnabled() ) logger.info( "Account fee: " +  cap.getAccountFee() );
//        if ( cap.isCheckLimitEnabled() ) logger.info( "Check Limit: " +  cap.getCheckLimit() );
//        if ( cap.isInterestEnabled() ) logger.info( "Interest Rate: " +  cap.getInterestRate() );
//        if ( cap.isOverdraftLimitEnabled()) {
//            logger.info("Overdraft Limit" + cap.getOverdraftLimit());
//            logger.info("Overdraft Fee" + cap.getOverdraftFee());
//        }
        _static.resources.accountCaps = cap ;
    }
    //@Override
    public void updateResources()
    {
        _static.resources.logger.info( "Updating Resources, User ID: " + UserID ) ;
        _static.resources.logger.info("Creating account record: " + UserID);

        //m_newAccount = new AccountRecord();
        //m_newAccount = resources.acctRecord ;
        //m_newAccount = resources.m_newAccountRecord() ;
        m_newAccount = _static.resources.newAccountRecord() ;
        m_newAccount.setAccountNumber( interfaceOrder.getAccountNumber());
        m_newAccount.setAccountName(interfaceOrder.getAccountName());
        //m_newAccount.setAccountClass( this.AccountClass ) ;
        //m_newAccount.setAccountType(this.AccountType);
        m_newAccount.setUserId(UserID);
        m_newAccount.setAccountBalance(interfaceOrder.getAccountBalance());
        m_newAccount.setAccountClassType( interfaceOrder.getAccountClassType() );
//        if ( cap.isCanBeSubEnabled() ) // i need to get this resource directly
//            currnty only can query based on what is in the () -> table
//            now i need to accociate new in that same toble.
        // ok now we have it from the user...
//        if ( resources.accountCaps.isCanBeSubEnabled() )
//            resources.assignMasterSub( m_newAccount, interfaceOrder.getMasterAccountID() ) ;
    }
    //@Override
    public void saveResources()
    {
        _static.resources.logger.info( "Saving Resources, User ID: " + UserID ) ;
        //m_newAccount = resources.accountRecordRepository.save( m_newAccount ) ;
        //resources.saveAccountRecord( m_newAccount ) ;

        if ( _static.resources.accountCaps.isCanBeSubEnabled() )
            _static.resources.saveAccountRecord( m_newAccount, interfaceOrder.getMasterAccountID() ) ;
        else _static.resources.saveAccountRecord( m_newAccount ) ;

        // assign outs for caller/client/requester
        Long Id = m_newAccount.getID() ;
        interfaceOrder.setID( Id );
        interfaceOrder.setAccountID( Id ) ;

        // also need to assign new master account to the master/sub table as master of itself
        // this establishes it as a master account ( can only be done after acquiring an ID value)
//        if ( resources.accountCaps.isCanBeMasterEnabled() )
//            resources.assignMasterSub( m_newAccount, m_newAccount.getID() ) ;
//        if ( resources.accountCaps.isCanBeSubEnabled() )
//            resources.assignMasterSub( m_newAccount, interfaceOrder.getMasterAccountID() ) ;

//        System.out.println( "this.ID " + interfaceOrder.getID() ) ;
//        System.out.println( "this.accountID " + interfaceOrder.getAccountID() ) ;
    }
}
