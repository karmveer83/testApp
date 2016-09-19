package org.warp.pages.appsetup;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.warp.entities.application.Host;
import org.warp.services.dao.admin.HostDAO;

@Import(stylesheet="appsetup.css")
public class HostSetupDetail {
	@Property
	Host host;
	
	@Property
	Long hostId;
	
	@Inject
	HostDAO hostFinder;
	
	 void setupRender() {
	        
	        // Get person with id 1 - ask business service to find it (from the database).
	        
		 host = hostFinder.findHost(hostId);


	    }
	 
	 void onActivate(Long hostId) {
	        this.hostId = hostId;
	    }
	    
	    Long onPassivate() {
	        return hostId;
	    }

}
