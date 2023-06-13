package com.harmonengineering.service;

import com.harmonengineering.beans.ConfiguredPortNumberBean;
import com.harmonengineering.entity.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import com.harmonengineering.beans.ConfiguredPortNumberBean;
        import com.harmonengineering.entity.OrderItem;
        import com.harmonengineering.entity.User;
        import com.harmonengineering.entity.UserOrder;
        import org.springframework.http.HttpEntity;
        import org.springframework.http.HttpMethod;
        import org.springframework.http.ResponseEntity;
        import org.springframework.stereotype.Service;
        import org.springframework.web.client.RestTemplate;

        import java.util.Arrays;
        import java.util.List;
        import java.util.Objects;

//@Service
public class UserCredentialService
{
    //    @Value(value = "${server.port}")
    public static String serverPort  ;


    //@Autowired
    private ConfiguredPortNumberBean portNumberBean ;

    public static  String serviceUrl = "/api/security/user/credential" ;
    public static String hostString = "http://localhost:" ;
    public static String userResourceUrl ;

    static final RestTemplate restTemplate = new RestTemplate() ;


    //@Autowired
    //Environment environment;

    public UserCredentialService(ConfiguredPortNumberBean portBean
                                  //String serverPort
    )
    {
        this.portNumberBean = portBean ;
        serverPort = portNumberBean.getServerPort() ;
        userResourceUrl = hostString + serverPort + serviceUrl ;

    }
    public List<UserCredential> getUserList( )
    {
        System.out.println( userResourceUrl );
        //System.out.println( hostName() );

        ResponseEntity<UserCredential[]> userList =
                restTemplate.getForEntity(userResourceUrl + "/all",
                        UserCredential[].class);

        return Arrays.asList( userList.getBody() ) ;
    }
    public UserCredential getUserById(Long id)
    {
        ResponseEntity<UserCredential> product = restTemplate.getForEntity( userResourceUrl + "/" + id ,UserCredential.class  ) ;
        return product.getBody() ;
    }
    public void updateUser( UserCredential u )
    {
        HttpEntity<UserCredential> req = new HttpEntity<>( u );
        restTemplate.exchange( userResourceUrl + "/update/" + u.getId(), HttpMethod.PUT, req, UserCredential.class ) ;
    }
    /*
    public List<User> getSearchResultList( User criteriaP )
    {
        StringBuilder sb = new StringBuilder() ;
        sb.append( userResourceUrl ).append("/search") ;
        sb.append( "/").append( criteriaP.getId() ) ;
        sb.append( "/").append( criteriaP.getUserName() ) ;
        sb.append("/").append( criteriaP.getFirstName()) ;
        sb.append("/").append( criteriaP.getLastName()) ;
        System.out.println("StringBuilder -> " + sb.toString()) ;

        ResponseEntity<User[]> userList =
                restTemplate.getForEntity( sb.toString(), User[].class );
        return Arrays.asList(Objects.requireNonNull(userList.getBody())) ;
    }*/
}

