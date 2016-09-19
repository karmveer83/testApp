package org.warp.services.dao.admin;

import java.sql.Timestamp;
import java.util.List;

import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.warp.entities.admin.Role;



public class RoleDAOImpl implements RoleDAO {

	@Inject
	Session session;

	@Inject
	private Logger logger;

	public Role findRole(Long roleId) {
		return (Role) session.get(Role.class, roleId);
	};
	public List<Role> findRoles() {
		return (List<Role>) session.createCriteria(Role.class).list();
	};

	
	public List<Role> findRoles(int maxResults) {
		return (List<Role>) session.createCriteria(Role.class).setMaxResults(maxResults).list();
	};
	
		 
	@CommitAfter
	public void add(Role role) {

		Role newRole = role;
		java.util.Date date = new java.util.Date();
		newRole.createdDt = new Timestamp(date.getTime());
		session.persist(newRole);

	};

	@CommitAfter
	public void update(Role role) {
		Role newRole = role;
		java.util.Date date = new java.util.Date();
		newRole.lastUpdatedDt = new Timestamp(date.getTime());
		session.persist(newRole);

	};

	@CommitAfter
	public void delete(Role role) {

		session.delete(role);

	};

}
