package com.harmonengineering.entity;


import javax.persistence.*;

@Entity
@Table( name="account_master_sub" )
public class AccountMasterSubLinkRecord
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name="ID" )                    private Long ID ;
    @Column( name="master_account" )             private Long masterAccountID ;
    @Column( name="sub_account" )                private Long subAccountID ;

    public AccountMasterSubLinkRecord() {}

    public Long getID() { return ID; }
    public void setID(Long ID) { this.ID = ID; }
    public Long getMasterAccountID() { return masterAccountID; }
    public void setMasterAccountID(Long masterAccountID) { this.masterAccountID = masterAccountID; }
    public Long getSubAccountID() { return subAccountID; }
    public void setSubAccountID(Long subAccountID) { this.subAccountID = subAccountID; }
}
