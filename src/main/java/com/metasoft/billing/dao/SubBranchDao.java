package com.metasoft.billing.dao;

import com.metasoft.billing.model.SubBranch;

public interface SubBranchDao extends AbstractDao<SubBranch, Integer>{
	
	public SubBranch findByBranchIdAndAmphurId(int branchId,int amphurId);
}
