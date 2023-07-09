package com.harmonengineering.bankservice;

import com.harmonengineering.entity.*;
import org.slf4j.Logger;

class _static
{
    static BankServiceResources resources ;
}

public class BankService
{
    public BankServiceOrder  serviceOrder( BankServiceOrder order )
    {
        System.out.println( "Fulfilling order: ") ;
        BankServiceOrder orderProcess = order.manifestFactory() ;

        orderProcess.fulfill();
        //return order.getMessage() ;
        return order ;
    }
    public void setResourceProviders( Logger logger , TxLogRecordRepository txRepo,
                                     AccountRecordRepository acctRepo,
                                     UserRepository userRepo,
                                     AccountClassTypeRecordRepository classTypeRepo,
                                     AccountCapacityRecordRepository capacityRepo,
                                     AccountMasterSubLinkRecordRepository linkRepo )
    {
        // BankServiceOrder.setResourceProviders( txRepo, acctRepo, userRepo, classTypeRepo, capacityRepo );
        _static.resources = new BankServiceResources() ;
        _static.resources.setResourceProviders( logger, txRepo, acctRepo, userRepo, classTypeRepo, capacityRepo, linkRepo );
        // BankServiceOrder.setResourceProviders( resources );
        // BankServiceProcess.setResourceProviders( resources ) ;
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
