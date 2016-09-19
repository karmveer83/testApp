package org.warp.pages.appsetup;

import java.util.List;

import org.apache.tapestry5.annotations.ActivationRequestParameter;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Grid;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.grid.GridDataSource;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.Value;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;
import org.slf4j.Logger;
import org.warp.entities.admin.ActionURL;
import org.warp.services.dao.admin.ActionURLDAO;

/**
 * Start page of application warp.
 */

@Import(stylesheet = "appsetup.css")
public class PageSetup {
	@Inject
	private Logger logger;
	
	private List<ActionURL> actionURLs;

	@Property
	@ActivationRequestParameter("navBar")
	private String navBarHeader;
	
	@Property
	ActionURL actionURL;

	@InjectComponent
	private Zone pageListZone;
	
	@InjectComponent
	private Grid grid;

	@Inject
	private Request request;

	@Inject
	private AjaxResponseRenderer ajaxResponseRenderer;
	
	@Inject
	private ActionURLDAO pageFinderService;
	
	@Value("${GRID_MAX_RESULTS}")
	private int maxResults ;
	
	@Property
	private List<String> navBarHeaders=pageFinderService.findAllNavHeaders();
	
    void setupRender() {

        // Build 2 of the filters from the names found in a list of all persons.
    	 List<ActionURL> actionURLs = pageFinderService.findPages(maxResults);
    	
        if (grid.getSortModel().getSortConstraints().isEmpty()) {
            grid.getSortModel().updateSort("actionURLPageName");
        }
    	
   }

    void onValidateFromFilterCriteria() {
        if (request.isXHR()) {
            ajaxResponseRenderer.addRender(pageListZone);
        }
   
    }
    public  List<ActionURL>  getActionURLs() {
        return  pageFinderService.findPages(navBarHeader,maxResults);
    }


}
