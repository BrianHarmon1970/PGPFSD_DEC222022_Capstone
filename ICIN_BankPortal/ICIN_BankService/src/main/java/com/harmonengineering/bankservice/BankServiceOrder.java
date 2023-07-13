package com.harmonengineering.bankservice;

abstract public class BankServiceOrder implements BankServiceOrderInterface
{
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
}
