package org.warp.entities.admin;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;
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
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NonVisual
	public Long roleId;
	

	@Validate("required")
	@Column(name="ROLE_DESC", unique=true)
	public String roleDesc;

	public boolean isActive;
	
	@Column(name="CREATED_DT")
	public Timestamp createdDt;
	
	@Column(name="LAST_UPDATED_DT")
	public Timestamp lastUpdatedDt;
	
	@ManyToOne 
	@JoinColumn
	(name="TENANTID")
	Tenant tenant;
	
	@ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name="ACTION_ROLE_MAPPING",
          joinColumns=@JoinColumn(name="ROLEID"),
          inverseJoinColumns=@JoinColumn(name="ACTION_URL_ID"))
    public List<ActionURL> actionURLs;
}
