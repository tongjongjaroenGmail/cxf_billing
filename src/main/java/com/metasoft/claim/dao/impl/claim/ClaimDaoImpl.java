package com.metasoft.claim.dao.impl.claim;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.metasoft.claim.bean.paging.ClaimPaging;
import com.metasoft.claim.bean.paging.ClaimSearchResultVoPaging;
import com.metasoft.claim.controller.vo.ClaimSearchResultVo;
import com.metasoft.claim.dao.AbstractDaoImpl;
import com.metasoft.claim.dao.claim.ClaimDao;
import com.metasoft.claim.model.ClaimType;
import com.metasoft.claim.model.JobStatus;
import com.metasoft.claim.model.StdInsurance;
import com.metasoft.claim.model.TblClaimRecovery;

@Repository("claimDao")
@Transactional
public class ClaimDaoImpl extends AbstractDaoImpl<TblClaimRecovery, Integer> implements ClaimDao {
	public ClaimDaoImpl() {
		super(TblClaimRecovery.class);
	}

	@Override
	public ClaimPaging searchPaging(Date jobDateStart,Date jobDateEnd,StdInsurance partyInsurance,
			Date maturityDate,ClaimType claimType, String claimNumber,JobStatus jobStatus, int start,int length){
		ClaimPaging resultPaging = new ClaimPaging();

		Criteria criteriaRecordsTotal = getCurrentSession().createCriteria(entityClass);
		criteriaRecordsTotal.setProjection(Projections.rowCount());
		resultPaging.setRecordsTotal((Long) criteriaRecordsTotal.uniqueResult());

		Criteria criteriaCount = getCurrentSession().createCriteria(entityClass);
		if (jobDateStart != null && jobDateEnd != null) {
			criteriaCount.add(Restrictions.between("jobDate", jobDateStart, jobDateEnd));
		}else if (jobDateStart != null) {
			criteriaCount.add(Restrictions.ge("jobDate", jobDateStart));
		}else if (jobDateEnd != null) {
			criteriaCount.add(Restrictions.le("jobDate", jobDateEnd));
		}
		
		if(partyInsurance != null){
			criteriaCount.add(Restrictions.eq("partyInsurance", partyInsurance));
		}
		
		if(maturityDate != null){
			criteriaCount.add(Restrictions.eq("maturityDate", maturityDate));
		}
		
		if(claimType != null){
			criteriaCount.add(Restrictions.eq("claimType", claimType));
		}
		
		if(StringUtils.isNotBlank(claimNumber)){
			criteriaCount.add(Restrictions.ilike("claimNumber", claimNumber + "%"));
		}
		
		if(jobStatus != null){
			criteriaCount.add(Restrictions.eq("jobStatus", jobStatus));
		}
		criteriaCount.setProjection(Projections.rowCount());
		resultPaging.setRecordsFiltered((Long) criteriaCount.uniqueResult());

		if (resultPaging.getRecordsFiltered() != 0) {
			Criteria criteria = getCurrentSession().createCriteria(entityClass);
			if (jobDateStart != null && jobDateEnd != null) {
				criteria.add(Restrictions.between("jobDate", jobDateStart, jobDateEnd));
			}else if (jobDateStart != null) {
				criteria.add(Restrictions.ge("jobDate", jobDateStart));
			}else if (jobDateEnd != null) {
				criteria.add(Restrictions.le("jobDate", jobDateEnd));
			}
			
			if(partyInsurance != null){
				criteria.add(Restrictions.eq("partyInsurance", partyInsurance));
			}
			
			if(maturityDate != null){
				criteria.add(Restrictions.eq("maturityDate", maturityDate));
			}
			
			if(claimType != null){
				criteria.add(Restrictions.eq("claimType", claimType));
			}
			
			if(StringUtils.isNotBlank(claimNumber)){
				criteria.add(Restrictions.ilike("claimNumber", claimNumber + "%"));
			}
			
			if(jobStatus != null){
				criteria.add(Restrictions.eq("jobStatus", jobStatus));
			}

			criteria.addOrder(Order.asc("maturityDate"));
			criteria.setFirstResult(start);
			criteria.setMaxResults(length);
			resultPaging.setData(criteria.list());
		}
		
		if(resultPaging.getData() == null){
			resultPaging.setData(new ArrayList<TblClaimRecovery>());
		}
		return resultPaging;
	}
}
