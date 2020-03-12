package property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author mz
 * @version V1.0
 * @Title ZkProperty
 * @Package property
 * @Description
 * @date 2020-03-13 00:04
 */
@Component
@ConfigurationProperties(prefix = "rpc.zk")
public class ZkProperty {

    private String addres;

    public String getAddres() {
        return addres;
    }

    public void setAddres(String addres) {
        this.addres = addres;
    }
}