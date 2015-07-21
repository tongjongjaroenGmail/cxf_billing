/**
 * 
 */
package com.metasoft.claim.service.claim;



import java.util.List;

import com.metasoft.claim.bean.paging.TrackingSearchResultVoPaging;
import com.metasoft.claim.bean.report.BillingExportResult;
import com.metasoft.claim.controller.vo.LaborResultVo;
import com.metasoft.claim.controller.vo.TrackingSearchResultVo;
import com.metasoft.claim.dao.claim.ReportDao;
import com.metasoft.claim.model.TblClaimRecovery;
import com.metasoft.claim.service.ModelBasedService;

public interface ReportService extends ModelBasedService<ReportDao, TblClaimRecovery, Integer> {
	public TrackingSearchResultVoPaging searchPaging(String paramJobDateStart, String paramJobDateEnd, String paramPartyInsuranceId,
			String paramClaimTypeId, int start, int length,String pageName);
	
	public TrackingSearchResultVoPaging searchPagingLabor(String paramJobDateStart, String paramJobDateEnd, String agentName,
			String paramClaimTypeId, int start, int length);
	
	public List<TrackingSearchResultVo> trackingPrint(String paramJobDateStart, String paramJobDateEnd, String paramPartyInsuranceId,
			//String paramClaimTypeId, int start, int length,String pageName);
			String paramClaimTypeId,String pageName);
	
	public List<TrackingSearchResultVo> laborPrint(String paramJobDateStart, String paramJobDateEnd, String agentName,
			String paramClaimTypeId);
	
	public List<TrackingSearchResultVo> searchExport(Integer[] ids); 
	
	public List<LaborResultVo> searchExportLabor(Integer[] ids);
	

	 
}
