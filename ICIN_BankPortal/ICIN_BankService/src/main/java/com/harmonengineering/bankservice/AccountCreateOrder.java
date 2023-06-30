package com.harmonengineering.bankservice;
import com.harmonengineering.entity.*;
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


    public AccountCreateOrder() {  }

    //public BankServiceOrder manifestFactory()  { return this ; };
    AccountCreateOrderProcess orderProcess = new AccountCreateOrderProcess( this ) ; ;
    public BankServiceOrder manifestFactory() { return orderProcess ; }
//    public void fulfill()
//    {
////        System.out.println( this.getClass().getName());
////        UserID = this.getUserID() ;
////
////        System.out.println( this.getClass().getName() + " UserID " + UserID );
////        logger.info( this.getClass().getName()) ;
////        logger.info( "Received Order, User ID: " + UserID ) ;
////        processTransaction();
//    } ;
    public void processTransaction() {   }
    public void loadResources() {   }
    public void updateResources(){  }
    public void saveResources() {   }

}
class AccountCreateOrderProcess extends BankServiceOrder implements BankServiceOrderInterface
{
    AccountCreateOrder interfaceOrder ;
    private AccountRecord newAccount ;
    AccountCreateOrderProcess( AccountCreateOrder order ) { interfaceOrder = order ; }
    Long UserID ;
    public void fulfill()
    {
        System.out.println( this.getClass().getName());
        UserID = interfaceOrder.getUserID() ;

        System.out.println( this.getClass().getName() + " UserID " + UserID );
        logger.info( this.getClass().getName()) ;
        logger.info( "Received Order, User ID: " + UserID ) ;
        processTransaction();
    } ;
    public void processTransaction()
    {
        System.out.println( "proccesTransaction" ) ;
        logger.info( "Processing User ID: " + UserID ) ;

        // begin transaction
        // get the records
        loadResources();
        // validate verify - check compatibility and capabilities etc..
        updateResources() ;
        saveResources() ;
        // commit transaction
        // or catch rollback transaction.
    } ;
    @Override public void loadResources()
    {
        logger.info( "Loading Resources, User ID: " + UserID ) ;

        // presently only need to verify that the user exists.
        // should also check whether the user can create account...
        // potentially want to validate the authority of the requesting user
        // as well.
        //if ( !userRepository.existsById( UserID ))
        if ( !resources.userRepository.existsById(UserID))
            throw new RuntimeException( "UserID does not Exist in AccountCreate (service order): UserID - " + UserID )  ;

        // at this time need to derive the id of classtype from the passed string value(s)
        AccountClassTypeRecord r = new AccountClassTypeRecord() ;
        r.setIdTagname( interfaceOrder.getAccountClassTypeTag() );
        Example<AccountClassTypeRecord> example = Example.of( r ) ;
        //r = classTypeRecordRepository.findOne( example ).orElseThrow() ;
        r = resources.classTypeRecordRepository.findOne( example ).orElseThrow() ;
        interfaceOrder.setAccountClassType( r.getAccountClassTypeId() ) ;

        // now that have the classtype and the capabilites resource
        // get the caps and verify the transaction validity and account capacity for it.
        /// perhaps here for  now just get it and print it's values on the log
        AccountCapacityRecord cap = resources.capacityRecordRepository.findByClassTypeId( interfaceOrder.getAccountClassType()) ;
        logger.info( cap.getIdTagname() + ": " + "can be master -> " + cap.isCanBeMasterEnabled()) ;
        logger.info( cap.getIdTagname() + ": " + "can be sub -> " + cap.isCanBeSubEnabled()) ;
        logger.info( cap.getIdTagname() + ": " + "account fee enabled: " + cap.isAccountFeeEnabled()) ;
        logger.info( cap.getIdTagname() + ": " + "Checking -> " + cap.isCheckingEnabled()) ;
        logger.info( cap.getIdTagname() + ": " + "Check Limit -> " + cap.isCheckLimitEnabled()) ;
        logger.info( cap.getIdTagname() + ": " + "Interest enabled -> " + cap.isInterestEnabled()) ;
        logger.info( cap.getIdTagname() + ": " + "Overdraft Limit enabled -> " + cap.isOverdraftLimitEnabled()) ;

        logger.info( cap.getIdTagname() + ": " + "Account Enabled -> " + cap.isAccountEnabled())  ;
        logger.info( cap.getIdTagname() + ": " + "Withdraw Enabled -> " + cap.isWithdrawEnabled())  ;
        logger.info( cap.getIdTagname() + ": " + "Deposit Enabled -> " + cap.isDepositEnabled() )  ;
        logger.info( cap.getIdTagname() + ": " + "Transfer Enabled -> " + cap.isTransferEnabled())  ;
        logger.info( cap.getIdTagname() + ": " + "IntraAccount Transfer Enabled -> " + cap.isIntraAccountTransferEnabled()) ;
        logger.info( cap.getIdTagname() + ": " + "InterAccount Transfer Enabled -> " + cap.isInterAccountTransferEnabled())  ;
        logger.info( cap.getIdTagname() + ": " + "InterBank Transfer Enabled -> " + cap.isInterBankTransferEnabled())  ;

        if ( cap.isAccountFeeEnabled() ) logger.info( "Account fee: " +  cap.getAccountFee() );
        if ( cap.isCheckLimitEnabled() ) logger.info( "Check Limit: " +  cap.getCheckLimit() );
        if ( cap.isInterestEnabled() ) logger.info( "Interest Rate: " +  cap.getInterestRate() );
        if ( cap.isOverdraftLimitEnabled()) {
            logger.info("Overdraft Limit" + cap.getOverdraftLimit());
            logger.info("Overdraft Fee" + cap.getOverdraftFee());
        }

    }
    @Override public void updateResources()
    {
        logger.info( "Updating Resources, User ID: " + UserID ) ;
        logger.info("Creating account record: " + UserID);
        newAccount = new AccountRecord();
        newAccount.setAccountNumber( interfaceOrder.getAccountNumber());
        newAccount.setAccountName(interfaceOrder.getAccountName());
        //newAccount.setAccountClass( this.AccountClass ) ;
        //newAccount.setAccountType(this.AccountType);
        newAccount.setUserId(UserID);
        newAccount.setAccountBalance(interfaceOrder.getAccountBalance());
        newAccount.setAccountClassType( interfaceOrder.getAccountClassType() );
//        if ( cap.isCanBeSubEnabled() ) // i need to get this resource directly
//            currnty only can query based on what is in the () -> table
//            now i need to accociate new in that same toble.

    }
    @Override public void saveResources()
    {
        logger.info( "Saving Resources, User ID: " + UserID ) ;
        newAccount = resources.accountRecordRepository.save( newAccount ) ;
        Long Id = newAccount.getID() ;
        interfaceOrder.setID( Id );
        interfaceOrder.setAccountID( Id ) ;
        System.out.println( "this.ID " + interfaceOrder.getID() ) ;
        System.out.println( "this.accountID " + interfaceOrder.getAccountID() ) ;
    }
}
