package com.harmonengineering.icin_bankservice;

//import com.harmonengineering.springsecurity.security.CustomUserDetailsService;

import com.harmonengineering.authentication.AuthenticateResult;
import com.harmonengineering.authentication.AuthenticationService;
import com.harmonengineering.authentication.CustomUserDetailsService;
import com.harmonengineering.beans.ConfiguredPortNumberBean;
import com.harmonengineering.beans.ReportCriteriaBean;
import com.harmonengineering.beans.UserPrincipal;
import com.harmonengineering.beans.ValidatorBean;
import com.harmonengineering.entity.User;
import com.harmonengineering.entity.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.web.servlet.SecurityMarker;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.ArrayList;
import java.util.Optional;

@EnableJpaRepositories
@Configuration
class Config
{
    @Bean    public AuthenticationService newAuthenticationService()
        {   return new AuthenticationService() ;    }
    @Bean    public PasswordEncoder encoder()
        {   return new BCryptPasswordEncoder();     }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//        return encoder;
//    }
    @Bean public ValidatorBean newValidatorBean()
        {   return new ValidatorBean() ;    }
    @Bean public ReportCriteriaBean newReportCriteriaBean()
        {   return new ReportCriteriaBean() ; }
    @Value("$server.port") String portNo ;
    @Bean public ConfiguredPortNumberBean newConfiguredPortNumberBean()
        {    String sp = portNo ; System.out.println( "PortNumberBean == " + sp ) ; return new ConfiguredPortNumberBean(); }
}


@Configuration
@EnableWebSecurity

//@CurrentSecurityContext( "main-context")
//@EnableJpaRepositories
class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired AuthenticationService authenticationService ;
    @Autowired PasswordEncoder encoder ;
    @Autowired CustomUserDetailsService customUserDetailsService ;

    protected void configure( AuthenticationManagerBuilder builder )
            throws Exception
    {
        authenticationService.PasswordEncoder( encoder ) ;
        builder.authenticationProvider( this.authenticationService ) ;
        builder.userDetailsService( this.customUserDetailsService ) ;


        //builder.build() ;
        /*ArrayList<String> auths;
        UserPrincipal user = new UserPrincipal();
        user.setUserName("user");
        user.setUserPassword( "{bcrypt}"+encoder.encode("password"));

        auths = new ArrayList<>();
        auths.add("USER");
        user.GrantUserAuthorities(auths);

        UserPrincipal admin = new UserPrincipal();
        admin.setUserName("admin");
        admin.setUserPassword("{bcrypt}"+encoder.encode("admin"));
        auths = new ArrayList<>();
        auths.add("USER");
        auths.add("ADMIN");
        admin.GrantUserAuthorities(auths);

        AuthenticateResult result ;
        result = authenticationService
                .getAuthenticator()
                .AddUser(user, user.getUserPassword());

        System.out.println( result.getResultStatus() ) ;
        System.out.println( result.getUser().getUserName());
        System.out.println( result.getUser().getUserPassword());

        result = authenticationService
                .getAuthenticator()
                .AddUser(admin, admin.getUserPassword()) ;

        System.out.println( result.getResultStatus() ) ;
        System.out.println( result.getUser().getUserName());
        System.out.println( result.getUser().getUserPassword());*/


    }

    public void configure(WebSecurity security )
    {
        security.ignoring().antMatchers("/resources/**") ;
        //security.ignoring().antMatchers( "/user/**");
    }

    protected void configure( HttpSecurity http )
            throws  Exception
    {

        http.authorizeRequests()
                .antMatchers("/", "/index.html", "/signup.html", "/about.html").permitAll()
                .antMatchers("/login","/LoginUser","/RegisterUser","/doRegister","/doLogin").permitAll()
                .antMatchers("/user/**").permitAll()
                .antMatchers( "/api/security/**").permitAll()
                .antMatchers("/secure/**").hasAuthority("USER")
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                //         .antMatchers("/resource.html").hasAuthority("USER")
                .anyRequest().authenticated()
                .and().formLogin()
                /*.loginPage("/UserLogin.html").failureUrl(
                        "/UserLogin.html?errormsg=Login%20failed.%20Please%20retry.")
                .defaultSuccessUrl("/index.html")*/
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll()
                .and().logout()
                .logoutUrl("/logout.html").logoutSuccessUrl("/UserLogoutMessage.html")
                .invalidateHttpSession(true).deleteCookies("JSESSIONID")
                .permitAll()
                .and().csrf().disable();
        //.httpBasic();
        //return http.build();
    }
}

