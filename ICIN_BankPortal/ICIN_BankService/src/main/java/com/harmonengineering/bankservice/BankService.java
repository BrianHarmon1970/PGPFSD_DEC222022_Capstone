package com.harmonengineering.bankservice;

import com.harmonengineering.entity.*;
import com.harmonengineering.icin_bankservice.IcinBankServiceApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;

public class BankService
{
    private static Logger logger ;
    //private static TxLogRecordRepository txLogRecordRepository ;
    //private static AccountRecordRepository accountRecordRepository ;
    //private static UserRepository userRepository ;

    public BankServiceOrder  serviceOrder( BankServiceOrder order )
    {
        System.out.println( "Fulfilling order: ") ;
        order.setLogger( logger ) ;
//        order.setResourceProviders(
//                txLogRecordRepository ,
//                accountRecordRepository ,
//                userRepository
//        );
        BankServiceOrder orderProcess = order.manifestFactory() ;
        orderProcess.fulfill();
        //return order.getMessage() ;
        return order ;
    }
    public void setLogger( Logger logger ) { this.logger = logger ; }
    //public void setResourceProviders( BankServiceResources bsr ) { BankServiceOrder.setResourceProviders(bsr);}

    public void setResourceProviders(TxLogRecordRepository txRepo,
                                     AccountRecordRepository acctRepo,
                                     UserRepository userRepo,
                                     AccountClassTypeRecordRepository classTypeRepo,
                                     AccountCapacityRecordRepository capacityRepo,
                                     AccountMasterSubLinkRecordRepository linkRepo )
    {
        //BankServiceOrder.setResourceProviders( txRepo, acctRepo, userRepo, classTypeRepo, capacityRepo );
        BankServiceResources resources = new BankServiceResources() ;
        resources.setResourceProviders( txRepo, acctRepo, userRepo, classTypeRepo, capacityRepo, linkRepo );
        BankServiceOrder.setResourceProviders( resources );

//        userRepository = userRepo ;
//        txLogRecordRepository = txRepo ;
//        accountRecordRepository = acctRepo ;

    }

//    public String getRequestType( BankServiceOrder order )
//    {
//        return order.getType() ;
//    }
//    public BankServiceOrder builder( BankServiceOrder order )
//    {
//
//        if (order.getType().equals( "extra" ))
//        {
//            //return new ExtraMessageOrder( order);
//        }
//        return order;
//    }
}
