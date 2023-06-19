package com.harmonengineering.entity;

import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table( name="transaction")
@EnableTransactionManagement
public class TxLogRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")       private Long ID;

    @Column( name = "creation_time" ) private Date creationTime ;
    @Column( name = "statechange_time" ) private Date modifiedTime ;
    @Column( name = "account_id" ) private Long accountId ;
    @Column( name = "tx_status" ) private String txStatus ;
    @Column( name = "tx_type" ) private String txType ;
    @Column( name = "tx_amount" )  private Double txAmount ;

    public TxLogRecord() {    }

    public Long getID() { return ID; }
    public void setID(Long ID) { this.ID = ID; }

    public Date getCreationTime() { return creationTime; }
    public void setCreationTime(Date creationTime) { this.creationTime = creationTime; }

    public Date getModifiedTime() { return modifiedTime; }
    public void setModifiedTime(Date modifiedTime) { this.modifiedTime = modifiedTime; }

    public Long getAccountId() { return accountId ; }
    public void setAccountId(Long ID) { this.accountId = ID; }

    public String getTxStatus() { return txStatus; }
    public void setTxStatus(String txStatus) { this.txStatus = txStatus; }

    public String getTxType() { return txType; }
    public void setTxType(String txType) { this.txType = txType; }

    public Double getTxAmount() { return txAmount; }
    public void setTxAmount(Double txAmount) { this.txAmount = txAmount; }
}