package org.warp.pages.admin;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.warp.entities.admin.Role;
import org.warp.services.dao.admin.RoleDAO;

@Import(stylesheet="appsetup.css")
public class RoleDetail {
	@Property
	Role role;
	
	@Property
	Long roleId;
	
	@Inject
	RoleDAO roleFinder;
	
	 void setupRender() {
	        
	        // Get person with id 1 - ask business service to find it (from the database).
	        
		 role = roleFinder.findRole(roleId);


	    }
	 
	 void onActivate(Long roleId) {
	        this.roleId = roleId;
	    }
	    
	    Long onPassivate() {
	        return roleId;
	    }

}
