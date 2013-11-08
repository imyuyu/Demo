package org.demo.plugin.exchange.exception;

/**
 * @author Administrator
 * @version 1.0
 * @created 11-九月-2012 15:31:50
 */
public class ExchangeException extends RuntimeException {
    
    /**  */
    private static final long serialVersionUID = 1L;
    
    private long currentTime = System.currentTimeMillis();

	public ExchangeException(){

	}

    public ExchangeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExchangeException(String message) {
        super(message);
    }

    public ExchangeException(Throwable cause) {
        super(cause);
    }

    
    public long getCurrentTime() {
        return currentTime;
    }

	
	
}
