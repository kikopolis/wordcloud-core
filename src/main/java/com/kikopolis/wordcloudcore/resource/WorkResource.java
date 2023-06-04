package com.kikopolis.wordcloudcore.resource;

import com.kikopolis.wordcloudcore.entity.WordCount;
import com.kikopolis.wordcloudcore.entity.WorkOrder;
import com.kikopolis.wordcloudcore.service.WordCloudService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static jakarta.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;
import static jakarta.ws.rs.core.Response.Status.NOT_FOUND;

@Path("/work")
public class WorkResource {
    private final WordCloudService wordCloudService;

    @Autowired
    public WorkResource(final WordCloudService wordCloudService) {
        this.wordCloudService = wordCloudService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/order/{uuid}")
    public Response getWorkOrder(@PathParam("uuid") final String uuid) {
        Response response;
        final WorkOrder workOrder;
        try {
            workOrder = wordCloudService.getWorkOrder(uuid);
            response = Response.ok().entity(workOrder).build();
        } catch (final EntityNotFoundException e) {
            response = Response.status(NOT_FOUND).build();
        } catch (final Exception e) {
            response = Response.status(INTERNAL_SERVER_ERROR).build();
        }
        return response;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/status/{uuid}")
    public Response getWorkOrderStatus(@PathParam("uuid") final String uuid) {
        Response response;
        final WorkOrder workOrder;
        try {
            workOrder = wordCloudService.getWorkOrder(uuid);
            response = Response.ok().entity(workOrder.getStatus()).build();
        } catch (final EntityNotFoundException e) {
            response = Response.status(NOT_FOUND).build();
        } catch (final Exception e) {
            response = Response.status(INTERNAL_SERVER_ERROR).build();
        }
        return response;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/counts/{uuid}")
    public Response getWordCounts(@PathParam("uuid") final String uuid) {
        Response response;
        final List<WordCount> wordCounts;
        try {
            wordCounts = wordCloudService.getWordCountsForWorkOrder(uuid);
            response = Response.ok().entity(wordCounts).build();
        } catch (final EntityNotFoundException e) {
            response = Response.status(NOT_FOUND).build();
        } catch (final RuntimeException e) {
            response = Response.status(INTERNAL_SERVER_ERROR).build();
        }
        return response;
    }
}
