package com.metasoft.claim.dao;

import java.util.List;

import com.metasoft.claim.model.TblUserInsurance;

public interface UserInsuranceDao extends AbstractDao<TblUserInsurance, Integer>{
	public List<TblUserInsurance> searchByInsuranceId(int insuranceId);
}
