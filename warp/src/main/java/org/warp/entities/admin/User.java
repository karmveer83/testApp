package org.warp.entities.admin;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.tapestry5.beaneditor.NonVisual;
import org.apache.tapestry5.beaneditor.Validate;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.warp.services.navigation.CryptoService;

@Entity
@Table(name="USER")
public class User {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@NonVisual
public Long userId;

@Validate("required")
@Column(unique=true)
public String loginId;

public String userName;

@Column(name="PASSWORD")
public String userPassword;

@Column(name="EMAIL",unique=true)
public String userEmail;

public boolean isActive;

@Column(name="CREATED_DT")
public Timestamp createdDt;

@Column(name="LAST_UPDATED_DT")
public Timestamp lastUpdatedDt;

//@ManyToOne(fetch=FetchType.EAGER) 
//@JoinColumn
//(name="TENANTID")
//public Tenant tenant;
//
//@OneToOne(fetch=FetchType.LAZY) 
//@JoinColumn
//(name="USERID")
//public UserAuthentication UserAuthentication;
//
//@OneToOne(fetch=FetchType.EAGER) 
//@JoinColumn(name="SESSIONKEY")
//public UserSession userSession;
//
//@ManyToMany(fetch=FetchType.LAZY)
//@JoinTable(name="USER_ROLE_MAPPING",
//      joinColumns=@JoinColumn(name="USERID"),
//      inverseJoinColumns=@JoinColumn(name="ROLEID"))
//public List<Role> userRoles;

}
