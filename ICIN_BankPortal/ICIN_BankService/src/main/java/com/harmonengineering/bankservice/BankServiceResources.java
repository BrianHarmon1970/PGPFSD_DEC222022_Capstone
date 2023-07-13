package com.harmonengineering.bankservice;

import com.harmonengineering.entity.*;
import org.slf4j.Logger;
import org.springframework.data.domain.Example;

import java.util.UUID;

public class BankServiceResources extends BankResourceManager
{
    //private UserRepository userRepository;
//    UserRepository userRepository;
//    private AccountClassTypeRecordRepository classTypeRecordRepository;
 //   private AccountMasterSubLinkRecordRepository subLinkRepository ;
    Logger logger ;

    ResourceManager resourceManager ;
    UUID USERREPO_ID ;
    UUID ACCTCLASSREPO_ID ;
    UUID MSTRSUBREPO_ID ;

    UUID TXREPO_ID ;
    UUID ACCTREPO_ID ;
    UUID CAPREPO_ID ;

    UUID TXENTITY_ID ;
    UUID ACCTENTITY_ID ;
    UUID CAPENTITY_ID ;

    BankServiceResources( Logger logger, TxLogRecordRepository txRepo,
                         AccountRecordRepository acctRepo,
                         UserRepository userRepo,
                         AccountClassTypeRecordRepository classTypeRepo,
                         AccountCapacityRecordRepository capacityRepo,
                         AccountMasterSubLinkRecordRepository linkRepo ) {
        setResourceProviders(logger, txRepo, acctRepo, userRepo, classTypeRepo, capacityRepo, linkRepo );


    }
    BankServiceResources() {}
    void setLogger ( Logger logger )
    {
        this.logger = logger ;
    }
    void setResourceProviders(Logger logger, TxLogRecordRepository txRepo,
                              AccountRecordRepository acctRepo,
                              UserRepository userRepo,
                              AccountClassTypeRecordRepository classTypeRepo,
                              AccountCapacityRecordRepository capacityRepo,
                              AccountMasterSubLinkRecordRepository linkRepo) {
        //userRepository = userRepo;
        //classTypeRecordRepository = classTypeRepo;
        //subLinkRepository = linkRepo ;
        this.logger = logger ;


        TxLogRecordRepositoryResource txRepoResource = new TxLogRecordRepositoryResource() ;
        AccountRecordRepositoryResource accountRepoResource = new AccountRecordRepositoryResource() ;
        AccountCapacityRecordRepositoryResource capacityRepoResource = new AccountCapacityRecordRepositoryResource() ;

        txRepoResource.setRepository( txRepo ) ;
        accountRepoResource.setRepository( acctRepo ) ;
        capacityRepoResource.setRepository( capacityRepo ) ;

        resourceManager = new BankResourceManager() ;

        TXREPO_ID =     resourceManager.AddManagedResource( txRepoResource ) ;
        ACCTREPO_ID =   resourceManager.AddManagedResource( accountRepoResource ) ;
        CAPREPO_ID =    resourceManager.AddManagedResource( capacityRepoResource ) ;

        USERREPO_ID = resourceManager.AddManagedResource( new UserRepositoryResource( userRepo ) ) ;

        AccountClassTypeRecordRepositoryResource classTypeRepoRsrc = new AccountClassTypeRecordRepositoryResource() ;
        RepositoryResource<AccountMasterSubLinkRecordRepository> masterSubRepoResrc = new RepositoryResource<AccountMasterSubLinkRecordRepository>() ;

        classTypeRepoRsrc.setRepository( classTypeRepo ) ;
        masterSubRepoResrc.setRepository( linkRepo ) ;

        ACCTCLASSREPO_ID = resourceManager.AddManagedResource( classTypeRepoRsrc ) ;
        MSTRSUBREPO_ID = resourceManager.AddManagedResource(  masterSubRepoResrc  ) ;

        logger.info( "TXREPO_ID: " + TXREPO_ID ) ;
        logger.info( "ACCTREPO_ID: " + ACCTREPO_ID ) ;
        logger.info( "CAPREPO_ID: " + CAPREPO_ID ) ;
    }

    TxLogRecordRepository getTransactionLogRepository()
    { return ((TxLogRecordRepositoryResource)resourceManager.getResource( TXREPO_ID )).getRepository() ; }

    AccountRecordRepository getAccountRepository()
    { return ((AccountRecordRepositoryResource) resourceManager.getResource( ACCTREPO_ID )).getRepository() ; }

    AccountCapacityRecordRepository getCapacityRepository()
    { return ((AccountCapacityRecordRepositoryResource)resourceManager.getResource( CAPREPO_ID )).getRepository() ; }

    UserRepository getUserRepository()
    { return ((UserRepositoryResource)resourceManager.getResource( USERREPO_ID )).getRepository() ; }

    AccountClassTypeRecordRepository getAccountClassTypeRepository()
    { return ((AccountClassTypeRecordRepositoryResource)resourceManager.getResource( ACCTCLASSREPO_ID )).getRepository() ; }

    AccountMasterSubLinkRecordRepository getAccountMasterSublinkRepository()
    { return ((RepositoryResource<AccountMasterSubLinkRecordRepository>)resourceManager.getResource( MSTRSUBREPO_ID )).getRepository() ; }


    public TxLogRecord getTransaction() { return getManagedTransaction() ; }
    public AccountRecord getAccount() { return managed_acctRecord.getEntity() ; }
    public AccountCapacityRecord getEffectiveCaps() { return getManagedCaps() ; }

    public TxLogRecord getManagedTransaction()
    {
        return ((TxLogRecordResource)resourceManager.getResource( TXENTITY_ID )).getEntity() ;
    }
    public AccountRecord getManagedAccount() {   return managed_acctRecord.getEntity() ;}
    public AccountCapacityRecord getManagedCaps() { return  managed_CapsRecord.getEntity() ; }

    private TxLogRecordResource managed_txRecord = new TxLogRecordResource() ;
    private AccountRecordResource managed_acctRecord = new AccountRecordResource() ;
    private AccountCapacityRecordResource managed_CapsRecord = new AccountCapacityRecordResource();

    // data access utility ...
    // tagname -> classtype id
    // at this time need to derive the id of classtype from the passed string value(s)
    Long resolveTypeId( String typeTag )
    {
        AccountClassTypeRecord r = new AccountClassTypeRecord();
        r.setIdTagname( typeTag );
        Example<AccountClassTypeRecord> example = Example.of(r);
        //r = this.classTypeRecordRepository.findOne(example).orElseThrow();
        r = this.getAccountClassTypeRepository().findOne(example).orElseThrow();
        return r.getAccountClassTypeId() ;
    }
    // classtypeId -> capacityRecord
    AccountCapacityRecord getCapacity( Long typeId )
    {
        AccountCapacityRecord cap = this.getCapacityRepository().findByClassTypeId( typeId ) ;
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

        managed_CapsRecord.setEntity( cap );
        return cap ;
    }
    TxLogRecord loadTxContext( Long txID )
    {
        installManagedResources();

        TxLogRecord tx = loadTransactionRecord( txID ) ;
        AccountRecord acct = loadAccountRecord( tx.getAccountId()) ;
        AccountCapacityRecord cp = loadAccountCapacityRecords( acct.getID() ) ;

        return getTransaction() ;
    }
    void installManagedResources()
    {
//        TxLogRecordResource txRecRsrc = new TxLogRecordResource() ;
//        AccountRecordResource acctRecRsrc = new AccountRecordResource() ;
//        AccountCapacityRecordResource acctCapRecRsrc = new AccountCapacityRecordResource() ;
//
//        TXENTITY_ID = resourceManager.AddManagedResource( txRecRsrc ) ;
//        ACCTENTITY_ID = resourceManager.AddManagedResource( acctRecRsrc ) ;
//        CAPENTITY_ID = resourceManager.AddManagedResource( acctCapRecRsrc ) ;

        TXENTITY_ID = resourceManager.AddManagedResource( new TxLogRecordResource() ) ;
        ACCTENTITY_ID = resourceManager.AddManagedResource( new AccountRecordResource() ) ;
        CAPENTITY_ID = resourceManager.AddManagedResource( new AccountCapacityRecordResource() ) ;

        managed_txRecord = (TxLogRecordResource) resourceManager.getResource( TXENTITY_ID ) ;
        managed_acctRecord = (AccountRecordResource) resourceManager.getResource( ACCTENTITY_ID ) ;
        managed_CapsRecord = (AccountCapacityRecordResource) resourceManager.getResource( CAPENTITY_ID ) ;

        logger.info( "TXENTITY_ID: " + TXENTITY_ID ) ;
        logger.info( "ACCTENTITY_ID: " + ACCTENTITY_ID ) ;
        logger.info( "CAPENTITY_ID: " + CAPENTITY_ID ) ;
    }
    void saveCurrentContext( )
    {
        getAccountRepository().save( getAccount() ) ;
        getTransactionLogRepository().save( getTransaction() ) ;
    }
    TxLogRecord loadTransactionRecord( Long txID )
    {
        TxLogRecord tx = getTransactionLogRepository().findById( txID ).orElseThrow() ;
        managed_txRecord.setEntity( tx ) ;
        return managed_txRecord.getEntity() ;
    }
    AccountRecord loadAccountRecord( Long acctID )
    {
        AccountRecord acct = getAccountRepository().findById( acctID ).orElseThrow() ;
        ((AccountRecordResource)resourceManager.getResource( ACCTENTITY_ID )).setEntity( acct ) ;
        return ((AccountRecordResource)resourceManager.getResource( ACCTENTITY_ID )).getEntity( ) ;
        //managed_acctRecord.setEntity( acct ) ;
        //return managed_acctRecord.getEntity() ;
    }
    AccountCapacityRecord loadAccountCapacityRecords( Long acctID)
    {
        AccountCapacityRecord effectiveCaps ;
        AccountCapacityRecord accountCaps ;
        AccountCapacityRecord classTypeCaps ;
        classTypeCaps = getCapacityRepository().findByClassTypeId( getAccount().getAccountClassType()) ;
        accountCaps = getCapacityRepository().findByAccountId( getAccount().getID()) ;

        effectiveCaps = ( accountCaps == null ? classTypeCaps : accountCaps ) ;
        managed_CapsRecord.setEntity( effectiveCaps ) ;
        return managed_CapsRecord.getEntity() ;
    }



    AccountRecord newAccountRecord( )
    {

        managed_acctRecord.setEntity( new AccountRecord() ) ;

        this.getAccount().setID( null ) ;
        return this.getAccount() ;

        //return new AccountRecord() ;
    }
    AccountRecord saveAccountRecord( AccountRecord account )
    {
        this.getAccountRepository().save( account ) ;
        account = updateAccountMaster( account, account ) ;
        return account ;
    }
    AccountRecord saveAccountRecord( AccountRecord account, Long MasterId  )
    {
        saveAccountRecord( account ) ;
        account = updateAccountSub( account, MasterId ) ;
        return account ;
    }
    AccountRecord updateAccountMaster( AccountRecord to, AccountRecord from )
    {
        if ( this.getEffectiveCaps().isCanBeMasterEnabled() )
            this.assignMasterSub( to, from.getID() ) ;
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
        return getAccountMasterSublinkRepository().save( link ) ;
    }
} ;


