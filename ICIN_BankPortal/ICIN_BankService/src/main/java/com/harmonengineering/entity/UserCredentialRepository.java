package com.harmonengineering.entity;

import org.hibernate.annotations.SQLInsert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.harmonengineering.entity.UserCredential ;

@Repository
public interface UserCredentialRepository extends JpaRepository<UserCredential,Long>
{

    UserCredential findByUserName(String userName);

    //UserCredential addUserCredential(UserCredential userCredential) ;
    //UserCredential uUserCredential(UserCredential userCredential );
    //void deleteUserCredential(String username);
    //void changePassword(String oldPassword, String newPassword);
    //boolean existsUserCredentialByUserName(String username);
}