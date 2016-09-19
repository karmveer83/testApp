package org.warp.pages.appsetup;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.BeanEditForm;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.warp.entities.application.AlertCriteria;
import org.warp.pages.appsetup.AlertCriteriaSetup;
import org.warp.services.dao.admin.AlertCriteriaDAO;

@Import(stylesheet="appsetup.css")
public class AlertCriteriaEdit {
	@Property
	AlertCriteria alertCriteria;
	
	@Inject
	AlertCriteriaDAO alertCriteriaFinder;
	
	private Long alertCriteriaId;
	
    @InjectComponent("alertCriteriaForm")
    private BeanEditForm alertCriteriaForm;

	@InjectPage
	private AlertCriteriaSetup backTo;
	 
	 void onActivate(Long alertCriteriaId) {
	        this.alertCriteriaId = alertCriteriaId;
	    }
	    
	    Long onPassivate() {
	        return alertCriteriaId;
	    }
	    
	    void onPrepareForRender() throws Exception {

	        // If fresh start, make sure there's a Person object available.

	        if (alertCriteriaForm.isValid()) {
	            alertCriteria = alertCriteriaFinder.findAlertCriteria(alertCriteriaId);

	            // We'd like to handle null person in the template, but can't because we're in BeanEditpageEditForm 
	            // BeanEditForm doesn't handle null object well, so throw an exception to bypass it.

	            if (alertCriteria == null) {
	                throw new Exception("AlertCriteria " + alertCriteriaId + " does not exist.");
	            }
	        }

	    }

	    // Form bubbles up the PREPARE_FOR_SUBMIT event during form submission.

	    void onPrepareForSubmit() {

	        // Get Person object for the form fields to overlay.
	        alertCriteria = alertCriteriaFinder.findAlertCriteria(alertCriteriaId);

	        if (alertCriteria == null) {
	        	alertCriteriaForm.recordError("Person has been deleted by another process.");
	            // Instantiate an empty person to avoid NPE in the BeanEditpageEditForm
	            alertCriteria = new AlertCriteria();
	        }
	    }

	    void onValidateFromAlertCriteriaForm() {


	        if (alertCriteriaForm.getHasErrors()) {
	            // We get here only if a server-side validator detected an error.
	            return;
	        }

	        try {
	        	alertCriteriaFinder.update(alertCriteria); 
	        }
	        catch (Exception e) {
	            // Display the cause. In a real system we would try harder to get a alertCriteria-friendly message.
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
