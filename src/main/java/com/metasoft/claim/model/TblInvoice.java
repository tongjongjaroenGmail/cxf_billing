package com.metasoft.claim.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
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
	@Column(name="invoice_id")
	private int invoiceId;

	@Column(name="invoice_number")
	private String invoiceNumber;
	
	//bi-directional many-to-one association to TblClaimRecovery
	@ManyToOne
	@JoinColumn(name="claim_recovery_id")
	private TblClaimRecovery tblClaimRecovery;

	public TblInvoice() {
	}

	public int getInvoiceId() {
		return this.invoiceId;
	}

	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}

	public String getInvoiceNumber() {
		return this.invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	public TblClaimRecovery getTblClaimRecovery() {
		return this.tblClaimRecovery;
	}

	public void setTblClaimRecovery(TblClaimRecovery tblClaimRecovery) {
		this.tblClaimRecovery = tblClaimRecovery;
	}

	@Override
	public Serializable getId() {
		return invoiceId;
	}
}