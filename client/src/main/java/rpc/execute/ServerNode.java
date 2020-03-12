package rpc.execute;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author mz
 * @version V1.0
 * @Title ServerNode
 * @Package rpc.execute
 * @Description
 * @date 2020-03-12 23:27
 */
public class ServerNode implements Comparable<ServerNode> {

    private String ip;

    private String post;

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public ServerNode(String ip, String post) {
        this.ip = ip;
        this.post = post;
    }

    public String getIp() {
        atomicInteger.incrementAndGet();
        return ip;
    }

    public AtomicInteger getCount() {
        return atomicInteger;
    }

    public String getPost() {
        return post;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServerNode that = (ServerNode) o;
        return Objects.equals(ip, that.ip) &&
                Objects.equals(post, that.post);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ip, post);
    }

    @Override
    public int compareTo(ServerNode o) {
        AtomicInteger count = o.getCount();
        if (this.atomicInteger.intValue() > count.intValue()) {
            return 1;
        } else if (this.atomicInteger.intValue() < count.intValue()) {
            return -1;
        }
        return 0;
    }


}