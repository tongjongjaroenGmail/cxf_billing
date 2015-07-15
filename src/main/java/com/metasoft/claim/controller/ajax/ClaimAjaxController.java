/**
 * 
 */
package com.metasoft.claim.controller.ajax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.metasoft.claim.bean.paging.ClaimSearchResultVoPaging;
import com.metasoft.claim.controller.vo.ClaimSearchResultVo;
import com.metasoft.claim.service.claim.ClaimService;
import com.metasoft.claim.util.DateUtil;

@Controller
public class ClaimAjaxController extends BaseAjaxController {
	@Autowired
	private ClaimService claimService;

	@RequestMapping(value = "/claim/search", method = RequestMethod.POST)
	public @ResponseBody
	String init(Model model,
			@RequestParam(required = false) String jobDateStart,
			@RequestParam(required = false) String jobDateEnd,
			@RequestParam(required = false) String insuranceId,
			@RequestParam(required = false) String totalDayOfMaturity,
			@RequestParam(required = false) String claimTypeId,
			@RequestParam(required = false) String claimNumber,
			@RequestParam(required = false) String jobStatusId,
			@RequestParam(required = false) String firstTime,
			
			@RequestParam(required = true) Integer draw,
			@RequestParam(required = true) Integer start,
			@RequestParam(required = true) Integer length) throws ParseException {
		ClaimSearchResultVoPaging resultVoPaging = new ClaimSearchResultVoPaging();
		if(new Boolean(firstTime)){
			resultVoPaging.setDraw(++draw);
			resultVoPaging.setRecordsFiltered(0L);
			resultVoPaging.setRecordsTotal(0L);
			resultVoPaging.setData(new ArrayList<ClaimSearchResultVo>());
		}else{
			SimpleDateFormat sf = new SimpleDateFormat(DateUtil.DATE_PATTERN_VIEW_DDMMYYYY + " HH:mm:ss");
			SimpleDateFormat sfShort = new SimpleDateFormat(DateUtil.DATE_PATTERN_VIEW_DDMMYYYY);
			
//			resultVoPaging = tblClaimRecoveryService.searchPaging(jobDateStart, jobDateEnd, insuranceId,
//					totalDayOfMaturity,claimTypeId,claimNumber,jobStatusId, start, length);
			
		}

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(resultVoPaging);
		return json;
	}
}
