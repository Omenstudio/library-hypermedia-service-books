package com.github.omenstudio.weblibrary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class RequestTracer implements Filter {
    private final Logger logger = LoggerFactory.getLogger(RequestTracer.class);

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;
        String url = request.getServletPath();
        chain.doFilter(req, res);
        logger.info(url + " - " + request.getMethod() + " - " + response.getStatus());
    }

    public void init(FilterConfig filterConfig) {}

    public void destroy() {}

}