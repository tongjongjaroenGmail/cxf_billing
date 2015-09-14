package com.metasoft.billing.rest.service.impl;

import org.springframework.stereotype.Service;

import com.metasoft.billing.rest.model.DhipRequest;
import com.metasoft.billing.rest.model.DhipResponse;
import com.metasoft.billing.rest.service.DhipManager;

@Service("dhipManagerService")
public class DhipManagerService implements DhipManager{
	@Override
	public DhipResponse calcDhip(DhipRequest request) {
		DhipResponse response = new DhipResponse();
		return response;
	}
}
