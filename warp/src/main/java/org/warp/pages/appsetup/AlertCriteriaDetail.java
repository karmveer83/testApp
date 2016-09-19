package org.warp.pages.appsetup;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.warp.entities.application.AlertCriteria;
import org.warp.services.dao.admin.AlertCriteriaDAO;

@Import(stylesheet="appsetup.css")
public class AlertCriteriaDetail {
	@Property
	AlertCriteria alertCriteria;
	
	@Property
	Long alertCriteriaId;
	
	@Inject
	AlertCriteriaDAO alertCriteriaFinder;
	
	 void setupRender() {
	        
	        // Get person with id 1 - ask business service to find it (from the database).
	        
		 alertCriteria = alertCriteriaFinder.findAlertCriteria(alertCriteriaId);


	    }
	 
	 void onActivate(Long alertCriteriaId) {
	        this.alertCriteriaId = alertCriteriaId;
	    }
	    
	    Long onPassivate() {
	        return alertCriteriaId;
	    }

}
