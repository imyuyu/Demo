package org.demo.plugin.exchange.config.domain;

import java.io.Serializable;

/**
 * 客户端对象
 * @author Developer
 *
 */
public class ExtIecClientInfo implements Serializable{
	private String iecId;
	
	private int sort;
	
	private String name;

	private String description;

	private String shortName;

	private String ip;

	private int port;

	private String passwd;

	private String serverCode;

	private String selfCode;

	private int state;

	public String getIecId() {
		return iecId;
	}

	public void setIecId(String iecId) {
		this.iecId = iecId;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getServerCode() {
		return serverCode;
	}

	public void setServerCode(String serverCode) {
		this.serverCode = serverCode;
	}

	public String getSelfCode() {
		return selfCode;
	}

	public void setSelfCode(String selfCode) {
		this.selfCode = selfCode;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
	/**
	 * 保存成本地XML
	 */
	public void saveToXML(){
		
	}
}
