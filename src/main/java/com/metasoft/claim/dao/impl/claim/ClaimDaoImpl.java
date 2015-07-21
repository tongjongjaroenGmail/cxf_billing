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
import com.metasoft.claim.dao.AbstractDaoImpl;
import com.metasoft.claim.dao.claim.ClaimDao;
import com.metasoft.claim.dao.security.UserDao;
import com.metasoft.claim.model.ClaimType;
import com.metasoft.claim.model.JobStatus;
import com.metasoft.claim.model.SecUser;
import com.metasoft.claim.model.StdInsurance;
import com.metasoft.claim.model.TblClaimRecovery;

@Repository("claimDao")
@Transactional
public class ClaimDaoImpl extends AbstractDaoImpl<TblClaimRecovery, Integer> implements ClaimDao {
	@Autowired
	private UserDao userDao;

	public ClaimDaoImpl() {
		super(TblClaimRecovery.class);
	}

	@Override
	public List<TblClaimRecovery> searchExport(Date jobDateStart, Date jobDateEnd, StdInsurance partyInsurance, Date maturityDate,
			ClaimType claimType, String claimNumber, JobStatus jobStatus) {
		List<TblClaimRecovery> results = new ArrayList<TblClaimRecovery>();

		Criteria criteria = getCurrentSession().createCriteria(entityClass);
		if (jobDateStart != null && jobDateEnd != null) {
			criteria.add(Restrictions.between("jobDate", jobDateStart, jobDateEnd));
		} else if (jobDateStart != null) {
			criteria.add(Restrictions.ge("jobDate", jobDateStart));
		} else if (jobDateEnd != null) {
			criteria.add(Restrictions.le("jobDate", jobDateEnd));
		}

		if (partyInsurance != null) {
			criteria.add(Restrictions.eq("partyInsurance", partyInsurance));
		}

		if (maturityDate != null) {
			criteria.add(Restrictions.le("maturityDate", maturityDate));
		}

		if (claimType != null) {
			criteria.add(Restrictions.eq("claimType", claimType));
		}

		if (StringUtils.isNotBlank(claimNumber)) {
			criteria.add(Restrictions.ilike("claimNumber", claimNumber + "%"));
		}

		if (jobStatus != null) {
			criteria.add(Restrictions.eq("jobStatus", jobStatus));
		}

		criteria.addOrder(Order.asc("maturityDate"));

		results = criteria.list();
		if (results == null) {
			results = new ArrayList<TblClaimRecovery>();
		}
		return results;
	}

	@Override
	public ClaimPaging searchPaging(Date jobDateStart, Date jobDateEnd, StdInsurance partyInsurance, Date maturityDate,
			ClaimType claimType, String claimNumber, JobStatus jobStatus, int start, int length, SecUser user) {
		ClaimPaging resultPaging = new ClaimPaging();

		SecUser agent = null;
		if (user != null) {
			agent = userDao.findById(user.getId());
		}

		Criteria criteriaRecordsTotal = getCurrentSession().createCriteria(entityClass);
		if (agent != null && agent.getStdPosition().getId() == 2) {
			criteriaRecordsTotal.add(Restrictions.eq("agent", agent));
		}

		criteriaRecordsTotal.setProjection(Projections.rowCount());
		resultPaging.setRecordsTotal((Long) criteriaRecordsTotal.uniqueResult());

		Criteria criteriaCount = getCurrentSession().createCriteria(entityClass);
		if (jobDateStart != null && jobDateEnd != null) {
			criteriaCount.add(Restrictions.between("jobDate", jobDateStart, jobDateEnd));
		} else if (jobDateStart != null) {
			criteriaCount.add(Restrictions.ge("jobDate", jobDateStart));
		} else if (jobDateEnd != null) {
			criteriaCount.add(Restrictions.le("jobDate", jobDateEnd));
		}

		if (partyInsurance != null) {
			criteriaCount.add(Restrictions.eq("partyInsurance", partyInsurance));
		}

		if (maturityDate != null) {
			criteriaCount.add(Restrictions.le("maturityDate", maturityDate));
		}

		if (claimType != null) {
			criteriaCount.add(Restrictions.eq("claimType", claimType));
		}

		if (StringUtils.isNotBlank(claimNumber)) {
			criteriaCount.add(Restrictions.ilike("claimNumber", claimNumber + "%"));
		}

		if (jobStatus != null) {
			criteriaCount.add(Restrictions.eq("jobStatus", jobStatus));
		}
		if (agent != null && agent.getStdPosition().getId() == 2) {
			criteriaCount.add(Restrictions.eq("agent", agent));
		}
		criteriaCount.setProjection(Projections.rowCount());
		resultPaging.setRecordsFiltered((Long) criteriaCount.uniqueResult());

		if (resultPaging.getRecordsFiltered() != 0) {
			Criteria criteria = getCurrentSession().createCriteria(entityClass);
			if (jobDateStart != null && jobDateEnd != null) {
				criteria.add(Restrictions.between("jobDate", jobDateStart, jobDateEnd));
			} else if (jobDateStart != null) {
				criteria.add(Restrictions.ge("jobDate", jobDateStart));
			} else if (jobDateEnd != null) {
				criteria.add(Restrictions.le("jobDate", jobDateEnd));
			}

			if (partyInsurance != null) {
				criteria.add(Restrictions.eq("partyInsurance", partyInsurance));
			}

			if (maturityDate != null) {
				criteria.add(Restrictions.le("maturityDate", maturityDate));
			}

			if (claimType != null) {
				criteria.add(Restrictions.eq("claimType", claimType));
			}

			if (StringUtils.isNotBlank(claimNumber)) {
				criteria.add(Restrictions.ilike("claimNumber", claimNumber + "%"));
			}

			if (jobStatus != null) {
				criteria.add(Restrictions.eq("jobStatus", jobStatus));
			}
			if (agent != null && agent.getStdPosition().getId() == 2) {
				criteria.add(Restrictions.eq("agent", agent));
			}

			criteria.addOrder(Order.asc("maturityDate"));
			criteria.setFirstResult(start);
			criteria.setMaxResults(length);
			resultPaging.setData(criteria.list());
		}

		if (resultPaging.getData() == null) {
			resultPaging.setData(new ArrayList<TblClaimRecovery>());
		}
		return resultPaging;
	}

	@Override
	public boolean checkDupClaimNumber(String claimNumber) {
		Criteria criteriaCount = getCurrentSession().createCriteria(entityClass);

		if (StringUtils.isNotBlank(claimNumber)) {
			criteriaCount.add(Restrictions.eq("claimNumber", claimNumber));
		}
		criteriaCount.setProjection(Projections.rowCount());

		return ((Long) criteriaCount.uniqueResult() > 0) ? true : false;
	}

	@Override
	public ClaimPaging searchBillingPaging(Date closeDateStart, Date closeDateEnd, StdInsurance partyInsurance, ClaimType claimType,
			int start, int length) {
		ClaimPaging resultPaging = new ClaimPaging();

		Criteria criteriaRecordsTotal = getCurrentSession().createCriteria(entityClass);

		criteriaRecordsTotal.add(Restrictions.ge("jobStatus", JobStatus.CLOSED));
		
		criteriaRecordsTotal.setProjection(Projections.rowCount());
		resultPaging.setRecordsTotal((Long) criteriaRecordsTotal.uniqueResult());

		Criteria criteriaCount = getCurrentSession().createCriteria(entityClass);
		criteriaCount.add(Restrictions.ge("jobStatus", JobStatus.CLOSED));
		
		if (closeDateStart != null && closeDateEnd != null) {
			criteriaCount.add(Restrictions.between("closeDate", closeDateStart, closeDateEnd));
		} else if (closeDateStart != null) {
			criteriaCount.add(Restrictions.ge("closeDate", closeDateStart));
		} else if (closeDateEnd != null) {
			criteriaCount.add(Restrictions.le("closeDate", closeDateEnd));
		}

		if (partyInsurance != null) {
			criteriaCount.add(Restrictions.eq("partyInsurance", partyInsurance));
		}

		if (claimType != null) {
			criteriaCount.add(Restrictions.eq("claimType", claimType));
		}

		criteriaCount.setProjection(Projections.rowCount());
		resultPaging.setRecordsFiltered((Long) criteriaCount.uniqueResult());

		if (resultPaging.getRecordsFiltered() != 0) {
			Criteria criteria = getCurrentSession().createCriteria(entityClass);
			criteria.add(Restrictions.ge("jobStatus", JobStatus.CLOSED));
			if (closeDateStart != null && closeDateEnd != null) {
				criteria.add(Restrictions.between("closeDate", closeDateStart, closeDateEnd));
			} else if (closeDateStart != null) {
				criteria.add(Restrictions.ge("closeDate", closeDateStart));
			} else if (closeDateEnd != null) {
				criteria.add(Restrictions.le("closeDate", closeDateEnd));
			}

			if (partyInsurance != null) {
				criteria.add(Restrictions.eq("partyInsurance", partyInsurance));
			}

			if (claimType != null) {
				criteria.add(Restrictions.eq("claimType", claimType));
			}

			criteria.addOrder(Order.asc("claimNumber"));
			criteria.setFirstResult(start);
			criteria.setMaxResults(length);
			resultPaging.setData(criteria.list());
		}

		if (resultPaging.getData() == null) {
			resultPaging.setData(new ArrayList<TblClaimRecovery>());
		}
		return resultPaging;
	}
}
