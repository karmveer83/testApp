package org.warp.pages.admin;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.warp.entities.admin.User;
import org.warp.services.dao.admin.UserDAO;

@Import(stylesheet="appsetup.css")
public class UserDetail {
	@Property
	User user;
	
	@Property
	Long userId;
	
	@Inject
	UserDAO userFinder;
	
	 void setupRender() {
	        
	        // Get person with id 1 - ask business service to find it (from the database).
	        
		 user = userFinder.findUser(userId);


	    }
	 
	 void onActivate(Long userId) {
	        this.userId = userId;
	    }
	    
	    Long onPassivate() {
	        return userId;
	    }

}
