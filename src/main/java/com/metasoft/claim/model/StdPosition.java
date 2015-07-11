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
public class StdPosition implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="position_id")
	private int positionId;

	@Column(name="position_name")
	private String positionName;

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


}