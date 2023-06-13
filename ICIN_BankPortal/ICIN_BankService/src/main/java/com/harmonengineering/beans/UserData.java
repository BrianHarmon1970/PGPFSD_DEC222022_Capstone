/* *****************************************
 * File: UserData.java
 *  9/6/2022 B>S>H>
 */
package com.harmonengineering.beans;

public class UserData
{
    private int UserKey ;
    private String firstName ;
    private String lastName ;
    private String emailAddress ;
    private String phoneNumber ;

    public UserData() {}
    public void setUserKey(int userKey) { UserKey = userKey;  }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setEmailAddress(String emailAddress) { this.emailAddress = emailAddress; }

    public int getUserKey() { return UserKey; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmailAddress() { return emailAddress; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
}
