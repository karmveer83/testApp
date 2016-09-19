package org.warp.services.dao.admin;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.warp.entities.admin.ActionURL;
import org.warp.entities.application.MaintenanceWindow;


public interface MaintenanceWindowDAO {
	
	
	 MaintenanceWindow findMaintenanceWindow(Long maintenanceWindowId);
	
	 List<MaintenanceWindow> findMaintenanceWindows();

	 List<MaintenanceWindow> findMaintenanceWindows(int maxResults);
	 
	 List<MaintenanceWindow> findMaintenanceWindows(String hostGroupName,int maxResults);
	 
	 List<MaintenanceWindow> findActiveMaintenanceWindows();

	 List<MaintenanceWindow> findActiveMaintenanceWindows(int maxResults);
	 
	 List<MaintenanceWindow> findActiveMaintenanceWindows(String hostGroupName,int maxResults);
	 

	  @CommitAfter
	  void add(MaintenanceWindow maintenanceWindow);
	 
	  @CommitAfter
	  void update(MaintenanceWindow maintenanceWindow);
	 
	  @CommitAfter
	  void delete(MaintenanceWindow maintenanceWindow);

}
