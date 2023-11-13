package com.example.http.interceptor;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Component
@Order(1)
public class TraceIdHeaderFilter implements Filter {

    public static final String TRACE_ID = "trace-id";

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest httpServletRequest) {
            final String traceId = Optional.ofNullable(httpServletRequest.getHeader(TRACE_ID))
                    .orElse(UUID.randomUUID().toString());
            Map<String, String> customHeaders = Map.of(TRACE_ID, traceId);
            chain.doFilter(new Wrapper(httpServletRequest, customHeaders), response);
        } else {
            chain.doFilter(request, response);
        }
    }

    // Stolen from https://stackoverflow.com/questions/48240291/adding-custom-header-to-request-via-filter
    public static class Wrapper extends HttpServletRequestWrapper {

        private final Map<String, String> customHeaders;

        public Wrapper(
                final HttpServletRequest httpServletRequest,
                final Map<String, String> customHeaders) {
            super(httpServletRequest);
            this.customHeaders = customHeaders;
        }

        private HttpServletRequest getServletRequest() {
            return (HttpServletRequest) getRequest();
        }

        @Override
        public String getHeader(String name) {
            return customHeaders.getOrDefault(name, getServletRequest().getHeader(name));
        }

        @Override
        public Enumeration<String> getHeaders(String name) {
            return Optional.ofNullable(customHeaders.get(name))
                    .map(v -> Collections.enumeration(List.of(v)))
                    .orElseGet(() -> getServletRequest().getHeaders(name));
        }

        @Override
        public Enumeration<String> getHeaderNames() {
            return Collections.enumeration(
                    Stream.concat(
                                customHeaders.keySet().stream(),
                                toStream(getServletRequest().getHeaderNames().asIterator()))
                            .collect(Collectors.toSet()));
        }

        private static Stream<String> toStream(final Iterator<String> iterator) {
            return StreamSupport.stream(
                    Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED),
                    false
            );
        }
    }

}