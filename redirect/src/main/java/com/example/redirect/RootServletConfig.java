package com.example.redirect;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletContainerInitializer;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.Host;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.Collections;

@Configuration
public class RootServletConfig {

    public static final String SOURCE_CONTEXT_PATH = "source";

    @Bean
    public TomcatServletWebServerFactory servletWebServerFactory() {
        return new RedirectTomcatServletWebServerFactory();
    }

    private static class RedirectTomcatServletWebServerFactory extends TomcatServletWebServerFactory {
        @Override
        protected void prepareContext(Host host, ServletContextInitializer[] initializers) {
            super.prepareContext(host, initializers);
            StandardContext child = new StandardContext();
            child.addLifecycleListener(new Tomcat.FixContextListener());
            child.setPath(SOURCE_CONTEXT_PATH);
            ServletContainerInitializer initializer = getServletContextInitializer(getContextPath());
            child.addServletContainerInitializer(initializer, Collections.emptySet());
            child.setCrossContext(true);
            host.addChild(child);
        }

        private ServletContainerInitializer getServletContextInitializer(String contextPath) {
            return (c, context) -> {
                Servlet servlet = new HttpServlet() {
                    @Override
                    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
                        resp.sendRedirect(contextPath + (req.getPathInfo() == null ? "" : req.getPathInfo()));
                    }
                };
                context.addServlet("root", servlet).addMapping("/*");
            };
        }
    }
}