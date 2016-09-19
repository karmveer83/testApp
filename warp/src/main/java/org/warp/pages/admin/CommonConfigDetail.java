package org.warp.pages.admin;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.warp.entities.siteadmin.CommonConfig;
import org.warp.services.dao.admin.CommonConfigDAO;

@Import(stylesheet="appsetup.css")
public class CommonConfigDetail {
	@Property
	CommonConfig commonConfig;
	
	@Property
	Long commonConfigId;
	
	@Inject
	CommonConfigDAO commonConfigFinder;
	
	 void setupRender() {
	        
	        // Get person with id 1 - ask business service to find it (from the database).
	        
		 commonConfig = commonConfigFinder.findCommonConfig(commonConfigId);


	    }
	 
	 void onActivate(Long commonConfigId) {
	        this.commonConfigId = commonConfigId;
	    }
	    
	    Long onPassivate() {
	        return commonConfigId;
	    }

}
