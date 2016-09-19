package org.warp.services.dao.admin;

import java.util.List;

import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.warp.entities.admin.Role;
import org.warp.entities.admin.User;


public interface UserDAO {
	
	
	 User findUser(Long userId);
	
	 List<User> findUsers();

	 List<User> findUsers(int maxResults);
	 
	 boolean authenticateUserCredentials(String loginId, String PasswordKeyedIn);
	 
	 boolean userExists(String loginId);
	 
//	 List<Role> findUserRoles(Long userId);
	 
	 public List<String> findUserNames();
	  @CommitAfter
	  void add(User user);
	 
	  @CommitAfter
	  void update(User user);
	 
	  @CommitAfter
	  void delete(User user);

}
