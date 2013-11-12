package org.demo.plugin.exchange.protocol.server;

import java.io.OutputStream;

import org.demo.plugin.exchange.protocol.IExchangePackage;

public interface IExchangeServerResponse extends IExchangePackage {
    
    public static final int EXCHANGE_PROTOCOL_RESULTCODE_UNDEFINED1 = 100;
    
    public static final int EXCHANGE_PROTOCOL_RESULTCODE_SUC = 200;
    
    public static final int EXCHANGE_PROTOCOL_RESULTCODE_UNDEFINED2 = 300;
    
    public static final int EXCHANGE_PROTOCOL_RESULTCODE_NOSERVER = 400;
    
    public static final int EXCHANGE_PROTOCOL_RESULTCODE_SERVERERROR = 500;

	/**
	 * 应答流
	 * @return
	 */
	public OutputStream getOutputStream();

	/**
	 * 协议版本
	 * @return
	 */
	public void setVersion(String arg);
    
    /**
     * 主机
     * @return
     */
    public void setHost(String arg);
    
    /**
     * 目标机
     * @return
     */
    public void setTarget(String arg);
    
    /**
     * 主机信息
     * @return
     */
    public void setHostMessage(String arg);
    
    /**
     * 服务类型
     * @return
     */
    public void setAction(String arg);
	
	/**
	 * 结果码
	 * 100
	 * 200 执行成功
	 * 300 
	 * 400 未找到服务
	 * 500 服务失败
	 * @param code
	 */
	public void setResult(int code);

}