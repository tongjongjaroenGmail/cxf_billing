/**
 * 
 */
package com.metasoft.claim.controller.ajax;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.ldap.userdetails.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.metasoft.claim.controller.vo.AdjustLeaveVo;
import com.metasoft.claim.controller.vo.ResultVo;
import com.metasoft.claim.model.Employee;
import com.metasoft.claim.model.LeaveProfile;
import com.metasoft.claim.service.EmployeeService;
import com.metasoft.claim.service.LeaveProfileService;

@Controller
public class AdjustLeaveAjaxController extends BaseAjaxController {
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private LeaveProfileService leaveProfileService;

	@RequestMapping(value = "/adjust-leave/adjustLeaveList", method = RequestMethod.GET, headers = { "Content-type=application/json" })
	public @ResponseBody
	String searchAdjustLeaveList(Model model) throws ParseException {
		List<Employee> employees = employeeService.findAllStatusNotResign();

		List<AdjustLeaveVo> adjustLeaveVos = new ArrayList<AdjustLeaveVo>();

		for (Employee employee : employees) {
			for (LeaveProfile leaveProfile  : employee.getLeaveProfiles()) {
				AdjustLeaveVo adjustLeaveVo = new AdjustLeaveVo();
				adjustLeaveVo.setEmployeeId(employee.getId());
				adjustLeaveVo.setEmployeeName(employee.getFullName());
				adjustLeaveVo.setDepartmentId(employee.getDepartment().getId());
				
				adjustLeaveVo.setCarryForward(leaveProfile.getCarryForward()); 
				adjustLeaveVo.setLeaveAvailable(leaveProfile.getLeaveAvailable()); 
				adjustLeaveVo.setNote(leaveProfile.getNote());
				adjustLeaveVo.setTotalLeave(adjustLeaveVo.getCarryForward() + adjustLeaveVo.getLeaveAvailable());
				adjustLeaveVo.setLeaveProfileId(leaveProfile.getId());
				adjustLeaveVo.setYear(leaveProfile.getYear());
				
				adjustLeaveVos.add(adjustLeaveVo);
			}
		}

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json2 = gson.toJson(adjustLeaveVos);
		return json2;
	}

	@RequestMapping(value = "/adjust-leave/update", method = RequestMethod.POST, headers = { "Content-type=application/x-www-form-urlencoded;" })
	public @ResponseBody
	String update(Model model , AdjustLeaveVo adjustLeaveVo, HttpSession session) throws ParseException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		ResultVo resultVo = new ResultVo();
		
		SecurityContextImpl securityContextImpl = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
		Employee loginEmployee = employeeService.findByCode(((Person)securityContextImpl.getAuthentication().getPrincipal()).getUsername());
		
		LeaveProfile leaveProfile = leaveProfileService.findById(adjustLeaveVo.getLeaveProfileId());
		leaveProfile.setCarryForward(adjustLeaveVo.getCarryForward());
		leaveProfile.setLeaveAvailable(adjustLeaveVo.getLeaveAvailable());
		leaveProfile.setNote(adjustLeaveVo.getNote());
		leaveProfile.setUpdateBy(loginEmployee);
		leaveProfile.setUpdateDate(new Date());
		leaveProfileService.saveOrUpdate(leaveProfile);
		resultVo.setMessage("");

		String json2 = gson.toJson(resultVo);
		return json2;
	}
}
