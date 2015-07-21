/**
 * 
 */
package com.metasoft.claim.service.report;

import java.util.List;

import com.metasoft.claim.bean.paging.BillingSearchResultVoPaging;
import com.metasoft.claim.bean.report.BillingExportResult;
import com.metasoft.claim.dao.claim.ClaimDao;
import com.metasoft.claim.model.TblClaimRecovery;
import com.metasoft.claim.service.ModelBasedService;

public interface BillingService extends ModelBasedService<ClaimDao, TblClaimRecovery, Integer> {
	public BillingSearchResultVoPaging searchPaging(String paramCloseDateStart, String paramCloseDateEnd, String paramPartyInsuranceId,
			String paramClaimTypeId, int start, int length);

	public List<BillingExportResult> searchExport(Integer[] ids);
}
