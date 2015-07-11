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

import com.metasoft.claim.model.LeaveType;

/**
 * @author 
 * 
 */
@Controller
public class LeaveTypeAjaxController extends BaseAjaxController
{
    @RequestMapping(value = "/leave-type/view", method = RequestMethod.GET)
    public void view(Model model, @RequestParam(required = true) Integer id)
    {
        // Find by PK
    }

    @RequestMapping(value = "/leave-type/list", method = RequestMethod.GET, produces = { "application/json" })
    public @ResponseBody
    String list(Model model)
    {
        // Find all
        return null;
    }

    @RequestMapping(value = "/leave-type/listBy", method = RequestMethod.GET, produces = { "application/json" })
    public @ResponseBody
    String list(Model model, @RequestParam Map<String, String> listByParameters)
    {
        // Find by criteria
        LeaveType criteria = new LeaveType();

        return null;
    }

    @RequestMapping(value = "/leave-type/create", method = RequestMethod.POST)
    public void create(Model model, LeaveType LeaveType)
    {

    }

    @RequestMapping(value = "/leave-type/update", method = RequestMethod.POST)
    public void update(Model model, LeaveType LeaveType)
    {

    }

    @RequestMapping(value = "/leave-type/delete", method = RequestMethod.POST)
    public void delete(Model model, @RequestParam(required = false) Integer id)
    {

    }
}
