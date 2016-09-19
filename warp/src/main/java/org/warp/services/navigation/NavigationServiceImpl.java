package org.warp.services.navigation;
import java.util.ArrayList;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.warp.custom.elements.PageURL;
import org.warp.services.dao.admin.ActionURLDAO;

public class NavigationServiceImpl implements NavigationService{
	
	@Inject
	private ActionURLDAO pageFinder;
	

	
	public ArrayList<PageURL> getNavbarPages(){
		
		 return pageFinder.findNavBarPages();  
	}
	public ArrayList<PageURL> getPageTabs(String pageName){
		ArrayList<PageURL> pageTabList = new ArrayList<PageURL>();
		if(pageName.startsWith("application")){
			return pageFinder.findTabs("Monitor");
		} else if (pageName.startsWith("appsetup")){
			return pageFinder.findTabs("Setup");
		} else if (pageName.startsWith("admin")){
			return pageFinder.findTabs("Admin");
		}
		 return pageTabList;  
	}
}
