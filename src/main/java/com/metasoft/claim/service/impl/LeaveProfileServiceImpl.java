/**
 * 
 */
package com.metasoft.claim.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.metasoft.claim.dao.LeaveProfileDao;
import com.metasoft.claim.model.Employee;
import com.metasoft.claim.model.LeaveProfile;
import com.metasoft.claim.service.EmployeeService;
import com.metasoft.claim.service.LeaveProfileService;
import com.metasoft.claim.service.LeaveService;

@Component
public class LeaveProfileServiceImpl extends ModelBasedServiceImpl<LeaveProfileDao, LeaveProfile, Integer> implements LeaveProfileService
{
    private LeaveProfileDao leaveProfileDao;

    @Autowired
    private EmployeeService employeeService;
    
    @Autowired
    LeaveService leaveService;
    /**
     * 
     * @param entityClass
     */
    @Autowired
    public LeaveProfileServiceImpl(LeaveProfileDao leaveProfileDao)
    {
        super(leaveProfileDao);
        this.leaveProfileDao = leaveProfileDao;
    }

    @Transactional
	public void createAnnualLeaveNextYear()  {
		List<Employee> employees = employeeService.findAllStatusNotResign();
		
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy");
        int thisYear = Integer.parseInt(fmt.format(new Date()));
        Date curDate = new Date();		
        Employee admin = employeeService.findById(162);
		for (Employee employee : employees) {
			LeaveProfile curLeaveProfile = employee.getLeaveProfileInCurrentYear();
			if(curLeaveProfile == null){
				curLeaveProfile = new LeaveProfile();
				curLeaveProfile.setYear(thisYear);
				curLeaveProfile.setEmployee(employee);
				curLeaveProfile.setCarryForward(0f);
				curLeaveProfile.setLeaveAvailable(14f);
				curLeaveProfile.setCreateDate(curDate);
				curLeaveProfile.setCreateBy(admin);
				leaveProfileDao.save(curLeaveProfile);
			}
			
			LeaveProfile newLeaveProfile = new LeaveProfile();
			newLeaveProfile.setYear(thisYear + 1);
			newLeaveProfile.setEmployee(employee);
			newLeaveProfile.setCarryForward(0f);
			newLeaveProfile.setLeaveAvailable(curLeaveProfile.getLeaveAvailable() + 1);
			newLeaveProfile.setCreateDate(curDate);
			newLeaveProfile.setCreateBy(admin);
			leaveProfileDao.save(newLeaveProfile);
			
			float remainLeaveDays = leaveService.calcRemainLeaveDays(employee.getId(), 1, thisYear - 1);
			if(remainLeaveDays > 5){
				remainLeaveDays = 5;
			}
			curLeaveProfile.setCarryForward(remainLeaveDays);
			curLeaveProfile.setUpdateDate(curDate);
			curLeaveProfile.setUpdateBy(admin);
			leaveProfileDao.saveOrUpdate(curLeaveProfile);
		}
	}

}
