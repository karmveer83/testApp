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
import org.warp.entities.application.MaintenanceWindow;
import org.warp.entities.siteadmin.CommonConfig;

public class MaintenanceWindowDAOImpl implements MaintenanceWindowDAO {

	@Inject
	Session session;

	@Inject
	private Logger logger;

	public MaintenanceWindow findMaintenanceWindow(Long maintenanceWindowId) {
		return (MaintenanceWindow) session.get(MaintenanceWindow.class, maintenanceWindowId);
	};
	public List<MaintenanceWindow> findMaintenanceWindows() {
		return (List<MaintenanceWindow>) session.createCriteria(MaintenanceWindow.class).list();
	};
	
	public List<MaintenanceWindow> findActiveMaintenanceWindows() {
		 java.util.Date date= new java.util.Date();
		return (List<MaintenanceWindow>) session.createCriteria(MaintenanceWindow.class).add(Restrictions.gt("endDt", new Timestamp(date.getTime()))).list();
	};
	
	public List<MaintenanceWindow> findMaintenanceWindows(String hostGroupName,int maxResults) {
		List<MaintenanceWindow> resultList = new ArrayList<MaintenanceWindow>();
		if(hostGroupName!=null){ 
			resultList= session.createCriteria(MaintenanceWindow.class).add(Restrictions.eq("hostGroupName",hostGroupName)).setMaxResults(maxResults).list();
		} else{
			resultList= findMaintenanceWindows( maxResults);
		}
		return resultList;
	};
	
	public List<MaintenanceWindow> findActiveMaintenanceWindows(String hostGroupName,int maxResults) {
		List<MaintenanceWindow> resultList = new ArrayList<MaintenanceWindow>();
		if(hostGroupName!=null){ 
			 java.util.Date date= new java.util.Date();
			resultList= session.createCriteria(MaintenanceWindow.class).add(Restrictions.eq("hostGroupName",hostGroupName)).add(Restrictions.gt("endDt", new Timestamp(date.getTime()))).setMaxResults(maxResults).list();
		} else{
			resultList= findActiveMaintenanceWindows( maxResults);
		}
		return resultList;
	};
	public List<MaintenanceWindow> findMaintenanceWindows(int maxResults) {
		return (List<MaintenanceWindow>) session.createCriteria(MaintenanceWindow.class).setMaxResults(maxResults).list();
	};
	
	public List<MaintenanceWindow> findActiveMaintenanceWindows(int maxResults) {
		java.util.Date date= new java.util.Date();
		return (List<MaintenanceWindow>) session.createCriteria(MaintenanceWindow.class).add(Restrictions.gt("endDt", new Timestamp(date.getTime()))).setMaxResults(maxResults).list();
	};



	@CommitAfter
	public void add(MaintenanceWindow maintenanceWindow) {

		MaintenanceWindow newMaintenanceWindow = maintenanceWindow;
		java.util.Date date = new java.util.Date();
		newMaintenanceWindow.createdDt = new Timestamp(date.getTime());
		session.persist(newMaintenanceWindow);

	};

	@CommitAfter
	public void update(MaintenanceWindow maintenanceWindow) {
		MaintenanceWindow newMaintenanceWindow = maintenanceWindow;
		java.util.Date date = new java.util.Date();
		newMaintenanceWindow.lastUpdatedDt = new Timestamp(date.getTime());
		session.persist(newMaintenanceWindow);

	};

	@CommitAfter
	public void delete(MaintenanceWindow maintenanceWindow) {

		session.delete(maintenanceWindow);

	};

}
