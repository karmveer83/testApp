package org.warp.pages.admin;

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
import org.warp.entities.siteadmin.CommonConfig;
import org.warp.services.dao.admin.CommonConfigDAO;

/**
 * Start page of application warp.
 */

@Import(stylesheet = "appsetup.css")
public class CommonConfigSetup {
	@Inject
	private Logger logger;
	
	private List<CommonConfig> commonConfigs;

//	@Property
//	@ActivationRequestParameter("searchCommonConfig")
//	private String searchCommonConfigName;
	
	@Property
	CommonConfig commonConfig;

//	@InjectComponent
//	private Zone commonConfigListZone;
	
	@InjectComponent
	private Grid grid;

	@Inject
	private Request request;

	@Inject
	private AjaxResponseRenderer ajaxResponseRenderer;
	
	@Inject
	private CommonConfigDAO commonConfigFinder;
	
	@Value("${GRID_MAX_RESULTS}")
	private int maxResults ;
	
	
    void setupRender() {

        // Build 2 of the filters from the names found in a list of all persons.
    	 List<CommonConfig> commonConfigs = commonConfigFinder.findCommonConfigs(maxResults);
    	
        if (grid.getSortModel().getSortConstraints().isEmpty()) {
            grid.getSortModel().updateSort("configType");
        }
    	
   }
//
//    void onValidateFromFilterCriteria() {
//        if (request.isXHR()) {
//            ajaxResponseRenderer.addRender(pageListZone);
//        }
   
//    }
    public  List<CommonConfig>  getCommonConfigs() {
        return  commonConfigFinder.findCommonConfigs(maxResults);
    }


}
