package com.metasoft.claim.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.metasoft.claim.service.LeaveProfileService;

public class CreateLeaveProfileNextYearTask {
	@Autowired
	private LeaveProfileService leaveProfileService;
	
	@Transactional
	public void createAnnualLeaveNextYear()  throws Exception {
		leaveProfileService.createAnnualLeaveNextYear();
	}
}