package com.metasoft.claim.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the std_receive_type database table.
 * 
 */
@Entity
@Table(name="std_receive_type")
@NamedQuery(name="StdReceiveType.findAll", query="SELECT s FROM StdReceiveType s")
public class StdReceiveType extends BaseModel {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="receive_type_id")
	private int receiveTypeId;

	@Column(name="receive_type_name")
	private String receiveTypeName;

	public StdReceiveType() {
	}

	public int getReceiveTypeId() {
		return this.receiveTypeId;
	}

	public void setReceiveTypeId(int receiveTypeId) {
		this.receiveTypeId = receiveTypeId;
	}

	public String getReceiveTypeName() {
		return this.receiveTypeName;
	}

	public void setReceiveTypeName(String receiveTypeName) {
		this.receiveTypeName = receiveTypeName;
	}

	@Override
	public Serializable getId() {
		return receiveTypeId;
	}

	

}