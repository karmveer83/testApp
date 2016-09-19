package org.warp.pages.admin;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.BeanEditForm;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.warp.entities.admin.Role;
import org.warp.pages.admin.RoleSetup;
import org.warp.services.dao.admin.RoleDAO;

@Import(stylesheet="appsetup.css")
public class RoleEdit {
	@Property
	Role role;
	
	@Inject
	RoleDAO roleFinder;
	
	private Long roleId;
	
    @InjectComponent("roleForm")
    private BeanEditForm roleForm;

	@InjectPage
	private RoleSetup backTo;
	 
	 void onActivate(Long roleId) {
	        this.roleId = roleId;
	    }
	    
	    Long onPassivate() {
	        return roleId;
	    }
	    
	    void onPrepareForRender() throws Exception {

	        // If fresh start, make sure there's a Person object available.

	        if (roleForm.isValid()) {
	            role = roleFinder.findRole(roleId);

	            // We'd like to handle null person in the template, but can't because we're in BeanEditpageEditForm 
	            // BeanEditForm doesn't handle null object well, so throw an exception to bypass it.

	            if (role == null) {
	                throw new Exception("Role " + roleId + " does not exist.");
	            }
	        }

	    }

	    // Form bubbles up the PREPARE_FOR_SUBMIT event during form submission.

	    void onPrepareForSubmit() {

	        // Get Person object for the form fields to overlay.
	        role = roleFinder.findRole(roleId);

	        if (role == null) {
	        	roleForm.recordError("Person has been deleted by another process.");
	            // Instantiate an empty person to avoid NPE in the BeanEditpageEditForm
	            role = new Role();
	        }
	    }

	    void onValidateFromRoleForm() {


	        if (roleForm.getHasErrors()) {
	            // We get here only if a server-side validator detected an error.
	            return;
	        }

	        try {
	        	roleFinder.update(role); 
	        }
	        catch (Exception e) {
	            // Display the cause. In a real system we would try harder to get a role-friendly message.
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
