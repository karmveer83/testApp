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
import org.warp.entities.application.Host;
import org.warp.entities.siteadmin.CommonConfig;

public class HostDAOImpl implements HostDAO {

	@Inject
	Session session;

	@Inject
	private Logger logger;

	public Host findHost(Long hostId) {
		return (Host) session.get(Host.class, hostId);
	};
	public List<Host> findHosts() {
		return (List<Host>) session.createCriteria(Host.class).list();
	};
	public List<String> findHostNames() {
		
		List<String> resultList = new ArrayList<String>();
		for(Host hostItem : (List<Host>) session.createCriteria(Host.class).list()){
			resultList.add(hostItem.hostName);
		}
		return resultList;
	};
	
	public List<Host> findHosts(int maxResults) {
		return (List<Host>) session.createCriteria(Host.class).setMaxResults(maxResults).list();
	};
		
	public List<Host> findHostsByHostGroup(String hostGroupName){
		if(hostGroupName!=null){
			 return (List<Host>) session.createCriteria(Host.class).add(Restrictions.eq("hostGroupName",hostGroupName)).list();
			} else{
				return findHosts();
			}
		};
	 
	public List<Host> findHostsByHostGroup(String hostGroupName, int maxResults){
		
		if(hostGroupName!=null){
		 return (List<Host>) session.createCriteria(Host.class).add(Restrictions.eq("hostGroupName",hostGroupName)).setMaxResults(maxResults).list();
		} else{
			return findHosts(maxResults);
		}
	 };

	@CommitAfter
	public void add(Host host) {

		Host newHost = host;
		java.util.Date date = new java.util.Date();
		newHost.createdDt = new Timestamp(date.getTime());
		session.persist(newHost);

	};

	@CommitAfter
	public void update(Host host) {
		Host newHost = host;
		java.util.Date date = new java.util.Date();
		newHost.lastUpdatedDt = new Timestamp(date.getTime());
		session.persist(newHost);

	};

	@CommitAfter
	public void delete(Host host) {

		session.delete(host);

	};

}
