package com.harmonengineering.bankservice;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantLock;

class ResourceManager
{
    HashMap<UUID,ManagedResource> ResourceMap ;
    public ResourceManager()
    {
        ResourceMap = new HashMap<>() ;
    }
    ManagedResource getResource( UUID id )
    {
        ManagedResource rsrc ;
        rsrc = ResourceMap.get( id ) ;
        System.out.println("Resource Accessed: " + rsrc.getResourceId()
                + "\t" + rsrc.getTypename()
                + "\t" + rsrc.toString() ) ;
        return rsrc ;
    }
    UUID AddManagedResource( ManagedResource rsrc )
    {
        ResourceMap.put( rsrc.getResourceId(), rsrc ) ;
        System.out.println("Resource Added: " + rsrc.getResourceId()
                + "\t" + rsrc.getTypename()
                + "\t" + rsrc.toString() ) ;
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
    String typename ;

    public ManagedResource()
    {
        resourceId  = UUID.randomUUID() ;
        inUse = new ResourceLock() ; inUse.setLocked( false ) ;
        concurrencyLock = new ResourceLock() ; concurrencyLock.setLocked( false ) ;
        accessLock = new ResourceLock() ; accessLock.setLocked( false ) ;
        capacityLock = new ResourceLock() ; capacityLock.setLocked( false ) ;
        setTypename( this.getClass().getSimpleName() );
    }
    void setTypename( String typename ) { this.typename = typename ;}
    String getTypename() { return typename ; }

    public UUID getResourceId() { return resourceId ; }
    private void setResourceId(UUID resourceId) { this.resourceId = resourceId; }


    public ResourceLock getInUse() { return inUse; }
    public void setInUse(ResourceLock inUse) { this.inUse = inUse; }
    public ResourceLock getConcurrencyLock() { return concurrencyLock; }
    public void setConcurrencyLock(ResourceLock concurrencyLock) { this.concurrencyLock = concurrencyLock;  }
    public ResourceLock getAccessLock() { return accessLock; }
    public void setAccessLock(ResourceLock accessLock) { this.accessLock = accessLock; }
    public ResourceLock getCapacityLock() { return capacityLock; }
    public void setCapacityLock(ResourceLock capacityLock) { this.capacityLock = capacityLock; }
}
interface IProcessContext {}
class CProcessContext{}

class ProcessResource<PROCESSOR_T> extends ManagedResource
{
    private PROCESSOR_T m_process ;
    CProcessContext m_procCtx ;
    public ProcessResource() {}
    public ProcessResource( PROCESSOR_T p ) { m_process = p ; }
    void setProcessor( PROCESSOR_T p ) { m_process = p ;}
    PROCESSOR_T getProcessor( ) { return m_process ; }
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
    public void setLocked(boolean locked)
    {
        try {
            lockMutex.lock();
            this.locked = locked;
        } finally { lockMutex.unlock(); }
    }
}

// JPA Resource Management Components
class EntityResource< ENTITY_T > extends ManagedResource
{
    ENTITY_T entity ;

    public ENTITY_T getEntity() { return entity; }
    public void setEntity(ENTITY_T entity) { this.entity = entity; }
}
class RepositoryResource<REPOSITORY_T> extends ManagedResource
{
    REPOSITORY_T repository ;

    public RepositoryResource() {}
    public RepositoryResource( REPOSITORY_T repo  ) { setRepository( repo ) ;}

    public REPOSITORY_T getRepository() { return repository; }
    public void setRepository( REPOSITORY_T repository) { this.repository = repository; }
}
// Managed JPA Components
class CManagedEntity<ENTITY_T>
{
    UUID RES_ID ;
    ResourceManager rm ;
    EntityResource<ENTITY_T> recordResource ;
    //ENTITY_T    recordEntity ;
    public CManagedEntity() { }
    ManagedResource getResource() { return rm.getResource(RES_ID) ;}
    ENTITY_T getRecordEntity() { return recordResource.getEntity() ; }
    public void setRecordEntity( ENTITY_T record ) { recordResource.setEntity( record ) ;}

    ENTITY_T getEntity() { return recordResource.getEntity() ; }
    public void setEntity( ENTITY_T record ) { recordResource.setEntity( record ) ;}

    public EntityResource<ENTITY_T> getRecordResource() { return recordResource ; }
    public void  setRecordResource(EntityResource<ENTITY_T> er ) { recordResource = er ; }
    public void Install( ResourceManager rm )
    {
        this.rm = rm ;
        recordResource = new EntityResource<ENTITY_T>() ;
        RES_ID = rm.AddManagedResource( recordResource ) ;
    }
    void UUID( UUID uuid ) { RES_ID = uuid ; }
    UUID UUID() { return RES_ID ; }
}
class CManagedRepository<REPOSITORY_T>
{
    UUID RES_ID ;
    ResourceManager rm ;
    RepositoryResource<REPOSITORY_T> repositoryResource ;
    //ENTITY_T    recordEntity ;
    public CManagedRepository() { repositoryResource = new RepositoryResource<REPOSITORY_T>() ; }
    ManagedResource getResource() { return rm.getResource(RES_ID) ;}
    REPOSITORY_T getRepository() { return repositoryResource.getRepository() ; }
    public void setRepository( REPOSITORY_T repo ) { repositoryResource.setRepository( repo ) ;}

    public RepositoryResource<REPOSITORY_T> getRepositoryResource() { return repositoryResource ; }
    public void  setRepositoryResource(RepositoryResource<REPOSITORY_T> rr ) { repositoryResource = rr ; }
    public void Install( ResourceManager rm )
    {
        this.rm = rm ;
        repositoryResource = new RepositoryResource<REPOSITORY_T>() ;
        RES_ID = rm.AddManagedResource( repositoryResource ) ;
    }
    void UUID( UUID uuid ) { RES_ID = uuid ; }
    UUID UUID() { return RES_ID ; }
}
class CManagedProcess< PROCESS_T >
{
    UUID RES_ID ;
    ResourceManager rm ;
    ProcessResource<PROCESS_T> processResource ;
    //ENTITY_T    recordEntity ;
    public CManagedProcess() {}
    ManagedResource getResource() { return rm.getResource(RES_ID) ;}
    PROCESS_T getProcess() { return processResource.getProcessor() ; }
    public void setProcess( PROCESS_T proc ) { processResource.setProcessor( proc ); }

    public ProcessResource<PROCESS_T> getProcessResource() { return processResource ; }
    public void  setRepositoryResource( ProcessResource<PROCESS_T> pr ) { processResource = pr ; }
    public void Install( ResourceManager rm )
    {
        this.rm = rm ;
        processResource = new ProcessResource<PROCESS_T>() ;
        RES_ID = rm.AddManagedResource( processResource ) ;
    }
    void UUID( UUID uuid ) { RES_ID = uuid ; }
    UUID UUID() { return RES_ID ; }
}
class TManagedResource< RESOURCE_T >
{
//    UUID RES_ID ;
//    ResourceManager rm ;
//    RepositoryResource<REPOSITORY_T> repositoryResource ;
//    //ENTITY_T    recordEntity ;
//    public CManagedRepository() {}
//    ManagedResource getResource() { return rm.getResource(RES_ID) ;}
//    REPOSITORY_T getRepository() { return repositoryResource.getRepository() ; }
//    public void setRepository( REPOSITORY_T repo ) { repositoryResource.setRepository( repo ) ;}
//
//    public RepositoryResource<REPOSITORY_T> getRepositoryResource() { return repositoryResource ; }
//    public void  setRepositoryResource(RepositoryResource<REPOSITORY_T> rr ) { repositoryResource = rr ; }
//    public void Install( ResourceManager rm )
//    {
//        this.rm = rm ;
//        repositoryResource = new RepositoryResource<REPOSITORY_T>() ;
//        RES_ID = rm.AddManagedResource( repositoryResource ) ;
//    }
//    void UUID( UUID uuid ) { RES_ID = uuid ; }
//    UUID UUID() { return RES_ID ; }
}


