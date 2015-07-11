/**
 * 
 */
package com.metasoft.claim.service;

import com.metasoft.claim.dao.LeaveProfileDao;
import com.metasoft.claim.model.LeaveProfile;


public interface LeaveProfileService extends ModelBasedService<LeaveProfileDao, LeaveProfile, Integer>
{
	public void createAnnualLeaveNextYear();
}
