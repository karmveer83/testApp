package org.warp.entities.application;
import java.sql.Timestamp;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import org.apache.tapestry5.beaneditor.NonVisual;
import org.apache.tapestry5.beaneditor.Validate;
import org.warp.entities.siteadmin.Tenant;

@Entity
@Table(name="ALERT_RECORD")
public class AlertRecord {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NonVisual
	public Long alertRecordId;

	@Validate("required")
	public Long alertCriteriaId;
	
	@Column(name="HOSTNAME")
	@Validate("required")
	public Long alertRecordHostName;
	
	@Validate("required")
	public String alertLevel;
	
	@Validate("required")
	public String alertType;

	public boolean needAlarm;
	
	public boolean needEmail;
	
	public boolean needAcknowledgement;
	
	public boolean isAlarmRaised;
	
	public boolean isEmailSent;

	public boolean isAcklnowedged;
	
	@Validate("required")
	public boolean isMatched;
	
	@Column(name="ACKNOWLEDGED_BY")
	public Long acknowledgedUserId;
	
	@Column(name="CREATED_DT")
	public Timestamp createdDt;
	
	@Column(name="LAST_UPDATED_DT")
	public Timestamp lastUpdatedDt;
	
	@ManyToOne(fetch=FetchType.LAZY) 
	@JoinColumn(name="ALERTCRITERIAID")
	public AlertCriteria alertCriteria;
}
