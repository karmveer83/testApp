package org.warp.services.dao.admin;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;

import org.warp.entities.application.AlertCriteria;
import org.warp.entities.siteadmin.CommonConfig;

public class AlertCriteriaDAOImpl implements AlertCriteriaDAO {

	@Inject
	Session session;

	@Inject
	private Logger logger;

	public AlertCriteria findAlertCriteria(Long alertCriteriaId) {
		return (AlertCriteria) session.get(AlertCriteria.class, alertCriteriaId);
	};

	public long countAlertCriterias() {

		return (Long) session.createCriteria(AlertCriteria.class).setProjection(Projections.rowCount()).uniqueResult();
	};

	public List<AlertCriteria> findAlertCriterias(int maxResults) {
		return (List<AlertCriteria>) session.createCriteria(AlertCriteria.class).setMaxResults(maxResults).list();
	};

	@SuppressWarnings("unchecked")
	public List<AlertCriteria> findAlertCriteriasByApplication(String application, int maxResults) {

		List<AlertCriteria> l = new ArrayList<AlertCriteria>();
		if (application != null) {
			l = session.createCriteria(AlertCriteria.class).setMaxResults(maxResults)
					.add(Restrictions.eq("alertCriteriaHostGroup", application)).list();
		} else {
			l = session.createCriteria(AlertCriteria.class).setMaxResults(maxResults).list();
		}

		return l;

	}

	public List<AlertCriteria> findAlertCriteriasByApplication(String applicationName) {
		List<AlertCriteria> alertCriteriaResults = session.createCriteria(AlertCriteria.class)
				.add(Restrictions.eq("alertCriteriaHostGroup", applicationName)).list();
		return alertCriteriaResults;
	}

	@SuppressWarnings("unchecked")
	public List<AlertCriteria> findAlertCriteriasByHostName(String hostname, int maxResults) {

		List<AlertCriteria> l = new ArrayList<AlertCriteria>();
		if (hostname != null) {
			l = session.createCriteria(AlertCriteria.class).setMaxResults(maxResults)
					.add(Restrictions.eq("alertCriteriaHost", hostname)).list();
		} else {
			l = session.createCriteria(AlertCriteria.class).setMaxResults(maxResults).list();
		}

		return l;

	}

	public List<AlertCriteria> findAlertCriteriasByHostName(String hostname) {
		List<AlertCriteria> alertCriteriaResults = session.createCriteria(AlertCriteria.class)
				.add(Restrictions.eq("alertCriteriaHost", hostname)).list();
		return alertCriteriaResults;
	}

	public AlertCriteria findAlertCriteriaByAlertCriteriaUID(String alertCriteriaUID) {
		AlertCriteria alertCriteriaResult = (AlertCriteria) session.createCriteria(AlertCriteria.class)
				.add(Restrictions.eq("alertCriteriaUID", alertCriteriaUID)).uniqueResult();
		return alertCriteriaResult;
	}



	@CommitAfter
	public void add(AlertCriteria newAlertCriteria) {

		AlertCriteria alertCriteria = newAlertCriteria;
		java.util.Date date = new java.util.Date();
		newAlertCriteria.createdDt = new Timestamp(date.getTime());
		session.persist(alertCriteria);

	};

	@CommitAfter
	public void update(AlertCriteria alertCriteria) {
		AlertCriteria newAlertCriteria = alertCriteria;
		java.util.Date date = new java.util.Date();
		newAlertCriteria.lastUpdatedDt = new Timestamp(date.getTime());
		session.persist(newAlertCriteria);

	};

	@CommitAfter
	public void delete(AlertCriteria alertCriteria) {

		session.delete(alertCriteria);

	};

}
