package org.demo.plugin.exchange.protocol.client;

import org.demo.plugin.exchange.protocol.IExchangePackage;



/**
 * 客户端应答
 * @author Yang.Cheng
 * @date Sep 29, 20123:57:22 PM
 * @Copyright(c) ChantSoft Co.,LTD
 */
public interface IExchangeResponse extends IExchangePackage {
    
    
    /**
     * 协议版本
     * @return
     */
    public String getVersion();
    
    /**
     * 主机
     * @return
     */
    public String getHost();
    
    /**
     * 目标
     * @return
     */
    public String getTarget();
    
    /**
     * 主机信息
     * @return
     */
    public String getHostMessage();
    
    /**
     * 请求的服务
     * @return
     */
    public String getAction();

    /**
     * 结果
     * @return
     */
    public int getResult();
    
}
