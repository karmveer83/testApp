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
import org.warp.entities.admin.User;
import org.warp.services.dao.admin.UserDAO;

/**
 * Start page of application warp.
 */

@Import(stylesheet = "appsetup.css")
public class UserSetup {
	@Inject
	private Logger logger;
	
	//private List<User> users;

//	@Property
//	@ActivationRequestParameter("searchUser")
//	private String searchUserName;
	
	@Property
	User userListItem;
	
	

//	@InjectComponent
//	private Zone userListZone;
	
	@InjectComponent
	private Grid grid;

	@Inject
	private Request request;

	@Inject
	private AjaxResponseRenderer ajaxResponseRenderer;
	
	@Inject
	private UserDAO userFinder;
	
	@Value("${GRID_MAX_RESULTS}")
	private int maxResults ;
	
	
    void setupRender() {

        // Build 2 of the filters from the names found in a list of all persons.
    	 //List<User> users = userFinder.findUsers(maxResults);
    	
        if (grid.getSortModel().getSortConstraints().isEmpty()) {
            grid.getSortModel().updateSort("loginId");
        }
    	
   }
//
//    void onValidateFromFilterCriteria() {
//        if (request.isXHR()) {
//            ajaxResponseRenderer.addRender(pageListZone);
//        }
   
//    }
    public  List<User>  getUserList() {
        return  userFinder.findUsers(maxResults);
    }


}
