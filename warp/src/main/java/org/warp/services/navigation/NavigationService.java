package org.warp.services.navigation;

import org.warp.custom.elements.PageURL;
import java.util.ArrayList;

public interface NavigationService {

	public ArrayList<PageURL> getNavbarPages();
	public ArrayList<PageURL> getPageTabs(String pageName);
}
