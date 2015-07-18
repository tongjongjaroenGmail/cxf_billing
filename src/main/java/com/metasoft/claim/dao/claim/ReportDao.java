package com.metasoft.claim.dao.claim;

import java.util.Date;

import com.metasoft.claim.bean.paging.ReportPaging;
import com.metasoft.claim.dao.AbstractDao;
import com.metasoft.claim.model.ClaimType;
import com.metasoft.claim.model.JobStatus;
import com.metasoft.claim.model.SecUser;
import com.metasoft.claim.model.StdInsurance;
import com.metasoft.claim.model.TblClaimRecovery;

public interface ReportDao extends AbstractDao<TblClaimRecovery, Integer>{
	public ReportPaging searchPaging(Date jobDateStart,Date jobDateEnd,StdInsurance partyInsurance,
			ClaimType claimType,int start,int length,String pageName);
	
	   
}