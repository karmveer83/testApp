package org.warp.pages.appsetup;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.BeanEditForm;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.warp.entities.admin.ActionURL;
import org.warp.services.dao.admin.ActionURLDAO;

@Import(stylesheet="appsetup.css")
public class PageSetupEdit {
	@Property
	ActionURL actionURL;
	
	@Inject
	ActionURLDAO pageFinderService;
	
	private Long actionURLId;
	
    @InjectComponent("actionURLForm")
    private BeanEditForm actionURLForm;

	@InjectPage
	private PageSetup backTo;
	 
	 void onActivate(Long actionURLId) {
	        this.actionURLId = actionURLId;
	    }
	    
	    Long onPassivate() {
	        return actionURLId;
	    }
	    
	    void onPrepareForRender() throws Exception {

	        // If fresh start, make sure there's a Person object available.

	        if (actionURLForm.isValid()) {
	            actionURL = pageFinderService.findPage(actionURLId);

	            // We'd like to handle null person in the template, but can't because we're in BeanEditpageEditForm 
	            // BeanEditForm doesn't handle null object well, so throw an exception to bypass it.

	            if (actionURL == null) {
	                throw new Exception("Page " + actionURLId + " does not exist.");
	            }
	        }

	    }

	    // Form bubbles up the PREPARE_FOR_SUBMIT event during form submission.

	    void onPrepareForSubmit() {

	        // Get Person object for the form fields to overlay.
	        actionURL = pageFinderService.findPage(actionURLId);

	        if (actionURL == null) {
	        	actionURLForm.recordError("Person has been deleted by another process.");
	            // Instantiate an empty person to avoid NPE in the BeanEditpageEditForm
	            actionURL = new ActionURL();
	        }
	    }

	    void onValidateFromActionURLForm() {


	        if (actionURLForm.getHasErrors()) {
	            // We get here only if a server-side validator detected an error.
	            return;
	        }

	        try {
	        	pageFinderService.update(actionURL); 
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
