package com.metasoft.claim.dao.impl.claim;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.metasoft.claim.bean.paging.ClaimPaging;
import com.metasoft.claim.bean.paging.ClaimSearchResultVoPaging;
import com.metasoft.claim.bean.paging.ReportPaging;
import com.metasoft.claim.controller.vo.ClaimSearchResultVo;
import com.metasoft.claim.dao.AbstractDaoImpl;
import com.metasoft.claim.dao.claim.ClaimDao;
import com.metasoft.claim.dao.claim.ReportDao;
import com.metasoft.claim.dao.security.UserDao;
import com.metasoft.claim.model.ClaimType;
import com.metasoft.claim.model.JobStatus;
import com.metasoft.claim.model.SecUser;
import com.metasoft.claim.model.StdInsurance;
import com.metasoft.claim.model.TblClaimRecovery;

@Repository("reportDao")
@Transactional
public class ReportDaoImpl extends AbstractDaoImpl<TblClaimRecovery, Integer>
		implements ReportDao {
	@Autowired
	private UserDao userDao;

	public ReportDaoImpl() {
		super(TblClaimRecovery.class);
	}

	@Override
	public ReportPaging searchPaging(Date jobDateStart, Date jobDateEnd,
			StdInsurance partyInsurance, ClaimType claimType, int start,
			int length, String pageName) {
		System.out.println(">>>>>> pageName = " + pageName);

		ReportPaging resultPaging = new ReportPaging();
		try {
			Criteria criteriaRecordsTotal = getCurrentSession().createCriteria(
					entityClass);

			criteriaRecordsTotal.setProjection(Projections.rowCount());
			resultPaging.setRecordsTotal((Long) criteriaRecordsTotal
					.uniqueResult());

			Criteria criteriaCount = getCurrentSession().createCriteria(
					entityClass);

			if (partyInsurance != null) {
				criteriaCount.add(Restrictions.eq("partyInsurance",
						partyInsurance));
			}

			if (claimType != null) {
				criteriaCount.add(Restrictions.eq("claimType", claimType));
			}

			if (pageName.equals("tracking")) {
				if (jobDateStart != null && jobDateEnd != null) {
					criteriaCount.add(Restrictions.between("followDate",
							jobDateStart, jobDateEnd));
				} else if (jobDateStart != null) {
					criteriaCount.add(Restrictions.ge("followDate",
							jobDateStart));
				} else if (jobDateEnd != null) {
					criteriaCount
							.add(Restrictions.le("followDate", jobDateEnd));
				}
			}
			if (pageName.equals("billing")) {
				System.out.println(">>>>>> billing");
				if (jobDateStart != null && jobDateEnd != null) {
					criteriaCount.add(Restrictions.between("closeDate",
							jobDateStart, jobDateEnd));
				} else if (jobDateStart != null) {
					criteriaCount.add(Restrictions
							.ge("closeDate", jobDateStart));
				} else if (jobDateEnd != null) {
					criteriaCount.add(Restrictions.le("closeDate", jobDateEnd));
				}
				//criteriaCount.add(Restrictions.eq("jobStatus", "2"));
			}

			criteriaCount.setProjection(Projections.rowCount());
			resultPaging
					.setRecordsFiltered((Long) criteriaCount.uniqueResult());

			if (resultPaging.getRecordsFiltered() != 0) {
				Criteria criteria = getCurrentSession().createCriteria(
						entityClass);
				if (partyInsurance != null) {
					criteria.add(Restrictions.eq("partyInsurance",
							partyInsurance));
				}

				if (claimType != null) {
					criteria.add(Restrictions.eq("claimType", claimType));
				}

				if (pageName.equals("tracking")) {
					if (jobDateStart != null && jobDateEnd != null) {
						criteria.add(Restrictions.between("followDate",
								jobDateStart, jobDateEnd));
					} else if (jobDateStart != null) {
						criteria.add(Restrictions
								.ge("followDate", jobDateStart));
					} else if (jobDateEnd != null) {
						criteria.add(Restrictions.le("followDate", jobDateEnd));
					}
				}
				if (pageName.equals("billing")) {
					if (jobDateStart != null && jobDateEnd != null) {
						criteria.add(Restrictions.between("closeDate",
								jobDateStart, jobDateEnd));
					} else if (jobDateStart != null) {
						criteria.add(Restrictions.ge("closeDate", jobDateStart));
					} else if (jobDateEnd != null) {
						criteria.add(Restrictions.le("closeDate", jobDateEnd));
					}
				//	criteria.add(Restrictions.eq("jobStatus", "2"));
				}

				criteria.setFirstResult(start);
				criteria.setMaxResults(length);
				resultPaging.setData(criteria.list());
			}

			if (resultPaging.getData() == null) {
				resultPaging.setData(new ArrayList<TblClaimRecovery>());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultPaging;
	}
}
