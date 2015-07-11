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

import com.metasoft.claim.model.Leave;
import com.metasoft.claim.model.LeaveRequest;

/**
 * @author 
 * 
 */
@Controller
public class LeaveAjaxController extends BaseAjaxController
{
    @RequestMapping(value = "/leave/view", method = RequestMethod.GET)
    public void view(Model model, @RequestParam(required = true) Integer id)
    {
        // Find by PK
    }

    @RequestMapping(value = "/leave/list", method = RequestMethod.GET, produces = { "application/json" })
    public @ResponseBody
    String list(Model model)
    {
        // Find all
        return null;
    }

    @RequestMapping(value = "/leave/listBy", method = RequestMethod.GET, produces = { "application/json" })
    public @ResponseBody
    String list(Model model, @RequestParam Map<String, String> listByParameters)
    {
        // Find by criteria
        Leave criteria = new Leave();
        if (listByParameters.containsKey("leaveRequest.id"))
        {
            LeaveRequest leaveRequest = new LeaveRequest();
            leaveRequest.setId(Integer.valueOf(listByParameters.get("leaveRequest.id")));
            criteria.setLeaveRequest(leaveRequest);
        }

        return null;
    }

    @RequestMapping(value = "/leave/create", method = RequestMethod.POST)
    public void create(Model model, Leave leave)
    {

    }

    @RequestMapping(value = "/leave/update", method = RequestMethod.POST)
    public void update(Model model, Leave leave)
    {

    }

    @RequestMapping(value = "/leave/delete", method = RequestMethod.POST)
    public void delete(Model model, @RequestParam(required = false) Integer id)
    {

    }
}
