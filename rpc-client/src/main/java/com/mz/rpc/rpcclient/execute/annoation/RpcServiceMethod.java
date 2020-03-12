package com.mz.rpc.rpcclient.execute.annoation;


import com.mz.rpc.rpcclient.execute.MethodEnum;

import java.lang.annotation.*;

/**
 * @author mz
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RpcServiceMethod {

    /**
     * http调用方法
     *
     * @return
     */
    MethodEnum method();

    /**
     * 相对的服务路径 不需要 接口 与 ip
     *
     * @return
     */
    String url();
}
