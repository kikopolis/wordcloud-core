package com.kikopolis.wordcloudcore.resource;

import com.kikopolis.wordcloudcore.service.WordCountService;
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

@Path("/word-count")
public class WordCountResource {
    private final WordCountService wordCountService;

    @Autowired
    public WordCountResource(final WordCountService wordCountService) {
        this.wordCountService = wordCountService;
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/file")
    public Response countFromFile(
            @FormDataParam("file") final InputStream file,
            @FormDataParam("ignoredWords") final String ignoredWords,
            @FormDataParam("ignoreDefaultWords") final boolean ignoreDefaultWords,
            @FormDataParam("autoCorrectGrammar") final boolean autoCorrectGrammar) {
        final String uuid;
        try {
            uuid = wordCountService.countFromFile(file, ignoredWords, ignoreDefaultWords, autoCorrectGrammar);
        } catch (final IllegalArgumentException e) {
            final String errMsg = e.getMessage();
            return Response.status(BAD_REQUEST).entity(errMsg).build();
        }
        return Response.ok().entity(uuid).build();
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/text")
    public Response countFromText(
            @FormDataParam("text") final String text,
            @FormDataParam("ignoredWords") final String ignoredWords,
            @FormDataParam("ignoreDefaultWords") final boolean ignoreDefaultWords,
            @FormDataParam("autoCorrectGrammar") final boolean autoCorrectGrammar) {
        final String uuid;
        try {
            uuid = wordCountService.countFromText(text, ignoredWords, ignoreDefaultWords, autoCorrectGrammar);
        } catch (final IllegalArgumentException e) {
            final String errMsg = e.getMessage();
            return Response.status(BAD_REQUEST).entity(errMsg).build();
        }
        return Response.ok().entity(uuid).build();
    }
}
