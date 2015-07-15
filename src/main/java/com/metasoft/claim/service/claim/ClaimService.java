/**
 * 
 */
package com.metasoft.claim.service.claim;

import java.util.List;

import com.metasoft.claim.dao.claim.ClaimDao;
import com.metasoft.claim.model.TblClaimRecovery;
import com.metasoft.claim.service.ModelBasedService;


public interface ClaimService extends ModelBasedService<ClaimDao, TblClaimRecovery, Integer>
{
	public List<TblClaimRecovery> searchPaging(String jobDateStart,String jobDateEnd,String insuranceId,
			String totalDayOfMaturity,String claimTypeId, String claimNumber,String jobStatusId, int start,int length);
}
