package com.metasoft.claim.dao.impl;

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
import com.metasoft.claim.dao.AbstractDaoImpl;
import com.metasoft.claim.dao.UserInsuranceDao;
import com.metasoft.claim.model.ClaimType;
import com.metasoft.claim.model.JobStatus;
import com.metasoft.claim.model.StdInsurance;
import com.metasoft.claim.model.TblClaimRecovery;
import com.metasoft.claim.model.TblUserInsurance;

@Repository("userInsuranceDao")
@Transactional
public class UserInsuranceDaoImpl extends AbstractDaoImpl<TblUserInsurance, Integer> implements UserInsuranceDao {
	public UserInsuranceDaoImpl() {
		super(TblUserInsurance.class);
	}

	@Override
	public List<TblUserInsurance> searchByInsuranceId(int insuranceId) {
		Criteria criteria = getCurrentSession().createCriteria(entityClass);
		criteria.add(Restrictions.eq("id.insuranceId", insuranceId));
		return criteria.list();
	}
}
