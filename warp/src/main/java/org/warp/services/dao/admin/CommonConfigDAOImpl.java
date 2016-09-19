package org.warp.services.dao.admin;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.warp.entities.siteadmin.CommonConfig;

public class CommonConfigDAOImpl implements CommonConfigDAO {

	@Inject
	Session session;

	@Inject
	private Logger logger;

	public CommonConfig findCommonConfig(Long commonConfigId) {
		return (CommonConfig) session.get(CommonConfig.class, commonConfigId);
	};
	public List<CommonConfig> findCommonConfigs() {
		return (List<CommonConfig>) session.createCriteria(CommonConfig.class).list();
	};

	
	public List<CommonConfig> findCommonConfigs(int maxResults) {
		return (List<CommonConfig>) session.createCriteria(CommonConfig.class).setMaxResults(maxResults).list();
	};
	
		 
	@CommitAfter
	public void add(CommonConfig commonConfig) {

		CommonConfig newCommonConfig = commonConfig;
		java.util.Date date = new java.util.Date();
		newCommonConfig.createdDt = new Timestamp(date.getTime());
		session.persist(newCommonConfig);

	};

	@CommitAfter
	public void update(CommonConfig commonConfig) {
		CommonConfig newCommonConfig = commonConfig;
		java.util.Date date = new java.util.Date();
		newCommonConfig.lastUpdatedDt = new Timestamp(date.getTime());
		session.persist(newCommonConfig);

	};

	@CommitAfter
	public void delete(CommonConfig commonConfig) {

		session.delete(commonConfig);

	};

}
