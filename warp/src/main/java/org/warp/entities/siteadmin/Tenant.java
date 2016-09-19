package org.warp.entities.siteadmin;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import org.apache.tapestry5.beaneditor.NonVisual;
import org.apache.tapestry5.beaneditor.Validate;
import org.warp.entities.admin.User;
import org.warp.entities.admin.Role;
import org.warp.entities.admin.TenantConfig;

@Entity
@Table(name="TENANT")
public class Tenant {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NonVisual
	public Long tenantId;
	
	@Validate("required")
	public String tenant;
	
	@Column(unique=true)
	public String tenantName;
	
	public boolean isActive;
	
	@Column(name="CREATED_DT")
	public Timestamp createdDt;
	
	@Column(name="LAST_UPDATED_DT")
	public Timestamp lastUpdatedDt;
	
//	@OneToMany(mappedBy="tenant") 
//	List<User> users;
//	
//	@OneToMany(mappedBy="tenant") 
//	List<Role> roles;
//	
//	@OneToMany(mappedBy="tenant") 
//	List<TenantConfig> tenantConfig;
}
