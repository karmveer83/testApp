package org.warp.services.dao.admin;

import java.util.List;

import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.warp.entities.siteadmin.Tenant;



public interface TenantDAO {
	
	
	 Tenant findTenant(Long tenantId);
	
	 List<Tenant> findTenants();

	 List<Tenant> findTenants(int maxResults);
	 	 

	  @CommitAfter
	  void add(Tenant tenant);
	 
	  @CommitAfter
	  void update(Tenant tenant);
	 
	  @CommitAfter
	  void delete(Tenant tenant);

}
