/**
 * 
 */
package com.metasoft.claim.service.impl.claim;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metasoft.claim.controller.vo.ClaimSearchCriteriaVo;
import com.metasoft.claim.dao.claim.ClaimDao;
import com.metasoft.claim.model.TblClaimRecovery;
import com.metasoft.claim.service.claim.ClaimService;
import com.metasoft.claim.service.impl.ModelBasedServiceImpl;

@Service
public class ClaimServiceImpl extends ModelBasedServiceImpl<ClaimDao, TblClaimRecovery, Integer> implements ClaimService
{
    private ClaimDao claimDao;
    /**
     * 
     * @param entityClass
     */
    @Autowired
    public ClaimServiceImpl(ClaimDao dao)
    {
        super(dao);
        this.claimDao = dao;
    }
	@Override
	public List<TblClaimRecovery> searchPaging(String jobDateStart, String jobDateEnd, String insuranceId, String totalDayOfMaturity,
			String claimTypeId, String claimNumber, String jobStatusId, int start, int length) {
				return null;
//		return tblClaimRecoveryDao.searchPaging(jobDateStart, jobDateEnd, insuranceId,
//				totalDayOfMaturity,claimTypeId,claimNumber,jobStatusId, start, length);
	}
	
}
