package com.kikopolis.wordcloudbackend;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {
    public static void main(final String[] args) {
        final var application = new Application();
        final var configure = application.configure(new SpringApplicationBuilder(Application.class));
        configure.run(args);
    }
}
