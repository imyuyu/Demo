package org.demo.plugin.exchange.base;

import java.util.LinkedHashMap;
import java.util.Map;

public class ProtocolPackageHeader {
	/** ͷ头信息集合 */
    protected Map<String, String> headers = new LinkedHashMap<String, String>();
    
    protected String headEnd;
    
    static final String SPILT_CHAR = ":";
    
    public ProtocolPackageHeader(String headEnd){
        this.headEnd = headEnd;
    }
    
    public String getHeader(String name) {
        return headers.get(name);
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeader(String name, String value) {
        headers.put(name, value);
    }

    public boolean checkHeader(){
        return true;
    }
}
