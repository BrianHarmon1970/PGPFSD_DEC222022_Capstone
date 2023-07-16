package com.harmonengineering.entity;
import javax.persistence.*;

@Entity
@Table( name="transaction_master")
public class MasterTransactionRecord
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "ID" )              private Long ID ;
    @Column( name = "master_account" )         private Long masterAccountId ;
    @Column( name = "primary_transaction" ) private Long primaryTransactionId ;
    @Column( name = "secondary_transaction" ) private Long secondaryTransactionId ;
//    @Column( name = "transaction_id" ) private Long transactionId ;
//
//    @Column( name = "masterlink_id" ) private Long masterLinkId ; // not on the view
//    @Column( name = "transaction_id" ) private Long transactionId ;
    public void MasterTransactionRecord() {}

    public Long getID() {  return ID;  }
    public void setID(Long ID) {  this.ID = ID;  }

    public Long getMasterAccountId() {  return masterAccountId; }
    public void setMasterAccountId(Long masterAccountId) {  this.masterAccountId = masterAccountId;  }

    public Long getPrimaryTransactionId() { return primaryTransactionId; }
    public void setPrimaryTransactionId(Long primaryTransactionId)
    { this.primaryTransactionId = primaryTransactionId; }

    public Long getSecondaryTransactionId() { return secondaryTransactionId; }
    public void setSecondaryTransactionId(Long secondaryTransactionId)
    { this.secondaryTransactionId = secondaryTransactionId; }

//    public Long getMasterLinkId() {  return masterLinkId; }
//    public void setMasterLinkId(Long masterLinkId) {  this.masterLinkId = masterLinkId;  }
//
//    public Long getTransactionId() { return transactionId; }
//    public void setTransactionId(Long transactionId) { this.transactionId = transactionId; }
}
