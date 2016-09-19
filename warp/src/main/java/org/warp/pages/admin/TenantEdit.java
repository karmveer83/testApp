package org.warp.pages.admin;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.BeanEditForm;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.warp.entities.siteadmin.Tenant;
import org.warp.pages.admin.TenantSetup;
import org.warp.services.dao.admin.TenantDAO;

@Import(stylesheet="appsetup.css")
public class TenantEdit {
	@Property
	Tenant tenant;
	
	@Inject
	TenantDAO tenantFinder;
	
	private Long tenantId;
	
    @InjectComponent("tenantForm")
    private BeanEditForm tenantForm;

	@InjectPage
	private TenantSetup backTo;
	 
	 void onActivate(Long tenantId) {
	        this.tenantId = tenantId;
	    }
	    
	    Long onPassivate() {
	        return tenantId;
	    }
	    
	    void onPrepareForRender() throws Exception {

	        // If fresh start, make sure there's a Person object available.

	        if (tenantForm.isValid()) {
	            tenant = tenantFinder.findTenant(tenantId);

	            // We'd like to handle null person in the template, but can't because we're in BeanEditpageEditForm 
	            // BeanEditForm doesn't handle null object well, so throw an exception to bypass it.

	            if (tenant == null) {
	                throw new Exception("Tenant " + tenantId + " does not exist.");
	            }
	        }

	    }

	    // Form bubbles up the PREPARE_FOR_SUBMIT event during form submission.

	    void onPrepareForSubmit() {

	        // Get Person object for the form fields to overlay.
	        tenant = tenantFinder.findTenant(tenantId);

	        if (tenant == null) {
	        	tenantForm.recordError("Person has been deleted by another process.");
	            // Instantiate an empty person to avoid NPE in the BeanEditpageEditForm
	            tenant = new Tenant();
	        }
	    }

	    void onValidateFromTenantForm() {


	        if (tenantForm.getHasErrors()) {
	            // We get here only if a server-side validator detected an error.
	            return;
	        }

	        try {
	        	tenantFinder.update(tenant); 
	        }
	        catch (Exception e) {
	            // Display the cause. In a real system we would try harder to get a tenant-friendly message.
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
