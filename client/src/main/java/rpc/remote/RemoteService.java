package rpc.remote;

import rpc.execute.MethodEnum;
import rpc.execute.annoation.RpcServiceMethod;

/**
 * @author mz
 * @version V1.0
 * @Title RemoteService
 * @Package rpc.remote
 * @Description
 * @date 2020-03-12 23:04
 */

public interface RemoteService {

    @RpcServiceMethod(method = MethodEnum.GET,url="4234234")
    void getData();

}