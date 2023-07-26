package com.harmonengineering.bankservice;

public class AccountTransferProcess extends AccountBalanceChangeProcess
        implements BankServiceProcessInterface
{
    @Override
    public BankServiceOrder fulfill(BankServiceOrder order) {
        getServiceContext().logger.info("account-transfer service:") ;
        getServiceContext().logger.info("There is work to be done here!!!") ;
        return order ;
        //return super.fulfill(order);

    }
}
