package com.metasoft.claim.controller.vo;

import java.sql.Date;

public class TrackingSearchResultVo {
	 private int id;
	 private String  accidentDate = "";
	 private String claimAmount = "";
	 private float claimInsuranceAmount = 0;
	 private String  claimNumber= "";
	 private int   createBy = 0;
	 private String  createDate = "";
	 private String  jobCancelRemark= "";
	 private String  jobCloseRemark= "";
	 private String   jobDate= "";
	 private String  jobNo= "";
	 private String  jobStartRemark= "";
	 private String  licenseNumber= "";
	 private String  maturityDate= "";
	 private String  partyClaimNumber= "";
	 private int  partyInsuranceId=0;
	 private String  partyLicenseNumber= "";
	 private String  partyPolicyNo= "";
	 private String  policyNo= "";
	 private float  requestAmount=0;
	 private int   responsibility=0;
	 private int  updateBy=0;
	 private String   updateDate= "";
	 private int  agentId=0;
	 private String  claimType="";
	 private int   jobStatus=0;
	 private int  insuranceId=0;
	 private int  receiveType=0;
	 private String insuranceName= "";
	 private String closeDate= "";
	 private String invoiceNumber= "";
	 private int no=0;
	 private String jobStaus= "";
	 private String laborPrice= "";
	 private String totalPrice= "";
	 private String insuranceFullName= "";
	 private String responseStatus= "";
	 private String followRemark= "";
	 private String followDate= "";
	 private String titleName= "";
	 private String claimId = "";
	 private String agent="";
	 private String agentName="";
	 
	
	 
	public String getAgentName() {
		return agentName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccidentDate() {
		return accidentDate;
	}
	public void setAccidentDate(String accidentDate) {
		this.accidentDate = accidentDate;
	}
	public String getClaimAmount() {
		return claimAmount;
	}
	public void setClaimAmount(String claimAmount) {
		this.claimAmount = claimAmount;
	}
	public float getClaimInsuranceAmount() {
		return claimInsuranceAmount;
	}
	public void setClaimInsuranceAmount(float claimInsuranceAmount) {
		this.claimInsuranceAmount = claimInsuranceAmount;
	}
	public String getClaimNumber() {
		return claimNumber;
	}
	public void setClaimNumber(String claimNumber) {
		this.claimNumber = claimNumber;
	}
	public int getCreateBy() {
		return createBy;
	}
	public void setCreateBy(int createBy) {
		this.createBy = createBy;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getJobCancelRemark() {
		return jobCancelRemark;
	}
	public void setJobCancelRemark(String jobCancelRemark) {
		this.jobCancelRemark = jobCancelRemark;
	}
	public String getJobCloseRemark() {
		return jobCloseRemark;
	}
	public void setJobCloseRemark(String jobCloseRemark) {
		this.jobCloseRemark = jobCloseRemark;
	}
	public String getJobDate() {
		return jobDate;
	}
	public void setJobDate(String jobDate) {
		this.jobDate = jobDate;
	}
	public String getJobNo() {
		return jobNo;
	}
	public void setJobNo(String jobNo) {
		this.jobNo = jobNo;
	}
	public String getJobStartRemark() {
		return jobStartRemark;
	}
	public void setJobStartRemark(String jobStartRemark) {
		this.jobStartRemark = jobStartRemark;
	}
	public String getLicenseNumber() {
		return licenseNumber;
	}
	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}
	public String getMaturityDate() {
		return maturityDate;
	}
	public void setMaturityDate(String maturityDate) {
		this.maturityDate = maturityDate;
	}
	public String getPartyClaimNumber() {
		return partyClaimNumber;
	}
	public void setPartyClaimNumber(String partyClaimNumber) {
		this.partyClaimNumber = partyClaimNumber;
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
	public float getRequestAmount() {
		return requestAmount;
	}
	public void setRequestAmount(float requestAmount) {
		this.requestAmount = requestAmount;
	}
	public int getResponsibility() {
		return responsibility;
	}
	public void setResponsibility(int responsibility) {
		this.responsibility = responsibility;
	}
	public int getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(int updateBy) {
		this.updateBy = updateBy;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
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
	public int getJobStatus() {
		return jobStatus;
	}
	public void setJobStatus(int jobStatus) {
		this.jobStatus = jobStatus;
	}
	public int getInsuranceId() {
		return insuranceId;
	}
	public void setInsuranceId(int insuranceId) {
		this.insuranceId = insuranceId;
	}
	public int getReceiveType() {
		return receiveType;
	}
	public void setReceiveType(int receiveType) {
		this.receiveType = receiveType;
	}
	public String getInsuranceName() {
		return insuranceName;
	}
	public void setInsuranceName(String insuranceName) {
		this.insuranceName = insuranceName;
	}
	public String getCloseDate() {
		return closeDate;
	}
	public void setCloseDate(String closeDate) {
		this.closeDate = closeDate;
	}
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
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
	public String getLaborPrice() {
		return laborPrice;
	}
	public void setLaborPrice(String laborPrice) {
		this.laborPrice = laborPrice;
	}
	public String getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getInsuranceFullName() {
		return insuranceFullName;
	}
	public void setInsuranceFullName(String insuranceFullName) {
		this.insuranceFullName = insuranceFullName;
	}
	public String getResponseStatus() {
		return responseStatus;
	}
	public void setResponseStatus(String responseStatus) {
		this.responseStatus = responseStatus;
	}
	public String getFollowRemark() {
		return followRemark;
	}
	public void setFollowRemark(String followRemark) {
		this.followRemark = followRemark;
	}
	public String getFollowDate() {
		return followDate;
	}
	public void setFollowDate(String followDate) {
		this.followDate = followDate;
	}
	public String getTitleName() {
		return titleName;
	}
	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}
	public String getClaimId() {
		return claimId;
	}
	public void setClaimId(String claimId) {
		this.claimId = claimId;
	}
	public String getAgent() {
		return agent;
	}
	public void setAgent(String agent) {
		this.agent = agent;
	}
}
