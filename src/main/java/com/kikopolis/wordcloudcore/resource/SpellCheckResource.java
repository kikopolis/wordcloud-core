package com.kikopolis.wordcloudcore.resource;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.glassfish.jersey.media.multipart.FormDataParam;

import java.io.InputStream;

@Path("/spell-check")
public class SpellCheckResource {
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/file")
    public Response verifyText(@FormDataParam("file") final InputStream file) {
        return Response.ok().entity("Hello World").build();
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/text")
    public Response verifyText(@FormDataParam("text") final String text) {
        return Response.ok().entity("Hello World").build();
    }
}
