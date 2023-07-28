package com.harmonengineering.bankservice;

abstract public class AccountBalanceChangeOrder extends BankServiceOrder
{
    Long accountId ;
    Double txAmount ;

    public Long getAccountId() {  return accountId; }
    public void setAccountId(Long AccountId) { this.accountId = AccountId; }

    public Double getTxAmount() { return txAmount; }
    public void setTxAmount(Double txAmount) { this.txAmount = txAmount; }

    public AccountBalanceChangeOrder() {} ;

}


