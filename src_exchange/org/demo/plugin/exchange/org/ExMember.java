package org.demo.plugin.exchange.org;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.demo.plugin.exchange.standard.JaxbAdapter;
@XmlRootElement(name="ExMember")
@XmlAccessorType(XmlAccessType.FIELD) 
public class ExMember {
	private Long id;
	private String name ;
	private String code;
	private Boolean enabled;
	private String primaryLanguange;
	private Integer sortId;
	private Byte state ;
	private Byte type ;
	private Boolean isInternal;
	private Boolean isLoginable;
	private boolean isValid   ;
	private Boolean isAssigned;
	private Boolean isAdmin;
	@XmlJavaTypeAdapter(value=JaxbAdapter.class)
	private Date createTime;
	@XmlJavaTypeAdapter(value=JaxbAdapter.class)
	private Date updateTime;
	private Long OrgDepartmentId;
	private Long OrgLevelId ;
	private Long OrgAccountId ;
	private Long OrgPostId;
	private Long AgentId ;
	private Long AgentToId;
	@XmlJavaTypeAdapter(value=JaxbAdapter.class)
	private Date AgentTime;
	private String telNumber;
	private Boolean isDeleted   ;
	private String emailAddress;
	private Integer gender;
	@XmlJavaTypeAdapter(value=JaxbAdapter.class)
	private Date birthday;
	private Long OrgDutyLevelId;
	private Integer status ;
	private String description;
	
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
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	public String getPrimaryLanguange() {
		return primaryLanguange;
	}
	public void setPrimaryLanguange(String primaryLanguange) {
		this.primaryLanguange = primaryLanguange;
	}
	public Integer getSortId() {
		return sortId;
	}
	public void setSortId(Integer sortId) {
		this.sortId = sortId;
	}
	public Byte getState() {
		return state;
	}
	public void setState(Byte state) {
		this.state = state;
	}
	public Byte getType() {
		return type;
	}
	public void setType(Byte type) {
		this.type = type;
	}
	public Boolean getIsInternal() {
		return isInternal;
	}
	public void setIsInternal(Boolean isInternal) {
		this.isInternal = isInternal;
	}
	public Boolean getIsLoginable() {
		return isLoginable;
	}
	public void setIsLoginable(Boolean isLoginable) {
		this.isLoginable = isLoginable;
	}
	public boolean isValid() {
		return isValid;
	}
	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}
	public Boolean getIsAssigned() {
		return isAssigned;
	}
	public void setIsAssigned(Boolean isAssigned) {
		this.isAssigned = isAssigned;
	}
	public Boolean getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Long getOrgDepartmentId() {
		return OrgDepartmentId;
	}
	public void setOrgDepartmentId(Long orgDepartmentId) {
		OrgDepartmentId = orgDepartmentId;
	}
	public Long getOrgLevelId() {
		return OrgLevelId;
	}
	public void setOrgLevelId(Long orgLevelId) {
		OrgLevelId = orgLevelId;
	}
	public Long getOrgAccountId() {
		return OrgAccountId;
	}
	public void setOrgAccountId(Long orgAccountId) {
		OrgAccountId = orgAccountId;
	}
	public Long getOrgPostId() {
		return OrgPostId;
	}
	public void setOrgPostId(Long orgPostId) {
		OrgPostId = orgPostId;
	}
	public Long getAgentId() {
		return AgentId;
	}
	public void setAgentId(Long agentId) {
		AgentId = agentId;
	}
	public Long getAgentToId() {
		return AgentToId;
	}
	public void setAgentToId(Long agentToId) {
		AgentToId = agentToId;
	}
	public Date getAgentTime() {
		return AgentTime;
	}
	public void setAgentTime(Date agentTime) {
		AgentTime = agentTime;
	}
	public String getTelNumber() {
		return telNumber;
	}
	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}
	public Boolean getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Long getOrgDutyLevelId() {
		return OrgDutyLevelId;
	}
	public void setOrgDutyLevelId(Long orgDutyLevelId) {
		OrgDutyLevelId = orgDutyLevelId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
