package domain;

/**
 * @author mz
 * @version V1.0
 * @Title RpcMethodDomain
 * @Package PACKAGE_NAME
 * @Description
 * @date 2020-03-12 21:32
 */
public class RpcMethodDomain {

    private String url;

    private String method;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public String toString() {
        return "RpcMethodDomain{" +
                "url='" + url + '\'' +
                ", method='" + method + '\'' +
                '}';
    }
}