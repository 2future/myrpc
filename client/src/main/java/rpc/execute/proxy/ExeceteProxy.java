package rpc.execute.proxy;

import rpc.execute.MyTestTemplate;
import rpc.execute.RpcServer;
import rpc.execute.ServerNode;
import rpc.execute.annoation.RpcServiceMethod;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author mz
 * @version V1.0
 * @Title ExeceteProxy
 * @Package rpc.execute.proxy
 * @Description
 * @date 2020-03-12 22:30
 */
public class ExeceteProxy implements InvocationHandler {

    private MyTestTemplate myTestTemplate;

    public ExeceteProxy(MyTestTemplate myTestTemplate) {
        this.myTestTemplate = myTestTemplate;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        RpcServiceMethod annotation = method.getAnnotation(RpcServiceMethod.class);
        if (annotation != null) {
            ServerNode node = RpcServer.getInstance().getNode();
            //拼接地址
            String ip = node.getIp();
            String post = node.getPost();
            StringBuilder buf = new StringBuilder("http://");
            buf.append(ip);
            buf.append(post);
            buf.append(annotation.url());
            Class<?> returnType = method.getReturnType();
            Object execute = myTestTemplate.execute(annotation.method(), buf.toString(),
                    returnType, args, null);
            return execute;
        }
        return null;
    }
}