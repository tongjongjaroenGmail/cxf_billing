package com.metasoft.claim.controller.ajax;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.metasoft.claim.bean.UploadedFile;
import com.metasoft.claim.controller.vo.ImportError;
import com.metasoft.claim.controller.vo.ResultVo;
import com.metasoft.claim.model.SecUser;
import com.metasoft.claim.service.claim.ClaimImportService;

@Controller
public class ClaimImportAjaxController {
	@Autowired
	private ClaimImportService claimImportService;

	@RequestMapping(value = "/claim/import/upload", method = RequestMethod.POST)
	public @ResponseBody String upload(MultipartHttpServletRequest request, HttpServletResponse response) throws Exception {
		ResultVo resultVo = new ResultVo();

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		// 0. notice, we have used MultipartHttpServletRequest

		// 1. get the files from the request object
		Iterator<String> itr = request.getFileNames();

		MultipartFile mpf = request.getFile(itr.next());
		// System.out.println(mpf.getOriginalFilename() + " uploaded!");

		List<ImportError> errorClaimNumbers = new ArrayList<ImportError>();

		UploadedFile ufile = new UploadedFile();
		// just temporary save file info into ufile
		try {
			ufile.length = mpf.getBytes().length;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ufile.bytes = mpf.getBytes();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ufile.type = mpf.getContentType();
		ufile.name = mpf.getOriginalFilename();

		errorClaimNumbers = claimImportService.saveFromFile(ufile, (SecUser) request.getSession().getAttribute("loginUser"));

		resultVo.setResult(errorClaimNumbers);
		resultVo.setMessage("บันทึกข้อมูลเรียบร้อยแล้ว");
		
		String json = gson.toJson(resultVo);
		return json;
	}
}