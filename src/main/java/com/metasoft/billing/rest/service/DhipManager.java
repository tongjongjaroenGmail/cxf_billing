package com.metasoft.billing.rest.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.metasoft.billing.rest.model.DhipRequest;
import com.metasoft.billing.rest.model.DhipResponse;

@Consumes("application/json")
@Produces("application/json")
public interface DhipManager
{
	@POST
	@Path("/calcDhip/")
	public DhipResponse calcDhip(DhipRequest request);
}