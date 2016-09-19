package org.warp.pages.appsetup;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.BeanEditForm;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.warp.entities.application.HostGroup;
import org.warp.services.dao.admin.HostGroupDAO;

@Import(stylesheet="appsetup.css")
public class ApplicationSetupEdit {
	@Property
	HostGroup hostGroup;
	
	@Inject
	HostGroupDAO hostGroupFinder;
	
	private Long hostGroupId;
	
    @InjectComponent("hostGroupForm")
    private BeanEditForm hostGroupForm;

	@InjectPage
	private ApplicationSetup backTo;
	 
	 void onActivate(Long hostGroupId) {
	        this.hostGroupId = hostGroupId;
	    }
	    
	    Long onPassivate() {
	        return hostGroupId;
	    }
	    
	    void onPrepareForRender() throws Exception {

	        // If fresh start, make sure there's a Person object available.

	        if (hostGroupForm.isValid()) {
	            hostGroup = hostGroupFinder.findHostGroup(hostGroupId);

	            // We'd like to handle null person in the template, but can't because we're in BeanEditpageEditForm 
	            // BeanEditForm doesn't handle null object well, so throw an exception to bypass it.

	            if (hostGroup == null) {
	                throw new Exception("HostGroup " + hostGroupId + " does not exist.");
	            }
	        }

	    }

	    // Form bubbles up the PREPARE_FOR_SUBMIT event during form submission.

	    void onPrepareForSubmit() {

	        // Get Person object for the form fields to overlay.
	        hostGroup = hostGroupFinder.findHostGroup(hostGroupId);

	        if (hostGroup == null) {
	        	hostGroupForm.recordError("Person has been deleted by another process.");
	            // Instantiate an empty person to avoid NPE in the BeanEditpageEditForm
	            hostGroup = new HostGroup();
	        }
	    }

	    void onValidateFromHostGroupForm() {


	        if (hostGroupForm.getHasErrors()) {
	            // We get here only if a server-side validator detected an error.
	            return;
	        }

	        try {
	        	hostGroupFinder.update(hostGroup); 
	        }
	        catch (Exception e) {
	            // Display the cause. In a real system we would try harder to get a hostGroup-friendly message.
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
