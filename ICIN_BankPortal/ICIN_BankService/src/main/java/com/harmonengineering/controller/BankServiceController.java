package com.harmonengineering.controller;

import com.harmonengineering.bankservice.*;
import com.harmonengineering.entity.*;
import com.harmonengineering.icin_bankservice.IcinBankServiceApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:4200",
        methods = { RequestMethod.GET, RequestMethod.DELETE,
                RequestMethod.POST, RequestMethod.PUT } ,
        allowedHeaders = "*", maxAge = 3600 )
@RequestMapping( path = "/api/bank-service" )
@RestController( )
public class BankServiceController
{
    private static final Logger logger = LoggerFactory.getLogger(IcinBankServiceApplication.class);
    private static final BankService bankService = new BankService() ;
    private static TxLogRecordRepository txLogRecordRepository ;
    private static AccountRecordRepository accountRecordRepository ;
    private static UserRepository userRepository ;
    private static AccountClassTypeRecordRepository accountClassTypeRepository ;
    private static AccountCapacityRecordRepository accountCapacityRepository ;
    private static AccountMasterSubLinkRecordRepository accountMasterSubLinkRecordRepository ;
    public BankServiceController(
            TxLogRecordRepository txRepo ,
            AccountRecordRepository acctRepo,
            UserRepository userRepo,
            AccountClassTypeRecordRepository classTypeRepo,
            AccountCapacityRecordRepository capacityRepo,
            AccountMasterSubLinkRecordRepository msLinkRepo )
    {
        txLogRecordRepository = txRepo ;
        accountRecordRepository = acctRepo ;
        userRepository = userRepo ;
        accountClassTypeRepository = classTypeRepo ;
        accountCapacityRepository = capacityRepo ;
        accountMasterSubLinkRecordRepository = msLinkRepo ;

        bankService.setLogger( logger ) ;
        bankService.setResourceProviders( txLogRecordRepository,
                                            accountRecordRepository,
                                            userRepository,
                                            accountClassTypeRepository,
                                            accountCapacityRepository,
                                            accountMasterSubLinkRecordRepository
        );
    }
    @PostMapping( path="account-withdraw",
            produces = "application/json; charset=UTF-8; application/x-www-form-urlencoded",
            consumes = "application/json; charset=UTF-8; application/x-www-form-urlencoded")
    public AccountWithdrawOrder message(@RequestBody AccountWithdrawOrder serviceOrder )
    {
        //serviceOrder.setLogger( logger ) ;
        //serviceOrder.setResourceProviders( txLogRecordRepository, accountRecordRepository );
        bankService.serviceOrder( serviceOrder ) ;
        //return serviceOrder.getClass().getSimpleName() ;
        return serviceOrder ;
    }
    @PostMapping( path="account-deposit",
            produces = "application/json; charset=UTF-8; application/x-www-form-urlencoded",
            consumes = "application/json; charset=UTF-8; application/x-www-form-urlencoded")
    public AccountDepositOrder message(@RequestBody AccountDepositOrder serviceOrder )
    {
        //serviceOrder.setLogger( logger ) ;
        //serviceOrder.setResourceProviders( txLogRecordRepository, accountRecordRepository );
        bankService.serviceOrder( serviceOrder ) ;
        //return serviceOrder.getClass().getSimpleName() ;
        return serviceOrder ;
    }
    @PostMapping( path="account-create",
            produces = "application/json; charset=UTF-8; application/x-www-form-urlencoded",
            consumes = "application/json; charset=UTF-8; application/x-www-form-urlencoded"
    )
    public AccountCreateOrder message(@RequestBody AccountCreateOrder serviceOrder )    {
        //serviceOrder.setLogger( logger ) ;
        bankService.serviceOrder( serviceOrder ) ;
        //return serviceOrder.getClass().getSimpleName() ;
        //logger.info()

        System.out.println( "serviceOrder.getID() " + serviceOrder.getID()) ;
        return serviceOrder ;
    }
    @PostMapping( path="extra" )
    public String message(@RequestBody ExtraMessageOrder serviceOrder )
    {
        //serviceOrder.setLogger( logger ) ;
        bankService.serviceOrder( serviceOrder ) ;
        return serviceOrder.getMessage() + ". " + serviceOrder.getExtra() + "." ;
    }
    ///============ /api/bank-service/system-configuration ==============
    static final String systemConfigurationRoot = "/system-configuraion" ; // /api/bank-service/system-configuration
    //@GetMapping( path=systemConfigurationRoot+ "account-classtypes")
    @GetMapping( path="/system-configuration/account-classtypes")
    List<AccountClassTypeRecord> listClassType()
    {
        return accountClassTypeRepository.findAll() ;
    }
    @GetMapping( path="/system-configuration/account-capacity/classtype/{id}")
    public AccountCapacityRecord capacityRecordByClasstypeId( @PathVariable Long id )
    {
        logger.info( "id = : " +  id ) ;
        AccountCapacityRecord cap  = accountCapacityRepository.findByClassTypeId(id) ;
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
        if ( cap.isOverdraftLimitEnabled()) {
            logger.info("Overdraft Limit" + cap.getOverdraftLimit());
            logger.info("Overdraft Fee" + cap.getOverdraftFee());
        }
        return cap ;

    }
}
