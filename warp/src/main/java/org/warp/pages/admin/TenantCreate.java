package org.warp.pages.admin;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.BeanEditForm;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.warp.entities.siteadmin.Tenant;
import org.warp.services.dao.admin.TenantDAO;

@Import(stylesheet="appsetup.css")
public class TenantCreate {
	@InjectPage
	private TenantSetup backTo;
	

	
	@Property
	private Tenant tenant;
	
	@Property
	private String tenantPageName;
	
	@Inject
	private TenantDAO tenantCreateService;
	
    @InjectComponent("tenantCreateForm")
    private BeanEditForm tenantCreateForm;
    
    void onPrepareForRender() throws Exception {

        // If fresh start, make sure there's a Person object available.

        if (tenantCreateForm.isValid()) {
        	tenant = new Tenant();
        }
    }
    
    void onValidateFromTenantCreateForm() {



        if (tenantCreateForm.getHasErrors()) {
            // We get here only if a server-side validator detected an error.
            return;
        }

        try {
        	tenantCreateService.add(tenant); 
        }
        catch (Exception e) {
            
        	e.printStackTrace();
        }
    }
	
    Object onSuccess() {
        
        return backTo;
    }

    void onRefresh() {
        // By doing nothing the page will be displayed afresh.
    }
	 


}
