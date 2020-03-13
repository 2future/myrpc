package com.mz.rpc.rpcclient.remote;


import com.mz.rpc.rpcclient.execute.MethodEnum;
import com.mz.rpc.rpcclient.execute.annoation.RpcServiceMethod;

import java.util.List;

/**
 * @author mz
 * @version V1.0
 * @Title RemoteService
 * @Package rpc.remote
 * @Description
 * @date 2020-03-12 23:04
 */

public interface RemoteService {

    @RpcServiceMethod(method = MethodEnum.GET,url="/provider/data")
    List<String> getData();

}