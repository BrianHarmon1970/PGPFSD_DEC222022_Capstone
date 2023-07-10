package com.harmonengineering.bankservice;

import com.harmonengineering.entity.*;
import org.slf4j.Logger;

class _static
{
    static BankServiceResources resources ;
}

public class BankService
{
    public BankServiceOrder serviceOrder( BankServiceOrder order )
    {
        System.out.println( "Fulfilling order: ") ;
        BankServiceProcess orderProcess = order.manifestFactory() ;
        return  orderProcess.fulfill( order ) ;
        //return order ;
    }
    public void setResourceProviders( Logger logger , TxLogRecordRepository txRepo,
                                     AccountRecordRepository acctRepo,
                                     UserRepository userRepo,
                                     AccountClassTypeRecordRepository classTypeRepo,
                                     AccountCapacityRecordRepository capacityRepo,
                                     AccountMasterSubLinkRecordRepository linkRepo )
    {
        _static.resources = new BankServiceResources() ;
        _static.resources.setResourceProviders( logger, txRepo, acctRepo, userRepo, classTypeRepo, capacityRepo, linkRepo );
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
