package org.warp.pages.admin;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.BeanEditForm;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.warp.entities.admin.Role;
import org.warp.services.dao.admin.RoleDAO;

@Import(stylesheet="appsetup.css")
public class RoleCreate {
	@InjectPage
	private RoleSetup backTo;
	

	
	@Property
	private Role role;
	
	@Property
	private String rolePageName;
	
	@Inject
	private RoleDAO roleCreateService;
	
    @InjectComponent("roleCreateForm")
    private BeanEditForm roleCreateForm;
    
    void onPrepareForRender() throws Exception {

        // If fresh start, make sure there's a Person object available.

        if (roleCreateForm.isValid()) {
        	role = new Role();
        }
    }
    
    void onValidateFromRoleCreateForm() {



        if (roleCreateForm.getHasErrors()) {
            // We get here only if a server-side validator detected an error.
            return;
        }

        try {
        	roleCreateService.add(role); 
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
