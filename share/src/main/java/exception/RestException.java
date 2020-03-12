package exception;

/**
 * @author mz
 * @version V1.0
 * @Title RestException
 * @Package exception
 * @Description
 * @date 2020-03-12 21:49
 */
public class RestException extends RuntimeException {


    public RestException(String message) {
        super(message);
    }
}