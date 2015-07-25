package com.metasoft.claim.controller.vo;

import java.sql.Date;

public class LaborResultVo {
	
	 private String  accidentDate;
	 private float claimAmount;
	 private String  claimNumber;
	 private int  partyInsuranceId;
	 private String  partyLicenseNumber;
	 private String  partyPolicyNo;
	 private String  policyNo;
	 private int  agentId;
	 private String  claimType;
	 private String insuranceName;
	 private int no;
	 private String jobStaus;
	 private float laborPrice;
	 
	public String getAccidentDate() {
		return accidentDate;
	}
	public void setAccidentDate(String accidentDate) {
		this.accidentDate = accidentDate;
	}
	public float getClaimAmount() {
		return claimAmount;
	}
	public void setClaimAmount(float claimAmount) {
		this.claimAmount = claimAmount;
	}
	public String getClaimNumber() {
		return claimNumber;
	}
	public void setClaimNumber(String claimNumber) {
		this.claimNumber = claimNumber;
	}
	public int getPartyInsuranceId() {
		return partyInsuranceId;
	}
	public void setPartyInsuranceId(int partyInsuranceId) {
		this.partyInsuranceId = partyInsuranceId;
	}
	public String getPartyLicenseNumber() {
		return partyLicenseNumber;
	}
	public void setPartyLicenseNumber(String partyLicenseNumber) {
		this.partyLicenseNumber = partyLicenseNumber;
	}
	public String getPartyPolicyNo() {
		return partyPolicyNo;
	}
	public void setPartyPolicyNo(String partyPolicyNo) {
		this.partyPolicyNo = partyPolicyNo;
	}
	public String getPolicyNo() {
		return policyNo;
	}
	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}
	
	public int getAgentId() {
		return agentId;
	}
	public void setAgentId(int agentId) {
		this.agentId = agentId;
	}
	public String getClaimType() {
		return claimType;
	}
	public void setClaimType(String claimType) {
		this.claimType = claimType;
	}
	
	
	public String getInsuranceName() {
		return insuranceName;
	}
	public void setInsuranceName(String insuranceName) {
		this.insuranceName = insuranceName;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getJobStaus() {
		return jobStaus;
	}
	public void setJobStaus(String jobStaus) {
		this.jobStaus = jobStaus;
	}
	public float getLaborPrice() {
		return laborPrice;
	}
	public void setLaborPrice(float laborPrice) {
		this.laborPrice = laborPrice;
	}

	
	 
}
