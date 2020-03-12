package com.mz.rpc.rpcclient.execute;

public enum MethodEnum {

    GET("get"),

    POST("post"),

    PUT("put"),

    DELETE("delete");

    MethodEnum(String methodName) {
        this.methodName = methodName;
    }

    private String methodName;

    public String getMethodName() {
        return methodName;
    }

}
