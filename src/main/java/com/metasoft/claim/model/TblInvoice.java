package com.metasoft.claim.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the tbl_invoice database table.
 * 
 */
@Entity
@Table(name="tbl_invoice")
@NamedQuery(name="TblInvoice.findAll", query="SELECT t FROM TblInvoice t")
public class TblInvoice extends BaseModel {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer invoiceId;

	@Column(name="invoice_number")
	private String invoiceNumber;
	
	//bi-directional many-to-one association to TblClaimRecovery
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "claim_recovery_id", nullable = false)
	private TblClaimRecovery tblClaimRecovery;

	
	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public TblClaimRecovery getTblClaimRecovery() {
		return tblClaimRecovery;
	}

	public void setTblClaimRecovery(TblClaimRecovery tblClaimRecovery) {
		this.tblClaimRecovery = tblClaimRecovery;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(Integer invoiceId) {
		this.invoiceId = invoiceId;
	}

	@Override
	public Serializable getId() {
		return invoiceId;
	}


}