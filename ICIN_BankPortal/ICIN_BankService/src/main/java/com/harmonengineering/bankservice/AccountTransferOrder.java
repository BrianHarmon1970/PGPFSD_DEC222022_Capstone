package com.harmonengineering.bankservice;

public class AccountTransferOrder extends BankServiceOrder // extends AccountBalanceChangeOrder
        implements BankServiceOrderInterface
{
    // in
    Long masterAccountId ;
    Long primaryAccountId ;
    Long secondaryAccountId ;
    Double transferAmount ;

    // out
    Long id ;
    Long primaryTxId ;
    Long secondaryTxId ;

    // in
    public Long getMasterAccountId() { return masterAccountId; }
    public void setMasterAccountId(Long masterAccountId) { this.masterAccountId = masterAccountId; }

    public Long getPrimaryAccountId() { return primaryAccountId; }
    public void setPrimaryAccountId(Long primaryAccountId) { this.primaryAccountId = primaryAccountId; }

    public Long getSecondaryAccountId() { return secondaryAccountId ; }
    public void setSecondaryAccountId(Long secondaryAccountId ) { this.secondaryAccountId  = secondaryAccountId; }

    public Double getTransferAmount() { return transferAmount; }
    public void setTransferAmount(Double transferAmount) { this.transferAmount = transferAmount; }

    // out

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getPrimaryTxId() { return primaryTxId; }
    public void setPrimaryTxId(Long primaryTxId) { this.primaryTxId = primaryTxId; }

    public Long getSecondaryTxId() { return secondaryTxId; }
    public void setSecondaryTxId(Long secondaryTxId) { this.secondaryTxId = secondaryTxId; }

    @Override
    public BankServiceProcess manifestFactory() {
        return new AccountTransferProcess() ;
    }
}
