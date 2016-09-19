package org.warp.services.dao.admin;

import java.util.List;

import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.warp.entities.admin.Role;



public interface RoleDAO {
	
	
	 Role findRole(Long roleId);
	
	 List<Role> findRoles();

	 List<Role> findRoles(int maxResults);
	 	 

	  @CommitAfter
	  void add(Role role);
	 
	  @CommitAfter
	  void update(Role role);
	 
	  @CommitAfter
	  void delete(Role role);

}
