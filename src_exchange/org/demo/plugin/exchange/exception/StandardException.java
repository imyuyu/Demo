package org.demo.plugin.exchange.exception;

/**
 * 标准化异常
 */
public class StandardException extends ExchangeException {

	private static final long serialVersionUID = 1L;

	public StandardException(){

	}
	
	public StandardException(String message, Throwable cause) {
	        super(message, cause);
	    }

	public StandardException(String message) {
	        super(message);
	   }

	public StandardException(Throwable cause) {
	        super(cause);
	  }

}
