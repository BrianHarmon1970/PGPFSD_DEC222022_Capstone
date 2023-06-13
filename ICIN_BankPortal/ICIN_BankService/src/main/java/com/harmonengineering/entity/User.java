package com.harmonengineering.entity;

import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.*;

@Entity
@Table( name="users")
@EnableTransactionManagement
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name="ID")
    private Long ID ;
    @Column( name="user_name" )
    private String userName ;
//    @Column( name="user_pass" )
//    private String userPass ;
//    @Column( name="user_type" )
//    private String userType ;

    @Column( name="first_name" )
    private String firstName ;
    @Column( name="last_name")
    private String lastName ;
    @Column( name="email")
    private String emailAddress ;
    @Column( name="phone_number")
    private String phoneNumber ;


    public User() {  }
    public void setId( Long id ) { ID = id ; }
    public Long getId() { return ID ; }

    public void setUserName(String userName) { this.userName = userName; }
    public String getUserName() { return userName; }
    /*
    public void setUserPass(String userPass) { this.userPass = userPass; }
    public void setUserType(String userType) { this.userType = userType; }
    */

    /*
    public String getUserPass() { return userPass; }
    public String getUserType() { return userType; }
    */

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getEmailAddress() { return emailAddress; }
    public void setEmailAddress(String emailAddress) { this.emailAddress = emailAddress; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

}
