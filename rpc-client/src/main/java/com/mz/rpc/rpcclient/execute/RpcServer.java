package com.mz.rpc.rpcclient.execute;

import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author mz
 * @version V1.0
 * @Title RpcServer
 * @Package rpc.execute
 * @Description
 * @date 2020-03-12 23:24
 */
public class RpcServer {

    private static RpcServer instance = new RpcServer();

    public static RpcServer getInstance() {
        return instance;
    }

    private RpcServer() {
    }

    /**
     * 使用 CopyOnWriteArrayList 可以排序 并且线程安全（添加时候 不保证 获取时候 数据一致）
     */
    CopyOnWriteArrayList<ServerNode> nodeList = new CopyOnWriteArrayList();

    /**
     * 初始化节点
     */
    public void initNodes() {
        nodeList.clear();
    }

    /**
     * 更新最新节点数据
     *
     * @param serviceList
     */
    public void updateNode(List<ServerNode> serviceList) {
        nodeList.clear();
        nodeList.addAllAbsent(serviceList);
    }

    /**
     * 获取被调用最少的那个节点
     *
     * @return
     */
    public ServerNode getNode() {
        //根据调用次数 模拟 轮训负载均衡
        if (CollectionUtils.isEmpty(nodeList)) {
            return null;
        }
        Collections.sort(nodeList);
        ServerNode serverNode = nodeList.get(0);
        return serverNode;
    }

}