package org.warp.services.dao.admin;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.warp.entities.admin.User;
import org.warp.services.navigation.CryptoService;

public class UserDAOImpl implements UserDAO {

	@Inject
	Session session;

	@Inject
	private Logger logger;
	
	@Inject
	CryptoService crypto;

	public User findUser(Long userId) {
		return (User) session.get(User.class, userId);
	};
	public List<User> findUsers() {
		return (List<User>) session.createCriteria(User.class).list();
	};
	public List<String> findUserNames() {
		
		List<String> resultList = new ArrayList<String>();
		for(User userItem : (List<User>) session.createCriteria(User.class).list()){
			resultList.add(userItem.userName);
		}
		return resultList;
	};
	
	public List<User> findUsers(int maxResults) {
		return (List<User>) session.createCriteria(User.class).setMaxResults(maxResults).list();
	};
	
	
	 
	public boolean authenticateUserCredentials(String loginId, String PasswordKeyedIn){
		boolean authenticationResult = false;
		User searchUser = (User)session.createCriteria(User.class).add(Restrictions.eq("loginId",loginId)).uniqueResult();
		if(searchUser!=null && crypto.checkPassword(PasswordKeyedIn, searchUser.userPassword)){
			authenticationResult = true;
		}
			return authenticationResult;
	}
	 
	public boolean userExists(String loginId){
		boolean authenticationResult = false;
		User searchUser = (User)session.createCriteria(User.class).add(Restrictions.eq("loginId",loginId)).uniqueResult();
		if(searchUser!=null){
			authenticationResult = true;
		}
			return authenticationResult;
	}
	
//	 public List<Role> findUserRoles(Long userId){
//		return findUser(userId).userRoles;		 
//	 }

	@CommitAfter
	public void add(User user) {

		User newUser = user;
		java.util.Date date = new java.util.Date();
		newUser.createdDt = new Timestamp(date.getTime());
		newUser.userPassword = crypto.encrpytPassword(user.userPassword);
		session.persist(newUser);

	};

	@CommitAfter
	public void update(User user) {
		User newUser = user;
		java.util.Date date = new java.util.Date();
		newUser.lastUpdatedDt = new Timestamp(date.getTime());
		session.persist(newUser);

	};

	@CommitAfter
	public void delete(User user) {

		session.delete(user);

	};

}
