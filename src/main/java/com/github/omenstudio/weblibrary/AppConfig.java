package com.github.omenstudio.weblibrary;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class AppConfig extends WebMvcConfigurerAdapter {

    public static final String SITE_ADDR = "hhtp://localhost:8080";

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.defaultContentType(JsonLdMediaType.APPLICATION_JSON_LD);
    }

    /**
     * A sub-class of {@link MediaType} that add support HTTP Content-Type for {@code application/ld+json}
     */
    private static class JsonLdMediaType extends MediaType {

        /**
         * A String equivalent of {@link #APPLICATION_JSON_LD}.
         */
        private final static String APPLICATION_JSON_LD_VALUE = "application/ld+json";

        /**
         * Public constant media type for {@code application/ld+json}.
         */
        private final static MediaType APPLICATION_JSON_LD = parseMediaType(APPLICATION_JSON_LD_VALUE);

        public JsonLdMediaType(String type) {
            super(type);
        }
    }
}
