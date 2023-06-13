package com.harmonengineering.entity;

import com.harmonengineering.beans.User;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.*;

@Entity
@Table( name="users")
@EnableTransactionManagement
public class UserCredential
{
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column( name="ID")         private Long ID ;
        @Column( name="user_name" ) private String userName ;
        @Column( name="user_pass" ) private String userPass ;
        @Column( name="user_type" ) private String userType ;

        public UserCredential( User user ) {
                ID = user.getRecordKey();
                userName = user.getUserName();
                userPass = user.getUserPassword();
                userType = user.getUserAuthorities().get(0);
        }
        public UserCredential( UserCredential user )
        { ID = user.ID ; userName = user.getUserName() ; userPass = user.userPass ; }
        public UserCredential() {  }

        public void setId( Long id ) { ID = id ; }
        public void setUserName(String userName) { this.userName = userName; }
        public void setUserPass(String userPass) { this.userPass = userPass; }
        public void setUserType(String userType) { this.userType = userType; }

        public Long getId() { return ID ; }
        public String getUserName() { return userName; }
        public String getUserPass() { return userPass; }
        public String getUserType() { return userType; }
} ;
