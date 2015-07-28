package com.metasoft.claim.bean.report;

import java.util.Date;

public class BillingExportResult {
	private int no;
	private Date accidentDate;
	private Date closeDate;
	private String jobNo;
	private String claimNumber;
	private String licenseNumber;
	private String insuranceName;
	private int claimType;
	private Float wage;
	private Float claimAmount;
	private String receiveMoneyType;
	
	public Date getAccidentDate() {
		return accidentDate;
	}
	public void setAccidentDate(Date accidentDate) {
		this.accidentDate = accidentDate;
	}
	public String getReceiveMoneyType() {
		return receiveMoneyType;
	}
	public void setReceiveMoneyType(String receiveMoneyType) {
		this.receiveMoneyType = receiveMoneyType;
	}
	public Float getClaimAmount() {
		return claimAmount;
	}
	public void setClaimAmount(Float claimAmount) {
		this.claimAmount = claimAmount;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public Date getCloseDate() {
		return closeDate;
	}
	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}
	public String getJobNo() {
		return jobNo;
	}
	public void setJobNo(String jobNo) {
		this.jobNo = jobNo;
	}
	public String getClaimNumber() {
		return claimNumber;
	}
	public void setClaimNumber(String claimNumber) {
		this.claimNumber = claimNumber;
	}
	public String getLicenseNumber() {
		return licenseNumber;
	}
	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}
	public String getInsuranceName() {
		return insuranceName;
	}
	public void setInsuranceName(String insuranceName) {
		this.insuranceName = insuranceName;
	}

	public int getClaimType() {
		return claimType;
	}
	public void setClaimType(int claimType) {
		this.claimType = claimType;
	}
	public Float getWage() {
		return wage;
	}
	public void setWage(Float wage) {
		this.wage = wage;
	}

	
}
