package org.warp.entities.admin;
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
@Table(name="USER_ROLE_MAPPING")
public class UserRoleMapping {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NonVisual
	public Long actionRoleMappingLId;

	@Validate("required")
	public Long roleId;
	
	@Validate("required")
	public Long userId;
	
	@Column(name="CREATED_DT")
	public Timestamp createdDt;

	@Column(name="LAST_UPDATED_DT")
	public Timestamp lastUpdatedDt;
	
	
}
