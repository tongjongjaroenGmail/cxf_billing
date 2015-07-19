package com.metasoft.claim.controller.ajax;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.metasoft.claim.bean.paging.ClaimSearchResultVoPaging;
import com.metasoft.claim.controller.vo.StatusResponse;
import com.metasoft.claim.service.claim.ClaimService;
import com.metasoft.claim.service.impl.DownloadService;
import com.metasoft.claim.service.impl.ExporterService;
import com.metasoft.claim.service.impl.TokenService;
import com.metasoft.claim.util.DateToolsUtil;

@Controller
@RequestMapping("/report")
public class ReportAjaxController {
	@Autowired
	private ClaimService claimService;

	@Autowired
	private DownloadService downloadService;

	@Autowired
	private TokenService tokenService;


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
		
		ClaimSearchResultVoPaging results = claimService.searchPaging(txtJobDateStart, txtJobDateEnd, selInsurance, txtTotalDayOfMaturity, selClaimType, 
				txtClaimNumber, selJobStatus, 0, 65000, null);

		
		downloadService.download(
				ExporterService.EXTENSION_TYPE_EXCEL, 
				"work", 
				session.getServletContext().getRealPath("/report/work"), 
				new HashMap(), 
				results.getData(),
				token, 
				response);
	}
}