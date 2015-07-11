/**
 * 
 */
package com.metasoft.claim.controller.ajax;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.ldap.userdetails.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.metasoft.claim.controller.EmployeeJsonObject;
import com.metasoft.claim.controller.vo.EmployeeInfoVo;
import com.metasoft.claim.controller.vo.EmployeeLeaveVo;
import com.metasoft.claim.controller.vo.EmployeeSubLeaveVo;
import com.metasoft.claim.controller.vo.ResultVo;
import com.metasoft.claim.dao.LeaveTypeDao;
import com.metasoft.claim.model.Employee;
import com.metasoft.claim.model.EmployeeStatus;
import com.metasoft.claim.model.Leave;
import com.metasoft.claim.model.LeaveProfile;
import com.metasoft.claim.model.LeaveRequest;
import com.metasoft.claim.model.LeaveRequestStatus;
import com.metasoft.claim.model.LeaveType;
import com.metasoft.claim.service.EmployeeService;
import com.metasoft.claim.service.LeaveRequestService;
import com.metasoft.claim.service.LeaveService;
import com.metasoft.claim.service.RoleService;
import com.metasoft.claim.util.DateUtil;

/**
 * @author 
 * 
 */
@Controller
public class EmployeeAjaxController extends BaseAjaxController {
	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private LeaveRequestService leaveRequestService;
	
	@Autowired
	private LeaveService leaveService;
	
	@Autowired
    private LeaveTypeDao leaveTypeDao;
	
	@Autowired
    private RoleService roleService;

	@RequestMapping(value = "/employee/view", method = RequestMethod.GET, produces = { "application/json" })
	public @ResponseBody
	String view(Model model, HttpSession session) throws IllegalAccessException,
			InvocationTargetException {
		SimpleDateFormat format = new SimpleDateFormat(DateUtil.DATE_PATTERN_VIEW_DDMMYYYY);
		
		SecurityContextImpl securityContextImpl = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
		Employee employee = employeeService.findByCode(((Person)securityContextImpl.getAuthentication().getPrincipal()).getUsername());
	
		LeaveProfile leaveProfile = employee.getLeaveProfileInCurrentYear();
	
		EmployeeInfoVo employeeInfoVo = new EmployeeInfoVo();
		employeeInfoVo.setFirstname(StringUtils.trimToEmpty(employee.getFirstname()));
		employeeInfoVo.setLastname(StringUtils.trimToEmpty(employee.getLastname()));
		employeeInfoVo.setEmployeeId(StringUtils.trimToEmpty(employee.getEmployeeId()));
		employeeInfoVo.setJoinDate(employee.getJoinDate() == null?"":format.format(employee.getJoinDate()));
		employeeInfoVo.setAvailableLeave(leaveProfile.getLeaveAvailable() == null ? 0 : leaveProfile
				.getLeaveAvailable());
		employeeInfoVo.setCarryForward(leaveProfile.getCarryForward() == null ? 0 : leaveProfile.getCarryForward());
		employeeInfoVo.setTakenAnnualLeave(leaveService.calcUsedLeaveDaysInCurrentYear(employee.getId(), 1));
		employeeInfoVo.setTakenSickLeave(leaveService.calcUsedLeaveDaysInCurrentYear(employee.getId(), 2));
		
		List<EmployeeStatus> employeeStatuses = new ArrayList<EmployeeStatus>();
		employeeStatuses.add(EmployeeStatus.ACTIVE);
		employeeInfoVo.setLineManagers(new ArrayList<String>());
		List<Employee> employeeManagers = employeeService.searchByRoleAndDepartmentAndStatuses(roleService.findById(2), employee.getDepartment(), employeeStatuses);
		for (Employee employeeManager : employeeManagers) {
			employeeInfoVo.getLineManagers().add(employeeManager.getFullName());
		}
		
		List<LeaveType> leaveTypes = leaveTypeDao.findAll();
		List<Integer> leaveTypeIds = new ArrayList<Integer>();
		for (LeaveType leaveType : leaveTypes) {
			if(leaveType.getId().intValue() != 1 && leaveType.getId().intValue() != 2)
				leaveTypeIds.add(leaveType.getId().intValue());
		}	
		
		employeeInfoVo.setTakenOtherLeave(leaveService.calcUsedLeaveDaysInCurrentYear(employee.getId(), leaveTypeIds));

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json2 = gson.toJson(employeeInfoVo);

		return json2;
	}

	@RequestMapping(value = "/employee/listBy", method = RequestMethod.GET, produces = { "application/json" })
	public @ResponseBody
	String list(Model model, HttpSession session) {
		SimpleDateFormat format = new SimpleDateFormat(DateUtil.DATE_PATTERN_VIEW_DDMMYYYY);

		SecurityContextImpl securityContextImpl = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
		Employee employee = employeeService.findByCode(((Person)securityContextImpl.getAuthentication().getPrincipal()).getUsername());
		
		List<LeaveRequest> leaveRequests = employee.getLeaveRequests();
		List<EmployeeLeaveVo> employeeLeaveVos = new ArrayList<EmployeeLeaveVo>();
		for (LeaveRequest leaveRequest : leaveRequests) {
			EmployeeLeaveVo employeeLeaveVo = new EmployeeLeaveVo();

			List<Leave> leaves = leaveRequest.getLeaves();
			employeeLeaveVo.setFromDate(format.format(leaves.get(0).getDate()));
			employeeLeaveVo.setToDate(format.format(leaves.get(leaves.size() - 1).getDate()));

			if(leaveRequest.getLeaveType().getParent() != null){
				employeeLeaveVo.setLeaveType(leaveRequest.getLeaveType().getParent().getName() + " (" + leaveRequest.getLeaveType().getName() + ")");
			}else{
				employeeLeaveVo.setLeaveType(leaveRequest.getLeaveType().getName());
			}
			
			employeeLeaveVo.setPeriod(leaveRequest.getTotalNoDays());
			employeeLeaveVo.setStatus(leaveRequest.getStatus().getName());
			employeeLeaveVo.setStatusId(leaveRequest.getStatus().getId());

			String considerEmployeeName = "";
			employeeLeaveVo.setCancelStatus(true);
			if (leaveRequest.getStatus().equals(LeaveRequestStatus.NEW)) {
				considerEmployeeName = leaveRequest.getCreateBy().getFirstname() + " "
						+ leaveRequest.getCreateBy().getLastname();
			}else if (leaveRequest.getStatus().equals(LeaveRequestStatus.VIEWED)) {
				considerEmployeeName = leaveRequest.getUpdateBy().getFirstname() + " "
						+ leaveRequest.getUpdateBy().getLastname();
			}else if (leaveRequest.getStatus().equals(LeaveRequestStatus.APPROVED)) {
				considerEmployeeName = leaveRequest.getApproveEmployee().getFirstname() + " "
						+ leaveRequest.getApproveEmployee().getLastname();
				employeeLeaveVo.setCancelStatus(false);
			} else if (leaveRequest.getStatus().equals(LeaveRequestStatus.CANCELLED)) {
				considerEmployeeName = leaveRequest.getCancelEmployee().getFirstname() + " "
						+ leaveRequest.getCancelEmployee().getLastname();
				employeeLeaveVo.setCancelStatus(false);
			} else if (leaveRequest.getStatus().equals(LeaveRequestStatus.REJECTED)) {
				considerEmployeeName = leaveRequest.getRejectEmployee().getFirstname() + " "
						+ leaveRequest.getRejectEmployee().getLastname();
				employeeLeaveVo.setCancelStatus(false);
			}
			employeeLeaveVo.setBy(considerEmployeeName);
			employeeLeaveVo.setLeaveRequestId(leaveRequest.getId());
			employeeLeaveVo.setRequestNote(StringUtils.trimToEmpty(leaveRequest.getRequestNote()));
			employeeLeaveVo.setRejectNote(leaveRequest.getRejectNote());
			employeeLeaveVo.setEmployeeSubLeaveVos(new ArrayList<EmployeeSubLeaveVo>());
			for (Leave leave : leaves) {
				EmployeeSubLeaveVo employeeSubLeaveVo = new EmployeeSubLeaveVo();
				employeeSubLeaveVo.setDate(format.format(leave.getDate()));
				employeeSubLeaveVo.setAm(leave.getAm());
				employeeSubLeaveVo.setPm(leave.getPm());
				employeeSubLeaveVo.setFromTime(leave.getFromTime().toString());
				employeeSubLeaveVo.setToTime(leave.getToTime().toString());
				employeeLeaveVo.getEmployeeSubLeaveVos().add(employeeSubLeaveVo);
			}

			employeeLeaveVos.add(employeeLeaveVo);
		}

		EmployeeJsonObject empJson = new EmployeeJsonObject();
		empJson.setiTotalDisplayRecords(employeeLeaveVos.size());
		empJson.setiTotalRecords(employeeLeaveVos.size());
		empJson.setAaData(employeeLeaveVos);

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json2 = gson.toJson(empJson);

		return json2;
	}

	@RequestMapping(value = "/employee/cancelLeave", method = RequestMethod.POST, headers = { "Content-type=application/json" })
	public @ResponseBody
	String cancelLeave(Model model, @RequestBody EmployeeLeaveVo employeeLeaveVo, HttpSession session) {
		ResultVo resultVo = new ResultVo();
		SecurityContextImpl securityContextImpl = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
		Employee loginEmployee = employeeService.findByCode(((Person)securityContextImpl.getAuthentication().getPrincipal()).getUsername());
		
		LeaveRequest leaveRequest = leaveRequestService.findById(employeeLeaveVo.getLeaveRequestId());
		resultVo = leaveRequestService.validateBeforeUpdate(leaveRequest,-1, loginEmployee);
		
		if (!resultVo.isError()) {	
			leaveRequestService.delete(leaveRequest);
			resultVo.setMessage("");
		}
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json2 = gson.toJson(resultVo);

		return json2;
	}

	@RequestMapping(value = "/employee/delete", method = RequestMethod.POST)
	public void delete(Model model, @RequestParam(required = false) Integer id) {

	}
}
