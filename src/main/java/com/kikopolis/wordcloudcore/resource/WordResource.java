package com.kikopolis.wordcloudcore.resource;

import com.kikopolis.wordcloudcore.service.WordCloudService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.InputStream;

import static jakarta.ws.rs.core.Response.Status.BAD_REQUEST;

@Path("/words")
public class WordResource {
    private final WordCloudService wordCloudService;

    @Autowired
    public WordResource(final WordCloudService wordCloudService) {
        this.wordCloudService = wordCloudService;
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/send")
    public Response hello(
            @FormDataParam("file") final InputStream file,
            @FormDataParam("ignoredWords") final String ignoredWords,
            @FormDataParam("ignoreDefaultWords") final boolean ignoreDefaultWords) {
        final String uuid;
        try {
            uuid = wordCloudService.handleUpload(file, ignoredWords, ignoreDefaultWords);
        } catch (final IllegalArgumentException e) {
            final String errMsg = e.getMessage();
            return Response.status(BAD_REQUEST).entity(errMsg).build();
        }
        return Response.ok().entity(uuid).build();
    }
}
