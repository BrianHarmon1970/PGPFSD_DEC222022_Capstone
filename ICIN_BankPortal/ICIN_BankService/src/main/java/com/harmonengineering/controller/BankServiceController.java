package com.harmonengineering.controller;

import com.harmonengineering.bankservice.*;
import com.harmonengineering.icin_bankservice.IcinBankServiceApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

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
    public BankServiceController()
    {
        bankService.setLogger( logger ) ;
    }
    @PostMapping( path="account-withdraw",
            produces = "application/json; charset=UTF-8; application/x-www-form-urlencoded",
            consumes = "application/json; charset=UTF-8; application/x-www-form-urlencoded")
    public AccountWithdrawOrder message(@RequestBody AccountWithdrawOrder serviceOrder )
    {
        serviceOrder.setLogger( logger ) ;
        bankService.serviceOrder( serviceOrder ) ;
        //return serviceOrder.getClass().getSimpleName() ;
        return serviceOrder ;
    }
    @PostMapping( path="account-deposit",
            produces = "application/json; charset=UTF-8; application/x-www-form-urlencoded",
            consumes = "application/json; charset=UTF-8; application/x-www-form-urlencoded")
    public AccountDepositOrder message(@RequestBody AccountDepositOrder serviceOrder )
    {
        serviceOrder.setLogger( logger ) ;
        bankService.serviceOrder( serviceOrder ) ;
        //return serviceOrder.getClass().getSimpleName() ;
        return serviceOrder ;
    }
    @PostMapping( path="account-create" )
    public String message(@RequestBody AccountCreateOrder serviceOrder )
    {
        serviceOrder.setLogger( logger ) ;
        bankService.serviceOrder( serviceOrder ) ;
        return serviceOrder.getClass().getSimpleName() ;
    }
    @PostMapping( path="extra" )
    public String message(@RequestBody ExtraMessageOrder serviceOrder )
    {
        serviceOrder.setLogger( logger ) ;
        bankService.serviceOrder( serviceOrder ) ;
        return serviceOrder.getMessage() + ". " + serviceOrder.getExtra() + "." ;
    }
}