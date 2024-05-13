package com.demo.springsecuritybasic.filters;


import jakarta.servlet.*;

import java.io.IOException;
import java.util.logging.Logger;

public class LogAfterFilter implements Filter {

    private final Logger logger = Logger.getLogger(LogAfterFilter.class.getName());

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        logger.info("testing after logging");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
