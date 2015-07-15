package com.metasoft.claim.dao.impl.standard;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.metasoft.claim.dao.AbstractDaoImpl;
import com.metasoft.claim.dao.standard.InsuranceDao;
import com.metasoft.claim.model.StdInsurance;

@Repository("insuranceDao")
@Transactional
public class InsuranceDaoImpl extends AbstractDaoImpl<StdInsurance, Integer> implements InsuranceDao {
	public InsuranceDaoImpl() {
		super(StdInsurance.class);
	}
}
