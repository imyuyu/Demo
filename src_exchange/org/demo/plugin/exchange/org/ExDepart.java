package org.demo.plugin.exchange.org;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.demo.plugin.exchange.standard.JaxbAdapter;
@XmlRootElement(name="ExDepart")
@XmlAccessorType(XmlAccessType.FIELD) 
public class ExDepart {
	
	private Long id;
	private String name ;
	private String code ;
	private String path ;
	private Boolean enable ;
	private Boolean is_internal;
	private Integer sort_id ;
	@XmlJavaTypeAdapter(value=JaxbAdapter.class)
	private Date create_time;
	@XmlJavaTypeAdapter(value=JaxbAdapter.class)
	private Date update_time ;
	private String description ;
	private Long org_account_id ;
	private Boolean is_deleted ;
	private Integer status ;
	private String dept_short_name ;
	@XmlElementWrapper(name="members")  
    @XmlElements({//表示或的关系，list中内容可以为以下两种类型  
        @XmlElement(name="ExMember",type=ExMember.class),  
        @XmlElement(name="m",type=String.class)  
    })  
	private List<ExMember> members;
	public ExDepart() {
		members = new ArrayList<ExMember>();
	}

	public List<ExMember> getMembers() {
		return members;
	}

	public void setMembers(List<ExMember> members) {
		this.members = members;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public Boolean getIs_internal() {
		return is_internal;
	}

	public void setIs_internal(Boolean is_internal) {
		this.is_internal = is_internal;
	}

	public Integer getSort_id() {
		return sort_id;
	}

	public void setSort_id(Integer sort_id) {
		this.sort_id = sort_id;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getOrg_account_id() {
		return org_account_id;
	}

	public void setOrg_account_id(Long org_account_id) {
		this.org_account_id = org_account_id;
	}

	public Boolean getIs_deleted() {
		return is_deleted;
	}

	public void setIs_deleted(Boolean is_deleted) {
		this.is_deleted = is_deleted;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getDept_short_name() {
		return dept_short_name;
	}

	public void setDept_short_name(String dept_short_name) {
		this.dept_short_name = dept_short_name;
	}
}
