package org.demo.plugin.exchange.protocol;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;

import org.demo.plugin.exchange.base.ProtocolPackageHeader;

public abstract class AbstractExchangePackage implements IExchangePackage {
	/** 数据域流 */
    protected InputStream inputStream = null;
    
    ProtocolPackageHeader exchangePackageHeader = new ProtocolPackageHeader(IExchangePackage.EXCHANGE_PROTOCOL_HEAD);
    
    public InputStream getDataArea() {
        return inputStream;
    }

    public void setDataArea(InputStream is) {
        this.inputStream = is;
    }


    public String getHeader(String name) {
        return exchangePackageHeader.getHeader(name);
    }

    public Map<String, String> getHeaders() {
        return exchangePackageHeader.getHeaders();
    }

    public void setHeader(String name, String value) {
        exchangePackageHeader.setHeader(name, value);
    }
    
    public void expressByObject(InputStream is) throws IOException {
	}

	public InputStream expressByStream() throws IOException {
		
		return null;
	}
}
