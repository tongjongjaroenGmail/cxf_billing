/**
 * 
 */
package com.bredit.leavemanagement.controller.ajax;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.joda.time.Days;
import org.joda.time.DurationFieldType;
import org.joda.time.LocalDate;
import org.joda.time.LocalDate.Property;
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

import com.bredit.leavemanagement.bean.paging.EmployeePaging;
import com.bredit.leavemanagement.bean.paging.ViewLeavePaging;
import com.bredit.leavemanagement.controller.vo.ResultVo;
import com.bredit.leavemanagement.controller.vo.ViewLeaveDateDetailVo;
import com.bredit.leavemanagement.controller.vo.ViewLeaveDateVo;
import com.bredit.leavemanagement.controller.vo.ViewLeaveEmployeeVo;
import com.bredit.leavemanagement.controller.vo.ViewLeaveLeaveRequestVo;
import com.bredit.leavemanagement.controller.vo.ViewLeaveLeaveVo;
import com.bredit.leavemanagement.controller.vo.ViewLeaveSaveFormVo;
import com.bredit.leavemanagement.dao.LeaveTypeDao;
import com.bredit.leavemanagement.model.Department;
import com.bredit.leavemanagement.model.Employee;
import com.bredit.leavemanagement.model.EmployeeStatus;
import com.bredit.leavemanagement.model.Holiday;
import com.bredit.leavemanagement.model.Leave;
import com.bredit.leavemanagement.model.LeaveRequest;
import com.bredit.leavemanagement.model.LeaveRequestStatus;
import com.bredit.leavemanagement.model.LeaveType;
import com.bredit.leavemanagement.service.DepartmentService;
import com.bredit.leavemanagement.service.EmployeeService;
import com.bredit.leavemanagement.service.HolidayService;
import com.bredit.leavemanagement.service.LeaveRequestService;
import com.bredit.leavemanagement.service.LeaveService;
import com.bredit.leavemanagement.util.DateUtil;
import com.bredit.leavemanagement.util.LocalDateComparator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
public class ViewLeaveAjaxController extends BaseAjaxController {
	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private LeaveService leaveService;

	@Autowired
	private LeaveRequestService leaveRequestService;

	@Autowired
	private LeaveTypeDao leaveTypeDao;
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private HolidayService holidayService;

	@RequestMapping(value = "/view-leave/viewLeaveList", method = RequestMethod.POST)
	public @ResponseBody
	String viewLeaveList(Model model, 
			@RequestParam(required = true) String startDate,
			@RequestParam(required = true) String endDate,
			@RequestParam(required = false) String employeeName,
			@RequestParam(required = false) String departmentIds,
			@RequestParam(required = true) Integer draw,
			@RequestParam(required = true) Integer start,
			@RequestParam(required = true) Integer length) throws ParseException {
		SimpleDateFormat sf = new SimpleDateFormat(DateUtil.DATE_PATTERN_VIEW_DDMMYYYY + " HH:mm:ss");
		SimpleDateFormat sfShort = new SimpleDateFormat(DateUtil.DATE_PATTERN_VIEW_DDMMYYYY);
		LocalDateComparator localDateComparator = new LocalDateComparator();
		
		List<EmployeeStatus> employeeStatuses = new ArrayList<EmployeeStatus>();
		employeeStatuses.add(EmployeeStatus.ACTIVE);
		
		List<Department> departments = new ArrayList<Department>();
		if(StringUtils.isNotBlank(departmentIds)){
			String[] arrDepartmentId = departmentIds.split(",");
			Integer[] arrDepartmentIdInt = new Integer[arrDepartmentId.length];
			for(int i = 0;i < arrDepartmentId.length;i++)
			{
				arrDepartmentIdInt[i] = Integer.parseInt(arrDepartmentId[i].trim());
			}
			departments = departmentService.findByIds(arrDepartmentIdInt);
		}
		
		EmployeePaging employeePaging = employeeService.searchPagingByFullNameAndDepartmentIdAndStatus(employeeName, departments, employeeStatuses, start, length);

		List<ViewLeaveEmployeeVo> viewLeaveEmployeeVos = new ArrayList<ViewLeaveEmployeeVo>();

		List<LeaveRequestStatus> leaveRequestStatuses = new ArrayList<LeaveRequestStatus>();
		leaveRequestStatuses.add(LeaveRequestStatus.NEW);
		leaveRequestStatuses.add(LeaveRequestStatus.VIEWED);
		leaveRequestStatuses.add(LeaveRequestStatus.APPROVED);
		
		Date _startDate = sf.parse(startDate + " 00:00:00");
		Date _endDate = sf.parse(endDate + " 23:59:59");
		
		LocalDate startLocalDate = new LocalDate(_startDate);
		LocalDate endLocalDate = new LocalDate(_endDate);
		
		List<Holiday> holidays = holidayService.searchByBetweenDate(_startDate, _endDate);
		List<LocalDate> holidayDates = new ArrayList<LocalDate>();
		for (Holiday holiday : holidays) {
			holidayDates.add(new LocalDate(holiday.getDate()));
		}
		Collections.sort(holidayDates);

		if(employeePaging.getRecordsFiltered() != 0){
			for (Employee employee : employeePaging.getData()) {
				ViewLeaveEmployeeVo viewLeaveEmployeeVo = new ViewLeaveEmployeeVo();
				viewLeaveEmployeeVo.setEmployeeId(employee.getId());
				viewLeaveEmployeeVo.setEmployeeName(employee.getFullName());
				viewLeaveEmployeeVo.setDepartmentId(employee.getDepartment().getId());
				
				List<ViewLeaveDateVo> viewLeaveDateVos = new ArrayList<ViewLeaveDateVo>();
				Hashtable<String ,ViewLeaveDateVo> hashViewLeaveDateVo = new Hashtable<String ,ViewLeaveDateVo>();
				int days = Days.daysBetween(startLocalDate, endLocalDate).getDays() + 1;
				for (int i=0; i < days; i++) {
				    LocalDate d = startLocalDate.withFieldAdded(DurationFieldType.days(), i);
				    
				    Property pDoW = d.dayOfWeek();
				    ViewLeaveDateVo viewLeaveDateVo = new ViewLeaveDateVo();
				    viewLeaveDateVo.setDate(sfShort.format(d.toDate()));
				    
				    if(StringUtils.isNotBlank(employee.getWorkingPolicy().getWeekendDescription())){
				    	String arrWeekend[] = employee.getWorkingPolicy().getWeekendDescription().toLowerCase().split(",");
				    	if(Arrays.asList(arrWeekend).contains(pDoW.getAsShortText().toLowerCase())){
				    		viewLeaveDateVo.setWeekend(true);
				    	}	
				    }
				    
				    if(employee.getWorkingPolicy().getHasHoliday() && !holidayDates.isEmpty()){
				    	if(Collections.binarySearch(holidayDates, d, localDateComparator) >= 0){
				    		viewLeaveDateVo.setHoliday(true);
				    	}
				    }
				    
				    if(DateUtils.isSameDay(d.toDate(), new Date())){
			    		viewLeaveDateVo.setToday(true);
			    	}
				    viewLeaveDateVos.add(viewLeaveDateVo);
				    hashViewLeaveDateVo.put(sf.format(d.toDate()), viewLeaveDateVo);
				}
	
				List<LeaveRequest> leaveRequests = leaveRequestService.searchByEmployeeIdAndDateAndStatuses(employee.getId(),_startDate  , _endDate,leaveRequestStatuses);

				for (LeaveRequest leaveRequest : leaveRequests) {
					ViewLeaveLeaveRequestVo ViewLeaveLeaveRequestVo = new ViewLeaveLeaveRequestVo();
					ViewLeaveLeaveRequestVo.setLeaveRequestId(leaveRequest.getId());
					ViewLeaveLeaveRequestVo.setLeaveTypeId(leaveRequest.getLeaveType().getId());
					ViewLeaveLeaveRequestVo.setLeaveTypeColor(leaveRequest.getLeaveType().getColor());
					ViewLeaveLeaveRequestVo.setLeaveTypeName(leaveRequest.getLeaveType().getName());
					ViewLeaveLeaveRequestVo.setStatusId(leaveRequest.getStatus().getId());
					ViewLeaveLeaveRequestVo.setStatusName(leaveRequest.getStatus().getName());
					ViewLeaveLeaveRequestVo.setRequestDate(sfShort.format(leaveRequest.getRequestDate()));
					ViewLeaveLeaveRequestVo.setRequestNote(leaveRequest.getRequestNote());
					
					for (Leave leave : leaveRequest.getLeaves()) {
						ViewLeaveDateVo viewLeaveDateVo = hashViewLeaveDateVo.get(sf.format(leave.getDate()));
						if(viewLeaveDateVo != null){
							ViewLeaveDateDetailVo viewLeaveDateDetailVo = new ViewLeaveDateDetailVo();
							viewLeaveDateDetailVo.setLeaveRequestId(leave.getLeaveRequest().getId());
							viewLeaveDateDetailVo.setAm(leave.getAm());
							viewLeaveDateDetailVo.setPm(leave.getPm());
							viewLeaveDateVo.getViewLeaveDateDetailVos().add(viewLeaveDateDetailVo);
						}
	
						ViewLeaveLeaveVo viewLeaveLeaveVo = new ViewLeaveLeaveVo();
						viewLeaveLeaveVo.setDate(sfShort.format(leave.getDate()));
						viewLeaveLeaveVo.setAm(leave.getAm());
						viewLeaveLeaveVo.setPm(leave.getPm());
						ViewLeaveLeaveRequestVo.getViewLeaveLeaveVos().add(viewLeaveLeaveVo);
					}
					
					viewLeaveEmployeeVo.getViewLeaveLeaveRequestVos().add(ViewLeaveLeaveRequestVo);
				}
				viewLeaveEmployeeVo.setViewLeaveDateVos(viewLeaveDateVos);
				viewLeaveEmployeeVos.add(viewLeaveEmployeeVo);
			}
		}
		ViewLeavePaging viewLeavePaging = new ViewLeavePaging();
		viewLeavePaging.setDraw(++draw);
		viewLeavePaging.setRecordsFiltered(employeePaging.getRecordsFiltered());
		viewLeavePaging.setRecordsTotal(employeePaging.getRecordsTotal());
		viewLeavePaging.setData(viewLeaveEmployeeVos);

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json2 = gson.toJson(viewLeavePaging);

		return json2;
	}

	@RequestMapping(value = "/view-leave/saveLeaveRequest", method = RequestMethod.POST, headers = { "Content-type=application/json" })
	public @ResponseBody
	String saveLeaveRequest(Model model, @RequestBody ViewLeaveSaveFormVo formVo, HttpSession session)
			throws ParseException {
		SecurityContextImpl securityContextImpl = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
		Employee loginEmployee = employeeService.findByCode(((Person)securityContextImpl.getAuthentication().getPrincipal()).getUsername());
		LeaveType leaveType = leaveTypeDao.findById(formVo.getLeaveTypeId());
		
		ResultVo resultVo = leaveRequestService.validateBeforeAdd(formVo.getEmployeeId(), formVo.getLeaveTypeId(),  formVo.getViewLeaveLeaveVos());
		if (!resultVo.isError()) {
			SimpleDateFormat sf = new SimpleDateFormat(DateUtil.DATE_PATTERN_VIEW_DDMMYYYY);

			LeaveRequest leaveRequest = new LeaveRequest();
			leaveRequest.setRequestEmployee(employeeService.findById(formVo.getEmployeeId()));
			leaveRequest.setTotalNoDays(formVo.getTotalNoDays());
			leaveRequest.setStatus(LeaveRequestStatus.NEW);
			leaveRequest.setLeaveType(leaveType);
			leaveRequest.setRequestDate(new Date());
			leaveRequest.setRequestNote(formVo.getRequestNote());
			List<Leave> leaves = new ArrayList<Leave>();

			for (ViewLeaveLeaveVo viewLeaveLeaveVo : formVo.getViewLeaveLeaveVos()) {
				Leave leave = new Leave();
				leave.setAm(viewLeaveLeaveVo.getAm());
				if (viewLeaveLeaveVo.getAm()) {
					leave.setFromTime(Time.valueOf("09:00:00"));
				} else {
					leave.setFromTime(Time.valueOf("13:00:00"));
				}
				leave.setPm(viewLeaveLeaveVo.getPm());
				if (viewLeaveLeaveVo.getPm()) {
					leave.setToTime(Time.valueOf("18:00:00"));
				} else {
					leave.setToTime(Time.valueOf("12:00:00"));
				}
				leave.setDate(sf.parse(viewLeaveLeaveVo.getDate().trim()));
				leave.setLeaveRequest(leaveRequest);
				leaves.add(leave);
			}
			leaveRequest.setLeaves(leaves);

			leaveRequest.setCreateBy(loginEmployee);
			leaveRequest.setCreateDate(new Date());
			leaveRequestService.save(leaveRequest);

			resultVo.setMessage("");
		}
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json2 = gson.toJson(resultVo);
		return json2;
	}

	@RequestMapping(value = "/view-leave/updateLeaveStatus", method = RequestMethod.GET, produces = { "application/json" })
	public @ResponseBody
	String updateLeaveStatus(Model model, @RequestParam(required = true) Integer newLeaveStatus,
			@RequestParam(required = true) Integer leaveRequestId, @RequestParam(required = false) String rejectNote, HttpSession session) throws ParseException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		ResultVo resultVo = new ResultVo();
		SecurityContextImpl securityContextImpl = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
		Employee loginEmployee = employeeService.findByCode(((Person)securityContextImpl.getAuthentication().getPrincipal()).getUsername());

		LeaveRequest leaveRequest = leaveRequestService.findById(leaveRequestId);
		resultVo = leaveRequestService.validateBeforeUpdate(leaveRequest, newLeaveStatus, loginEmployee);

		if (!resultVo.isError()) {
			if(newLeaveStatus.intValue() == -1){
				leaveRequestService.delete(leaveRequest);
			}else{
				leaveRequest.setStatus(LeaveRequestStatus.getById(newLeaveStatus));
				if(newLeaveStatus.intValue() == LeaveRequestStatus.REJECTED.getId()){
					leaveRequest.setRejectNote(rejectNote);
				}
				leaveRequest.setUpdateBy(loginEmployee);
				leaveRequest.setUpdateDate(new Date());
				leaveRequestService.saveOrUpdate(leaveRequest);
			}
			if(newLeaveStatus.intValue() == LeaveRequestStatus.VIEWED.getId()){
				resultVo.setMessage("");
			}else{
				resultVo.setMessage("");
			}
		}

		String json2 = gson.toJson(resultVo);
		return json2;
	}
}
