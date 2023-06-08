package com.kikopolis.wordcloudcore.config;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.core.MultivaluedMap;

import java.io.IOException;

public class CorsFilter implements ContainerResponseFilter {
    @Override
    public void filter(final ContainerRequestContext requestContext,
                       final ContainerResponseContext responseContext) {
        final MultivaluedMap<String, Object> headers = responseContext.getHeaders();
        headers.add("Access-Control-Allow-Origin", "*");
    }
}
