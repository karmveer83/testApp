package org.warp.services.dao.admin;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.warp.entities.admin.ActionURL;
import org.warp.entities.application.Host;


public interface HostDAO {
	
	
	 Host findHost(Long hostId);
	
	 List<Host> findHosts();

	 List<Host> findHosts(int maxResults);
	 
	 public List<String> findHostNames();
	 
	 List<Host> findHostsByHostGroup(String hostGroupName);
	 
	 List<Host> findHostsByHostGroup(String hostGroupName, int maxResults);
	 

	  @CommitAfter
	  void add(Host host);
	 
	  @CommitAfter
	  void update(Host host);
	 
	  @CommitAfter
	  void delete(Host host);

}
