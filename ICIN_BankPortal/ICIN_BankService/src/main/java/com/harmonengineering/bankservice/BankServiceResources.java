package com.harmonengineering.bankservice;

import com.harmonengineering.entity.*;
import org.slf4j.Logger;
import org.springframework.data.domain.Example;

public class BankServiceResources extends BankResourceManager
{
    UserRepository userRepository;
    TxLogRecordRepository txLogRecordRepository;
    AccountClassTypeRecordRepository classTypeRecordRepository;
    AccountCapacityRecordRepository capacityRecordRepository;
    AccountRecordRepository accountRecordRepository;
    AccountMasterSubLinkRecordRepository subLinkRepository ;
    Logger logger ;

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
        txLogRecordRepository = txRepo;
        accountRecordRepository = acctRepo;
        classTypeRecordRepository = classTypeRepo;
        capacityRecordRepository = capacityRepo;
        subLinkRepository = linkRepo ;
        this.logger = logger ;
    }

    TxLogRecord txRecord = new TxLogRecord();
    AccountRecord acctRecord = new AccountRecord() ;

    AccountCapacityRecord classTypeCaps ;
    AccountCapacityRecord accountCaps ;
    AccountCapacityRecord effectiveCaps ;

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
        AccountCapacityRecord cap = this.capacityRecordRepository.findByClassTypeId( typeId ) ;
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

        if ( cap.isAccountFeeEnabled() ) logger.info( "Account fee: " +  cap.getAccountFee() );
        if ( cap.isCheckLimitEnabled() ) logger.info( "Check Limit: " +  cap.getCheckLimit() );
        if ( cap.isInterestEnabled() ) logger.info( "Interest Rate: " +  cap.getInterestRate() );
        if ( cap.isOverdraftLimitEnabled())
        {
            logger.info("Overdraft Limit" + cap.getOverdraftLimit());
            logger.info("Overdraft Fee" + cap.getOverdraftFee());
        }
        return cap ;
    }
    TxLogRecord loadTxContext( Long txID )
    {
        loadTransactionRecord( txID ) ;
        loadAccountRecord( txRecord.getAccountId() ) ;
        loadAccountCapacityRecords( acctRecord.getID() ) ;
        return txRecord ;
    }
    TxLogRecord loadTransactionRecord( Long txID )
    {
        txRecord = txLogRecordRepository.findById( txID ).orElseThrow() ;
        return txRecord ;
    }
    AccountRecord loadAccountRecord( Long acctID )
    {
        acctRecord = accountRecordRepository.findById( acctID ).orElseThrow() ;
        //loadAccountCapacityRecords( acctID ) ;
        return acctRecord ;
    }
    AccountCapacityRecord loadAccountCapacityRecords( Long acctID)
    {
        classTypeCaps = _static.resources.capacityRecordRepository.findByClassTypeId( acctRecord.getAccountClassType()) ;
        accountCaps = _static.resources.capacityRecordRepository.findByAccountId( acctRecord.getID()) ;
        effectiveCaps = ( accountCaps == null ? classTypeCaps : accountCaps ) ;
        return effectiveCaps ;
    }
    AccountRecord newAccountRecord( )
    {
        this.acctRecord.setID( null ) ;
        return acctRecord ;
        //return new AccountRecord() ;

    }
    AccountRecord saveAccountRecord( AccountRecord account )
    {
        this.accountRecordRepository.save( account ) ;
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
        if ( this.accountCaps.isCanBeMasterEnabled() )
            this.assignMasterSub( to, from.getID() ) ;
        return to ;
    }
    AccountRecord updateAccountSub( AccountRecord account, Long MasterId )
    {
        if ( this.accountCaps.isCanBeSubEnabled() )
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


