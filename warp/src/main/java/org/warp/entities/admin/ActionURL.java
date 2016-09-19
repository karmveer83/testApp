package org.warp.entities.admin;
import java.sql.Timestamp;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import org.apache.tapestry5.beaneditor.NonVisual;
import org.apache.tapestry5.beaneditor.Validate;


@Entity
@Table(name="ACTION_URL")
public class ActionURL {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NonVisual
	@Column(name="URL_ID")
	public Long actionURLId;

	@Validate("required")
	@Column(name="ACTION_URL_PAGE_NAME", unique=true)
	public String actionURLPageName;
	
	@Validate("required")
	@Column(name="ACTION_URL_PAGE_TITLE")
	public String actionURLPageTitle;
	
	@Validate("required")
	@Column(name="URL_GROUP")
	public String urlGroup;
	
	@Validate("required")
	@Column(name="URL_GROUP_ORDER")
	public int urlGroupOrder;
	
	
	@Validate("required")
	@Column(name="URL_PLACEMENT")
	public String urlPlacement;
	
	@Validate("required")
	@Column(name="URL_PLACEMENT_ORDER")
	public int urlPlacementOrder;
	
	@Column(name="CREATED_DT")
	public Timestamp createdDt;
	
	@Column(name="LAST_UPDATED_DT")
	public Timestamp lastUpdatedDt;
}
