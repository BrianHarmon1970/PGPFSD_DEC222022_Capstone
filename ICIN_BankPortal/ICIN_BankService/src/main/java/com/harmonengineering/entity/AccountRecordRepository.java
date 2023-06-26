package com.harmonengineering.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountRecordRepository extends JpaRepository<AccountRecord,Long>
{
//    @Query( value="select acct.ID, account_classtype.account_class, account_classtype.account_type \n" +
//            "from accounts acct, account_classtype \n" +
//            "where account_classtype.ID = acct.account_classtype ;  \n", nativeQuery = true )
//    List<AccountRecord> findAll() ;
    @Query( value="select a.ID, a.account_class, a.account_type, a.user_id, a.account_number, a.account_name, a.account_balance \n" +
            "\tfrom account_view a, account_master_sub ams \n" +
            "\t\twhere a.ID = ams.master_account \n" +
            "        and ams.master_account = ams.sub_account",
            nativeQuery = true )
    List<AccountRecord> findAllMaster() ;

    @Query( value="select a.ID, a.account_class, a.account_type, a.user_id, a.account_number, a.account_name, a.account_balance from account_view a, account_master_sub ams where a.ID = ams.sub_account and  ams.master_account = ?",
            nativeQuery = true )
    List<AccountRecord> findAllSubsByMasterId(Long ID) ;

    @Query( value="select a.ID, a.account_class, a.account_type, a.user_id, a.account_number, a.account_name, a.account_balance from account_view a, account_master_sub ams where a.ID = ams.master_account and ams.sub_account = ?",
            nativeQuery = true )
    AccountRecord getMasterBySubId(Long ID) ;

}
