package com.metasoft.claim.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.metasoft.claim.dao.AbstractDaoImpl;
import com.metasoft.claim.dao.StdInsuranceDao;
import com.metasoft.claim.model.StdInsurance;

@Repository("stdInsuranceDao")
@Transactional
public class StdInsuranceDaoImpl extends AbstractDaoImpl<StdInsurance, Integer> implements StdInsuranceDao {
	public StdInsuranceDaoImpl() {
		super(StdInsurance.class);
	}
}
