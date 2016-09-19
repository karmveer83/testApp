package org.warp.pages.appsetup;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.warp.entities.application.HostGroup;
import org.warp.services.dao.admin.HostGroupDAO;

@Import(stylesheet="appsetup.css")
public class ApplicationSetupDetail {
	@Property
	HostGroup hostGroup;
	
	@Property
	Long hostGroupId;
	
	@Inject
	HostGroupDAO hostGroupFinder;
	
	 void setupRender() {
	        
	        // Get person with id 1 - ask business service to find it (from the database).
	        
		 hostGroup = hostGroupFinder.findHostGroup(hostGroupId);


	    }
	 
	 void onActivate(Long hostGroupId) {
	        this.hostGroupId = hostGroupId;
	    }
	    
	    Long onPassivate() {
	        return hostGroupId;
	    }

}
