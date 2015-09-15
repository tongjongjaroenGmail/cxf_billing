package com.metasoft.billing.bean.cont;

public enum ClaimType {

	follow("ติดตาม");
	
	private final String value;

    private ClaimType(String value) {
        this.value = value;
    }

	public String getValue() {
		return value;
	}
}
