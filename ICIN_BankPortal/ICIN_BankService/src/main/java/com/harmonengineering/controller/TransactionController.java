package com.harmonengineering.controller;

import com.harmonengineering.entity.TxLogRecord;
import com.harmonengineering.entity.TxLogRecordRepository;
import com.harmonengineering.entity.User;
import com.harmonengineering.icin_bankservice.IcinBankServiceApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200",
        methods = { RequestMethod.GET, RequestMethod.DELETE,
                RequestMethod.POST, RequestMethod.PUT } ,
        allowedHeaders = "*", maxAge = 3600 )
@RestController()
//@RequestMapping(value = "api/transaction")
@RequestMapping(value = "${com.harmonengineering.icin_bank.transaction-root}")
public class TransactionController
{
    private static final Logger logger = LoggerFactory.getLogger(IcinBankServiceApplication.class);
    private final TxLogRecordRepository transactionRepository ;

    public TransactionController( TxLogRecordRepository txRepo )
    {
        this.transactionRepository = txRepo ;
    }

    @GetMapping( path= "" )
    public List<TxLogRecord> getTransactions() //- method GET, return all transactions for all accounts
    {
        return transactionRepository.findAll() ;
    }
    @GetMapping( path="{id}")
    public TxLogRecord getTransactionById(  @PathVariable Long id )
    {
        Optional<TxLogRecord> result = transactionRepository.findById( id ) ;
        return result.orElseThrow() ;
    }
    @PostMapping( path = "" )
    public TxLogRecord postTransaction( @RequestBody TxLogRecord tx )
    {
        return transactionRepository.save( tx ) ;
    }
    @PutMapping( path="/{id}",
            produces = "application/json; charset=UTF-8; application/x-www-form-urlencoded",
            consumes = "application/json; charset=UTF-8; application/x-www-form-urlencoded")
    public TxLogRecord updateRecord(@PathVariable Long id, @RequestBody TxLogRecord tx )
    {
        tx.setID( id ); ;
        return transactionRepository.save( tx ) ;
    }
    @PutMapping( path="",
            produces = "application/json; charset=UTF-8; application/x-www-form-urlencoded",
            consumes = "application/json; charset=UTF-8; application/x-www-form-urlencoded")
    public TxLogRecord updateRecord( @RequestBody TxLogRecord tx )
    {
        return transactionRepository.save( tx ) ;
    }
    @DeleteMapping("/{id}")
    public void deleteRecordById(@PathVariable Long id)
    {
        transactionRepository.deleteById(id);
    }

	//-- /api/transaction/{status} - method get, return all transactions for all accounts with specified status
//    @GetMapping( path="/{status}") // conflicts with GET path= '/{id} - unless different by type
//    public List<TxLogRecord> getTransactionsWithStatus(  @PathVariable String status )
//    {
//        TxLogRecord t = new TxLogRecord() ;
//        t.setTxStatus( status ) ;
//        Example<TxLogRecord> example = Example.of( t ) ;
//        return transactionRepository.findAll(example);
//    }

    //-- /api/transaction/foraccount/{id} - method GET, return all transactions for specified account id
	@GetMapping( path="foraccount/{accountId}")
    public List<TxLogRecord> getAccountTransactions( @PathVariable Long accountId )
    {
        TxLogRecord t = new TxLogRecord() ;
        t.setAccountId( accountId ) ;
        Example<TxLogRecord> example = Example.of( t ) ;
        return transactionRepository.findAll(example);
    }

    //-- /api/transaction/foraccount/{id}/{status} - method GET, return all transactions
    //--  for specified account id with specified status
    @GetMapping( path="foraccount/{accountId}/{status}")
    public List<TxLogRecord> getAccountTransactionsWithStatus( @PathVariable Long accountId, @PathVariable String status )
    {
        TxLogRecord t = new TxLogRecord() ;
        t.setAccountId( accountId ) ;
        t.setTxStatus( status ) ;
        Example<TxLogRecord> example = Example.of( t ) ;
        return transactionRepository.findAll(example);
    }
}
