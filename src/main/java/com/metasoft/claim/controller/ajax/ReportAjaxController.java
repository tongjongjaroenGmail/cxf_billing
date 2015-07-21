package com.metasoft.claim.controller.ajax;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.metasoft.claim.controller.vo.ClaimSearchResultVo;
import com.metasoft.claim.controller.vo.StatusResponse;
import com.metasoft.claim.controller.vo.TrackingSearchResultVo;
import com.metasoft.claim.service.claim.ClaimService;
import com.metasoft.claim.service.claim.ReportService;
import com.metasoft.claim.service.impl.DownloadService;
import com.metasoft.claim.service.impl.ExporterService;
import com.metasoft.claim.service.impl.TokenService;

@Controller
@RequestMapping("/report")
public class ReportAjaxController {
	@Autowired
	private ClaimService claimService;

	@Autowired
	private DownloadService downloadService;

	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private ReportService reportService;


	@RequestMapping(value="/download/progress")
	public @ResponseBody StatusResponse checkDownloadProgress(@RequestParam String token) {
		return new StatusResponse(true, tokenService.check(token));
	}
	
	@RequestMapping(value="/download/token")
	public @ResponseBody StatusResponse getDownloadToken() {
		return new StatusResponse(true, tokenService.generate());
	}
             
	@RequestMapping(value="/work")
	public void work(
			@RequestParam(required = false) String txtJobDateStart,
			@RequestParam(required = false) String txtJobDateEnd, 
			@RequestParam(required = false) String selInsurance , 
			@RequestParam(required = false) String txtTotalDayOfMaturity,
			@RequestParam(required = false) String selClaimType,
			@RequestParam(required = false) String txtClaimNumber,
			@RequestParam(required = false) String selJobStatus,
			@RequestParam(required = false) String token,
			HttpSession session,
			HttpServletResponse response) throws ParseException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
		
		List<ClaimSearchResultVo> results = claimService.searchExport(txtJobDateStart, txtJobDateEnd, selInsurance, txtTotalDayOfMaturity, selClaimType, 
				txtClaimNumber, selJobStatus);

		
		downloadService.download(
				ExporterService.EXTENSION_TYPE_EXCEL, 
				"work", 
				session.getServletContext().getRealPath("/report/work"), 
				new HashMap(), 
				results,
				token, 
				response);
	}
	
	@RequestMapping(value="/tracking")
	public void tracking(
			
			@RequestParam(required = false) String paramJobDateStart,
			@RequestParam(required = false) String paramJobDateEnd,
			@RequestParam(required = false) String paramPartyInsuranceId,
			@RequestParam(required = false) String paramClaimTypeId,
			@RequestParam(required = false) String paramPageName,
			@RequestParam(required = false) String paramFirstTime,
			@RequestParam(required = false) String token,
			
			HttpSession session,
			HttpServletResponse response) throws ParseException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
		
//		List<ClaimSearchResultVo> results = reportService.searchExport(txtJobDateStart, txtJobDateEnd, selInsurance, txtTotalDayOfMaturity, selClaimType, 
//				txtClaimNumber, selJobStatus);
				List<TrackingSearchResultVo> results = reportService.trackingPrint(paramJobDateStart, paramJobDateEnd, paramPartyInsuranceId, paramClaimTypeId, "tracking");

		
		downloadService.download(
				ExporterService.EXTENSION_TYPE_EXCEL, 
				"Tracking", 
				session.getServletContext().getRealPath("/report/tracking"), 
				new HashMap(), 
				results,
				token, 
				response);
	}
	
	@RequestMapping(value="/labor")
	public void labor(
			
			@RequestParam(required = false) String paramJobDateStart,
			@RequestParam(required = false) String paramJobDateEnd,
			@RequestParam(required = false) String agentName,
			@RequestParam(required = false) String paramClaimTypeId,
			@RequestParam(required = false) String paramPageName,
			@RequestParam(required = false) String paramFirstTime,
			@RequestParam(required = false) String token,
			@RequestParam(required = false) String claimId,
			
			HttpSession session,
			HttpServletResponse response) throws ParseException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
		
//		List<ClaimSearchResultVo> results = reportService.searchExport(txtJobDateStart, txtJobDateEnd, selInsurance, txtTotalDayOfMaturity, selClaimType, 
//				txtClaimNumber, selJobStatus);
				List<TrackingSearchResultVo> results = reportService.laborPrint(paramJobDateStart, paramJobDateEnd, agentName, paramClaimTypeId);

		
		downloadService.download(
				ExporterService.EXTENSION_TYPE_EXCEL, 
				"labor", 
				session.getServletContext().getRealPath("/report/labor"), 
				new HashMap(), 
				results,
				token, 
				response);
	}
}