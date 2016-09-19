package org.warp.pages.admin;

import java.util.List;

import org.apache.tapestry5.annotations.ActivationRequestParameter;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Grid;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.grid.GridDataSource;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.Value;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;
import org.slf4j.Logger;
import org.warp.entities.siteadmin.Tenant;
import org.warp.services.dao.admin.TenantDAO;

/**
 * Start page of application warp.
 */

@Import(stylesheet = "appsetup.css")
public class TenantSetup {
	@Inject
	private Logger logger;
	
	private List<Tenant> tenants;

//	@Property
//	@ActivationRequestParameter("searchTenant")
//	private String searchTenantName;
	
	@Property
	Tenant tenant;

//	@InjectComponent
//	private Zone tenantListZone;
	
	@InjectComponent
	private Grid grid;

	@Inject
	private Request request;

	@Inject
	private AjaxResponseRenderer ajaxResponseRenderer;
	
	@Inject
	private TenantDAO tenantFinder;
	
	@Value("${GRID_MAX_RESULTS}")
	private int maxResults ;
	
	
    void setupRender() {

        // Build 2 of the filters from the names found in a list of all persons.
    	 List<Tenant> tenants = tenantFinder.findTenants(maxResults);
    	
        if (grid.getSortModel().getSortConstraints().isEmpty()) {
            grid.getSortModel().updateSort("tenantName");
        }
    	
   }
//
//    void onValidateFromFilterCriteria() {
//        if (request.isXHR()) {
//            ajaxResponseRenderer.addRender(pageListZone);
//        }
   
//    }
    public  List<Tenant>  getTenants() {
        return  tenantFinder.findTenants(maxResults);
    }


}
