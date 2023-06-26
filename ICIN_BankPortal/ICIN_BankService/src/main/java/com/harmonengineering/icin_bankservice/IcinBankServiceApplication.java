package com.harmonengineering.icin_bankservice;

import com.harmonengineering.authentication.AuthenticateResult;
import com.harmonengineering.authentication.AuthenticationService;
import com.harmonengineering.authentication.CustomUserDetailsService;
import com.harmonengineering.beans.User;
import com.harmonengineering.beans.UserPrincipal;
import com.harmonengineering.controller.MainController;
import com.harmonengineering.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableJpaRepositories(basePackages = "com.harmonengineering.entity")
@EntityScan("com.harmonengineering.entity")
@SpringBootApplication(scanBasePackages =
        { "com.harmonengineering", "com.harmonengineering.beans","com.harmonengineering.entity",
                "com.harmonengineering.service",
                "com.harmonengineering.controller",
                "com.harmonengineering.bankservice"
                },
            exclude = { SecurityAutoConfiguration.class })
    public class IcinBankServiceApplication implements CommandLineRunner {

    @Autowired public AuthenticationService authenticationService ;
    //@Autowired public UserCredentialRepository userCredentialRepository ;
    @Autowired public CustomUserDetailsService userDetailsService ;
    @Autowired public PasswordEncoder encoder ;
    private static final Logger logger = LoggerFactory.getLogger(IcinBankServiceApplication.class);

    public static void main(String[] args)
    {
        SpringApplication.run(IcinBankServiceApplication.class, args);
    }

//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurerAdapter() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/api/**").allowedOrigins("http://localhost:4200").
//                        allowedMethods("GET,PUT,POST,DELETE").
//                        allowedHeaders( "Requestor-Type") ;
//            }
//        };
//    }

    @Override public void run(String... arg0) throws Exception {
        System.out.println("Hello world from Command Line Runner");
//            logger.info("this is a info message");
//            logger.warn("this is a warn message");
//            logger.error("this is a error message");

        runAccountDataServiceTest();
        //runUserAuthenticationTest();
    }
    @Autowired AccountRecordRepository accountRecordRepository ;
    @Autowired
    AccountCapacityRecordRepository accountCapacityRecordRepository ;
    void runAccountDataServiceTest()
    {
        logger.info("Listing Master accounts: " ) ;
        Long ID = null;
       List<AccountRecord> accountRecordList = accountRecordRepository.findAllMaster() ;
       Iterator<AccountRecord> I ;
       I = accountRecordRepository.findAllMaster().iterator() ;
       while( I.hasNext() )
       {
           AccountRecord r = I.next() ;
           logger.info( r.getID() + "\t" + r.getAccountNumber() ) ;
           ID = r.getID() ;
       }
        logger.info("Listing  sub-accounts: " ) ;
        I = accountRecordRepository.findAllSubsByMasterId( ID ).iterator() ;
        while( I.hasNext() )
        {
            AccountRecord r = I.next() ;
            logger.info( r.getID() + "\t" + r.getAccountNumber() ) ;
            ID = r.getID() ;
        }
        logger.info( "Showing master for sub ID: " + ID ) ;
        AccountRecord r = accountRecordRepository.getMasterBySubId( ID ) ;
        logger.info( r.getID() + "\t" + r.getAccountNumber() ) ;

        // AccountCapacity
        logger.info("Listing Account Capacity(capabilities) Records: " ) ;
        //Long ID = null;
        //List<AccountRecord> accountCapacityRecordList = accountRecordRepository.findAllMaster() ;
        Iterator<AccountCapacityRecord> acI ;
        acI = accountCapacityRecordRepository.findAll().iterator() ;
        while( acI.hasNext() )
        {
            AccountCapacityRecord acRecord = acI.next() ;
            logger.info( r.getID() + "\t" + acRecord.getIdTagname() ) ;
            ID = acRecord.getID() ;
        }
    }
    void runUserAuthenticationTest()
    {
        ArrayList<String> auths;
        UserPrincipal user = new UserPrincipal();
        user.setUserName("user");
//        user.assignPasswordEncoder(encoder);
//        user.setUserPassword( "{bcrypt}"+encoder.encode("password"));
        user.setUserPassword("password");

        auths = new ArrayList<>();
        auths.add("USER");
        user.GrantUserAuthorities(auths);

        UserPrincipal admin = new UserPrincipal();
        admin.setUserName("admin");
//        admin.assignPasswordEncoder(encoder);
//        admin.setUserPassword("{bcrypt}"+encoder.encode("admin"));
//        admin.unpackPassword();
//
        admin.setUserPassword("admin");
        auths = new ArrayList<>();
        auths.add("USER");
        auths.add("ADMIN");
        admin.GrantUserAuthorities(auths);

        AuthenticateResult result ;
//        result = authenticationService
//                .getAuthenticator()
//                .AddUser(user, user.getUserPassword());
//        userDetailsService.DeleteUser( user ) ;
        //result = userDetailsService.addUser( user ) ;
//        result = userDetailsService.addNewUser( user ) ;
        //      System.out.println( result.getResultStatus() ) ;
        //      System.out.println( result.getUser().getUserName());
        //      System.out.println( result.getUser().getUserPassword());
        // user.buildFromUser( result.getUser()) ;


        result = authenticationService
                .getAuthenticator()
                .AddUser(admin, admin.getUserPassword()) ;

        System.out.println( result.getResultStatus() ) ;
        System.out.println( result.getUser().getUserName());
        System.out.println( result.getUser().getUserPassword());

        //userDetailsService.UpdatePassword( user, "password", "newpassword") ;

        //List<UserCredential> userCredentialList = userCredentialRepository.findAll() ;
        List<UserCredential> userCredentialList = userDetailsService.userCredentialList() ;
        System.out.println("=============[\tName\t][\tPassword\t]================") ;
        Iterator<UserCredential> i = userCredentialList.iterator() ;
        UserCredential userCredential ;
        UserPrincipal newUser ; //= new UserPrincipal();
        while( i.hasNext() )
        {
            userCredential = i.next() ;
            //newUser = new UserPrincipal(userCredential) ;
            newUser = new UserPrincipal() ;
            newUser.buildFromUser( new User(userCredential));
            //           newUser.setUserName( userCredential.getUserName());
            //           newUser.setUserPassword( userCredential.getUserPass());

//            result = authenticationService.getAuthenticator().AddUser(
//                    newUser, newUser.getUserPassword()) ;

            result = userDetailsService.addUser( newUser ) ;
            System.out.println( "\t" + userCredential.getUserName() + "\t\t" +
                    userCredential.getUserPass() + "\t\t" +
                    result.getResultStatus().toString()) ;
            System.out.println( "\t" + result.getUser().getUserName() + "\t\t" +
                    result.getUser().getUserPassword() + "\t\t" +
                    result.getResultStatus().toString()) ;
        }
        userDetailsService.UpdatePassword( user, "password", "newpassword") ;


    }

}
