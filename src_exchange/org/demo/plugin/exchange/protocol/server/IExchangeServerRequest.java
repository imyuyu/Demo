package org.demo.plugin.exchange.protocol.server;
import org.demo.plugin.exchange.protocol.IExchangePackage;
import java.io.InputStream;

public interface IExchangeServerRequest extends IExchangePackage{
	/**
	 * 请求流
	 * @return
	 */
	public InputStream getInputStream();

	/**
     * 版本
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
     * 服务类型
     * @return
     */
    public String getAction();

}
