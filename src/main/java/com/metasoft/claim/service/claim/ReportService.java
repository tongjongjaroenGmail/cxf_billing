/**
 * 
 */
package com.metasoft.claim.service.claim;



import com.metasoft.claim.bean.paging.TrackingSearchResultVoPaging;
import com.metasoft.claim.dao.claim.ReportDao;
import com.metasoft.claim.model.TblClaimRecovery;
import com.metasoft.claim.service.ModelBasedService;

public interface ReportService extends ModelBasedService<ReportDao, TblClaimRecovery, Integer> {
	public TrackingSearchResultVoPaging searchPaging(String paramJobDateStart, String paramJobDateEnd, String paramPartyInsuranceId,
			String paramClaimTypeId, int start, int length,String pageName);
	
}
