package com.metasoft.claim.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the std_position database table.
 * 
 */
@Entity
@Table(name="std_position")
@NamedQuery(name="StdPosition.findAll", query="SELECT s FROM StdPosition s")
public class StdPosition extends BaseModel {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="position_id")
	private int positionId;

	@Column(name="position_name")
	private String positionName;
	
	@Column(name="role")
	private String role;

	public StdPosition() {
	}

	public int getPositionId() {
		return this.positionId;
	}

	public void setPositionId(int positionId) {
		this.positionId = positionId;
	}

	public String getPositionName() {
		return this.positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public Serializable getId() {
		return positionId;
	}


}