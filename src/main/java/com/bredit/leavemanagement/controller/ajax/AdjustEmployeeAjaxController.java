/**
 * 
 */
package com.bredit.leavemanagement.controller.ajax;

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

import com.bredit.leavemanagement.controller.vo.AdjustEmployeeVo;
import com.bredit.leavemanagement.controller.vo.ResultVo;
import com.bredit.leavemanagement.model.Employee;
import com.bredit.leavemanagement.service.EmployeeService;
import com.bredit.leavemanagement.service.RoleService;
import com.bredit.leavemanagement.service.WorkingPolicyService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
public class AdjustEmployeeAjaxController extends BaseAjaxController {
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private WorkingPolicyService workingPolicyService;
	
	@RequestMapping(value = "/adjust-employee/adjustEmployeeList", method = RequestMethod.GET, headers = { "Content-type=application/json" })
	public @ResponseBody
	String searchAdjustEmployeeList(Model model) throws ParseException {
		List<Employee> employees = employeeService.findAllStatusNotResign();

		List<AdjustEmployeeVo> adjustEmployeeVos = new ArrayList<AdjustEmployeeVo>();

		for (Employee employee : employees) {
			AdjustEmployeeVo adjustEmployeeVo = new AdjustEmployeeVo();
			adjustEmployeeVo.setEmployeeId(employee.getId());
			adjustEmployeeVo.setEmployeeName(employee.getFullName());
			adjustEmployeeVo.setDepartmentId(employee.getDepartment().getId());
			adjustEmployeeVo.setRoleId(employee.getRole().getId());
			adjustEmployeeVo.setWorkingPolicyId(employee.getWorkingPolicy().getId());
			
			adjustEmployeeVos.add(adjustEmployeeVo);
		}

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json2 = gson.toJson(adjustEmployeeVos);
		return json2;
	}

	@RequestMapping(value = "/adjust-employee/update", method = RequestMethod.POST, headers = { "Content-type=application/x-www-form-urlencoded;" })
	public @ResponseBody
	String update(Model model , AdjustEmployeeVo adjustEmployeeVo, HttpSession session) throws ParseException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		ResultVo resultVo = new ResultVo();
		
		SecurityContextImpl securityContextImpl = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
		Employee loginEmployee = employeeService.findByCode(((Person)securityContextImpl.getAuthentication().getPrincipal()).getUsername());
			
		Employee employee = employeeService.findById(adjustEmployeeVo.getEmployeeId());
		employee.setRole(roleService.findById(adjustEmployeeVo.getRoleId()));
		employee.setWorkingPolicy(workingPolicyService.findById(adjustEmployeeVo.getWorkingPolicyId()));
		employee.setUpdateBy(loginEmployee);
		employee.setUpdateDate(new Date());
		employeeService.saveOrUpdate(employee);
		resultVo.setMessage("");

		String json2 = gson.toJson(resultVo);
		return json2;
	}
}
