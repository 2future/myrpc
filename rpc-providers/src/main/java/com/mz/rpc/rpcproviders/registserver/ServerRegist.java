package com.mz.rpc.rpcproviders.registserver;

import com.alibaba.fastjson.JSON;
import domain.RpcMethodDomain;
import org.I0Itec.zkclient.ZkClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author mz
 * @version V1.0
 * @Title ServerRegist
 * @Package com.mz.rpc.rpcproviders.registserver
 * @Description
 * @date 2020-03-13 14:07
 */
@Component
public class ServerRegist implements ApplicationListener {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    Environment environment;

    @Autowired
    ZkClient zkClient;

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        if (applicationEvent instanceof ApplicationStartedEvent) {
            System.err.println("spring 启动成功");
            zkClient.createPersistent("/rpc-server" , true);

            RequestMappingHandlerMapping mapping = webApplicationContext
                    .getBean(RequestMappingHandlerMapping.class);
            // 获取url与类和方法的对应信息
            Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();
            List<RpcMethodDomain> urlList=new ArrayList<>();
            for (RequestMappingInfo info : map.keySet()) {
                // 获取url的Set集合，一个方法可能对应多个url
                Set<String> patterns = info.getPatternsCondition().getPatterns();
                List<String> patternsList = patterns.stream().collect(Collectors.toList());

                RequestMethodsRequestCondition methodsCondition = info.getMethodsCondition();

                Set<RequestMethod> methods = methodsCondition.getMethods();

                List<RequestMethod> methodsList = methods.stream().collect(Collectors.toList());

                if (!CollectionUtils.isEmpty(methods)) {
                    RpcMethodDomain domain = new RpcMethodDomain();
                    domain.setMethod(methodsList.get(0).toString());
                    domain.setUrl(patternsList.get(0));
                    urlList.add(domain);
                }
            }
            String port = environment.getProperty("local.server.port");
            try {
                String urlData = JSON.toJSONString(urlList);
                String hostAddress = Inet4Address.getLocalHost().getHostAddress();
                //创建临时节点
                zkClient.createEphemeral("/rpc-server/" + hostAddress + ":" + port, urlData);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }


        }
    }

}