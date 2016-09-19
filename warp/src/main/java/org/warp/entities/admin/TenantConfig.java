package org.warp.entities.admin;
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
@Table(name="TENANT_CONFIG")
public class TenantConfig {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NonVisual
	public Long configId;
	
	@Validate("required")
	public Long tenantId;

	@Validate("required")
	public String configType;
	
	@Validate("required")
	public String configKey;
	
	@Validate("required")
	public String configValue;
	
	@Column(name="CREATED_DT")
	public Timestamp createdDt;
	
	@Column(name="LAST_UPDATED_DT")
	public Timestamp lastUpdatedDt;
	
	@ManyToOne 
	@JoinColumn
	(name="TENANTID")
	Tenant tenant;
	

}
