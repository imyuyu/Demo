package org.demo.plugin.exchange.exception;

/**
 * 服务端异常
 * @author Developer
 *
 */
public class ExchangeServerException extends ExchangeException {

    public ExchangeServerException() {
    }

    public ExchangeServerException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExchangeServerException(String message) {
        super(message);
    }

    public ExchangeServerException(Throwable cause) {
        super(cause);
    }
}
