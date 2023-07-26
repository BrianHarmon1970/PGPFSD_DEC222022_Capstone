package com.harmonengineering.bankservice;
import com.harmonengineering.entity.AccountRecord;

class AccountCreateProcess extends BankServiceProcess implements BankServiceProcessInterface
{
    AccountCreateOrder interfaceOrder ;
    private AccountRecord m_newAccount ;
    AccountCreateProcess( AccountCreateOrder order ) { interfaceOrder = order ; }
    Long UserID ;
    public void assignInterfaceOrder( AccountCreateOrder ticket ) { interfaceOrder = ticket ; }
    public void initProcess( BankServiceOrder order )
    {
        System.out.println( this.getClass().getName());
        assignInterfaceOrder( (AccountCreateOrder) order ) ;
        interfaceOrder.setAccountID( null )  ;
        UserID = interfaceOrder.getUserID() ;

        System.out.println( this.getClass().getName() + " UserID " + UserID );
//        _static.resources.logger.info( this.getClass().getName()) ;
//        _static.resources.logger.info( "Received Order, User ID: " + UserID ) ;

        getServiceContext().logger.info( this.getClass().getName()) ;
        getServiceContext().logger.info( "Received Order, User ID: " + UserID ) ;

    }
//    public BankServiceOrder fulfill(BankServiceOrder order )
//    {
//        initProcess( order ) ;
//        processTransaction();
//        return interfaceOrder ;
//    } ;
//    public void processTransaction()
//    {
//        System.out.println( "proccesTransaction" ) ;
//        //_static.resources.logger.info( "Processing User ID: " + UserID ) ;
//        getServiceContext().m_Resource.logger.info( "Processing User ID: " + UserID ) ;
//
//        // begin transaction
//        // get the records
//        loadResources();
//        // validate verify - check compatibility and capabilities etc..
//        updateResources() ;
//        saveResources() ;
//        // commit transaction
//        // or catch rollback transaction.
//    } ;
    public CAccountCreateContext getServiceContext() { return (CAccountCreateContext) super.getServiceContext() ; }
    //@Override
    public void loadResources()
    {
        //_static.resources.logger.info( "Loading Resources, User ID: " + UserID ) ;
        getServiceContext().logger.info( "Loading Resources, User ID: " + UserID );

        // presently only need to verify that the user exists.
        // should also check whether the user can create account...
        // potentially want to validate the authority of the requesting user
        // as well.

        //if ( !_static.resources.getUserRepository().existsById(UserID))
        if ( !getServiceContext().m_Resource.getUserRepository().existsById(UserID))
            throw new RuntimeException( "UserID does not Exist in AccountCreate (service order): UserID - " + UserID )  ;
//        UserRepositoryResource userRepoRsrc = new UserRepositoryResource() ;
//        userRepoRsrc.setRepository(resources.userRepository);
//        if ( !userRepoRsrc.getRepository().existsById(UserID) )
//            throw new RuntimeException( "UserID does not Exist in AccountCreate (service order): UserID - " + UserID )  ;

        // at this time need to derive the id of classtype from the passed string value(s)
        //Long typeid = _static.createContext.resolveTypeId( interfaceOrder.getAccountClassTypeTag() ) ;
        Long typeid = getServiceContext().resolveTypeId( interfaceOrder.getAccountClassTypeTag() ) ;
        System.out.println( "typeid: " + typeid ) ;
        interfaceOrder.setAccountClassType( typeid ) ; /// out variable - returned to client

        // now that have the classtype and the capabilites resource
        // get the caps and verify the transaction validity and account capacity for it.
        /// perhaps here for  now just get it and print it's values on the log
        //AccountCapacityRecord cap = _static.resources.getCapacity( typeid ) ;
        //_static.resources.accountCaps = cap ;
        //_static.createContext.getCapacityFromTypeId( typeid ) ;

        getServiceContext().getCapacityFromTypeId( typeid ) ;


    }
    //@Override
    public void updateResources()
    {
        //_static.resources.logger.info( "Updating Resources, User ID: " + UserID ) ;
        //_static.resources.logger.info("Creating account record: " + UserID);

        getServiceContext().logger.info( "Updating Resources, User ID: " + UserID ) ;
        getServiceContext().logger.info("Creating account record: " + UserID);


        //m_newAccount = _static.createContext.newAccountRecord() ;
        m_newAccount = getServiceContext().newAccountRecord() ;
        m_newAccount.setAccountNumber( interfaceOrder.getAccountNumber()); // out variable
        m_newAccount.setAccountName(interfaceOrder.getAccountName()); // out variable

        m_newAccount.setUserId(UserID);
        m_newAccount.setAccountBalance(interfaceOrder.getAccountBalance());         // out
        m_newAccount.setAccountClassType( interfaceOrder.getAccountClassType() );   // out

    }
    public void saveResources()
    {
        //_static.resources.logger.info( "Saving Resources, User ID: " + UserID ) ;

        //if ( _static.createContext.getEffectiveCaps().isCanBeSubEnabled() )
        //    _static.createContext.saveAccountRecord( m_newAccount, interfaceOrder.getMasterAccountID() ) ;
        //else _static.createContext.saveAccountRecord( m_newAccount ) ;


        getServiceContext().logger.info( "Saving Resources, User ID: " + UserID ) ;

        if ( getServiceContext().getEffectiveCaps().isCanBeSubEnabled() )
            getServiceContext().saveAccountRecord( m_newAccount, interfaceOrder.getMasterAccountID() ) ;
        else getServiceContext().saveAccountRecord( m_newAccount ) ;



        // assign outs for caller/client/requester
        Long Id = m_newAccount.getID() ;
        interfaceOrder.setID( Id );  // out variable
        interfaceOrder.setAccountID( Id ) ; // out variable
    }
    public boolean preValidate() { return true ; }
    public boolean Validate() { return true ; }
    public boolean Verify() { return true ; }
    public boolean postVerify( ) { return true ; }
}
