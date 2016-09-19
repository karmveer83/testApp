package org.warp.entities.admin;
import java.sql.Timestamp;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import org.apache.tapestry5.beaneditor.NonVisual;
import org.apache.tapestry5.beaneditor.Validate;
import org.warp.entities.admin.ActionURL;

@Entity
@Table(name="ACTION_ROLE_MAPPING")
public class ActionRoleMapping {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NonVisual
	@Column(name="MAPPING_ID")
	public Long actionRoleMappingLId;

	@Validate("required")
	public Long roleId;
	
	@Column(name="URL_ID")
	@Validate("required")
	public Long actionURLId;
	
	@Column(name="CREATED_DT")
	public Timestamp createdDt;
	
	@Column(name="LAST_UPDATED_DT")
	public Timestamp lastUpdatedDt;
	
	

}
