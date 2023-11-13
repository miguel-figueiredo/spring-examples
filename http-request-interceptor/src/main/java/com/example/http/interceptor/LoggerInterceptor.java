package com.example.http.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Slf4j
public class LoggerInterceptor implements HandlerInterceptor {

    public static final String TRACE_ID = "trace-id";

    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler){
        // The trace-id header is injected in TraceIdHeaderFilter if null
        MDC.put(TRACE_ID, request.getHeader(TRACE_ID));
        log.info("Prgie-handling request: {}", request.getRequestURI());
        return true;
    }

    @Override
    public void postHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler, final ModelAndView modelAndView) throws Exception {
        log.info("Post-handling request: {}", request.getRequestURI());
        MDC.clear();
    }
}