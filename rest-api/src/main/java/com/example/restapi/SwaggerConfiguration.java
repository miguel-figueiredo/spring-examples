package com.example.restapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SwaggerConfiguration {

    private final String contextPath;
    private final String springdocApiDocsPath;

    public SwaggerConfiguration(@Value("${server.servlet.context-path}") String contextPath,
                                @Value("${springdoc.api-docs.path}") String springdocApiDocsPath
    ) {
        this.contextPath = contextPath;
        this.springdocApiDocsPath = springdocApiDocsPath;
    }

    public String getRedirectUrl() {
        return "index.html?url=" + contextPath + springdocApiDocsPath;
    }
}
