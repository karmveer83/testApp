package org.warp.entities.application;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.tapestry5.beaneditor.NonVisual;
import org.apache.tapestry5.beaneditor.Validate;
@Entity
@Table(name="MAINTENANCE_WINDOW")
public class MaintenanceWindow {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NonVisual
	public Long MaintenanceWindowId;

	@Column(name="APPLICATION_ID")
	@Validate("required")
	public String hostGroupName;
	
	@Validate("required")
	@Column(name="MAINTENANCE_START_DT")
	public Date startDt;
	
	@Validate("required")
	@Column(name="MAINTENANCE_END_DT")
	public Date endDt;
	
	@Column(name="INCIDENT_OR_CHANGE_RECORD_INFO", unique=true)
	@Validate("required")
	public String incidentOrChangeDetails;

	@Column(name="CREATED_DT")
	public Timestamp createdDt;
	
	@Column(name="LAST_UPDATED_DT")
	public Timestamp lastUpdatedDt;
	

}
