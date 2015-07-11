/**
 * 
 */
package com.bredit.leavemanagement.controller.ajax;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bredit.leavemanagement.model.LeaveProfile;

/**
 * @author KaweepattC
 * 
 */
@Controller
public class LeaveProfileAjaxController extends BaseAjaxController
{
    @RequestMapping(value = "/leave-profile/view", method = RequestMethod.GET)
    public void view(Model model, @RequestParam(required = true) Integer id)
    {
        // Find by PK
    }

    @RequestMapping(value = "/leave-profile/list", method = RequestMethod.GET, produces = { "application/json" })
    public @ResponseBody
    String list(Model model)
    {
        // Find all
        return null;
    }

    @RequestMapping(value = "/leave-profile/listBy", method = RequestMethod.GET, produces = { "application/json" })
    public @ResponseBody
    String list(Model model, @RequestParam Map<String, String> listByParameters)
    {
        // Find by criteria
        LeaveProfile criteria = new LeaveProfile();

        return null;
    }

    @RequestMapping(value = "/leave-profile/create", method = RequestMethod.POST)
    public void create(Model model, LeaveProfile LeaveProfile)
    {

    }

    @RequestMapping(value = "/leave-profile/update", method = RequestMethod.POST)
    public void update(Model model, LeaveProfile LeaveProfile)
    {

    }

    @RequestMapping(value = "/leave-profile/delete", method = RequestMethod.POST)
    public void delete(Model model, @RequestParam(required = false) Integer id)
    {

    }
}
