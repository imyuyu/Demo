package org.demo.plugin.exchange.protocol.transport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataBean {   
	private  String key;
	private  Map<String,String> value;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}

	public void setValue(String key,String  value) {
		this.value.put(key,value);
	}	
	public Map<String,String> getValue() {
		return this.value;
	}	
	public DataBean(){
		this.value = new  HashMap<String,String>();
	}
	public DataBean(String key,Map<String,String> map){
		this.value = new  HashMap<String,String>();
		this.key = key;
		this.value.putAll(map);		
	}


	
}
