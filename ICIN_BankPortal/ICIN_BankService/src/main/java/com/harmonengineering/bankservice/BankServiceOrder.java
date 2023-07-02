package com.harmonengineering.bankservice;

import com.harmonengineering.entity.*;
import org.slf4j.Logger;

import javax.transaction.SystemException;


public class BankServiceOrder { //implements BankServiceOrderInterface {
    private String type;
    protected Long ID;
    static Logger logger;

//    static TxLogRecordRepository txLogRecordRepository;
//    static AccountRecordRepository accountRecordRepository;
//    static UserRepository userRepository;
//    static AccountClassTypeRecordRepository classTypeRecordRepository;
//    static AccountCapacityRecordRepository capacityRecordRepository;

    static BankServiceResources resources;

    public BankServiceOrder() {
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BankServiceOrder manifestFactory() {
        return this;
    }

    public void setLogger(Logger pLogger) {
        logger = pLogger;
    }

    public void fulfill() {
        System.out.println(this.getClass().getName());
        System.out.println("BTW, I'm Super!!!");
        throw new RuntimeException("BankServiceOrder - super - not overloaded by sub");
    }
    //protected void rejectTransaction() throws Exception { throw new Exception("Unhandled TxRejection" ) ; }
    public void rejectTransaction() {}
    public boolean preVerify() { return true ; }

//    public static void setResourceProviders(TxLogRecordRepository txRepo,
//                                            AccountRecordRepository acctRepo,
//                                            UserRepository userRepo,
//                                            AccountClassTypeRecordRepository classTypeRepo,
//                                            AccountCapacityRecordRepository capacityRepo) {
////        userRepository = userRepo;
////        txLogRecordRepository = txRepo;
////        accountRecordRepository = acctRepo;
////        classTypeRecordRepository = classTypeRepo;
////        capacityRecordRepository = capacityRepo;
//    }

    public static void setResourceProviders(BankServiceResources rp) { resources = rp; }
//      need to document and allocate for this -- 7/1/2023 B>S>H>
//      public void RejectTransaction() throws Exception
//      {
//          resources.txRecord.setTxStatus("TRANSACTION_STATUS_REJECTED");
//          resources.txLogRecordRepository.save( resources.txRecord ) ;
//          this.rejectTransaction();
//      }

//    probable not keeping these here. possibly adding call/response counterparts? -- 7/1/2023 B>S>H>
//    public void processTransaction() {}
//    public void loadResources() {}
//    public void updateResources() {}
//    public void saveResources() {}
}
class BankServiceResources
{
    UserRepository userRepository;
    TxLogRecordRepository txLogRecordRepository;
    AccountClassTypeRecordRepository classTypeRecordRepository;
    AccountCapacityRecordRepository capacityRecordRepository;
    AccountRecordRepository accountRecordRepository;
    AccountMasterSubLinkRecordRepository subLinkRepository ;

    BankServiceResources(TxLogRecordRepository txRepo,
                         AccountRecordRepository acctRepo,
                         UserRepository userRepo,
                         AccountClassTypeRecordRepository classTypeRepo,
                         AccountCapacityRecordRepository capacityRepo,
                         AccountMasterSubLinkRecordRepository linkRepo ) {
        setResourceProviders(txRepo, acctRepo, userRepo, classTypeRepo, capacityRepo, linkRepo );
    }
    BankServiceResources() {}

    void setResourceProviders(TxLogRecordRepository txRepo,
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
    }

    TxLogRecord txRecord ;
    AccountRecord acctRecord ;
    AccountCapacityRecord classTypeCaps ;
    AccountCapacityRecord accountCaps ;

    // data access utility ...
    // tagname -> classtype id
    // classtypeId -> capacityRecord
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
    //public st        atic BankServiceResources getResourceContainer() { return resources ; }

