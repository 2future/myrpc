package domain;

/**
 * @author mz
 * @version V1.0
 * @Title RpcDomain
 * @Package PACKAGE_NAME
 * @Description
 * @date 2020-03-12 19:44
 */
public class RpcDomain {

    /**
     * ip 地址
     */
    String ip;

    /**
     * 端口
     */
    String host;

    /**
     * 具体地址
     */
    RpcMethodDomain[] urls;


    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public RpcMethodDomain[] getUrls() {
        return urls;
    }

    public void setUrls(RpcMethodDomain[] urls) {
        this.urls = urls;
    }
}