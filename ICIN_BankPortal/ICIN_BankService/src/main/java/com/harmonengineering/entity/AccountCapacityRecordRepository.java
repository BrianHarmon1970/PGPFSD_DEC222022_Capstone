package com.harmonengineering.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountCapacityRecordRepository extends JpaRepository<AccountCapacityRecord,Long> {
    @Query( value="select  ac.ID, ac.ID_TAGNAME, canbe_master_enabled, \n" +
            "\tcanbe_sub_enabled, checking_enabled, account_fee_enabled,\n" +
            "    check_limit_enabled, interest_enabled, account_fee,\n" +
            "    check_limit, interest_rate, overdraft_limit_enabled,\n" +
            "    overdraft_limit, overdraft_fee \n" +
            "    from account_classtype_capacity acc , account_capacity ac where acc.ID_TAGNAME = ac.ID_TAGNAME and classtype_id = ?  \n",
            nativeQuery = true )
    AccountCapacityRecord findByClassTypeId( Long Id  );
//   @Query(
//    value="",
//            nativeQuery = true )
//    AccountCapacityRecord findByAccountNumber( Long Id  );
}
