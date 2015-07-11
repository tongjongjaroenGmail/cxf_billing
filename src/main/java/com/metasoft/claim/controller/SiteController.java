/**
 * 
 */
package com.metasoft.claim.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.metasoft.claim.controller.vo.HolidayEventVo;
import com.metasoft.claim.dao.HolidayDao;
import com.metasoft.claim.dao.LeaveTypeDao;
import com.metasoft.claim.model.Employee;
import com.metasoft.claim.model.EmployeeStatus;
import com.metasoft.claim.model.Holiday;
import com.metasoft.claim.model.LdapEmployeeSynchronizationResult;
import com.metasoft.claim.model.LeaveRequestStatus;
import com.metasoft.claim.repository.PersonRepository;
import com.metasoft.claim.service.DepartmentService;
import com.metasoft.claim.service.EmployeeService;
import com.metasoft.claim.service.LdapEmployeeSynchronizationService;
import com.metasoft.claim.service.LoginService;
import com.metasoft.claim.service.RoleService;
import com.metasoft.claim.service.WorkingPolicyService;
import com.metasoft.claim.util.DateUtil;

/**
 * @author 
 * 
 */
@Controller
public class SiteController extends BaseController
{
    @Autowired
    private LoginService loginService;

    @Autowired
   	private EmployeeService employeeService;

    @Autowired
    private LeaveTypeDao leaveTypeDao;
    
    @Autowired
    private DepartmentService departmentService;
  
    @Autowired
    private HolidayDao holidayDao;
    
    @Autowired
    private PersonRepository personRepository;
    
    @Autowired
   	private RoleService roleService;

    @Autowired
   	private WorkingPolicyService workingPolicyService;
    
    @Autowired
    private LdapEmployeeSynchronizationService ldapEmployeeSynchronizationService;

    /**
     * Display summary page
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, HttpSession session)
    {
        return "login";
    }

    /**
     * Display summary page
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model)
    {
        return "redirect:/summary";
    }

    /**
     * Display summary page
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/summary", method = RequestMethod.GET)
    public String summary(Model model)
    {	
    	addAttributeALLSite(model);
        return "summary";
    }

    @RequestMapping(value = "/summaryTemp", method = RequestMethod.GET)
    public String summaryTemp(Model model)
    {
    	addAttributeALLSite(model);
        return "summary-temp";
    }

    /**
     * Display view leaves page
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/view-leave", method = RequestMethod.GET)
    public String viewLeaves(Model model, @RequestParam(required = false) String message)
    {
    	addAttributeALLSite(model);
    	
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Employee loginEmployee = employeeService.findByCode(authentication.getName());
    	List<EmployeeStatus> employeeStatuses = new ArrayList<EmployeeStatus>();
		employeeStatuses.add(EmployeeStatus.ACTIVE);
		List<String> lineManagers = new ArrayList<String>();
		List<Employee> employeeManagers = employeeService.searchByRoleAndDepartmentAndStatuses(roleService.findById(2),loginEmployee.getDepartment(), employeeStatuses);
		for (Employee employeeManager : employeeManagers) {
			lineManagers.add(employeeManager.getFullName());
		}
		model.addAttribute("lineManagers", lineManagers);
    	model.addAttribute("leaveTypes", leaveTypeDao.findAll());
    	model.addAttribute("departments", departmentService.searchAllInEmployee());
    	model.addAttribute("holidays", holidayDao.findAll());
        model.addAttribute("message", message);
        return "view-leave";
    }

    /**
     * Display history log page
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/history-log", method = RequestMethod.GET)
    public String historyLog(Model model, @RequestParam(required = false) String message)
    {
    	addAttributeALLSite(model);
    	
       	model.addAttribute("leaveTypes", leaveTypeDao.findAll());
        model.addAttribute("message", message);
        return "history-log";
    }

    /**
     * Display adjust leave page
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/adjust-leave", method = RequestMethod.GET)
    public String adjustLeaves(Model model, @RequestParam(required = false) String message)
    {
    	addAttributeALLSite(model);
    	
    	model.addAttribute("departments", departmentService.findAll());
        model.addAttribute("message", message);
        return "adjust-leave";
    }
    
    @RequestMapping(value = "/adjust-employee", method = RequestMethod.GET)
    public String adjustEmployee(Model model, @RequestParam(required = false) String message)
    {
    	addAttributeALLSite(model);
    	
    	model.addAttribute("roles", roleService.findAll());
    	model.addAttribute("workingPolicys", workingPolicyService.findAll());
    	model.addAttribute("departments", departmentService.findAll());
        model.addAttribute("message", message);      
        return "adjust-employee";
    }

    @RequestMapping(value = "/import-employee", method = RequestMethod.GET)
    public String importEmployee(Model model, @RequestParam(required = false) String message)
    {
    	addAttributeALLSite(model);
    	
        model.addAttribute("message", message);   
        return "import-employee";
    }

    @RequestMapping(value = "/import-employee", method = RequestMethod.POST)
    public String executeImportEmployee(Model model, @RequestParam(required = false) String message)
    {
    	addAttributeALLSite(model);
    	
        LdapEmployeeSynchronizationResult result = ldapEmployeeSynchronizationService.syncAll();

        model.addAttribute("result", result);
        return "import-employee";
    }
    
    @RequestMapping(value = "/holiday", method = RequestMethod.GET)
    public String holiday(Model model, @RequestParam(required = false) String message)
    {
    	addAttributeALLSite(model);
    	
    	SimpleDateFormat sfVo = new SimpleDateFormat(DateUtil.DATE_PATTERN_VIEW_YYYYMMDD);
    	List<Holiday> holidays = holidayDao.findAll();
    	List<HolidayEventVo> holidayEventVos = new ArrayList<HolidayEventVo>();
    	for (Holiday holiday : holidays) {
    		HolidayEventVo holidayEventVo = new HolidayEventVo();
    		holidayEventVo.setTitle(holiday.getName());
    		holidayEventVo.setStart(sfVo.format(holiday.getDate()));
    		holidayEventVos.add(holidayEventVo);
		}
    	Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json2 = gson.toJson(holidayEventVos);
		model.addAttribute("HolidayEventVos", json2);
        model.addAttribute("message", message);    
        return "holiday";
    }
    
    private void addAttributeALLSite(Model model){
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Employee loginEmployee = employeeService.findByCode(authentication.getName());
     	model.addAttribute("loginEmployee", loginEmployee);
     	 
     	model.addAttribute("leaveRequestStatusAPPROVED", LeaveRequestStatus.APPROVED);
        model.addAttribute("leaveRequestStatusCANCELLED", LeaveRequestStatus.CANCELLED);
        model.addAttribute("leaveRequestStatusNEW", LeaveRequestStatus.NEW);
        model.addAttribute("leaveRequestStatusREJECTED", LeaveRequestStatus.REJECTED);
        model.addAttribute("leaveRequestStatusVIEWED", LeaveRequestStatus.VIEWED);
    }

}
