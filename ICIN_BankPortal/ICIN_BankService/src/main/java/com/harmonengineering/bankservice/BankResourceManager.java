package com.harmonengineering.bankservice;
import com.harmonengineering.entity.*;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantLock;

class ResourceManager
{
    HashMap<UUID,ManagedResource> ResourceMap ;
    public ResourceManager()
    {
        ResourceMap = new HashMap<>() ;
    }
    ManagedResource getResource( UUID id ) { return ResourceMap.get( id ) ; }
    UUID AddManagedResource( ManagedResource rsrc )
    {
        ResourceMap.put( rsrc.getResourceId(), rsrc ) ;
        return rsrc.getResourceId() ;
    }
}

class ManagedResource
{
    UUID resourceId ;
    ResourceLock inUse ;
    ResourceLock concurrencyLock ;
    ResourceLock accessLock ;
    ResourceLock capacityLock ;

    public UUID getResourceId() { return resourceId ; }
    private void setResourceId(UUID lockId) { this.resourceId = lockId; }

    public ResourceLock getInUse() { return inUse; }
    public void setInUse(ResourceLock inUse) { this.inUse = inUse; }
    public ResourceLock getConcurrencyLock() { return concurrencyLock; }
    public void setConcurrencyLock(ResourceLock concurrencyLock) { this.concurrencyLock = concurrencyLock;  }
    public ResourceLock getAccessLock() { return accessLock; }
    public void setAccessLock(ResourceLock accessLock) { this.accessLock = accessLock; }
    public ResourceLock getCapacityLock() { return capacityLock; }
    public void setCapacityLock(ResourceLock capacityLock) { this.capacityLock = capacityLock; }
}
interface IProcessContext
{

}

class CProcessContext
{

}

class ProcessResource<PROCESSOR_T> extends ManagedResource
{
    CProcessContext m_procCtx ;
}

class ResourceLock
{
    UUID lockId ;
    boolean locked ;

    public ResourceLock()
    {
        UUID uid  = UUID.randomUUID() ;
        setLockId( uid ) ;
    }

    public UUID getLockId() { return lockId; }
    private void setLockId(UUID lockId) { this.lockId = lockId; }

    public boolean isLocked() { return locked; }
    public void setLocked(boolean locked) { this.locked = locked; }
    public void lock( ) { setLocked( true ) ; }
    public void release() { setLocked( false ) ; }
}

class ConcurrencyLock extends ResourceLock
{
    static ReentrantLock lockMutex ;
    //static ReentrantLock idMutex ;

    public void setLocked(boolean locked)
    {
        try {
            lockMutex.lock();
            this.locked = locked;
        } finally { lockMutex.unlock(); }
    }
}

// ============

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
    public void setServiceOrder( SERVICE_PROCESS_T process ) { serviceProcess = process ; }
}

class AccountWithdrawServiceOrderResource<AccountWithdrawServiceOrder>
            extends ServiceOrderResource<AccountWithdrawServiceOrder>{}

class AccountDepositServiceOrderResource<AccountDepositServiceOrder>
            extends ServiceOrderResource<AccountDepositServiceOrder>{}

class AccountCreateServiceOrderResource<AccountCreateServiceOrder>
            extends ServiceOrderResource<AccountCreateServiceOrder>{}

class BankServiceProcessResource extends ServiceProcessResource<BankServiceProcess> {}
class BankServiceOrderResource extends ServiceOrderResource<BankServiceOrder> {}
//{
//    BankServiceOrder serviceOrder ;
//    public BankServiceOrder getServiceOrder( ) { return serviceOrder ; }
//    public void setServiceOrder( BankServiceOrder order ) { serviceOrder = order ; }
//}
class RepositoryResource<REPOSITORY_T> extends ManagedResource
{
    REPOSITORY_T repository ;
    public REPOSITORY_T getRepository() { return repository; }
    public void setRepository( REPOSITORY_T repository) { this.repository = repository; }
}

class EntityResource< ENTITY_T > extends ManagedResource
{
    ENTITY_T entity ;

    public ENTITY_T getEntity() { return entity; }
    public void setEntity(ENTITY_T entity) { this.entity = entity; }
}

class AccountDefinitionResourceManager
{

}

class AccountCapacityRecordRepositoryResource extends RepositoryResource<AccountCapacityRecordRepository> {}
class AccountClassTypeRecordRepositoryResource extends RepositoryResource<AccountClassTypeRecordRepository> { }
class AccountRecordRepositoryResource extends RepositoryResource<AccountRecordRepository>{}
class TxLogRecordRepositoryResource extends RepositoryResource<TxLogRecordRepository> {}
class UserRepositoryResource extends RepositoryResource<UserRepository> {}

class AccountCapacityRecordResource extends EntityResource<AccountCapacityRecord> {}
class AccountClassTypeRecordResource extends EntityResource<AccountClassTypeRecord> { }
class AccountRecordResource extends EntityResource<AccountRecord>{}
class TxLogRecordResource extends EntityResource<TxLogRecord> {}
class UserResource extends EntityResource<User>{}



