package org.demo.plugin.exchange.protocol;

import java.io.InputStream;
import java.util.Map;

import org.demo.plugin.exchange.base.IStreamable;


/**
 * 交换协议包
 * @author Yang.Cheng
 * @date Sep 21, 201211:25:52 AM
 * @Copyright(c) ChantSoft Co.,LTD
 */
public interface IExchangePackage extends IStreamable {
    
    public static final String EXCHANGE_PROTOCOL_HEAD_VERSION = "Version";
    
    public static final String EXCHANGE_PROTOCOL_HEAD_HOST = "Host";
    
    public static final String EXCHANGE_PROTOCOL_HEAD_HOSTMESSAGE = "HostMessage";
    
    public static final String EXCHANGE_PROTOCOL_HEAD_TARGET = "Target";
    
    public static final String EXCHANGE_PROTOCOL_HEAD_ACTION = "Action";
    
    public static final String EXCHANGE_PROTOCOL_HEAD_RESULT = "Result";
    
    public static final String EXCHANGE_PROTOCOL_HEAD_HOSTMESSAGE_VALUE = "A8320sp2";
    
    public static final String EXCHANGE_PROTOCOL_HEAD = "ExchangeHead";
    
    /**
     * 获取头信息ͷ
     * @param name 头信息 
     */
    public String getHeader(String name);
    
    /**
     * 设置头信息
     * @param name
     * @param value
     */
    public void setHeader(String name,String value);

    /** 
     * 获取头信息列表
     */
    public Map<String, String> getHeaders();
    
    
    /**
     * 获取数据域
     * @return
     */
    public InputStream getDataArea();
    
    /**
     * 设置数据域
     * @param is 数据域流
     */
    public void setDataArea(InputStream is);
    
}
