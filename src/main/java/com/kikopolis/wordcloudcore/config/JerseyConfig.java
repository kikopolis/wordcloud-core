package com.kikopolis.wordcloudcore.config;

import com.kikopolis.wordcloudcore.resource.HealthResource;
import com.kikopolis.wordcloudcore.resource.WordCountResource;
import com.kikopolis.wordcloudcore.resource.WorkOrderResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public final class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(WordCountResource.class);
        register(WorkOrderResource.class);
        register(HealthResource.class);
    }
}
