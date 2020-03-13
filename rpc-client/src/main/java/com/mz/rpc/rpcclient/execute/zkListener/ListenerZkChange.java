package com.mz.rpc.rpcclient.execute.zkListener;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

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
        zkClient.subscribeChildChanges("/rpc-server", (s, list) -> {
            System.err.println("节点变化重新处理");
            List<String> children = zkClient.getChildren("/rpc-server");
            for (String child : children) {
                Object o = zkClient.readData("/rpc-server/" + child);
                System.err.println(o);
            }
        });
        List<String> children = zkClient.getChildren("/rpc-server");
        for (String child : children) {
            Object o = zkClient.readData("/rpc-server/" + child);
            System.err.println(o);
        }
    }
}