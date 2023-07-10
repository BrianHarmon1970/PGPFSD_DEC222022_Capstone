package com.harmonengineering.bankservice;

import com.harmonengineering.entity.*;
import org.slf4j.Logger;
import org.springframework.data.domain.Example;

import javax.transaction.SystemException;


abstract public class BankServiceOrder implements BankServiceOrderInterface {
    private String type;
    protected Long ID;

    public BankServiceOrder() { }

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

    public BankServiceProcess manifestFactory() {
        return null;
    }
//    public void fulfill() {
//        System.out.println(this.getClass().getName());
//        System.out.println("BTW, I'm Super!!!");
//        throw new RuntimeException("BankServiceOrder - super - not overloaded by sub");
//    }
    //protected void rejectTransaction() throws Exception { throw new Exception("Unhandled TxRejection" ) ; }
    //void rejectTransaction() {}
    ///boolean preVerify() { return true ; }
}
