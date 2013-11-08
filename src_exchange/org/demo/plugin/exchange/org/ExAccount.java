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

@XmlRootElement(name="ExAccountData")
@XmlAccessorType(XmlAccessType.FIELD) 
public class ExAccount {
	private Long id;
	private String name ;
	private String second_name;
	private String code ;
	private String alias ;
	private String shortname;
	private String group_shortname ;
	private Integer sort_id ;
	private Boolean enable ;
	String admin_name ;
	private Long admin_id ;
	private String admin_email ;
	@XmlJavaTypeAdapter(value=JaxbAdapter.class)
	private Date create_time ;
	@XmlJavaTypeAdapter(value=JaxbAdapter.class)
	private Date update_time ;
	private String decription ;
	private Long superior ;
	private Integer level_scope ;
	private Boolean isroot ;
	private Integer access_permission ;
	private Boolean is_deleted ;
	private Integer status ;
	@XmlElementWrapper(name="departments")  
    @XmlElements({//表示或的关系，list中内容可以为以下两种类型  
        @XmlElement(name="ExDepart",type=ExDepart.class),  
        @XmlElement(name="d",type=String.class)  
    })  
	private List<ExDepart> deps;
	public ExAccount() {
		deps = new ArrayList<ExDepart>();
	}

	public List<ExDepart> getDeps() {
		return deps;
	}

	public void setDeps(List<ExDepart> deps) {
		this.deps = deps;
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

	public String getSecond_name() {
		return second_name;
	}

	public void setSecond_name(String second_name) {
		this.second_name = second_name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getShortname() {
		return shortname;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname;
	}

	public String getGroup_shortname() {
		return group_shortname;
	}

	public void setGroup_shortname(String group_shortname) {
		this.group_shortname = group_shortname;
	}

	public Integer getSort_id() {
		return sort_id;
	}

	public void setSort_id(Integer sort_id) {
		this.sort_id = sort_id;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public String getAdmin_name() {
		return admin_name;
	}

	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}

	public Long getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(Long admin_id) {
		this.admin_id = admin_id;
	}

	public String getAdmin_email() {
		return admin_email;
	}

	public void setAdmin_email(String admin_email) {
		this.admin_email = admin_email;
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

	public String getDecription() {
		return decription;
	}

	public void setDecription(String decription) {
		this.decription = decription;
	}

	public Long getSuperior() {
		return superior;
	}

	public void setSuperior(Long superior) {
		this.superior = superior;
	}

	public Integer getLevel_scope() {
		return level_scope;
	}

	public void setLevel_scope(Integer level_scope) {
		this.level_scope = level_scope;
	}

	public Boolean getIsroot() {
		return isroot;
	}

	public void setIsroot(Boolean isroot) {
		this.isroot = isroot;
	}

	public Integer getAccess_permission() {
		return access_permission;
	}

	public void setAccess_permission(Integer access_permission) {
		this.access_permission = access_permission;
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
	
	
}
