package org.warp.components;

import org.apache.tapestry5.*;
import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.PasswordField;
import org.apache.tapestry5.corelib.components.TextField;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.annotations.*;
import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.SymbolConstants;

import java.util.ArrayList;
import org.warp.custom.elements.PageURL;
import org.warp.services.navigation.NavigationService;

/**
 * Layout component for pages of application test-project.
 */
@Import(module="bootstrap/collapse")
public class Layout
{
  @Inject
  private ComponentResources resources;
  
  @Inject
  private NavigationService navigate;

  /**
   * The page title, for the <title> element and the <h1> element.
   */
  @Property
  @Parameter(required = true, defaultPrefix = BindingConstants.LITERAL)
  private String title;

  @Property
  private String pageName;
  
  @Property
  private PageURL pageURL;
  
  
  @Property
  private PageURL tabURL;
  
  

  @Property
  @Inject
  @Symbol(SymbolConstants.APPLICATION_VERSION)
  private String appVersion;

  
  public String getClassForPageName()
  {
    return resources.getPageName().startsWith(pageURL.getPageTitle().toLowerCase())
        ? "active"
        : null;
  }
  
  public String getClassForTabName()
  {
    return resources.getPageName().equals(tabURL.getPageURL())
        ? "active"
        : null;
  }

  public ArrayList<PageURL> getPageNames()
  {
    return navigate.getNavbarPages();
  }

  public ArrayList<PageURL> getPageTabs()
  {
    return navigate.getPageTabs(resources.getPageName().toLowerCase());
  }
  public boolean isTabs() {
	  
	    return  getPageTabs().size()>0;
	}


}
