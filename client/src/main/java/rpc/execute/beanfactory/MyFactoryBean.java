package rpc.execute.beanfactory;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import rpc.execute.MyTestTemplate;
import rpc.execute.proxy.ExeceteProxy;

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