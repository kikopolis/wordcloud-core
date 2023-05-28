package com.kikopolis.wordcloudbackend.config;

import com.kikopolis.wordcloudbackend.resource.HealthResource;
import com.kikopolis.wordcloudbackend.resource.WordCloudResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public final class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(WordCloudResource.class);
        register(HealthResource.class);
    }
}
