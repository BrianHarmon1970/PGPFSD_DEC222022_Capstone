package com.harmonengineering.beans;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class ConfiguredPortNumberBean
{
    @Value(value = "${server.port}")
    private String serverPort;

    //public void setServerPort( String port ) { serverPort = port ; }
//    public ConfiguredPortNumberBean(String serverPort) {
//        this.serverPort = serverPort;
//    } ;
    public ConfiguredPortNumberBean() {}
    public String  getServerPort()
    { return serverPort ; }
    public String toString() {
        return "ConfiguredPortNumberBean"+this.hashCode();
    }
}

