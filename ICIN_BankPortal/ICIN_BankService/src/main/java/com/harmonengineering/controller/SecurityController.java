package com.harmonengineering.controller;

import com.harmonengineering.entity.User;
import com.harmonengineering.entity.UserCredential;
import com.harmonengineering.entity.UserCredentialRepository;
import com.harmonengineering.entity.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping(value = "api/security", produces = "application/json; charset=UTF-8")
public class SecurityController
{
    @Autowired    private UserCredentialRepository userCredentialRepository ;
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
}
