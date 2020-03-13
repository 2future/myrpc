package com.mz.rpc.rpcclient.execute.beanfactory;

import com.mz.rpc.rpcclient.execute.annoation.RpcService;
import com.mz.rpc.rpcclient.remote.RemoteService;
import exception.RpcAnnotationException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import java.io.IOException;
import java.util.*;

/**
 * @author mz
 * @version V1.0
 * @Title MyBeanDefinitionRegistry
 * @Package btree.MyBeanDefinitionRegistry
 * @Description
 * @date 2020-03-12 15:49
 */
@Component
public class MyBeanDefinitionRegistry implements BeanDefinitionRegistryPostProcessor, ResourceLoaderAware, ApplicationContextAware {

    private static final String DEFAULT_RESOURCE_PATTERN = "**/*.class";

    private ApplicationContext applicationContext;

    private ResourcePatternResolver resourcePatternResolver;

    private MetadataReaderFactory metadataReaderFactory;

    /**
     * 将代理的 接口 自定义  bd 注册到spring
     *
     * @param beanDefinitionRegistry
     * @throws BeansException
     */
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(RpcService.class);
        List<String> scanPackages = new ArrayList<>();
        for (String s : beansWithAnnotation.keySet()) {
            String scanPackage = AopUtils.getTargetClass(beansWithAnnotation.get(s)).getAnnotation(RpcService.class).sacPackage();
            if (StringUtils.isBlank(scanPackage)) {
                throw new RpcAnnotationException("scanpackage vendor can not be null");
            }
            scanPackages.add(scanPackage);

        }
        for (String scanPackage : scanPackages) {
            Set<Class<?>> classes = scannerPackages(scanPackage);
            classes.forEach(e -> registBeanDefinition(beanDefinitionRegistry, e));
        }
    }


    private void registBeanDefinition(BeanDefinitionRegistry beanDefinitionRegistry, Class myInterface) {
        // 这里 TestTrsultInterface 是我们定义的接口 需要被注入的接口应用类型
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(myInterface);
        //定义一个通用的 BeanDefinition
        GenericBeanDefinition definition = (GenericBeanDefinition) builder.getRawBeanDefinition();
        //设置 BeanDefinition 的bean的构造方法的值
        definition.getConstructorArgumentValues().addGenericArgumentValue(myInterface);
        //定义 beanfactory 要使用这个 factory 去生成你的bean
        definition.setBeanClass(MyFactoryBean.class);
        //bean的注入方式
        definition.setAutowireMode(GenericBeanDefinition.AUTOWIRE_BY_TYPE);
        //将这个 BeanDefinition 注册要spring
        beanDefinitionRegistry.registerBeanDefinition(RemoteService.class.getSimpleName(), definition);
    }


    private Set<Class<?>> scannerPackages(String basePackage) {
        Set<Class<?>> set = new LinkedHashSet<>();
        String basePackageName = ClassUtils.convertClassNameToResourcePath(applicationContext.getEnvironment().resolveRequiredPlaceholders(basePackage));

        String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +
                basePackageName + '/' + DEFAULT_RESOURCE_PATTERN;
        try {
            Resource[] resources = this.resourcePatternResolver.getResources(packageSearchPath);
            for (Resource resource : resources) {
                if (resource.isReadable()) {
                    MetadataReader metadataReader = this.metadataReaderFactory.getMetadataReader(resource);
                    String className = metadataReader.getClassMetadata().getClassName();
                    Class<?> clazz;
                    try {
                        clazz = Class.forName(className);
                        set.add(clazz);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return set;
    }


    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourcePatternResolver = ResourcePatternUtils.getResourcePatternResolver(resourceLoader);
        this.metadataReaderFactory = new CachingMetadataReaderFactory(resourceLoader);
    }
}