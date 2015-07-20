package com.metasoft.claim.dao.claim;

import java.util.Date;
import java.util.List;

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
	
	public ReportPaging searchPaging(Date jobDateStart,Date jobDateEnd,int agent,
			ClaimType claimType);
	
	public ReportPaging searchPaging(Date jobDateStart,Date jobDateEnd,int agent,
			ClaimType claimType,int start,int length);
	
	public List<TblClaimRecovery> searchExport(Date jobDateStart,Date jobDateEnd,int agent,
			ClaimType claimType);
	
	
	public List<TblClaimRecovery> searchExportTracking(Date jobDateStart,Date jobDateEnd,StdInsurance partyInsurance,
			ClaimType claimType,String pageName);
	   
}