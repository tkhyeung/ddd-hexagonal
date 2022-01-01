package com.example.ddd.config.interceptor;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//This class is required to wrap the request and response, and pass along to the servlet in order to cache the request body and response body,
// so that the interceptor can read the body.
@Slf4j
public class RequestResponseWrappingLogFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
            RequestWrapper requestWrapper = new RequestWrapper((HttpServletRequest) request);
            ResponseWrapper responseWrapper = new ResponseWrapper((HttpServletResponse) response);
            log.info("Wrapped request response and chain.doFilter");
            chain.doFilter(requestWrapper, responseWrapper);
        } else {
            log.info("chain.doFilter");
            chain.doFilter(request, response);
        }

    }
}
