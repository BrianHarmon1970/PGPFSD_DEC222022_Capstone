package com.harmonengineering.bankservice;


import com.harmonengineering.entity.AccountRecord;

public class AccountCreateOrder extends BankServiceOrder
        implements BankServiceOrderInterface
{
    // user id
    // account number
    // account name
    // account type

    private Long UserID ;
    private Long AccountID ;
    private String AccountNumber ;
    private String AccountName ;
    private String AccountType ;
    private Double AccountBalance ;
    private String AccountClass ;

    private AccountRecord newAccount ;

    public Long getAccountID() { return AccountID; }
    public void setAccountID(Long accountID) { AccountID = accountID; }
    public Long getUserID() { return UserID; }
    public void setUserID(Long userID) { UserID = userID; }
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


    public AccountCreateOrder() {
    }

    public BankServiceOrder manifestFactory()  { return null ; };

    //public void fulfill() { System.out.println( this.getClass().getName()  );} ;

    public void fulfill()
    {
        System.out.println( this.getClass().getName());
        UserID = this.getUserID() ;
        System.out.println( "HELLOHELLOHELLOEHELLO");

        System.out.println( this.getClass().getName() + " UserID " + UserID );
        logger.info( this.getClass().getName()) ;
        logger.info( "Received Order, User ID: " + UserID ) ;
        processTransaction();

    } ;
    public void processTransaction()
    {
        System.out.println( "proccesTransaction" ) ;
        logger.info( "Processing User ID: " + UserID ) ;

        // get the records
        loadResources();
        updateResources() ;
        saveResources() ;
    } ;
    @Override public void loadResources()
    {
        logger.info( "Loading Resources, User ID: " + UserID ) ;
        // presently only need to verify that the user exists.
        // should also check whether the user can create account...
        // potentially want to validate the authority of the requesting user
        // as well.
        if ( !userRepository.existsById( UserID ))
            throw new RuntimeException( "UserID does not Exist in AccountCreate (service order): UserID - " + UserID )  ;

    }
    @Override public void updateResources()
    {
        logger.info( "Updating Resources, User ID: " + UserID ) ;
            logger.info("Creating account record: " + UserID);
            newAccount = new AccountRecord();
            newAccount.setAccountNumber(this.AccountNumber);
            newAccount.setAccountName(this.AccountName);
            newAccount.setAccountClass( this.AccountClass ) ;
            newAccount.setAccountType(this.AccountType);

            newAccount.setUserId(UserID);
            newAccount.setAccountBalance(this.AccountBalance);
        }
    @Override public void saveResources()
    {
        logger.info( "Saving Resources, User ID: " + UserID ) ;
                newAccount = accountRecordRepository.save( newAccount ) ;
        this.ID = this.AccountID = newAccount.getID() ;
        System.out.println( "this.ID " + this.ID ) ;
        System.out.println( "this.accountID " + this.AccountID ) ;

    }
}
