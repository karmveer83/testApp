package org.warp.services.dao.admin;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.warp.entities.siteadmin.Tenant;

public class TenantDAOImpl implements TenantDAO {

	@Inject
	Session session;

	@Inject
	private Logger logger;

	public Tenant findTenant(Long tenantId) {
		return (Tenant) session.get(Tenant.class, tenantId);
	};
	public List<Tenant> findTenants() {
		return (List<Tenant>) session.createCriteria(Tenant.class).list();
	};

	
	public List<Tenant> findTenants(int maxResults) {
		return (List<Tenant>) session.createCriteria(Tenant.class).setMaxResults(maxResults).list();
	};
	
		 
	@CommitAfter
	public void add(Tenant tenant) {

		Tenant newTenant = tenant;
		java.util.Date date = new java.util.Date();
		newTenant.createdDt = new Timestamp(date.getTime());
		session.persist(newTenant);

	};

	@CommitAfter
	public void update(Tenant tenant) {
		Tenant newTenant = tenant;
		java.util.Date date = new java.util.Date();
		newTenant.lastUpdatedDt = new Timestamp(date.getTime());
		session.persist(newTenant);

	};

	@CommitAfter
	public void delete(Tenant tenant) {

		session.delete(tenant);

	};

}
