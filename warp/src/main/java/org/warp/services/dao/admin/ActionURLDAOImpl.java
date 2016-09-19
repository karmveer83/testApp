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
import org.warp.custom.elements.PageURL;
import org.warp.entities.admin.ActionURL;
import org.warp.entities.siteadmin.CommonConfig;

public class ActionURLDAOImpl implements ActionURLDAO {

	@Inject
	Session session;

	@Inject
	private Logger logger;

	public ActionURL findPage(Long actionURLId) {
		return (ActionURL) session.get(ActionURL.class, actionURLId);
	};

	public long countPages() {

		return (Long) session.createCriteria(ActionURL.class).setProjection(Projections.rowCount()).uniqueResult();
	};

	public List<ActionURL> findPages(int maxResults) {
		return (List<ActionURL>) session.createCriteria(ActionURL.class).setMaxResults(maxResults).list();
	};

	@SuppressWarnings("unchecked")
	public List<ActionURL> findPages(String navBarHeader, int maxResults) {

		List<ActionURL> l = new ArrayList<ActionURL>();
		if (navBarHeader != null) {
			l = session.createCriteria(ActionURL.class).setMaxResults(maxResults)
					.add(Restrictions.eq("urlGroup", navBarHeader)).list();
		} else {
			l = session.createCriteria(ActionURL.class).setMaxResults(maxResults).list();
		}

		return l;

	}

	public List<ActionURL> findPagesByNavBarHeader(String navBarHeader) {
		List<ActionURL> pageResults = session.createCriteria(ActionURL.class)
				.add(Restrictions.eq("urlGroup", navBarHeader)).list();
		return pageResults;
	}

	public ArrayList<PageURL> findNavBarPages() {
		ArrayList<PageURL> l = new ArrayList<PageURL>();
		List<ActionURL> pageResults = session.createCriteria(ActionURL.class)
				.add(Restrictions.eq("urlPlacement", "NAVHEADER")).addOrder(Order.asc("urlPlacementOrder")).list();
		for (ActionURL pageResultItem : pageResults) {
			l.add(new PageURL(pageResultItem.actionURLPageTitle, pageResultItem.actionURLPageName));
		}
		return l;
	}
	public ArrayList<PageURL> findTabs(String urlGroup) {
		ArrayList<PageURL> l = new ArrayList<PageURL>();
		List<ActionURL> pageResults = session.createCriteria(ActionURL.class).add(Restrictions.eq("urlGroup", urlGroup))
				.add(Restrictions.eq("urlPlacement", "TAB")).addOrder(Order.asc("urlPlacementOrder")).list();
		for (ActionURL pageResultItem : pageResults) {
			l.add(new PageURL(pageResultItem.actionURLPageTitle, pageResultItem.actionURLPageName));
		}
		return l;
	}
	public ActionURL findPageByPageName(String actionURLPageName) {
		ActionURL pageResult = (ActionURL) session.createCriteria(ActionURL.class)
				.add(Restrictions.eq("actionURLPageName", actionURLPageName)).uniqueResult();
		return pageResult;
	}

	public ArrayList<String> findAllNavHeaders() {

		List<CommonConfig> result = session.createCriteria(CommonConfig.class)
				.add(Restrictions.eq("configType", "SITE_NAVBAR_HEADER")).list();
		ArrayList<String> navHeaderList = new ArrayList<String>();
		for (CommonConfig resultItem : result) {
			navHeaderList.add(resultItem.configValue);
		}
		return navHeaderList;
	};

	@CommitAfter
	public void add(ActionURL newActionURL) {

		ActionURL newPage = newActionURL;
		java.util.Date date = new java.util.Date();
		newPage.createdDt = new Timestamp(date.getTime());
		session.persist(newPage);

	};

	@CommitAfter
	public void update(ActionURL actionURL) {
		ActionURL newPage = actionURL;
		java.util.Date date = new java.util.Date();
		newPage.lastUpdatedDt = new Timestamp(date.getTime());
		session.persist(newPage);

	};

	@CommitAfter
	public void delete(ActionURL actionURL) {

		session.delete(actionURL);

	};

}
