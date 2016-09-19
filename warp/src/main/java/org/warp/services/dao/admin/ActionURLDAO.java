package org.warp.services.dao.admin;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.warp.custom.elements.PageURL;
import org.warp.entities.admin.ActionURL;


public interface ActionURLDAO {
	
	
	 ActionURL findPage(Long actoionURLId);
	
	 long countPages();
	 
	 List<ActionURL> findPages(int maxResults);
	 
	 List<ActionURL>  findPagesByNavBarHeader(String navBarHeader);
	 
	 ArrayList<PageURL> findNavBarPages();
	 
	 public ArrayList<PageURL> findTabs(String urlGroup) ;
	 
	 List<String> findAllNavHeaders();
	 
	 List<ActionURL> findPages(String navBarHeader, int maxResults);
	 
	 ActionURL  findPageByPageName(String actionURLPageName);
	 
	  @CommitAfter
	  void add(ActionURL newActionURL);
	 
	  @CommitAfter
	  void update(ActionURL ActionURL);
	 
	  @CommitAfter
	  void delete(ActionURL ActionURL);

}
