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
import org.warp.entities.application.Host;
import org.warp.services.dao.admin.HostDAO;
import org.warp.services.dao.admin.HostGroupDAO;

/**
 * Start host of application warp.
 */

@Import(stylesheet = "appsetup.css")
public class HostSetup {
	@Inject
	private Logger logger;
	


	private List<Host> hosts;

	@Property
	@ActivationRequestParameter("appselect")
	private String application;
	
	@Property
	Host host;

	@InjectComponent
	private Zone hostListZone;
	
	@InjectComponent
	private Grid grid;

	@Inject
	private Request request;

	@Inject
	private AjaxResponseRenderer ajaxResponseRenderer;
	
	@Inject
	private HostDAO hostFinder;
	
	@Inject
	private HostGroupDAO appfinder;
	
	@Value("${GRID_MAX_RESULTS}")
	private int maxResults ;
	
	@Property
	private List<String> applications=appfinder.findHostGroupNames();
	
    void setupRender() {

        // Build 2 of the filters from the names found in a list of all persons.
    	 List<Host> hosts = hostFinder.findHosts(maxResults);
    	
        if (grid.getSortModel().getSortConstraints().isEmpty()) {
            grid.getSortModel().updateSort("hostName");
        }
    	
   }

    void onValidateFromFilterCriteria() {
        if (request.isXHR()) {
            ajaxResponseRenderer.addRender(hostListZone);
        }
   
    }
    public  List<Host>  getHosts() {
        return  hostFinder.findHostsByHostGroup(application, maxResults);
    }


}
