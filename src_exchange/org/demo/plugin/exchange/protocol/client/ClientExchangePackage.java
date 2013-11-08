package org.demo.plugin.exchange.protocol.client;

import java.io.IOException;
import java.io.InputStream;

import org.demo.plugin.exchange.protocol.AbstractExchangePackage;
import org.demo.plugin.exchange.protocol.IExchangePackage;


/**
 * 客户端请求包
 * @author Yang.Cheng
 * @date Sep 29, 20124:24:58 PM
 * @Copyright(c) ChantSoft Co.,LTD
 */
public class ClientExchangePackage extends AbstractExchangePackage implements IExchangeResponse,IExchangeRequest {

    public String getAction() {
        return this.getHeader(IExchangePackage.EXCHANGE_PROTOCOL_HEAD_ACTION);
    }

    public String getHost() {
        return this.getHeader(IExchangePackage.EXCHANGE_PROTOCOL_HEAD_HOST);
    }

    public String getHostMessage() {
        return this.getHeader(IExchangePackage.EXCHANGE_PROTOCOL_HEAD_HOSTMESSAGE);
    }

    public int getResult() {
        String rs = this.getHeader(IExchangePackage.EXCHANGE_PROTOCOL_HEAD_RESULT);
        return rs == null?-1:Integer.parseInt(rs);
    }

    public String getTarget() {
        return this.getHeader(IExchangePackage.EXCHANGE_PROTOCOL_HEAD_TARGET);
    }

    public String getVersion() {
        return this.getHeader(IExchangePackage.EXCHANGE_PROTOCOL_HEAD_VERSION);
    }

    public void setResult(int code) {
        this.setHeader(IExchangePackage.EXCHANGE_PROTOCOL_HEAD_RESULT, String.valueOf(code));
    }

    public void setAction(String arg) {
        this.setHeader(IExchangePackage.EXCHANGE_PROTOCOL_HEAD_ACTION, arg);
    }

    public void setHost(String arg) {
        this.setHeader(IExchangePackage.EXCHANGE_PROTOCOL_HEAD_HOST, arg);
    }

    public void setHostMessage(String arg) {
        this.setHeader(IExchangePackage.EXCHANGE_PROTOCOL_HEAD_HOSTMESSAGE, arg);
    }

    public void setTarget(String arg) {
        this.setHeader(IExchangePackage.EXCHANGE_PROTOCOL_HEAD_TARGET, arg);
    }

    public void setVersion(String arg) {
        this.setHeader(IExchangePackage.EXCHANGE_PROTOCOL_HEAD_VERSION, arg);
    }
}
