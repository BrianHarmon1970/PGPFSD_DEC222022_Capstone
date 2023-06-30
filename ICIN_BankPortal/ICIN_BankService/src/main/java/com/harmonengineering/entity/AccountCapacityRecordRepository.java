package com.harmonengineering.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountCapacityRecordRepository extends JpaRepository<AccountCapacityRecord,Long> {
    @Query( value="select  ac.ID, ac.ID_TAGNAME, canbe_master_enabled, \n" +
            "canbe_sub_enabled, checking_enabled, account_fee_enabled,\n" +
            "check_limit_enabled, interest_enabled, account_fee,\n" +
            "check_limit, interest_rate, overdraft_limit_enabled,\n" +
            "overdraft_limit, overdraft_fee, \n" +
            "account_enabled, deposit_enabled, withdraw_enabled, transfer_enabled, \n" +
            "intra_account_transfer_enabled, inter_account_transfer_enabled, \n" +
            "inter_bank_transfer_enabled \n" +
            "from account_classtype_capacity acc , account_capacity ac where acc.ID_TAGNAME = ac.ID_TAGNAME and classtype_id = ? ",
            nativeQuery = true )
    AccountCapacityRecord findByClassTypeId( Long Id  );
   @Query(
    value="select  ac.ID, ac.ID_TAGNAME, canbe_master_enabled, \n" +
            "canbe_sub_enabled, checking_enabled, account_fee_enabled,\n" +
            "check_limit_enabled, interest_enabled, account_fee,\n" +
            "check_limit, interest_rate, overdraft_limit_enabled,\n" +
            "overdraft_limit, overdraft_fee, \n" +
            "account_enabled, deposit_enabled, withdraw_enabled, transfer_enabled, \n" +
            "intra_account_transfer_enabled, inter_account_transfer_enabled, \n" +
            "inter_bank_transfer_enabled \n" +
            "from account_capacities acc , account_capacity ac where acc.capacity_id = ac.ID and account_id = ? ",
            nativeQuery = true )
    AccountCapacityRecord findByAccountId( Long Id  );
}
