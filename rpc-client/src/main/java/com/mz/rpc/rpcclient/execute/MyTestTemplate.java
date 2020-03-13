package com.mz.rpc.rpcclient.execute;

import com.alibaba.fastjson.JSON;
import exception.RestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

/**
 * @author mz
 * @version V1.0
 * @Title MyTestTemplate
 * @Package MyRestTemplate
 * @Description
 * @date 2020-03-12 21:36
 */
@Component
public class MyTestTemplate {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * http调用方法
     *
     * @param methodEnum
     * @param fullUrl
     * @param returnType
     * @param requestBody
     * @param args
     * @param <T>
     * @return
     */
    public <T> T execute(MethodEnum methodEnum, String fullUrl, Class<T> returnType, Object requestBody, Object args) {

        HttpEntity<Object> entity = new HttpEntity<Object>(requestBody, getHttpHeaders());

        T result;
        switch (methodEnum) {
            case GET:
                String getResult = restTemplate.exchange(fullUrl, HttpMethod.GET, entity, String.class).getBody();
                result = JSON.parseObject(getResult, returnType);
                break;
            case POST:
                String postResult = restTemplate.exchange(fullUrl, HttpMethod.POST, entity, String.class).getBody();
                result = JSON.parseObject(postResult, returnType);
                break;
            case PUT:
                String putResult = restTemplate.exchange(fullUrl, HttpMethod.PUT, entity, String.class).getBody();
                result = JSON.parseObject(putResult, returnType);
                break;
            case DELETE:
                String deleteResult = restTemplate.exchange(fullUrl, HttpMethod.DELETE, entity, String.class).getBody();
                result = JSON.parseObject(deleteResult, returnType);
                break;
            default:
                throw new RestException("调用方法异常");
        }
        return result;
    }


    public HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json;charset=UTF-8");
        headers.set("Accept", "application/json");
        return headers;
    }


}