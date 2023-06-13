//package com.harmonengineering.icin_bankservice;
//import com.harmonengineering.authentication.AuthenticationService;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import com.harmonengineering.beans.ConfiguredPortNumberBean;
//import com.harmonengineering.beans.ReportCriteriaBean;
//import com.harmonengineering.beans.ValidatorBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@EnableJpaRepositories
//@Configuration
//public class AppConfiguration
//{
//    @Bean    public AuthenticationService newAuthenticationService()
//    {
//        return new AuthenticationService() ;
//    }
//    @Bean    public PasswordEncoder encoder() {
//        return new BCryptPasswordEncoder();
//    }
//    //    @Bean
////    public PasswordEncoder passwordEncoder() {
////        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
////        return encoder;
////    }
//    @Bean public ValidatorBean newValidatorBean()
//    {
//        return new ValidatorBean() ;
//    }
//    @Bean public ReportCriteriaBean newReportCriteriaBean()
//    {  return new ReportCriteriaBean() ; }
//    @Bean public ConfiguredPortNumberBean newConfiguredPortNumberBean()
//    {
//        return new ConfiguredPortNumberBean(); }
//}
//
//

