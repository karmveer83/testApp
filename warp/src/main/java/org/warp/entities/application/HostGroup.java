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
@Table(name="HOSTGROUP")
public class HostGroup {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@NonVisual
public Long hostGroupId;


@Validate("required")
@Column(name="HOST_GROUP_NAME", unique=true)
public String hostGroupName;

@Column(name="HOST_GROUP_CATEGORY")
public String hostGroupCategory;

public boolean isActive;

@Column(name="CREATED_DT")
public Timestamp createdDt;

@Column(name="LAST_UPDATED_DT")
public Timestamp lastUpdatedDt;



@Column(name="TENANT_ID")
@Validate("required")
public Long hostGroupTenantId;

@Override
public String toString() {
	return "HostGroup [hostGroupId=" + hostGroupId + ", hostGroupTenantId=" + hostGroupTenantId + ", hostGroupName="
			+ hostGroupName + ", hostGroupCategory=" + hostGroupCategory + ", isActive=" + isActive + ", createdDt="
			+ createdDt + ", lastUpdatedDt=" + lastUpdatedDt + "]";
}





}
