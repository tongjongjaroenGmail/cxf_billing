package com.metasoft.claim.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the tbl_claim_recovery database table.
 * 
 */
@Entity
@Table(name = "tbl_claim_recovery")
@NamedQuery(name = "TblClaimRecovery.findAll", query = "SELECT t FROM TblClaimRecovery t")
public class TblClaimRecovery extends BaseModel {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Temporal(TemporalType.DATE)
	@Column(name = "accident_date")
	private Date accidentDate;

	@ManyToOne
	@JoinColumn(name = "agent_id")
	private SecUser agent;

	@Column(name = "claim_amount")
	private Float claimAmount;

	@Column(name = "claim_insurance_amount")
	private Float claimInsuranceAmount;

	@Column(name = "claim_number")
	private String claimNumber;

	@ManyToOne
	@JoinColumn(name = "create_by")
	private SecUser createBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date")
	private Date createDate;

	@Column(name = "receive_remark")
	private String receiveRemark;

	@Column(name = "follow_remark")
	private String followRemark;

	@Temporal(TemporalType.DATE)
	@Column(name = "job_date")
	private Date jobDate;

	@Column(name = "job_no")
	private String jobNo;

	@Column(name = "close_remark")
	private String closeRemark;

	@Column(name = "cancel_remark")
	private String cancelRemark;

	@Column(name = "license_number")
	private String licenseNumber;

	@Column(name = "party_claim_number")
	private String partyClaimNumber;

	@ManyToOne
	@JoinColumn(name = "party_insurance_id")
	private StdInsurance partyInsurance;

	@Column(name = "party_license_number")
	private String partyLicenseNumber;

	@Column(name = "party_policy_no")
	private String partyPolicyNo;

	@Column(name = "policy_no")
	private String policyNo;

	@Column(name = "request_amount")
	private Float requestAmount;

	@Column(nullable = false, columnDefinition = "default 1")
	private boolean responsibility = true;

	@ManyToOne
	@JoinColumn(name = "update_by")
	private SecUser updateBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_date")
	private Date updateDate;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "receive_money_type")
	private ReceiveMoneyType receiveMoneyType;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "job_status")
	private JobStatus jobStatus = JobStatus.RECEIVED;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "claim_type")
	private ClaimType claimType;

	@Column(name = "invoice_number", length = 1000)
	private String invoiceNumber;

	@Temporal(TemporalType.DATE)
	@Column(name = "maturity_date")
	private Date maturityDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "follow_date")
	private Date followDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "cancel_date")
	private Date cancelDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "close_date")
	private Date closeDate;

	public Date getFollowDate() {
		return followDate;
	}

	public void setFollowDate(Date followDate) {
		this.followDate = followDate;
	}

	public Date getCancelDate() {
		return cancelDate;
	}

	public void setCancelDate(Date cancelDate) {
		this.cancelDate = cancelDate;
	}

	public Date getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getAccidentDate() {
		return accidentDate;
	}

	public void setAccidentDate(Date accidentDate) {
		this.accidentDate = accidentDate;
	}

	public SecUser getAgent() {
		return agent;
	}

	public void setAgent(SecUser agent) {
		this.agent = agent;
	}

	public Float getClaimAmount() {
		return claimAmount;
	}

	public void setClaimAmount(Float claimAmount) {
		this.claimAmount = claimAmount;
	}

	public Float getClaimInsuranceAmount() {
		return claimInsuranceAmount;
	}

	public void setClaimInsuranceAmount(Float claimInsuranceAmount) {
		this.claimInsuranceAmount = claimInsuranceAmount;
	}

	public String getClaimNumber() {
		return claimNumber;
	}

	public void setClaimNumber(String claimNumber) {
		this.claimNumber = claimNumber;
	}

	public SecUser getCreateBy() {
		return createBy;
	}

	public void setCreateBy(SecUser createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getJobDate() {
		return jobDate;
	}

	public void setJobDate(Date jobDate) {
		this.jobDate = jobDate;
	}

	public String getJobNo() {
		return jobNo;
	}

	public void setJobNo(String jobNo) {
		this.jobNo = jobNo;
	}

	public String getReceiveRemark() {
		return receiveRemark;
	}

	public void setReceiveRemark(String receiveRemark) {
		this.receiveRemark = receiveRemark;
	}

	public String getFollowRemark() {
		return followRemark;
	}

	public void setFollowRemark(String followRemark) {
		this.followRemark = followRemark;
	}

	public String getCloseRemark() {
		return closeRemark;
	}

	public void setCloseRemark(String closeRemark) {
		this.closeRemark = closeRemark;
	}

	public String getCancelRemark() {
		return cancelRemark;
	}

	public void setCancelRemark(String cancelRemark) {
		this.cancelRemark = cancelRemark;
	}

	public String getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public String getPartyClaimNumber() {
		return partyClaimNumber;
	}

	public void setPartyClaimNumber(String partyClaimNumber) {
		this.partyClaimNumber = partyClaimNumber;
	}

	public StdInsurance getPartyInsurance() {
		return partyInsurance;
	}

	public void setPartyInsurance(StdInsurance partyInsurance) {
		this.partyInsurance = partyInsurance;
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

	public Float getRequestAmount() {
		return requestAmount;
	}

	public void setRequestAmount(Float requestAmount) {
		this.requestAmount = requestAmount;
	}

	public boolean isResponsibility() {
		return responsibility;
	}

	public void setResponsibility(boolean responsibility) {
		this.responsibility = responsibility;
	}

	public SecUser getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(SecUser updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public ReceiveMoneyType getReceiveMoneyType() {
		return receiveMoneyType;
	}

	public void setReceiveMoneyType(ReceiveMoneyType receiveMoneyType) {
		this.receiveMoneyType = receiveMoneyType;
	}

	public JobStatus getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(JobStatus jobStatus) {
		this.jobStatus = jobStatus;
	}

	public ClaimType getClaimType() {
		return claimType;
	}

	public void setClaimType(ClaimType claimType) {
		this.claimType = claimType;
	}

	public Boolean getResponsibility() {
		return responsibility;
	}

	public void setResponsibility(Boolean responsibility) {
		this.responsibility = responsibility;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public Date getMaturityDate() {
		return maturityDate;
	}

	public void setMaturityDate(Date maturityDate) {
		this.maturityDate = maturityDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}