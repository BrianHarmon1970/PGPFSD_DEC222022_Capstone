package com.harmonengineering.entity;

import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.*;

@Entity
@Table( name="accounts")
@EnableTransactionManagement
public class AccountRecord
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "ID" )              private Long ID ;
    @Column( name = "user_id" )         private Long userId ;

    @Column( name = "account_class" )   private String accountClass ;
    @Column( name = "account_type" )    private String accountType ;

    @Column( name = "account_number" )  private String accountNumber ;
    @Column( name = "account_name" )    private String accountName ;
    @Column( name = "account_balance" ) private Double accountBalance ;


    public AccountRecord() {}

    public Long getID() {  return ID;  }
    public void setID(Long ID) {  this.ID = ID;  }

    public String getAccountClass() { return accountClass; }
    public void setAccountClass(String accountClass) { this.accountClass = accountClass; }

    public String   getAccountType() { return accountType; }
    public void     setAccountType(String accountType) { this.accountType = accountType; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String   getAccountNumber() { return accountNumber; }
    public void     setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }

    public String   getAccountName() { return accountName; }
    public void     setAccountName(String accountName) { this.accountName = accountName; }

    public Double   getAccountBalance() { return accountBalance; }
    public void     setAccountBalance(Double accountBalance) { this.accountBalance = accountBalance; }
}
