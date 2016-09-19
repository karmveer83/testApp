package org.warp.pages.appsetup;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.BeanEditForm;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.warp.entities.application.Host;
import org.warp.pages.appsetup.HostSetup;
import org.warp.services.dao.admin.HostDAO;

@Import(stylesheet="appsetup.css")
public class HostSetupEdit {
	@Property
	Host host;
	
	@Inject
	HostDAO hostFinder;
	
	private Long hostId;
	
    @InjectComponent("hostForm")
    private BeanEditForm hostForm;

	@InjectPage
	private HostSetup backTo;
	 
	 void onActivate(Long hostId) {
	        this.hostId = hostId;
	    }
	    
	    Long onPassivate() {
	        return hostId;
	    }
	    
	    void onPrepareForRender() throws Exception {

	        // If fresh start, make sure there's a Person object available.

	        if (hostForm.isValid()) {
	            host = hostFinder.findHost(hostId);

	            // We'd like to handle null person in the template, but can't because we're in BeanEditpageEditForm 
	            // BeanEditForm doesn't handle null object well, so throw an exception to bypass it.

	            if (host == null) {
	                throw new Exception("Host " + hostId + " does not exist.");
	            }
	        }

	    }

	    // Form bubbles up the PREPARE_FOR_SUBMIT event during form submission.

	    void onPrepareForSubmit() {

	        // Get Person object for the form fields to overlay.
	        host = hostFinder.findHost(hostId);

	        if (host == null) {
	        	hostForm.recordError("Person has been deleted by another process.");
	            // Instantiate an empty person to avoid NPE in the BeanEditpageEditForm
	            host = new Host();
	        }
	    }

	    void onValidateFromHostForm() {


	        if (hostForm.getHasErrors()) {
	            // We get here only if a server-side validator detected an error.
	            return;
	        }

	        try {
	        	hostFinder.update(host); 
	        }
	        catch (Exception e) {
	            // Display the cause. In a real system we would try harder to get a host-friendly message.
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
