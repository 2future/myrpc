package com.mz.rpc.rpcproviders.controller;

import org.springframework.web.bind.annotation.*;

/**
 * @author mz
 * @version V1.0
 * @Title ProviderController
 * @Package com.mz.rpc.rpcproviders.controller
 * @Description
 * @date 2020-03-13 14:04
 */
@RestController
@RequestMapping(value = "/provider")
public class ProviderController {

    @GetMapping("/data")
    public String getData() {
        System.err.println("provider get 方法调用成功");
        return "get success";
    }

    @PostMapping("/data")
    public String postData() {
        System.err.println("provider post 方法调用成功");
        return "post success";
    }

    @PutMapping("/data")
    public String putData() {
        System.err.println("provider put 方法调用成功");
        return "put success";
    }

    @DeleteMapping("/data")
    public String deleteData() {
        System.err.println("provider  delete 方法调用成功");
        return "delete success";
    }

}
