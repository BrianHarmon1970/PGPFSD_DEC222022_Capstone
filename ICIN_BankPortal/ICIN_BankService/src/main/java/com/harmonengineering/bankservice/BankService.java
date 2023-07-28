package com.harmonengineering.bankservice;

import com.harmonengineering.entity.*;
import org.slf4j.Logger;
import java.util.HashMap;

class _static
{
    static BankService  bankService ;
}
public class BankService
{
    BankServiceResources resources  ;
    AccountCreateProcess createProcess ;
    AccountDepositProcess depositProcess ;
    AccountWithdrawProcess withdrawProcess ;
    AccountTransferProcess transferProcess ;

    CAccountTransactionContext accountTransactionContext ;
    CAccountDualTransactionContext accountDualTransactionContext ;
    CAccountCreateContext createContext ;

    HashMap<String,BankServiceProcess> orderProcessMap = new HashMap<>();

    public BankServiceOrder serviceOrder( BankServiceOrder order )
    {
        try {
            System.out.println("Fulfilling order: ");
            //BankServiceProcess orderProcess = order.manifestFactory() ;
            BankServiceProcess orderProcess = resolveProcess(order);
            return orderProcess.fulfill(order);
        }
        catch (Exception e)
        {
            resources.logger.info( "Caught exception: " + e.getMessage() ) ;
        }
        return order ;
    }
    public void setResourceProviders( Logger logger , TxLogRecordRepository txRepo,
                                     AccountRecordRepository acctRepo,
                                     UserRepository userRepo,
                                     AccountClassTypeRecordRepository classTypeRepo,
                                     AccountCapacityRecordRepository capacityRepo,
                                     AccountMasterSubLinkRecordRepository linkRepo,
                                      MasterTransactionRecordRepository mtRepo )
    {
        resources = new BankServiceResources() ;
        resources.setResourceProviders( logger, txRepo, acctRepo, userRepo,
                                                classTypeRepo, capacityRepo, linkRepo, mtRepo );
        installProcesses();
    }
    public void installProcesses( )
    {
        createProcess = resources.getAccountCreateProcess() ;
        depositProcess = resources.getAccountDepositProcess() ;
        withdrawProcess = resources.getAccountWithdrawProcess() ;
        transferProcess = resources.getAccountTransferProcess() ;

        // Master Transaction Repository and Record(s)
        // work to do here...done 7/28/2023
        accountTransactionContext = new CAccountTransactionContext( resources ) ;
        accountTransactionContext.installManagedResources();

        accountDualTransactionContext = new CAccountDualTransactionContext( resources ) ;
        accountDualTransactionContext.installManagedResources() ;

        createContext = new CAccountCreateContext( resources ) ;
        createContext.installManagedResources() ;

        createProcess.setServiceContext( createContext ) ;
        depositProcess.setServiceContext( accountTransactionContext ) ;
        withdrawProcess.setServiceContext( accountTransactionContext ) ;
        transferProcess.setServiceContext( accountDualTransactionContext ) ;

        orderProcessMap.put( AccountCreateOrder.class.toString(), createProcess ) ;
        orderProcessMap.put( AccountDepositOrder.class.toString(), depositProcess ) ;
        orderProcessMap.put( AccountWithdrawOrder.class.toString(), withdrawProcess ) ;
        orderProcessMap.put( AccountTransferOrder.class.toString(), transferProcess ) ;

        System.out.println( AccountCreateOrder.class.toString()) ;
        System.out.println( AccountDepositOrder.class.toString()) ;
        System.out.println( AccountWithdrawOrder.class.toString()) ;
        System.out.println( AccountTransferOrder.class.toString()) ;
    }
    BankServiceProcess resolveProcess( BankServiceOrder order ) throws Exception
    {
        System.out.println(  order.getClass() ) ;
        BankServiceProcess p = orderProcessMap.get( order.getClass().toString() ) ;
        if ( p == null ) throw new Exception( "Unknown order type." ) ;
        return p ;
    }
}
