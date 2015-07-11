/**
 * 
 */
package com.bredit.leavemanagement.service;

import com.bredit.leavemanagement.dao.LeaveProfileDao;
import com.bredit.leavemanagement.model.LeaveProfile;


public interface LeaveProfileService extends ModelBasedService<LeaveProfileDao, LeaveProfile, Integer>
{
	public void createAnnualLeaveNextYear();
}
