package org.demo.plugin.exchange.protocol.client;

import org.demo.plugin.exchange.protocol.IExchangePackage;

public interface IExchangeRequest extends IExchangePackage{
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
     * 目标
     * @return
     */
    public void setTarget(String arg);
    
    /**
     * 主机信息
     * @return
     */
    public void setHostMessage(String arg);
    
    /**
     * 请求的服务
     * @return
     */
    public void setAction(String arg);
}
