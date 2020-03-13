package com.mz.rpc.rpcproviders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.session.SessionAutoConfiguration;


@SpringBootApplication(
        exclude = {DataSourceAutoConfiguration.class, SessionAutoConfiguration.class},
        scanBasePackages = "com.mz.rpc.*")
public class RpcProvidersApplication {

    public static void main(String[] args) {
        SpringApplication.run(RpcProvidersApplication.class, args);
    }

}
