package com.harmonengineering.controller;

import com.harmonengineering.entity.User;
import com.harmonengineering.entity.UserCredential;
import com.harmonengineering.entity.UserCredentialRepository;
import com.harmonengineering.entity.UserRepository;
import com.harmonengineering.icin_bankservice.IcinBankServiceApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin( origins = "http://localhost:4200", allowedHeaders = "Requestor-Type", maxAge = 3600 )
@RestController()
@RequestMapping(value = "api/security", produces = "application/json; charset=UTF-8")
public class SecurityController
{
    private static final Logger logger = LoggerFactory.getLogger(IcinBankServiceApplication.class);
    @Autowired  private UserCredentialRepository userCredentialRepository ;
    @Autowired  private UserRepository userRepository ;
    public SecurityController() {}
    @GetMapping(value = "user/credential/all", produces = "application/json; charset=UTF-8")
    List<UserCredential> listAllUsers( )
    {
        List<UserCredential> creds = (List<UserCredential>)userCredentialRepository.findAll() ;
        return creds ;
    }

    @GetMapping(value="register", produces = "text/html; charset=UTF-8")
    public String doRegister( Model model )
    {
        model.addAttribute("Message", "<h1>doRegister!!!</h1>" ) ;
        return("<h1>doRegister!!!</h1>") ;
    }

    @GetMapping(value="login", produces = "text/html; charset=UTF-8")
    public String doLogin( Model model )
    {
        model.addAttribute( "Message", "<h1>doLogin!!!</h1>" ) ;
        return("<h1>doLogin!!!</h1>") ;
    }
    @CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "Requestor-Type")
    @GetMapping( path="user/{id}" )
    User getById_( @PathVariable Long id )
    {

        Optional<User> optional = userRepository.findById( id ) ;
        return (User) optional.orElse(null);
    }

    @PutMapping( path="/user/update/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user)
    {


        user.setId( id ) ;
        logger.info( "saving user: " + user.getUserName());
        logger.info("this is a info message");
        logger.warn("this is a warn message");
        logger.error("this is a error message");

        return userRepository.save( user ) ;
    }
}
