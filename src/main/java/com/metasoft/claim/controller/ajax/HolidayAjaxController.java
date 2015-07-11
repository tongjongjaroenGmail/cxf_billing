/**
 * 
 */
package com.metasoft.claim.controller.ajax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.metasoft.claim.controller.vo.HolidaySaveVo;
import com.metasoft.claim.controller.vo.ResultVo;
import com.metasoft.claim.model.Holiday;
import com.metasoft.claim.service.HolidayService;
import com.metasoft.claim.util.DateUtil;

/**
 * @author 
 * 
 */
@Controller
public class HolidayAjaxController extends BaseAjaxController
{
	@Autowired
	private HolidayService holidayService;
	
    @RequestMapping(value = "/holiday/view", method = RequestMethod.GET)
    public void view(Model model, @RequestParam(required = true) Integer id)
    {
        // Find by PK
    }

    @RequestMapping(value = "/holiday/list", method = RequestMethod.GET, produces = { "application/json" })
    public @ResponseBody
    String list(Model model)
    {
        // Find all
        return null;
    }

    @RequestMapping(value = "/holiday/listBy", method = RequestMethod.GET, produces = { "application/json" })
    public @ResponseBody
    String list(Model model, @RequestParam Map<String, String> listByParameters)
    {
        // Find by criteria
        Holiday criteria = new Holiday();

        return null;
    }

    @RequestMapping(value = "/holiday/create", method = RequestMethod.POST)
    public void create(Model model, Holiday holiday)
    {

    }

    @RequestMapping(value = "/holiday/save", method = RequestMethod.POST,headers = { "Content-type=application/json" })
    public @ResponseBody String save(Model model,  @RequestBody HolidaySaveVo holidaySaveVo) throws ParseException
    {
    	Gson gson = new GsonBuilder().setPrettyPrinting().create();
		ResultVo resultVo = new ResultVo();
		
		SimpleDateFormat sfVo = new SimpleDateFormat(DateUtil.DATE_PATTERN_VIEW_DDMMYYYY);
		
		if(StringUtils.isNotBlank(holidaySaveVo.getOldStart()) ){
			Holiday holiday = holidayService.findByDate(sfVo.parse(holidaySaveVo.getOldStart()));
			holidayService.delete(holiday);
		}
		if(StringUtils.isNotBlank(holidaySaveVo.getNewStart()) ){
			Holiday holiday = new Holiday();
	    	holiday.setName(holidaySaveVo.getNewTitle());
	    	holiday.setDate(sfVo.parse(holidaySaveVo.getNewStart()));
	    	holidayService.save(holiday);
		}

    	resultVo.setMessage("");
    	
    	String json2 = gson.toJson(resultVo);
		return json2;
    }

    @RequestMapping(value = "/holiday/delete", method = RequestMethod.POST)
    public void delete(Model model, @RequestParam(required = false) Integer id)
    {

    }
}
