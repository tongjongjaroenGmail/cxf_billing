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
import com.metasoft.claim.controller.vo.LaborResultVo;
import com.metasoft.claim.controller.vo.TrackingSearchResultVo;
import com.metasoft.claim.service.claim.ReportService;
import com.metasoft.claim.service.impl.DownloadService;
import com.metasoft.claim.service.impl.ExporterService;
import com.metasoft.claim.service.impl.TokenService;
import com.metasoft.claim.service.report.BillingService;
import com.metasoft.claim.util.ThaiBaht;

@Controller
@RequestMapping("/report/labor")
public class LaborAjaxController extends BaseAjaxController {
	@Autowired
	private ReportService reportService;

	@Autowired
	private DownloadService downloadService;

	@Autowired
	private TokenService tokenService;


	@RequestMapping(value = "/export", method = RequestMethod.POST)
	public void export(@RequestParam(required = true) Integer[] chk,
			@RequestParam(required = false) String token, HttpSession session, HttpServletResponse response) throws ServletException,
			IOException, JRException, Exception {

		List<LaborResultVo> results = reportService.searchExportLabor(chk);

		if (!results.isEmpty()) {
			List<String> fileList = new ArrayList<String>();
			List<InputStream> inputStreams = new ArrayList<InputStream>();

			float totalWage = 0;
			List<LaborResultVo> exports = new ArrayList<LaborResultVo>();
			
			int i = 1;
			HashMap<String,Object> param = new HashMap<String,Object>();
			
			ThaiBaht thaiBaht = new ThaiBaht();
			for (LaborResultVo result : results) {
				totalWage += result.getLaborPrice();
				if (totalWage > 30000) {
					param = new HashMap<String,Object>();
					param.put("totalWage", totalWage - result.getLaborPrice());
					param.put("totalWageThai", thaiBaht.getText(totalWage - result.getLaborPrice()));
					ByteArrayOutputStream reportOut = downloadService.generateReportXLS(null,
							session.getServletContext().getRealPath("/report/labor"), ExporterService.EXTENSION_TYPE_EXCEL,
							param, "labor", exports);
					exports = new ArrayList<LaborResultVo>();
					totalWage = result.getLaborPrice();
					if (reportOut != null) {
						InputStream in = new ByteArrayInputStream(reportOut.toByteArray());
						inputStreams.add(in);
						fileList.add("labor" + i++);
					}
				}

				exports.add(result);
			}

			if (!exports.isEmpty()) {
				param = new HashMap<String,Object>();
				param.put("totalWage", totalWage);
				param.put("totalWageThai", thaiBaht.getText(totalWage));
				ByteArrayOutputStream reportOut = downloadService.generateReportXLS(null,
						session.getServletContext().getRealPath("/report/labor"), ExporterService.EXTENSION_TYPE_EXCEL, param,
						"labor", exports);
				exports = new ArrayList<LaborResultVo>();

				if (reportOut != null) {
					InputStream in = new ByteArrayInputStream(reportOut.toByteArray());
					inputStreams.add(in);
					fileList.add("labor" + i++ + ".xls");
				}
			}
			String header = "";
			header = "attachment; filename=labor.zip";
			OutputStream outs = null;
			header = new String(header.getBytes("UTF-8"), "ISO8859_1");
			outs = response.getOutputStream();
			response.setHeader("Content-Disposition", header);
			response.setContentType("application/ms-excel");
			downloadService.writeZipFile(fileList, inputStreams, outs, token);
		}
	}
}
