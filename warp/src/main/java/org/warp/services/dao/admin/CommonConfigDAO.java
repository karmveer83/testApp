package org.warp.services.dao.admin;

import java.util.List;

import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.warp.entities.siteadmin.CommonConfig;



public interface CommonConfigDAO {
	
	
	 CommonConfig findCommonConfig(Long commonConfigId);
	
	 List<CommonConfig> findCommonConfigs();

	 List<CommonConfig> findCommonConfigs(int maxResults);
	 	 

	  @CommitAfter
	  void add(CommonConfig commonConfig);
	 
	  @CommitAfter
	  void update(CommonConfig commonConfig);
	 
	  @CommitAfter
	  void delete(CommonConfig commonConfig);

}
