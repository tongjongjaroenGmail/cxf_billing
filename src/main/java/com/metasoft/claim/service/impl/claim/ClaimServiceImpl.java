/**
 * 
 */
package com.metasoft.claim.service.impl.claim;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.metasoft.claim.bean.paging.ClaimPaging;
import com.metasoft.claim.bean.paging.ClaimSearchResultVoPaging;
import com.metasoft.claim.controller.vo.ClaimSaveVo;
import com.metasoft.claim.controller.vo.ClaimSearchResultVo;
import com.metasoft.claim.dao.claim.ClaimDao;
import com.metasoft.claim.dao.security.UserDao;
import com.metasoft.claim.dao.standard.InsuranceDao;
import com.metasoft.claim.model.ClaimType;
import com.metasoft.claim.model.JobStatus;
import com.metasoft.claim.model.SecUser;
import com.metasoft.claim.model.StdInsurance;
import com.metasoft.claim.model.TblClaimRecovery;
import com.metasoft.claim.service.claim.ClaimService;
import com.metasoft.claim.service.impl.ModelBasedServiceImpl;
import com.metasoft.claim.service.standard.InsuranceService;
import com.metasoft.claim.util.DateToolsUtil;
import com.metasoft.claim.util.NumberToolsUtil;

@Service("claimService")
public class ClaimServiceImpl extends ModelBasedServiceImpl<ClaimDao, TblClaimRecovery, Integer> implements ClaimService
{
	@Autowired
	private InsuranceService insuranceService;
	
    private ClaimDao claimDao;
    
    @Autowired
    private InsuranceDao insuranceDao;
    
    @Autowired
    private UserDao userDao;
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
	public ClaimSearchResultVoPaging searchPaging(String paramJobDateStart, String paramJobDateEnd, String paramPartyInsuranceId,
			String paramTotalDayOfMaturity, String paramClaimTypeId, String paramClaimNumber, String paramJobStatusId, int start, int length) {
    	
    	Date jobDateStart = null;
		Date jobDateEnd = null;
		StdInsurance partyInsurance = null;
		Date maturityDate = null;
		ClaimType claimType = null;
		JobStatus jobStatus = null;
		if(StringUtils.isNotBlank(paramJobDateStart)){
			jobDateStart = DateToolsUtil.convertStringToDate(paramJobDateStart, DateToolsUtil.LOCALE_TH);
		}
		
		if(StringUtils.isNotBlank(paramJobDateEnd)){
			jobDateEnd = DateToolsUtil.convertStringToDate(paramJobDateEnd, DateToolsUtil.LOCALE_TH);
		}
		
		if(StringUtils.isNotBlank(paramPartyInsuranceId)){
			partyInsurance = insuranceService.findById(Integer.parseInt(paramPartyInsuranceId));
		}
		
		if(StringUtils.isNotBlank(paramTotalDayOfMaturity)){
			int totalDayOfMaturity = NumberToolsUtil.parseToInteger(paramTotalDayOfMaturity);
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_YEAR, -totalDayOfMaturity);
			maturityDate = cal.getTime();
		}
		
		if(StringUtils.isNotBlank(paramClaimTypeId)){
			claimType = ClaimType.valueOf(paramClaimTypeId);
		}
		
		if(StringUtils.isNotBlank(paramJobStatusId)){
			jobStatus = JobStatus.valueOf(paramJobStatusId);
		}

		return searchPaging(jobDateStart, jobDateEnd, partyInsurance, maturityDate, claimType, paramClaimNumber, jobStatus, start, length);
	}
    
	@Override
	public ClaimSearchResultVoPaging searchPaging(Date jobDateStart, Date jobDateEnd, StdInsurance partyInsurance, Date maturityDate,
			ClaimType claimType, String claimNumber, JobStatus jobStatus, int start, int length) {
		ClaimPaging claimPaging = claimDao.searchPaging(jobDateStart, jobDateEnd, partyInsurance, maturityDate, claimType, claimNumber, jobStatus, start, length);
	
		ClaimSearchResultVoPaging voPaging = new ClaimSearchResultVoPaging();
		voPaging.setDraw(claimPaging.getDraw());
		voPaging.setRecordsFiltered(claimPaging.getRecordsFiltered());
		voPaging.setRecordsTotal(claimPaging.getRecordsTotal());
		voPaging.setData(new ArrayList<ClaimSearchResultVo>());
		if(claimPaging != null && claimPaging.getData() != null){
			for (TblClaimRecovery claim : claimPaging.getData()) {
				ClaimSearchResultVo vo = new ClaimSearchResultVo();
				if(claim.getAgent() != null){
					vo.setAgentName(StringUtils.trimToEmpty(claim.getAgent().getName())  + " " + (StringUtils.trimToEmpty(claim.getAgent().getLastName())));
				}
				vo.setClaimId(claim.getId().intValue());
				vo.setClaimNumber(StringUtils.trimToEmpty(claim.getClaimNumber()));
				if(claim.getClaimType() != null){
					vo.setClaimType(claim.getClaimType().getName());
				}		
				if(claim.getPartyInsurance() != null){
					vo.setInsuranceName(claim.getPartyInsurance().getName());
				}
				if(claim.getJobDate() != null){
					vo.setJobDate(DateToolsUtil.convertToString(claim.getJobDate() , DateToolsUtil.LOCALE_TH));
				}
				vo.setJobNo(StringUtils.trimToEmpty(claim.getJobNo()));
				if(claim.getJobStatus() != null){
					vo.setJobStatus(claim.getJobStatus().getName());
				}
				if(claim.getMaturityDate() != null){
					vo.setMaturityDate(DateToolsUtil.convertToString(claim.getMaturityDate() , DateToolsUtil.LOCALE_TH));
				}
				if(claim.getRequestAmount() != null){
					vo.setRequestAmount(NumberToolsUtil.decimalFormat(claim.getRequestAmount()));
				}	
				voPaging.getData().add(vo);
			}			
		}
		
		return voPaging;
	}

	@Override
	@Transactional
	public TblClaimRecovery save(ClaimSaveVo claimSaveVo, SecUser user) {
		TblClaimRecovery entity = new TblClaimRecovery();
		Date today = new Date();
		
		if(StringUtils.isNotBlank(claimSaveVo.getTxtClaimId()) ){
			entity = claimDao.findById(Integer.parseInt(claimSaveVo.getTxtClaimId()));
		}
		
		entity.setClaimNumber(StringUtils.trimToNull(claimSaveVo.getTxtClaimNumber()));
		entity.setPolicyNo(StringUtils.trimToNull(claimSaveVo.getTxtPolicyNo()));
		entity.setLicenseNumber(StringUtils.trimToNull(claimSaveVo.getTxtlicenseNumber()));
		if(StringUtils.isNotBlank(claimSaveVo.getTxtAccidentDate())){
			entity.setAccidentDate(DateToolsUtil.convertStringToDate(claimSaveVo.getTxtAccidentDate(), DateToolsUtil.LOCALE_TH));
		}
		if(StringUtils.isNotBlank(claimSaveVo.getTxtMaturityDate())){
			entity.setMaturityDate(DateToolsUtil.convertStringToDate(claimSaveVo.getTxtMaturityDate(), DateToolsUtil.LOCALE_TH));
		}
		
		if(StringUtils.isNotBlank(claimSaveVo.getSelClaimType())){
			entity.setClaimType(ClaimType.getById(Integer.parseInt(claimSaveVo.getSelClaimType())));
		}
		
		entity.setClaimInsuranceAmount(NumberToolsUtil.parseFormatToFloat(claimSaveVo.getTxtClaimInsuranceAmount()));
		entity.setRequestAmount(NumberToolsUtil.parseFormatToFloat(claimSaveVo.getTxtRequestAmount()));
		entity.setClaimAmount(NumberToolsUtil.parseFormatToFloat(claimSaveVo.getTxtClaimAmount()));
		
		if(StringUtils.isNotBlank(claimSaveVo.getSelPartyInsurance())){
			entity.setPartyInsurance(insuranceDao.findById(Integer.parseInt(claimSaveVo.getSelPartyInsurance())));
		}
		entity.setPartyLicenseNumber(StringUtils.trimToNull(claimSaveVo.getTxtPartylicenseNumber()));
		entity.setPartyClaimNumber(StringUtils.trimToNull(claimSaveVo.getTxtPartyClaimNumber()));
		entity.setPartyPolicyNo(StringUtils.trimToNull(claimSaveVo.getTxtPartyPolicyNo()));
		entity.setInvoiceNumber(StringUtils.trimToNull(claimSaveVo.getTxtInvoiceNumber()));
		
		if(StringUtils.isNotBlank(claimSaveVo.getSelAgent())){
			entity.setAgent(userDao.findById(Integer.parseInt(claimSaveVo.getSelAgent())));
		}
		
		
		if(StringUtils.isBlank(claimSaveVo.getTxtClaimId()) ){		
			entity.setJobDate(today);
			entity.setJobStatus(JobStatus.RECEIVED);

			entity.setReceiveRemark(StringUtils.trimToNull(claimSaveVo.getTxtReceiveRemark()));

			entity.setCreateBy(user);
			entity.setCreateDate(today);
			entity.setId(super.save(entity));
			
			entity.setJobNo(DateToolsUtil.convertToString(today, DateToolsUtil.DATE_PATTERN_VIEW_YYYYMMDD, DateToolsUtil.LOCALE_TH) + entity.getId());			
		}else{
			entity.setJobStatus(JobStatus.getById(Integer.parseInt(claimSaveVo.getSelJobStatus())));
			
			if(entity.getJobStatus().getId() == JobStatus.RECEIVED.getId()){
				entity.setReceiveRemark(StringUtils.trimToNull(claimSaveVo.getTxtReceiveRemark()));
			}else if(entity.getJobStatus().getId() == JobStatus.FOLLOWED.getId()){
				entity.setFollowRemark(StringUtils.trimToNull(claimSaveVo.getTxtFollowRemark()));
			}else if(entity.getJobStatus().getId() == JobStatus.CLOSED.getId()){
				entity.setCloseRemark(StringUtils.trimToNull(claimSaveVo.getTxtCloseRemark()));
			}else if(entity.getJobStatus().getId() == JobStatus.CANCELLED.getId()){
				entity.setCancelRemark(StringUtils.trimToNull(claimSaveVo.getTxtCancelRemark()));
			}
			entity.setUpdateBy(user);
			entity.setUpdateDate(today);
		}
		super.save(entity);

        return entity;
	}
	
	
}
