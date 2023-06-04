package com.kikopolis.wordcloudcore.config;

import com.kikopolis.wordcloudcore.resource.HealthResource;
import com.kikopolis.wordcloudcore.resource.WordResource;
import com.kikopolis.wordcloudcore.resource.WorkResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public final class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(WordResource.class);
        register(WorkResource.class);
        register(HealthResource.class);
    }
}
