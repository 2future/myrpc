package rpc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.session.SessionAutoConfiguration;
import rpc.execute.annoation.RpcService;

/**
 * @author mz
 * @version V1.0
 * @Title ApplicationStart
 * @Package rpc.execute
 * @Description
 * @date 2020-03-12 21:47
 */
@SpringBootApplication(
        exclude = {DataSourceAutoConfiguration.class, SessionAutoConfiguration.class},
        scanBasePackages = "rpc.*")
@RpcService(sacPackage = "rpc.remote.*")
public class ApplicationStart {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationStart.class, args);
    }

}