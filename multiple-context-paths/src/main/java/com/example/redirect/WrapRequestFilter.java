package com.example.redirect;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class WrapRequestFilter extends OncePerRequestFilter {

    private static final String[] PATHS = new String[] { "/food", "/equipment" };

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        ServletContextFacadeRequestWrapper wrapper = new ServletContextFacadeRequestWrapper(request);
        String path = getMatchingContextPathForRequest(request);
        if (path != null) {
            wrapper.setContextPath(request.getContextPath() + path);
            String newPath = request.getServletPath().substring(path.length());
            if (newPath.length() == 0) {
                newPath = "/";
            }
            wrapper.setServletPath(newPath);
        }
        filterChain.doFilter(wrapper, response);
    }

    public String getMatchingContextPathForRequest(HttpServletRequest request) {
        for (String path : PATHS) {
            if (request.getServletPath().startsWith(path)) {
                return path;
            }
        }
        return null;
    }

}