package com.kikopolis.wordcloudbackend.resource;

import com.kikopolis.wordcloudbackend.service.formdata.Text;
import com.rabbitmq.client.ConnectionFactory;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.springframework.web.bind.annotation.RequestBody;

@Path("/words")
public class WordCloudResource {
    private String queueName = "wordcloud";
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/send")
    public Response hello(@RequestBody final Text data) {
        final String text = data.getText();
        if (text == null || text.isEmpty()) {
            System.out.println(" [x] Text is empty");
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        System.out.println(" [x] Sending '" + text + "'");
        final var factory = new ConnectionFactory();
        factory.setHost("mq");
        factory.setPort(5672);
        factory.setUsername("compose-mq");
        factory.setPassword("compose-mq");
        try (final var connection = factory.newConnection();
        final var channel = connection.createChannel();) {
            channel.queueDeclare(queueName, false, false, false, null);
            channel.basicPublish("", queueName, null, text.getBytes());
            System.out.println(" [x] Sent '" + text + "'");
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return Response.ok().build();
    }
}
