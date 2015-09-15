package com.metasoft.billing.rest.model;

public class DhipResponse extends Response {
	private Dhip dhip = new Dhip();

	public Dhip getDhip() {
		return dhip;
	}

	public void setDhip(Dhip dhip) {
		this.dhip = dhip;
	}

}
