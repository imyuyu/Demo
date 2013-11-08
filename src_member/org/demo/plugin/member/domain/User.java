package org.demo.plugin.member.domain;

public class User extends BaseModel{
	private String name;//
	private String username;//
	private String password;//
	
	public static final String PROP_ID = "Id";
	public static final String PROP_NAME = "name";
	public static final String PROP_USERNAME = "username";
	public static final String PROP_PASSWORD = "password";
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
