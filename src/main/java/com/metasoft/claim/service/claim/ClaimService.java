/**
 * 
 */
package com.metasoft.claim.service.claim;

import java.util.Date;
import java.util.List;

import com.metasoft.claim.bean.paging.ClaimSearchResultVoPaging;
import com.metasoft.claim.controller.vo.ClaimSaveVo;
import com.metasoft.claim.controller.vo.ClaimSearchResultVo;
import com.metasoft.claim.dao.claim.ClaimDao;
import com.metasoft.claim.model.ClaimType;
import com.metasoft.claim.model.JobStatus;
import com.metasoft.claim.model.SecUser;
import com.metasoft.claim.model.StdInsurance;
import com.metasoft.claim.model.TblClaimRecovery;
import com.metasoft.claim.service.ModelBasedService;

public interface ClaimService extends ModelBasedService<ClaimDao, TblClaimRecovery, Integer> {
	public ClaimSearchResultVoPaging searchPaging(String paramJobDateStart, String paramJobDateEnd, String paramPartyInsuranceId,
			String paramTotalDayOfMaturity, String paramClaimTypeId, String paramClaimNumber, String paramJobStatusId, int start, int length,SecUser user);
	
	public List<ClaimSearchResultVo> searchExport(String paramJobDateStart, String paramJobDateEnd, String paramPartyInsuranceId,
			String paramTotalDayOfMaturity, String paramClaimTypeId, String paramClaimNumber, String paramJobStatusId);

	public List<ClaimSearchResultVo> searchExport(Date jobDateStart, Date jobDateEnd, StdInsurance partyInsurance, Date maturityDate,
			ClaimType claimType, String claimNumber, JobStatus jobStatus);
	
	public ClaimSearchResultVoPaging searchPaging(Date jobDateStart, Date jobDateEnd, StdInsurance partyInsurance, Date maturityDate,
			ClaimType claimType, String claimNumber, JobStatus jobStatus, int start, int length,SecUser user);
	
	public TblClaimRecovery save(ClaimSaveVo claimSaveVo,SecUser user);
	
	public ClaimSaveVo findById(Integer id,SecUser user);
}
