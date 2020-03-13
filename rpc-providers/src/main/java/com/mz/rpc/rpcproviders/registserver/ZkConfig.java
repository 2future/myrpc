package com.mz.rpc.rpcproviders.registserver;

import org.I0Itec.zkclient.ZkClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import property.ZkProperty;
import serializer.CustomSerializer;

/**
 * @author mz
 * @version V1.0
 * @Title ZkConfig
 * @Package rpc.execute
 * @Description
 * @date 2020-03-13 00:03
 */
@EnableConfigurationProperties(ZkProperty.class)
@ConfigurationProperties(prefix = "rpc.zk")
@Component
public class ZkConfig {

    @Autowired
    ZkProperty zkProperty;

    @Bean("ZkClient")
    public ZkClient createZkClient() {
        ZkClient client = new ZkClient(zkProperty.getAddres(),10000,10000,new CustomSerializer());
        return client;
    }

}