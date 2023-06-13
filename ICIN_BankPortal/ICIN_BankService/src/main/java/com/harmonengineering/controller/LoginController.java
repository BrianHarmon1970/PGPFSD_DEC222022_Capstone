package com.harmonengineering.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController
{
    @RequestMapping("login")
    public String loginRegister( Model model )
    {
        return("LoginRegister") ;
    }
    @RequestMapping("LoginUser")
    public String login( Model model )
    {
        return("UserLogin") ;
    }
    @RequestMapping("RegisterUser")
    public String register( Model model )
    {
        return("Register") ;
    }

//    @RequestMapping("doRegister")
//    public String doRegister( Model model )
//    {
//        model.addAttribute("Message", "<h1>doRegister!!!</h1>" ) ;
//        return("Message") ;
//    }
//
//    @RequestMapping("doLogin")
//    public String doLogin( Model model )
//    {
//        model.addAttribute( "Message", "<h1>doLogin!!!</h1>" ) ;
//        return("Message") ;
//    }

}
