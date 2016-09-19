package org.warp.services.dao.admin;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.warp.entities.application.AlertCriteria;


public interface AlertCriteriaDAO {
	
	
	 AlertCriteria findAlertCriteria(Long alertCriteriaId);
	
	 long countAlertCriterias();
	 
	 List<AlertCriteria> findAlertCriterias(int maxResults);
	 
	 List<AlertCriteria>  findAlertCriteriasByApplication(String application);
	 	 
	 List<AlertCriteria> findAlertCriteriasByApplication(String application, int maxResults);
	 
	 List<AlertCriteria> findAlertCriteriasByHostName(String hostNae, int maxResults);
	 
	 AlertCriteria  findAlertCriteriaByAlertCriteriaUID(String alertCriteriaUID);
	 
	  @CommitAfter
	  void add(AlertCriteria newAlertCriteria);
	 
	  @CommitAfter
	  void update(AlertCriteria AlertCriteria);
	 
	  @CommitAfter
	  void delete(AlertCriteria AlertCriteria);

}
