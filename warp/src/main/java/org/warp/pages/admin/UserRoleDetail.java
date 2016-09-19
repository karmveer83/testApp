package org.warp.pages.admin;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.warp.entities.admin.ActionURL;
import org.warp.services.dao.admin.ActionURLDAO;

@Import(stylesheet="appsetup.css")
public class UserRoleDetail {
	@Property
	ActionURL actionURL;
	
	@Property
	Long actionURLId;
	
	@Inject
	ActionURLDAO pageFinderService;
	
	 void setupRender() {
	        
	        // Get person with id 1 - ask business service to find it (from the database).
	        
		 actionURL = pageFinderService.findPage(actionURLId);


	    }
	 
	 void onActivate(Long actionURLId) {
	        this.actionURLId = actionURLId;
	    }
	    
	    Long onPassivate() {
	        return actionURLId;
	    }

}
