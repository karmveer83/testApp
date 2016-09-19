package org.warp.pages.appsetup;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.BeanEditForm;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.warp.entities.application.MaintenanceWindow;
import org.warp.pages.appsetup.MaintenanceWindowSetup;
import org.warp.services.dao.admin.MaintenanceWindowDAO;

@Import(stylesheet="appsetup.css")
public class MaintenanceWindowEdit {
	@Property
	MaintenanceWindow MaintenanceWindow;
	
	@Inject
	MaintenanceWindowDAO MaintenanceWindowFinder;
	
	private Long MaintenanceWindowId;
	
    @InjectComponent("MaintenanceWindowForm")
    private BeanEditForm MaintenanceWindowForm;

	@InjectPage
	private MaintenanceWindowSetup backTo;
	 
	 void onActivate(Long MaintenanceWindowId) {
	        this.MaintenanceWindowId = MaintenanceWindowId;
	    }
	    
	    Long onPassivate() {
	        return MaintenanceWindowId;
	    }
	    
	    void onPrepareForRender() throws Exception {

	        // If fresh start, make sure there's a Person object available.

	        if (MaintenanceWindowForm.isValid()) {
	            MaintenanceWindow = MaintenanceWindowFinder.findMaintenanceWindow(MaintenanceWindowId);

	            // We'd like to handle null person in the template, but can't because we're in BeanEditpageEditForm 
	            // BeanEditForm doesn't handle null object well, so throw an exception to bypass it.

	            if (MaintenanceWindow == null) {
	                throw new Exception("MaintenanceWindow " + MaintenanceWindowId + " does not exist.");
	            }
	        }

	    }

	    // Form bubbles up the PREPARE_FOR_SUBMIT event during form submission.

	    void onPrepareForSubmit() {

	        // Get Person object for the form fields to overlay.
	        MaintenanceWindow = MaintenanceWindowFinder.findMaintenanceWindow(MaintenanceWindowId);

	        if (MaintenanceWindow == null) {
	        	MaintenanceWindowForm.recordError("Person has been deleted by another process.");
	            // Instantiate an empty person to avoid NPE in the BeanEditpageEditForm
	            MaintenanceWindow = new MaintenanceWindow();
	        }
	    }

	    void onValidateFromMaintenanceWindowForm() {


	        if (MaintenanceWindowForm.getHasErrors()) {
	            // We get here only if a server-side validator detected an error.
	            return;
	        }

	        try {
	        	MaintenanceWindowFinder.update(MaintenanceWindow); 
	        }
	        catch (Exception e) {
	            // Display the cause. In a real system we would try harder to get a MaintenanceWindow-friendly message.
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
