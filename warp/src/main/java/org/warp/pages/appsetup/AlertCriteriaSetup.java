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
import org.warp.entities.application.AlertCriteria;
import org.warp.services.dao.admin.AlertCriteriaDAO;
import org.warp.services.dao.admin.HostGroupDAO;

/**
 * Start alertCriteria of application warp.
 */

@Import(stylesheet = "appsetup.css")
public class AlertCriteriaSetup {
	@Inject
	private Logger logger;
	


	private List<AlertCriteria> alertCriterias;

	@Property
	@ActivationRequestParameter("appselect")
	private String application;
	
	@Property
	AlertCriteria alertCriteria;

	@InjectComponent
	private Zone alertCriteriaListZone;
	
	@InjectComponent
	private Grid grid;

	@Inject
	private Request request;

	@Inject
	private AjaxResponseRenderer ajaxResponseRenderer;
	
	@Inject
	private AlertCriteriaDAO alertCriteriaFinder;
	
	@Inject
	private HostGroupDAO appfinder;
	
	@Value("${GRID_MAX_RESULTS}")
	private int maxResults ;
	
	@Property
	private List<String> applications=appfinder.findHostGroupNames();
	
    void setupRender() {

        // Build 2 of the filters from the names found in a list of all persons.
    	 List<AlertCriteria> alertCriterias = alertCriteriaFinder.findAlertCriterias(maxResults);
    	
        if (grid.getSortModel().getSortConstraints().isEmpty()) {
            grid.getSortModel().updateSort("alertCriteriaUID");
        }
    	
   }

    void onValidateFromFilterCriteria() {
        if (request.isXHR()) {
            ajaxResponseRenderer.addRender(alertCriteriaListZone);
        }
   
    }
    public  List<AlertCriteria>  getAlertCriterias() {
        return  alertCriteriaFinder.findAlertCriteriasByApplication(application, maxResults);
    }


}
