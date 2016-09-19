package org.warp.services.dao.admin;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.warp.entities.application.HostGroup;
import org.warp.entities.siteadmin.CommonConfig;

public class HostGroupDAOImpl implements HostGroupDAO {

	@Inject
	Session session;

	@Inject
	private Logger logger;

	public HostGroup findHostGroup(Long hostGroupId) {
		return (HostGroup) session.get(HostGroup.class, hostGroupId);
	};
	
	public List<HostGroup> findHostGroup(String hostGroupName) {
		return (List<HostGroup>) session.createCriteria(HostGroup.class).add(Restrictions.eq("hostGroupName",hostGroupName)).list();
	};
	
	public List<HostGroup> findHostGroup(String hostGroupName, int maxResults) {
		
		if(hostGroupName!=null){
			return findHostGroup(hostGroupName);
		} else{
		return (List<HostGroup>) session.createCriteria(HostGroup.class).setMaxResults(maxResults).list();
		}
	};
	public List<HostGroup> findHostGroups() {
		return (List<HostGroup>) session.createCriteria(HostGroup.class).list();
	};
	public List<String> findHostGroupNames() {
		
		List<String> resultList = new ArrayList<String>();
		for(HostGroup hostGroupItem : (List<HostGroup>) session.createCriteria(HostGroup.class).list()){
			resultList.add(hostGroupItem.hostGroupName);
		}
		return resultList;
	};
	
	public List<HostGroup> findHostGroups(int maxResults) {
		return (List<HostGroup>) session.createCriteria(HostGroup.class).setMaxResults(maxResults).list();
	};


	@CommitAfter
	public void add(HostGroup hostGroup) {

		HostGroup newHostGroup = hostGroup;
		java.util.Date date = new java.util.Date();
		newHostGroup.createdDt = new Timestamp(date.getTime());
		session.persist(newHostGroup);

	};

	@CommitAfter
	public void update(HostGroup hostGroup) {
		HostGroup newHostGroup = hostGroup;
		java.util.Date date = new java.util.Date();
		newHostGroup.lastUpdatedDt = new Timestamp(date.getTime());
		session.persist(newHostGroup);

	};

	@CommitAfter
	public void delete(HostGroup hostGroup) {

		session.delete(hostGroup);

	};

}
