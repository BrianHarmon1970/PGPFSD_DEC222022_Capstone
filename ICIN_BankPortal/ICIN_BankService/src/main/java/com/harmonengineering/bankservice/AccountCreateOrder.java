package com.harmonengineering.bankservice;
import com.harmonengineering.entity.*;

public class AccountCreateOrder extends BankServiceOrder
        implements BankServiceOrderInterface
{
    // user id
    // account number
    // account name
    // account type

    /// -------- Interface variables
    // out
    private Long AccountID ;
    private Long AccountClassType ;

    // in-betweens
    private String AccountClass ;
    private String AccountType ;

    // in
    private Long MasterAccountID ;
    private String AccountClassTypeTag ;
    private Long UserID ;
    private String AccountNumber ;
    private String AccountName ;
    private Double AccountBalance ;
    // ---------------------------- end of interface variables

    // ----------- interface variable interface ( getters and setters )
    public Long getID() { return AccountID; }
    public void setID(Long accountID) { AccountID = accountID; }

    public Long getAccountID() { return AccountID; }
    public void setAccountID(Long accountID) { AccountID = accountID; }

    public Long getMasterAccountID() { return MasterAccountID; }
    public void setMasterAccountID(Long masterAccountID) { MasterAccountID = masterAccountID; }

    public Long getAccountClassType() {  return AccountClassType; }
    public void setAccountClassType(Long accountClassType) { AccountClassType = accountClassType; }

    public Long getUserID() { return UserID; }
    public void setUserID(Long userID) { UserID = userID; }

    public String getAccountClassTypeTag() { return AccountClassTypeTag; }
    public void setAccountClassTypeTag(String accountClassTypeTag) { AccountClassTypeTag = accountClassTypeTag; }

    public String getAccountNumber() { return AccountNumber; }
    public void setAccountNumber(String accountNumber) { AccountNumber = accountNumber; }
    public String getAccountName() { return AccountName; }
    public void setAccountName(String accountName) { AccountName = accountName; }
    public String getAccountType() { return AccountType; }
    public void setAccountType(String accountType) { AccountType = accountType; }
    public Double getAccountBalance() { return AccountBalance; }
    public void setAccountBalance(Double accountBalance) { AccountBalance = accountBalance; }
    public String getAccountClass() { return AccountClass; }
    public void setAccountClass(String accountClass) { AccountClass = accountClass; }

    public AccountCreateOrder()
    {
        orderProcess = _static.resources.getAccountCreateProcess() ;
        orderProcess.assignInterfaceOrder( this ) ;

    }

    AccountCreateProcess orderProcess ;
    //AccountCreateProcess orderProcess = new AccountCreateProcess( this ) ; ;
    
    public BankServiceProcess manifestFactory()
    {

        return orderProcess ;
    }

    //public void fulfill() { orderProcess.fulfill(); }
}