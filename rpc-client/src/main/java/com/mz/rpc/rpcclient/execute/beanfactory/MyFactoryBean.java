package com.mz.rpc.rpcclient.execute.beanfactory;

import com.mz.rpc.rpcclient.execute.MyTestTemplate;
import com.mz.rpc.rpcclient.execute.proxy.ExeceteProxy;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.Proxy;

/**
 * @author mz
 * @version V1.0
 * @Title MyFactoryBean
 * @Package btree.FactoryBeanTest
 * @Description
 * @date 2020-03-09 22:53
 */
public class MyFactoryBean<T> implements FactoryBean<T>, ApplicationContextAware {

    private ApplicationContext applicationContext;

    Class<T> myInterface;

    public MyFactoryBean(Class<T> myInterface) {
        this.myInterface = myInterface;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public T getObject() {
        MyTestTemplate bean = applicationContext.getBean(MyTestTemplate.class);
        ExeceteProxy myProxy = new ExeceteProxy(bean);
        T o = (T) Proxy.newProxyInstance(myInterface.getClassLoader(), new Class[]{myInterface}, myProxy);
        return o;
    }

    @Override
    public Class<T> getObjectType() {
        return myInterface;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}