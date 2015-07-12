package com.metasoft.claim.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the tbl_user_insurance database table.
 * 
 */
@Embeddable
public class TblUserInsurancePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="user_id", insertable=false, updatable=false)
	private int userId;

	@Column(name="insurance_id", insertable=false, updatable=false)
	private int insuranceId;

	public TblUserInsurancePK() {
	}
	public int getUserId() {
		return this.userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getInsuranceId() {
		return this.insuranceId;
	}
	public void setInsuranceId(int insuranceId) {
		this.insuranceId = insuranceId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TblUserInsurancePK)) {
			return false;
		}
		TblUserInsurancePK castOther = (TblUserInsurancePK)other;
		return 
			(this.userId == castOther.userId)
			&& (this.insuranceId == castOther.insuranceId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.userId;
		hash = hash * prime + this.insuranceId;
		
		return hash;
	}
}