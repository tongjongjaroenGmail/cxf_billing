package com.metasoft.claim.dao.standard;

import java.util.List;

import com.metasoft.claim.dao.AbstractDao;
import com.metasoft.claim.model.StdInsurance;

public interface InsuranceDao extends AbstractDao<StdInsurance, Integer>{
	public List<StdInsurance> findAllOrder();
	public StdInsurance findByName(String name);
}
