package com.metasoft.claim.dao.impl.claim;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.metasoft.claim.bean.paging.ClaimSearchResultVoPaging;
import com.metasoft.claim.dao.AbstractDaoImpl;
import com.metasoft.claim.dao.claim.ClaimDao;
import com.metasoft.claim.model.TblClaimRecovery;

@Repository("claimDao")
@Transactional
public class ClaimDaoImpl extends AbstractDaoImpl<TblClaimRecovery, Integer> implements ClaimDao {
	public ClaimDaoImpl() {
		super(TblClaimRecovery.class);
	}

	@Override
	public ClaimSearchResultVoPaging searchPaging(String jobDateStart, String jobDateEnd, String insuranceId, String totalDayOfMaturity,
			String claimTypeId, String claimNumber, String jobStatusId, int start, int length) {
		// TODO Auto-generated method stub
		return null;
	}
}
