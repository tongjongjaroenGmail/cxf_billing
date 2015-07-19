/**
 * 
 */
package com.metasoft.claim.controller.ajax;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.metasoft.claim.controller.vo.ClaimSaveResultVo;
import com.metasoft.claim.controller.vo.ClaimSaveVo;
import com.metasoft.claim.controller.vo.ClaimSearchResultVo;
import com.metasoft.claim.controller.vo.ResultVo;
import com.metasoft.claim.controller.vo.TrackingSearchCriteriaVo;
import com.metasoft.claim.controller.vo.TrackingSearchResultVo;
import com.metasoft.claim.model.SecUser;
import com.metasoft.claim.model.TblClaimRecovery;
import com.metasoft.claim.service.claim.ClaimService;
import com.metasoft.claim.service.claim.ReportService;

@Controller
public class TrackingAjaxController extends BaseAjaxController {
	@Autowired
	private ReportService trackingService;

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
	
	@RequestMapping(value = "/tracking/print", method = RequestMethod.POST)
	public void print(Model model,
			@RequestParam(required = false) String paramJobDateStart,
			@RequestParam(required = false) String paramJobDateEnd,
			@RequestParam(required = false) String paramPartyInsuranceId,
			@RequestParam(required = false) String paramClaimTypeId,
			@RequestParam(required = false) String paramPageName,
			@RequestParam(required = false) String paramFirstTime,
			
			@RequestParam(required = false) Integer draw,
			@RequestParam(required = false) Integer start,
			@RequestParam(required = false) Integer length,
			HttpSession session,
			HttpServletResponse response ) throws ParseException, ServletException, IOException {
		
		try{
			
		   List<TrackingSearchResultVo> dataList = new ArrayList<TrackingSearchResultVo>();
		   
		   dataList = trackingService.trackingPrint(paramJobDateStart, paramJobDateEnd, paramPartyInsuranceId, paramClaimTypeId, "tracking");
		   
		   System.out.println(">>>>> dataList.size() = "+dataList.size());
		   
//		   String contentType = "application/vnd.ms-excel";
//		   req.setAttribute("rowCount", dataList.size());
//           req.setAttribute("vo", dataList);
//
//           String headerName = "attachment; filename=\"" + "Follow" + "\"";
//           headerName = new String(headerName.getBytes("TIS620"), "ISO8859-1");
//           resp.setContentType(contentType);
//           //resp.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//           resp.addHeader("Content-Disposition", headerName);
//           RequestDispatcher view = req.getServletContext().getRequestDispatcher("/report/trackingRPT.jsp");
//           view.forward( req, resp);
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
}
