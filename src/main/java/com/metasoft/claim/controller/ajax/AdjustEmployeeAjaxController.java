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
import com.metasoft.claim.controller.vo.AdjustEmployeeVo;
import com.metasoft.claim.controller.vo.ResultVo;
import com.metasoft.claim.model.Employee;
import com.metasoft.claim.service.EmployeeService;
import com.metasoft.claim.service.RoleService;
import com.metasoft.claim.service.WorkingPolicyService;

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
