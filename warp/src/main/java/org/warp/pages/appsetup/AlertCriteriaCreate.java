package org.warp.pages.appsetup;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.BeanEditForm;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.warp.entities.application.AlertCriteria;
import org.warp.services.dao.admin.AlertCriteriaDAO;

@Import(stylesheet="appsetup.css")
public class AlertCriteriaCreate {
	@InjectPage
	private AlertCriteriaSetup backTo;
	

	
	@Property
	private AlertCriteria alertCriteria;
	
	@Property
	private String alertCriteriaPageName;
	
	@Inject
	private AlertCriteriaDAO alertCriteriaCreateService;
	
    @InjectComponent("alertCriteriaCreateForm")
    private BeanEditForm alertCriteriaCreateForm;
    
    void onPrepareForRender() throws Exception {

        // If fresh start, make sure there's a Person object available.

        if (alertCriteriaCreateForm.isValid()) {
        	alertCriteria = new AlertCriteria();
        }
    }
    
    void onValidateFromAlertCriteriaCreateForm() {



        if (alertCriteriaCreateForm.getHasErrors()) {
            // We get here only if a server-side validator detected an error.
            return;
        }

        try {
        	alertCriteriaCreateService.add(alertCriteria); 
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
