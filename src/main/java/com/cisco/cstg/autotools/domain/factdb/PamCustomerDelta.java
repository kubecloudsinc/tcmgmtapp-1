package com.cisco.cstg.autotools.domain.factdb;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "PAM_CUSTOMER_DELTA",schema="PAM_ADMIN")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PamCustomerDelta implements Serializable {

	private static final long serialVersionUID = 6605159114199196385L;
	
//	
//	 CUSTOMER_DELTA_OBJID  NUMBER(20),
//	  CCO_ID                VARCHAR2(100 BYTE),
//	  DURATION              NUMBER,
//	  SHOW_SETTINGS         NUMBER,
//	  CREATE_DATE           DATE,
//	  CREATE_ORA_LOGIN      VARCHAR2(30 BYTE),
//	  CREATE_USER_ID        VARCHAR2(50 BYTE),
//	  UPDATE_DATE           DATE,
//	  UPDATE_ORA_LOGIN      VARCHAR2(30 BYTE),
//	  UPDATE_USER_ID        VARCHAR2(50 BYTE)

	private Long customerDeltaObjId;
	private String ccoId;
	private Long duration;
	private Long showSettings;
	private Date createDate;
	private String createOraLogin;
	private String createUserId;
	private Date updateDate;
	private String updateOraLogin;
	private String updateUserId;
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="CUSTOMER_DELTA_OBJID")
	public Long getCustomerDeltaObjId() {
		return customerDeltaObjId;
	}

	public void setCustomerDeltaObjId(Long customerDeltaObjId) {
		this.customerDeltaObjId = customerDeltaObjId;
	}

	@Column(name="CCO_ID")
	public String getCcoId() {
		return ccoId;
	}

	public void setCcoId(String ccoId) {
		this.ccoId = ccoId;
	}

	@Column(name="DURATION")
	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	@Column(name="SHOW_SETTINGS")
	public Long getShowSettings() {
		return showSettings;
	}

	public void setShowSettings(Long showSettings) {
		this.showSettings = showSettings;
	}

	@Column(name="CREATE_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name="CREATE_ORA_LOGIN")
	public String getCreateOraLogin() {
		return createOraLogin;
	}

	public void setCreateOraLogin(String createOraLogin) {
		this.createOraLogin = createOraLogin;
	}

	@Column(name="CREATE_USER_ID")
	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	@Column(name="UPDATE_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Column(name="UPDATE_ORA_LOGIN")
	public String getUpdateOraLogin() {
		return updateOraLogin;
	}

	public void setUpdateOraLogin(String updateOraLogin) {
		this.updateOraLogin = updateOraLogin;
	}

	@Column(name="UPDATE_USER_ID")
	public String getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}
}
