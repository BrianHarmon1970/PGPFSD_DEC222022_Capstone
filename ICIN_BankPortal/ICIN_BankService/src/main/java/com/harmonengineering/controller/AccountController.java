package com.harmonengineering.controller;

import com.harmonengineering.entity.AccountRecord;
import com.harmonengineering.entity.AccountRecordRepository;
import com.harmonengineering.entity.UserCredentialRepository;
import com.harmonengineering.entity.UserRepository;
import com.harmonengineering.icin_bankservice.IcinBankServiceApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static java.util.List.of;

@RestController()
@CrossOrigin(origins = "http://localhost:4200",
        methods = { RequestMethod.GET, RequestMethod.DELETE, RequestMethod.POST, RequestMethod.PUT } ,
        allowedHeaders = "*", maxAge = 3600 )

@RequestMapping(value = "api/account")
//,        produces = "application/json; charset=UTF-8;"  )

public class AccountController
{
    private static final Logger logger = LoggerFactory.getLogger(IcinBankServiceApplication.class);
    //@Autowired
    private AccountRecordRepository accountRecordRepository ;
    //@Autowired  private TransactionRepository transactionRepository ;
    public AccountController( AccountRecordRepository acctRepo )
    {
        this.accountRecordRepository = acctRepo ;
    }

//--    /api/account/ - method GET, return all accounts
    @GetMapping( path="")
    public List<AccountRecord> getAllAccounts()
    {
        return accountRecordRepository.findAll() ;
    }
//--    /api/account/ - method POST, create a new account with supplied RequestBody
    @PostMapping( path="" )
    public AccountRecord postNewAccountRecord( @RequestBody AccountRecord newRecord )
    {
        logger.info( "newRecord >" + newRecord.getAccountName() +
                "\naccountBalance = " + newRecord.getAccountBalance()) ;
        //return newRecord ;
        return accountRecordRepository.save(newRecord);
    }
//--    /api/account/ - method PUT, update the record with indicated id in RequestBody supplied update data.
    @PutMapping( path="" )
    public AccountRecord updateAccount( @RequestBody AccountRecord modifiedRecord )
    {
        logger.info( "modifiedRecord > " + modifiedRecord.getAccountName() ) ;
        return accountRecordRepository.save( modifiedRecord ) ;
    }
//--    /api/account/ - method DELETE, delete the existing record with id indicated in RequestBody
    @DeleteMapping( path = "" )
    public void deleteAccount( @RequestBody AccountRecord deleteRecord )
    {
        //Long id = deleteRecord.getID() ;
        accountRecordRepository.delete( deleteRecord ) ;
    }
//--    /api/account/{id} - method GET, return the account with specified id
    @GetMapping( path="{id}")
    public AccountRecord getAccountById( @PathVariable Long id )
    {
        Optional<AccountRecord> result = accountRecordRepository.findById( id ) ;
        return result.orElseThrow() ;
    }
//--    /api/account/{id} - method PUT, update the record having specified id with supplied RequestBody data
    @PutMapping( path="{id}")
    public AccountRecord updateAccountRecordWithId( @PathVariable Long id, @RequestBody AccountRecord updateRecord )
    {
        updateRecord.setID( id ) ;
        return accountRecordRepository.save( updateRecord ) ;
    }
//--    /api/account/{id} - method DELETE, delete the existing record with the specified id
    @DeleteMapping( path="{id}")
    public void deleteAccountById( @PathVariable Long id )
    {
        accountRecordRepository.deleteById( id ) ;
    }
//--    /api/account/foruser/{id} - method GET, return the account(s) with specified user id
    @GetMapping( path = "foruser/{userId}" )
    public List<AccountRecord> getUserAccounts( @PathVariable Long userId )
    {
        AccountRecord acct = new AccountRecord() ;
        acct.setUserId( userId ) ;
        Example<AccountRecord> example = Example.of( acct ) ;
        return accountRecordRepository.findAll( example ) ;
    }
    @GetMapping( path="/master")
    List<AccountRecord> findAllMaster( )
    {
        return accountRecordRepository.findAllMaster() ;
    }
    @GetMapping( path="/masters-for-user/{id}")
    List<AccountRecord> findAllMasterForUser(@PathVariable Long id)
    {
        return accountRecordRepository.findAllMastersByUserId( id ) ;
    }
    @GetMapping( path="/subs-by-master/{id}")
    List<AccountRecord> findAllSubsForMaster(@PathVariable Long id)
    {
        return accountRecordRepository.findAllSubsByMasterId( id ) ;
    }
    @GetMapping( path="/master-by-sub/{id}")
    AccountRecord findMasterForSub(@PathVariable Long id)
    {
        return accountRecordRepository.getMasterBySubId( id ) ;
    }
}
