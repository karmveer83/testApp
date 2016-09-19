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
@Table(name="HOST")
public class Host {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@NonVisual
public Long hostId;

@Column(name="TENANT_ID")
@Validate("required")
public String hostTenantId;

@Column(unique=true)
@Validate("required")
public String hostName;

@Validate("required")
public String hostGroupName;

@Column(name="HOST_IP")
public String hostIPAddresss;

@Column(name="HOST_INTERFACE_ID")
public String hostInterfaceId;

@Column(name="HOST_AGENT_DIR")
public String hostAgentDir;


public boolean isActive;

@Column(name="CREATED_DT")
public Timestamp createdDt;

@Column(name="LAST_UPDATED_DT")
public Timestamp lastUpdatedDt;

@Validate("required")
@ManyToOne(fetch=FetchType.EAGER) 
@JoinColumn(name="HOSTGROUPID")
public HostGroup hostGroup;

}
