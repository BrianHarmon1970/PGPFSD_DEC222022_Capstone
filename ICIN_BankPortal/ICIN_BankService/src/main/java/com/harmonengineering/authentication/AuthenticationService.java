package com.harmonengineering.authentication;
import com.harmonengineering.authentication.Authenticate ;
import com.harmonengineering.beans.User;
import com.harmonengineering.beans.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

public class AuthenticationService implements AuthenticationProvider
{
    private Authenticate authenticator  = new Authenticate();
    @Autowired private PasswordEncoder passwordEncoder;
    private Authentication principal;
    private String newPassword;

    public AuthenticationService()
    {
        //    this.authenticator = new Authenticate() ;
    }
    public void PasswordEncoder( PasswordEncoder pe )
    {
        this.passwordEncoder = pe ;
        authenticator.setEncoder( passwordEncoder ) ;
    }

    public com.harmonengineering.authentication.Authenticate getAuthenticator() {
        return authenticator;
    }

    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException
     {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        String encodedPassword = "{bcrypt}" + passwordEncoder.encode( password ) ;

            // use the credentials
            // and authenticate against the third-party system
         AuthenticateResult result = authenticator.AuthenticateUser( name, password ) ;
         System.out.println( "user: " + name ) ;
         System.out.println( "password: " + password ) ;
         System.out.println( " (ignore this) encoded password: " + encodedPassword )  ;
         System.out.println( result.getResultStatus() ) ;
         if (result.getResultStatus() == AuthStatus.Authenticated) {
             UserPrincipal userPrincipal = new UserPrincipal( ) ;
             userPrincipal.buildFromUser( result.getUser() ) ;
             return new UsernamePasswordAuthenticationToken(
                     name, password,
                     userPrincipal.getAuthorities()) ;
         } else return null ;
    }

    public boolean supports(Class<?> authentication) {
        return authentication == UsernamePasswordAuthenticationToken.class ;
    }
//    void saveUser(Authentication principal, String newPassword)
//    {
////        String password = principal.getCredentials().toString() ;
////        authenticator.AddUser( principal.getName(),
////                                password, password ) ;
//    };
    public String toString() {
        return "AuthenticationService"+this.hashCode();
    }
}

//@Component
//public class CustomAuthenticationProvider implements AuthenticationProvider {
//
//    @Override
//    public Authentication authenticate(Authentication authentication)
//            throws AuthenticationException {
//
//        String name = authentication.getName();
//        String password = authentication.getCredentials().toString();
//
//        //if (shouldAuthenticateAgainstThirdPartySystem()) {
//
//            // use the credentials
//            // and authenticate against the third-party system
//            return new UsernamePasswordAuthenticationToken(
//                    name, password, new ArrayList<>());
////        } else {
////            return null;
////        }
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return authentication.equals(UsernamePasswordAuthenticationToken.class);
//    }
//}