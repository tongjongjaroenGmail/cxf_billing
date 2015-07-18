/**
 * 
 */
package com.metasoft.claim.service.impl.claim;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import net.sf.jasperreports.repo.ReportResource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.metasoft.claim.bean.paging.ClaimPaging;
import com.metasoft.claim.bean.paging.ClaimSearchResultVoPaging;
import com.metasoft.claim.bean.paging.ReportPaging;
import com.metasoft.claim.bean.paging.TrackingSearchResultVoPaging;
import com.metasoft.claim.controller.vo.ClaimSaveVo;
import com.metasoft.claim.controller.vo.ClaimSearchResultVo;
import com.metasoft.claim.controller.vo.TrackingSearchCriteriaVo;
import com.metasoft.claim.controller.vo.TrackingSearchResultVo;
import com.metasoft.claim.dao.claim.ClaimDao;
import com.metasoft.claim.dao.claim.ReportDao;
import com.metasoft.claim.dao.security.UserDao;
import com.metasoft.claim.dao.standard.InsuranceDao;
import com.metasoft.claim.model.ClaimType;
import com.metasoft.claim.model.JobStatus;
import com.metasoft.claim.model.SecUser;
import com.metasoft.claim.model.StdInsurance;
import com.metasoft.claim.model.TblClaimRecovery;
import com.metasoft.claim.service.claim.ClaimService;
import com.metasoft.claim.service.claim.ReportService;
import com.metasoft.claim.service.impl.ModelBasedServiceImpl;
import com.metasoft.claim.service.standard.InsuranceService;
import com.metasoft.claim.util.DateToolsUtil;
import com.metasoft.claim.util.NumberToolsUtil;

@Service("reportService")
public class ReportServiceImpl extends ModelBasedServiceImpl<ReportDao, TblClaimRecovery, Integer> implements ReportService {
	
	@Autowired
	private InsuranceService insuranceService;
	private ReportDao reportDao;


	/**
	 * 
	 * @param entityClass
	 */
	@Autowired
	public ReportServiceImpl(ReportDao dao) {
		super(dao);
		this.reportDao = dao;
	}

	
	@Override
	public TrackingSearchResultVoPaging searchPaging(String paramJobDateStart,
			String paramJobDateEnd, String paramPartyInsuranceId,
			String paramClaimTypeId, int start, int length,String pageName) {
		
		Date jobDateStart = null;
		Date jobDateEnd = null;
		StdInsurance partyInsurance = null;
		ClaimType claimType = null;
		
		if (StringUtils.isNotBlank(paramJobDateStart)) {
			jobDateStart = DateToolsUtil.convertStringToDate(paramJobDateStart, DateToolsUtil.LOCALE_TH);
		}

		if (StringUtils.isNotBlank(paramJobDateEnd)) {
			jobDateEnd = DateToolsUtil.convertStringToDate(paramJobDateEnd, DateToolsUtil.LOCALE_TH);
		}

		if (StringUtils.isNotBlank(paramPartyInsuranceId)) {
			partyInsurance = insuranceService.findById(Integer.parseInt(paramPartyInsuranceId));
		}
		
		if (StringUtils.isNotBlank(paramClaimTypeId)) {
			claimType = ClaimType.getById(Integer.parseInt(paramClaimTypeId));
		}
		
		ReportPaging reportPaging = reportDao.searchPaging(jobDateStart, jobDateEnd, partyInsurance, claimType, start, length,pageName);
		TrackingSearchResultVoPaging voPaging = new TrackingSearchResultVoPaging();
		
		voPaging.setDraw(reportPaging.getDraw());
		voPaging.setRecordsFiltered(reportPaging.getRecordsFiltered());
		voPaging.setRecordsTotal(reportPaging.getRecordsTotal());
		voPaging.setData(new ArrayList<TrackingSearchResultVo>());
		
		if(reportPaging != null && reportPaging.getData() != null){
			for (TblClaimRecovery claim : reportPaging.getData()) {
				TrackingSearchResultVo  vo = new TrackingSearchResultVo();
				vo.setClaimNumber(StringUtils.trimToEmpty(claim.getClaimNumber()));
				if (claim.getClaimType() != null) {
					vo.setClaimType(claim.getClaimType().getName());
				}
				if (claim.getPartyInsurance() != null) {
					vo.setInsuranceName(claim.getPartyInsurance().getName());
				}
				if (claim.getJobDate() != null) {
					vo.setJobDate(DateToolsUtil.convertToString(claim.getJobDate(), DateToolsUtil.LOCALE_TH));
				}
				
				if (claim.getMaturityDate() != null) {
					vo.setMaturityDate(DateToolsUtil.convertToString(claim.getMaturityDate(), DateToolsUtil.LOCALE_TH));
				}
				
				if (claim.getAccidentDate() != null) {
					vo.setAccidentDate(DateToolsUtil.convertToString(claim.getAccidentDate(),DateToolsUtil.LOCALE_TH));
				}
				
				if(claim.getLicenseNumber() != null) {
					vo.setLicenseNumber(claim.getLicenseNumber());
				}
				if (claim.getClaimAmount() != null) {
					vo.setClaimAmount(claim.getClaimAmount());
				}
				if (claim.getPartyPolicyNo() != null) {
					vo.setPolicyNo(claim.getPartyPolicyNo());
				}
				if (claim.getCloseDate() != null) {
					vo.setCloseDate(DateToolsUtil.convertToString(claim.getCloseDate(),DateToolsUtil.LOCALE_TH));
					
				}
				
				voPaging.getData().add(vo);
			}
		}

		return voPaging;
	}
		


}
