package com.mz.rpc.rpcclient.execute.zkListener;

import com.alibaba.fastjson.JSON;
import com.mz.rpc.rpcclient.execute.RpcServer;
import com.mz.rpc.rpcclient.execute.ServerNode;
import domain.RpcMethodDomain;
import javafx.util.Pair;
import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
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
            RpcServer instance = RpcServer.getInstance();
            instance.initNodes();
            List<ServerNode> newNodeList=new ArrayList<>();
            for (String child : children) {
                //子节点为ip+端口
                Pair<String, String> stringStringPair = paserHost(child);
                ServerNode node=new ServerNode(stringStringPair.getKey(),stringStringPair.getValue());
                List<RpcMethodDomain> rpcMethodDomains = JSON.parseArray(zkClient.readData("/rpc-server/" + child), RpcMethodDomain.class);
                node.setRpcMethodDomainList(rpcMethodDomains);
                newNodeList.add(node);
            }
            instance.updateNode(newNodeList);
        });

        List<String> children = zkClient.getChildren("/rpc-server");
        RpcServer instance = RpcServer.getInstance();
        instance.initNodes();
        List<ServerNode> newNodeList=new ArrayList<>();
        for (String child : children) {
            //子节点为ip+端口
            Pair<String, String> stringStringPair = paserHost(child);
            ServerNode node=new ServerNode(stringStringPair.getKey(),stringStringPair.getValue());
            List<RpcMethodDomain> rpcMethodDomains = JSON.parseArray(zkClient.readData("/rpc-server/" + child), RpcMethodDomain.class);
            node.setRpcMethodDomainList(rpcMethodDomains);
            newNodeList.add(node);
        }
        instance.updateNode(newNodeList);

    }

    private Pair<String,String> paserHost(String host){
        String[] split = host.split(":");
        return  new Pair<String,String>(split[0],split[1]);

    }


}