/**
 * 
 */
package com.metasoft.claim.controller.ajax;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.metasoft.claim.bean.paging.BillingSearchResultVoPaging;
import com.metasoft.claim.bean.report.BillingExportResult;
import com.metasoft.claim.controller.vo.BillingSearchResultVo;
import com.metasoft.claim.service.impl.DownloadService;
import com.metasoft.claim.service.impl.ExporterService;
import com.metasoft.claim.service.impl.TokenService;
import com.metasoft.claim.service.report.BillingService;
import com.metasoft.claim.util.ThaiBaht;

@Controller
@RequestMapping("/report/billing")
public class BillingAjaxController extends BaseAjaxController {
	@Autowired
	private BillingService billingService;

	@Autowired
	private DownloadService downloadService;

	@Autowired
	private TokenService tokenService;

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public @ResponseBody String search(Model model, @RequestParam(required = false) String paramCloseDateStart,
			@RequestParam(required = false) String paramCloseDateEnd, @RequestParam(required = false) String paramPartyInsuranceId,
			@RequestParam(required = false) String paramClaimTypeId, @RequestParam(required = true) String paramFirstTime,

			@RequestParam(required = true) Integer draw, @RequestParam(required = true) Integer start,
			@RequestParam(required = true) Integer length) throws ParseException {

		BillingSearchResultVoPaging resultPaging = new BillingSearchResultVoPaging();
		resultPaging.setDraw(++draw);
		if (new Boolean(paramFirstTime)) {
			resultPaging.setRecordsFiltered(0L);
			resultPaging.setRecordsTotal(0L);
			resultPaging.setData(new ArrayList<BillingSearchResultVo>());
		} else {
			resultPaging = billingService.searchPaging(paramCloseDateStart, paramCloseDateEnd, paramPartyInsuranceId, paramClaimTypeId,
					start, length);
		}

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(resultPaging);
		return json;
	}

	@RequestMapping(value = "/export", method = RequestMethod.POST)
	public void export(@RequestParam(required = true) Integer[] chk,
			@RequestParam(required = false) String token, HttpSession session, HttpServletResponse response) throws ServletException,
			IOException, JRException, Exception {

		List<BillingExportResult> results = billingService.searchExport(chk);

		if (!results.isEmpty()) {
			List<String> fileList = new ArrayList<String>();
			List<InputStream> inputStreams = new ArrayList<InputStream>();

			float totalWage = 0;
			List<BillingExportResult> exports = new ArrayList<BillingExportResult>();
			
			int i = 1;
			HashMap<String,Object> param = new HashMap<String,Object>();
			
			ThaiBaht thaiBaht = new ThaiBaht();
			for (BillingExportResult result : results) {
				totalWage += result.getWage();
				if (totalWage > 30000) {
					param = new HashMap<String,Object>();
					param.put("totalWage", totalWage - result.getWage());
					param.put("totalWageThai", thaiBaht.getText(totalWage - result.getWage()));
					ByteArrayOutputStream reportOut = downloadService.generateReportXLS(null,
							session.getServletContext().getRealPath("/report/billing"), ExporterService.EXTENSION_TYPE_EXCEL,
							param, "billing", exports);
					exports = new ArrayList<BillingExportResult>();
					totalWage = result.getWage();
					if (reportOut != null) {
						InputStream in = new ByteArrayInputStream(reportOut.toByteArray());
						inputStreams.add(in);
						fileList.add("billing" + i++);
					}
				}

				exports.add(result);
			}

			if (!exports.isEmpty()) {
				param = new HashMap<String,Object>();
				param.put("totalWage", totalWage);
				param.put("totalWageThai", thaiBaht.getText(totalWage));
				ByteArrayOutputStream reportOut = downloadService.generateReportXLS(null,
						session.getServletContext().getRealPath("/report/billing"), ExporterService.EXTENSION_TYPE_EXCEL, param,
						"billing", exports);
				exports = new ArrayList<BillingExportResult>();

				if (reportOut != null) {
					InputStream in = new ByteArrayInputStream(reportOut.toByteArray());
					inputStreams.add(in);
					fileList.add("billing" + i++ + ".xls");
				}
			}
			String header = "";
			header = "attachment; filename=billing.zip";
			OutputStream outs = null;
			header = new String(header.getBytes("UTF-8"), "ISO8859_1");
			outs = response.getOutputStream();
			response.setHeader("Content-Disposition", header);
			response.setContentType("application/ms-excel");
			downloadService.writeZipFile(fileList, inputStreams, outs, token);
		}
	}
}
