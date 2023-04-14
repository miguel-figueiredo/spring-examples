package com.example.redirect;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

public class ServletContextFacadeRequestWrapper extends HttpServletRequestWrapper {

    private String contextPath;
    private String servletPath;

    public ServletContextFacadeRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getContextPath() {
        if (StringUtils.isNotBlank(contextPath)) {
            return contextPath;
        }
        return super.getContextPath();
    }

    @Override
    public String getServletPath() {
        if (StringUtils.isNotBlank(servletPath)) {
            return servletPath;
        }
        return super.getServletPath();
    }

    @Override
    public String getRequestURI() {
        String requestURI = super.getRequestURI();
        if (requestURI.equals(contextPath)) {
            return requestURI + "/";
        }
        return requestURI;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    public void setServletPath(String servletPath) {
        this.servletPath = servletPath;
    }

}