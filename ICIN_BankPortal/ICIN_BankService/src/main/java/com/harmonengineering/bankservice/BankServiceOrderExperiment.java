package com.harmonengineering.bankservice;

import org.springframework.web.bind.annotation.RequestBody;

import java.lang.annotation.Annotation;

public class BankServiceOrderExperiment implements RequestBody {
    public final String HelloMessage = "Hello, RequestBody implementation here!" ;
    public String message ;
    @Override
    public boolean required() {
        return false;
    }
    public String getMessage() { return this.message ; } ;
    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }
}
