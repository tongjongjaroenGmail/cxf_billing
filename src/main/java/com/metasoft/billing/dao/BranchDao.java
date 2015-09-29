package com.metasoft.billing.dao;

import com.metasoft.billing.model.Branch;

public interface BranchDao extends AbstractDao<Branch, Integer>{

	public Long countByAmhurId(int amphurId);
	
	public Long countByProvinceId(int provinceId);
	
	public Branch findByName(String name);
}
