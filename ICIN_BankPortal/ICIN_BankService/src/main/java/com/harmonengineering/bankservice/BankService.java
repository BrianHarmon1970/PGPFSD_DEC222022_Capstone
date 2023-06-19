package com.harmonengineering.bankservice;

import com.harmonengineering.icin_bankservice.IcinBankServiceApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;

public class BankService
{
    private static Logger logger ;
    public void  serviceOrder( BankServiceOrder order )
    {

        System.out.println( "Fulfilling order: ") ;
        order.fulfill();
        //return order.getMessage() ;
    }
    public void setLogger( Logger logger ) { this.logger = logger ; }

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
