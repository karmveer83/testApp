package org.warp.entities.admin;
import java.sql.Timestamp;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;
import org.apache.tapestry5.beaneditor.NonVisual;
import org.apache.tapestry5.beaneditor.Validate;



@Entity
@Table(name="USER_SESSION_INFO")
public class UserSession {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@NonVisual
public Long sessionkey;

@Column(name="JSESSION_ID", unique=true)
public String jSessionId;

public boolean isActive;

@Column(name="CREATED_DT")
public Timestamp createdDt;

@Column(name="LAST_UPDATED_DT")
public Timestamp lastUpdatedDt;


}
