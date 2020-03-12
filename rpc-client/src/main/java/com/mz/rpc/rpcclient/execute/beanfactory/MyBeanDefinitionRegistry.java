package com.mz.rpc.rpcclient.execute.beanfactory;

import com.mz.rpc.rpcclient.remote.RemoteService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.stereotype.Component;

/**
 * @author mz
 * @version V1.0
 * @Title MyBeanDefinitionRegistry
 * @Package btree.MyBeanDefinitionRegistry
 * @Description
 * @date 2020-03-12 15:49
 */
@Component
public class MyBeanDefinitionRegistry implements BeanDefinitionRegistryPostProcessor {

    /**
     * 将代理的 接口 自定义  bd 注册到spring
     * @param beanDefinitionRegistry
     * @throws BeansException
     */
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {


        registBeanDefinition(beanDefinitionRegistry,null);
    }


    private void registBeanDefinition(BeanDefinitionRegistry beanDefinitionRegistry,Class myInterface){
        // 这里 TestTrsultInterface 是我们定义的接口 需要被注入的接口应用类型
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(RemoteService.class);
        //定义一个通用的 BeanDefinition
        GenericBeanDefinition definition = (GenericBeanDefinition) builder.getRawBeanDefinition();
        //设置 BeanDefinition 的bean的构造方法的值
        definition.getConstructorArgumentValues().addGenericArgumentValue(RemoteService.class);
        //定义 beanfactory 要使用这个 factory 去生成你的bean
        definition.setBeanClass(MyFactoryBean.class);
        //bean的注入方式
        definition.setAutowireMode(GenericBeanDefinition.AUTOWIRE_BY_TYPE);
        //将这个 BeanDefinition 注册要spring
        beanDefinitionRegistry.registerBeanDefinition(RemoteService.class.getSimpleName(), definition);
    }




    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }

}