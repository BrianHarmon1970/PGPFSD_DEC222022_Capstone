package com.harmonengineering.bankservice;

import com.harmonengineering.entity.*;
import org.slf4j.Logger;
import org.springframework.data.domain.Example;

import java.util.UUID;

public class BankServiceResources extends BankResourceManager
{
    //private UserRepository userRepository;
    UserRepository userRepository;
    //private TxLogRecordRepository txLogRecordRepository;
    private AccountClassTypeRecordRepository classTypeRecordRepository;
    //private AccountCapacityRecordRepository capacityRecordRepository;
    //private AccountRecordRepository accountRecordRepository;
    private AccountMasterSubLinkRecordRepository subLinkRepository ;
    Logger logger ;
    ResourceManager resourceManager ;
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
        userRepository = userRepo;
        //txLogRecordRepository = txRepo;
        //accountRecordRepository = acctRepo;
        classTypeRecordRepository = classTypeRepo;
        //capacityRecordRepository = capacityRepo;
        subLinkRepository = linkRepo ;
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

    //private TxLogRecord txRecord = new TxLogRecord();
    //private AccountRecord acctRecord = new AccountRecord() ;

    //private AccountCapacityRecord classTypeCaps ;
    //private AccountCapacityRecord accountCaps ;
    //private AccountCapacityRecord effectiveCaps ;

    //public TxLogRecord getTxRecord() { return txRecord ; }

    //public AccountRecord getAcctRecord() { return acctRecord ; }
    //public AccountCapacityRecord getEffectiveCapsRecord() { return effectiveCaps ; }
    //public AccountCapacityRecord getEffectiveCapsRecord() { return getEffectiveCaps(); }

    //public TxLogRecord getTransaction() { return getTxRecord() ; }
    public TxLogRecord getTransaction() { return getManagedTransaction() ; }
    //public AccountRecord getAccount() { return getAcctRecord() ; }
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
        //r = classTypeRecordRepository.findOne( example ).orElseThrow() ;

        r = this.classTypeRecordRepository.findOne(example).orElseThrow();

        //interfaceOrder.setAccountClassType(r.getAccountClassTypeId());
        return r.getAccountClassTypeId() ;
    }
    // classtypeId -> capacityRecord
    AccountCapacityRecord getCapacity( Long typeId )
    {
        //AccountCapacityRecord cap = this.capacityRecordRepository.findByClassTypeId( typeId ) ;
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
        //effectiveCaps = classTypeCaps = cap ;
        managed_CapsRecord.setEntity( cap );
        return cap ;
    }
    TxLogRecord loadTxContext( Long txID )
    {
        TxLogRecord tx = loadTransactionRecord( txID ) ;
        //loadAccountRecord( txRecord.getAccountId() ) ;
        AccountRecord acct = loadAccountRecord( tx.getAccountId()) ;
        //AccountCapacityRecord cp = loadAccountCapacityRecords( acctRecord.getID() ) ;
        AccountCapacityRecord cp = loadAccountCapacityRecords( acct.getID() ) ;

        managed_txRecord.setEntity( tx ) ;
        managed_acctRecord.setEntity( acct ) ;
        managed_CapsRecord.setEntity( cp ) ;
        installManagedResources() ;

        //return txRecord ;
        return getTransaction() ;
    }
    void installManagedResources()
    {
        //managed_txRecord.setEntity( txRecord ) ;
        //managed_acctRecord.setEntity( acctRecord ) ;
        //managed_CapsRecord.setEntity( effectiveCaps ) ;

        TXENTITY_ID = resourceManager.AddManagedResource( managed_txRecord ) ;
        ACCTENTITY_ID = resourceManager.AddManagedResource( managed_acctRecord ) ;
        CAPENTITY_ID = resourceManager.AddManagedResource( managed_CapsRecord ) ;

        logger.info( "TXENTITY_ID: " + TXENTITY_ID ) ;
        logger.info( "ACCTENTITY_ID: " + ACCTENTITY_ID ) ;
        logger.info( "CAPENTITY_ID: " + CAPENTITY_ID ) ;
    }
    void saveCurrentContext( )
    {
        //txLogRecordRepository.save( txRecord ) ;
        //accountRecordRepository.save( acctRecord ) ;
        //getTransactionLogRepository().save( txRecord ) ;
        getAccountRepository().save( getAccount() ) ;
        getTransactionLogRepository().save( getTransaction() ) ;
    }

    TxLogRecord loadTransactionRecord( Long txID )
    {
        //txRecord = txLogRecordRepository.findById( txID ).orElseThrow() ;
//        txRecord = ((TxLogRecordRepositoryResource)resourceManager.getResource( TXREPO_ID )).
//                getRepository().findById( txID ).orElseThrow() ;
//        txRecord = getTransactionLogRepository().findById( txID ).orElseThrow() ;
//        return txRecord ;
//        return ((TxLogRecordRepositoryResource)resourceManager.getResource( TXREPO_ID )).
//                getRepository().findById( txID ).orElseThrow() ;
        return getTransactionLogRepository().findById( txID ).orElseThrow() ;
    }
    AccountRecord loadAccountRecord( Long acctID )
    {
//        acctRecord = accountRecordRepository.findById( acctID ).orElseThrow() ;
//        //loadAccountCapacityRecords( acctID ) ;
//        return acctRecord ;
        AccountRecord acct = getAccountRepository().findById( acctID ).orElseThrow() ;
        managed_acctRecord.setEntity( acct ) ;
        return managed_acctRecord.getEntity() ;

    }
    AccountCapacityRecord loadAccountCapacityRecords( Long acctID)
    {
        //classTypeCaps = _static.resources.capacityRecordRepository.findByClassTypeId( acctRecord.getAccountClassType()) ;
        //accountCaps = _static.resources.capacityRecordRepository.findByAccountId( acctRecord.getID()) ;
        AccountCapacityRecord effectiveCaps ;
        AccountCapacityRecord accountCaps ;
        AccountCapacityRecord classTypeCaps ;
//        classTypeCaps = getCapacityRepository().findByClassTypeId( acctRecord.getAccountClassType()) ;
//        accountCaps = getCapacityRepository().findByAccountId( acctRecord.getID()) ;
        classTypeCaps = getCapacityRepository().findByClassTypeId( getAccount().getAccountClassType()) ;
        accountCaps = getCapacityRepository().findByAccountId( getAccount().getID()) ;

        effectiveCaps = ( accountCaps == null ? classTypeCaps : accountCaps ) ;
        managed_CapsRecord.setEntity( effectiveCaps ) ;
        return managed_CapsRecord.getEntity() ;
    }
    AccountRecord newAccountRecord( )
    {
//        this.acctRecord.setID( null ) ;
//        return acctRecord ;
        this.getAccount().setID( null ) ;
        return this.getAccount() ;
        //return new AccountRecord() ;

    }
    AccountRecord saveAccountRecord( AccountRecord account )
    {
        //this.accountRecordRepository.save( account ) ;

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
        return subLinkRepository.save( link ) ;
    }
} ;
//static BankServiceResources resources ;
//public static BankServiceResources getResourceContainer() { return resources ; }


