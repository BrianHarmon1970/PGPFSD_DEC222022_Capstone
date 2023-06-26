package com.harmonengineering.entity;

import javax.persistence.*;

@Entity
@Table( name = "account_classtype_view" )
public class AccountClassTypeRecord
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "ID" )              private Long ID ;
    @Column( name = "account_class" )   private String accountClass ;
    @Column( name = "account_type" )    private String accountType ;
    @Column( name = "ID_TAGNAME" )      private String idTagname ;

    public Long getID() { return ID; }
    public void setID(Long ID) { this.ID = ID;}
    // informative aliases
    public Long getAccountClassTypeId() { return ID; }
    public void setAccountClassTypeId(Long ID) { this.ID = ID;}


    public String getAccountClass() { return accountClass; }
    public void setAccountClass(String accountClass) {this.accountClass = accountClass; }

    public String getAccountType() { return accountType; }
    public void setAccountType(String accountType) { this.accountType = accountType; }

    public String getIdTagname() { return idTagname; }
    public void setIdTagname(String idTagname) { this.idTagname = idTagname; }
}
