/**
 * 
 */
package com.metasoft.claim.controller.ajax;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.metasoft.claim.model.Employee;
import com.metasoft.claim.model.LeaveRequest;
import com.metasoft.claim.model.LeaveRequestStatus;

/**
 * @author 
 * 
 */
@Controller
public class LeaveRequestAjaxController extends BaseAjaxController
{
    @RequestMapping(value = "/leave-request/view", method = RequestMethod.GET)
    public void view(Model model, @RequestParam(required = true) Integer id)
    {
        // Find by PK
    }

    @RequestMapping(value = "/leave-request/list", method = RequestMethod.GET, produces = { "application/json" })
    public @ResponseBody
    String list(Model model)
    {
        // Find all
        return null;
    }

    @RequestMapping(value = "/leave-request/listBy", method = RequestMethod.GET, produces = { "application/json" })
    public @ResponseBody
    String list(Model model, @RequestParam Map<String, String> listByParameters)
    {
        // Find by criteria
        LeaveRequest criteria = new LeaveRequest();
        if (listByParameters.containsKey("requestEmployee.id"))
        {
            Employee employee = new Employee();
            employee.setId(Integer.valueOf(listByParameters.get("requestEmployee.id")));
            criteria.setRejectEmployee(employee);
        }

        if (listByParameters.containsKey("approveEmployee.id"))
        {
            Employee employee = new Employee();
            employee.setId(Integer.valueOf(listByParameters.get("approveEmployee.id")));
            criteria.setApproveEmployee(employee);
        }

        if (listByParameters.containsKey("rejectEmployee.id"))
        {
            Employee employee = new Employee();
            employee.setId(Integer.valueOf(listByParameters.get("rejectEmployee.id")));
            criteria.setRejectEmployee(employee);
        }

        if (listByParameters.containsKey("cancelEmployee.id"))
        {
            Employee employee = new Employee();
            employee.setId(Integer.valueOf(listByParameters.get("cancelEmployee.id")));
            criteria.setCancelEmployee(employee);
        }

        if (listByParameters.containsKey("status"))
        {
            criteria.setStatus(LeaveRequestStatus.valueOf(listByParameters.get("status")));
        }

        return null;
    }

    @RequestMapping(value = "/leave-request/create", method = RequestMethod.POST)
    public void create(Model model, LeaveRequest leaveRequest)
    {

    }

    @RequestMapping(value = "/leave-request/update", method = RequestMethod.POST)
    public void update(Model model, LeaveRequest leaveRequest)
    {

    }

    @RequestMapping(value = "/leave-request/delete", method = RequestMethod.POST)
    public void delete(Model model, @RequestParam(required = false) Integer id)
    {

    }
}
