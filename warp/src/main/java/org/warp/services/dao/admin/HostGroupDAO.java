package org.warp.services.dao.admin;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.warp.entities.admin.ActionURL;
import org.warp.entities.application.HostGroup;


public interface HostGroupDAO {
	
	
	 HostGroup findHostGroup(Long hostGroupId);
	 
	 List<HostGroup>  findHostGroup(String hostGroupName);
	 
	 public List<HostGroup> findHostGroup(String hostGroupName, int maxResults);
	
	 List<HostGroup> findHostGroups();

	 List<HostGroup> findHostGroups(int maxResults);
	 
	 public List<String> findHostGroupNames();
	  @CommitAfter
	  void add(HostGroup hostGroup);
	 
	  @CommitAfter
	  void update(HostGroup hostGroup);
	 
	  @CommitAfter
	  void delete(HostGroup hostGroup);

}
