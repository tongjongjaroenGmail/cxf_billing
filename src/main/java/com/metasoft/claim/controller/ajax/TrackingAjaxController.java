/**
 * 
 */
package com.metasoft.claim.controller.ajax;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRException;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.metasoft.claim.bean.paging.ClaimSearchResultVoPaging;
import com.metasoft.claim.bean.paging.TrackingSearchResultVoPaging;
import com.metasoft.claim.bean.report.BillingExportResult;
import com.metasoft.claim.controller.vo.ClaimSaveResultVo;
import com.metasoft.claim.controller.vo.ClaimSaveVo;
import com.metasoft.claim.controller.vo.ClaimSearchResultVo;
import com.metasoft.claim.controller.vo.ResultVo;
import com.metasoft.claim.controller.vo.TrackingSearchCriteriaVo;
import com.metasoft.claim.controller.vo.TrackingSearchResultVo;
import com.metasoft.claim.model.ClaimType;
import com.metasoft.claim.model.SecUser;
import com.metasoft.claim.model.TblClaimRecovery;
import com.metasoft.claim.service.claim.ClaimService;
import com.metasoft.claim.service.claim.ReportService;
import com.metasoft.claim.service.impl.DownloadService;
import com.metasoft.claim.service.impl.ExporterService;
import com.metasoft.claim.service.impl.TokenService;
import com.metasoft.claim.service.standard.InsuranceService;
import com.metasoft.claim.util.ThaiBaht;

@Controller
public class TrackingAjaxController extends BaseAjaxController {
	@Autowired
	private ReportService trackingService;
	
	@Autowired
	private DownloadService downloadService;

	@Autowired
	private TokenService tokenService;
	@Autowired
	private InsuranceService insuranceService;

	

	@RequestMapping(value = "/tracking/search", method = RequestMethod.POST)
	public @ResponseBody
	String search(Model model,
			@RequestParam(required = false) String paramJobDateStart,
			@RequestParam(required = false) String paramJobDateEnd,
			@RequestParam(required = false) String paramPartyInsuranceId,
			@RequestParam(required = false) String paramClaimTypeId,
			@RequestParam(required = false) String paramPageName,
			@RequestParam(required = false) String paramFirstTime,
			
			@RequestParam(required = true) Integer draw,
			@RequestParam(required = true) Integer start,
			@RequestParam(required = true) Integer length) throws ParseException {
		
		TrackingSearchResultVoPaging resultPaging = new TrackingSearchResultVoPaging();
		resultPaging.setDraw(++draw);
		if(new Boolean(paramFirstTime)){		
			resultPaging.setRecordsFiltered(0L);
			resultPaging.setRecordsTotal(0L);
			resultPaging.setData(new ArrayList<TrackingSearchResultVo>());;
		}else{
			resultPaging = trackingService.searchPaging(paramJobDateStart, paramJobDateEnd, paramPartyInsuranceId, paramClaimTypeId, start, length,paramPageName);
		}

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(resultPaging);
		return json;
	}
	
	
	@RequestMapping(value = "/tracking/labor", method = RequestMethod.POST)
	public @ResponseBody
	String searchLabor(Model model,
			@RequestParam(required = false) String paramJobDateStart,
			@RequestParam(required = false) String paramJobDateEnd,
			@RequestParam(required = false) String paramAgentId,
			@RequestParam(required = false) String paramClaimTypeId,
			@RequestParam(required = false) String paramPageName,
			@RequestParam(required = false) String paramFirstTime,
			
			@RequestParam(required = true) Integer draw,
			@RequestParam(required = true) Integer start,
			@RequestParam(required = true) Integer length) throws ParseException {
		
		System.out.println(">>>>> paramAgentId = "+paramAgentId);
		
		TrackingSearchResultVoPaging resultPaging = new TrackingSearchResultVoPaging();
		resultPaging.setDraw(++draw);
		if(new Boolean(paramFirstTime)){		
			resultPaging.setRecordsFiltered(0L);
			resultPaging.setRecordsTotal(0L);
			resultPaging.setData(new ArrayList<TrackingSearchResultVo>());
		}else{
			resultPaging = trackingService.searchPagingLabor(paramJobDateStart, paramJobDateEnd, paramAgentId, paramClaimTypeId, start, length);
		}

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(resultPaging);
		return json;
	}
		
	@RequestMapping(value = "/tracking/export", method = RequestMethod.POST)
	public void export(
			@RequestParam(required = true) Integer[] chk,
			@RequestParam(required = false) String token,
			@RequestParam(required = true) String claimSearch,
			@RequestParam(required = true) String companySearch,
			HttpSession session, HttpServletResponse response) 
			throws ServletException,IOException, JRException, Exception {
		
			String titleName = "หนังสือสัญญา";
			String insuranceName = "บริษัท ";
			HashMap<String,Object> params = new HashMap<String, Object>();
			
			System.out.println(">>>> claimSearch = "+claimSearch);
			System.out.println(">>>> ClaimType.FAST_TRACK = "+ClaimType.FAST_TRACK);
			
			if (claimSearch != null && !claimSearch.equals("")) {
					int temp = Integer.parseInt(claimSearch);
				if (temp == ClaimType.FAST_TRACK.getId()) {
					titleName = "หนังสือสัญญา เรียกร้องค่าสินไหมทดแทนรถยนต์แบบ Fast-Track";
				} else if  (temp ==  ClaimType.KFK.getId()) {
					titleName = "หนังสือสัญญา ตกลงไม่เรียกร้องค่าเสียหายซึ่งกันและกัน";
				} else if (temp ==  ClaimType.REQUEST.getId()) {
					titleName = "หนังสือสัญญา รื่องเรียกร้องค่าเสียหาย";
				} else {
					titleName = "หนังสือสัญญา";
				}
				params.put("titleName", titleName);
			} else {
				params.put("titleName", titleName);
			}
			
			if (companySearch != null && !companySearch.equals("")) {
				insuranceName = insuranceService.findById(Integer.parseInt(companySearch)).getFullName();
				params.put("insuranceFullName", insuranceName);
			} else {
				params.put("insuranceFullName", insuranceName);
			}
			
			List<TrackingSearchResultVo> results = trackingService.searchExport(chk);
			List<TrackingSearchResultVo> exports = new ArrayList<TrackingSearchResultVo>();
			
			for (TrackingSearchResultVo result : results) {
					exports.add(result);
				}
				downloadService.download(ExporterService.EXTENSION_TYPE_EXCEL, "tracking", session.getServletContext().getRealPath("/report/tracking"),
					params,
					exports, 
					token, 
					response);
			}
	
	
}
