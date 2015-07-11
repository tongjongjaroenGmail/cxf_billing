package com.metasoft.claim.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the con_job_status database table.
 * 
 */
@Entity
@Table(name="con_job_status")
@NamedQuery(name="ConJobStatus.findAll", query="SELECT c FROM ConJobStatus c")
public class ConJobStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="job_status")
	private String jobStatus;

	@Column(name="job_name")
	private String jobName;

	public ConJobStatus() {
	}

	public String getJobStatus() {
		return this.jobStatus;
	}

	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

	public String getJobName() {
		return this.jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
}