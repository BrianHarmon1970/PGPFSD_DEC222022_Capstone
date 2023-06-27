package com.harmonengineering.bankservice;
import com.harmonengineering.entity.AccountClassTypeRecord;
import com.harmonengineering.entity.AccountClassTypeRecordRepository;
import com.harmonengineering.entity.AccountRecord;
import org.springframework.data.domain.Example;


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
    private Long    AccountClassType ;

    // in-betweens
    private String AccountClass ;
    private String AccountType ;

    // in
    private String AccountClassTypeTag ;
    private Long UserID ;
    private String AccountNumber ;
    private String AccountName ;
    private Double AccountBalance ;
    // ---------------------------- end of interface variables
    // ----------- interface variable interface ( getters and setters )
    public Long getAccountID() { return AccountID; }

    public Long getAccountClassType() {  return AccountClassType; }
    public void setAccountClassType(Long accountClassType) { AccountClassType = accountClassType; }
    public void setAccountID(Long accountID) { AccountID = accountID; }


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
    // --------- end of getters and setters for interface variables ( im sorry im processing the request inside the request
    /// extra comments necessary. count on they will go away in the near future as i restructure and separate process and
    // data.)




    private AccountRecord newAccount ;
    public AccountCreateOrder() {  }

    public BankServiceOrder manifestFactory()  { return null ; };

    //public void fulfill() { System.out.println( this.getClass().getName()  );} ;

    public void fulfill()
    {
        System.out.println( this.getClass().getName());
        UserID = this.getUserID() ;
        System.out.println( "HELLOHELLOHELLOEHELLO.... I was muffled by line 55 - //public void fulfill() { System.out.println( this.getClass().getName()  );} ;");


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
        // at this time need to derive the id of classtype from the passed string value(s)
        AccountClassTypeRecord r = new AccountClassTypeRecord() ;
        r.setIdTagname( this.getAccountClassTypeTag() );
        Example<AccountClassTypeRecord> example = Example.of( r ) ;
        r = classTypeRecordRepository.findOne( example ).orElseThrow() ;
        setAccountClassType( r.getAccountClassTypeId() ) ;

    }
    @Override public void updateResources()
    {
        logger.info( "Updating Resources, User ID: " + UserID ) ;
            logger.info("Creating account record: " + UserID);
            newAccount = new AccountRecord();
            newAccount.setAccountNumber(this.AccountNumber);
            newAccount.setAccountName(this.AccountName);
            //newAccount.setAccountClass( this.AccountClass ) ;
            //newAccount.setAccountType(this.AccountType);
            newAccount.setUserId(UserID);
            newAccount.setAccountBalance(this.AccountBalance);
            newAccount.setAccountClassType( this.AccountClassType );
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
