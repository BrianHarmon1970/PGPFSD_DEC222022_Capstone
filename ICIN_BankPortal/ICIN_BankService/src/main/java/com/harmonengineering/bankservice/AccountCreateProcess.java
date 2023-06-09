package com.harmonengineering.bankservice;
import com.harmonengineering.entity.AccountRecord;

class AccountCreateProcess extends BankServiceProcess implements BankServiceProcessInterface
{
    AccountCreateOrder interfaceOrder ;
    private AccountRecord m_newAccount ;
    AccountCreateProcess( AccountCreateOrder order ) { interfaceOrder = order ; }
    Long UserID ;
    public void assignInterfaceOrder( AccountCreateOrder ticket ) { interfaceOrder = ticket ; }
    public BankServiceOrder fulfill(BankServiceOrder order )
    {
        System.out.println( this.getClass().getName());
        assignInterfaceOrder( (AccountCreateOrder) order ) ;
        interfaceOrder.setAccountID( null )  ;
        UserID = interfaceOrder.getUserID() ;

        System.out.println( this.getClass().getName() + " UserID " + UserID );
        _static.resources.logger.info( this.getClass().getName()) ;
        _static.resources.logger.info( "Received Order, User ID: " + UserID ) ;
        processTransaction();
        return interfaceOrder ;
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

        if ( !_static.resources.getUserRepository().existsById(UserID))
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
        //AccountCapacityRecord cap = _static.resources.getCapacity( typeid ) ;
        //_static.resources.accountCaps = cap ;
        _static.resources.getCapacity( typeid ) ;

    }
    //@Override
    public void updateResources()
    {
        _static.resources.logger.info( "Updating Resources, User ID: " + UserID ) ;
        _static.resources.logger.info("Creating account record: " + UserID);

        m_newAccount = _static.resources.newAccountRecord() ;
        m_newAccount.setAccountNumber( interfaceOrder.getAccountNumber());
        m_newAccount.setAccountName(interfaceOrder.getAccountName());

        m_newAccount.setUserId(UserID);
        m_newAccount.setAccountBalance(interfaceOrder.getAccountBalance());
        m_newAccount.setAccountClassType( interfaceOrder.getAccountClassType() );

    }
    public void saveResources()
    {
        _static.resources.logger.info( "Saving Resources, User ID: " + UserID ) ;

        if ( _static.resources.getEffectiveCaps().isCanBeSubEnabled() )
            _static.resources.saveAccountRecord( m_newAccount, interfaceOrder.getMasterAccountID() ) ;
        else _static.resources.saveAccountRecord( m_newAccount ) ;

        // assign outs for caller/client/requester
        Long Id = m_newAccount.getID() ;
        interfaceOrder.setID( Id );
        interfaceOrder.setAccountID( Id ) ;
    }
    public boolean preValidate() { return true ; }
    public boolean Validate() { return true ; }
    public boolean Verify() { return true ; }
    public boolean postVerify( ) { return true ; }
}
