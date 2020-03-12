package rpc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import rpc.remote.RemoteService;

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
    public Object testQuery2() {
        remoteService.getData();
        return null;
    }

}