package com.mz.rpc.rpcclient;

import com.mz.rpc.rpcclient.execute.annoation.RpcService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.session.SessionAutoConfiguration;

@SpringBootApplication(
        exclude = {DataSourceAutoConfiguration.class, SessionAutoConfiguration.class},
        scanBasePackages = "com.mz.rpc.*")
@RpcService(sacPackage = "rpc.remote.*")
public class RpcClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(RpcClientApplication.class, args);
    }

}
