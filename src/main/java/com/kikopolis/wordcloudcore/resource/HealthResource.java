package com.kikopolis.wordcloudcore.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/health")
public class HealthResource {
    @GET
    public Response health() {
        return Response.ok().build();
    }
}
