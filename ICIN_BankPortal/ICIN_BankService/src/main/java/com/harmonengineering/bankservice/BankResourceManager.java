package com.harmonengineering.bankservice;
import com.harmonengineering.entity.*;
import java.util.UUID;

public class BankResourceManager extends ResourceManager
{
    UUID AccountRepositoryResourceID ;
    UUID TxLogRecordRepositoryResourceID ;
    public BankResourceManager()
    {
        AccountRepositoryResourceID = AddManagedResource( new AccountRecordRepositoryResource() ) ;
    }
    public AccountRecordRepositoryResource getRepository( )
    {
        return (AccountRecordRepositoryResource) super.getResource( AccountRepositoryResourceID );
    }
    public ManagedResource getResource( ) { return this.getResource( AccountRepositoryResourceID ) ; }
}
class RestApiResponder
{

}
class ServiceOrderResource< SERVICE_ORDER_T > extends ManagedResource
{
    SERVICE_ORDER_T serviceOrder ;
    public SERVICE_ORDER_T getServiceOrder( ) { return serviceOrder ; }
    public void setServiceOrder( SERVICE_ORDER_T order ) { serviceOrder = order ; }
}
class ServiceProcessResource< SERVICE_PROCESS_T > extends ManagedResource
{
    SERVICE_PROCESS_T serviceProcess ;
    public SERVICE_PROCESS_T getServiceProcess( ) { return serviceProcess ; }
    public void setServiceProcess( SERVICE_PROCESS_T process ) { serviceProcess = process ; }
}

class AccountWithdrawServiceOrderResource<AccountWithdrawServiceOrder>
            extends ServiceOrderResource<AccountWithdrawServiceOrder>{}

class AccountDepositServiceOrderResource<AccountDepositServiceOrder>
            extends ServiceOrderResource<AccountDepositServiceOrder>{}

class AccountCreateServiceOrderResource<AccountCreateServiceOrder>
            extends ServiceOrderResource<AccountCreateServiceOrder>{}

class BankServiceProcessResource extends ServiceProcessResource<BankServiceProcess> {}
class BankServiceOrderResource extends ServiceOrderResource<BankServiceOrder> {}

class AccountCapacityRecordRepositoryResource extends RepositoryResource<AccountCapacityRecordRepository> {}
class AccountClassTypeRecordRepositoryResource extends RepositoryResource<AccountClassTypeRecordRepository> { }
class AccountRecordRepositoryResource extends RepositoryResource<AccountRecordRepository>{}
class TxLogRecordRepositoryResource extends RepositoryResource<TxLogRecordRepository> {}
class UserRepositoryResource extends RepositoryResource<UserRepository>
{ UserRepositoryResource( UserRepository urepo ) { super( urepo ) ; }}

class AccountCapacityRecordResource extends EntityResource<AccountCapacityRecord> {}
class AccountClassTypeRecordResource extends EntityResource<AccountClassTypeRecord> { }
class AccountRecordResource extends EntityResource<AccountRecord>{}
class TxLogRecordResource extends EntityResource<TxLogRecord> {}
class UserResource extends EntityResource<User>{}

//class AccountCreateProcessResource extends ProcessResource<AccountCreateProcess> {}
class AccountWithdrawProcessResource extends ProcessResource<AccountWithdrawProcess>{}
class AccountDepositProcessResource extends ProcessResource<AccountDepositProcess> {}

class AccountProcess extends ProcessResource<BankServiceProcess>{}


