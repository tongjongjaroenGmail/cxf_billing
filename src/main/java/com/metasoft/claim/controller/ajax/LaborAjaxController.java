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

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
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
import com.metasoft.claim.model.SecUser;
import com.metasoft.claim.service.claim.ReportService;
import com.metasoft.claim.service.impl.DownloadService;
import com.metasoft.claim.service.impl.ExporterService;
import com.metasoft.claim.service.impl.TokenService;
import com.metasoft.claim.service.report.BillingService;
import com.metasoft.claim.service.security.UserService;
import com.metasoft.claim.util.ThaiBaht;

@Controller
@RequestMapping("/labor")
public class LaborAjaxController extends BaseAjaxController {
	@Autowired
	private ReportService reportService;

	@Autowired
	private DownloadService downloadService;

	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UserService userService;


	@RequestMapping(value = "/export", method = RequestMethod.POST)
	public void export(@RequestParam(required = true) Integer[] chk,
					  @RequestParam(required = false) String token,
					  HttpSession session,
					  HttpServletResponse response) throws ServletException,IOException, JRException, Exception {

		System.out.println(">>>>>>>> labor export >> chk = "+chk.length);
		
		
		List<LaborResultVo> results = reportService.searchExportLabor(chk);
		System.out.println(">>>>>>>> results.size() = "+results.size());
		
		List<LaborResultVo> exports = new ArrayList<LaborResultVo>();
		
		float totalAmount = 0;
		ThaiBaht thaiBaht = new ThaiBaht();
		for (LaborResultVo result : results) {
			float tempLaborPrice = Float.valueOf(result.getLaborPrice());
			totalAmount = totalAmount + tempLaborPrice;
			System.out.println(">>>>>> totalAmount = "+totalAmount);
			exports.add(result);
		}
		
		HashMap<String,Object> params = new HashMap<String, Object>();
		
		if(exports.get(0).getAgentId() != 0){
			SecUser agent = userService.findById(exports.get(0).getAgentId());
			params.put("agentName", StringUtils.trimToEmpty(agent.getName())  + " " + StringUtils.trimToEmpty(agent.getLastName()));
		}

		params.put("totalAmountThai", thaiBaht.getText(totalAmount));
		
		
			System.out.println(">>> totalAmountThai = "+thaiBaht.getText(totalAmount));
			downloadService.download(ExporterService.EXTENSION_TYPE_EXCEL, "labor", session.getServletContext().getRealPath("/report/labor"),
					params,
				exports, 
				token, 
				response);
		}

//
//		if (!results.isEmpty()) {
//			List<String> fileList = new ArrayList<String>();
//			List<InputStream> inputStreams = new ArrayList<InputStream>();
//
//			float totalWage = 0;
//			List<LaborResultVo> exports = new ArrayList<LaborResultVo>();
//			
//			int i = 1;
//			HashMap<String,Object> param = new HashMap<String,Object>();
//			
//			ThaiBaht thaiBaht = new ThaiBaht();
//			boolean isContinue = false;
//			for (LaborResultVo result : results) {
//				isContinue = false;
//				totalWage += result.getLaborPrice();
//				if (totalWage > 30000) {
//					param = new HashMap<String,Object>();
//					if(exports.size() == 0){
//						param.put("totalWage", totalWage);
//						param.put("totalWageThai", thaiBaht.getText(totalWage));
//						exports.add(result);
//						totalWage = 0;
//						isContinue = true;
//					}else{
//						param.put("totalWage", totalWage - result.getLaborPrice());
//						param.put("totalWageThai", thaiBaht.getText(totalWage - result.getLaborPrice()));
//						totalWage = result.getLaborPrice();
//					}
//	
//					ByteArrayOutputStream reportOut = downloadService.generateReportXLS(null,
//							session.getServletContext().getRealPath("/report/labor"), ExporterService.EXTENSION_TYPE_EXCEL,
//							param, "labor", exports);
//					exports = new ArrayList<LaborResultVo>();
//					
//					if (reportOut != null) {
//						InputStream in = new ByteArrayInputStream(reportOut.toByteArray());
//						inputStreams.add(in);
//						fileList.add("labor" + i++ + ".xls");
//					}
//					if(isContinue){
//						continue;
//					}
//				}
//
//				exports.add(result);
//			}
//
//			if (!exports.isEmpty()) {
//				param = new HashMap<String,Object>();
//				param.put("totalWage", totalWage);
//				param.put("totalWageThai", thaiBaht.getText(totalWage));
//				ByteArrayOutputStream reportOut = downloadService.generateReportXLS(null,
//						session.getServletContext().getRealPath("/report/labor"), ExporterService.EXTENSION_TYPE_EXCEL, param,
//						"labor", exports);
//				exports = new ArrayList<LaborResultVo>();
//
//				if (reportOut != null) {
//					InputStream in = new ByteArrayInputStream(reportOut.toByteArray());
//					inputStreams.add(in);
//					fileList.add("labor" + i++ + ".xls");
//				}
//			}
//			String header = "";
//
//			OutputStream outs = null;	
//			outs = response.getOutputStream();
//
//			if(fileList.size() > 1){
//				header = "attachment; filename=labor.zip";
//			}else{
//				header = "attachment; filename=labor.xls";
//			}		
//			
//			header = new String(header.getBytes("UTF-8"), "ISO8859_1");
//			response.setHeader("Content-Disposition", header);
//			response.setContentType("application/ms-excel");
//			
//			if(fileList.size() > 1){
//				downloadService.writeZipFile(fileList, inputStreams, outs, token);
//			}else{		
//				byte[] bytes = IOUtils.toByteArray(inputStreams.get(0));
//				ByteArrayOutputStream baos = new ByteArrayOutputStream(bytes.length);
//				baos.write(bytes, 0, bytes.length);
//				downloadService.write(token, response, baos);
//				baos.close();
//			}		
//		}
//	}
}
