/**
 * 
 */
package com.metasoft.claim.controller.ajax;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.metasoft.claim.controller.vo.ClaimSaveResultVo;
import com.metasoft.claim.controller.vo.ClaimSaveVo;
import com.metasoft.claim.controller.vo.ClaimSearchResultVo;
import com.metasoft.claim.controller.vo.ResultVo;
import com.metasoft.claim.dao.UserInsuranceDao;
import com.metasoft.claim.dao.security.UserDao;
import com.metasoft.claim.model.SecUser;
import com.metasoft.claim.model.TblClaimRecovery;
import com.metasoft.claim.model.TblUserInsurance;
import com.metasoft.claim.service.claim.ClaimService;

@Controller
public class ClaimAjaxController extends BaseAjaxController {
	@Autowired
	private ClaimService claimService;
	
	@Autowired
	private UserInsuranceDao userInsuranceDao;
	
	@Autowired
	private UserDao userDao;

	@RequestMapping(value = "/claim/search", method = RequestMethod.POST)
	public @ResponseBody
	String search(Model model,
			@RequestParam(required = false) String paramJobDateStart,
			@RequestParam(required = false) String paramJobDateEnd,
			@RequestParam(required = false) String paramPartyInsuranceId,
			@RequestParam(required = false) String paramTotalDayOfMaturity,
			@RequestParam(required = false) String paramClaimTypeId,
			@RequestParam(required = false) String paramClaimNumber,
			@RequestParam(required = false) String paramJobStatusId,
			@RequestParam(required = false) String paramFirstTime,
			
			@RequestParam(required = true) Integer draw,
			@RequestParam(required = true) Integer start,
			@RequestParam(required = true) Integer length) throws ParseException {
		ClaimSearchResultVoPaging resultPaging = new ClaimSearchResultVoPaging();
		resultPaging.setDraw(++draw);
		if(new Boolean(paramFirstTime)){		
			resultPaging.setRecordsFiltered(0L);
			resultPaging.setRecordsTotal(0L);
			resultPaging.setData(new ArrayList<ClaimSearchResultVo>());
		}else{
			resultPaging = claimService.searchPaging(paramJobDateStart, paramJobDateEnd, paramPartyInsuranceId, paramTotalDayOfMaturity, paramClaimTypeId, 
					paramClaimNumber, paramJobStatusId, start, length);	
		}

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(resultPaging);
		return json;
	}
	
	@RequestMapping(value = "/claim/find/{id}", method = RequestMethod.GET)
    public @ResponseBody String find(Model model,  @PathVariable("id") int  id,HttpSession session) throws ParseException
    {
		ResultVo resultVo = new ResultVo();
    	Gson gson = new GsonBuilder().setPrettyPrinting().create();
    	
    	ClaimSaveVo claimVo = claimService.findById(id , (SecUser)session.getAttribute("loginUser"));		
    	resultVo.setResult(claimVo);

    	String json = gson.toJson(resultVo);
		return json;
    }
	
	@RequestMapping(value = "/claim/searchUserFromInsurance/{id}", method = RequestMethod.GET)
    public @ResponseBody String searchUserFromInsurance(Model model,  @PathVariable("id") int insuranceId) throws ParseException
    {
		List<SecUser> users = new ArrayList<SecUser>();
    	Gson gson = new GsonBuilder().setPrettyPrinting().create();
    	
    	List<TblUserInsurance> tblUserInsurances = userInsuranceDao.searchByInsuranceId(insuranceId);		
    	
    	for (TblUserInsurance tblUserInsurance : tblUserInsurances) {
    		users.add(userDao.findById(tblUserInsurance.getId().getUserId()));
		}

    	String json = gson.toJson(users);
		return json;
    }
	
	@RequestMapping(value = "/claim/save", method = RequestMethod.POST,headers = { "Content-type=application/json" })
    public @ResponseBody String save(Model model,  @RequestBody ClaimSaveVo saveVo,HttpSession session) throws ParseException
    {
		ResultVo resultVo = new ResultVo();
    	Gson gson = new GsonBuilder().setPrettyPrinting().create();
    	
    	if(StringUtils.isNotBlank(saveVo.getTxtClaimId())){
    		TblClaimRecovery claim = claimService.findById(Integer.parseInt(saveVo.getTxtClaimId()));
    		if(claim.getJobStatus().getId() == 2 || claim.getJobStatus().getId() == 3){
    			resultVo.setMessage("ไม่สามารถบันทึกข้อมูลได้เนื่องจากสถานะเป็นปิดงานหรือยกเลิกแล้ว");
    			resultVo.setError(true);
    		}
    	}
    	
    	if(!resultVo.isError()){
    		TblClaimRecovery result = claimService.save(saveVo, (SecUser)session.getAttribute("loginUser"));
        	
        	ClaimSaveResultVo claimSaveResultVo = new ClaimSaveResultVo();
        	claimSaveResultVo.setId(result.getId().toString());
        	claimSaveResultVo.setJobNo(result.getJobNo());
        	resultVo.setResult(claimSaveResultVo);
        	resultVo.setMessage("บันทึกข้อมูลเรียบร้อยแล้ว");
    	}

    	String json = gson.toJson(resultVo);
		return json;
    }
}
