package com.metasoft.claim.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the sec_user database table.
 * 
 */
@Entity
@Table(name="sec_user")
@NamedQuery(name="SecUser.findAll", query="SELECT s FROM SecUser s")
public class SecUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;

	private String email;

	private String lastname;

	private String name;

	private String status;

	@Column(name="tel_number")
	private String telNumber;

	//bi-directional many-to-one association to StdPosition
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name="position_id")
	private StdPosition stdPosition;

	//bi-directional many-to-many association to StdInsurance
	@ManyToMany
	@JoinTable(
		name="tbl_user_insurance"
		, joinColumns={
			@JoinColumn(name="user_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="insurance_id")
			}
		)
	private List<StdInsurance> stdInsurances;

	public SecUser() {
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTelNumber() {
		return this.telNumber;
	}

	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}

	public StdPosition getStdPosition() {
		return this.stdPosition;
	}

	public void setStdPosition(StdPosition stdPosition) {
		this.stdPosition = stdPosition;
	}

	public List<StdInsurance> getStdInsurances() {
		return this.stdInsurances;
	}

	public void setStdInsurances(List<StdInsurance> stdInsurances) {
		this.stdInsurances = stdInsurances;
	}

}