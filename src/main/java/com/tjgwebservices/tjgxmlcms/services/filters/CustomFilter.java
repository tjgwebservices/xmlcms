package com.tjgwebservices.tjgxmlcms.services.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component("loggingFilter")
public class CustomFilter implements Filter  {

    private static Logger LOGGER = LoggerFactory.getLogger(CustomFilter.class);
 
    public CustomFilter(){}
    
    @Override
    public void init(FilterConfig config) throws ServletException {
            LOGGER.info("Custom Filter init");
    }

    @Override
    public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain fc) throws IOException, ServletException {
            fc.doFilter(sr, sr1);
            LOGGER.info("Custom Filter Do Filter");
    }

    @Override
    public void destroy() {
        Filter.super.destroy(); 
    }
 
}
