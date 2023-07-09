package com.harmonengineering.bankservice;

import com.harmonengineering.entity.*;
import org.slf4j.Logger;
import org.springframework.data.domain.Example;

import javax.transaction.SystemException;


public class BankServiceOrder { //implements BankServiceOrderInterface {
    private String type;
    protected Long ID;
    //static Logger logger;

//    static TxLogRecordRepository txLogRecordRepository;
//    static AccountRecordRepository accountRecordRepository;
//    static UserRepository userRepository;
//    static AccountClassTypeRecordRepository classTypeRecordRepository;
//    static AccountCapacityRecordRepository capacityRecordRepository;

    //static BankServiceResources _static;

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

    public void setLogger(Logger pLogger)
    {
        //logger = pLogger;
        //setLogger( pLogger ) ;
    }

    public void fulfill() {
        System.out.println(this.getClass().getName());
        System.out.println("BTW, I'm Super!!!");
        throw new RuntimeException("BankServiceOrder - super - not overloaded by sub");
    }
    //protected void rejectTransaction() throws Exception { throw new Exception("Unhandled TxRejection" ) ; }
    public void rejectTransaction() {}
    public boolean preVerify() { return true ; }


    ///public static void setResourceProviders(BankServiceResources rp) { resources = rp; }

//    probable not keeping these here. possibly adding call/response counterparts? -- 7/1/2023 B>S>H>
//    public void processTransaction() {}
//    public void loadResources() {}
//    public void updateResources() {}
//    public void saveResources() {}
}
