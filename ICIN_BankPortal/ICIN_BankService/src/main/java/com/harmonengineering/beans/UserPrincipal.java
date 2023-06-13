/* *****************************************
 * File: UserPrincipal.java
 *  12/24/2022 B>S>H>
 */
package com.harmonengineering.beans;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;

public class UserPrincipal extends User implements UserDetails
{
    PasswordEncoder passwordEncoder ;
    private ArrayList<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<SimpleGrantedAuthority>() ;
    private String m_encryptionMethod = "{}";
    public String getEncryptionMethod() { return m_encryptionMethod ; }
    public UserPrincipal() { grantedAuthorities = new ArrayList<SimpleGrantedAuthority>() ;}

    private String userType = "USER";

    public void buildFromUser( User user )
    {
    //    grantedAuthorities = new ArrayList<SimpleGrantedAuthority>() ;
        this.GrantAuthorities( user.getUserAuthorities()) ;
        this.setUserName( user.getUserName()) ;
        this.setUserPassword( user.getUserPassword()) ;
        this.setRecordKey( user.getRecordKey() ) ;

        // ----
        this.userType = user.getUserType() ;
    }
    public void unpackPassword()
    {
        String password = getUserPassword() ;
        if ( password.startsWith("{")) {
            if (password.startsWith("{bcrypt}")) {
                this.setUserPassword( password.substring("{bcrypt}".length()));
                this.m_encryptionMethod = "{bcrypt}" ;
            }
            else if (password.startsWith("{text}")) {
                this.setUserPassword( password.substring("{text}".length()));
                this.m_encryptionMethod = "{text}" ;
            }
            else m_encryptionMethod = "{UNKNOWN_METHOD}"; // leaving it packed as is
        } else m_encryptionMethod = "{raw}" ; // nothing to unpack
//        System.out.println( "unpacked password: " + returnValue ) ;
    }
    public void assignPasswordEncoder( PasswordEncoder encoder )
    {
        passwordEncoder = encoder ;
    }
    public boolean matchPassword( String p1 )
    {
        String p2 = this.getUserPassword() ;
        return p1.equals(p2) ||
                passwordEncoder.matches(p1, p2) ||
                passwordEncoder.matches(p2, p1);

    }
    public void encodePassword()
    {
        encodePackedPassword();
    }
    public void encodeUnpackedPassword()
    {
        unpackPassword(); // assuming it to be so, so making it so
        if ( m_encryptionMethod.equals("{raw}") || m_encryptionMethod.equals("{text}"))
        {
            setUserPassword( passwordEncoder.encode( getUserPassword()));
            m_encryptionMethod = "{bcrypt}" ;
        }
    }
    public void encodePackedPassword()
    {
        encodeUnpackedPassword();
        packPassword();
    }


    public void packPassword()
    {
        if ( !m_encryptionMethod.equals("{UNKNOWN_METHOD}"))
        {
            if ( !m_encryptionMethod.equals("{}") )
            {
                if( m_encryptionMethod.equals("{raw}"))
                    setUserPassword("{bcrypt}"+passwordEncoder.encode( getUserPassword()));
                else
                    setUserPassword( m_encryptionMethod + getUserPassword()) ;
                m_encryptionMethod = "{}" ;
            }
        }
    }
    public void GrantAuthorities( ArrayList<String> authorityList )
    {
        System.out.println(authorityList ) ;
        for (String str : authorityList) {
            System.out.println( str ) ;
            grantedAuthorities.add(new SimpleGrantedAuthority(str));
        }
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return this.getUserPassword() ;
    }

    @Override
    public String getUsername() {
        return this.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
