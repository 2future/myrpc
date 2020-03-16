# myrpc
使用方法:
启动类添加包扫描路径
@RpcService(sacPackage = "com.mz.rpc.rpcclient.remote")

```
@SpringBootApplication(
        exclude = {DataSourceAutoConfiguration.class, SessionAutoConfiguration.class},
        scanBasePackages = "com.mz.rpc.*")
@RpcService(sacPackage = "com.mz.rpc.rpcclient.remote")
public class RpcClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(RpcClientApplication.class, args);
    }

}
```
本扫描路径为需要代理的接口 接口使用方式
```
public interface RemoteService {

    @RpcServiceMethod(method = MethodEnum.GET,url="/provider/data")
    List<String> getData();

}

```
注解 RpcServiceMethod 中 method 为http调用方法 url为地址

配置 在 消费端 和 提供端 zk地址要一致
```
rpc.zk.addres=localhost:2181
server.port=8081
```



