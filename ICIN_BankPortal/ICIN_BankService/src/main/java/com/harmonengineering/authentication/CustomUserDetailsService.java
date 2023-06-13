package com.harmonengineering.authentication ;

import com.harmonengineering.beans.User;
import com.harmonengineering.beans.UserPrincipal;
import com.harmonengineering.entity.UserCredential;
import com.harmonengineering.entity.UserCredentialRepository;
import com.harmonengineering.entity.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired private UserCredentialRepository userCredentialRepository ;
    @Autowired private AuthenticationService authenticationService ;
    @Autowired private PasswordEncoder passwordEncoder ;

    @Override
    public UserDetails loadUserByUsername(String userName) {
        UserCredential userCred = userCredentialRepository.findByUserName( userName );
        if (userCred == null) {
            throw new UsernameNotFoundException(userName);
        }
        UserPrincipal userPrincipal = new UserPrincipal() ;
        userPrincipal.buildFromUser( new User( userCred ));
        userPrincipal.GrantAuthorities( new ArrayList<String>(Collections.singleton("USER")));
        userPrincipal.assignPasswordEncoder( passwordEncoder ) ;
        userPrincipal.unpackPassword();
        addUser( userPrincipal ) ;
        return userPrincipal ;
    }
    public List<UserCredential> userCredentialList() { return userCredentialRepository.findAll() ; }
//    void createUser(UserDetails user){};
//    void updateUser(UserDetails user){};
//    void deleteUser(String username){};
//    void changePassword(String oldPassword, String newPassword){};

    void createUser(UserPrincipal user)
    {
        if ( !userExists( user ) ) userCredentialRepository.save( new UserCredential(user) ) ;
        loadUserByUsername( user.getUserName()) ;
    };
    void updateUser(UserPrincipal user)
    {
        if ( userExists( user ) ) userCredentialRepository.save( new UserCredential(user) ) ;
    };
    void deleteUser(String username)
    {
        UserCredential userCred = userCredentialRepository.findByUserName( username ) ;
        if ( userCred != null )
            userCredentialRepository.delete( userCred ) ;
    };
    void deleteUser( UserPrincipal user )
    {
        deleteUser( user.getUserName() ) ;
    }
    void changePassword(String oldPassword, String newPassword){};
    boolean userExists( UserPrincipal user )
    {
        return userCredentialRepository.existsById( user.getRecordKey() );
    }

    public UserPrincipal UpdatePassword( UserPrincipal user, String oldPassword, String newPassword )
    {
        Authenticate auth = authenticationService.getAuthenticator() ;
        if ( auth.IsAuthenticUser( user, oldPassword )) {
            //auth.RemoveUser(user.getUserName(), oldPassword);
            user.packPassword();
            user.setUserPassword(newPassword);
            user.encodePassword();
            //auth.AddUser(user.getUserName(), user.getUserPassword(),user.getUserPassword()) ;
            auth.UpdateUser( user ) ;
            updateUser( user ) ;
            return user ;
        }
        return null ;
    }
    public AuthenticateResult addNewUser( UserPrincipal user )
    {
        UserCredential uc = new UserCredential( user ) ;
        user.assignPasswordEncoder( passwordEncoder );
        user.encodePackedPassword();
        AuthenticateResult result = addUser( user ) ;
        if ( result.getResultStatus() == AuthStatus.Authenticated ) {


            uc.setUserType("USER");
            //user.packPassword();
            uc = userCredentialRepository.save(new UserCredential(user));
            ;
            //user.unpackPassword();
            user.setRecordKey( uc.getId() ) ;
        }
        return result ;
        //return user ;
    }
    public AuthenticateResult addUser( UserPrincipal user )
    {

        user.unpackPassword();
        AuthenticateResult result = authenticationService.getAuthenticator()
                        .AddUser( user, user.getPassword()) ;
        return result ;
    }

    public void DeleteUser( UserPrincipal user )
    {
        deleteUser( user ) ;
        authenticationService.getAuthenticator().RemoveUser( user ) ;

    }
}