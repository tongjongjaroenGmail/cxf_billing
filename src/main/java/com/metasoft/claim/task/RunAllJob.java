package com.metasoft.claim.task;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Component
public class RunAllJob extends QuartzJobBean {
	private CreateLeaveProfileNextYearTask createLeaveProfileNextYearTask;

	public void setCreateLeaveProfileNextYearTask(CreateLeaveProfileNextYearTask createLeaveProfileNextYearTask) {
		this.createLeaveProfileNextYearTask = createLeaveProfileNextYearTask;
	}

	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {

			try {
				createLeaveProfileNextYearTask.createAnnualLeaveNextYear();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
}