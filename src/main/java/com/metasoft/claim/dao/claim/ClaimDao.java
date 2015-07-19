package com.metasoft.claim.dao.claim;

import java.util.Date;

import com.metasoft.claim.bean.paging.ClaimPaging;
import com.metasoft.claim.dao.AbstractDao;
import com.metasoft.claim.model.ClaimType;
import com.metasoft.claim.model.JobStatus;
import com.metasoft.claim.model.SecUser;
import com.metasoft.claim.model.StdInsurance;
import com.metasoft.claim.model.TblClaimRecovery;

public interface ClaimDao extends AbstractDao<TblClaimRecovery, Integer>{
	public ClaimPaging searchPaging(Date jobDateStart,Date jobDateEnd,StdInsurance partyInsurance,
			Date maturityDate,ClaimType claimType, String claimNumber,JobStatus jobStatus, int start,int length,SecUser user);
	
	public boolean checkDupClaimNumber(String claimNumber);
	   
}