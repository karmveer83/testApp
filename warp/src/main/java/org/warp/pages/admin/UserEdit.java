package org.warp.pages.admin;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.BeanEditForm;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.warp.entities.admin.User;
import org.warp.pages.admin.UserSetup;
import org.warp.services.dao.admin.UserDAO;

@Import(stylesheet="appsetup.css")
public class UserEdit {
	@Property
	User user;
	
	@Inject
	UserDAO userFinder;
	
	private Long userId;
	
    @InjectComponent("userForm")
    private BeanEditForm userForm;

	@InjectPage
	private UserSetup backTo;
	 
	 void onActivate(Long userId) {
	        this.userId = userId;
	    }
	    
	    Long onPassivate() {
	        return userId;
	    }
	    
	    void onPrepareForRender() throws Exception {

	        // If fresh start, make sure there's a Person object available.

	        if (userForm.isValid()) {
	            user = userFinder.findUser(userId);

	            // We'd like to handle null person in the template, but can't because we're in BeanEditpageEditForm 
	            // BeanEditForm doesn't handle null object well, so throw an exception to bypass it.

	            if (user == null) {
	                throw new Exception("User " + userId + " does not exist.");
	            }
	        }

	    }

	    // Form bubbles up the PREPARE_FOR_SUBMIT event during form submission.

	    void onPrepareForSubmit() {

	        // Get Person object for the form fields to overlay.
	        user = userFinder.findUser(userId);

	        if (user == null) {
	        	userForm.recordError("Person has been deleted by another process.");
	            // Instantiate an empty person to avoid NPE in the BeanEditpageEditForm
	            user = new User();
	        }
	    }

	    void onValidateFromUserForm() {


	        if (userForm.getHasErrors()) {
	            // We get here only if a server-side validator detected an error.
	            return;
	        }

	        try {
	        	userFinder.update(user); 
	        }
	        catch (Exception e) {
	            // Display the cause. In a real system we would try harder to get a user-friendly message.
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
