package org.warp.pages.appsetup;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.BeanEditForm;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.warp.entities.application.MaintenanceWindow;
import org.warp.services.dao.admin.MaintenanceWindowDAO;

@Import(stylesheet="appsetup.css")
public class MaintenanceWindowCreate {
	@InjectPage
	private MaintenanceWindowSetup backTo;
	

	
	@Property
	private MaintenanceWindow maintenanceWindow;
	
	@Property
	private String maintenanceWindowPageName;
	
	@Inject
	private MaintenanceWindowDAO maintenanceWindowCreateService;
	
    @InjectComponent("maintenanceWindowCreateForm")
    private BeanEditForm maintenanceWindowCreateForm;
    
    void onPrepareForRender() throws Exception {

        // If fresh start, make sure there's a Person object available.

        if (maintenanceWindowCreateForm.isValid()) {
        	maintenanceWindow = new MaintenanceWindow();
        }
    }
    
    void onValidateFromMaintenanceWindowCreateForm() {



        if (maintenanceWindowCreateForm.getHasErrors()) {
            // We get here only if a server-side validator detected an error.
            return;
        }

        try {
        	maintenanceWindowCreateService.add(maintenanceWindow); 
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
