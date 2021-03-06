package com.mz.rpc.rpcclient.controller;

import com.mz.rpc.rpcclient.remote.RemoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author mz
 * @version V1.0
 * @Title TestController
 * @Package rpc.controller
 * @Description
 * @date 2020-03-12 23:06
 */
@RestController
public class TestController {

    @Autowired
    private RemoteService remoteService;

    @GetMapping("test")
    public Object testQuery() {
        List<String> data = remoteService.getData();
        return data;
    }

}