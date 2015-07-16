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
import com.metasoft.claim.model.ReceiveMoneyType;
import com.metasoft.claim.model.SecUser;
import com.metasoft.claim.model.StdInsurance;
import com.metasoft.claim.model.TblClaimRecovery;
import com.metasoft.claim.service.claim.ClaimService;
import com.metasoft.claim.service.impl.ModelBasedServiceImpl;
import com.metasoft.claim.service.standard.InsuranceService;
import com.metasoft.claim.util.DateToolsUtil;
import com.metasoft.claim.util.NumberToolsUtil;

@Service("claimService")
public class ClaimServiceImpl extends ModelBasedServiceImpl<ClaimDao, TblClaimRecovery, Integer> implements ClaimService {
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
	public ClaimServiceImpl(ClaimDao dao) {
		super(dao);
		this.claimDao = dao;
	}

	@Override
	public ClaimSearchResultVoPaging searchPaging(String paramJobDateStart, String paramJobDateEnd, String paramPartyInsuranceId,
			String paramTotalDayOfMaturity, String paramClaimTypeId, String paramClaimNumber, String paramJobStatusId, int start, int length,SecUser user) {

		Date jobDateStart = null;
		Date jobDateEnd = null;
		StdInsurance partyInsurance = null;
		Date maturityDate = null;
		ClaimType claimType = null;
		JobStatus jobStatus = null;
		if (StringUtils.isNotBlank(paramJobDateStart)) {
			jobDateStart = DateToolsUtil.convertStringToDate(paramJobDateStart, DateToolsUtil.LOCALE_TH);
		}

		if (StringUtils.isNotBlank(paramJobDateEnd)) {
			jobDateEnd = DateToolsUtil.convertStringToDate(paramJobDateEnd, DateToolsUtil.LOCALE_TH);
		}

		if (StringUtils.isNotBlank(paramPartyInsuranceId)) {
			partyInsurance = insuranceService.findById(Integer.parseInt(paramPartyInsuranceId));
		}

		if (StringUtils.isNotBlank(paramTotalDayOfMaturity)) {
			int totalDayOfMaturity = NumberToolsUtil.parseToInteger(paramTotalDayOfMaturity);
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_YEAR, + totalDayOfMaturity);
			maturityDate = cal.getTime();
		}

		if (StringUtils.isNotBlank(paramClaimTypeId)) {
			claimType = ClaimType.getById(Integer.parseInt(paramClaimTypeId));
		}

		if (StringUtils.isNotBlank(paramJobStatusId)) {
			jobStatus = JobStatus.getById(Integer.parseInt(paramJobStatusId));
		}

		return searchPaging(jobDateStart, jobDateEnd, partyInsurance, maturityDate, claimType, paramClaimNumber, jobStatus, start, length, user);
	}

	@Override
	public ClaimSearchResultVoPaging searchPaging(Date jobDateStart, Date jobDateEnd, StdInsurance partyInsurance, Date maturityDate,
			ClaimType claimType, String claimNumber, JobStatus jobStatus, int start, int length,SecUser user) {
		ClaimPaging claimPaging = claimDao.searchPaging(jobDateStart, jobDateEnd, partyInsurance, maturityDate, claimType, claimNumber,
				jobStatus, start, length, user);

		ClaimSearchResultVoPaging voPaging = new ClaimSearchResultVoPaging();
		voPaging.setDraw(claimPaging.getDraw());
		voPaging.setRecordsFiltered(claimPaging.getRecordsFiltered());
		voPaging.setRecordsTotal(claimPaging.getRecordsTotal());
		voPaging.setData(new ArrayList<ClaimSearchResultVo>());
		if (claimPaging != null && claimPaging.getData() != null) {
			for (TblClaimRecovery claim : claimPaging.getData()) {
				ClaimSearchResultVo vo = new ClaimSearchResultVo();
				if (claim.getAgent() != null) {
					vo.setAgentName(StringUtils.trimToEmpty(claim.getAgent().getName()) + " "
							+ (StringUtils.trimToEmpty(claim.getAgent().getLastName())));
				}
				vo.setClaimId(claim.getId().intValue());
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
				vo.setJobNo(StringUtils.trimToEmpty(claim.getJobNo()));
				if (claim.getJobStatus() != null) {
					vo.setJobStatus(claim.getJobStatus().getName());
				}
				if (claim.getMaturityDate() != null) {
					vo.setMaturityDate(DateToolsUtil.convertToString(claim.getMaturityDate(), DateToolsUtil.LOCALE_TH));
				}
				if (claim.getRequestAmount() != null) {
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

		if (StringUtils.isNotBlank(claimSaveVo.getTxtClaimId())) {
			entity = claimDao.findById(Integer.parseInt(claimSaveVo.getTxtClaimId()));
		}

		entity.setClaimNumber(StringUtils.trimToNull(claimSaveVo.getTxtClaimNumber()));
		entity.setPolicyNo(StringUtils.trimToNull(claimSaveVo.getTxtPolicyNo()));
		entity.setLicenseNumber(StringUtils.trimToNull(claimSaveVo.getTxtlicenseNumber()));
		if (StringUtils.isNotBlank(claimSaveVo.getTxtAccidentDate())) {
			entity.setAccidentDate(DateToolsUtil.convertStringToDate(claimSaveVo.getTxtAccidentDate(), DateToolsUtil.LOCALE_TH));
		}
		if (StringUtils.isNotBlank(claimSaveVo.getTxtMaturityDate())) {
			entity.setMaturityDate(DateToolsUtil.convertStringToDate(claimSaveVo.getTxtMaturityDate(), DateToolsUtil.LOCALE_TH));
		}

		if (StringUtils.isNotBlank(claimSaveVo.getSelClaimType())) {
			entity.setClaimType(ClaimType.getById(Integer.parseInt(claimSaveVo.getSelClaimType())));
		}

		entity.setClaimInsuranceAmount(NumberToolsUtil.parseFormatToFloat(claimSaveVo.getTxtClaimInsuranceAmount()));
		entity.setRequestAmount(NumberToolsUtil.parseFormatToFloat(claimSaveVo.getTxtRequestAmount()));
		entity.setClaimAmount(NumberToolsUtil.parseFormatToFloat(claimSaveVo.getTxtClaimAmount()));

		if (StringUtils.isNotBlank(claimSaveVo.getSelPartyInsurance())) {
			entity.setPartyInsurance(insuranceDao.findById(Integer.parseInt(claimSaveVo.getSelPartyInsurance())));
		}
		entity.setPartyLicenseNumber(StringUtils.trimToNull(claimSaveVo.getTxtPartyLicenseNumber()));
		entity.setPartyClaimNumber(StringUtils.trimToNull(claimSaveVo.getTxtPartyClaimNumber()));
		entity.setPartyPolicyNo(StringUtils.trimToNull(claimSaveVo.getTxtPartyPolicyNo()));
		entity.setInvoiceNumber(StringUtils.trimToNull(claimSaveVo.getTxtInvoiceNumber()));

		if (StringUtils.isNotBlank(claimSaveVo.getSelAgent())) {
			entity.setAgent(userDao.findById(Integer.parseInt(claimSaveVo.getSelAgent())));
		}

		if (StringUtils.isBlank(claimSaveVo.getTxtClaimId())) {
			entity.setJobDate(today);
			entity.setJobStatus(JobStatus.RECEIVED);

			entity.setReceiveRemark(StringUtils.trimToNull(claimSaveVo.getTxtReceiveRemark()));

			entity.setCreateBy(user);
			entity.setCreateDate(today);
			entity.setId(super.save(entity));

			entity.setJobNo(DateToolsUtil.convertToString(today, DateToolsUtil.DATE_PATTERN_VIEW_YYYYMMDD, DateToolsUtil.LOCALE_TH)
					+ entity.getId());
		} else {
			entity.setJobStatus(JobStatus.getById(Integer.parseInt(claimSaveVo.getSelJobStatus())));

			if (entity.getJobStatus().getId() == JobStatus.RECEIVED.getId()) {
				entity.setReceiveRemark(StringUtils.trimToNull(claimSaveVo.getTxtReceiveRemark()));
				if (entity.getJobDate() == null) {
					entity.setJobDate(today);
				}
			} else if (entity.getJobStatus().getId() == JobStatus.FOLLOWED.getId()) {
				entity.setFollowRemark(StringUtils.trimToNull(claimSaveVo.getTxtFollowRemark()));
				if (entity.getFollowDate() == null) {
					entity.setFollowDate(today);
				}
			} else if (entity.getJobStatus().getId() == JobStatus.CLOSED.getId()) {
				entity.setCloseRemark(StringUtils.trimToNull(claimSaveVo.getTxtCloseRemark()));
				if (entity.getCloseDate() == null) {
					entity.setCloseDate(today);
				}
				
				entity.setReceiveMoneyType(ReceiveMoneyType.getById(Integer.parseInt(claimSaveVo.getSelReceiveMoneyType())));
			} else if (entity.getJobStatus().getId() == JobStatus.CANCELLED.getId()) {
				entity.setCancelRemark(StringUtils.trimToNull(claimSaveVo.getTxtCancelRemark()));
				if (entity.getCancelDate() == null) {
					entity.setCancelDate(today);
				}
			}
			entity.setUpdateBy(user);
			entity.setUpdateDate(today);
			
		}
		super.save(entity);

		return entity;
	}

	@Override
	public ClaimSaveVo findById(Integer id,SecUser user) {
		TblClaimRecovery entity = super.findById(id);
		ClaimSaveVo claimSaveVo = new ClaimSaveVo();

		claimSaveVo.setTxtClaimNumber(StringUtils.trimToEmpty(entity.getClaimNumber()));
		claimSaveVo.setTxtPolicyNo(StringUtils.trimToEmpty(entity.getPolicyNo()));
		claimSaveVo.setTxtlicenseNumber(StringUtils.trimToEmpty(entity.getLicenseNumber()));

		if (entity.getAccidentDate() != null) {
			claimSaveVo.setTxtAccidentDate(DateToolsUtil.convertToString(entity.getAccidentDate(), DateToolsUtil.LOCALE_TH));
		}
		if (entity.getMaturityDate() != null) {
			claimSaveVo.setTxtMaturityDate(DateToolsUtil.convertToString(entity.getMaturityDate(), DateToolsUtil.LOCALE_TH));
		}

		if (entity.getClaimType() != null) {
			claimSaveVo.setSelClaimType(String.valueOf(entity.getClaimType().getId()));
		}

		claimSaveVo.setTxtClaimInsuranceAmount(NumberToolsUtil.decimalFormat(entity.getClaimInsuranceAmount()));
		claimSaveVo.setTxtRequestAmount(NumberToolsUtil.decimalFormat(entity.getRequestAmount()));
		claimSaveVo.setTxtClaimAmount(NumberToolsUtil.decimalFormat(entity.getClaimAmount()));

		if (entity.getPartyInsurance() != null) {
			claimSaveVo.setSelPartyInsurance(String.valueOf(entity.getPartyInsurance().getId()));
		}
		claimSaveVo.setTxtPartyLicenseNumber(StringUtils.trimToEmpty(entity.getPartyLicenseNumber()));
		claimSaveVo.setTxtPartyClaimNumber(StringUtils.trimToEmpty(entity.getPartyClaimNumber()));
		claimSaveVo.setTxtPartyPolicyNo(StringUtils.trimToEmpty(entity.getPartyPolicyNo()));
		claimSaveVo.setTxtInvoiceNumber(StringUtils.trimToEmpty(entity.getInvoiceNumber()));

		if (entity.getAgent() != null) {
			claimSaveVo.setSelAgent(String.valueOf(entity.getAgent().getId()));
		}

		claimSaveVo.setTxtJobDate(DateToolsUtil.convertToString(entity.getJobDate(), DateToolsUtil.LOCALE_TH));
		claimSaveVo.setSelJobStatus(String.valueOf(entity.getJobStatus().getId()));
		claimSaveVo.setTxtReceiveRemark(StringUtils.trimToEmpty(entity.getReceiveRemark()));
		claimSaveVo.setTxtJobNo(StringUtils.trimToEmpty(entity.getJobNo()));

		claimSaveVo.setTxtFollowRemark(StringUtils.trimToEmpty(entity.getFollowRemark()));
		if (entity.getFollowDate() != null) {
			claimSaveVo.setTxtFollowDate(DateToolsUtil.convertToString(entity.getFollowDate(), DateToolsUtil.LOCALE_TH));
		}

		claimSaveVo.setTxtCloseRemark(StringUtils.trimToEmpty(entity.getCloseRemark()));
		if (entity.getCloseDate() != null) {
			claimSaveVo.setTxtCloseDate(DateToolsUtil.convertToString(entity.getCloseDate(), DateToolsUtil.LOCALE_TH));
		}

		claimSaveVo.setTxtCancelRemark(StringUtils.trimToEmpty(entity.getCancelRemark()));
		if (entity.getCancelDate() != null) {
			claimSaveVo.setTxtCancelDate(DateToolsUtil.convertToString(entity.getCancelDate(), DateToolsUtil.LOCALE_TH));
		}
		
		if (entity.getReceiveMoneyType() != null) {
			claimSaveVo.setSelReceiveMoneyType(String.valueOf(entity.getReceiveMoneyType().getId()));
		}
		
		claimSaveVo.setTxtClaimId(String.valueOf(entity.getId()));
		
		return claimSaveVo;
	}
}
