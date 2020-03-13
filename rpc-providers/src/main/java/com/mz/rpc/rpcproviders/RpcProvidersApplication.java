package com.mz.rpc.rpcproviders;

import domain.RpcMethodDomain;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RpcProvidersApplication {

    public static void main(String[] args) {
        RpcMethodDomain domain =new RpcMethodDomain();
        SpringApplication.run(RpcProvidersApplication.class, args);
    }

}
