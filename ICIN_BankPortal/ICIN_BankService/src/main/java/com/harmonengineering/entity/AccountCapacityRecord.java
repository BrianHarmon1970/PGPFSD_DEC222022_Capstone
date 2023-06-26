package com.harmonengineering.entity;

import javax.persistence.*;

@Entity
@Table( name="account_capacity" )
public class AccountCapacityRecord
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name="ID" )                    private Long ID ;
    @Column( name="ID_TAGNAME" )            private String IdTagname ;
    @Column( name="canbe_master_enabled" )  private boolean canBeMasterEnabled ;
    @Column( name="canbe_sub_enabled" )     private boolean canBeSubEnabled ;
    @Column( name="checking_enabled" )      private boolean checkingEnabled ;
    @Column( name="account_fee_enabled" )   private boolean accountFeeEnabled ;
    @Column( name="check_limit_enabled"	)   private boolean checkLimitEnabled	;
    @Column( name="interest_enabled" )      private boolean interestEnabled ;
    @Column( name="account_fee" )           private Double  accounFee	;
    @Column( name="check_limit" )           private Long    checkLimit	;
    @Column( name="interest_rate" )         private Double  interestRate	;
    @Column( name="overdraft_limit_enabled" )    private boolean     overdraftLimitEnabled ;
    @Column( name="overdraft_limit"	)       private Double  overdraftLimit	;
    @Column( name="overdraft_fee" )         private Double  overdraftFee	;

    public Long getID() { return ID; }
    public void setID(Long id) { ID = id; }

    public String getIdTagname() { return IdTagname;  }
    public void setIdTagname(String idTagname) { IdTagname = idTagname; }

    public boolean isCanBeMasterEnabled() { return canBeMasterEnabled; }
    public void setCanBeMasterEnabled(boolean canBeMasterEnabled) { this.canBeMasterEnabled = canBeMasterEnabled; }

    public boolean isCanBeSubEnabled() { return canBeSubEnabled; }
    public void setCanBeSubEnabled(boolean canBeSubEnabled) { this.canBeSubEnabled = canBeSubEnabled; }

    public boolean isCheckingEnabled() { return checkingEnabled; }
    public void setCheckingEnabled(boolean checkingEnabled) { this.checkingEnabled = checkingEnabled; }

    public boolean isAccountFeeEnabled() { return accountFeeEnabled; }
    public void setAccountFeeEnabled(boolean accountFeeEnabled) { this.accountFeeEnabled = accountFeeEnabled; }

    public boolean isCheckLimitEnabled() { return checkLimitEnabled; }
    public void setCheckLimitEnabled(boolean checkLimitEnabled) { this.checkLimitEnabled = checkLimitEnabled; }

    public boolean isInterestEnabled() { return interestEnabled; }
    public void setInterestEnabled(boolean interestEnabled) { this.interestEnabled = interestEnabled; }

    public Double getAccounFee() { return accounFee; }
    public void setAccounFee(Double accounFee) { this.accounFee = accounFee; }

    public Long getCheckLimit() { return checkLimit; }
    public void setCheckLimit(Long checkLimit) { this.checkLimit = checkLimit; }

    public Double getInterestRate() { return interestRate; }
    public void setInterestRate(Double interestRate) { this.interestRate = interestRate; }

    public boolean isOverdraftLimitEnabled() { return overdraftLimitEnabled; }
    public void setOverdraftLimitEnabled(boolean overdraftLimitEnabled) { this.overdraftLimitEnabled = overdraftLimitEnabled; }

    public Double getOverdraftLimit() { return overdraftLimit; }
    public void setOverdraftLimit(Double overdraftLimit) { this.overdraftLimit = overdraftLimit; }

    public Double getOverdraftFee() { return overdraftFee; }
    public void setOverdraftFee(Double overdraftFee) { this.overdraftFee = overdraftFee; }
}
