package com.harmonengineering.bankservice;

import org.slf4j.Logger;
import org.springframework.context.annotation.Bean;



public class BankServiceOrder implements BankServiceOrderInterface {
    private String type;
    protected static Logger logger ;
    public BankServiceOrder() {    }

    public String getType() {
        return this.type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public BankServiceOrder manifestFactory() {
        return this;
    }

    public void setLogger( Logger logger ) { this.logger = logger ; }
    public void fulfill() {
        System.out.println(this.getClass().getName());
        System.out.println("BTW, I'm Super!!!");
    }
}

