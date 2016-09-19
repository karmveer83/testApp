package org.warp.pages.appsetup;

import java.util.List;

import org.apache.tapestry5.annotations.ActivationRequestParameter;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Grid;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.Value;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;
import org.slf4j.Logger;
import org.warp.entities.application.MaintenanceWindow;
import org.warp.services.dao.admin.MaintenanceWindowDAO;
import org.warp.services.dao.admin.HostGroupDAO;

/**
 * Start maintenanceWindow of application warp.
 */

@Import(stylesheet = "appsetup.css")
public class MaintenanceWindowSetup {
	@Inject
	private Logger logger;
	


	private List<MaintenanceWindow> maintenanceWindows;

	@Property
	@ActivationRequestParameter("appselect")
	private String application;
	
	@Property
	MaintenanceWindow maintenanceWindow;

	@InjectComponent
	private Zone maintenanceWindowListZone;
	
	@InjectComponent
	private Grid grid;

	@Inject
	private Request request;

	@Inject
	private AjaxResponseRenderer ajaxResponseRenderer;
	
	@Inject
	private MaintenanceWindowDAO maintenanceWindowFinder;
	
	@Inject
	private HostGroupDAO appfinder;
	
	@Value("${GRID_MAX_RESULTS}")
	private int maxResults ;
	
	@Property
	private List<String> applications=appfinder.findHostGroupNames();
	
    void setupRender() {

        // Build 2 of the filters from the names found in a list of all persons.
    	 List<MaintenanceWindow> maintenanceWindows = maintenanceWindowFinder.findMaintenanceWindows(maxResults);
    	
        if (grid.getSortModel().getSortConstraints().isEmpty()) {
            grid.getSortModel().updateSort("endDt");
        }
    	
   }

    void onValidateFromFilterCriteria() {
        if (request.isXHR()) {
            ajaxResponseRenderer.addRender(maintenanceWindowListZone);
        }
   
    }
    public  List<MaintenanceWindow>  getMaintenanceWindows() {
        return  maintenanceWindowFinder.findActiveMaintenanceWindows(application, maxResults);
    }


}
