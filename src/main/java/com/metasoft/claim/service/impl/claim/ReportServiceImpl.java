/**
 * 
 */
package com.metasoft.claim.service.impl.claim;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
import com.metasoft.claim.service.report.ExportExcel;
import com.metasoft.claim.service.security.UserService;
import com.metasoft.claim.service.standard.InsuranceService;
import com.metasoft.claim.util.DateToolsUtil;
import com.metasoft.claim.util.NumberToolsUtil;

@Service("reportService")
public class ReportServiceImpl extends
		ModelBasedServiceImpl<ReportDao, TblClaimRecovery, Integer> implements
		ReportService {

	@Autowired
	private InsuranceService insuranceService;
	@Autowired
	private ReportDao reportDao;
	@Autowired
	private UserService userService;

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
			String paramClaimTypeId, int start, int length, String pageName) {

		Date jobDateStart = null;
		Date jobDateEnd = null;
		StdInsurance partyInsurance = null;
		ClaimType claimType = null;

		if (StringUtils.isNotBlank(paramJobDateStart)) {
			jobDateStart = DateToolsUtil.convertStringToDate(paramJobDateStart,
					DateToolsUtil.LOCALE_TH);
		}

		if (StringUtils.isNotBlank(paramJobDateEnd)) {
			jobDateEnd = DateToolsUtil.convertStringToDate(paramJobDateEnd,
					DateToolsUtil.LOCALE_TH);
		}

		if (StringUtils.isNotBlank(paramPartyInsuranceId)) {
			partyInsurance = insuranceService.findById(Integer
					.parseInt(paramPartyInsuranceId));
		}

		if (StringUtils.isNotBlank(paramClaimTypeId)) {
			claimType = ClaimType.getById(Integer.parseInt(paramClaimTypeId));
		}

		ReportPaging reportPaging = reportDao.searchPaging(jobDateStart,
				jobDateEnd, partyInsurance, claimType, start, length, pageName);
		TrackingSearchResultVoPaging voPaging = new TrackingSearchResultVoPaging();

		voPaging.setDraw(reportPaging.getDraw());
		voPaging.setRecordsFiltered(reportPaging.getRecordsFiltered());
		voPaging.setRecordsTotal(reportPaging.getRecordsTotal());
		voPaging.setData(new ArrayList<TrackingSearchResultVo>());

		int i = 1;
		if (reportPaging != null && reportPaging.getData() != null) {
			for (TblClaimRecovery claim : reportPaging.getData()) {
				TrackingSearchResultVo vo = new TrackingSearchResultVo();
				vo.setNo(i);
				i++;
				vo.setClaimNumber(StringUtils.trimToEmpty(claim
						.getClaimNumber()));
				if (claim.getClaimType() != null) {
					vo.setClaimType(claim.getClaimType().getName());
				}
				if (claim.getPartyInsurance() != null) {
					vo.setInsuranceName(claim.getPartyInsurance().getName());
				}
				if (claim.getJobDate() != null) {
					vo.setJobDate(DateToolsUtil.convertToString(
							claim.getJobDate(), DateToolsUtil.LOCALE_TH));
				}

				if (claim.getMaturityDate() != null) {
					vo.setMaturityDate(DateToolsUtil.convertToString(
							claim.getMaturityDate(), DateToolsUtil.LOCALE_TH));
				}

				if (claim.getAccidentDate() != null) {
					vo.setAccidentDate(DateToolsUtil.convertToString(
							claim.getAccidentDate(), DateToolsUtil.LOCALE_TH));
				}

				if (claim.getLicenseNumber() != null) {
					vo.setLicenseNumber(claim.getLicenseNumber());
				}
				if (claim.getClaimAmount() != null) {
					vo.setClaimAmount(String.valueOf(claim.getClaimAmount()));
				}
				if (claim.getPolicyNo() != null) {
					vo.setPolicyNo(claim.getPolicyNo());
				}
				if (claim.getCloseDate() != null) {
					vo.setCloseDate(DateToolsUtil.convertToString(
							claim.getCloseDate(), DateToolsUtil.LOCALE_TH));

				}
				voPaging.getData().add(vo);
			}

		}
		return voPaging;
	}

	@Override
	public TrackingSearchResultVoPaging searchPagingLabor(
			String paramJobDateStart, String paramJobDateEnd, String id,
			String paramClaimTypeId, int start, int length) {

		Date jobDateStart = null;
		Date jobDateEnd = null;
		int agent = 0;
		ClaimType claimType = null;

		if (StringUtils.isNotBlank(paramJobDateStart)) {
			jobDateStart = DateToolsUtil.convertStringToDate(paramJobDateStart,
					DateToolsUtil.LOCALE_TH);
		}

		if (StringUtils.isNotBlank(paramJobDateEnd)) {
			jobDateEnd = DateToolsUtil.convertStringToDate(paramJobDateEnd,
					DateToolsUtil.LOCALE_TH);
		}

		if (StringUtils.isNotBlank(id)) {
			agent = Integer.parseInt(id);
		}

		if (StringUtils.isNotBlank(paramClaimTypeId)) {
			claimType = ClaimType.getById(Integer.parseInt(paramClaimTypeId));
		}

		ReportPaging reportPaging = reportDao.searchPaging(jobDateStart,
				jobDateEnd, agent, claimType, start, length);
		TrackingSearchResultVoPaging voPaging = new TrackingSearchResultVoPaging();

		voPaging.setDraw(reportPaging.getDraw());
		voPaging.setRecordsFiltered(reportPaging.getRecordsFiltered());
		voPaging.setRecordsTotal(reportPaging.getRecordsTotal());
		voPaging.setData(new ArrayList<TrackingSearchResultVo>());

		int i = 1;
		if (reportPaging != null && reportPaging.getData() != null) {
			for (TblClaimRecovery claim : reportPaging.getData()) {
				TrackingSearchResultVo vo = new TrackingSearchResultVo();
				vo.setNo(i);
				i++;

				vo.setClaimNumber(StringUtils.trimToEmpty(claim
						.getClaimNumber()));
				if (claim.getClaimType() != null) {
					vo.setClaimType(claim.getClaimType().getName());
				}
				if (claim.getPartyInsurance() != null) {
					vo.setInsuranceName(claim.getPartyInsurance().getName());
				}
				if (claim.getJobDate() != null) {
					vo.setJobDate(DateToolsUtil.convertToString(
							claim.getJobDate(), DateToolsUtil.LOCALE_TH));
				}

				if (claim.getMaturityDate() != null) {
					vo.setMaturityDate(DateToolsUtil.convertToString(
							claim.getMaturityDate(), DateToolsUtil.LOCALE_TH));
				}

				if (claim.getAccidentDate() != null) {
					vo.setAccidentDate(DateToolsUtil.convertToString(
							claim.getAccidentDate(), DateToolsUtil.LOCALE_TH));
				}

				if (claim.getLicenseNumber() != null) {
					vo.setLicenseNumber(claim.getLicenseNumber());
				}
				if (claim.getClaimAmount() != null) {
					vo.setClaimAmount(String.valueOf(claim.getClaimAmount()));
				}
				if (claim.getPolicyNo() != null) {
					vo.setPolicyNo(claim.getPolicyNo());
				}
				if (claim.getCloseDate() != null) {
					vo.setCloseDate(DateToolsUtil.convertToString(
							claim.getCloseDate(), DateToolsUtil.LOCALE_TH));

				}
				if (claim.getClaimType().getId() != 3) {
					vo.setLaborPrice("80");

				} else if (claim.getClaimType().getId() == 3) {
					vo.setLaborPrice("100");

				} else {
					vo.setLaborPrice("0");
				}

				vo.setTotalPrice("");

				voPaging.getData().add(vo);
			}

		}

		return voPaging;
	}

	@Override
	public List<TrackingSearchResultVo> trackingPrint(String paramJobDateStart,
			String paramJobDateEnd, String paramPartyInsuranceId,
			// String paramClaimTypeId, int start, int length, String pageName)
			// {
			String paramClaimTypeId, String pageName) {

		List<TrackingSearchResultVo> dataList = null;

		Date jobDateStart = null;
		Date jobDateEnd = null;
		StdInsurance partyInsurance = null;
		ClaimType claimType = null;

		if (StringUtils.isNotBlank(paramJobDateStart)) {
			jobDateStart = DateToolsUtil.convertStringToDate(paramJobDateStart,
					DateToolsUtil.LOCALE_TH);
		}

		if (StringUtils.isNotBlank(paramJobDateEnd)) {
			jobDateEnd = DateToolsUtil.convertStringToDate(paramJobDateEnd,
					DateToolsUtil.LOCALE_TH);
		}

		if (StringUtils.isNotBlank(paramPartyInsuranceId)) {
			partyInsurance = insuranceService.findById(Integer
					.parseInt(paramPartyInsuranceId));
		}

		if (StringUtils.isNotBlank(paramClaimTypeId)) {
			claimType = ClaimType.getById(Integer.parseInt(paramClaimTypeId));
		}

		ReportPaging reportPaging = reportDao.searchPaging(jobDateStart,
				jobDateEnd, partyInsurance, claimType, 1, 10, pageName);
		// TrackingSearchResultVoPaging voPaging = new
		// TrackingSearchResultVoPaging();
		//
		// voPaging.setDraw(reportPaging.getDraw());
		// voPaging.setRecordsFiltered(reportPaging.getRecordsFiltered());
		// voPaging.setRecordsTotal(reportPaging.getRecordsTotal());
		// voPaging.setData(new ArrayList<TrackingSearchResultVo>());
		int i = 1;
		if (reportPaging != null && reportPaging.getData() != null) {
			dataList = new ArrayList<TrackingSearchResultVo>();

			for (TblClaimRecovery claim : reportPaging.getData()) {
				i = i + 1;
				TrackingSearchResultVo vo = new TrackingSearchResultVo();
				vo.setNo(i);
				vo.setClaimNumber(StringUtils.trimToEmpty(claim
						.getClaimNumber()));

				if (claim.getClaimType() != null) {
					vo.setClaimType(claim.getClaimType().getName());
				}
				if (claim.getPartyInsurance() != null) {
					vo.setInsuranceName(claim.getPartyInsurance().getName());
				}
				if (claim.getJobDate() != null) {
					vo.setJobDate(DateToolsUtil.convertToString(
							claim.getJobDate(), DateToolsUtil.LOCALE_TH));
				}

				if (claim.getMaturityDate() != null) {
					vo.setMaturityDate(DateToolsUtil.convertToString(
							claim.getMaturityDate(), DateToolsUtil.LOCALE_TH));
				}

				if (claim.getAccidentDate() != null) {
					vo.setAccidentDate(DateToolsUtil.convertToString(
							claim.getAccidentDate(), DateToolsUtil.LOCALE_TH));
				}

				if (claim.getLicenseNumber() != null) {
					vo.setLicenseNumber(claim.getLicenseNumber());
				}
				if (claim.getClaimAmount() != null) {
					vo.setClaimAmount(String.valueOf(claim.getClaimAmount()));
				}
				if (claim.getPolicyNo() != null) {
					vo.setPolicyNo(claim.getPolicyNo());
				}
				if (claim.getCloseDate() != null) {
					vo.setCloseDate(DateToolsUtil.convertToString(
							claim.getCloseDate(), DateToolsUtil.LOCALE_TH));

				}
				if (claim.getPartyClaimNumber() != null) {
					vo.setPartyClaimNumber(claim.getPartyClaimNumber());

				}
				if (claim.getPartyPolicyNo() != null) {
					vo.setPartyPolicyNo(claim.getPartyPolicyNo());
				}

				if (claim.getInvoiceNumber() != null) {
					vo.setInvoiceNumber(claim.getInvoiceNumber());
				}

				if (claim.getRequestAmount() != null) {
					vo.setRequestAmount(claim.getRequestAmount());
				}

				if (claim.getJobStatus() != null) {
					vo.setJobStaus(claim.getJobStatus().getName());

				}

				dataList.add(vo);
			}

		}

		return dataList;
	}

	@Override
	public List<TrackingSearchResultVo> laborPrint(String paramJobDateStart,
			String paramJobDateEnd, String id, String paramClaimTypeId) {
		Date jobDateStart = null;
		Date jobDateEnd = null;
		int agent = 0;
		ClaimType claimType = null;
		
		List<TrackingSearchResultVo> dataList = null;
		try {
			if (StringUtils.isNotBlank(paramJobDateStart)) {
				jobDateStart = DateToolsUtil.convertStringToDate(
						paramJobDateStart, DateToolsUtil.LOCALE_TH);
			}

			if (StringUtils.isNotBlank(paramJobDateEnd)) {
				jobDateEnd = DateToolsUtil.convertStringToDate(paramJobDateEnd,
						DateToolsUtil.LOCALE_TH);
			}

			if (StringUtils.isNotBlank(id)) {
				agent = Integer.parseInt(id);
			}

			if (StringUtils.isNotBlank(paramClaimTypeId)) {
				claimType = ClaimType.getById(Integer
						.parseInt(paramClaimTypeId));
			}

			List<TblClaimRecovery> daoList = reportDao.searchExport(jobDateStart, jobDateEnd, agent, claimType);
			int i = 1;

			if (daoList != null) {
				
				dataList = new ArrayList<TrackingSearchResultVo>();
				for (TblClaimRecovery claim : daoList) {
					TrackingSearchResultVo vo = new TrackingSearchResultVo();
					vo.setNo(i);
					i++;

					vo.setClaimNumber(StringUtils.trimToEmpty(claim
							.getClaimNumber()));
					if (claim.getClaimType() != null) {
						vo.setClaimType(claim.getClaimType().getName());
					}
					if (claim.getPartyInsurance() != null) {
						vo.setInsuranceName(claim.getPartyInsurance().getName());
					}
					if (claim.getJobDate() != null) {
						vo.setJobDate(DateToolsUtil.convertToString(
								claim.getJobDate(), DateToolsUtil.LOCALE_TH));
					}

					if (claim.getMaturityDate() != null) {
						vo.setMaturityDate(DateToolsUtil.convertToString(
								claim.getMaturityDate(),
								DateToolsUtil.LOCALE_TH));
					}

					if (claim.getAccidentDate() != null) {
						vo.setAccidentDate(DateToolsUtil.convertToString(
								claim.getAccidentDate(),
								DateToolsUtil.LOCALE_TH));
					}

					if (claim.getLicenseNumber() != null) {
						vo.setLicenseNumber(claim.getLicenseNumber());
					}
					if (claim.getClaimAmount() != null) {
						vo.setClaimAmount(String.valueOf(claim.getClaimAmount()));
					}
					if (claim.getPolicyNo() != null) {
						vo.setPolicyNo(claim.getPolicyNo());
					}
					if (claim.getCloseDate() != null) {
						vo.setCloseDate(DateToolsUtil.convertToString(
								claim.getCloseDate(), DateToolsUtil.LOCALE_TH));

					}
					if (claim.getClaimType().getId() != 3) {
						vo.setLaborPrice("80");

					} else if (claim.getClaimType().getId() == 3) {
						vo.setLaborPrice("100");

					} else {
						vo.setLaborPrice("0");
					}

					vo.setTotalPrice("");
					

					dataList.add(vo);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataList;
		

	}

}
