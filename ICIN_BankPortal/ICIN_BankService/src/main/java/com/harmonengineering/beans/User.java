/* *****************************************
 * File: User.java
 *  9/6/2022 B>S>H>
 *  12/24/2022 B>S>H> added authorities as a simple string array
 */
package com.harmonengineering.beans;

import com.harmonengineering.entity.UserCredential;

import java.util.ArrayList;

public class User
{
    private Long RecordKey ;
    private String userName ;
    private String userPassword ;
    private String userType ; // legacy role modeling - administrator vs. user

    private final ArrayList<String> authorities ;

    public User( UserCredential uc )
    {
        this.RecordKey = uc.getId() ;
        this.userName = uc.getUserName() ;
        this.userPassword = uc.getUserPass() ;
        this.authorities = new ArrayList<String>() ;

        // temporary support
        this.userType = uc.getUserType() ;
    }
    public User( ) { this.authorities = new ArrayList<String>() ;}
    public User( String userName, String userPassword )
    {
        this.userName = userName ;
        this.userPassword = userPassword ;
        this.authorities = new ArrayList<String>() ;
    }
    String getUserType() { return this.userType; } void setUserType( String type ) { this.userType = type ; }
    public void GrantUserAuthorities( ArrayList<String> newAuthorities )
    {
        authorities.addAll(newAuthorities) ;
    }
    public ArrayList<String> getUserAuthorities() { return authorities ; }
    public void setRecordKey(Long recordKey) {  RecordKey = recordKey;  }
    public void setUserName(String userName) { this.userName = userName; }
    public void setUserPassword(String userPassword) { this.userPassword = userPassword;  }

    public Long getRecordKey() { return RecordKey; }
    public String getUserName() { return userName; }
    public String getUserPassword() { return userPassword; }

    private void EncodeUserPassword()
    {

    }
}
