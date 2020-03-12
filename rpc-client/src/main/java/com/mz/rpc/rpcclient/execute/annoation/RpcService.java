package com.mz.rpc.rpcclient.execute.annoation;

import java.lang.annotation.*;

/**
 * @author mz
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RpcService {

    /**
     * 服务名
     *
     * @return
     */
    String sacPackage() default "";

}
