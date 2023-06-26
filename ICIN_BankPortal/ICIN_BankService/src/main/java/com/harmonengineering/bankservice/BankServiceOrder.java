package com.harmonengineering.bankservice;

import com.harmonengineering.entity.AccountClassTypeRecordRepository;
import com.harmonengineering.entity.AccountRecordRepository;
import com.harmonengineering.entity.TxLogRecordRepository;
import com.harmonengineering.entity.UserRepository;
import org.slf4j.Logger;
import org.springframework.context.annotation.Bean;



public class BankServiceOrder { //implements BankServiceOrderInterface {
    private String type;
    protected Long ID ;
    static Logger logger ;
    static TxLogRecordRepository txLogRecordRepository ;
    static AccountRecordRepository accountRecordRepository ;
    static UserRepository userRepository ;
    static AccountClassTypeRecordRepository classTypeRecordRepository ;

    public BankServiceOrder() {    }

    public Long getID() { return ID; }
    public void setID(Long ID) { this.ID = ID; }

    public String getType() { return this.type; }
    public void setType(String type) { this.type = type; }

    public BankServiceOrder manifestFactory() {
        return this;
    }

    public void setLogger( Logger pLogger ) { logger = pLogger ; }
    public void fulfill() {
        System.out.println(this.getClass().getName());
        System.out.println("BTW, I'm Super!!!");
        throw new RuntimeException( "BankServiceOrder - super - not overloaded by sub" ) ;
    }
    public static void setResourceProviders(TxLogRecordRepository txRepo,
                                     AccountRecordRepository acctRepo,
                                     UserRepository userRepo , AccountClassTypeRecordRepository classTypeRepo )
    {
        userRepository = userRepo ;
        txLogRecordRepository = txRepo ;
        accountRecordRepository = acctRepo ;
        classTypeRecordRepository = classTypeRepo ;
    }
//    public void processTransaction() {}
//    public void loadResources() {}
//    public void updateResources() {}
//    public void saveResources() {}

}

