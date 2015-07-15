package com.metasoft.claim.dao.claim;

import com.metasoft.claim.bean.paging.ClaimSearchResultVoPaging;
import com.metasoft.claim.dao.AbstractDao;
import com.metasoft.claim.model.TblClaimRecovery;

public interface ClaimDao extends AbstractDao<TblClaimRecovery, Integer>{
	public ClaimSearchResultVoPaging searchPaging(String jobDateStart,String jobDateEnd,String insuranceId,
			String totalDayOfMaturity,String claimTypeId, String claimNumber,String jobStatusId, int start,int length);
	   
}