package com.harmonengineering.bankservice;

import com.harmonengineering.entity.*;
import org.slf4j.Logger;
import org.springframework.data.domain.Example;
import java.util.UUID;

class CManagedEntity<ENTITY_T>
{
    UUID RES_ID ;
    ResourceManager rm ;
    EntityResource<ENTITY_T> recordResource ;
    //ENTITY_T    recordEntity ;
    public CManagedEntity() { }
    ManagedResource getResource() { return rm.getResource(RES_ID) ;}
    ENTITY_T getRecordEntity() { return recordResource.getEntity() ; }
    public void setRecordEntity( ENTITY_T record ) { recordResource.setEntity( record ) ;}

    ENTITY_T getEntity() { return recordResource.getEntity() ; }
    public void setEntity( ENTITY_T record ) { recordResource.setEntity( record ) ;}

    public EntityResource<ENTITY_T> getRecordResource() { return recordResource ; }
    public void  setRecordResource(EntityResource<ENTITY_T> er ) { recordResource = er ; }
    public void Install( ResourceManager rm )
    {
        this.rm = rm ;
        recordResource = new EntityResource<ENTITY_T>() ;
        RES_ID = rm.AddManagedResource( recordResource ) ;

    }
    void UUID( UUID uuid ) { RES_ID = uuid ; }
    UUID UUID() { return RES_ID ; }
}

class ManagedAccountRecord extends  CManagedEntity<AccountRecord> {}
class ManagedAccountCapacityRecord extends CManagedEntity<AccountCapacityRecord>{}
class ManagedAccountTransactionRecord extends CManagedEntity<TxLogRecord>{}
class ManagedMasterTransactionRecord extends CManagedEntity<MasterTransactionRecord>{}

class CManagedRepository<REPOSITORY_T>
{
    UUID RES_ID ;
    ResourceManager rm ;
    RepositoryResource<REPOSITORY_T> repositoryResource ;
    //ENTITY_T    recordEntity ;
    public CManagedRepository() { repositoryResource = new RepositoryResource<REPOSITORY_T>() ; }
    ManagedResource getResource() { return rm.getResource(RES_ID) ;}
    REPOSITORY_T getRepository() { return repositoryResource.getRepository() ; }
    public void setRepository( REPOSITORY_T repo ) { repositoryResource.setRepository( repo ) ;}

    public RepositoryResource<REPOSITORY_T> getRepositoryResource() { return repositoryResource ; }
    public void  setRepositoryResource(RepositoryResource<REPOSITORY_T> rr ) { repositoryResource = rr ; }
    public void Install( ResourceManager rm )
    {
        this.rm = rm ;
        repositoryResource = new RepositoryResource<REPOSITORY_T>() ;
        RES_ID = rm.AddManagedResource( repositoryResource ) ;
    }
    void UUID( UUID uuid ) { RES_ID = uuid ; }
    UUID UUID() { return RES_ID ; }
}
class ManagedAccountRepository extends  CManagedRepository<AccountRecordRepository> {}
class ManagedAccountCapacityRepository extends CManagedRepository<AccountCapacityRecordRepository>{}
class ManagedAccountTransactionRepository extends CManagedRepository<TxLogRecordRepository>{}

class ManagedUserRepository extends CManagedRepository<UserRepository> {}
class ManagedAccountClassTypeRepository extends CManagedRepository<AccountClassTypeRecordRepository>{}
class ManagedAccountMasterSubLinkRepository extends CManagedRepository<AccountMasterSubLinkRecordRepository>{}

class ManagedMasterTransactionRepository extends CManagedRepository<MasterTransactionRecordRepository> {}

class CManagedProcess< PROCESS_T >
{
    UUID RES_ID ;
    ResourceManager rm ;
    ProcessResource<PROCESS_T> processResource ;
    //ENTITY_T    recordEntity ;
    public CManagedProcess() {}
    ManagedResource getResource() { return rm.getResource(RES_ID) ;}
    PROCESS_T getProcess() { return processResource.getProcessor() ; }
    public void setProcess( PROCESS_T proc ) { processResource.setProcessor( proc ); }

    public ProcessResource<PROCESS_T> getProcessResource() { return processResource ; }
    public void  setRepositoryResource( ProcessResource<PROCESS_T> pr ) { processResource = pr ; }
    public void Install( ResourceManager rm )
    {
        this.rm = rm ;
        processResource = new ProcessResource<PROCESS_T>() ;
        RES_ID = rm.AddManagedResource( processResource ) ;
    }
    void UUID( UUID uuid ) { RES_ID = uuid ; }
    UUID UUID() { return RES_ID ; }
}

class ManagedAccountCreateProcess extends CManagedProcess<AccountCreateProcess> {}
class ManagedAccountWithdrawProcess extends CManagedProcess<AccountWithdrawProcess> {}
class ManagedAccountDepositProcess extends CManagedProcess<AccountDepositProcess> {}
class ManagedAccountTransferProcess extends CManagedProcess<AccountTransferProcess> {}

class TManagedResource< RESOURCE_T >
{
//    UUID RES_ID ;
//    ResourceManager rm ;
//    RepositoryResource<REPOSITORY_T> repositoryResource ;
//    //ENTITY_T    recordEntity ;
//    public CManagedRepository() {}
//    ManagedResource getResource() { return rm.getResource(RES_ID) ;}
//    REPOSITORY_T getRepository() { return repositoryResource.getRepository() ; }
//    public void setRepository( REPOSITORY_T repo ) { repositoryResource.setRepository( repo ) ;}
//
//    public RepositoryResource<REPOSITORY_T> getRepositoryResource() { return repositoryResource ; }
//    public void  setRepositoryResource(RepositoryResource<REPOSITORY_T> rr ) { repositoryResource = rr ; }
//    public void Install( ResourceManager rm )
//    {
//        this.rm = rm ;
//        repositoryResource = new RepositoryResource<REPOSITORY_T>() ;
//        RES_ID = rm.AddManagedResource( repositoryResource ) ;
//    }
//    void UUID( UUID uuid ) { RES_ID = uuid ; }
//    UUID UUID() { return RES_ID ; }
}


/*-----------------------------------------------------------------------------------------------
 *** CBankServiceContext ***
 */
interface IBankServiceContext
{
    void loadContext() ;
    void installManagedResources() ;
    void saveContext() ;
}
abstract class CBankServiceContext extends CProcessContext implements IBankServiceContext
{
    //========================================================================
    BankServiceResources m_Resource ;
    Logger logger ;
    //========================================================================
    public CBankServiceContext( BankServiceResources rm )
    {
        m_Resource = rm ;
        logger = m_Resource.logger ;
    }
//    public void loadContext() {}
//    public void installManagedResources() {}
//    public void saveContext() {}
//    public CBankServiceContext() {}
    public void printCapacityRecord( AccountCapacityRecord cap )
    {
        logger = m_Resource.logger ;
        logger.info( cap.getIdTagname() + ": " + "can be master -> " + cap.isCanBeMasterEnabled()) ;
        logger.info( cap.getIdTagname() + ": " + "can be sub -> " + cap.isCanBeSubEnabled()) ;
        logger.info( cap.getIdTagname() + ": " + "account fee enabled: " + cap.isAccountFeeEnabled()) ;
        logger.info( cap.getIdTagname() + ": " + "Checking -> " + cap.isCheckingEnabled()) ;
        logger.info( cap.getIdTagname() + ": " + "Check Limit -> " + cap.isCheckLimitEnabled()) ;
        logger.info( cap.getIdTagname() + ": " + "Interest enabled -> " + cap.isInterestEnabled()) ;
        logger.info( cap.getIdTagname() + ": " + "Overdraft Limit enabled -> " + cap.isOverdraftLimitEnabled()) ;

        logger.info( cap.getIdTagname() + ": " + "Account Enabled -> " + cap.isAccountEnabled())  ;
        logger.info( cap.getIdTagname() + ": " + "Withdraw Enabled -> " + cap.isWithdrawEnabled())  ;
        logger.info( cap.getIdTagname() + ": " + "Deposit Enabled -> " + cap.isDepositEnabled() )  ;
        logger.info( cap.getIdTagname() + ": " + "Transfer Enabled -> " + cap.isTransferEnabled())  ;
        logger.info( cap.getIdTagname() + ": " + "IntraAccount Transfer Enabled -> " + cap.isIntraAccountTransferEnabled()) ;
        logger.info( cap.getIdTagname() + ": " + "InterAccount Transfer Enabled -> " + cap.isInterAccountTransferEnabled())  ;
        logger.info( cap.getIdTagname() + ": " + "InterBank Transfer Enabled -> " + cap.isInterBankTransferEnabled())  ;

        if ( cap.isAccountFeeEnabled() ) logger.info( "Account fee:\t" +  cap.getAccountFee() );
        if ( cap.isCheckLimitEnabled() ) logger.info( "Check Limit:\t" +  cap.getCheckLimit() );
        if ( cap.isInterestEnabled() ) logger.info( "Interest Rate:\t" +  cap.getInterestRate() );
        if ( cap.isOverdraftLimitEnabled())
        {
            logger.info("Overdraft Limit:\t" + cap.getOverdraftLimit());
            logger.info("Overdraft Fee:\t" + cap.getOverdraftFee());
        }
    }
}

/*-----------------------------------------------------------------------------------------------
 *** CBankTransactionContext ***
 */
class CBankTransactionContext extends CBankServiceContext
{
    ManagedAccountRecord managed_accountRecord = new ManagedAccountRecord() ;
    ManagedAccountCapacityRecord managed_capacityRecord = new ManagedAccountCapacityRecord() ;

    public CBankTransactionContext( BankServiceResources rm ) { super( rm ) ; }

    AccountRecord getAccount() { return managed_accountRecord.getRecordEntity() ; }
    void setAccount( AccountRecord r ) { managed_accountRecord.setRecordEntity( r ) ; }

    AccountCapacityRecord getAccountCapacity() { return managed_capacityRecord.getEntity() ; }
    void setAccountCapacity( AccountCapacityRecord r ) { managed_capacityRecord.setEntity( r ) ; }

    AccountCapacityRecord getEffectiveCaps() { return getAccountCapacity() ; }

    void loadAccountContext( Long AcctId ) {  }
    public void loadContext() {  }
    public void saveContext()
    {
         m_Resource.getAccountRepository().save(managed_accountRecord.getEntity()) ;
    }
    public void installManagedResources()
    {
        ResourceManager resourceManager = m_Resource.resourceManager;

        managed_accountRecord.Install( resourceManager ) ;
        managed_capacityRecord.Install( resourceManager ) ;

        setAccount( new AccountRecord() ) ;
        setAccountCapacity( new AccountCapacityRecord() ) ;
    }
}

/*-----------------------------------------------------------------------------------------------
 *** CAccountCreateContext ***
 */
class CAccountCreateContext extends CBankServiceContext
{

    ManagedAccountRecord managed_masterAccountRecord = new ManagedAccountRecord() ;
    ManagedAccountRecord managed_accountRecord = new ManagedAccountRecord() ;
    ManagedAccountCapacityRecord managed_capacityRecord = new ManagedAccountCapacityRecord() ;

    AccountRecord getAccount() { return managed_accountRecord.getRecordEntity() ; }
    void setAccount( AccountRecord r ) { managed_accountRecord.setRecordEntity( r ) ; }

    AccountCapacityRecord getAccountCapacity() { return managed_capacityRecord.getRecordEntity() ; }
    void setAccountCapacity( AccountCapacityRecord r ) { managed_capacityRecord.setRecordEntity( r ) ; }

    CAccountCreateContext( BankServiceResources rm ) { super(rm) ; }

    public void loadContext() {}
    public void installManagedResources()
    {
        ResourceManager rm = m_Resource.resourceManager ;

        managed_accountRecord.Install( rm )   ;
        managed_capacityRecord.Install( rm ) ;

        setAccount( new AccountRecord() );
        setAccountCapacity( new AccountCapacityRecord() );
    }
    AccountRecord newAccountRecord( )
    {
        setAccount( new AccountRecord() ) ;
        getAccount().setID(null) ;
        return getAccount() ;
    }
    public void saveContext()
    {
        m_Resource.getAccountRepository().save( getAccount() ) ;
    }
    AccountRecord  saveAccountRecord( AccountRecord account )
    {
        m_Resource.getAccountRepository().save( account ) ;
        account = updateAccountMaster( account, account ) ;
        return account ;
    }
    AccountRecord saveAccountRecord( AccountRecord account, Long MasterId  )
    {
        account = saveAccountRecord( account ) ;
        account = updateAccountSub( account, MasterId ) ;
        return account ;
    }
    AccountRecord updateAccountMaster( AccountRecord to, AccountRecord from )
    {
        if ( this.getEffectiveCaps().isCanBeMasterEnabled() )
            this.assignMasterSub(to, from.getID());
        return to ;
    }
    AccountRecord updateAccountSub( AccountRecord account, Long MasterId )
    {
        if ( this.getEffectiveCaps().isCanBeSubEnabled() )
            this.assignMasterSub( account, MasterId ) ;
        return account ;
    }
    public AccountMasterSubLinkRecord assignMasterSub(  AccountRecord sub, Long masterId )
    {
        AccountMasterSubLinkRecord link = new AccountMasterSubLinkRecord() ;
        link.setID( null ) ;
        link.setMasterAccountID( masterId ) ;
        link.setSubAccountID( sub.getID() ) ;
        return m_Resource.getAccountMasterSublinkRepository().save( link ) ;
    }
    // data access utility ...
    // tagname -> classtype id
    // at this time need to derive the id of classtype from the passed string value(s)
    Long resolveTypeId( String typeTag )
    {
        AccountClassTypeRecord r = new AccountClassTypeRecord();
        r.setIdTagname( typeTag );
        Example<AccountClassTypeRecord> example = Example.of(r);
        r = m_Resource.getAccountClassTypeRepository().findOne(example).orElseThrow();
        return r.getAccountClassTypeId() ;
    }
    // classtypeId -> capacityRecord
    AccountCapacityRecord getCapacityFromTypeId( Long typeId )
    {

        AccountCapacityRecord cap = m_Resource.getCapacityRepository().findByClassTypeId( typeId ) ;
        printCapacityRecord( cap ) ;
        setAccountCapacity( cap );

        return cap ;

    }
    AccountCapacityRecord getEffectiveCaps()
    {
        //printCapacityRecord( getAccountCapacity() );
        return getAccountCapacity() ;
    }
}

/*-----------------------------------------------------------------------------------------------
 *** CAccountTransactionContext ***
 */
class CAccountTransactionContext extends CBankTransactionContext
{
    final private ManagedAccountTransactionRecord managed_txRecord = new ManagedAccountTransactionRecord() ;

    CAccountTransactionContext( BankServiceResources rm ) { super(rm) ; }

    void loadTransactionContext( Long TxID )
    {
        TxLogRecord txRecord ;
        AccountRecord acctRecord ;
        AccountCapacityRecord capacityRecord ;

        txRecord = m_Resource.getTransactionLogRepository().findById( TxID ).orElseThrow() ;
        Long AcctId = txRecord.getAccountId() ;
        acctRecord = m_Resource.getAccountRepository().findById( AcctId ).orElseThrow() ;
        setAccount( acctRecord ) ;
        setTransaction(txRecord);
        capacityRecord = findEffectiveCaps( ) ;
        setAccountCapacity( capacityRecord ) ;
    }

    void loadAccountContext( Long AcctId )
    {
        AccountRecord acctRecord ; // = new AccountRecord();
        AccountCapacityRecord capacityRecord ;

        acctRecord = m_Resource.getAccountRepository().findById( AcctId ).orElseThrow() ;
        setAccount( acctRecord ) ;
        capacityRecord = findEffectiveCaps( ) ;
        setAccountCapacity( capacityRecord ) ;
    }

    //AccountCapacityRecord findEffectiveCaps( Long AcctID )
    AccountCapacityRecord findEffectiveCaps( )
    {
        AccountCapacityRecord effectiveCaps ;
        AccountCapacityRecord accountCaps ;
        AccountCapacityRecord classTypeCaps ;

        classTypeCaps = m_Resource.getCapacityRepository().findByClassTypeId( getAccount().getAccountClassType()) ;
        accountCaps = m_Resource.getCapacityRepository().findByAccountId( getAccount().getID() ) ;
        effectiveCaps = ( accountCaps == null ? classTypeCaps : accountCaps ) ;
        return effectiveCaps ;
    }

    public void saveContext()
    {
        //super.saveContext();
        m_Resource.getTransactionLogRepository().save( getTransaction() ) ;
        m_Resource.getAccountRepository().save( getAccount() ) ;
    }

    public void installManagedResources()
    {
        super.installManagedResources();
        ResourceManager resourceManager = m_Resource.resourceManager;

        managed_txRecord.Install( resourceManager ) ;
        setTransaction( new TxLogRecord()) ;
    }

    TxLogRecord getTransaction() { return managed_txRecord.getEntity() ; }
    //AccountRecord getAccount() { return managed_acctRecord.getEntity() ; }
    //AccountCapacityRecord getAccountCapacity() { return managed_CapsRecord.getEntity() ; }

    void setTransaction( TxLogRecord tx ) { managed_txRecord.setEntity( tx ) ;}
    //void setAccount( AccountRecord acct ) { managed_acctRecord.setEntity( acct ) ;}
    //void setAccountCapacity( AccountCapacityRecord cp ) { managed_CapsRecord.setEntity( cp ) ;}
}

class CAccountDualTransactionContext extends CBankTransactionContext
{
    CAccountTransactionContext primaryTxContext ;
    CAccountTransactionContext secondaryTxContext ;
    ManagedMasterTransactionRecord managed_masterTxRecord ;

    public CAccountDualTransactionContext(BankServiceResources rm) {
        super(rm);
    }
    public void loadContext( Long primaryAccountId, Long secondaryAccountId )
    {
        TxLogRecord newPrimaryTxRecord = new TxLogRecord() ;
        TxLogRecord newSecondaryTxRecord = new TxLogRecord() ;
        newPrimaryTxRecord.setAccountId( primaryAccountId ) ;
        newSecondaryTxRecord.setAccountId( secondaryAccountId ) ;

        newPrimaryTxRecord.setTxAmount( 0.00 ) ; // initially set to zero to bypass non null constraint...
        newSecondaryTxRecord.setTxAmount( 0.00 ) ;

        newPrimaryTxRecord.setTxStatus( "TRANSACTION_STATUS_RECORDCREATED" ) ; // initially set to zero to bypass non null constraint...
        newSecondaryTxRecord.setTxStatus( "TRANSACTION_STATUS_RECORDCREATED" ) ;

        newPrimaryTxRecord.setTxType( "TRANSFER" ) ; // initially set to zero to bypass non null constraint...
        newSecondaryTxRecord.setTxType( "TRANSFER" ) ;


        newPrimaryTxRecord = m_Resource.getTransactionLogRepository().save( newPrimaryTxRecord ) ;
        newSecondaryTxRecord = m_Resource.getTransactionLogRepository().save( newSecondaryTxRecord ) ;

        primaryTxContext.loadTransactionContext( newPrimaryTxRecord.getID() ) ;
        secondaryTxContext.loadTransactionContext( newSecondaryTxRecord.getID() ) ;

        MasterTransactionRecord mtx = new MasterTransactionRecord() ;
        mtx.setMasterAccountId( primaryAccountId ) ;
        mtx.setPrimaryTransactionId( newPrimaryTxRecord.getID() ) ;
        mtx.setSecondaryTransactionId(newSecondaryTxRecord.getID() ) ;
        mtx = m_Resource.getMasterTransactionRepository().save( mtx ) ;
        setMasterTransaction( mtx ) ;
    }
    public void saveContext()
    {
        //super.saveContext();
        primaryTxContext.saveContext();
        secondaryTxContext.saveContext();
        m_Resource.getMasterTransactionRepository().save( getMasterTransaction() ) ;
    }

    public void installManagedResources()
    {
        super.installManagedResources();

        primaryTxContext = new CAccountTransactionContext( m_Resource ) ;
        secondaryTxContext = new CAccountTransactionContext( m_Resource ) ;
        primaryTxContext.installManagedResources();
        secondaryTxContext.installManagedResources();

        ResourceManager resourceManager = m_Resource.resourceManager;

        managed_masterTxRecord = new ManagedMasterTransactionRecord() ;
        managed_masterTxRecord.Install( resourceManager ) ;
        setMasterTransaction( new MasterTransactionRecord() ) ;
    }
    MasterTransactionRecord getMasterTransaction() { return managed_masterTxRecord.getEntity() ; }
    void setMasterTransaction( MasterTransactionRecord tx ) { managed_masterTxRecord.setEntity( tx ) ;}

    TxLogRecord getPrimaryTransaction() { return primaryTxContext.getTransaction() ; }
    void setPrimaryTransaction( TxLogRecord tx ) { primaryTxContext.setTransaction( tx ) ;}

    TxLogRecord getSecondaryTransaction() { return secondaryTxContext.getTransaction() ; }
    void setSecondaryTransaction( TxLogRecord tx ) { secondaryTxContext.setTransaction( tx ) ;}

    AccountRecord getPrimaryAccount() { return primaryTxContext.getAccount() ; }
    void setPrimaryAccount( AccountRecord r ) { primaryTxContext.setAccount( r ) ;}

    AccountRecord getSeconcdaryAccount() { return secondaryTxContext.getAccount() ; }
    void setSecondaryAccount( AccountRecord r ) { secondaryTxContext.setAccount( r ) ;}



}

/*-----------------------------------------------------------------------------------------------
*** BankServiceResources ***
 */
 public class BankServiceResources //extends CBankServiceContext
{
    Logger logger ;

    ResourceManager resourceManager ;
    ManagedAccountRepository managed_accountRepository = new ManagedAccountRepository() ;
    ManagedAccountTransactionRepository managed_txRepository = new ManagedAccountTransactionRepository() ;
    ManagedAccountCapacityRepository managed_capacityRepository = new ManagedAccountCapacityRepository() ;
    ManagedUserRepository managed_userRepository = new ManagedUserRepository() ;
    ManagedAccountClassTypeRepository managed_classTypeRepository = new ManagedAccountClassTypeRepository() ;
    ManagedAccountMasterSubLinkRepository managed_masterSubLinkRepository = new ManagedAccountMasterSubLinkRepository() ;
    ManagedMasterTransactionRepository managed_masterTransactionRepository = new ManagedMasterTransactionRepository() ;

    ManagedAccountCreateProcess managed_createProcess = new ManagedAccountCreateProcess() ;
    ManagedAccountWithdrawProcess managed_withdrawProcess = new ManagedAccountWithdrawProcess() ;
    ManagedAccountDepositProcess managed_depositProcess = new ManagedAccountDepositProcess() ;
    ManagedAccountTransferProcess managed_transferProcess = new ManagedAccountTransferProcess() ;



//    BankServiceResources( Logger logger, TxLogRecordRepository txRepo,
//                         AccountRecordRepository acctRepo,
//                         UserRepository userRepo,
//                         AccountClassTypeRecordRepository classTypeRepo,
//                         AccountCapacityRecordRepository capacityRepo,
//                         AccountMasterSubLinkRecordRepository linkRepo,
//                          MasterTransactionRecordRepository mtRepo ) {
//        setResourceProviders(logger, txRepo, acctRepo, userRepo, classTypeRepo, capacityRepo, linkRepo, mtRepo );
//    }
    //BankServiceResources() {}

    void setResourceProviders(Logger logger, TxLogRecordRepository txRepo,
                              AccountRecordRepository acctRepo,
                              UserRepository userRepo,
                              AccountClassTypeRecordRepository classTypeRepo,
                              AccountCapacityRecordRepository capacityRepo,
                              AccountMasterSubLinkRecordRepository linkRepo,
                              MasterTransactionRecordRepository mtRepo ) {
        this.logger = logger ;
        resourceManager = new BankResourceManager() ;
        installManagedResources();
//
//        TxLogRecordRepositoryResource txRepoResource = new TxLogRecordRepositoryResource() ;
//        AccountRecordRepositoryResource accountRepoResource = new AccountRecordRepositoryResource() ;
//        AccountCapacityRecordRepositoryResource capacityRepoResource = new AccountCapacityRecordRepositoryResource() ;

//        managed_accountRepository.setRepositoryResource( accountRepoResource ) ;
//        managed_txRepository.setRepositoryResource( txRepoResource ) ;
//        managed_capacityRepository.setRepositoryResource( capacityRepoResource ) ;


//        ProcessResource<AccountCreateProcess> acctCreateProc = new ProcessResource<>(new AccountCreateProcess(null)) ;
//        ProcessResource<AccountDepositProcess> acctDepositProc = new ProcessResource<>( new AccountDepositProcess()) ;
//        ProcessResource<AccountWithdrawProcess> acctWithdrawProc = new ProcessResource<>( new AccountWithdrawProcess()) ;


        managed_accountRepository.setRepository( acctRepo ) ;
        managed_txRepository.setRepository( txRepo ) ;
        managed_capacityRepository.setRepository( capacityRepo ) ;

        managed_userRepository.setRepository( userRepo ) ;
        managed_classTypeRepository.setRepository( classTypeRepo ) ;
        managed_masterSubLinkRepository.setRepository( linkRepo ) ;

        managed_masterTransactionRepository.setRepository( mtRepo ) ;

        managed_createProcess.setProcess( new AccountCreateProcess(null) ) ;
        managed_withdrawProcess.setProcess( new AccountWithdrawProcess() ) ;
        managed_depositProcess.setProcess( new AccountDepositProcess() ) ;
        managed_transferProcess.setProcess( new AccountTransferProcess() ) ;

//        // Master Transaction Repository and Record(s)
//        // work to do here...
//        _static.accountTransactionContext = new CAccountTransactionContext( this ) ;
//        _static.accountTransactionContext.installManagedResources();
//
//        _static.createContext = new CAccountCreateContext( this ) ;
//        _static.createContext.installManagedResources() ;

        logger.info( "TXREPO_ID: " + managed_txRepository.UUID() ) ;
        logger.info( "ACCTREPO_ID: " + managed_accountRepository.UUID() ) ;
        logger.info( "CAPREPO_ID: " + managed_capacityRepository.UUID() ) ;

        logger.info( "ACCTCREATE_ID: " + managed_createProcess.UUID() ) ;
        logger.info( "ACCTDEPOSIT_ID: " + managed_depositProcess.UUID() ) ;
        logger.info( "ACCTWITHDRAW_ID: " + managed_withdrawProcess.UUID() ) ;
        logger.info( "ACCTTRANSVER_ID: " + managed_transferProcess.UUID() ) ;

       // runResourceTest() ;
    }
    void runResourceTest()
    {
        logger.info( "User7 firstName: " + managed_userRepository.getRepository().findById( 7L ).orElseThrow().getFirstName()) ;
        logger.info( "User repository: " + managed_userRepository.getRepository().toString()) ;
        logger.info( "MasterSubLink repository: " + managed_masterSubLinkRepository.getRepository().toString()) ;
        logger.info( "ClassType repository: " + managed_classTypeRepository.getRepository().toString()) ;
    }

    TxLogRecordRepository getTransactionLogRepository()     { return managed_txRepository.getRepository() ; }
    AccountRecordRepository getAccountRepository()          { return managed_accountRepository.getRepository() ; }
    AccountCapacityRecordRepository getCapacityRepository() { return managed_capacityRepository.getRepository() ; }
    UserRepository getUserRepository()                      { return managed_userRepository.getRepository() ;}

    AccountClassTypeRecordRepository getAccountClassTypeRepository()
    { return managed_classTypeRepository.getRepository() ; }

    AccountMasterSubLinkRecordRepository getAccountMasterSublinkRepository()
    { return managed_masterSubLinkRepository.getRepository() ; }

    MasterTransactionRecordRepository getMasterTransactionRepository()
    { return managed_masterTransactionRepository.getRepository() ; }

    AccountCreateProcess getAccountCreateProcess()    { return managed_createProcess.getProcess() ; }
    AccountDepositProcess getAccountDepositProcess() { return managed_depositProcess.getProcess() ; }
    AccountWithdrawProcess getAccountWithdrawProcess() { return managed_withdrawProcess.getProcess() ; }
    AccountTransferProcess getAccountTransferProcess() { return managed_transferProcess.getProcess() ; }

    public void loadContext() { }
    public void saveContext() { }
    public void installManagedResources()
    {
        managed_accountRepository.Install( resourceManager ) ;
        managed_capacityRepository.Install( resourceManager ) ;
        managed_txRepository.Install( resourceManager ) ;
        managed_classTypeRepository.Install( resourceManager ) ;
        managed_userRepository.Install( resourceManager ) ;
        managed_masterSubLinkRepository.Install( resourceManager ) ;
        managed_masterTransactionRepository.Install( resourceManager ) ;

        managed_createProcess.Install( resourceManager ) ;
        managed_withdrawProcess.Install( resourceManager ) ;
        managed_depositProcess.Install( resourceManager ) ;
        managed_transferProcess.Install( resourceManager ) ;
    }
}

