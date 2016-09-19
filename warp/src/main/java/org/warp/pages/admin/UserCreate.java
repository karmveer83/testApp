package org.warp.pages.admin;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.BeanEditForm;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.warp.entities.admin.User;
import org.warp.services.dao.admin.UserDAO;

@Import(stylesheet="appsetup.css")
public class UserCreate {
	@InjectPage
	private UserSetup backTo;
	

	
	@Property
	private User user;
	
	@Property
	private String userPageName;
	
	@Inject
	private UserDAO userCreateService;
	
    @InjectComponent("userCreateForm")
    private BeanEditForm userCreateForm;
    
    void onPrepareForRender() throws Exception {

        // If fresh start, make sure there's a Person object available.

        if (userCreateForm.isValid()) {
        	user = new User();
        }
    }
    
    void onValidateFromUserCreateForm() {



        if (userCreateForm.getHasErrors()) {
            // We get here only if a server-side validator detected an error.
            return;
        }

        try {
        	userCreateService.add(user); 
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
