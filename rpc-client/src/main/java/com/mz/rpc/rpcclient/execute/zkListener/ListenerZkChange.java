package com.mz.rpc.rpcclient.execute.zkListener;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author mz
 * @version V1.0
 * @Title ListenerZkChange
 * @Package rpc.execute.zkListener
 * @Description
 * @date 2020-03-13 00:09
 */
@Component
@DependsOn("ZkClient")
public class ListenerZkChange {

    @Autowired
    ZkClient zkClient;

    @PostConstruct
    public void listenUpdate(){
        zkClient.subscribeDataChanges("rpc-server",new IZkDataListener() {

            @Override
            public void handleDataChange(String s, Object o) throws Exception {
                System.err.println(s);
                System.err.println(o);
            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
                System.err.println(s);
            }

        });

    }


}