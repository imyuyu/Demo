package org.demo.plugin.member.domain;

import javax.persistence.*;

import org.demo.Constants.Constants;
import org.demo.util.UUIDLong;

@MappedSuperclass
public class BaseModel {
	private Long id = -1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * 设置一个新ID
	 */
	public void setNewId(){
		setId(UUIDLong.absLongUUID());
	}
	/**
	 * 如果是新对象就生成ID
	 */
	public void setIdIfNew(){
		if(id == Constants.GLOBAL_NULL_ID){
			setId(UUIDLong.absLongUUID());
		}
	}
	
	/**
	 * 克隆对象，同时把新对象的id置为默认值
	 */
	public Object clone() throws CloneNotSupportedException {
		BaseModel m = null;
		try {
			m = (BaseModel) org.apache.commons.beanutils.BeanUtils.cloneBean(this);
			m.setId(Constants.GLOBAL_NULL_ID);
		}
		catch (Exception e) {
			throw new CloneNotSupportedException(e.getMessage());
		}

		return m;
	}
}
