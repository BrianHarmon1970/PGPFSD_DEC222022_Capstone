package com.harmonengineering.bankservice;

public class BankServiceProcess {
    private String type;
    protected Long ID;

    public BankServiceProcess() { }

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

    //public BankServiceOrder manifestFactory() {  return this;  }
    public void fulfill() {
        System.out.println(this.getClass().getName());
        System.out.println("BTW, I'm Super!!!");
        throw new RuntimeException("BankServiceOrder - super - not overloaded by sub");
    }
    //protected void rejectTransaction() throws Exception { throw new Exception("Unhandled TxRejection" ) ; }
    public void rejectTransaction() {}
    public boolean preVerify() { return true ; }
}

