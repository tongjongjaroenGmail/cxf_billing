package com.bredit.leavemanagement.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.bredit.leavemanagement.service.LeaveProfileService;

public class CreateLeaveProfileNextYearTask {
	@Autowired
	private LeaveProfileService leaveProfileService;
	
	@Transactional
	public void createAnnualLeaveNextYear()  throws Exception {
		leaveProfileService.createAnnualLeaveNextYear();
	}
}