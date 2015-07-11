package com.metasoft.claim.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_invoice database table.
 * 
 */
@Entity
@Table(name="tbl_invoice")
@NamedQuery(name="TblInvoice.findAll", query="SELECT t FROM TblInvoice t")
public class TblInvoice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="invoice_id")
	private int invoiceId;

	@Column(name="invoice_number")
	private String invoiceNumber;

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

}