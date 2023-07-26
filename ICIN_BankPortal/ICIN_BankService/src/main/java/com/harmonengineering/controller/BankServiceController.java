package com.harmonengineering.controller;

import com.harmonengineering.bankservice.*;
import com.harmonengineering.entity.*;
import com.harmonengineering.icin_bankservice.IcinBankServiceApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;


@CrossOrigin(origins = "http://localhost:4200",
        methods = { RequestMethod.GET, RequestMethod.DELETE,
                RequestMethod.POST, RequestMethod.PUT } ,
        allowedHeaders = "*", maxAge = 3600 )
//@RequestMapping( path = "/api/bank-service" )

@RequestMapping( value = "${com.harmonengineering.icin_bank.bank-service-root}")
@RestController( )
public class BankServiceController {
    private static final Logger logger = LoggerFactory.getLogger(IcinBankServiceApplication.class);
    private static final BankService bankService = new BankService();
    private static TxLogRecordRepository txLogRecordRepository;
    private static AccountRecordRepository accountRecordRepository;
    private static UserRepository userRepository;
    private static AccountClassTypeRecordRepository accountClassTypeRepository;
    private static AccountCapacityRecordRepository accountCapacityRepository;
    private static AccountMasterSubLinkRecordRepository accountMasterSubLinkRecordRepository;
    private static MasterTransactionRecordRepository masterTransactionRecordRepository ;


    public BankServiceController(
            TxLogRecordRepository txRepo,
            AccountRecordRepository acctRepo,
            UserRepository userRepo,
            AccountClassTypeRecordRepository classTypeRepo,
            AccountCapacityRecordRepository capacityRepo,
            AccountMasterSubLinkRecordRepository msLinkRepo,
            MasterTransactionRecordRepository mtRepo ) {
        txLogRecordRepository = txRepo;
        accountRecordRepository = acctRepo;
        userRepository = userRepo;
        accountClassTypeRepository = classTypeRepo;
        accountCapacityRepository = capacityRepo;
        accountMasterSubLinkRecordRepository = msLinkRepo ;
        masterTransactionRecordRepository = mtRepo ;

        bankService.setResourceProviders( logger, txLogRecordRepository,
                accountRecordRepository,
                userRepository,
                accountClassTypeRepository,
                accountCapacityRepository,
                accountMasterSubLinkRecordRepository,
                masterTransactionRecordRepository
        );
//        bankService.setLogger(logger);
    }
    @PostMapping(path = "account-transfer",
            produces = "application/json; charset=UTF-8; application/x-www-form-urlencoded",
            consumes = "application/json; charset=UTF-8; application/x-www-form-urlencoded")
    public AccountTransferOrder message(@RequestBody AccountTransferOrder serviceOrder) {
        bankService.serviceOrder(serviceOrder);
        return serviceOrder;
    }

    @PostMapping(path = "account-withdraw",
            produces = "application/json; charset=UTF-8; application/x-www-form-urlencoded",
            consumes = "application/json; charset=UTF-8; application/x-www-form-urlencoded")
    public AccountWithdrawOrder message(@RequestBody AccountWithdrawOrder serviceOrder) {
        //serviceOrder.setLogger( logger ) ;
        //serviceOrder.setResourceProviders( txLogRecordRepository, accountRecordRepository );
         bankService.serviceOrder(serviceOrder);
        //return serviceOrder.getClass().getSimpleName() ;
        return serviceOrder;
    }

    @PostMapping(path = "account-deposit",
            produces = "application/json; charset=UTF-8; application/x-www-form-urlencoded",
            consumes = "application/json; charset=UTF-8; application/x-www-form-urlencoded")
    public AccountDepositOrder message(@RequestBody AccountDepositOrder serviceOrder) {
        //serviceOrder.setLogger( logger ) ;
        //serviceOrder.setResourceProviders( txLogRecordRepository, accountRecordRepository );
        bankService.serviceOrder(serviceOrder);
        //return serviceOrder.getClass().getSimpleName() ;
        return serviceOrder;
    }

    @PostMapping(path = "account-create",
            produces = "application/json; charset=UTF-8; application/x-www-form-urlencoded",
            consumes = "application/json; charset=UTF-8; application/x-www-form-urlencoded"
    )
    public AccountCreateOrder message(@RequestBody AccountCreateOrder serviceOrder) {
        //serviceOrder.setLogger( logger ) ;
        bankService.serviceOrder(serviceOrder);
        //return serviceOrder.getClass().getSimpleName() ;
        //logger.info()

        System.out.println("serviceOrder.getID() " + serviceOrder.getID());
        return serviceOrder;
    }

    @PostMapping(path = "extra")
    public String message(@RequestBody ExtraMessageOrder serviceOrder) {
        //serviceOrder.setLogger( logger ) ;
        bankService.serviceOrder(serviceOrder);
        return serviceOrder.getMessage() + ". " + serviceOrder.getExtra() + ".";
    }

    ///============ /api/bank-service/system-configuration ==============
    static final String systemConfigurationRoot = "/system-configuraion"; // /api/bank-service/system-configuration
    //@GetMapping( path=systemConfigurationRoot+ "account-classtypes")

    // /api/system/bank-service/configuration/account-classtypes
    //			- METHOD GET - returns a List of records with attributes indicating, class, type
    //    and pre-qualified TAGNAME_ID, suitable for submission in service order to the
    //					/api/bank-service/account-create API endpoint service order.
    @GetMapping(path = "/system-configuration/account-classtypes")
    List<AccountClassTypeRecord> listClassType() {
        return accountClassTypeRepository.findAll();
    }

    // +/{id} - METHOD GET - returns a single record identified by {id}
    @GetMapping(path = "/system-configuration/account-classtypes/{id}")
    AccountClassTypeRecord findAccountClassTypeById(@PathVariable Long id) {
        return accountClassTypeRepository.findById(id).orElseThrow();
    }

    //  METHOD POST - inserts a record into the data set
    @PostMapping(path = "/system-configuration/account-classtypes")
    AccountClassTypeRecord addAccountClassTypeRecord(@RequestBody AccountClassTypeRecord r) {
        return accountClassTypeRepository.save(r);
    }

    // METHOD PUT - updates a given record
    @PutMapping(path = "/system-configuration/account-classtypes")
    AccountClassTypeRecord updateAccountClassTypeRecord(@RequestBody AccountClassTypeRecord r) {
        return accountClassTypeRepository.save(r);
    }

    // +/{id} - METHOD DELETE - deletes a record with indicated id - {id}
    @DeleteMapping(path = "/system-configuration/account-classtypes/{id}")
    void deleteAccountClassTypeRecordById(@PathVariable Long id) {
        accountClassTypeRepository.deleteById(id);
    }
//        - METHOD GET - returns a list of records with attributes defining account service parameters... and can be associated to an
//    account or classtype - saying as much and its now clear that for the public interface there needs to be at minimum
//        an endpoint specific to classtype or else other means ( such as by query of the id of the returned classtype on the
//        data service described above) to differentiate classtype associations from specific account associations.
    @GetMapping( path="/system-configuration/account-capacity/" )
    List<AccountCapacityRecord> findAllAccountCapacityRecord()
    {
        return accountCapacityRepository.findAll() ;
    }
    // METHOD GET - returns a single record with ID value of {id}
    @GetMapping( path="/system-configuration/account-capacity/{id}" )
    AccountCapacityRecord findAccountCapacityRecordById( @PathVariable Long id )
    {
        return accountCapacityRepository.findById( id ).orElseThrow() ;
    }
    // METHOD POST - inserts a record into the data set
    @PostMapping( path="/system-configuration/account-capacity/" )
    AccountCapacityRecord addAccountCapacityRecord( @RequestBody AccountCapacityRecord r )
    {
        return accountCapacityRepository.save( r ) ;
    }
    // METHOD PUT - updates a given record
    @PutMapping( path="/system-configuration/account-capacity/" )
    AccountCapacityRecord updateAccountCapacityRecord( @RequestBody AccountCapacityRecord r )
    {
        return accountCapacityRepository.save( r ) ;
    }
//
//                /api/bank-service/system-configuration/account-capacity/{id}
    // METHOD DELETE - deletes the record indicated by ID value of {id}
    @DeleteMapping( path="/system-configuration/account-capacity/{id}" )
    void deleteAccountCapacityRecordById( @PathVariable Long id )
    {
        accountCapacityRepository.deleteById( id ) ;
    }

    @GetMapping( path="/system-configuration/account-capacity/classtype/{id}")
    public AccountCapacityRecord capacityRecordByClasstypeId( @PathVariable Long id )
    {
        logger.info( "typeId = : " +  id ) ;
        AccountCapacityRecord cap  = accountCapacityRepository.findByClassTypeId(id) ;
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
//
//        if ( cap.isAccountFeeEnabled() ) logger.info( "Account fee: " +  cap.getAccountFee() );
//        if ( cap.isCheckLimitEnabled() ) logger.info( "Check Limit: " +  cap.getCheckLimit() );
//        if ( cap.isInterestEnabled() ) logger.info( "Interest Rate: " +  cap.getInterestRate() );
//        if ( cap.isOverdraftLimitEnabled()) {
//            logger.info("Overdraft Limit" + cap.getOverdraftLimit());
//            logger.info("Overdraft Fee" + cap.getOverdraftFee());
//        }
        return cap ;
    }
}
