package org.warp.pages;

import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.PasswordField;
import org.apache.tapestry5.corelib.components.TextField;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.slf4j.Logger;
import org.warp.services.dao.admin.UserDAO;

public class Index
{
  @Inject
  private Logger logger;

  @Inject
  private AlertManager alertManager;
  
  @Inject
  private UserDAO userService;

  @InjectComponent
  private Form login;
  
  @InjectComponent("loginId")
  private TextField loginIdField;
  
  @InjectComponent("PasswordKeyedIn")
  private PasswordField passwordField;

  @Property
  private String loginId;

  @Property
  private String PasswordKeyedIn;



  void onValidateFromLogin()
  {
	  
	  if(!userService.authenticateUserCredentials(loginId, PasswordKeyedIn)){
    if ( !userService.userExists(loginId)){
      login.recordError(loginIdField, "User Not Found");
    } else {
      login.recordError(passwordField, "Incorrect Password. Please try again");
	  }
    }
  }

  Object onSuccessFromLogin()
  {
    logger.info("Login successful!");
//    alertManager.success("Welcome aboard!");

    return Home.class;
  }

  void onFailureFromLogin()
  {
    logger.warn("Login error!");
//    alertManager.error("I'm sorry but I can't log you in!");
  }

}
