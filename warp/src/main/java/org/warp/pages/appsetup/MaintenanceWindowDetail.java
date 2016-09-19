package org.warp.pages.appsetup;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.warp.entities.application.MaintenanceWindow;
import org.warp.services.dao.admin.MaintenanceWindowDAO;

@Import(stylesheet="appsetup.css")
public class MaintenanceWindowDetail {
	@Property
	MaintenanceWindow maintenanceWindow;
	
	@Property
	Long maintenanceWindowId;
	
	@Inject
	MaintenanceWindowDAO maintenanceWindowFinder;
	
	 void setupRender() {
	        
	        // Get person with id 1 - ask business service to find it (from the database).
	        
		 maintenanceWindow = maintenanceWindowFinder.findMaintenanceWindow(maintenanceWindowId);


	    }
	 
	 void onActivate(Long maintenanceWindowId) {
	        this.maintenanceWindowId = maintenanceWindowId;
	    }
	    
	    Long onPassivate() {
	        return maintenanceWindowId;
	    }

}
