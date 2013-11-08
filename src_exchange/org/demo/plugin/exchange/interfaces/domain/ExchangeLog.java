package org.demo.plugin.exchange.interfaces.domain;

import java.util.Date;

public class ExchangeLog {
	private Long id;
	private Date createDate;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}
