package org.warp.entities.application;
import java.sql.Timestamp;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import org.apache.tapestry5.beaneditor.NonVisual;
import org.apache.tapestry5.beaneditor.Validate;
@Entity
@Table(name="ALERT_CRITERIA")
public class AlertCriteria {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NonVisual
	public Long alertCriteriaId;
	
	@Column(name="TENANTID")
	@Validate("required")
	public Long alertCriteriaTenantId;
	
	@Column(name="ALERT_CRITERIA_UID", unique=true)
	@Validate("required")
	public String alertCriteriaUID;
	
	@Column(name="ALERT_CRITERIA_HOST")
	@Validate("required")
	public String alertCriteriaHost;
	
	@Column(name="ALERT_CRITERIA_HOSTGROUP")
	@Validate("required")
	public String alertCriteriaHostGroup;
	
	@Validate("required")
	public String alertLevel;
	
	@Validate("required")
	public String alertType;

	@Column(name="ALERT_CRITERIA_DESC", unique=true)
	@Validate("required")
	public String alertCriteriaDescription;
	
	
	@Validate("required")
	public boolean requireAlarm;
	
	@Validate("required")
	public boolean requireEmail;
	
	@Validate("required")
	public boolean requireAcknowledgement;
	
	@Column(name="CREATED_DT")
	public Timestamp createdDt;
	
	@Column(name="LAST_UPDATED_DT")
	public Timestamp lastUpdatedDt;
}
