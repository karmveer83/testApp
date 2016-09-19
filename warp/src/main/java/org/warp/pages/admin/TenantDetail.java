package org.warp.pages.admin;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.warp.entities.siteadmin.Tenant;
import org.warp.services.dao.admin.TenantDAO;

@Import(stylesheet="appsetup.css")
public class TenantDetail {
	@Property
	Tenant tenant;
	
	@Property
	Long tenantId;
	
	@Inject
	TenantDAO tenantFinder;
	
	 void setupRender() {
	        
	        // Get person with id 1 - ask business service to find it (from the database).
	        
		 tenant = tenantFinder.findTenant(tenantId);


	    }
	 
	 void onActivate(Long tenantId) {
	        this.tenantId = tenantId;
	    }
	    
	    Long onPassivate() {
	        return tenantId;
	    }

}
