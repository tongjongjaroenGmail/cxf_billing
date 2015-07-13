package com.metasoft.claim.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the sec_user database table.
 * 
 */
@Entity
@Table(name = "sec_user")
@NamedQuery(name = "SecUser.findAll", query = "SELECT s FROM SecUser s")
public class SecUser extends BaseModel {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String email;

	private String lastName;

	private String name;

	@Column(name = "enable", nullable = false, columnDefinition = "boolean DEFAULT true")
	private boolean enable;

	@Column(name = "tel_number")
	private String telNumber;

	private String userName;

	private String password;

	// bi-directional many-to-one association to StdPosition
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "position_id")
	private StdPosition stdPosition;

	// bi-directional many-to-many association to StdInsurance
	@ManyToMany
	@JoinTable(name = "tbl_user_insurance", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "insurance_id") })
	private List<StdInsurance> stdInsurances;

	public SecUser() {
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}



	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastname(String lastName) {
		this.lastName = lastName;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	

}