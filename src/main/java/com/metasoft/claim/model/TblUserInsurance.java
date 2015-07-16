package com.metasoft.claim.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the tbl_user_insurance database table.
 * 
 */
@Entity
@Table(name="tbl_user_insurance")
@NamedQuery(name="TblUserInsurance.findAll", query="SELECT t FROM TblUserInsurance t")
public class TblUserInsurance extends BaseModel {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TblUserInsurancePK id;

	public TblUserInsurance() {
	}

	public TblUserInsurancePK getId() {
		return this.id;
	}

	public void setId(TblUserInsurancePK id) {
		this.id = id;
	}

}